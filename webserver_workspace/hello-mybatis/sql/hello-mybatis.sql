--===========================================
-- hello-mybatis
--===========================================
-- student 테이블 생성
create table student (
    no number,
    name varchar2(50) not null,
    tel char(11) not null,
    created_at date default sysdate,
    updated_at date default sysdate,
    deleted_at date,
    constraint pk_student_no primary key(no)
);

create sequence seq_student_no;

insert into
    student (no, name, tel)
values
    (seq_student_no.nextval, '홍길동', '01012341234');
    
insert into
    student (no, name, tel)
values
    (seq_student_no.nextval, '신사임당', '01033334444');

-- 수정
update
    student
set
    tel = '01011112222',
    updated_at = sysdate
where
    deleted_at is null
  and 
    no = 2;

-- 삭제
update 
    student
set
    deleted_at = sysdate
where
    no = 1;
    
select * from student where deleted_at is null;
select * from student where deleted_at is null and no = 1;

--delete from student where tel not like '010%';

commit;


-- web ---> kh
select * from kh.employee;
select * from kh.department;
select * from kh.job;

-- kh계정에서 테이블 읽기권한 부여
grant select on employee to web;
grant select on department to web;
grant select on job to web;

-- system계정에서 create synonym 권한 부여
grant create synonym to web;

-- 동의어 synonym 생성
create synonym emp for kh.employee;
create synonym dept for kh.department;
create synonym job for kh.job;

-- 동의어로 조회
select * from emp;
select * from dept;
select * from job;

-- search2
select
    *
from (
    select
        e.*,
        decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender
    from
        emp e
    ) e
where
    1 = 1
  and email like '%a%'
  and gender = '남';
    

-- search3
select
    e.*,
    (select job_name from job where job_code = e.job_code) job_name,
    (select dept_title from dept where dept_id = e.dept_code) dept_title
from
    emp e;

select 
    *
from
    dept;

-- empUpdate
-- web emp테이블 수정권한 부여
update
    emp
set
    dept_code = 'D7'
where
    emp_id = '201';

select * from emp where emp_id = '201';

-- 관리자 또는 테이블주인 계정에서 update권한 부여
grant update on employee to web;




