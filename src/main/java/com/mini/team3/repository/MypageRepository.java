package com.mini.team3.repository;

import com.mini.team3.entity.MyPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<MyPage, Long> {
}
