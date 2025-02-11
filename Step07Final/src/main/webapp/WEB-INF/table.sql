-- 가입된 회원정보를 저장할 테이블
CREATE TABLE users(
	num NUMBER PRIMARY KEY,
	userName VARCHAR2(100) UNIQUE,
	password VARCHAR2(100) NOT NULL,
	email VARCHAR2(100) UNIQUE,
	profileImage VARCHAR2(100),
	role VARCHAR2(10) DEFAULT 'USER' CHECK (role IN ('USER','STAFF','ADMIN')),
	createdAt DATE DEFAULT SYSDATE,
	updatedAt DATE DEFAULT SYSDATE
);

-- 회원번호를 얻어낼 시퀀스
CREATE SEQUENCE users_seq;
-- 글 목록을 저장할 테이블
CREATE TABLE posts(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	createdAt DATE DEFAULT SYSDATE,
	updatedAt DATE DEFAULT SYSDATE
);
-- 글번호를 얻어낼 시퀀스
CREATE SEQUENCE posts_seq;

-- 어떤 세션에서 몇번글을 읽었는지 정보를 저장할 테이블
CREATE TABLE readed_data(
	num NUMBER REFERENCES posts(num),
	session_id VARCHAR2(50)
);

CREATE TABLE comments(
	num NUMBER PRIMARY KEY, -- 댓글의 글번호 
	writer VARCHAR2(100) NOT NULL, -- 작성자
	content VARCHAR2(200) NOT NULL, -- 내용
	targetWriter VARCHAR2(100) NOT NULL, -- 댓글 대상자의 id (작성자, username)
	postNum NUMBER NOT NULL, -- 원글의 글번호
	parentNum NUMBER NOT NULL, -- 댓글의 그룹번호
	deleted CHAR(3) DEFAULT 'no',
	createdAt DATE
);

CREATE SEQUENCE comments_seq;











