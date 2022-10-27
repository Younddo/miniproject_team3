## 항해99 9기 C반 미니프로젝트 backend github 

## 🌻 프로젝트 주제
 항해99 9기 C반을 위한 커뮤니티 사이트 🌼

C반 구성원들끼리 자유롭게 소통하고📢 질문하고🙋 일상을 공유💞할 수 있는 SNS

📅 프로젝트 기간 : 2022.10.21-2022.10.27

## 👨‍👩‍👧‍👦 Our Team 
|장윤서|정성우|정동훈|오기쁨|손성화|
|:---:|:---:|:---:|:---:|:---:|
|@younddo|@anfrosus|@dhun0103|@joyfive|tjdghkek88@gmail.com|
|BE💙|BE💙|BE💙|FE💚|FE💚|

## 📝 Technologies & Software Used

<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white"/>  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=white"/>  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/>   <img src="https://img.shields.io/badge/github-181717?style=flat-square&logo=github&logoColor=white"/>  

<img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white"/>  <img src="https://img.shields.io/badge/java-FF81F9?style=flat-square"/>  <img src="https://img.shields.io/badge/JSONWebToken-000000?style=flat-square&logo=JsonWebToken&logoColor=white"/>  <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white"/>  <img src="https://img.shields.io/badge/IntelliJIDEA-000000?style=flat-square&logo=IntelliJIDEA&logoColor=white"/>  <img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white"/>  <img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=Notion&logoColor=white"/>

<img src="https://img.shields.io/badge/AmazonS3-569A31?style=flat-square&logo=AmazonS3&logoColor=white"/>  <img src="https://img.shields.io/badge/AmazonEC2-FF9900?style=flat-square&logo=AmazonEC2&logoColor=white"/>  <img src="https://img.shields.io/badge/AmazonRDS-527FFF?style=flat-square&logo=AmazonRDS&logoColor=white"/>  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=flat-square&logo=Ubuntu&logoColor=white"/>

## [📚 프로젝트 팀 노션 보러가기](https://real-relish-378.notion.site/9C-W6-3-S-A-c6efadb6c1214d92a67c40b615a69d10) 

## ✨ 프로젝트 구현 기능

1. 홈 화면 (/auth/home) 💒

    항해 최종발표일까지의 D-Day 표시와, 7가지의 랜덤 문구를 선정하여 사용자가 볼 수 있도록 구현하였습니다.

2. 회원가입 로그인 🔑

    JWT + Security 적용하여 구현하였습니다.

3. 게시글 조회 📜

    최신순,좋아요순 / 각 조별(all,1~6) / 태그별 (all, 일상, 공유, 질문, 공지) 태그를 만들어 param 값으로 받아 조건 별 조회가 가능합니다.

    작성시간을 '몇 분전', '몇 시간전', '며칠 전'으로 변경하여 반영할 수 있도록 구현하였습니다.

4. 게시글 작성 📝

    사용자가 업로드한 이미지를 Amazon S3로 저장하여 이미지 URL을 받아와 DB에 저장하여 관리합니다.

    작성자 account에 담겨있는 accountTeam(조)를 받아와 post에 함께 저장합니다.

    Tag 중 "공지"의 경우, 팀장의 권한을 가진 사람만 작성할 수 있습니다.

5. 게시글 좋아요 💗

    사용자가 게시글에 좋아요를 누를 수 있고 다시 한 번 누르면 좋아요가 취소됩니다.

    실시간으로 좋아요 수가 반영되게 하기 위하여 좋아요의 size를 따로 관리하였습니다.

6. 댓글 및 댓글 좋아요 💖

    게시글에 댓글을 작성할 수 있고, 작성자만 삭제 할 수 있으며 게시글과 같이 좋아요 기능이 포함되어 있습니다.

7. 마이페이지 🌝

    한 줄 소개를 작성하여 자신을 소개할 수 있습니다. 

    내가 작성한 글, 댓글을 확인할 수 있습니다.

    작성한 글이나 댓글로 그 글의 상세페이지를 확인할 수 있습니다. 

8. 예외처리 📛

    Custom ErrorCode를 Enum으로 관리하여 프론트엔드와 명확하게 소통하였습니다. 

## 🏀 Trouble Shooting
글에 달린 댓글들을 List로 반환 할 때 JPA관계 설정으로 인해 순환참조의 문제를 직면했습니다.

저희는 단순하게 Response 시 댓글List가 담길 객체의 멤버로 Entity를 보유하지 않는 방식을 선택하여 해결했습니다.

게시글을 조건별로 정렬하여 조회하기 위해 좋아요갯수 등을 Post의 Column으로 관리하였습니다.

