--===================================================
--STUDENT 계정
--===================================================
--drop table product_detail;
--drop table product_stock;
--drop table product_io;

create table product_detail (
  id  varchar2(30),
  brand varchar2(50) not null,
  name  varchar2(100)  not null,
  price number  not null,
  monitor_size number,
  os varchar2(100),
  storage number,
  reg_date date default sysdate,
  constraints pk_product_detail_id primary key(id)
);  

create table product_stock (
  product_id  varchar2(30),
  stock number default 0,
  constraint pk_product_stock_id primary key(product_id),
  constraint fk_product_stock_id foreign key(product_id) references product_detail(id) on delete cascade,
  constraint ck_product_stock check(stock >= 0)
);

create table product_io(
  no number,
  product_id varchar2(30),
  count number,
  status char(1) check (status in ('I', 'O')),
  io_datetime timestamp default systimestamp,
  constraint pk_product_io_no primary key(no),
  constraint fk_product_io foreign key (product_id) references product_detail(id) on delete cascade
);

create sequence seq_product_io_no;

--입출고 내역(product_io)에 따라 상품테이블(product_stock)의 재고량을 갱신시키는 trigger
--drop trigger trg_product;
create or replace trigger trig_product
  after 
  insert on product_io -- PRODUCT_IO 테이블에 ON 시키기
  for each row
begin
  if :new.status = 'I'
  then 
    update product_stock
    set stock = stock + :new.count 
    where product_id = :new.product_id;-- 새로 들어오는 PCODE 값과 PRODUCT의 PCODE값이 일치하는것을 찾아라
  elsif :new.status = 'O'
  then
    update product_stock
    set stock = stock - :new.count
    where product_id = :new.product_id;
  end if;
end;
/

--상품정보 insert
insert into product_detail 
values ('14TD90P-GX30K', 'LG전자', '그램360', 1046990, 15, 'Windows10', 256, default);
insert into product_stock(product_id)
values('14TD90P-GX30K');

insert into product_detail 
values ('X413EA-EB086', 'ASUS', '비보북', 609000, 14, 'Windows10', 256, default);
insert into product_stock(product_id)
values('X413EA-EB086');

insert into product_detail 
values ('16ACH-R7-STORM', 'Lenovo', 'LEGION 5 Pro', 	1699000, 16, 'Windows10', 512, default);
insert into product_stock(product_id)
values('16ACH-R7-STORM');

insert into product_detail 
values ('MVVK2KH/A', 'Apple', '맥북프로', 3705690, 16, 'macOS', 512, default);
insert into product_stock(product_id)
values('MVVK2KH/A');


--입출고정보 insert
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'14TD90P-GX30K' , 30, 'I');
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'14TD90P-GX30K' , 20, 'O');
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'X413EA-EB086' , 100, 'I');
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'X413EA-EB086' , 10, 'O');
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'16ACH-R7-STORM' , 50, 'I');
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'16ACH-R7-STORM' , 5, 'O');
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'MVVK2KH/A' , 30, 'I');
insert into product_io(no, product_id, count, status) 
values(seq_product_io_no.nextval,'MVVK2KH/A' , 8, 'O');

commit;

select * from product_detail;
select * from product_stock;
select * from product_io;
select * from user_triggers;

-- selectProductList 전체 상품 조회
select
    pd.*, ps.stock
from
    product_detail pd join product_stock ps
        on pd.id = ps.product_id;
        
-- selectProductIOList 상품 입출고 조회
select 
    pio.no,
    pio.product_id,
    pd.name,
    pd.brand,
    pio.count,
    pio.status,
    pio.io_datetime, 
    ps.stock
from
    product_detail pd 
        join product_io pio
            on pd.id = pio.product_id
        join product_stock ps
            on pd.id = ps.product_id
where
    pd.id = '14TD90P-GX30K'
order by 
    io_datetime desc;     
    
-- searchProductBy 상품 아이디/이름 검색
select
    pd.*, ps.stock
from
    product_detail pd join product_stock ps
        on pd.id = ps.product_id
where 
--    lower(id) like '%ch%';
    lower(name) like '%그%';
        