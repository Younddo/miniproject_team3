package com.mini.team3.service;

import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.*;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.Comment;
import com.mini.team3.entity.Post;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.repository.CommentRepository;
import com.mini.team3.repository.PostRepository;
import com.mini.team3.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;

    private final CommentRepository commentRepository;

    // 게시글 작성
    @Transactional
    public GlobalResponseDto createPost(MultipartFile multipartFile, PostRequestDto postRequestDto, Account account) throws IOException {

        if(!(account.getAccountLeader())&&postRequestDto.getTag().equals("공지")){
                throw new CustomException(ErrorCode.NotTeamLeader);
        }

        if(!(multipartFile==null)) {
            String img = s3Uploader.uploadFiles(multipartFile, "testdir1");

            Post post = new Post(postRequestDto, account, img);
            postRepository.save(post);
        }else{
            Post post = new Post(postRequestDto, account);
            postRepository.save(post);
        }

        return new GlobalResponseDto("Success Post", HttpStatus.OK.value());
    }

    // 게시글 수정
    @Transactional
    public PostUpdateDto updatePost(Long postId, MultipartFile multipartFile, PostRequestDto postRequestDto, Account account) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundPost)
        );

        String img = s3Uploader.uploadFiles(multipartFile, "testdir1");

        if (post.getAccount().getEmail().equals(account.getEmail())) {
            post.update(postRequestDto, img);

            return new PostUpdateDto(post);
        } else {
            throw new CustomException(ErrorCode.NotMatchUser);
        }
    }

    // 게시물 삭제
    @Transactional
    public GlobalResponseDto deletePost(Long postId, Account account) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundPost)
        );

        if (post.getAccount().getEmail().equals(account.getEmail())) {
            postRepository.deleteById(postId);
            return new GlobalResponseDto("게시글 삭제가 완료되었습니다!", HttpStatus.OK.value());
        } else {
            throw new CustomException(ErrorCode.NotMatchUser);
        }
    }

    // 게시물 전체 조회
    // 1. 디폴트 -> 시간순, 전체, 전체
    // 2. 시간순 -> 필터
    // 3. 좋아요순 -> 필터
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllPosts(String sort, String accountTeam, String tag) {
        List<Post> postsList = new ArrayList<>();
        // 시간순 일 때
        if (sort.equals("createdAt")) {
            if (accountTeam.equals("All")) {
                if (tag.equals("All")) {
                    postsList = postRepository.findAllByOrderByCreatedAtDesc();
                } else {
                    postsList = postRepository.findPostsByTagOrderByCreatedAtDesc(tag);
                }
            } else {
                if (tag.equals("All")) {
                    postsList = postRepository.findPostsByAccount_AccountTeamOrderByCreatedAtDesc(accountTeam);
                } else {
                    postsList = postRepository.findPostsByAccount_AccountTeamAndTagOrderByCreatedAtDesc(accountTeam, tag);
                }
            }
        } else if (sort.equals("likeCount")) {
            if (accountTeam.equals("All")) {
                if (tag.equals("All")) {
                    postsList = postRepository.findAllByOrderByPostLikeCountDescCreatedAtDesc();
                } else {
                    postsList = postRepository.findPostsByTagOrderByPostLikeCountDescCreatedAtDesc(tag);
                }
            } else {
                if (tag.equals("All")) {
                    postsList = postRepository.findPostsByAccount_AccountTeamOrderByPostLikeCountDescCreatedAtDesc(accountTeam);
                } else {
                    postsList = postRepository.findPostsByAccount_AccountTeamAndTagOrderByPostLikeCountDescCreatedAtDesc(accountTeam, tag);
                }
            }
        }
        List<PostResponseDto> postsList1 = new ArrayList<>();
        for (Post post : postsList) {
            List<CommentResponseDto> comment1 = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                comment1.add(new CommentResponseDto(comment));
            }
            postsList1.add(new PostResponseDto(post, comment1));
        }
        return postsList1;
    }

    // 우리 조 게시글 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> findTeamPosts(String sort, String accountTeam, String tag) {
        List<Post> postList = new ArrayList<>();
        if (sort.equals("최신순")) {
            if (tag.equals("All")) {
                postList = postRepository.findPostsByAccount_AccountTeamOrderByCreatedAtDesc(accountTeam);
            } else {
                postList = postRepository.findPostsByAccount_AccountTeamAndTagOrderByCreatedAtDesc(accountTeam, tag);
            }

        } else if (sort.equals("좋아요순")) {
            if (tag.equals("All")) {
                postList = postRepository.findPostsByAccount_AccountTeamOrderByPostLikeCountDescCreatedAtDesc(accountTeam);
            } else {
                postList = postRepository.findPostsByAccount_AccountTeamAndTagOrderByPostLikeCountDescCreatedAtDesc(accountTeam, tag);
            }
        }
        List<PostResponseDto> postsList1 = new ArrayList<>();
        for (Post post : postList) {
            List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                commentResponseDtos.add(new CommentResponseDto(comment));
            }
            postsList1.add(new PostResponseDto(post, commentResponseDtos));
        }
        return postsList1;
    }

    // 게시글 상세조회
    @Transactional(readOnly = true)
    public PostResponseDto getOnePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundPost)
        );
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            commentResponseDtos.add(new CommentResponseDto(comment));
        }
        PostResponseDto postResponseDto = new PostResponseDto(post, commentResponseDtos);
        return postResponseDto;
    }

}
