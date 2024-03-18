# MyPetLife

## Introduction

<img src="https://github.com/daeun0220/MyPetLife/assets/81563958/174316d5-c203-4725-b03e-d8e73a1ee182" width="100" height="100"/>
<br>

📚 프로젝트 소개
  - 지금까지 겪었던 다양한 에러나 문제 상황, 효율성 증대 방법들을 정리하기 위해 만들었습니다.
  - 반려동물 커뮤니티 서비스 입니다.


## ERD
![erd](https://github.com/daeun0220/MyPetLife/assets/81563958/d17c5861-adc6-4645-a348-d8ccf3c671eb")

---
<br>
<details>
  <summary>📂 서비스 주요 기능</summary>
  
  ### Board
    - 게시글 CRUD
    - 게시글 목록 조회
    - 제목 검색 동적 쿼리
    - 본인이 작성한 게시글 목록 조회
    - 본인이 좋아요한 게시글 목록 조회
  ### Comment
    - 댓글 CRUD
    - 대댓글 CRUD
  ### Likes
    - 게시글 '좋아요' 하기
    - 게시글 '좋아요' 취소
  ### User
    - OAuth2 + Jwt + Spring Security
    - 소셜 로그인 
  ### Chat
    - 채팅 기능 (구현 예정) 
</details>

## Description
🔎 목차
  - [nGrinder를 사용한 부하 테스트](#nGrinder-를-사용한-부하-테스트)
  - [인덱스를 통한 쿼리 성능 개선](#인덱스를-통한-쿼리-성능-개선)
  - [N+1 문제 해결](#n1-문제-해결)
  - [QueryDSL을 사용한 동적 쿼리 기능](#querydsl-을-사용한-동적-쿼리-기능)
  - [OAuth2 + JWT 기반 로그인 기능](#oauth2--jwt-기반-로그인-기능)
  - [좋아요 기능 동시성 제어](#좋아요-기능-동시성-제어)
  - [단위 테스트 작성](#단위-테스트-작성)
    
---

## nGrinder 를 사용한 부하 테스트

**1. nGrinder**
- nGrinder는 네이버에서 제공하는 서버 부하 테스트 오픈 소스 프로젝트입니다.
- `Controller` : 성능 측정을 위한 웹 인터페이스를 제공하며, Web Application으로 Tomcat과 같은 웹서버 엔진을 이용하여 구동할 수 있습니다.
- `Agent` : Controller의 명령을 받아 실행하며, target에 프로세스와 스레드를 실행시켜 부하를 발생시킵니다.
- `Target` : 테스트하려는 target 애플리케이션을 의미합니다.
  
**2. 같은 환경에서 테스트하기**
- `Agent`: 성능 측정에 사용할 Agent 개수.
  - 일반적인 로컬에서 테스트를 실행할 경우 1이 고정값
  - Agent를 여러개로 구성하고 싶은 경우 Docker 나 cloud service 를 사용해 동시적으로 실행시켜야 합니다.
- `Vuser per agent` : 동시에 요청을 날리는 사용자 수
- `Process / Thread`: 한 Agent 에서 생성할 프로세스와 스레드 수
- `Run Count / Duration` : Run Count 와 Duration 중 선택하여 얼마나 오래, 많이 테스트를 실행할 것인지 정합니다.
- `Ramp-up` : 점진적으로 부하를 테스트 할 때 사용합니다.

**3. 부하 테스트 지표**
- `TPS`: 일정 시간동안 얼마나 많은 요청을 처리할수 있는지를 나타내는 지표로 성능 테스팅의 주요 지표로 활용됩니다.
   - TPS 가 높기만 해서 성능 좋은 서버를 의미하는 것은 아닙니다.
   - 사용자가 늘어나면서 TPS 가 높아지다 한계를 만나면 거의 증가하지 않는 그래프 특성을 띄는 것이 가장 이상적입니다.
- `Mean Test Time`: 평균 테스트 시간
   - 사용자가 늘어나면서 조금씩 높아지다 특정 지점에서 급증하는 그래프의 형태를 갖습니다.
- 부하 테스트 지표를 분석할 때에는 사용자, TPS, Time 의 관계를 함께 고려하는 것이 좋습니다.
  
**3. 주의할 점**
- nGrinder 를 사용하는 방법에는 깃허브 릴리즈 페이지에서 다운로드 받는 방법과 도커로 설치하는 방법이 있습니다.
- 저는 도커를 사용했는데 이때 nGrinder 의 localhost(127.0.0.1)는 내 노트북 ip 가 아닌 도커의 ip 를 의미합니다. nGrinder 가 도커 위에서 실행되고 있기 때문입니다.
- 스크립트 작성 시 api 를 호출하는 코드를 작성하게 되는데 이때 ip 를 노트북의 ip 주소를 입력해야 `Connection Refused` 가 일어나지 않습니다.


<br/>

## 인덱스를 통한 쿼리 성능 개선


<br/>


## N+1 문제 해결


<br/>


## QueryDSL 을 사용한 동적 쿼리 기능

<br/>



## OAuth2 + JWT 기반 로그인 기능

<br/>

## 좋아요 기능 동시성 제어

<br/>

## 단위 테스트 작성

<br/>


