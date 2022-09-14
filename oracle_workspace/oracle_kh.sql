--=================================
-- KH 계정
--=================================
show user;

-- 사용자의 테이블 조회
select * from tab;

-- 사원테이블
select * from employee;
select * from job;
select * from department;
select * from location;
select * from nation;
select * from sal_grade;

-- table(entity, relation) 테이블
-- column(field, attribute) 컬럼(속성) - 자료형지정
-- row(record, tuple) 행 - 사원한명, 하나의 부서에 대한 정보, Java App의 VO객체와 대응
-- domain 하나의 속성(컬럼)이 가질수 있는 원자값들의 집합

-- 테이블 명세
desc employee;

--===================================
-- 자료형
--===================================
-- 컬럼에 지정
-- 1. 문자형
-- 2. 숫자형
-- 3. 날짜형

---------------------------------------------------------
-- 1. 문자형
---------------------------------------------------------
-- char    : 고정형(최대 2000byte)
-- varchar2 : 가변형(최대 4000byte)
-- long     : 가변형(최대 2gb)
-- clob     : 가변형(Character Large Object)(최대 4gb)

/*
    고정형 char(10)인 컬럼에 'korea'를 입력하면, 실제데이터의 길이는 5byte지만, 10byte로 기록됨.
    가변형 varchar2(10)인 컬럼에 'korea'를 입력하면, 실제데이터의 길이는 5byte이므로, 5byte로 기록됨.
    
    둘다 지정한 크기를 넘는 데이터는 처리할 수 없다.
*/

-- 테이블 생성구문 - 컬럼명 자료형(크기) 지정
create table tb_type_char(
    a char(10),
    b varchar2(10)
);
--drop table tb_type_char;

-- lengthb는 실제 사용된 byte수를 반환
select a, lengthb(a), b, lengthb(b)
from tb_type_char;

-- 데이터 추가(row단위로 추가)
insert into tb_type_char
values ('korea', 'korea');

insert into tb_type_char
values ('I love Korea', 'I love Korea'); -- ORA-12899: "KH"."TB_TYPE_CHAR"."A" 열에 대한 값이 너무 큼(실제: 12, 최대값: 10)

insert into tb_type_char
values ('홍길동', '홍길동'); -- xe버젼은 3바이트씩 처리됨. ee버젼은 2바이트처리됨.

-- 메모리상 작업내용을 실제 db에 반영하기/취소하기
commit;
rollback;


----------------------------------------------------
-- 2. 숫자형
----------------------------------------------------
-- number(p, s)
-- p : 표현가능한 전체 자리수
-- s : 소수점이하 자리수

/*
  1234.567 데이터 처리시....
  
  데이터타입        저장된 값
  -------------------------------------
  number         1234.567
  number(7,1)     1234.6     -- 반올림
  number(7)       1235      -- 반올림
  number(7,-2)     1200      -- 반올림
  
*/
create table tb_type_number (
    a number,
    b number(7, 1),
    c number(7),
    d number(7, -2)
);

insert into tb_type_number
values (1234.567, 1234.567, 1234.567, 1234.567);

insert into tb_type_number
values (12345678, 12345678, 12345678, 12345678); -- ORA-01438: 이 열에 대해 지정된 전체 자릿수보다 큰 값이 허용됩니다.

select *
from tb_type_number;

---------------------------------------------------------
-- 3. 날짜형
---------------------------------------------------------
-- date : 년월일시분초
-- timestamp : 년월일시분초 + 밀리초 지역대

-- 산술연산이 가능하다.
/*
    연산          결과타입        설명
----------------------------------------------------------------------------
    날짜 + 숫자     date        날짜에서 지정한 숫자(일단위)후의 날짜 리턴
    날짜 - 숫자     date        날짜에서 지정한 숫자(일단위)전의 날짜 리턴
    날짜 - 날짜     number      두 날짜의 차이(일단위)를 리턴
*/

-- dual 가상테이블(1행) 사용
-- sysdate는 현재 날짜(시분초)정보를 반환
-- 1 하루
-- 1 / 24 한시간
-- 1 / 24 / 60 1분 ---> 30 * (1 / 24 / 60)

select 
    to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'),
    to_char(sysdate + (1 / 24), 'yyyy-mm-dd hh24:mi:ss'),
    to_char(sysdate - (30 * (1 / 24 / 60)), 'yyyy-mm-dd hh24:mi:ss')
from
    dual;
    
-- 날짜 - 날짜 = 숫자(일단위)
select
    to_date('2022/08/29', 'yyyy/mm/dd') - sysdate
from
    dual;

desc employee;

--회원테이블 tb_type_member을 생성한다.
-- id 6 ~ 15자리 문자
-- password 8 ~ 15자리 문자
-- name 한글입력(10자까지 가능)
-- ssn 주민번호 (-없이 13자리)
-- phone 전화번호 (-없이 11자리)
-- point 멤버쉽포인트
-- reg_date 가입일

create table tb_type_member (
    id varchar2(15),
    password varchar2(15),
    name varchar2(30),
    ssn char(13),
    phone char(11),
    point number,
    reg_date date
);


--================================================
-- DQL1
--================================================
-- Data Query Language 
-- DML의 한 종류로써, 테이블데이터를 검색하는 언어
-- select명령에 대한 응답을 결과집합(Result Set)이라고 함.
-- Result Set에는 0개 이상의 행이 포함된다.
-- Result Set은 특정기준에 따라 필터링되거나 정렬될 수 있다.

/*
    구조
    select 컬럼명 ----------------- (5) 원하는 컬럼지정
    from 테이블명 ----------------- (1) 조회할 대상테이블     
    where 조건절 ----------------- (2) 특정 조건을 만족하는 행만 필터링      
    group by 그룹핑 컬럼 ------- (3) 그룹핑
    having  그룹핑 조건절 ------- (4) 그룹핑된 결과를 필터링      
    order by 컬럼 ----------------- (6) 특정컬럼 기준으로 행을 정렬

    처리순서에 따라 dql을 작성한다.

*/

-- job테이블에서 job_name컬럼만 조회
select
    job_name
from
    job;
    
-- employee테이블에서 이름, 이메일, 전화번호, 입사일을 조회
select
    emp_name, email, phone, hire_date
from 
    employee;

-- employee테이블에서 급여가 2,500,000원이상인 사원의 이름과 급여를 조회
select
    emp_name, salary
from
    employee
where
    salary >= 2500000;

-- employee테이블에서 현재 근무중인 사원을 이름오름차순으로 조회
select
    *
from
    employee
where
    quit_yn = 'N' -- 같다 비교연산자가 == 이 아닌 = 이다.
order by
    emp_name asc;

-- employee테이블에서 급여가 350만원이상이면서 직급코드가 'J3' 인 사원의 사원명, 직급코드, 전화번호를 이름순으로 오름차순 정렬하여 출력
-- && -> and, || -> or
select
    emp_name, job_code, phone
from 
    employee
where
    salary >= 3500000 and job_code = 'J3'
order by
    emp_name; -- asc 오름차순(기본값) desc 내림차순

---------------------------------------------------------------------
-- SELECT
---------------------------------------------------------------------
-- 존재하는 컬럼뿐만 아니라, 연산결과를 출력가능하다.
-- 월급, 보너스, 실급여(월급 + (월급 * 보너스))
-- null과 산술연산, 비교연산이 불가하다.
-- nvl(nullable값, null일때 처리할 값)
select
    emp_name as "사원명",  
    salary as "급여", 
    bonus as "보너스", 
    nvl(bonus, 0) as "보너스",
    salary + (salary * nvl(bonus, 0)) as "실급여"
from 
    employee;
    
select
    null + 1, null * 1, null / 1, null - 1
from 
    dual;

-- 별칭 alias
-- as "별칭"
-- as와 쌍따옴표는 생략이 가능
select
    emp_name as "사원명",
    emp_no "주민번호",
    phone 전화번호,
    job_code "직급 코드", -- 공백이 있는 경우 쌍따옴표 생략불가
    dept_code "1부서코드" -- 숫자로 시작하는 경우 쌍따옴표 생략불가
from
    employee;

-- 고정값 출력
-- 모든 행에 같은 값이 반복출력
select
    emp_name, salary, '원'
from
    employee;

-- distinct 중복값을 제거
-- select구문에 딱 한번만 사용. 하나이상의 컬럼에 대해 중복값을 검사하고, 중복된 행을 제거
select
    distinct job_code, dept_code
from
    employee
order by 
    job_code;

-- || 문자열 연결연산자
select
    emp_name, salary || '원' 급여
from
    employee;

--@실습문제
-- employee테이블에서 이름, 급여, 보너스(없으면 0으로 처리), 보너스포함 급여, 실수령액(보너스포함 급여 - (보너스포함 급여 * 3.3%))을 조회
select
    emp_name 이름,
    salary 급여,
    nvl(bonus, 0) 보너스,
    salary + (salary * nvl(bonus, 0)) "보너스포함 급여",
    salary + (salary * nvl(bonus, 0)) - (salary + (salary * nvl(bonus, 0))) * 0.033 "실수령액"
from 
    employee;


---------------------------------------------------------
-- WHERE
---------------------------------------------------------
-- 대상테이블에서 특정행을 필터링하는 구문
-- 행에 대해서 조건절의 결과를 true/false로 구분, true인 행만 결과집합에 포함시킨다.
select 
    emp_name, dept_code
from
    employee
where
    dept_code = 'D9'; -- 홑따옴표안의 문자열은 실제값이므로 대소문자를 구분
    
-- 연산자
/*
    = 
    > < >= <=
    != <> ^=            같지 않다
    between a and b     a이상 b이하
    like | not like        문자패턴 비교
    is null | is not null   null값 비교
    in | not in           값목록에 포함여부
    
    논리 연결연산
    and                 두 조건을 모두 만족시키면 true  && 없다.
    or                  두 조건중 하나를 만족시키면 true  || 없다. (문자열 연결연산)
    not                 반전
    
*/

-- 부서코드가 D6이고, 급여를 2000000원보다 많이 받는 사원의 이름, 부서코드, 급여 조회
select
    emp_name, dept_code, salary
from 
    employee
where
    dept_code = 'D6' and salary > 2000000;

-- 부서코드가 D9이 아닌 사원 조회
select 
    *
from 
    employee
where
    dept_code <> 'D9'; -- null인 컬럼은 제외

-- 직급코드가 J1이 아닌 사원들의 월급등급(sal_level)을 중복없이 출력
select
    distinct sal_level
from
    employee
where
    job_code ^= 'J1';

-- 부서코드가 D5가 아닌 사원과 부서코드가 null인 사원 모두 조회
select
    *
from
    employee
where
    dept_code != 'D5' 
  or
    dept_code is null;

-- 근무기간이 20년이상인 사원의 이름, 급여, 보너스율을 조회
select
    emp_name, 
    salary,
    bonus,
    (sysdate - hire_date) / 365
from
    employee
where
    (sysdate - hire_date) / 365 >= 20;
    
-- between value1 and value2
-- value1 이상 value2 이하

-- 급여가 3500000원 이상 6000000원 이하의 사원 조회
select
    *
from
    employee
where
--    salary between 3500000 and 6000000;
    salary >= 3500000 and salary <= 6000000;

-- 날짜에 대해서 처리
-- 1990년 ~ 2000년 입사자 조회
select
    emp_name, hire_date
from
    employee
where
--    hire_date between '90/01/01' and '00/12/31';
    hire_date >= '90/01/01' and hire_date < '01/01/01'; -- 1990년 1월 1일 자정(포함)부터 2001년 1월1일 자정(미포함) 전까지 조회

-- like | not like
-- 문자열 패턴 검사

/*
    wildcard (특수한 의미를 가진 문자)
    1. % 문자가 0개이상   a% a다음에 문자 0개이상 -> a ab aaab....
    2. _  문자가 1개      a_ a다음에 문자 1개만 -> ab ac ad

*/

-- 전씨 성을 가지 사원조회
-- 전씨이면서 이름이 2글자인 사원조회
select
    *
from 
    employee
where
--    emp_name like '전%';
    emp_name like '전__';
    
-- 이름이 3글자이고, 가운데 글자가 '옹'인 사원조회
select
    emp_name
from 
    employee
where
    emp_name like '_옹_';

-- 이름에 '이'가 들어가는 사원조회
select
    emp_name
from 
    employee
where
    emp_name like '%이%';
    
-- 성이 이씨가 아닌 사원 조회
select
    emp_name
from
    employee
where
--    emp_name not like '이%';
    not (emp_name like '이%');
    

-- _앞에 글자가 3글자인 이메일조회
select
    email
from 
    employee
where
    email like '___#_%' escape '#'; -- 데이터에 # escape 문자가 있어서는 안된다.

-- in | not in
-- 값목록에 포함되어있으면 true로 처리

-- D6, D8부서원 조회
select
    emp_name, dept_code
from 
    employee
where
    dept_code in ('D6', 'D8');
--    dept_code = 'D6' or dept_code = 'D8';

-- D6, D8부서원을 제외하고 조회
select
    emp_name, dept_code
from 
    employee
where
--    dept_code not in ('D6', 'D8');
--    dept_code != 'D6' and dept_code != 'D8' 
    not (dept_code = 'D6' or dept_code = 'D8');
    
-- is null | is not null
-- 인턴 사원 조회
select
    emp_name,
    dept_code,
    nvl(dept_code, '인턴')
from 
    employee
where
--    dept_code is null;
--    dept_code = null;   -- 작동x
--    nvl(dept_code, '인턴') = '인턴';
    nvl(dept_code, 'ㅌㅌㅌㅌㅌ') = 'ㅌㅌㅌㅌㅌ';
    

select
    emp_name,
    hire_date,
    quit_date,
    nvl(quit_date, sysdate) - hire_date,
    quit_yn
from
    employee;


--------------------------------------------------------------------
-- ORDER BY
--------------------------------------------------------------------
-- 마지막에 실행되며 행의 순서를 재배치한다.
-- 오름차순(작은수 -> 큰수, 사전등재빠른순 -> 사전등재늦은순, 과거 -> 미래)
-- 1개이상의 정렬기준컬럼을 작성가능
-- nulls first | last
select
    emp_name,
    dept_code,
    salary,
    hire_date
from 
    employee
order by
    dept_code nulls first, salary desc;

-- 컬럼명 대신 별칭, 컬럼순서
select
    emp_name 사원명,
    dept_code 부서코드,
    salary 급여,
    hire_date 입사일
from 
    employee
order by
    부서코드 nulls first, 3 desc; -- 3번째 컬럼


--=====================================================
-- FUNCTION
--=====================================================
-- 일련의 작업절차를 모아놓은 서브프로그램
-- 호출시 매개인자를 전달하고, 그에 따른 수행결과를 반드시 리턴한다. (리턴값이 없을수 없다)

/*
    함수의 종류
    
    1. 단일행처리 함수 - 행마다 처리되는 함수
        a. 문자처리함수
        b. 숫자처리함수
        c. 날짜처리함수
        d. 형변환함수
        e. 기타함수
    2. 그룹처리 함수 - 행을 그룹핑한후 그룹에 대해서 처리하는 함수
*/

--------------------------------------------------------------------
-- 1. 단일행처리 함수
--------------------------------------------------------------------

--++++++++++++++++++++++++++++++++++++++++++
-- a. 문자처리 함수
--++++++++++++++++++++++++++++++++++++++++++
-- length(컬럼/값) : 길이값을 리턴
select
    emp_name, length(emp_name), lengthb(emp_name)
from 
    employee;

-- instr(대상문자열, 검색할문자열[, 시작인덱스, 출현횟수]) : 인덱스를 반환

select
    instr('kh정보교육원 국가정보원 정보문화사', '정보'), -- 3
    instr('kh정보교육원 국가정보원 정보문화사', '정보', 10), -- 11 
    instr('kh정보교육원 국가정보원 정보문화사', '정보', 1, 3), -- 15
    instr('kh정보교육원 국가정보원 정보문화사', '정보', -1), -- 15 시작인덱스 음수인 경우 뒤에서 검사
    instr('kh정보교육원 국가정보원 정보문화사', '안녕') -- 0 
from
    dual;
    
-- 사원테이블에서 이메일 아이디의 길이, 아이디를 조회
select
    email, 
    instr(email, '@') - 1 id_length, 
    substr(email, 1, instr(email, '@') - 1) id
    
from
    employee;

