--==========================================
-- SYSTEM 관리자계정
--==========================================
show user;

-- oracle설치시 자동으로 sys, system계정이 생성된다.
-- 1. sys 슈퍼관리자. db생성/삭제권한 있음. 그외 모든 db관련 처리가능. 로그인시 sysdba롤로 접속해야함.
-- 2. system 일반관리자. db생성/삭제권한 없음. 그외 모든 db관련 처리가능.

-- sql문은 대소문자를 구분하지 않는다.
-- 데이터와 사용자의 비번, 별칭은 대소문자를 구분한다.

-- ORA-65096: 공통 사용자 또는 롤 이름이 부적합합니다.
-- 12c버젼부터 일반사용자는 c##, C## 접두사를 사용해야 한다.
-- 우회방법
alter session set "_oracle_script" = true;

-- 일반사용자 kh추가
CREATE USER kh
IDENTIFIED BY kh -- 비밀번호
DEFAULT TABLESPACE USERS; -- 실제 데이터 저장공간

-- 접속권한(create session), 테이블생성권한 부여
-- connect롤안에 접속권한 포함
grant connect, resource to kh;

-- tablespace users에 사용용량 무제한 설정
alter user kh quota unlimited on users;


-- 실습문제용 계정 chun
alter session set "_oracle_script" = true;

create user chun
identified by chun
default tablespace users;

alter user chun quota unlimited on users;

grant connect, resource to chun;