## 📋 API Table
|기능|method|URL|request|response|error code|
|:-----:|:---|:---|:---|:---|:---|
|홈|GET|/auth/home||{"goodWord": "팀원들과 예쁜말로 소통하고 있나요??","dday": -53}||
|중복확인|POST|auth/check|{“email” : "you1dsf"}|{"msg": "사용가능한 아이디입니다","statusCode": 200}|{"status": 400,"code": "AlreadyHaveEmail","message": "이미 존재하는 아이디 입니다."}{"status": 400,"code": "Size","message": "아이디는 4~12 개의 문자만 허용합니다."}|
|회원가입|POST|/auth/signup|{"email" : "you1dsf","accountName" : "짱윤서","accountPw" : "@weffs3424A","accountPwConfirm": "@weffs3424A","accountTeam": "3","accountLeader": false}|{"msg": "Success signup","statusCode": 200}|===비밀번호 조건이 부합하지 않을 때===[{"status": 400,"code": "NotBlank","message": "비밀번호는 공백일 수 없습니다."},{"status": 400,"code": "Size","message": "비밀번호는 8~!6 개의 문자만 허용합니다."},{"status": 400,"code": "Pattern","message": "비밀번호는 무조건 영문, 숫자, 특수문자를 각각 1글자 이상 포함해야 합니다."}]{"status": 400,"code": "NotMatchPassword","message": "비밀번호가 일치하지 않습니다."}|
|로그인|POST|auth/login|{"email" : "you1dsf","accountPw" : "@weffs3424A"}|{"msg": "Success Login","statusCode": 200}|{"status": 400,"code": "NotMatchPassword","message": "비밀번호가 일치하지 않습니다."}{"status": 400,"code": "NotFoundUser","message": "아이디가 존재하지 않습니다."}|
|게시글 작성|POST|api/posts|{img : ~~~.jpg, "title" : "제목입니다", "contents" : "내용입니다", "tag” : “일상”}|{"msg": "Success Post","statusCode": 200}||
|게시글 수정|PUT|api/posts/{postId}|img : ~~~.jpg, "title" : "제목입니다", "contents" : "내용입니다", "tag” : “일상”|{"modifiedAt": "2022-11-21T14:24:52.4777783"}|{"status": 400,"code": "NotMatchUser","message": "작성자가 일치하지 않습니다"}|
|게시글 삭제|DELETE|/api/posts/{postId}||{"msg": "게시글 삭제가 완료되었습니다!","statusCode": 200}|{"status": 400,"code": "NotMatchUser","message": "작성자가 일치하지 않습니다"}|
|게시글 좋아요|GET|api/{postId}/like||{    "success": true, "data": "게시글 좋아요 완료",“likesCount”: 2, "myError": null}||
|게시글 전체조회|GET|/api/posts?sort=createdAt&accountTeam=All&tag=All||{"postId": 4,"accountName": "짱윤서","title": "제목","contents": "내용","tag": "일상","comments": [],"postLikeCount": 1,"createdAt": "27분 전","modifiedAt": "21분 전","img": "https://mysparta4.s3.ap-northeast-2.amazonaws.com/testdir1/4762d4cf-8400-46df-b52e-944bbb3231f9KakaoTalk_20201226_121850849.jpg"}||
|우리조 게시글 조회하기|GET|/api/posts/myteam|{"postId": 4,"accountName": "짱윤서","title": "제목","contents": "내용","tag": "일상","comments": [],"postLikeCount": 1,"createdAt": "27분 전","modifiedAt": "21분 전","img": "https://mysparta4.s3.ap-northeast-2.amazonaws.com/testdir1/4762d4cf-8400-46df-b52e-944bbb3231f9KakaoTalk_20201226_121850849.jpg"}||
|댓글 작성|POST|api/{postId}/comments|{”comments”:”댓글입니다”}|{"postId": 2,"commentId": 3,"accountName": "짱윤서","comment": "댓글1","commentLikes": 0,"createdAt": "방금 전"}|{"status": 404,"code": "NotFoundPost","message": "게시물을 찾을 수 없습니다."}|
|댓글 삭제|DELETE|/api/{postId}/comments/{commentId}||{"data": "댓글 삭제가 완료되었습니다."}||
|댓글 좋아요|GET|/api/{postId}/comments/{commentId}/like||{"data": "댓글 좋아요 완료","likesCount": 1}||
|마이페이지 조회|GET|/api/myPage||{"accountName": "짱윤서","accountTeam": "3조","oneSentence": null,"myPost": [{"postId": 2,"accountName": "짱윤서","title": "제목","contents": "내용","tag": "일상","comments": [],"postLike": 1,"createdAt": "3분 전","modifiedAt": "2분 전","img": "https://mysparta4.s3.ap-northeast-2.amazonaws.com/testdir1/3fb27fd6-9e4e-420e-8cb0-633da85eee50KakaoTalk_20201226_121850849.jpg"},"myComment": [{"postId": 2,"commentId": 2,"accountName": "짱윤서","comment": "댓글1","commentLikes": 0,"createdAt": "3분 전"},]}||
|마이페이지 한줄 쓰기|POST|/api/myPage|{”oneSentence”:”짱성우다.”}|{"accountName": null,"accountTeam": null,"oneSentence": " 짱성우다.","myPost": null,"myComment": null}||
|마이페이지 한줄 수정|PUT|/api/myPage|{”oneSentence”:”저는 정성우가 아닙니다”}|{"accountName": null,"accountTeam": null,"oneSentence": " 저는 정성우가 아닙니다.","myPost": null,"myComment": null}||




## 📜 ERD Table
![image](https://user-images.githubusercontent.com/99253403/198083569-a8d7786c-97f2-4266-9034-52bdc22d8c7f.png)