-- substr(대상문자열, index[, length]) : 문자열 리턴
select
    substr('SHOWMETHEMONEY', 5, 2), -- ME
    substr('SHOWMETHEMONEY', 5),    -- METHEMONEY
    substr('SHOWMETHEMONEY', -5)
from 
    dual;
    
-- lpad(문자열, 길이[, 패딩문자]) : 문자열 리턴
-- rpad(문자열, 길이[, 패딩문자]) : 문자열 리턴

select 
    lpad('hello', 10, '#'),
    rpad('hello', 10, '#'),
    lpad(123, 5, 00),
    'kh-' || to_char(sysdate, 'yymmdd') || '-' || lpad(123, 5, 0) 주문번호
from
    dual;
    
-- replace(대상문자열, 검색문자열, 치환문자열) : 문자열 리턴
select
    replace('hello@naver.com', 'naver.com', 'google.com') 새이메일
from
    dual;

-- 사원테이블에서 남자사원의 사번, 이름, 주민번호를 조회
-- (주민번호의 뒤 6자리는 *로 숨김처리)

select
    emp_id,
    emp_name,
    rpad(substr(emp_no, 1, 8), 14, '*') 주민번호,
    substr(emp_no, 1, 8) || '******' 주민번호
from
    employee
where
    substr(emp_no, 8, 1) in ('1', '3');
    

--+++++++++++++++++++++++++++++++++++++++++++++
-- b.숫자처리함수
--+++++++++++++++++++++++++++++++++++++++++++++

-- mod(피제수, 제수) : 나머지를 리턴
-- + - * / 사칙연산
-- % 나머지연산은 없다.
select
    10 + 3,
    10 - 3,
    10 * 3,
    10 / 3,
    mod(10, 3)
from 
    dual;

-- 생일 끝자리가 짝수인 사원만 조회
select 
    *
from
    employee
where
    mod(substr(emp_no, 6, 1), 2) = 0;
    
-- ceil(number) : 숫자리턴
select
    ceil(123.456), -- 124
    ceil(123.456 * 100) / 100  -- 123.46
from 
    dual;
    
-- round(number, 소수점이하자리수) : 숫자를 리턴
select 
    round(1234.56),
    round(1234.56, 1),
    round(1234.56, -2)
from
    dual;
    
-- floor(number) : 숫자리턴
select
    floor(234.567),
    floor(234.567 * 100) / 100
from 
    dual;

-- trunc(number, 소수점이하자리수) : 숫자를 리턴

select
    trunc(234.567),
    trunc(234.567, 2),
    trunc(234.567, -2)
from 
    dual;


--++++++++++++++++++++++++++++++++++++++++
-- c. 날짜 처리 함수
--++++++++++++++++++++++++++++++++++++++++

-- add_months(date, number): date를 반환
select
    add_months(sysdate, -1) 한달전, 
    sysdate,
    add_months(sysdate, 1) 한달뒤,
    add_months(to_date('22/03/31', 'yy/mm/dd'), 1) "3/31로부터 한달뒤",
    add_months(to_date('22/04/30', 'yy/mm/dd'), 1) "4/30로부터 한달뒤"
from
    dual;
    
-- months_between(미래날짜, 과거날짜) : 개월수차이를 리턴
select
    months_between('22/08/29', sysdate)
from 
    dual;

-- 사원테이블에서 사원별 근무개월수1(n개월), 근무개월수2(k년 j개월)를 출력
select
    emp_name,
    floor(months_between(sysdate, hire_date)) "근무개월수1",
    floor(months_between(sysdate, hire_date) / 12) || '년 ' || 
    floor(mod(months_between(sysdate, hire_date), 12)) || '개월' "근무개월수2"
from
    employee;


-- extract (year | month | day from date) : 숫자를 리턴
-- extract (hour | minute | second from cast(date as timestamp)) : 숫자를 리턴
select
    extract(year from sysdate) 년,
    extract(month from sysdate) 월,
    extract(day from sysdate) 일,
    extract(hour from cast(sysdate as timestamp)) 시,
    extract(minute from cast(sysdate as timestamp)) 분,
    extract(second from cast(sysdate as timestamp)) 초
from 
    dual;

-- trunc(date) : 날짜형을 리턴(시분초를 제거)
select
    to_char(sysdate, 'yy/mm/dd hh24:mi:ss') "sysdate",
    to_char(trunc(sysdate), 'yy/mm/dd hh24:mi:ss') "sysdate"
from
    dual;



--@실습문제
create table tbl_escape_watch(
    watchname   varchar2(40)
    ,description    varchar2(200)
);
--drop table tbl_escape_watch;
insert into tbl_escape_watch values('금시계', '순금 99.99% 함유 고급시계');
insert into tbl_escape_watch values('은시계', '고객 만족도 99.99점를 획득한 고급시계');
commit;
select * from tbl_escape_watch;

--tbl_escape_watch 테이블에서 description 컬럼에 99.99% 라는 글자가 들어있는 행만 추출하세요.

select *
from tbl_escape_watch
where description like '%99.99\%%' escape '\'; 
-- '\'말고 다른 문자(숫자,문자)가 와도 좋지만, 헷가릴 수 있으니 사용빈도가 적은 역슬래시 '\'를 사용한다.

select description
from tbl_escape_watch
where instr(description, '99.99%') != 0;


--@실습문제
--파일경로를 제외하고 파일명만 아래와 같이 출력하세요.
    
create table tbl_files (
    fileno number(3)
    ,filepath varchar2(500)
);
insert into tbl_files values(1, 'c:\abc\deft\salesinfo.xls');
insert into tbl_files values(2, 'c:\music.mp3');
insert into tbl_files values(3, 'c:\documents\resume.hwp');
commit;
select * 
from tbl_files;

/*
출력결과 :
--------------------------
파일번호          파일명
---------------------------
1             salesinfo.xls
2             music.mp3
3             resume.hwp
---------------------------
*/

