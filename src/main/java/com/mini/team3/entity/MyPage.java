package com.mini.team3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class MyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MyPageId;

    private String oneSentence;

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;


}
