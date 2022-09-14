-------------------------------------------------------------------
-- 관리자계정
-------------------------------------------------------------------
-- student계정 생성
-- c## 접두어 사용안함
alter session set "_oracle_script" = true;

create user student
identified by student
default tablespace users;

grant connect, resource to student;

alter user student quota unlimited on users;


-------------------------------------------------------------------
-- student계정
-------------------------------------------------------------------
-- member테이블 생성
create table member(
    id varchar2(20),
    name varchar2(100) not null,
    gender char(1),
    birthday date,
    email varchar2(500),
    address varchar2(1000),
    reg_date timestamp default systimestamp,
    constraint pk_member_id primary key(id),
    constraint uq_member_email unique(email),
    constraint ch_member_gender check(gender in ('M', 'F'))
);
insert into member values( 'honngd', '홍길동', 'M', '1990-09-09', 'honggd@naver.com', '서울시 강남구 테헤란로 123', default);
insert into member values( 'sinsa', '신사임당', 'F', '1954-10-19', 'sinsa@naver.com', '경기도 구리시 소동 43', default);
insert into member values( 'qwerty', '꾸어띠', 'M', '2005-01-09', 'qwerty@gmail.com', '서울시 중구', default);
commit;

select * from member order by reg_date desc;

-- java.sql.Date <---> date jdbc연결시 년월일 정보만 전송(시분초 유실)
-- java.sql.Timestmp <--> timestmap 
select sysdate, systimestamp from dual;