select 
    fileno 파일번호,
    instr(filepath, '\', -1),
    substr(filepath,  instr(filepath, '\', -1) + 1) as "파일명"
from tbl_files;

--++++++++++++++++++++++++++++++++++++++++++++
-- d. 형변환함수
--++++++++++++++++++++++++++++++++++++++++++++
/*
        to_char            to_date
    ----------------------->  --------------------->
number            char            date
    <----------------------   <-------------------
        to_number         to_char
    
*/

-- to_char(date, format_char) : char
select
    to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 날짜,
    to_char(sysdate, 'yyyy-mm-dd (day) hh:mi:ss') 날짜,
    to_char(sysdate, 'yyyy-mm-dd (day) (dy) (d)') 날짜, -- 1:일요일, 2:월요일, 3:화요일, 4:수요일, 5...
    to_char(sysdate, 'yyyy"년" mm"월" dd"일"') 날짜,
    to_char(sysdate, 'fmyyyy"년" mm"월" dd"일"') 날짜 -- 포맷문자덕에 생긴 0/공백 제거
from
    dual;

-- to_char(number, format_char) : char
-- 세자리 콤마적용. 소수점이하 처리
-- 실제값보다 형식문자 자리수가 적으면 표시할 수 없다.
select
    to_char(1234567890, '9,999'), -- ######
    to_char(1234567890, '999,999,999,999'), --    1,234,567,890
    to_char(1234567890, 'fm999,999,999,999'),
    123 숫자숫자숫자,
    to_char(1234567890, 'FML999,999,999,999'), -- L 지역통화기호
    to_char(123.456, 'FM99999.99999'), -- 해당자리수가 없을때 소수점이상 공백처리, 소수점이하는 0채움처리
    to_char(123.456, 'FM00000.00000')  -- 해당자리수가 없을때 소수점이상/이하는 0채움처리
from 
    dual;

-- 사원테이블에서 사원명, 급여, 연봉(급여 * 12), 입사일 조회
-- 금액형식지정, 년월일 형식
select
    emp_name,
    to_char(salary, 'FML9,999,999') 급여,
    to_char(salary * 12, 'FML9,999,999,999') 연봉,
    to_char(hire_date, 'FMyyyy"년" mm"월" dd"일"') 입사일
from 
    employee;

-- to_number(char, format_char) : number 리턴
-- 그룹핑처리된 숫자를 순수 숫자로 변환해서 연산처리
select
    to_number('￦8,000,000','L999,999,999') + 1000,
    '1000' + '100', -- +는 숫자사이에서만 가능
    '1000' || '100'  -- || 문자열 연결연산에 사용
from 
    dual;


-- to_date(char, format_char) : date리턴
select
    to_date('1999년 3월 16일', 'yyyy"년" mm"월" dd"일"') 날짜,
    extract(year from to_date('1955/01/01', 'yyyy/mm/dd')) 날짜,
    extract(year from to_date('1955/01/01', 'rrrr/mm/dd')) 날짜,
    extract(year from to_date('55/01/01', 'yy/mm/dd')) 날짜, -- 현재년도 기준으로 100년(2000 ~ 2099)
    extract(year from to_date('55/01/01', 'rr/mm/dd')) 날짜  -- 현재년도(2022) 기준으로 100년(1950 ~ 2049), 
    --    현재년도가 2055년이라면 100년
from 
    dual;
  
-- 나이 구하기 
-- 450505 -> 1945, 550505 -> 1955, 070707 -> 2007
-- yy? rr? 둘다 아니다. 
-- 주민번호의 뒷 첫번째자리를 근거로 1900 + 생년2자리, 2000 + 생년2자리
select
    extract(year from to_date('550505', 'yymmdd')),
    extract(year from to_date('070707', 'yymmdd')),
    
    extract(year from to_date('450505', 'rrmmdd')),
    extract(year from to_date('550505', 'rrmmdd')),
    extract(year from to_date('070707', 'rrmmdd'))
from
    dual;

-- 현재시각으로 부터 1일 2시간 3분 4초뒤를 시각조회
-- 년월일시분초 형태로 출력
-- 날짜 + 숫자(1:하루)
select
    to_char(sysdate + 1 + (2 / 24) + (3 / 24 / 60) + (4 / 24 / 60 / 60), 'yyyy/mm/dd hh24:mi:ss') result
from
    dual;

-- 2022/08/29 남은 일수 구하기
select
    '수료일로부터 D-' || ceil(to_date('2022/08/29', 'yyyy/mm/dd') - sysdate) || '입니다.'
from
    dual;

-- 기간 interval타입
-- 1. interval year to month
-- 2. interval day to second
select
    numtodsinterval(to_date('2022/08/29', 'yyyy/mm/dd') - sysdate, 'day') 기간,
    extract(day from numtodsinterval(to_date('2022/08/29', 'yyyy/mm/dd') - sysdate, 'day')) 일,
    extract(hour from numtodsinterval(to_date('2022/08/29', 'yyyy/mm/dd') - sysdate, 'day')) 시간,
    extract(minute from numtodsinterval(to_date('2022/08/29', 'yyyy/mm/dd') - sysdate, 'day')) 분,
    extract(second from numtodsinterval(to_date('2022/08/29', 'yyyy/mm/dd') - sysdate, 'day')) 초
from 
    dual;


--++++++++++++++++++++++++++++++++++++++++
-- e. 기타함수
--++++++++++++++++++++++++++++++++++++++++

-- nvl(nullable값, null인경우사용값) : 값
-- nvl2(nullable값, notnull인 경우 사용값, null인 경우 사용값) : 값
select
    emp_name, 
    bonus,
    nvl2(bonus, '보너스 있음', '보너스 없음') 보너스여부
from
    employee;


-- 선택함수 decode
-- decode(표현식, 값1, 결과값1, 값2, 결과값2, 값3, 결과값3, ....[, 기본값]) : 결과값
-- job_code (J1:대표, J2:부사장, J3:부장, J4:차장, J5: 과장, J6: 대리, J7:사원)

select
    emp_name,
    job_code,
    decode(job_code, 'J1', '대표', 'J2', '부사장', 'J3', '부장', 'J4', '차장', 'J5', '과장', 'J6', '대리', '사원') 직급명
from
    employee; 

-- 사원테이블에서 이름, 주민번호, 성별(남/여) 조회
select
    emp_name,
    emp_no,
    substr(emp_no, 8, 1),
    decode(substr(emp_no, 8, 1), '1', '남', '2', '여', '3', '남', '4', '여') 성별,
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') 성별
from
    employee;

-- 선택함수 case 

/*
타입1 (조건절로 처리)
    case 
        when 조건절1 then 결과값1
        when 조건절2 then 결과값2
        ...
        [else 기본값]
    end 
    
타입2 (decode와 유사)
    case 표현식 
        when 값1 then 결과값1
        when 값2 then 결과값2
        ...
        [else 기본값]
    end
*/
--타입1
select
    emp_name,
    case
        when substr(emp_no, 8, 1) = 1 then '남'
        when substr(emp_no, 8, 1) = 2 then '여'
        when substr(emp_no, 8, 1) = 3 then '남'
        when substr(emp_no, 8, 1) = 4 then '여'
    end gender,
    case
        when substr(emp_no, 8, 1) in ('1','3') then '남'
        when substr(emp_no, 8, 1) in ('2','4') then '여'
    end gender,
    case
        when substr(emp_no, 8, 1) in ('2','4') then '여'
        else '남'
    end gender
from 
    employee;
    
-- 타입2
select
    emp_name,
    case substr(emp_no, 8, 1)
        when '1' then '남'
        when '2' then '여'
        when '3' then '남'
        when '4' then '여'
    end gender,
    case substr(emp_no, 8, 1)
        when '1' then '남'        
        when '3' then '남'
        else '여'
    end gender
from
    employee;

-- 사원테이블에서 생일조회
select
    emp_name,
    emp_no,
    to_char(to_date(substr(emp_no, 1, 6), 'yymmdd'), 'yyyy-mm-dd') 생일,
    to_char(to_date(substr(emp_no, 1, 6), 'rrmmdd'), 'yyyy-mm-dd') 생일,
    decode(substr(emp_no, 8, 1), '1', 1900, '2', 1900, 2000) + substr(emp_no, 1, 2) 출생년도, 
--    decode(substr(emp_no, 8, 1), '1', 1900, '2', 1900, 2000) + substr(emp_no, 1, 2) || substr(emp_no, 3, 4),
    to_char(to_date(decode(substr(emp_no, 8, 1), '1', 1900, '2', 1900, 2000) + substr(emp_no, 1, 2) || substr(emp_no, 3, 4), 'yyyymmdd'), 'yyyy-mm-dd') 생일
from
    employee;


------------------------------------------------------------------------------
-- 그룹함수
------------------------------------------------------------------------------
-- 그룹단위로 처리되는 함수
-- group by지정이 없다면 전체행을 하나의 그룹으로 처리
-- 일반컬럼과 혼용해 쓸수 없다.

-- sum(컬럼) : 총합을 리턴
select
--    emp_name, -- ORA-00937: 단일 그룹의 그룹 함수가 아닙니다
    sum(salary),
    trunc(avg(salary))
from 
    employee;

-- 컬럼값이 null인 경우, 그룹함수에서 제외된다.
select 
    sum(bonus)
from 
    employee;
    
-- 실급여 합계구하기 (가상컬럼)
select
    sum(salary + (salary * nvl(bonus, 0)))
from 
    employee;


-- count(컬럼) : 해당 컬럼이 null이 아닌 행의 수를 리턴
select
    count(bonus),
    count(dept_code),
    count(*) -- *는 한행을 의미, 행이 존재하면 카운팅.
from
    employee;

select
    count(*)
from
    employee
where 
    bonus is not null;
    
-- sum을 이용해 bonus 받는 사원 조회
select
    sum(
        case
            when bonus is null then 0
            when bonus is not null then 1
        end
        ),
    count(bonus)
from
    employee;

-- max / min
-- 숫자, 날짜, 문자열(사전등재순)
select
    max(salary),
    min(salary),
    max(hire_date),
    min(hire_date),
    max(emp_name),
    min(emp_name)
from 
    employee;
    
-- 1. 남자사원의 급여총합을 조회
select
    to_char(sum(salary), 'FML999,999,999') 급여총합
from
    employee
where
    substr(emp_no, 8, 1) in ('1', '3');

-- 2. 부서코드가 D5인 사원들의 보너스 총합 조회 (보너스금액)
select
    sum(salary * nvl(bonus, 0)),
    sum(salary * bonus)
from
    employee
where
    dept_code = 'D5';

-- 3. 남/여 사원의 급여 총합/평균 조회
select
    to_char(sum(salary), 'fml999,999,999')  급여총합,
    to_char(trunc(avg(salary)), 'fml999,999,999') 급여평균
from
    employee
where
--    substr(emp_no, 8, 1) in ('1', '3');
    substr(emp_no, 8, 1) in ('2', '4');
    
-- 남자사원급여 가상컬럼, 여자사원급여 가상컬럼
select
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') 성별,
    decode(substr(emp_no, 8, 1), '1', salary, '3', salary) 남자사원급여만,
    decode(substr(emp_no, 8, 1), '2', salary, '4', salary) 여자사원급여만
from 
    employee;
    
select
    sum(decode(substr(emp_no, 8, 1), '1', salary, '3', salary)) 남자사원급여총합,
    avg(decode(substr(emp_no, 8, 1), '1', salary, '3', salary)) 남자사원급여평균,
    sum(decode(substr(emp_no, 8, 1), '2', salary, '4', salary)) 여자사원급총합,
    avg(decode(substr(emp_no, 8, 1), '2', salary, '4', salary)) 여자사원급평균
from 
    employee;


-- 4. 전사원의 보너스율 평균을 소수점 둘째자리까지 반올림처리해서 출력
select
--    avg(bonus), -- 9명 평균
    round(avg(nvl(bonus, 0)), 2) -- 24명 평균
from
    employee;
    
--===========================================================
-- DQL2
--===========================================================

----------------------------------------------------------------------------------------------
-- GROUP BY
----------------------------------------------------------------------------------------------
-- 별도의 그룹지정이 없다면 그룹함수는 전체를 하나의 그룹으로 간주한다.
-- 세부적 그룹지정을 group by절을 이용할 수 있다.

-- 부서별 급여 평균
select
    dept_code, 
    avg(salary)
from
    employee
group by
    dept_code
order by 
    dept_code;

-- 부서별 사원수를 조회
-- from - where - group by - having - select - order by
select
    nvl(dept_code, '인턴') dept_code,
    count(*)
from
    employee
group by 
    dept_code
order by
    1;

-- 성별 사원수를 조회
-- 가상컬럼을 기준 그룹핑 가능
select 
    employee.*,
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') 성별
from 
    employee;
    
select
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') 성별, 
    count(*) 사원수
from 
    employee
group by
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여');
    
-- 부서별 직급별 인원수    
select
    dept_code, 
    job_code,
    count(*)
from
    employee
group by
    dept_code, job_code
order by 
    1, 2;

-- 부서별 성별 인원수

select
    nvl(dept_code, '인턴') dept_code, 
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender,
    count(*)
from
    employee
group by
    dept_code, decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여')
order by 
    dept_code, gender;

----------------------------------------------------------------------------
-- HAVING
----------------------------------------------------------------------------
-- grouping한 결과행에 대해 조건절을 작성

-- 부서별 급여평균이 300만원 이상이 부서들만 조회(부서명, 급여평균)
select
    dept_code,
    avg(salary)
from
    employee
group by
    dept_code
having
    avg(salary) >= 3000000;


-- 사원테이블에서 J3직급을 제외하고, 직급별 인원수가 3명이상인 직급의 정보를 조회(직급코드, 인원수, 급여평균)
select
    job_code,
    count(*),
    trunc(avg(salary))
from
    employee
where
    job_code != 'J3'
group by
    job_code
having
    count(*) >= 3
order by
    1;

-- 1. where절에서 J3 제외하기
-- 2. having절에서 J3 제외하기


--관리하는 사원이 2명이상인 매니져의 사원아이디와 관리하는 사원수를 출력하세요.
select
    manager_id,
    count(*)
from
    employee
where
    manager_id is not null
group by
    manager_id
having 
    count(*) >= 2;
    
select
    manager_id,
    count(*)
from
    employee
group by
    manager_id
having 
    count(manager_id) >= 2;
    
-- 소계를 처리하는 rollup 
select
    nvl(job_code, '소계'),
    count(*)
from
    employee
group by
    rollup(job_code)
order by 
    job_code;
    

select
--    dept_code,
--    grouping(dept_code), -- 0이면 실제 데이터, 1이면 rollup에 의해 생성된 데이터
    decode(grouping(dept_code), 0, nvl(dept_code, '인턴'), 1, '총계') dept_code,
    count(*)
from
    employee
group by
    rollup(dept_code)
order by
    dept_code;
    

select
    decode(grouping(dept_code), 0, nvl(dept_code, '인턴'), 1, '총계') dept_code, 
    decode(grouping(job_code), 0, job_code, 1, '소계') job_code,
    count(*)
from
    employee
group by
    rollup(dept_code, job_code)
order by
    dept_code, job_code;
    
--======================================================
-- JOIN
--======================================================
-- 정규화된 테이블과 테이블을 합쳐서 가상테이블(relation)을 생성

-- join 특정컬럼을 기준으로 행과 행을 연결(가로)
-- union 동일한 컬럼을 가진 테이블 연결(세로)

-- join의 종류
/*
    1. Equi Join    : 동등조건(=)에 의해 연결되는 경우
        - inner join(내부조인)
        - outer join(외부조인)
        - cross join
        - self join
        - multiple join
        
    2. Non-Equi Join : 동등조건(=)이 아닌 조건(between and, in, not in..)에 의해 연결되는 경우

*/

-- join 문법
-- 1. ANSI 표준문법 : join, on키워드 사용
-- 2. 오라클 전용문법 : ,콤마 사용


-- 송종기사원의 부서명을 조회
-- 1. employee테이블에서 사원명이 송종기인 행의 dept_code 조회 
-- 2. department테이블에서 해당 dept_code로 dept_title을 조회
select 
    dept_code -- D9
from 
    employee
where
    emp_name = '송종기';
    
select 
    dept_title 
from 
    department
where
    dept_id = 'D9';

-- 조인으로 처리
-- 테이블 별칭 : as사용불가
select
    d.dept_title
from 
    employee e join department d
        on e.dept_code = d.dept_id
where
    e.emp_name = '송종기';

-----------------------------------------------------------------------
-- EQUI JOIN
-----------------------------------------------------------------------

--++++++++++++++++++++++++++++++++++++++++++++
-- INNER JOIN
--++++++++++++++++++++++++++++++++++++++++++++
-- 내부 조인(교집합)
-- 왼쪽/오른쪽테이블의 일치하는 행만 조회.
-- 기준컬럼이 null이면 제외, 상대테이블에서 매칭되는 행이 없으면 제외.
-- inner키워드 생략가능

-- employee에서 dept_code가 null인 사원 2행 제외
-- department에서 employee에서 참조되지 않은 D2, D3, D7 3행 제외
select
    *
from
    employee e inner join department d
        on e.dept_code = d.dept_id; -- 22
        
--[oracle 전용]
select
    *
from
    employee e, department d
where
    e.dept_code = d.dept_id;



--++++++++++++++++++++++++++++++++++++++++++++
-- OUTER JOIN
--++++++++++++++++++++++++++++++++++++++++++++
-- 외부조인 left outer join, right outer join
-- outer키워드 생략가능

-- 1. left outer join
-- 왼쪽테이블의 모든 행 포함. 오른쪽테이블에서는 조인조건에 만족하는 행만 포함.
-- 우측테이블에 매칭되는 행이 없다면 모두 null로 채워서 처리
select
    *
from
    employee e left outer join department d
        on e.dept_code = d.dept_id; -- 24 (22 + 2)
        
--[oracle 전용]
-- 외부조인 (+)를 상대테이블 컬럼에 추가
select
    *
from
    employee e, department d
where
    e.dept_code = d.dept_id(+);


        
-- 2.right outer join
select
    *
from
    employee e right outer join department d
        on e.dept_code = d.dept_id; -- 25 (22 + 3)
        
--[oracle 전용]
select
    *
from
    employee e, department d
where
    e.dept_code(+) = d.dept_id;

-- 3.full outer join
select
    *
from
    employee e full outer join department d
        on e.dept_code = d.dept_id; -- 27 (22 + 2 + 3)
        
--[oracle 전용] 없다.

-- 사원명, 직급명(job.job_name) 조회
-- employee.job_code ---- job.job_code
-- 1. 기준컬럼 null이면 제외
-- 2. 상대테이블에서 매칭되는 행이 없으면 제외

select count(distinct(job_code)) from employee;
select * from job;

-- 동일한 컬럼명을 사용하는 경우, 테이블별칭은 필수다.
select 
    e.emp_name,
    e.job_code,
    j.job_code,
    j.job_name
from
    employee e join job j
        on e.job_code = j.job_code;

-- 컬럼명이 유일하면 테이블 별칭을 생략할수 있다.
select
    *
from
    employee e join department d
        on e.dept_code = d.dept_id; 
        
-- 동일한 컬럼명을 사용하는 경우, on조건절 대신 using절을 사용할 수 있다.
-- 해당컬럼은 별칭으로 접근할 수 없다.
select
    *
from
    employee e join job j
        using(job_code);
        

--+++++++++++++++++++++++++++++++++++++++++++++++
-- CROSS JOIN
--+++++++++++++++++++++++++++++++++++++++++++++++
-- 상호조인
-- Cartesian Product(카테시안곱) - 모든 경우의 수
-- 좌우측테이블이 상대테이블의 행과 만날수 있는 모든 경우
select
    *
from
    employee e cross join department d; -- 216 (24 * 9)
    
--[oracle 전용]
select
    *
from
    employee e, department d;


-- 평균급여와 각 사원 급여차
select
    emp_name,
    salary,
    salary - avg_sal
from
    employee e cross join (select trunc(avg(salary)) avg_sal from employee);

-- 사원정보 조회(사원명, 부서코드, 급여, 부서별 평균급여)
select
    dept_code,
    trunc(avg(salary)) avg_sal
from
    employee
group by
    dept_code;


select
    e.emp_name,
    nvl(e.dept_code, '인턴') dept_code,
    e.salary,
    t.avg_sal
from 
    employee e left join (select
                        dept_code,
                        trunc(avg(salary)) avg_sal
                    from
                        employee
                    group by
                        dept_code) t
        on nvl(e.dept_code, 'ㅋㅋㅋㅋ') = nvl(t.dept_code, 'ㅋㅋㅋㅋ');


--++++++++++++++++++++++++++++++++++++++
-- SELF JOIN
--++++++++++++++++++++++++++++++++++++++
-- 같은 테이블을 연결하는 조인

-- 사원명, 관리자명을 조회
-- manager_id가 null인 사원, 관리자가 아닌 사원은 제외
select 
    e1.emp_id 사번,
    e1.emp_name 사원명,
    e1.manager_id 관리자사번,
--    e2.emp_id 관리자사번,
    e2.emp_name 관리자명
from 
    employee e1 left join employee e2
        on e1.manager_id = e2.emp_id;

--[oracle 전용]
select 
    e.emp_name, 
    m.emp_name
from
    employee e, employee m
where
    e.manager_id = m.emp_id(+);


--++++++++++++++++++++++++++++++++++++++++
-- MULTIPLE JOIN
--++++++++++++++++++++++++++++++++++++++++
-- 여러 테이블을 조인. 

select * from employee; -- dept_code
select * from department; -- dept_id, location_id
select * from location; -- local_code, national_code
select * from nation; -- national_code

-- 사원명(employee.emp_name), 직급명(job.job_name), 부서명(department.dept_title), 지역명(location.local_name), 국가명(nation.national_name)
-- left join으로 추가된 행이 누락되지 않도록 연속적으로 left join처리해야 한다.
-- join되는 순서가 중요하다.
select
    e.emp_name, 
    j.job_name,
    d.dept_title,
    l.local_name,
    n.national_name
from    
    employee e 
        left join department d
            on e.dept_code = d.dept_id
        left join location l
            on d.location_id = l.local_code
        left join nation n
            on l.national_code = n.national_code
        join job j
            on e.job_code = j.job_code;
            
--[oracle 전용]
select
    *
from
    employee e, location l, nation n, department d
where
    e.dept_code = d.dept_id(+)
    and
    d.location_id = l.local_code(+)
    and
    l.national_code = n.national_code(+);

            
-- 직급이 대리이면서 ASIA지역에 근무하는 직원 조회(사번, 이름, 직급명, 부서명, 근무지역명, 급여)
select 
    e.emp_id, e.emp_name, j.job_name, d.dept_title, l.local_name, e.salary
from
    employee e
        join job j
            using(job_code)
        left join department d
            on e.dept_code = d.dept_id
        left join location l
            on d.location_id = l.local_code
where
    j.job_name = '대리'
  and
    l.local_name like 'ASIA%';
    
--[oracle 전용]
select 
    e.emp_id, e.emp_name, j.job_name, d.dept_title, l.local_name, e.salary
from
    employee e, job j, department d, location l
where
        e.job_code = j.job_code
    and
        e.dept_code = d.dept_id(+)
    and
        d.location_id = l.local_code(+)
    and
        j.job_name = '대리'
    and
        l.local_name like 'ASIA%';


---------------------------------------------------------------------------------------
-- NON-EQUI JOIN
---------------------------------------------------------------------------------------
-- 동등비교(=)가 조인조건을 사용하는 경우

select * from employee;
select * from sal_grade;

-- employee.salary를 통해 급여등급 조회
select 
    e.emp_name,
    e.salary,
    s.*
from
    employee e join sal_grade s
        on e.salary between s.min_sal and s.max_sal;

--[oracle 전용]
select
    *
from
    employee e, sal_grade s
where
    e.salary between s.min_sal and s.max_sal;



--================================================
-- SET OPERATOR
--================================================
-- 여러 결과집합을 세로로 연결후 하나의 가상테이블을 생성한다.

-- select절의 컬럼수가 동일
-- select절의 해당컬럼의 자료형이 상호호환 가능(char, varchar2 상호호환) 
-- 컬럼명이 다른 경우, 첫번째 결과집합의 컬럼명을 사용
-- order by절은 마지막 결과집합에 한번만 사용

select 
    'a' "aaaa", 123, sysdate
from 
    dual
union
select 
    'b' "bbbb", 456, sysdate
from 
    dual
order by
    1 desc;

-- 연산자 종류
/*
    1. union 합집합 - 두 결과집합을 합친후 중복제거, 첫번째 컬럼기준 오름차순 정렬.
    2. union all 합집합 - 두 결과집합을 모두 포함.
    3. intersect 교집합
    4. minus 차집합
*/

-- 부서코드 D5인 사원 조회
-- salary가 300만이상인 사원 조회
-- union all : 두개의 결과집합을 추가작업 없이 그냥 연결한다. 작업속도 빠르다.
-- union : 두개의 결과집합을 합친 후, 중복된 행을 제거, 첫번째 컬럼 기준 오름차순. 작업속도 느리다.
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    dept_code = 'D5'
union
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    salary >= 3000000;

-- intersect : 중복된 행만 출력, 모든 컬럼값이 같은 때 중복으로 취급.
-- minus : 첫번째 결과집합에서 두번째 결과집합과의 중복된 행을 제거후 출력
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    dept_code = 'D5'
minus
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    salary >= 3000000;


-- 판매데이터 관리
create table tb_sales(
    pname varchar2(50),
    pcount number,
    sale_date date
);
--drop table tb_sales;

--두달전 판매 데이터
insert into tb_sales values ('버터링', 10, add_months(sysdate, -2));
insert into tb_sales values ('칸쵸', 15, add_months(sysdate, -2) + 1);
insert into tb_sales values ('와클', 7, add_months(sysdate, -2) + 2);
insert into tb_sales values ('버터링', 20, add_months(sysdate, -2) + 5);
insert into tb_sales values ('포카칩', 30, add_months(sysdate, -2) + 7);

-- 한달전 판매 데이터
insert into tb_sales values ('스윙칩', 10, add_months(sysdate, -1));
insert into tb_sales values ('초코칩쿠키', 15, add_months(sysdate, -1) + 1);
insert into tb_sales values ('와클', 7, add_months(sysdate, -1) + 2);
insert into tb_sales values ('버터링', 20, add_months(sysdate, -1) + 5);
insert into tb_sales values ('포카칩', 30, add_months(sysdate, -1) + 7);
insert into tb_sales values ('와클', 10, add_months(sysdate, -1) + 10);

-- 이번달 판매데이터
insert into tb_sales values ('스윙칩', 8, sysdate - 10);
insert into tb_sales values ('초코칩쿠키', 22, sysdate - 9);
insert into tb_sales values ('와클', 10, sysdate - 7);
insert into tb_sales values ('버터링', 15, sysdate - 5);
insert into tb_sales values ('포카칩', 3, sysdate - 2);
insert into tb_sales values ('야채타임', 5, sysdate - 1);

--insert into tb_sales values ('야채타임', 5, to_date('21/01/15'));
--delete from tb_sales where sale_date = to_date('21/01/15');

select * from tb_sales;
commit;

-- 2달전의 판매내역 조회
-- 1달후, 내년, 10년후에도 동일하게 2달전을 조회
select
    *
from
    tb_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate, -2), 'yyyy-mm');


-- 테이블쪼개기
-- tb_sales에는 현재달의 판매데이터만 관리, 지난달데이터는 tb_sales_yyyymm테이블을 별도 생성후 관리

-- 두달전
create table tb_sales_202201
as
select
    *
from
    tb_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate, -2), 'yyyy-mm');

