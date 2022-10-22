package com.mini.team3.service;

import com.mini.team3.dto.request.CommentRequestDto;
import com.mini.team3.dto.response.CommentLikeResponseDto;
import com.mini.team3.dto.response.CommentResponseDto;
import com.mini.team3.dto.response.DataResponseDto;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.Comment;
import com.mini.team3.entity.CommentLike;
import com.mini.team3.entity.Post;
import com.mini.team3.repository.CommentLikeRepository;
import com.mini.team3.repository.CommentRepository;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public ResponseEntity createComment(Long postId, CommentRequestDto commentRequestDto, Account currentAccount) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 글 없음"));
        Comment comment = new Comment(commentRequestDto, post, currentAccount);

        return new ResponseEntity(
                new CommentResponseDto(postId, comment.getCommentId(), commentRequestDto.getComments()),
                HttpStatus.OK
        );
    }

    @Transactional
    public ResponseEntity deleteComment(Long commentId ,Account currentAccount) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        if (comment.getAccount().getAccountId().equals(currentAccount.getAccountId())){
            commentRepository.deleteById(commentId);
            return new ResponseEntity(
                    new DataResponseDto("댓글 삭제가 완료되었습니다."),
                    HttpStatus.OK
            );
        }else {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
    }

    @Transactional
    public ResponseEntity likeComment(Long commentId, Account currentAccount) {

        String msg;
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        Integer commentLikeSize = comment.getCommentLikes().size();

        if (!commentLikeRepository.existsByCommentIdAndAccountId(commentId, currentAccount.getId())) {
            CommentLike commentLike = new CommentLike(comment, comment.getPost(), currentAccount);
            comment.updateSize(commentLikeSize + 1);
            commentLikeRepository.save(commentLike);
            msg = "댓글 좋아요 완료";

        }else {
            commentLikeRepository.deleteByCommentIdAndAccountId(commentId, currentAccount.getId());
            msg = "댓글 좋아요 취소";
            comment.updateSize(commentLikeSize - 1);
        }

        return new ResponseEntity(
                new CommentLikeResponseDto(msg, comment.getCommentLikeSize()),
                HttpStatus.OK
        );
    }
}
