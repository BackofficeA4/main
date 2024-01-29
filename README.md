# - BackofficeA4 Project -

<div align=center><h1>📚 STACKS</h1></div>
<div>

<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/kotlin-339AF0?style=for-the-badge&logo=kotlin&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<br>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  
</div>




## 프로젝트 주요 목적
저희 프로젝트는 도메인 주도 설계 일명 DDD를 기반으로 참고하여
DDD의 핵심 목표로 각각의 도메인이 서로 철저하게 분리되고 낮은 결합도로
변경과 확장에 용이한 설계를 하여 협업을 원활하게 하도록 시도하였습니다.


-------------------------------------------------------------
## 프로젝트 기능 설명
- 프로필 수정 부분의 Sequence Diagram
  1. 유저가 프로필 수정을 시도하면 서버에 권한 요청
  2. 서버는 유저의 권한을 판별 후 결과 반환
- 익명닉네임 생성
  1. Apache commons lang 라이브러리 사용



-------------------------------------------------------------------
## 프로젝트 구조
```bash
📦:a4document
 ┣ 📂:api
 ┃ ┣ :📂:admin
 ┃ ┃ ┣ :📂:controller
 ┃ ┃ ┗ :📂:service
 ┃ ┣ :📂:auth
 ┃ ┃ ┣ :📂:controller
 ┃ ┃ ┗ :📂:service
 ┃ ┣ :📂:member
 ┃ ┃ ┣ :📂:controller
 ┃ ┃ ┗ :📂:service
 ┃ ┗ :📂:post
 ┃ ┃ ┣ :📂:controller
 ┃ ┃ ┗ :📂:service
 ┣ :📂:common
 ┃ ┣ :📂:member
 ┃ ┃ ┣ :📂:auth
 ┃ ┃ ┃ ┣ :📂:config
 ┃ ┃ ┃ ┣ :📂:dto
 ┃ ┃ ┃ ┣ :📂:exception
 ┃ ┃ ┃ ┣ :📂:jwt
 ┃ ┃ ┃ ┃ ┣ :📂:exception
 ┃ ┃ ┃ ┃ ┣ :📂:token
 ┃ ┃ ┃ ┗ :📂:util
 ┃ ┃ ┣ :📂:dto
 ┃ ┃ ┣ :📂:entity
 ┃ ┃ ┣ :📂:exception
 ┃ ┃ ┣ :📂:repository
 ┃ ┃ ┣ :📂:service
 ┃ ┃ ┗ :📂:type
 ┃ ┗ :📂:util
 ┣ :📂:domain
 ┃ ┗ :📂:post
 ┃ ┃ ┣ :📂:comment
 ┃ ┃ ┃ ┣ :📂:dto
 ┃ ┃ ┃ ┣ :📂:entity
 ┃ ┃ ┃ ┣ :📂:exception
 ┃ ┃ ┃ ┣ :📂:repository
 ┃ ┃ ┃ ┗ :📂:service
 ┃ ┃ ┣ :📂:dto
 ┃ ┃ ┣ :📂:entity
 ┃ ┃ ┣ :📂:repository
 ┃ ┃ ┗ :📂:service
 ┣ :📂:global
 ┃ ┣ :📂:exception
 ┃ ┗ :📂:util
 ┣ :📂:infra
 ┃ ┣ :📂:security
 ┃ ┃ ┣ :📂:config
 ┃ ┗ :📂:swagger
 ┣ :📂:system
 ┃ ┣ :📂:admin
 ┃ ┗ :📂:errorobject
```