select * from tb_sales_202201;

--한달전
create table tb_sales_202202
as
select
    *
from
    tb_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate, -1), 'yyyy-mm');

select * from tb_sales_202202;

select * from tb_sales;

--delete from tb_sales
--where to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate, -2), 'yyyy-mm'); 
--delete from tb_sales
--where to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate, -1), 'yyyy-mm'); 
commit;

-- 지난 3개월 판매내역 데이터를 조회
select * from tb_sales_202201
union all
select * from tb_sales_202202
union all
select * from tb_sales;


-- 지난 3개월의 제품별 판매량을 조회
select
    pname,
    sum(pcount)
from (
    select * from tb_sales_202201
    union all
    select * from tb_sales_202202
    union all
    select * from tb_sales
)
group by 
    pname
order by
    2 desc;
    
    
--================================================
-- SUB QUERY
--================================================
-- 하나의 sql문안에 포함된 또 하나의 sql문
-- mainquery하위에 subquery가 포함되어 있음.
-- mainquery 실행중에 subquery를 실행하고, 그 결과를 mainquery에 전달하는 방식

-- 유의사항
-- subquery는 반드시 소괄호로 묶어야 한다.
-- 비교연산시 우항에 작성할 것.
-- order by문법 지원되지 않음.

-- 서브쿼리의 유형
/*
    1. 단일행 단일컬럼 (1행1열)
    2. 다중행 단일컬럼 (n행1열)
    3. 다중열(단일행/다중행) (n행 m열)
    4. 상관 - 메인쿼리에서 값을 전달받아 처리
    5. 스칼라 - select절에 사용된 단일값 상관서브쿼리
    6. 인라인뷰 - from절에 사용된 서브쿼리

*/

-- 노옹철의 관리자이름을 조회
select
    emp_name
from
    employee
where
    emp_id = (
            select
                manager_id
            from
                employee
            where
                emp_name = '노옹철');

----------------------------------------------------------------------------
-- 단일행 단일컬럼 서브쿼리
----------------------------------------------------------------------------
-- 서브쿼리 조회결과가 1행1열인 경우

-- 전 직원의 평균 급여보다 많은 급여를 받는 사원 조회(사번, 이름, 직급코드, 급여)
select
    emp_id, emp_name, job_code, salary, 
    trunc((select avg(salary) from employee)) 평급급여
from
    employee
where
    salary > (select avg(salary) from employee);

-- (윤은해와 같은 금액의 급여)를 받는 사원을 조회(사번 사원명 급여)
select 
    emp_id, emp_name, salary
from
    employee
where
    salary = (select salary from employee where emp_name = '윤은해')
    and
    emp_name <> '윤은해';


-- 사원테이블에서 급여가 최대/최소인 사원 조회(사번 사원명 급여)
select
    emp_id, emp_name, salary
from
    employee
where
--    salary = (select max(salary) from employee)
--    or
--    salary = (select min(salary) from employee);
    salary in ((select max(salary) from employee), (select min(salary) from employee));


-------------------------------------------------------------------------------
-- 다중행 단일컬럼 서브쿼리
-------------------------------------------------------------------------------
-- 서브쿼리 조회결과가 1열n행일때 서브쿼리
-- in | not in, any(some), all, exists | not exists 연산자와 함께 사용가능

-- in 값목록에 포함되어 있는지 검사
-- in (값1, 값2, 값3, ...) - 목록자리에 단중행 서브쿼리 사용가능

-- 송종기, 하이유 사원이 속한 부서원 조회
select
    dept_code
from 
    employee
where
    emp_name in ('송종기', '하이유');

select
    emp_name,
    dept_code
from
    employee
where 
    dept_code in (
        select
            dept_code
        from 
            employee
        where
            emp_name in ('송종기', '하이유')        
    );

-- 차태연, 박나라, 이오리사원과 같은 직급 사원 조회 (사원명, 직급명(직급코드))

select 
    emp_name, 
    job_name || '(' || job_code || ')' 직급
from 
    employee e join job j
        using(job_code)
where
    job_code in (
        select
            job_code
        from
            employee
        where
            emp_name in ('차태연', '박나라', '이오리')    
    );

---------------------------------------------------------------------------------
-- 다중열 서브쿼리
---------------------------------------------------------------------------------
-- 서브쿼리의 결과가 n열 m행(1행이상)인 경우

-- 퇴사한 직원과 같은 부서, 같은 직급에 해당하는 사회 조회(이름, 직급코드, 부서코드)
-- = 연산자인 경우 서브쿼리 리턴결과가 딱 1행일때만 처리할 수 있다.
-- in 연산자인 경우 서브쿼리 리턴결과 1 ~ n행일때 처리할 수 있다. 

select
    emp_name,
    dept_code,
    job_code
from
    employee
where
    (dept_code, job_code) in  (
        select 
            dept_code, job_code
        from
            employee
        where
            quit_yn = 'Y'
    );

-- 퇴사처리
select * from employee;

update 
    employee
set 
    quit_date = sysdate,
    quit_yn = 'Y'
where
    emp_id = '221';
        
-- 직급별 최소 급여를 받는 사원 조회(이름, 직급코드, 급여)
select
    job_code,
    min(salary)
from
    employee
group by
    job_code;
    
select
    emp_name,
    job_code,
    salary
from
    employee
where
    (job_code, salary) in (
        select
            job_code,
            min(salary)
        from
            employee
        group by
            job_code
    )
order by 
    job_code;
    
    
--부서별 최대급여를 받는 사원의 사원명, 부서명, 급여를 출력. 
--(심화1) 최소급여를 받는 사원도 출력.
--(심화2) 인턴사원도 포함시키기


select emp_name, dept_title, salary
from employee left join department on dept_code = dept_id
where (dept_code, salary) in (select dept_code, max(salary) 
                        from employee
                        group by dept_code)
order by 2,1;--6행

--(심화1)
--![질의 결과](https://d.pr/i/AYtgxl)

    select emp_name, dept_title, salary
    from employee left join department on dept_code = dept_id
    where (dept_code, salary) in (select dept_code, max(salary) 
                            from employee
                            group by dept_code)
        or (dept_code, salary) in (select dept_code, min(salary) 
                            from employee
                            group by dept_code)
    order by 2,1;--12행

--(심화2)

    select emp_name, nvl(dept_title,'인턴') 부서, salary
    from employee left join department on dept_code = dept_id
    where (nvl(dept_code,-1), salary) in (select nvl(dept_code,-1), max(salary) 
                            from employee
                            group by dept_code)
        or (nvl(dept_code,-1), salary) in (select nvl(dept_code,-1), min(salary) 
                            from employee
                            group by dept_code)
    order by 2,1;--14행

-----------------------------------------------------------------------------
-- 상관 서브쿼리
-----------------------------------------------------------------------------
-- 상관(상호연관)
-- 메인쿼리의 값을 전달받아 서브쿼리를 수행하고, 그 결과값을 반환.
-- 일반서브쿼리와는 다르게 단독실행(블럭)이 불가하다.


-- 직급별 평균급여보다 많은 급여를 받는 사원 조회
-- employee <join> 직급별 평균급여 가상테이블 
select
    e.*, 
    t.avg_sal_by_job
from 
    employee e join (
        select
            job_code, avg(salary) avg_sal_by_job
        from
            employee
        group by
            job_code
    
    ) t
        on e.job_code = t.job_code
where
    e.salary > t.avg_sal_by_job;

-- 상관 서브쿼리로 처리
select
    emp_name, job_code, salary
from
    employee e
where
    salary > (select avg(salary) from employee where job_code = e.job_code);

-- 실제 매행마다 처리되는 쿼리
select avg(salary) from employee where job_code = 'J2';
    
select * from employee;

-- 부서별 평균급여보다 많은 급여를 받는 사원 조회 (인턴 포함)
select
    emp_name, 
    dept_code, 
    salary, 
    trunc((select avg(salary) from employee where nvl(dept_code, '111') = nvl(e.dept_code, '111'))) avg_sal_by_dept --  스칼라서브쿼리 select절의 상관서브쿼리
from
    employee e
where
    salary > (select avg(salary) from employee where nvl(dept_code, '111') = nvl(e.dept_code, '111'));
    
select avg(salary) from employee where dept_code = null;

-- exists (sub-query)
-- 서브쿼리의 결과집합이 1행이상인 경우 true로 처리, 0행인 경우 false처리
select
    *
from
    employee
where
--    1 = 1; -- 무조건 true.
    1 = 0; -- 무조건 false

select
    *
from 
    employee
where
--    exists (select * from employee) -- 조회된 행이 있으므로 true처리
    exists (select * from employee where 1 = 0); -- 조회된 행이 없으므로 false처리

-- 실제 부서원이 존재하는 부서만 조회
select
    *
from
    department d
where
    exists(
        select 
            1
        from 
            employee
        where
            dept_code = d.dept_id
    );
    
select 
    1
from 
    employee
where
    dept_code = 'D3';

-- 실제 부서원이 존재하지 않는 부서만 조회
-- 서브쿼리의 행이 존재하면 false, 서브쿼리의 행이 존재하지 않으면, true -> 결과집합에 포함.
select
    *
from
    department d
where
    not exists(
        select 
            1
        from 
            employee
        where
            dept_code = d.dept_id
    );
    
-- 관리자사원 조회(부하직원의 manager_id가 관리자사원의 emp_id를 가리킨다) 
-- 관리자 emp_id가 부하직원의 manager_id로 참조되는 사원(행) 조회
select
    emp_id, emp_name
from
    employee e
where
    exists (
        select
            1
        from 
            employee
        where
            manager_id = e.emp_id
    );

-- not exists활용해 최대/최소에 해당하는 행을 조회
select 
    *
from 
    employee e
where
    not exists (
        select
            1
        from
            employee
        where
            salary < e.salary
    );


-------------------------------------------------------------------------------------
-- SCALA 서브쿼리
-------------------------------------------------------------------------------------
-- scala값은 단일값. 
-- select절에 사용하는 결과값이 하나인(1행1열) 상관서브쿼리

-- 사번, 이름, 관리자사번, 관리자명

-- self-join으로 처리
select
    e.emp_id,
    e.emp_name,
    e.manager_id,
--    m.emp_id,
    m.emp_name manager_name
from 
    employee e left join employee m
        on e.manager_id = m.emp_id
order by
    emp_id;

-- 스칼라 서브쿼리로 처리
select
    emp_id, 
    emp_name,
    manager_id, 
    (select emp_name from employee where emp_id = e.manager_id) manager_name 
from
    employee e
order by
    emp_id;
    
-- 사원명, 부서명, 급여, 부서별 평균급여 조회 (스칼라서브쿼리)
select
    emp_name 사원명,
    (
        select 
            dept_title 
        from 
            department 
        where 
            dept_id = e.dept_code
    ) 부서명,
    salary 급여,
    (
        select
            trunc(avg(salary)) 
        from
            employee
        where
            dept_code  = e.dept_code
    ) "부서별 평균급여"
from
    employee e;


-------------------------------------------------------------------------------
-- INLINE VIEW
-------------------------------------------------------------------------------
-- from절에 사용한 subquery
/*
    view란?
    실제테이블을 주어진 view를 통해 제한적으로 사용가능하도록 함.
    1. inline view (1회용)
    2. stored view - 별도의 객체로 저장해 재사용가능하도록 함.

*/

-- 사원테이블에서 여사원의 사번, 사원명, 부서코드, 성별을 조회
select
    emp_id, emp_name, dept_code, gender
from (
    select
        emp_id, emp_name, dept_code,
        decode(substr(emp_no, 8, 1), '2', '여', '4', '여', '남') gender
    from
        employee e
)
where
    gender = '여';

-- 입사년도가 1990 ~ 1999년인 사원조회(사번, 사원명, 입사년도) 
select
    emp_id, emp_name, hire_year
from (
    select
        e.*,
        extract(year from hire_date) hire_year
    from
        employee e
)
where
    hire_year between 1990 and 1999;



-- 사원중에 30/40대(30~49) 여자사원의 사번, 부서명, 성별, 나이를 조회
select 
    emp_id, 
    nvl((
        select
            dept_title
        from
            department
        where
            dept_id = e.dept_code
    ), '인턴') dept_title,
    gender,
    age
from (
    select
        e.*,
        decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender,
        (extract(year from sysdate)) - (substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900, '2', 1900, 2000)) + 1 age -- 현재년도 - 출생년도 + 1
    from
        employee e
) e
where
    gender = '여'
    and
    age between 30 and 49;
    

--===============================================
-- 고급쿼리
--===============================================

----------------------------------------------------------------------------    
-- TOP-N 분석
----------------------------------------------------------------------------
-- 특정컬럼기준으로 정렬후에 가장 앞선 n개의 행만 결과집합에 담아 리턴

-- rownum
-- 테이블생성후 각행이 insert될때 oracle에서 자동으로 부여하는 각 행에 대한 일력번호. 한번 부여되면 변경불가하다.
-- where절에 새로운 조건이 추가되었을때, inline-view를 사용했을때 새로 부여된다.
select
    rownum,
    e.*
from
    employee e
where
    dept_code in ('D5', 'D6');

-- 회사에서 급여가 높은 Top-5 조회
-- 1. 급여내림차순정렬 
-- 2. inline-view(rownum새로부여) 
-- 3. rownum 1 ~ 5 필터링
select 
    rownum,
    e.*
from (
    select
        emp_name, salary
    from
        employee
    order by
        salary desc
) e
where
    rownum between 1 and 5;
    
-- 최신 입사한 5명 사원 조회

select 
    rownum,
    e.*
from (
    select 
        emp_id, emp_name, hire_date
    from
        employee
    order by
        hire_date desc
) e
where
    rownum between 1 and 5;

-- 직급이 대리인 사원중에서 연봉상위 3명 조회
-- (급여 + (급여 * 보너스)) * 12 -> 연봉
select
    rownum, 
    e.*
from (
    select
        emp_name,
        job_code,
        (salary + (salary * nvl(bonus, 0))) * 12 annual_salary
    from
        employee e
    where
        job_code = (select job_code from job where job_name = '대리')
    order by
        annual_salary desc
) e
where
    rownum between 1 and 3;



-- 부서별 급여평균 top 3 조회(부서명, 평균급여)
select 
    rownum,
    e.*
from (
    select
        nvl(dept_code, '인턴') detp_code, 
        trunc(avg(salary)) avg_sal
    from
        employee
    group by
        dept_code
    order by 
        avg_sal desc
) e
where
    rownum between 1 and 3;



-- 급여 상위 랭킹 6 ~ 10위 조회
-- rownum은 where절이 완전히 끝났을때 부여 또한 끝이 난다. 
-- 1부터 시작하지 않고, offset이 있는 경우 인라인뷰가 한레벨 필요하다.
select
    *
from (
    select 
        rownum rnum,
        e.*
    from (
        select
            emp_name, salary
        from
            employee
        order by
            salary desc
    ) e
) 
where
    rnum between 6 and 10;

-- with절
-- inlineview에 이름을 붙여서 재사용가능하도록 하는 구문
with some_view
as
(
    select
        nvl(dept_code, '인턴') detp_code, 
        trunc(avg(salary)) avg_sal
    from
        employee
    group by
        dept_code
    order by 
        avg_sal desc
)
select 
    rownum,
    e.*
from (
    some_view
) e
where
    rownum between 1 and 3;

--------------------------------------------------------------------------------
-- WINDOW FUNCTION
--------------------------------------------------------------------------------
-- 행과 행간의 관계를 쉽게 파악/정의하기 위한 ANSI표준함수
-- select절에서만 사용가능
-- 순위관련처리, 집계관련처리, 순서관련처리, 비율/통계관련처리

