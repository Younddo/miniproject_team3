package com.mini.team3.service;

import com.mini.team3.dto.request.CommentRequestDto;
import com.mini.team3.dto.response.CommentLikeResponseDto;
import com.mini.team3.dto.response.CommentResponseDto;
import com.mini.team3.dto.response.DataResponseDto;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.Comment;
import com.mini.team3.entity.CommentLike;
import com.mini.team3.entity.Post;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.repository.CommentLikeRepository;
import com.mini.team3.repository.CommentRepository;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(Long postId, CommentRequestDto commentRequestDto, Account account) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 글 없음"));
        Comment comment = new Comment(commentRequestDto, post, account);
        commentRepository.save(comment);

        return new ResponseEntity(
                new CommentResponseDto(comment),
                HttpStatus.OK
        );
    }

    @Transactional
    public ResponseEntity deleteComment(Long commentId ,Account account) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundComment)
        );
        if (comment.getAccount().getId().equals(account.getId())){
            commentRepository.deleteById(commentId);
            return new ResponseEntity(
                    new DataResponseDto("댓글 삭제가 완료되었습니다."),
                    HttpStatus.OK
            );
        }else {
            throw new CustomException(ErrorCode.CantDelete);
        }
    }

    @Transactional
    public ResponseEntity likeComment(Long commentId, Account account) {

        String msg;
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundComment));
        Integer commentLikeSize = comment.getCommentLikes().size();

        Optional<CommentLike> commentLike = commentLikeRepository.findByCommentAndAccount(comment, account);

        if (!commentLike.isPresent()) {
            CommentLike newCommentLike = new CommentLike(comment, comment.getPost(), account);
            comment.updateSize(commentLikeSize + 1);
            commentLikeRepository.save(newCommentLike);
            msg = "댓글 좋아요 완료";

        }else {
            commentLikeRepository.deleteByCommentAndAccount(comment, account);
            msg = "댓글 좋아요 취소";
            comment.updateSize(commentLikeSize - 1);
        }

        return new ResponseEntity(
                new CommentLikeResponseDto(msg, comment.getCommentLikeSize()),
                HttpStatus.OK
        );
    }
}
