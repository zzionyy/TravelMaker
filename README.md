## 목차

1. [팀원소개](#팀원소개)
2. [기능명세서](#기능명세서)


# 팀원소개

팀장: 박지원(FE)
팀원: 김연화(FE) 이건희(FE/BE) 이진규(BE) 전성재(BE/BigData) 최영진(FE/BE/BigData)


# 기능명세서

### 1. 회원가입
- 카카오 소셜 가입
```
카카오 이메일(varchar 100)
프로필 사진(varchar1000)
이름(varchar 50)
성별 (varchar 50)
연령대 (int)
생일 (Date ⇒ YYYY-MM-DD) 
```

### 2. 로그인
- 카카오 소셜 로그인
```
[ Server ]
기능 1 : 로그인시 Access Token, Refresh Token 발급
기능 2 : Refresh Token DB에 저장
기능 3 : User 객체 + Flag 값(현재 어느 페이지로 리다이렉트로 갈지)
Flag : 0 (메인-코스전), 1 (메인-코스후), 2(메인-여행중)
선택 기능 1 : Refresh Token Redis 캐싱 처리 

[ Client ] 
기능 1 : 로그인시 반환되는  Access Token, Refresh Token Local Stroage 에 저장 및 상태관리 Store에 저장
```

### 3. navbar

```
[ 비로그인 시 ]
메인페이지 클릭 시 ⇒ 메인페이지 이동
마이페이지 클릭 시 ⇒ 로그인 페이지 이동
여행코스짜기 클릭 시 ⇒ 로그인 페이지 이동

[ 로그인 시 ]
각자 해당 페이지로 이동
```

### 4. 메인 


- 코스 전
```
[ 공통 ] 
 
1. 상단 이모티콘
2. 상태 메세지
3. 하단 추천 여행지 목록 (무한 스크롤)
4. 여행지 정보 클릭시 (화면 회전하며) 상세 내용 보여주기
5. 여행지 정보 좋아요 기능(DB에 저장)

[ 후기 작성 필요시 ]

1. 후기 작성 알림 활성화 (토스식: 일정시간-7일 이후 사라짐)

[서버]

1. 유저가 여행을 갔다온지 7일 이내이면 후기 작성 알림
2. 추천 여행지 목록 (각 여행지의 사진, 설명, 좋아요 Flag)

좋아요 서버에서 어떻게 처리 할 지 고민

[클라이언트]
 ( 로그인 - 서버에서 여행지 리스트 받아오기 )

1. 상태 store에서 userId GET, localStorage에서 access 토큰 GET
2. 서버로 추천 여행지 정보 요청
3. 응답 받은 추천 여행지 정보(임시로 10개) 화면에 뿌려주기
4. 스크롤 끝나면 다시 리스트 10개 GET, 리스트는 최신 리스트가 기존 리스트 하단에 붙는 무한 스크롤 방식

 ( 비로그인 - 서버에서 여행지 리스트 받아오기 )

1. 서버로 추천 여행지 정보 요청
2. 응답 받은 추천 여행지 정보(임시로 10개) 화면에 뿌려주기
3. 스크롤 끝나면 다시 리스트 10개 GET, 리스트는 최신 리스트가 기존 리스트 하단에 붙는 무한 스크롤 방식

 ( 로그인 - 추천 여행지 좋아요 )

1. 해당 리스트 컴포넌트에 각각에 좋아요 기능 적용
2. 좋아요 여부에 따라 좋아요 버튼 디자인 변경
    
    ⇒ 좋아요 TRUE - 꽉찬 하트 / 좋아요 FALSE - 빈 하트
    

 ( 비로그인 - 추천 여행지 좋아요 )

1. 좋아요 버튼 클릭시 로그인 페이지로 이동

 ( 여행지 클릭 시 정보 보여주기 )

 1. 여행지 이미지 클릭 시 컴포넌트 회전하면서 해당 여행지 정보 텍스트 노출

1. 텍스트 다시 클릭시 여행지 이미지 노출
```

- 코스 후
```
코스 후 페이지를 어느 시점부터 보여줄까?
⇒ 2주전부터

1. 상단 이모티콘
2. 상태 메세지 (D-day, 여행지/ ex) ㅇㅇ까지 D-20)
3. 예정 날씨 (출발날짜 기준)
    1. 이모티콘으로 날씨 표현
    2. 프론트에서
4. 코스 상세페이지 버튼
```

### 데이터

[서버]

1. 여행 장소 시 이름(ex. 대구), 날짜, 사진
- ~~대구 동성로 vs 동성로~~ vs 대구

[클라]

1. 이모티콘 설정
2. 장소, 날짜(D-day설정) → —으로 떠나기까지 D-3!
3. 날씨api 요청 → 예상 날씨 표현해주기(가능하면 이모티콘)
- 위치, 날짜, 기온, 설명(ex.흐림), 설명에 따른 이모티콘(우리가 갖고있는)
4. 여행장소 시 사진 클릭시 코스 확정 상세페이지 이동
5. 코스 확정 상세페이지 버튼


- 여행 중
```
1. 상단 이모티콘
2. 날씨 (출발날짜 기준)
    1. 이모티콘으로 날씨 표현
    2. 프론트에서
3. 당일 코스 상세 + 터치하면 텍스트 + 슬라이드로 코스 보기
```

### 5. 코스

- 날짜, 교통수단 선택
```
최대 3일만 선택가능

1. 캘린더 라이브러리
2. 동일 날짜 중복 일정 못 만들게 캘린더 날짜 비활성화(선택)
3. 이동수단 선택버튼 3개(자동차, 대중교통, 뚜벅이)
```

- 도 선택
```
1. 8개의 도 선택 버튼 증 한개 클릭 ⇒ 도 선택하기
```

- 시 선택
```
1. 선택한 도 안에서 좋아요 기반 지역(시) 선택
- 5개 추천해줌
2. 다음 
```

- 추천받은 목록 선택
```
명소, 식당, 카페 
사진, 이름, 관광지에 대한 간단한 설명
음영처리 후 정보나오기(카드뒤집기 ㅎㅎ)
검색기능(우리디비)
카테고리 상관없이!(카테고리별 갯수제한없음)

**일수별 최소 2개**(1일여행 → 최소 2개,  2일여행 → 최소4개,  3일여행 → 최소 6개)
⇒고른 항목 + 우리가 추천해 줄 코스 합쳐서 일 당 4개로 만들어주기 (4, 8, 12)

**1일 일 때**
- 가까운 곳의 명소, 카페, 식당 넣어서 4개로 만들어서 코스 짜주기

**2일 이상일 때**
- 4개(이상) 중 가까운 것 2개, 2개(혹은 1, 3개)로 묶어서 그 주변 명소, 식당, 카페 채워서 4개 만들어주고 코스 1, 2일차 나눠서 보여주기
```

- 코스 상세페이지

```
여정이름(기본값: 도시이름, 바꿀수있게만들기)
여행기간
지도
추천 코스 
편집버튼
일정저장버튼
```

- 코스 편집페이지
```
지도
여정이름(기본값: 도시이름, 바꿀수있게만들기)
여행기간
코스리스트
- 코스 정보(사진, 이름, 정보)
- 삭제버튼
- 소요시간?
- 추가버튼(코스사이사이)
- 버튼 누를 시 모달이 뜸 (추천받은 목록선택 페이지 같은)
- 모달에선 한 개만 선택이 가능!
```

- 코스 수정 후 페이지

```
여정이름(기본값: 도시이름, 바꿀수있게만들기)
여행기간
지도
추천 코스 
편집버튼
일정저장버튼
```

- 코스 다시보기 페이지
```
여정이름(기본값: 도시이름, 바꿀수있게만들기)
여행기간
지도
추천 코스 
편집버튼
```
### 6. 마이페이지
```
프로필사진 (기본 이미지)
사용자 이름
탭1 (나의 일정 - 모든 여행 정보 목록)
    1. 저장 한 일정을 목록 형식으로 보여주기(일정 이름 수정, 일정 삭제 필요)
    2. 여행지
    3. 날짜
    4. 공유하기
탭2 (일기 - 일기를 쓴 것만 보이게)
    1. 일기 목록 보여주기
    2. 여행 이름
    3. 사진
```

### 7. 코스 다시보기 페이지
```
여정이름(기본값: 도시이름, 바꿀수있게만들기)

여행기간

지도

- 확정 후에는 tmap api호출. 한번만 호출해도 되게끔

추천 코스 

편집버튼
```

### 8. 후기 작성 페이지
```
1. 작성할 여행의 정보
    1. 날짜, 장소, 여정이름 → 안바뀌게
2. 이미지업로드 → 10MB미만 10장
3. STT(Speech-To-Text) https://developers.rtzr.ai/docs/
4. https://blog.rtzr.ai/tutorial-nodejs-react-file-stt-ts/
    1. 작성한 거 아래에 붙게
    2. 텍스트만 저장
    3. 스트리밍 형식으로 STT 진행
    4. ~~음성파일 저장 (대체재)~~
5. 저장하기

---

### 데이터

[ Server ]
1. VITO STT
2. 여행id, 이미지 리스트, 후기 작성한 TEXT를 받는 일기 작성 API

[ Client ]
1. 여정명, 날짜, 지역 정보 바꾸지 않기 → 마이페이지에서 들고있는 정보 가져와
2. 이미지 업로드 → 10MB, 10장까지
3. stt버튼 → 스트리밍 ? 파일로 전달 ?
4. textarea
5. 완료버튼 (여행id, 일기text, 이미지들 폼데이터로 한번에 보내기)
```

### 9. 후기디테일페이지
```
# 후기디테일페이지

1. 코스정보(기간, 지역)
2. 이미지
3. 일기텍스트
4. ~~~수정~~~

---

### 데이터

[서버]

1. 일기 id 받으면 사진, 일기텍스트 반환 API (여행테이블이랑 join) 

[클라]

1. 여행명, 여행날짜, 여행지역, 사진들, 일기텍스트 받아와서 뿌려주기  
```