--++++++++++++++++++++++++++++++++++++++++++++++++++
-- 순위관련 윈도우함수
--++++++++++++++++++++++++++++++++++++++++++++++++++
/*
window_function (args) over ([partition by절][order by절][windowing절])
- args 함수인자(컬럼명) 0 ~ n개 
- partition by : 전체집합을 다시 그룹핑하기 위한 구문
- order by : 행간의 정렬
- windowing절 : 대상행을 지정
*/

-- rank() over()
-- 중복된 값이 있다면 중복된 만큼 건너뛰고 순위를 부여
-- dense_rank() over()
-- 중복된 값이 있어도 중복된 만큼 건너뛰지 않고 순위를 부여
select
    emp_name,
    salary,
    rank() over(order by salary desc) rank,
    dense_rank() over(order by salary desc) rank
from
    employee;

-- top-n분석에 활용
select
    *
from(
    select
    emp_name,
    salary,
    rank() over(order by salary desc) rank
from
    employee
)
where
    rank between 6 and 10;
    
-- 부서별 급여순위를 조회
select
    emp_name,
    dept_code,
    salary,
    rank() over(partition by dept_code order by salary desc) rank_by_dept,
    rank() over(order by salary desc) rank_by_all
from
    employee
order by
    dept_code;

-- 부서별 입사순서를 조회(사원명, 부서명, 입사일, 부서별 입사순번)
select
    emp_name,
    (select dept_title from department d where dept_id = e.dept_code) dept_title,
    hire_date,
    rank () over(partition by dept_code order by hire_date) rank,
    dense_rank () over(partition by dept_code order by hire_date) rank,
    row_number () over(partition by dept_code order by hire_date) rank
from
    employee e;

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- 집계처리 윈도우 함수
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- 집계/누계 관련 처리를 하는 윈도우 함수

-- sum() over()
-- 그룹함수와 일반컬럼은 같이 쓸 수 없지만, 윈도우함수 일반컬럼은 함께 사용이 가능하다.
select
    emp_name,
    dept_code,
    salary,
    sum(salary) over() "전사원 급여합계",
    trunc(salary / sum(salary) over() * 100, 1) "전체급여대비%",
    sum(salary) over(partition by dept_code) "부서별 급여합계",
    sum(salary) over(partition by dept_code order by salary) "부서별 누계"
from
    employee;

-- 판매테이블 지난 3개월 제품별 누계
select
    s.*,
    sum(pcount) over(partition by pname order by sale_date) "제품별 누계"
from (
    select * from tb_sales_202201
    union all
    select * from tb_sales_202202
    union all
    select * from tb_sales
) s;

-- 사원정보 조회(사번, 사원명, 부서코드, 급여, 부서별평균급여, 전체사원평균급여)
select
    emp_id,
    emp_name,
    dept_code,
    salary,
    trunc(avg(salary) over(partition by dept_code)) avg_sal_by_dept,
    trunc(avg(salary) over()) avg_sal_by_all,
    
    max(salary) over(),
    min(salary) over(),
    count(*) over()
from
    employee;


--===================================================
-- DML
--===================================================
-- Data Manipulation Language 데이터 조작어
-- 테이블의 데이터를 조작하는 명령어 모음
-- insert / update / delete / select
-- 처리된 행의 개수를 리턴
-- 명령어 실행으로는 DB에 실제 반영되지 않으므로, TCL commit명령어를 실행해야한다.
-- rollback처리하면 마지막 commit시점으로 돌아간다.


----------------------------------------------------------------------------------
-- INSERT
----------------------------------------------------------------------------------
-- 특정 테이블에 새로운 행(row/record)을 추가하는 명령어
-- 정상실행시 테이블에 행이 추가됨.


/*
    문법1 - 컬럼명을 지정하지 않는 경우 (모든 컬럼을 테이블의 정의된 순서대로 작성)
        insert into 테이블명
        values (컬럼1값, 컬럼2값, ....);
    
    
    문법2 - 컬럼명을 지정하는 경우 (지정한 컬럼만 데이터를 작성. not null컬럼은 생략이 불가)
        insert into 테이블명 (컬럼명1, 컬럼명2, ....)
        values (컬럼1값, 컬럼2값, ....);

*/

create table sample (
    code number,
    name varchar2(100) not null,
    nickname varchar2(100) default '홍길동',
    email varchar2(100) default 'honggd@gmail.com' not null,
    enroll_date date default sysdate
);

-- 데이터추가
insert into
    sample 
values(
    123, '고길동', '미스터고고', 'go@gmail.com', default
);

insert into
    sample 
values(
    123, '고길동', null, 'go@gmail.com', default
);
-- 자료형 일치하지 않을때 ORA-01722: 수치가 부적합합니다
-- 컬럼수가 맞지 않는 경우 ORA-00947: 값의 수가 충분하지 않습니다
-- not null컬럼에 null값 대입 ORA-01400: NULL을 ("KH"."SAMPLE"."NAME") 안에 삽입할 수 없습니다
-- 지정한 자료형 크기보다 큰값을 추가할 때 ORA-12899: "KH"."SAMPLE"."NICKNAME" 열에 대한 값이 너무 큼(실제: 360, 최대값: 100)

-- 생략한 값은 null이 대입되거나, default값이 지정된 경우 default값으로 처리
insert into 
    sample (code, name, email)
values(
    345, '신사', 'sinsa@gmail.com'
);

-- not null컬럼은 생략할 수 없다. ORA-01400: NULL을 ("KH"."SAMPLE"."NAME") 안에 삽입할 수 없습니다.
-- not null이어도 default값이 지정된 경우는 생략할 수 있다.
insert into 
    sample (code, name)
values(
    555, '세종'
);

insert into 
    sample (name, code )
values(
    '전봉준', 1234
);

select * from sample;

commit;


-- ex_employee 생성!
-- subquery를 이용해 table을 생성하면, not null을 제외한 제약조건, 기본값은 모두 제거된다.
create table ex_employee
as
select
    *
from
    employee;

select * from ex_employee;

-- not null 확인
desc employee;

-- 기본값 확인
select
    *
from
    user_tab_cols
where
    table_name = 'EX_EMPLOYEE';

-- 기본값, 제약조건 추가
alter table ex_employee
add constraint pk_ex_employee primary key(emp_id)   -- emp_id 기본키지정(식별자컬럼)
modify quit_yn default 'N'                          -- 기본값 지정
modify hire_date default sysdate;                    -- 기본값 지정

-- 데이터 추가
-- 301 함지민 - 모든 컬럼에 데이터추가 (문법1)
-- 302 김톄리 - not null 컬럼만 데이터 추가 (문법2)
insert into
    ex_employee 
values(
    '301', '함지민', '990909-2345678' , 'ham@gmail.com', '01012341234', 'D9', 'J2', 'S2', 5000000, 0.1, '200', default, null, default
);

insert into
    ex_employee (emp_id, emp_name, emp_no, job_code, sal_level)
values(
    '302', '김톄리', '770707-2345432', 'J3', 'S3'
);


select * from ex_employee;

-- 서브쿼리를 이용한 insert
create table ex_employee_info (
    emp_id char(3),
    emp_name varchar2(30),
    email varchar2(100)
);

-- values절이 없다.
insert into ex_employee_info (
    select
        emp_id, emp_name, email
    from
        ex_employee
);

select * from ex_employee_info;

-- ex_employee_manager테이블 생성
-- 사번 사원명 매니져사번 매니져명
desc ex_employee;

create table ex_employee_manager (
    emp_id char(3),
    emp_name varchar2(20),
    manager_id char(3),
    manager_name varchar2(20)
);

insert into ex_employee_manager(
    select
        emp_id,
        emp_name,
        manager_id,
        (select emp_name from employee where emp_id = e.manager_id) manager_name
    from
        employee e
);

select * from ex_employee_manager;

-- 특정테이블의 데이터를 여러테이블에 동시에 insert하기
create table ex_employee_hire_date (
    emp_id char(3),
    emp_name varchar2(20),
    hire_date date
);

create table ex_employee_salary (
    emp_id char(3),
    emp_name varchar2(20),
    salary number
);

select * from ex_employee_hire_date;
select * from ex_employee_salary;

-- insert all
insert all
into ex_employee_salary values(emp_id, emp_name, salary)
into ex_employee_hire_date values(emp_id, emp_name, hire_date)
select
    emp_id, emp_name, salary, hire_date
from
    ex_employee;


----------------------------------------------------------------------
-- UPDATE
----------------------------------------------------------------------
-- 특정행을 찾고, 해당행의 컬럼값을 변경
-- 처리이후 행의 수의 변화는 없다.
-- 요청후 수정된 행의 수를 반환한다.

-- 205번 사원의 급여를 100000원인상, 직급은 J2로 변경
select
    *
from
    ex_employee
where
    emp_id = '205';
    
    
update 
    ex_employee
set
    job_code = 'J4',
    salary = salary + 100000
where
    emp_id = '205';
    
commit;
rollback;

-- where절에 행이 여러개 조회되면 동시에 여러행을 수정가능
-- D5부서원의 급여를 10%로 인상
select * from ex_employee where dept_code = 'D5';

update
    ex_employee
set
    salary = salary + (salary * 0.1)
where
    dept_code = 'D5';

-- 임시환 차장의 직급을 과장으로 변경
select * from ex_employee where emp_name = '임시환';

update
    ex_employee
set
    job_code = (select job_code from job where job_name = '과장')
where
    emp_name = '임시환';

-- where절에 조건컬럼은 식별자컬럼을 사용하는 것이 좋다.


---------------------------------------------------------------------------------------
-- DELETE
---------------------------------------------------------------------------------------
-- 특정행을 삭제하는 구문. 처리결과 행의 수가 줄어든다.

--delete from
--    ex_employee
--where
--    emp_id = '302';

select * from ex_employee;

rollback;
commit;

-- where절 사용하지 않으면 전체행이 삭제
--delete from
--    ex_employee;


--+++++++++++++++++++++++++++++++++++++++++++++++++
-- TRUNCATE
--+++++++++++++++++++++++++++++++++++++++++++++++++
-- DDL로써, 전체행을 삭제한다.
-- auto-commit되므로, rollback으로 복구할 수 없다.
-- delete와 달리 before-image를 생성하지 않으므로, 처리속도가 매우 빠르다.

truncate table ex_employee;

select * from ex_employee;
rollback;

insert into ex_employee (
    select * from employee
);
commit;

-- DDL / DML을 혼용할 때 주의점
create table tb_test (
    id varchar2(20)
);

insert into tb_test values('honngd');
insert into tb_test values('sinsa');

-- 중간 끼어든 ddl작업 
-- commit실행시, 이전 작업내용 포함
create table tb_test2 (
    id varchar2(20)
);

rollback;

select * from tb_test;


--=====================================================
-- DDL
--=====================================================
-- Data Definition Language 데이터정의어
-- database객체를 생성/수정/삭제하는 명령어
-- create / alter / drop
-- 자동커밋되므로 DML과 혼용시 주의해야 한다.

-- db의 객체 조회
select
    count(distinct object_type)
from
    all_objects; -- Data Dictionary 객체의 메타정보를 관리하는 테이블

/*
오라클 database객체 종류
- table
- user
- view
- sequence
- index
- package
- procedure
- function
- trigger
- synonym
- job scheduler
.....

*/

----------------------------------------------------------------------------------
-- CREATE
----------------------------------------------------------------------------------
-- 주석
-- 테이블 생성시, 테이블에 대해, 컬럼에 대해 주석을 작성할 수 있다.
select
    *
from 
    user_tab_comments
where
    table_name = 'EX_EMPLOYEE';
    
select
    *
from
    user_col_comments
where
    table_name = 'EX_EMPLOYEE';

-- 테이블 / 컬럼 주석
comment on table ex_employee is '사원관리 연습테이블이다~';
comment on table ex_employee is ''; -- db '' null이 같다.

comment on column ex_employee.emp_id is '사번';
comment on column ex_employee.emp_name is '사원명';
comment on column ex_employee.emp_no is '주민번호';

--++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- 제약조건 constraint
--++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- 각컬럼에 대해 데이터 제약을 설정할 수 있다.
-- 하나의 레코드에서 하나의 컬럼이라도 제약조건을 위반하면 레코드 자체가 insert되지 않는다.
-- 데이터 무결성을 지키기 위해 필수적이다.
-- (데이터의 정확성, 일관성을 지켜내는 것)

/*
    1. not null (C): null을 허용하지 않음. 필수 컬럼
    2. unique  (U): 다른 레코드와 중복값을 허용하지 않음.
    3. primary key (P): 식별자컬럼. 기본키. 
        - 다른 레코드와 중복값을 허용하지 않고, null도 허용하지 않음.
        - 테이블당 하나만 지정 가능
    
    4. foreign key (R) : 외래키. 부모테이블에 있는 값만 참조할 수 있다.
        - department.dept_id -> employee.dept_code(FK) 
        
    5. check      (C)  : 지정한 도메인의 값만 사용할 수 있다.
        - 도메인의 범위를 지정함.
        
    CONSTRAINT_TYPE C는 not null 또는 check제약조건을 의미하므로 search_condition컬럼을 확인할것
*/

-- 제약조건 조회
select
    *
from
    user_constraints
where
    table_name = 'EMPLOYEE';

select
    *
from
    user_cons_columns
where
    table_name = 'EMPLOYEE';

-- 자주 사용되는 제약조건 조회 쿼리
select
    constraint_name,
    uc.table_name,
    ucc.column_name,
    uc.constraint_type,
    uc.search_condition
from
    user_constraints uc join user_cons_columns ucc
        using(constraint_name)
where
    uc.table_name = 'EMPLOYEE';



-- 제약조건 작성방법
-- 1. 컬럼레벨 nn, pk, uq, fk, ck
-- 2. 테이블레벨 pk, uq, fk, ck

--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- unique 제약조건
--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- 각 레코드별로 고유한 값을 사용해야 한다.

-- 제약조건 테스트용 테이블
create table tb_member(
    id varchar2(20),
    name varchar2(50) not null,
    password varchar2(20) not null,
    email varchar2(50) constraint uq_tb_member_email unique,
    gender char(1),
    reg_date date
);

insert into 
    tb_member
values (
    'honggd', '홍길동', '1234', 'gd@naver.com', 'M', sysdate
);
insert into 
    tb_member
values (
    'gogd', '고길동', '1234', 'gd@naver.com', 'M', sysdate
); -- ORA-00001: 무결성 제약 조건(KH.UQ_TB_MEMBER_EMAIL)에 위배됩니다

-- uq제약조건이 걸려있어도 null은 허용한다.
insert into 
    tb_member
values (
    'gogd', '고길동', '1234', null, 'M', sysdate
);

insert into 
    tb_member
values (
    'sinsa', '신사', '1234', null, 'F', sysdate
);
select * from tb_member;

-- 여러컬럼을 포함하는 unique제약조건 생성가능
create table abc (
    a varchar2(10) not null,
    b varchar2(20) not null,
    constraint uq_abc_ab unique(a, b)
);
-- drop table abc;
insert into 
    abc
values(
    '안녕', '잘가'    
);
insert into 
    abc
values(
    '안녕', '잘자'    
);
insert into 
    abc
values(
    '안녕', null    
); -- ORA-01400: NULL을 ("KH"."ABC"."B") 안에 삽입할 수 없습니다
select * from abc;

--@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- PRIMARY KEY 제약조건
--@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- 식별자컬럼. 다른 레코드와 구분할 수 있는 식별자. 테이블당 하나만 허용한다.
-- RDBMS에서 테이블간 참조를 위한 컬럼으로 주로 사용된다.

-- 제약조건 테스트용 테이블
-- drop table tb_member;
create table tb_member(
    id varchar2(20),
    name varchar2(50) not null,
    password varchar2(20) not null,
    email varchar2(50),
    gender char(1),
    reg_date date,
    constraint pk_tb_member_id primary key(id), -- 테이블 레벨 작성
    constraint uq_tb_member_email unique(email)
);
insert into tb_member values('honggd', '홍길동', '1234', null, null, sysdate);
insert into tb_member values('honggd', '황길동', '1234', null, null, sysdate); -- ORA-00001: 무결성 제약 조건(KH.PK_TB_MEMBER_ID)에 위배됩니다
insert into tb_member values(null, '황길동', '1234', null, null, sysdate); -- ORA-01400: NULL을 ("KH"."TB_MEMBER"."ID") 안에 삽입할 수 없습니다

