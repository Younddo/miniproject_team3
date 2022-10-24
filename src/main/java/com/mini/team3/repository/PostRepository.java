package com.mini.team3.repository;

import com.mini.team3.entity.Account;
import com.mini.team3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //시간순 정렬
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findPostsByTagOrderByCreatedAtDesc(String tag);
    List<Post> findPostsByAccount_AccountTeamOrderByCreatedAtDesc(String accountTeam);
    List<Post> findPostsByAccount_AccountTeamAndTagOrderByCreatedAtDesc(String accountTeam, String tag);

    //좋아요 정렬
    List<Post> findAllByOrderByPostLikeCountDescCreatedAtDesc();
    List<Post> findPostsByTagOrderByPostLikeCountDescCreatedAtDesc(String tag);
    List<Post> findPostsByAccount_AccountTeamOrderByPostLikeCountDescCreatedAtDesc(String accountTeam);
    List<Post> findPostsByAccount_AccountTeamAndTagOrderByPostLikeCountDescCreatedAtDesc(String accountTeam, String tag);


    List<Post> findPostsByAccount(Account account);
}
