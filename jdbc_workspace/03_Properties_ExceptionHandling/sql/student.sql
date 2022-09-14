----------------------------------------------------------
-- 탈퇴회원관리
----------------------------------------------------------
--member 삭제 테이블 생성
--기존 컬럼에서 del_date컬럼 추가
create table member_del
as
select member.*, sysdate del_date from member
where 1=0;

--drop table member_del;
desc member_del;


--제약조건 추가
alter table member_del 
add constraint pk_member_del primary key(id)
add constraint ck_member_del_gender check(gender in ('M','F'))
modify del_date default sysdate;

--제약조건 조회
select A.owner,
      A.table_name,
      B.column_name,
      constraint_name,
      A.constraint_type,
      A.search_condition
from user_constraints A join user_cons_columns B 
    using (constraint_name)
where A.table_name = 'MEMBER_DEL';



--삭제트리거 생성
--resource 롤에 create trigger권한이 있기때문에 별도의 DCL없이 진행할 수 있음.
create or replace trigger trig_delete_member
    before 
    delete on member
    for each row
begin
    insert into 
        member_del
    values(
        :old.id,
        :old.name, 
        :old.gender, 
        :old.birthday, 
        :old.email, 
        :old.address, 
        :old.reg_date, 
        default);
end;
/

--데이터확인
select * from member_del;