-- 기본키도 복합키(여러컬럼)로 설정이 가능
create table tb_order_composite (
    product_id varchar2(20),
    user_id varchar2(20),
    cnt number default 1,
    order_date date default sysdate,
    constraint pk_tb_order_composite primary key(product_id, user_id, order_date)
);

insert into tb_order_composite values('samsung_1234', 'honggd', 5, default);
insert into tb_order_composite values('samsung_1234', null, 5, default); -- ORA-01400: NULL을 ("KH"."TB_ORDER_COMPOSITE"."USER_ID") 안에 삽입할 수 없습니다

select 
    product_id, user_id, cnt, to_char(order_date, 'yyyy-mm-dd hh24:mi:ss') order_date
from 
    tb_order_composite;

select
    constraint_name,
    uc.table_name,
    ucc.column_name,
    uc.constraint_type,
    uc.search_condition
from
    user_constraints uc join user_cons_columns ucc
        using(constraint_name)
where
    uc.table_name = 'TB_ORDER_COMPOSITE';
    
--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    
-- CHECK 제약조건
--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
--컬럼값의 범위를 한정하는 제약조건

-- drop table tb_member;
create table tb_member(
    id varchar2(20),
    name varchar2(50) not null,
    password varchar2(20) not null,
    email varchar2(50),
    gender char(1),
    point number,
    reg_date date,
    constraint pk_tb_member_id primary key(id), -- 테이블 레벨 작성
    constraint uq_tb_member_email unique(email),
    constraint ck_tb_member_gender check(gender in ('M', 'F')),
    constraint ck_tb_member_point check(point >= 0)
);
insert into tb_member values('honggd', '홍길동', '1234', null, 'M', -10, default); -- ORA-02290: 체크 제약조건(KH.CK_TB_MEMBER_POINT)이 위배되었습니다
insert into tb_member values('honggd', '홍길동', '1234', null, 'm', 100, default); -- ORA-02290: 체크 제약조건(KH.CK_TB_MEMBER_GENDER)이 위배되었습니다
insert into tb_member values('honggd', '홍길동', '1234', null, 'M', 100, default);
update tb_member set point = point - 1000 where id = 'honggd';

--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- FOREIGN KEY 제약조건
--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- 참조무결성을 유지하기 위한 제약조건
-- 부모테이블(참조하는 테이블)에서 제공하는 값만 사용할 수 있게 제한을 거는 것.
-- null은 기본적으로 허용한다.
-- 참조하는 부모테이블 컬럼은 pk 또는 uq 제약조건이 걸려있어야한다.
select
    constraint_name,
    uc.table_name,
    ucc.column_name,
    uc.constraint_type,
    uc.search_condition
from
    user_constraints uc join user_cons_columns ucc
        using(constraint_name)
where
    uc.table_name = 'EMPLOYEE';

--회원테이블
create table shop_member(
    member_id varchar2(20),
    member_name varchar2(30),
    constraint pk_shop_member_id primary key(member_id)
);
insert into shop_member values('honggd', '홍길동');
insert into shop_member values('sinsa', '신사임당');

select * from shop_member;

--구매테이블
-- drop table shop_buy
create table shop_buy (
    buy_no number,
    member_id varchar2(20),
    product_name varchar2(30),
    constraint pk_shop_buy_no primary key(buy_no),
    constraint fk_shop_buy_member_id foreign key(member_id) 
                                  references shop_member(member_id)
--                                  on delete set null
                                  on delete cascade
);

insert into shop_buy values (1, 'hongggggggggd', '축구화'); -- ORA-02291: 무결성 제약조건(KH.FK_SHOP_BUY_MEMBER_ID)이 위배되었습니다- 부모 키가 없습니다
insert into shop_buy values (1, 'honggd', '축구화'); 
insert into shop_buy values (2, 'sinsa', '볼링화'); 

select * from shop_buy;

-- 부모테이블의 데이터를 삭제하려면?
delete from shop_member where member_id = 'honggd'; -- ORA-02292: 무결성 제약조건(KH.FK_SHOP_BUY_MEMBER_ID)이 위배되었습니다- 자식 레코드가 발견되었습니다
delete from shop_member where member_id = 'sinsa';

-- 외래키 삭제옵션 
-- 부모테이블의 데이터를 삭제할 경우, 처리방식을 결정
-- 1. on delete restricted (기본값)
-- 2. on delete set null - 부모데이터 삭제시 자식fk컬럼값을 null로 처리
-- 3. on delete cascade - 부모데이터 삭제시 참조하는 자식 레코드를 따라서 삭제


-- 식별관계 / 비식별관계
-- fk컬럼을 다시 pk로 사용하면 식별관계, pk로 사용하지 않으면 비식별관계
-- 식별관계인 경우 0,1개행이 존재할 수 있다.
-- 비식별관계인 경우 0 ~ n개행이 존재할 수 있다.

-- 부모
create table tb_person(
    id varchar2(20) primary key
);

insert into tb_person values('sinsa');
select * from tb_person;

-- 자식
create table tb_person_address (
    person_id varchar2(20),
    addr varchar2(500),
    constraint fk_tb_person_address foreign key (person_id) references tb_person(id),
    constraint pk_tb_person_address primary key(person_id)
);
insert into tb_person_address values('sinsa', '서울시 강남구 역삼동');
insert into tb_person_address values('sinsa', '서울시 강동구 신내동'); -- ORA-00001: 무결성 제약 조건(KH.PK_TB_PERSON_ADDRESS)에 위배됩니다
select * from tb_person_address;


---------------------------------------------------------------------------------------------
--  ALTER
---------------------------------------------------------------------------------------------
-- 객체를 수정하는 명령어
-- table에 대해서 수정가능한 것은 컬럼/제약조건.
-- table의 이름수정도 가능.

-- 서브명령어
/*
    add   컬럼/제약조건 추가
    modify  컬럼수정(자료형/default값, not null여부)
    rename 컬럼/제약조건 이름변경
    drop    컬럼/제약조건 삭제

*/

create table tb_user (
    no number primary key,
    id varchar2(20),
    pw varchar2(20)
);

describe tb_user;
desc tb_user;

--+++++++++++++++++++++++++++++++++++++++++++
-- ADD
--+++++++++++++++++++++++++++++++++++++++++++
--컬럼/제약조건을 추가
alter table 
    tb_user
add
    name varchar2(50);
    
alter table
    tb_user
add
    age number default 0;

desc tb_user;

alter table 
    tb_user
add
    constraint uq_tb_user_id unique(id);

select
    constraint_name,
    uc.table_name,
    ucc.column_name,
    uc.constraint_type,
    uc.search_condition
from
    user_constraints uc join user_cons_columns ucc
        using(constraint_name)
where
    uc.table_name = 'TB_USER';

--++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- MODIFY
--++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- 컬럼에 대해서만 수정이 가능.
-- not null은 수정이 가능
-- null 작성시 not null 해제

alter table
    tb_user
modify name varchar2(100) null;

desc tb_user;

-- 데이터가 있는 상황에서 자료형의 크기를 기존값크기보다 적게 수정할 수 없다.
insert into tb_user values (1, 'honggd', '1234', '홍길동길동길동길동', 33);
commit;
alter table
    tb_user
modify name varchar2(20); -- 01441. 00000 -  "cannot decrease column length because some value is too big"


--+++++++++++++++++++++++++++++++++++++++++++++++++++
-- RENAME
--+++++++++++++++++++++++++++++++++++++++++++++++++++
-- 컬럼명 / 제약조건명 수정
alter table
    tb_user
rename 
    column pw to password;
desc tb_user;

alter table 
    tb_user
rename 
    constraint SYS_C007606 to pk_tb_user_no;

-- gender컬럼 및 check제약조건 추가
alter table 
    tb_user
add gender char(1)
add constraint ck_tb_user_gender check(gender in ('M', 'F'));


--+++++++++++++++++++++++++++++++++++++
-- DROP
--+++++++++++++++++++++++++++++++++++++
-- 컬럼 삭제, 제약조건 삭제
-- not null 제약조건 modify에서 수정(null 지정)
alter table
    tb_user
drop column age;

alter table
    tb_user
drop constraint ck_tb_user_gender;


--테이블 이름 변경
alter table 
    tb_user
rename to 
    tb_user_after;
-- 간단버젼
rename tb_user_after to tb_user_before;
    
select * from tb_user_before;

--------------------------------------------------------------------------------
-- DROP
--------------------------------------------------------------------------------
-- 객체 삭제
-- sql작성시에도 항상 주석해둘것!
-- drop table tb_user_before;


-- 자식테이블이 참조하는 부모테이블을 삭제
select * from shop_member;
select * from shop_buy;
-- truncate table shop_member;
-- delete from shop_member where member_id = '1';
insert into shop_member values ('honggd', '홍길동');
insert into shop_buy values (123, 'honggd', '농구화');
insert into shop_buy values (456, 'honggd', '농구화');

--drop table shop_member; -- ORA-02449: 외래 키에 의해 참조되는 고유/기본 키가 테이블에 있습니다
--drop table shop_member cascade constraint;


select
    constraint_name,
    uc.table_name,
    ucc.column_name,
    uc.constraint_type,
    uc.search_condition
from
    user_constraints uc join user_cons_columns ucc
        using(constraint_name)
where
    uc.table_name = 'SHOP_BUY';


--=========================================================
-- DCL
--=========================================================
-- Data Control Languagel 데이터 제어어
-- 권한 할당, 권한 회수등에 사용하는 명령어
-- TCL을 포함하는 상위개념

-------------------------------------------------------------------------------------------
-- GRANT
-------------------------------------------------------------------------------------------
-- 권한을 사용자나 롤(권한 묶음)에 부여하는 명령어
-- grant (특정권한 | 롤) to (사용자 | 롤) [with admin option]
-- with admin option은 부여받은 권한을 다시 부여할 수 있는 옵션

--------------------------------------------------------------------------------------------
-- (관리자계정) qwerty 생성 -> 접속 시도 (실패) create session권한 부재
-- create session | connect 을 qwerty에게 부여 -> 접속 시도(성공)
alter session set "_oracle_script" = true;
create user qwerty identified by qwerty default tablespace users;
grant create session to qwerty;
grant connect to qwerty;
alter user qwerty quota unlimited on users;
grant resource to qwerty; -- 객체 생성권한 

-- 롤이 가지고 있는 권한 조회
select 
    *
from 
    dba_sys_privs
where
    grantee in ('CONNECT', 'RESOURCE');
/*
CONNECT	    CREATE SESSION
CONNECT	    SET CONTAINER

RESOURCE	CREATE TRIGGER
RESOURCE	CREATE CLUSTER
RESOURCE	CREATE TABLE
RESOURCE	CREATE INDEXTYPE
RESOURCE	CREATE OPERATOR
RESOURCE	CREATE TYPE
RESOURCE	CREATE PROCEDURE
RESOURCE	CREATE SEQUENCE

*/
--------------------------------------------------------------------------------------------

-- 특정테이블에 대한 권한 부여
create table tb_coffee (
    name varchar2(50),
    price number not null,
    company varchar2(50) not null,
    constraint pk_tb_coffee_name primary key(name)
);
insert into kh.tb_coffee values ('맥심', 3000, '동서식품');
insert into kh.tb_coffee values ('카누', 5000, '동서식품');
insert into kh.tb_coffee values ('네스카페', 4000, '네슬레');

commit;

-- kh(소유주)가 qwerty에게 tb_coffee 조회권한 부여
grant select on kh.tb_coffee to qwerty;
grant insert, update, delete on tb_coffee to qwerty;

select * from tb_coffee;

---------------------------------------------------------------------------------
-- REVOKE
---------------------------------------------------------------------------------
-- 권한을 회수하는 명령어
-- revoke (권한 | 롤) from (사용자 | 롤)

revoke insert, update, delete on tb_coffee from qwerty;

revoke select on tb_coffee from qwerty;


--===================================================
-- TCL
--===================================================
-- Traction Control Language 트랜잭션 제어어
-- commit / rollback / savepoint

-- Transaction 
-- 한번에 처리되어야 할 최소의 작업단위.
-- 하위 작업들은 모두 성공, 모두 실패되어야 한다.

-- 계좌이체 : a ---------> b  50000원 전송
-- 1. a계좌에서 50000원 차감.
-- 2. b계좌에서 50000원 증액.

-- 1,2번 update실행후 모두 성공시 commit, 하나라도 실패하면 rollback!


--=======================================================
-- DATABASE OBJECT 1
--=======================================================

----------------------------------------------------------------------------------------
-- DATA DICTIONARY
----------------------------------------------------------------------------------------
-- db를 효율적으로 관리하기 위해 다양한 객체의 정보를 가지고 있는 시스템 테이블
-- 모두 read-only이므로, 추가/수정/삭제 작업은 일절 없다.
-- 객체정보가 변경되면, 자동으로 DD에 반영된다.

-- DD의 종류
-- 1. 일반사용자용 user_xxxs
-- 2. 일반사용자용(부여받은객체포함) all_xxxs
-- 3. 관리자용 dba_xxxxs

-- DD조회
select * from dictionary;
select * from dict;

--++++++++++++++++++++++++++++++++++++++
-- USER_XXX
--++++++++++++++++++++++++++++++++++++++

select * from user_tables; -- 내가 소유한 table조회
select * from user_sys_privs; -- 내가 가진 권한조회
select * from user_role_privs; -- 내가 가진 롤조회
select * from role_sys_privs; -- 내가 가진 롤에 포함된 권한 조회

select * from user_constraints;   -- 제약조건 
select * from user_sequences;   -- 시퀀스
select * from user_indexes;     -- 인덱스
select * from user_views;     -- 뷰


--++++++++++++++++++++++++++++++++++++++
-- ALL_XXX
--++++++++++++++++++++++++++++++++++++++
--소유하거나 사용권한을 부여받은 모든 객체  조회

select * from all_tables;
select * from all_tab_comments; -- 테이블 코멘트


--++++++++++++++++++++++++++++++++++++++
-- DBA_XXX
--++++++++++++++++++++++++++++++++++++++
-- 관리자만 접근이 가능한 객체 정보 조회
-- 관리자는 모든 테이블, 모든 사용자에 대한 조회가 가능하다.
-- (관리자계정)
select * from dba_tables where owner = 'KH';

select * from kh.employee; 

select * from dba_sys_privs where grantee = 'KH';
select * from dba_role_privs where grantee = 'KH';
select * from dba_tab_privs where owner = 'KH'; -- 테이블에 대한 권한 관리


-----------------------------------------------------------------------
-- STORED VIEW
-----------------------------------------------------------------------
-- 하나이상의 테이블로부터 원하는 데이터를 선별적으로 제공하는 가상테이블
-- 실제데이터를 소유하지 않고, 실제테이블에 대한 통로역할.
-- 이 객체에는 inlinew view쿼리를 가지고 있다 쿼리 실행시 해당 inline view를 처리함.

select * from user_views;
-- (관리자계정) create view권한을 kh에 부여
grant create view to kh;

-- create view권한은 resoure롤에 포함되지 않으므로 새로 부여해야 함.
-- or replace 없으면 생성, 있으면 갱신!
create or replace view view_emp
as
select emp_id, emp_name, email, phone from employee;

select * from view_emp;
select * from (select emp_id, emp_name, email, phone from employee);

-- 1.타사용자에게 선별적으로 데이터 제공
-- qwerty에게 view_emp조회권한 부여
grant select on view_emp to qwerty;

-- 2. 가상컬럼, relation에 대한 조회를 손쉽게 할 수 있다.
-- 사번, 사원명, 성별, 나이, 직급명, 부서명 조회
create or replace view view_emp_read
as
select 
    emp_id,
    emp_name,
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender,
    extract(year from sysdate) - (decode(substr(emp_no, 8, 1), '1', 1900, '2', 1900, 2000) + substr(emp_no, 1, 2)) + 1 age,
    (select job_name from job where job_code = E.job_code) job_name,
    (select dept_title from department where dept_id = e.dept_code) dept_title
from
    employee e;

select * from view_emp_read where gender = '여';


-------------------------------------------------------------------------------
-- SEQUENCE
-------------------------------------------------------------------------------
-- 정수값을 순차적으로 발행하는 객체(채번기)
-- pk값을 정수로 관리하는 경우 sequence발급한 번호를 사용한다.

/*
    create sequence 시퀀스명
    [start with 시작값]             -- 1
    [increment by 증감값]          -- 1
    [maxvalue 최대값 | nomaxvalue]  -- nomaxvalue
    [minvalue 최소값 | nominvalue]   -- nominvalue
    [cycle | nocycle]               -- nocycle (최대/최소값 도달시 순환여부)
    [cache 수량 | nocache]          -- 20

*/
create table tb_advice(
    no number primary key, 
    user_id varchar2(20) not null,
    reg_date date default sysdate
);
create sequence seq_tb_advice_no;

insert into tb_advice(no, user_id) values(seq_tb_advice_no.nextval, 'honggd');
insert into tb_advice(no, user_id) values(seq_tb_advice_no.nextval, 'sinsa');
insert into tb_advice(no, user_id) values(seq_tb_advice_no.nextval, 'sejong');

select * from tb_advice;

--현재 sequence상태 조회 (마지막 발급번호)
select seq_tb_advice_no.currval from dual;

-- cacheing된 숫자가 건너뛸수 있다. pk는 고유하기만 하면 크게 문제가 되지 않는다.
-- 연속된 숫자로 관리를 원한다면 sequence사용시 nocache옵션으로 할것.
select * from user_sequences;

-- 시퀀스 객체의 start with값은 수정이 불가하다. 
-- 시퀀스 삭제후 다시 생성할 것!
-- increment by옵션은 수정 가능하다.
alter sequence seq_tb_advice_no increment by 10;

--복합문자열을 pk로 처리하세요.
--주문테이블 kh_order
create table kh_order (
    no varchar2(30), -- kh-220324-0001
    user_id varchar2(20) not null,
    prod_id varchar2(20) not null,
    cnt number default 1,
    order_date date default sysdate,
    constraint pk_kh_order_no primary key(no)
);
--적절한 sequence를 생성하고, 위와 같은 pk를 생성할수 있도록 insert문을 작성하세요.
create sequence seq_kh_order_no;

select 'kh-' || to_char(sysdate, 'yymmdd') || '-' || lpad(1, 4, '0') from dual;

insert into kh_order 
values(
    'kh-' || to_char(sysdate, 'yymmdd') || '-' || lpad(seq_kh_order_no.nextval, 4, '0'),
    'honggd',
    'ss_1234',
    10,
    default
);
select * from kh_order;


-------------------------------------------------------------------------------
-- INDEX
-------------------------------------------------------------------------------
-- 색인. sql명령의 처리속도 향상을 위해 table의 컬럼에 대해 생성하는 색인 객체.
-- key-value형태로 저장. key(컬럼값), value(행이 저장된 주소값)을 보관.

-- 장점
-- 1. 검색속도가 빨라지고, 시스템부하가 줄어든다.
-- 2. 전체적인 성능향상

-- 단점
-- 1. 인덱스를 위한 추가 저장공간이 필요.
-- 2. 인덱스 생성/수정/삭제시 별도의 작업시간이 소요.
-- 3. 데이터 생성/수정/삭제가 빈번하다면 인덱스로 인해 성능저하도 야기됨.

-- 어떤 컬럼에 대해 인덱스를 만들어야 하는가.
-- 1. 선택도(selectivity)가 좋은 컬럼을 인덱스로 만든다.
-- 선택도란? 고유한 값을 많이 가지는 것.
-- employee.emp_id 중복X -> 선택도가 아주 좋다.
-- employee.emp_no 중복X -> 선택도가 아주 좋다.
-- employee.emp_name 중복O -> 선택도가 좋다.
-- employee.dept_code 중복O -> 선택도 보통
-- employee.gender -> 선택도 나쁨

-- 2. where절에 자주 사용되는 컬럼을 선택
-- 3. join조건컬럼을 선택
-- 4. 한번 입력된 데이터가 변경이 자주 없는 컬럼

-- 중복값 많은 컬럼, null값이 많은 컬럼은 되도록 지양할것.

-- index조회
-- pk, uq제약조건이 걸린 컬럼은 자동으로 인덱스를 생성한다.
select * from user_indexes;
select * from user_ind_columns;

-- 인덱스조회
-- pk,uq로 생성된 인덱스는 제약조건명과 인덱스명이 같다. 제약조건명을 잘 지으면(테이블명, 컬럼명 포함) 인덱스정보 확인시 유용하다.
select * from user_constraints where constraint_type in ('P', 'U');
select 
    * 
from
    user_indexes ui join user_ind_columns uic
        using(index_name);


-- 실행계획(F10) 확인
select * from employee where job_code = 'J1'; -- job_code 인덱스 없음
select * from employee where emp_id = '203'; -- emp_id 인덱스 있음.
select * from employee where dept_code = 'D5';
select * from employee where emp_no = '070910-4653546';

select * from employee where emp_name = '송종기'; 

-- 인덱스 생성
create index idx_employee_emp_name on employee(emp_name);

-- 인덱스 사용시 주의사항
-- 인덱스 사용여부는 optimizer(최적화처리기)가 결정을 하지만, 다음 경우는 index를 사용하지 않는다.
-- 1. 인덱스 컬럼을 변형해 조회하는 경우 where substr(emp_no, 8, 1) = '1'
-- 2. null비교하는 경우 where emp_name is null
-- 3. not비교하는 경우 where emp_name != '송종기'
-- 4. 인덱스컬럼과 자료형이 다른 경우 where emp_id = 201

select * from employee where substr(emp_no, 8, 1) = '1';
select * from employee where emp_id = 201;

-- index 삭제
-- drop index 인덱스명

-- pk, uq제약조건으로 생성된 인덱스는 직접 삭제가 불가하다. 제약조건을 제거하면, 자동으로 삭제처리된다.



--====================================================
-- PL/SQL
--====================================================
-- Procedural Language extension to SQL
-- 기존 sql에 절차적 언어 방식을 추가해서 변수선언, 조건처리, 반복처리 지원하는 문법

-- pl/sql 유형
-- 1. 익명블럭 Anonymous Block 매번실행. 
-- 2. 프로시져 - pl/sql구문을 저장해서 호출해서 재사용 가능
-- 3. 함수 - pl/sql구문을 저장해서 호출해서 재사용 가능(반드시 하나의 리턴값을 가진다.)


-- 익명블럭 구조
/*
    declare
        -- 변수선언부(선택)
    begin
        -- 실행부(필수)
    
    exception
        -- 예외처리부(선택)
    end;
    /
sql문은 ;(세미콜론)으로 각sql문을 구분했으나, pl/sql에서는 블럭안에 여러개의 sql문이 올수있다.
/가 익명블럭의 종료를 의미함.

*/

--서버콘솔 출력
-- 기본값이 off이므로 매번 session생성시마다 1회 실행할 것!
set serveroutput ON;

begin
    dbms_output.put_line('hello world');
end;
/


declare
    v_emp_id char(3); -- 변수 선언
begin
    select 
        emp_id
    into
        v_emp_id
    from 
        employee
    where 
        emp_name = '정동일';
        
    dbms_output.put_line('emp_id : ' || v_emp_id);    
exception 
    when no_data_found then dbms_output.put_line('찾으시는 사원이 없습니다.');
end;
/

------------------------------------------------------------------
-- 익명블럭
------------------------------------------------------------------
-- declare 변수
-- 변수명 [constant] 자료형 [not null] [:= 값];

declare
    name varchar2(100);
    num number := 10 * 20;
    KKK constant number := 333;
begin
    name := '김사랑';
--    KKK := KKK * 100; -- ORA-06550: 줄 7, 열5:PLS-00363: 'KKK' 식은 피할당자로 사용될 수 없습니다.
    
    dbms_output.put_line(name);
    dbms_output.put_line(num);
    dbms_output.put_line(KKK);
end;
/

-- 변수의 자료형
-- 1.기본자료형
    -- varchar2, char, clob
    -- number, binary_integer, pls_integer
    -- date
    -- boolean (true, false, null)
-- 2.복합자료형
    -- record
    -- cursor
    -- collection(varray(배열), nested_table(List), associative array(Map))

-- 변수유형
-- 1. 스칼라변수(값)
-- 2. 참조변수(테이블.컬럼타입)

-- 참조변수1 %type
-- 변수자료형을 직접 선언하지않고, (다른 테이블).(특정 컬럼)을 참조.

-- 부서명도 함께 출력
declare
    v_emp_name employee.emp_name%type;
    v_phone employee.phone%type;
    v_dept_title department.dept_title%type;
begin
    select
        emp_name, phone, d.dept_title
    into 
        v_emp_name, v_phone, v_dept_title
    from
        employee e left join department d
            on e.dept_code = d.dept_id
    where
        emp_id = '&사번'; -- 사번 임시변수에 사용자입력값 받아서 조회
    
    
    dbms_output.put_line('이름 : '|| v_emp_name);
    dbms_output.put_line('전화번호 : '|| v_phone);
    dbms_output.put_line('부서명 : '|| v_dept_title);
end;
/

desc employee;


-- 참조변수2. %rowtype
-- 테이블의 모든 컬럼을 참조하는 타입

declare
    emp_row employee%rowtype;
begin
    select
        *
    into
        emp_row
    from
        employee
    where
        emp_id = '&사번';

    dbms_output.put_line('사원명 : ' || emp_row.emp_name);
    dbms_output.put_line('이메일 : ' || emp_row.email);

end;
/

-- 참조변수3. record
-- 원하는 컬럼만 가지고 있는 record자료형을 만들고, 사용한다.

declare
    type my_emp_type is record(
        emp_name employee.emp_name%type,
        dept_title department.dept_title%type
    );

    erow my_emp_type;
begin
    select
        e.emp_name, d.dept_title
    into
        erow
    from
        employee e left join department d
            on e.dept_code = d.dept_id
    where
        emp_id = '&사번';
        
    dbms_output.put_line('이름 : ' || erow.emp_name);
    dbms_output.put_line('부서 : ' || erow.dept_title);

end;
/

-- 사원명, 직급명을 처리할 수 있는 레코드를 선언하고 사번을 통해 조회하세요.


declare
    type emp_type is record(
        emp_name employee.emp_name%type,
        job_name job.job_name%type
    );
    erow emp_type;
begin
    select
        emp_name,
        (select job_name from job where job_code = e.job_code) job_name
    into 
        erow
    from 
        employee e
    where
        emp_id = '&사번';


    dbms_output.put_line('사원명 : ' || erow.emp_name);
    dbms_output.put_line('직급 : ' || erow.job_name);

end;
/

-- begin절에서 DML 사용하기
-- 익명블럭안에서 트랜잭션 처리까지 완료해야 한다.
select * from tb_member;
desc tb_member;

begin
    insert into 
        tb_member
    values (
        'sinsa', '신사임당', '1234', 'sinsa@gmail.com', 'F', 1000, default
    );
    -- 트랜잭션처리까지 완료하기
    commit;
end;
/

-- ex_employee의 마지막 번호를 조회한후, +1 사번을 부여해서 다음 정보를 insert하세요.
-- 김테리 880808-2345678 taeri@gmail.com 01012341234 null J4 S3 3500000 null 200 오늘 null N
select * from ex_employee;

declare
    v_emp_id employee.emp_id%type;
begin
    -- 1. 마지막 번호 조회
    select 
        max(emp_id)
    into
        v_emp_id
    from
        ex_employee;
            
--    dbms_output.put_line(v_emp_id);
    
    -- 2. insert
    insert into
        ex_employee
    values (
        v_emp_id + 1, '김테리', '880808-2345678', 'taeri@gmail.com', '01012341234', null, 'J4', 'S3', '3500000' ,null, '200', sysdate, null, 'N'
    );
    
    -- 2.1 트랜잭션처리
    commit;
end;
/

------------------------------------------------------------------------
-- 조건문
------------------------------------------------------------------------
-- if, else if, if else
/*
    if 조건식 then
        실행구문
    end if;
    
    if 조건식 then
        참일때 실행코드
    else
        거짓일때 실행코드
    end if;
    
    if 조건식1 then
        실행코드1
    elsif 조건식2 then
        실행코드2
    elsif 조건식3 then
        실행코드3
    ...
    
    end if;
    
*/
declare
    n number := &숫자;
begin
    dbms_output.put_line(n);
    
--    if mod(n, 2) = 0 then
--        dbms_output.put_line('짝수를 입력하셨습니다.');
--    else 
--        dbms_output.put_line('홀수를 입력하셨습니다.');
--    end if;
    
    if n > 0 then
        dbms_output.put_line('양수입니다.');
    elsif n = 0 then
        dbms_output.put_line('0입니다.');
    else
        dbms_output.put_line('음수입니다.');
    end if;
    
    
    dbms_output.put_line('끝');
end;
/

-- case문 
/*
문법1
    case 표현식
        when 값1 then
            실행코드1;
        when 값2 then
            실행코드2;
        ...
        else
            기본실행코드;
    end case;

문법2

    case
        when 조건식1 then 실행코드1;
        when 조건식2 then 실행코드2;
        when 조건식3 then 실행코드3;
        else 기본실행코드;
    end case;

*/
-- 가위(1) 바위(2) 보(3)
set serveroutput on;
declare
    n number := &가위바위보123;
begin
    case (n)
        when 1 then dbms_output.put_line('가위');
        when 2 then dbms_output.put_line('바위');
        when 3 then dbms_output.put_line('보');
        else dbms_output.put_line('잘못 입력하셨습니다.');
    end case;
end;
/

declare
    com number := trunc(dbms_random.value(1, 4)); -- 1.0보다 크거나 같고, 4.0보다 작은 실수 반환
    n number := &가위바위보123;
begin
    dbms_output.put_line(n || ' ' || com);
    case 
        when n = 1 then dbms_output.put_line('가위를 냈습니다.');
        when n = 2 then dbms_output.put_line('바위를 냈습니다.');
        when n = 3 then dbms_output.put_line('보를 냈습니다.');
        else dbms_output.put_line('잘못 입력하셨습니다.'); return;
    end case;
    
    -- 결과평가 
    case
        when com = n then dbms_output.put_line('> 비겼습니다.');
        when ((n = 1 and com = 3) or (n = 2 and com = 1) or (n = 3 and com = 2)) then dbms_output.put_line('> 당신이 이겼습니다.');
        else dbms_output.put_line('> 당신이 졌습니다.');
    end case;
    
end;
/

-- 사번을 입력받고, 관리자에 대한 성과급을 지급하려한다.
-- 관리하는 사원이 5명이상은 급여의 15% 지급 : '성과급은 ??원입니다.'
-- 관리하는 사원이 5명미만은 급여의 10% 지급 : '성과급은 ??원입니다.'
-- 관리하는 사원이 없는 경우는 '대상자가 아닙니다.'

declare
    salary employee.salary%type;
    num number; -- 부하직원수
begin
    -- 1. 사번을 가지고 부하직원수, 급여 조회
    select
        (select count(*) from employee where manager_id = e.emp_id), 
        salary
    into
        num, salary
    from
        employee e
    where
        emp_id = '&사번';
        
    dbms_output.put_line(num || ', ' || salary);

    -- 2. 성과금 평가
    if num >= 5 then
        dbms_output.put_line('성과금 : ' || salary * 0.15 || '원');
    elsif num > 0 then
        dbms_output.put_line('성과금 : ' || salary * 0.1 || '원');
    else
        dbms_output.put_line('성과금 대상자가 아닙니다');
    end if;
end;
/

------------------------------------------------------------------------
-- 반복문
------------------------------------------------------------------------
-- loop, while loop, for in loop

-- loop 무한반복 + 탈출문 exit
declare
    n number := 1;
begin

    loop
        dbms_output.put_line(n);
        n := n + 1;
        
        -- 탈출조건(필수)
        exit when n > 5;
        
    end loop;

end;
/


-- while (조건식) loop

declare
    n number := 1;
begin
    while n <= 5 loop
        dbms_output.put_line(n);
        n := n + 1;
    end loop;
end;
/

-- for loop : 증감변수 별도 선언 불필요
-- 증감변수 범위만큼 반복후 자동 종료
-- for 증감변수 in 시작값..종료값
-- 증감처리는 +1. 변경불가. reverse는 사용가능
declare 
    dan number := &구구단수;
begin
    for n in 1..9 loop
        dbms_output.put_line(dan || ' * ' || n || ' = ' || (dan * n));
    end loop;
end;
/

-- 2단~9단까지 출력
begin
    for dan in 2..9 loop
        for n in 1..9 loop
            dbms_output.put_line(dan || ' * ' || n || ' = ' || (dan * n));
        end loop;
        dbms_output.new_line;
    end loop;
end;
/

/*

dbms_output.new_line; -- 개행만 하고 싶은 경우


2 * 1 = 2
2 * 2 = 4
...

3 * 1 = 3
3 * 2 = 6
...

9 * 1 = 9
..
9 * 9 = 81
*/

-- 사원정보 출력
-- select문의 조회결과가 1행씩 처리
declare
    erow employee%rowtype;
begin
    for n in 200..223 loop
--        dbms_output.put_line(n);
        select 
            *
        into 
            erow
        from
            employee
        where
            emp_id = n;
        
        dbms_output.put_line(erow.emp_id || '   ' || erow.emp_name || '     ' || erow.email);
    end loop;
end;
/

--=======================================================
-- DATABASE OBJECT2
--=======================================================
-- pl/sql문법을 사용하는 db객체 (function, procedure, cursor, trigger, ....)

----------------------------------------------------------------------------------------
-- STORED FUNCTION
----------------------------------------------------------------------------------------
-- 리턴값이 반드시 존재하는 프로시져 객체
-- 함수객체는 일반sql문, 다른 프로시져, 익명블럭에서 호출이 가능
-- 기존 sql을 실행과 달리, 미리 컴파일하므로 실행속도가 빠르다.

/*

    create [or replace] function 함수명(매개변수1, 매개변수2, ...)
    return 자료형
    is
        -- 지역변수선언
    begin
        -- 실행코드
        return 리턴값;
    exception
        -- 예외처리
        return 예외발생시 리턴값;
    end;
    /
    
*/

-- 양모자씌우기 
-- 매개변수, 리터타입도 자료크기는 지정하지 않는다.
-- pl/sql의 varchar2(32676byte)가 최대이다.
create or replace function myfunc(p_emp_name employee.emp_name%type)
return varchar2
is
    result varchar2(32676);
begin
    result := 'd' || p_emp_name || 'b';
    dbms_output.put_line(result || '@myfunc');
    return result;
end;
/

-- 익명블럭에서 호출
begin
    dbms_output.put_line(myfunc('&이름'));
end;
/

-- 일반 sql문에서 호출
-- 함수내부의 로그출력없음.
select
    myfunc(emp_name)
from 
    employee;

-- data dictionary에서 확인
-- user_procedures에서 object_type='FUNCTION'
select 
    *
from
    user_procedures
where
    object_type = 'FUNCTION';
   
--주민번호를 인자로 성별을 리턴하는 저장함수 fn_get_gender 
create or replace function fn_get_gender(
    p_emp_no employee.emp_no%type
)
return char
is
    v_gender char(3);
begin
    case substr(p_emp_no, 8, 1)
        when '1' then v_gender := '남';
        when '3' then v_gender := '남';
        else v_gender := '여';
    end case;
    return v_gender;
end;
/

select
    emp_name, 
    emp_no,
    fn_get_gender(emp_no) gender
from
    employee;

-- 주민번호를 인자로 받아서 한국나이를 리턴하는 fn_get_age
-- 현재년도 - 출생년도 + 1
create or replace function fn_get_age(
    p_emp_no employee.emp_no%type
)
return number
is
    sys_year number := extract(year from sysdate);
    birth_year number;
begin
    -- 출생년도
    case 
        when substr(p_emp_no, 8, 1) in ('1', '2')
            then birth_year := 1900 + substr(p_emp_no, 1, 2);
        else 
            birth_year := 2000 + substr(p_emp_no, 1, 2);
    end case;
    -- 나이 리턴
    return sys_year - birth_year + 1;
end;
/

select
    emp_name,
    fn_get_age(emp_no) age
from
    employee;


---------------------------------------------------------------------------------
-- STORED PROCEDURE
---------------------------------------------------------------------------------
-- 일련의 작업절차를 객체로 저장하고 호출해서 사용하는 객체
-- 리턴값이 없다. out매개변수를 이용해서 호출부로 값전달 가능
-- 미리 컴파일해두므로 일반 sql대비 처리효율이 좋다.
-- select문에서 호출불가. 익명블럭 또는 다른 프로시져에서 호출가능.

/*
    create [or replace] procedure 프로시져명(매개변수1 mode 자료형, 매개변수2 mode 자료형, ...)
    is
        -- 지역변수 선언
    begin
        -- 실행코드
    end;
    /
    
    mode : in | out | inout
        - in 프로시져 값을 전달(기본값)
        - out 프로시져의 처리내용을 담아서 호출부로 전달
        - inout

*/

create or replace procedure proc_get_emp(
    p_emp_id in employee.emp_id%type,
    p_emp_name out employee.emp_name%type,
    p_phone out employee.phone%type
)
is   
begin
    -- 해당사원조회 
    select 
        emp_name, phone
    into 
        p_emp_name, p_phone
    from
        employee
    where
        emp_id = p_emp_id;
        
    dbms_output.put_line('사원명@proc_get_emp : ' || p_emp_name);
    dbms_output.put_line('전화번호@proc_get_emp : ' || p_phone);
end;
/

-- 익명블럭에서 호출
declare
    v_emp_id employee.emp_id%type := '&사번';
    v_emp_name employee.emp_name%type;
    v_phone employee.phone%type;
begin
    -- 프로시져호출
    proc_get_emp(v_emp_id, v_emp_name, v_phone);
    
    -- 값 확인
    dbms_output.put_line('사원명 : ' || v_emp_name);
    dbms_output.put_line('전화번호 : ' || v_phone);
    
end;
/

-- 사원 삭제 프로시져
-- DML처리시에는 트랜잭션처리까지 함께 할것!

create or replace procedure proc_del_emp(
    p_emp_id ex_employee.emp_id%type -- in mode(기본값)
)
is
begin
    -- 삭제
    delete from
        ex_employee
    where
        emp_id = p_emp_id;
    -- 트랜잭션처리
    commit;
    -- 콘솔로깅
    dbms_output.put_line(p_emp_id || '번 사원을 삭제했습니다.');        
end;
/

select * from ex_employee;

begin
    proc_del_emp('&사번');
end;
/

-- upsert 예제
-- 특정행이 없으면 insert 
-- 특정행이 존재하면 update

-- ex_job테이블
create table ex_job
as
select * from job;

-- 기본키, 자료형 수정
alter table 
    ex_job
add constraint pk_ex_job_code primary key(job_code)
modify job_code varchar2(5)
modify job_name not null;



-- 인자로 전달한 직급코드, 직급명에 따라 insert 또는 update처리를 하는 프로시져 
create or replace procedure proc_upsert_ex_job(
    p_job_code ex_job.job_code%type,
    p_job_name ex_job.job_name%type
)
is
    v_cnt number;
begin
    -- 1.p_job_code가 존재하는지 여부
    select
        count(*)
    into
        v_cnt
    from
        ex_job
    where
        job_code = p_job_code;

    -- 2.존재하면 update, 존재하지 않으면 insert
    if v_cnt = 0 then
        insert into
            ex_job
        values (
            p_job_code, p_job_name
        );
    else 
        update
            ex_job
        set
            job_name = p_job_name
        where
            job_code = p_job_code;
    end if;
    
    -- 3.트랜잭션처리
    commit;
end;
/

begin
--    proc_upsert_ex_job('J8', '인턴'); -- insert
    proc_upsert_ex_job('J8', '수습'); -- update
end;
/

select * from ex_job;

-- dd에서 조회
select * from user_procedures where object_type = 'PROCEDURE';


--------------------------------------------------------------------------------
-- CURSOR
--------------------------------------------------------------------------------
-- 커서란 sql실행결과를 가지고 있는 메모리영역(private sql)에 대한 포인터객체.
-- 한행이상의 결과집합인 경우도 순차적으로 접근이 가능

-- 1. 암시적 커서 
-- 2. 명시적 커서

-- open ~ fetch ~ close의 단계를 거쳐 처리하게 된다.

-- 커서 속성
-- %rowcount : 최근 실행된 sql문의 결과행수
-- %notfound : 결과집합에서 fetch된 행이 존재하면 false, 존재하지 않으면 true
-- %found   : 결과집합에서 fetch된 행이 존재하면 true, 존재하지 않으면 false
-- %isopen  : 최근 실행된 sql문 커서가 open상태이면 true

-- 암시적 커서 확인

declare
    v_emp_id employee.emp_id%type := '&사번';
    v_emp_name employee.emp_name%type;
begin
    select 
        emp_name
    into 
        v_emp_name
    from
        employee
    where
        emp_id = v_emp_id;
    
    if sql%found then
        dbms_output.put_line('조회된 행수 : ' || sql%rowcount);
    end if;

end;
/

-- 명시적 커서
-- 선언 - open - fetch - close
-- 직접 결과집합에 접근해서 행에 대한 처리가 가능한다.
-- for..in문 안에서는 open/close를 자동처리해줘서 간단히 커서를 사용할 수 있다.

declare
    -- 커서선언
    cursor mycursor
    is
    select * from employee where 1 = 0;
    
    erow employee%rowtype;
begin
    -- 커서 open
    open mycursor;

    loop
        -- 커서 fetch
        fetch mycursor into erow; -- 한행씩 가져오기
        exit when mycursor%notfound; -- 더이상 가져올 행이 없는 경우, exit
        
        dbms_output.put_line('사번 ' || erow.emp_id || '  사원명 : ' || erow.emp_name );
    end loop;
    
    -- 커서 close
    close mycursor;
end;
/


-- for..in문에서 명시적 커서 사용하기
-- open, fetch, close 대신 처리
-- fetch된 행을 담을 변수도 for..in안에서 선언
-- 별도의 exit를 작성 불필요
declare
    -- 커서선언
    cursor mycursor
    is
    select * from employee;
begin
    -- open, fetch, close 자동처리
    for erow in mycursor loop
        dbms_output.put_line('사번 ' || erow.emp_id || '  사원명 : ' || erow.emp_name );
    end loop;
end;
/

-- 매개변수가 있는 커서
-- 커서선언시에 매개변수도 함께 선언, open할때 매개인자를 전달

declare
    cursor cs_emp_by_dept(p_dept_code employee.dept_code%type)
    is
    select * from employee where dept_code = p_dept_code;
    
    v_dept_code employee.dept_code%type := '&부서코드';
    erow employee%rowtype;
begin

    dbms_output.put_line('사번    사원명     부서코드');
    dbms_output.put_line('=========================');
    for erow in cs_emp_by_dept(v_dept_code) loop
        dbms_output.put_line(erow.emp_id || '   ' || erow.emp_name || '     ' || erow.dept_code);
    end loop;
end;
/

-- 사용자에게 부서명을 입력받고 해당부서원을 모두 조회하는 프로시저 proc_print_emp_by_dept 작성.
-- 익명블럭에서 호출 proc_print_emp_by_dept('총무부');
-- 사번 사원명 부서명

-- 프로시져 선언
create or replace procedure proc_print_emp_by_dept(
    p_dept_title department.dept_title%type
)
is
    cursor cs_emp_by_dept(pc_dept_title department.dept_title%type)
    is
    select
        e.emp_id, e.emp_name, d.dept_title
    from
        employee e left join department d
            on e.dept_code = d.dept_id
    where
        d.dept_title = pc_dept_title;
begin

    for erow in cs_emp_by_dept(p_dept_title) loop
        dbms_output.put_line(erow.emp_id || '   ' || erow.emp_name || '     ' || erow.dept_title);
    end loop;

end;
/

-- 익명블럭에서 호출
begin
    proc_print_emp_by_dept('총무부');
end;
/


------------------------------------------------------------------------------
-- TRIGGER
------------------------------------------------------------------------------
-- 방아쇠. 연쇄반응.
-- 특정이벤트, DDL, DML등이 실행되었을때 자동적으로 어떤 처리가 일어나도록 하는 객체

-- 종류
-- 1. Logon/Logoff trigger
-- 2. DDL Trigger
-- 3. DML Trigger - insert / update / delete구문이 실행되었을때 트리거에 작성된 내용을 실행

-- 회원탈퇴시 탈퇴회원테이블에 자동으로 추가
-- 프로필을 변경시, 변경내역을 로그테이블에 추가

/*
    create [or replace] trigger 트리거명
        before | after
        insert or update or delete on 테이블명
        [for each row]
    declare        
        -- 지역변수 선언
    begin
        -- 실행코드
    end;
    /

    - before | after : 원 DML 실행전/후 트리거 설정 
    - for each row 
        - 생략시 문장레벨트리거 : 원DML문당 1번 실행
        - 작성시 행레벨트리거 : 처리되는 행마다 실행

    - 행레벨 트리거시 의사레코드(pseudo record)
    
                dml실행전   dml실행후
    --------------------------------------------------------
    insert        null       :new.컬럼명
    update       :old.컬럼명  :new.컬럼명
    delete       :old.컬럼명   null

*/

select * from tb_user;

create table tb_user(
    no number,
    name varchar2(100) not null,
    constraint pk_tb_user_no primary key(no)
);
create sequence seq_tb_user_no;

create table tb_user_log(
    no number,
    log varchar2(4000) not null,
    log_date date default sysdate,
    constraint pk_tb_user_log_no primary key(no)
);
create sequence seq_tb_user_log_no;


-- trigger
create or replace trigger trig_tb_user_log
    before
    insert or update or delete on tb_user
    for each row
begin
    -- 상태를 나타내는 boolean형 키워드
    -- inserting - insert시 true
    -- updating('컬럼명') - 특정컬럼을 수정하면 true
    -- deleting - delete시 true
    
    if inserting then
        insert into 
            tb_user_log(no, log)
        values(
            seq_tb_user_log_no.nextval,
            :new.no || '번 ' || :new.name || '님이 회원가입했습니다.'
        );
    elsif updating then
        insert into 
            tb_user_log(no, log)
        values(
            seq_tb_user_log_no.nextval,
            :new.no || '번 회원이 ' || :old.name || '에서 ' || :new.name || '으로 이름변경했습니다.'
        );
    
    elsif deleting then
        -- 1번회원이 탈퇴했습니다.
        insert into 
            tb_user_log(no, log)
        values(
            seq_tb_user_log_no.nextval,
            :old.no || '번 회원이 탈퇴했습니다.'
        );
    end if;
    
    -- 트랜잭션처리 하지 않는다.
    -- 트리거의 트랜잭션은 원DML문에 따라 commit 또는 rollback된다.
end;
/

-- 원 dml 실행
insert into
    tb_user
values(
    seq_tb_user_no.nextval,
    '홍길동'
);

update 
    tb_user
set
    name = '강길동'
where
    no = 2;

delete from
    tb_user
where
    no = 2;

commit;
rollback;

select * from tb_user;
select * from tb_user_log;

-- 트리거를 이용한 재고관리
-- 상품테이블(재고)
-- 입출고테이블

create table tb_product(
    pcode varchar2(20),
    pname varchar2(50),
    price number,
    stock number default 0,
    constraint pk_tb_product_pcode primary key(pcode),
    constraint ck_tb_product_stock check(stock >= 0)
);

create table tb_product_io(
    no number,
    pcode varchar2(20),
    status char(1), -- 입고 I, 출고 O
    amount number,
    io_date date default sysdate,
    constraint pk_tb_product_io_no primary key(no),
    constraint fk_tb_product_io_pcode foreign key(pcode) references tb_product(pcode)
);

create sequence seq_tb_product_io_no;

-- 상품데이터
insert into 
    tb_product
values (
    'apple_iphone_x', '아이폰X', 1000000, default
);
insert into 
    tb_product
values (
    'samsung_galaxy_20', '갤럭시20', 1500000, default
);

-- 입출고 데이터
insert into tb_product_io values(
    seq_tb_product_io_no.nextval, 'apple_iphone_x', 'I', 10, default
);
insert into tb_product_io values(
    seq_tb_product_io_no.nextval, 'apple_iphone_x', 'O', 3, default
);

select * from tb_product;
select * from tb_product_io;

-- 트리거생성
create or replace trigger trig_tb_product_stock
    before
    insert on tb_product_io
    for each row
begin
    if :new.status = 'I' then
        -- 입고시 + amount
        update
            tb_product
        set
            stock = stock + :new.amount
        where
            pcode = :new.pcode;
    else 
        -- 출고시 - amount
        update
            tb_product
        set
            stock = stock - :new.amount
        where
            pcode = :new.pcode;
    end if;

end;
/









