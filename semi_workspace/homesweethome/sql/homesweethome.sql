--=====================================================================
-- 관리자 계정
--=====================================================================
-- homesweethome 계정 생성
alter session set "_oracle_script" = true; -- c##으로 시작하지 않는 일반계정 생성 허용

create user homesweethome
identified by homesweethome
default tablespace users;

grant connect, resource to homesweethome;
alter user homesweethome quota unlimited on users;

--==================================================================
-- homesweethome 계정
--==================================================================

-- 회원 테이블
create table member (
	member_id	 varchar2(50)		NOT NULL,
	password	varchar2(300),
	member_name	varchar2(50)		NOT NULL,
	nickname	varchar2(100),
	member_role	char(1)	DEFAULT 'U'	NOT NULL,
	phone	char(11)		NOT NULL,
	email	varchar2(200)		NOT NULL,
	birthday	date	,
	gender	char(1),
    member_social varchar2(10) default 'non' not null,
    enroll_date	date	DEFAULT sysdate	NOT NULL,
    constraint pk_member_id primary key(member_id),
    constraint ck_member_role check(member_role in ('U', 'A')),
    constraint ck_member_gender check(gender in ('M', 'F')),
    constraint uq_member_email unique(email),
    constraint ck_member_social check(member_social in ('non', 'kakao', 'google', 'naver'))
);
commit;
--drop table member;
--alter table  member modify member_id varchar2(50);
--alter table  member add   member_social varchar2(10) default 'non' not null;
--alter table  member add  ck_member_social check(member_social in ('non', 'kakao', 'google', 'naver'));
select
        constraint_name,
        uc.table_name,
        ucc.column_name,
        uc.constraint_type,
        uc.search_condition
from
        user_constraints uc 
                join user_cons_columns ucc
                        using(constraint_name)
where
        uc.table_name = 'MEMBER';
--회원 추가
insert into member
values (
    'admin', 'I22NjLc4MurqLYSka1vS6Loemy9zyL1iLdsxCfl7p/X7mMpE4jnenlGOwQzu/0RU9JuUGk2Wvr135asB83fZGQ==', '관리자', '관리자',  'A', '01011111111', 'admin@naver.com', to_date('19920308', 'yyyymmdd'), 'M', 'non', default
);  -- 비밀번호 asdf456!
insert into member
values (
    'honggd', '7oM2uUt7/ZM2z6Dm1bvy4MpBvC6CjacpWwZPfojluZFX44fczavYurPeTLahxXThi74ysNUxJteydlEKh8GAGQ==', '홍길동', '신출귀몰',  'U', '01012341234', 'honggd@naver.com', to_date('19920101', 'yyyymmdd'), 'M', 'non', default
); -- 비밀번호 asdf456!

insert into member
values (
    'leesunsin', 'QLbxJQ64C+NOKeru0zJ8AJvX2OCxkhMitjWWdMO9u1Wojosget61PNSEm2Zx9Tcw5vSp2zpE5ISvtZGbfL0xHg==', '이순신', '불멸',  'U', '0101341564', 'sunsin@naver.com', to_date('19920608', 'yyyymmdd'), 'M', 'non', default
); -- 비밀번호 asdf456!

commit; 
select * from  member order by enroll_date;
--delete from member where member_id = 'google103542600240152876199';
-- He5Pi8y1UOXqccN0PFCc2I0EG7+CoAM0f+EydSVuWFNmNhioqBkHsbln1/A0ee/9bG1mEc2YLFhH65AIUn3gjA==
-- zNJ7Pyr+VYkjztPg7P9P2WndOr0jrPYsJbwuHE8504YtU8D2gr+T9Yk1yCqqxXU9k0ce6MInKZ/qitfKChmfcQ==
-- 배송지 테이블
--drop table address;

CREATE TABLE address (
	address_no	number		NOT NULL,
    member_id varchar2(50) not null,
    address_title varchar2(20) default '배송지' not null,
	post_code	varchar2(30)		NOT NULL,
	address	varchar2(200)		NOT NULL,
	address_detail	varchar2(30)		NOT NULL,
	address_extra	varchar2(30),
    constraint pk_address_address_no primary key(address_no),
    constraint fk_address_member_id  foreign key(member_id) references member(member_id)
);

--alter table  address modify member_id varchar2(50);
-- 주소 추가
insert into address 
values (
   seq_address_no.nextval, 'admin',  default,  '06234',  '서울특별시 강남구 테헤란로 10길 9 그랑프리빌딩 5F', '2관 5층 M강의장',  '(역삼동)'
);

insert into address 
values (
   seq_address_no.nextval, 'honggd',  default,  '06234', '서울특별시 강남구 테헤란로 10길 9 그랑프리빌딩 5F', '2관 5층 M강의장',  '(역삼동)'
);

--  배송지 테이블 시퀀스 코드
create sequence seq_address_no nocache;
-- drop sequence seq_address_no;
select * from address  order by address_no;
--delete from address where member_id = 'kakao2275196037';

select p.*, i.* from product p left join product_image i on p.product_id = i.product_id where p.product_id = 'furniture_samik델루나 LED 프리미엄 수납 호텔 침대';
-- 상품 대분류 테이블
CREATE TABLE main_category (
	main_code	varchar2(30)		NOT NULL,
	main_category_name	varchar2(30)		NOT NULL,
    constraint pk_main_category_main_code  primary key(main_code)
);

insert into main_category values ('furniture', '가구');
insert into main_category values ('electroics', '전자제품');
insert into main_category values ('lighting', '조명');
insert into main_category values ('organizing', '수납/정리');
insert into main_category values ('living', '생활용품');
desc main_category;
commit;
select * from main_category;
drop table main_category;
--delete from main_category where main_code = 'organizing_item';
-- 상품 소분류 테이블
CREATE TABLE sub_category (
	sub_code	varchar2(30)		NOT NULL,
    main_code varchar2(30) not null,
	sub_category_name	varchar2(30)		NOT NULL,
    constraint pk_sub_category_sub_code  primary key(sub_code),
    constraint fk_sub_category_main_code foreign key(main_code) references main_category(main_code)
);
insert into sub_category values ( 'bookshelf' , '책장');
insert into sub_category values ( 'desk', '책상');
insert into sub_category values ( 'table','식탁');
insert into sub_category values ( 'table_chair','식탁의자');
insert into sub_category values ( 'office_chair','사무용의자');
insert into sub_category values ( 'chest_of_drawers', '수납장');
insert into sub_category values ( 'wardrobe',  '옷장');
insert into sub_category values ( 'bed', '침대');

insert into sub_category values ( 'tv', 'electroics', 'TV');
insert into sub_category values ( 'air_conditioner', 'electroics', '에어컨');
insert into sub_category values ( 'refrigerator', 'electroics', '냉장고');
insert into sub_category values ( 'kimchi_refrigerator', 'electroics', '김치냉장고');
insert into sub_category values ( 'oven', 'electroics', '오븐');
insert into sub_category values ( 'microwave', 'electroics', '전자레인지');
insert into sub_category values ( 'washing_machine', 'electroics', '세탁기');

insert into sub_category values ( 'led_lighting', 'lighting', 'LED등');
insert into sub_category values ( 'fluorescent_lamp', 'lighting', '형광등');
insert into sub_category values ( 'desk_stand', 'lighting', '데스크 스탠드');
insert into sub_category values ( 'mood', 'lighting', '무드등');
insert into sub_category values ( 'wall_light', 'lighting', '벽조명');
insert into sub_category values ( 'sensor_light', 'lighting', '센서등');

insert into sub_category values ( 'storage_closet', 'organizing', '서랍장');
insert into sub_category values ( 'living_box', 'organizing', '리빙박스');
insert into sub_category values ( 'basket', 'organizing', '바구니');
insert into sub_category values ( 'clothes_rack', 'organizing', '행거');
insert into sub_category values ( 'shelf', 'organizing', '선반');
insert into sub_category values ( 'hanger', 'organizing', '옷걸이');

insert into sub_category values ( 'bathroom_products', 'living', '욕실용품');
insert into sub_category values ( 'towel', 'living', '수건');
insert into sub_category values ( 'cleaning_tools', 'living', '청소용품');
insert into sub_category values ( 'laundry_products', 'living', '세탁용품');
insert into sub_category values ( 'household_goods', 'living', '생활잡화');

select * from sub_category;
--drop table main_category;
--drop table sub_category;

commit;
-- 브랜드 테이블
CREATE TABLE brand (
	brand_id	varchar2(30)		NOT NULL,
	brand_name	varchar2(30)		NOT NULL,
    constraint pk_brand_brand_id  primary key(brand_id)
);
select * from brand;

--select p.*, b.brand_name from ( select row_number() over(order by reg_date desc) rnum, p.* from  product p left join today_deal t  on p.product_id = t.product_id ) p left join brand b on p.brand_id = b.brand_id where rnum between 1 and 4;

--drop table brand;
insert into brand values ( 'furniture_dodot', 'dodot');
insert into brand values ( 'furniture_desker', 'desker');
insert into brand values ( 'furniture_livart', 'livart');
insert into brand values ( 'furniture_hansam', '한샘');
insert into brand values ( 'furniture_samik', '삼익가구');

insert into brand values ( 'electroics_samsung', '삼성');
insert into brand values ( 'electroics_lg', '엘지');
insert into brand values ( 'electroics_carrier', '캐리어');
insert into brand values ( 'electroics_winia', '위니아딤채');

insert into brand values ( 'lighting_samsung', '삼성전자');
insert into brand values ( 'lighting_philips', '필립스');
insert into brand values ( 'lighting_lightingbank', '조명뱅크');
insert into brand values ( 'lighting_oa', '오아');

insert into brand values ( 'organizing_item_dearliving', '디얼리빙');

insert into brand values ( 'living_cottonliving', '코튼리빙');
commit;

select * from brand;
select * from sub_category;
select * from main_category;
--drop table main_category;

delete from product where sub_code = 'towel';
-- 상품 테이블
CREATE TABLE product (
	product_id	varchar2(80)		NOT NULL,
	product_name	varchar2(100)		NOT NULL,
	main_code	varchar2(30)		NOT NULL,
	sub_code varchar2(30)		NOT NULL,
	brand_id	varchar2(30)		NOT NULL,
	product_height	number		NOT NULL,
	product_width	 number		NULL,
	product_depth	number		NOT NULL,
	product_color	varchar2(10)		NOT NULL,
	product_price	number		NOT NULL,
	reg_date	date	DEFAULT sysdate	NOT NULL,
	p_content	varchar2(4000)		NULL,           -- 상품 설명 컬럼
    constraint pk_product_product_id primary key(product_id),
    constraint fk_product_main_code foreign key(main_code) references main_category(main_code),
    constraint fk_product_sub_code foreign key(sub_code) references sub_category(sub_code),
    constraint fk_product_brand_id foreign key(brand_id) references brand(brand_id)
);
--select p.* from product p left join main_category m on p.main_code = m.main_code left join sub_category s on p.sub_code = s.sub_code left join brand b on p.brand_id = b.brand_id where product_id like '%1800%' order by p.reg_date desc;
desc product;
--select p.product_name, i.*, s.stock from product_io i left join product p on i.product_id = p.product_id left join product_stock s on i.product_id = s.product_id  where p.main_code like 'furniture';
insert into product values ('product1', '제품1', 12345, 2525, 'electroics_samsung', 20, 20, 20, 'blue', 200000, default,'example' ,'이건말이죠, 바로 샘플1!');
select * from product;
--alter table  product modify product_name	varchar2(100);
--delete from product where product_id = 'furniture_dodot5단 철제 책장';
commit;
--drop table product;
--drop table product_image;
-- 상품 이미지 테이블
CREATE TABLE product_image (
	attach_no	number		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	original_filename	varchar2(255)		NULL,
	renamed_filename	varchar2(255)		NULL,
	reg_date	date	DEFAULT sysdate,
    constraint pk_product_image_attach_no primary key(attach_no),
    constraint fk_product_image_product_id foreign key(product_id) references product(product_id) on delete cascade
);
--drop table product_image;
select * from product_image;
-- 상품 이미지 테이블 시퀀스 코드
create sequence seq_product_image_no nocache;
commit;

-- 상품 설명용 이미지 테이블
CREATE TABLE product_description_image (
	attach_no	number		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	original_filename	varchar2(255)		NULL,
	renamed_filename	varchar2(255)		NULL,
	reg_date	date	DEFAULT sysdate,
    constraint pk_product_description_image_attach_no primary key(attach_no),
    constraint fk_product_description_image_product_id foreign key(product_id) references product(product_id) on delete cascade
);
--drop table product_image;
--drop table product_description_image;
select * from product_description_image;
-- 상품 이미지 테이블 시퀀스 코드
create sequence seq_product_description_image_no nocache;
commit;
-- 비회원 배송지 테이블
CREATE TABLE nm_delivery_info (
	non_member_code	varchar2(100)		NOT NULL,
	post_code	char(5)	,
	address	varchar2(200),
	address_detail	varchar2(400),
	address_extra	varchar2(100),
    constraint pk_nm_delivery_info_code primary key(non_member_code)
);


-- 비회원 테이블
CREATE TABLE non_member (
	non_code	varchar2(50)		NOT NULL,
    non_delivery_code	varchar2(100)		NOT NULL,
	non_order_no	number		NULL,
	non_member_name	varchar2(30)		NULL,
	email	varchar2(200)		NULL,
	phone	char(11)		NULL
    constraint pk_non_member_phone primary key(non_code),
    constraint fk_non_member_delivery_code foreign key(non_delivery_code) references nm_delivery_info(non_member_code) on delete cascade,
    constraint fk_non_member_non_order_no foreign key(non_order_no) references non_member_purchase(non_order_no)
);


-- 이벤트 테이블
CREATE TABLE event (
	event_id	varchar2(30)		NOT NULL,
	event_title	varchar2(1000),
	event_content	varchar2(1000),
	event_start_date	date	,
	event_end_date	date	,
	reg_date	date	DEFAULT sysdate,
     constraint pk_event_event_id primary key(event_id)
);

-- 이벤트 참여자 테이블
CREATE TABLE event_applicants (
	event_apply_code	varchar2(30)		NOT NULL,
	event_id	varchar2(30)		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
    constraint pk_event_applicants_code primary key(event_apply_code),
    constraint fk_event_applicants_event_id foreign key(event_id) references event(event_id) on delete cascade,
    constraint fk_event_applicants_member_id foreign key(member_id) references member(member_id) on delete cascade
    
);

-- 오늘의딜 테이블
CREATE TABLE today_deal (
	today_deal_no 	number		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	discount_rate	number	,
    constraint pk_today_deal_no primary key(today_deal_no),
    constraint fk_today_deal_product_id foreign key(product_id) references product(product_id) on delete cascade
);
select * from today_deal;
--drop table today_deal;
--commit;
-- 오늘의딜 테이블 시퀀스 코드
create sequence seq_today_deal_no nocache;
commit;
select p.*, b.brand_name, m.main_category_name, s.sub_category_name  from ( select row_number() over(order by reg_date desc) rnum, p.* from  product p left join today_deal t on p.product_id = t.product_id ) p left join brand b on p.brand_id = b.brand_id  left join main_category m on p.main_code = m.main_code left join sub_category s on p.sub_code = s.sub_code;

insert into today_deal values (seq_today_deal_no.nextval, 'lighting_oa무드등 타이머 높이조절 캔들워머', 33);
insert into today_deal values (seq_today_deal_no.nextval, 'furniture_samik델루나 LED 프리미엄 수납 호텔 침대', 41);
insert into today_deal values (seq_today_deal_no.nextval, 'organizing_item_dearliving논슬립 바지걸이 20개', 61);
insert into today_deal values (seq_today_deal_no.nextval, 'living_cottonliving코마사 40수 호텔수건', 61);

commit;
select * from product order by reg_date desc;

-- 쿠폰 테이블
CREATE TABLE coupon (
	coupon_id	varchar2(30)		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	coupon_name	varchar2(300)		NULL,
	coupon_discount_rate	number		NULL,
	reg_date	date	DEFAULT sysdate	NULL,
	start_date	date,
	end_date	date,
    constraint pk_coupon_id primary key(coupon_id),
    constraint fk_coupon_member_id foreign key(member_id) references member(member_id) on delete cascade
);


-- 회원 장바구니 테이블
CREATE TABLE cart (
	cart_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	product_count	number	DEFAULT 1	NOT NULL,
    constraint pk_cart_no primary key(cart_no),
    constraint fk_cart_member_id foreign key(member_id) references member(member_id) on delete cascade,
    constraint fk_cart_product_id foreign key(product_id) references product(product_id) on delete cascade
);
-- 회원 장바구니 테이블 시퀀스 코드
create sequence seq_qa_cart_no nocache;
select * from cart;
--drop table cart;
insert into cart values (1, 'honggd', ' ', 3);

-- 구매-개별상품 테이블
CREATE TABLE purchase (
	order_no	varchar2(255)		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	cart_no	number		NULL,
	product_count	number		NULL,
	order_date	date	DEFAULT sysdate,
    constraint pk_purchase_order_no primary key(order_no),
    constraint fk_purchase_member_id foreign key(member_id) references member(member_id)
    constraint fk_purchase_product_id foreign key(product_id) references product(product_id)
    constraint fk_purchase_cart_no foreign key(cart_no) references cart(cart_no)
);
select * from purchase;

-- 전체 구매목록 테이블
CREATE TABLE orderlist (
	order_list_no	varchar2(255)		NOT NULL,
	order_no	varchar2(255)		NOT NULL,
	total_price	number		NULL,
	order_date	date default sysdate,
    constraint pk_orderlist_no primary key(order_list_no),
    constraint fk_order_no foreign key(order_no) references member(order_no)
);

-- 상품 재고 테이블
CREATE TABLE product_stock (
	product_id	varchar2(80)		NOT NULL,
	stock  number	 DEFAULT 0	NULL,
    constraint fk_product_stock_product_id foreign key(product_id) references product(product_id) on delete cascade,
    constraint ck_product_stock check(stock >= 0)
);
commit;
insert into product_stock values('furniture_hansam4인용 대리석 식탁', default);
insert into product_stock values('furniture_dodot5단 철제 책장', default);
insert into product_stock values('furniture_deskerDSAD118D 1800x700 컴퓨터데스크 5colors', default);
select * from product_stock;
--drop table product_stock;
commit;
 
 update
        product_stock
set
       stock= '850'
where
        product_id = 'furniture_dodot5단 철제 책장';
        
--alter table  product_stock modify product_id varchar2(80);
--	no number		NOT NULL,
--    constraint pk_product_stock_no primary key(no),
-- 상품 재고 테이블 시퀀스 코드
--create sequence seq_product_stock_no nocache;
--drop table product_io;
--drop table product_stock;
-- 상품 입출고 테이블
CREATE TABLE product_io (
	no	 number		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
    count number,
	status	char(1)		NULL,
	io_datetime	timestamp	DEFAULT systimestamp	NOT NULL,
    constraint pk_product_io_no primary key(no), 
    constraint fk_product_io_product_id foreign key(product_id) references product(product_id),
    constraint ck_product_io_status check(status in ('I', 'O'))
);
select * from product_io order by io_datetime desc;
select * from product_io where product_id = '%%';
select * from product_io;
drop table product_io;
commit;

--select i.* from product_io i join product p on i.product_id = p.product_id where p.main_code = 'furniture';

insert into product_io values(seq_product_io_no.nextval, 'furniture_dodot5단 철제 책장', 30, 'I', default); -- 850
insert into product_io values(seq_product_io_no.nextval, 'furniture_deskerDSAD118D 1800x700 컴퓨터데스크 5colors', 50, 'I', default); -- 330
insert into product_io values(seq_product_io_no.nextval, 'furniture_hansam4인용 대리석 식탁',25, 'I', default); -- 75

--alter table  product_io modify product_id varchar2(80);
-- 상품 입출고 테이블 시퀀스 코드
create sequence seq_product_io_no nocache;
--drop sequence seq_product_io_no;
commit;

-- 입출고 시 트리거 
create or replace trigger trig_product_io
        before
        insert on product_io
        for each row
begin
          -- 입고 시
          if :new.status = 'I' then
                  update
                        product_stock
                  set
                        stock = stock + :new.count
                  where
                        product_id = :new.product_id;
           -- 출고 시              
          else
                 update
                        product_stock
                  set
                        stock = stock - :new.count
                  where
                        product_id = :new.product_id;
         end if;
end;
/


-- 비회원 장바구니
CREATE TABLE non_member_cart (
	cart_no	number		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	non_code	varchar2(50)		NOT NULL,
	product_count	number	DEFAULT 1	NOT NULL,
    constraint pk_non_member_cart_no primary key(cart_no), 
    constraint fk_non_member_cart_product_id foreign key(product_id) references product(product_id),
    constraint fk_non_member_cart_non_code foreign key(non_code) references non_member(non_code)
);
select * from non_member_cart;

-- 비회원 구매-개별상품
CREATE TABLE non_member_purchase (
	order_no	varchar2(255)		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	non_code	varchar2(50)		NOT NULL,
	cart_no	number		NOT NULL,
	product_count	number		NULL,
	order_date	date	DEFAULT sysdate,
    constraint pk_non_member_purchase_order_no primary key(order_no), 
    constraint fk_non_member_purchase_product_id foreign key(product_id) references product(product_id),
    constraint fk_non_member_purchase_non_code foreign key(non_code) references non_member(non_code),
    constraint fk_non_member_purchase_cart_no foreign key(cart_no) references non_member_cart(cart_no)
);
select * from non_member_purchase;


-- 비회원 전체구매목록
CREATE TABLE non_member_orderlist (
	non_member_order_list_no	varchar2(255)		NOT NULL,
	order_no	varchar2(255)		NOT NULL,
	total_price	number		NULL,
	order_date	date	 default sysdate,
    constraint pk_non_member_orderlist_no primary key(non_member_order_list_no), 
    constraint fk_non_member_orderlist_order_no foreign key(order_no) references non_member_purchase(order_no)
);

-- 상품 리뷰 테이블
CREATE TABLE prodcut_review (
	p_review_no	number		NOT NULL,
	product_id	varchar2(80)		NOT NULL,
	review_title	varchar2(100)		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	content	varchar2(4000)		NOT NULL,
	reg_date	date	DEFAULT sysdate	NOT NULL,
	durability_eval	number	DEFAULT 5	NULL,
	price_eval	number	DEFAULT 5	NOT NULL,
	design_eval	number	DEFAULT 5	NOT NULL,
	delivery_eval	number	DEFAULT 5	NOT NULL,
	like_count	number	 default 0,
    constraint pk_prodcut_review_no primary key(p_review_no), 
    constraint fk_prodcut_review_product_id foreign key(product_id) references product(product_id),
    constraint fk_prodcut_review_member_id foreign key(member_id) references member(member_id)
);

select * from prodcut_review;
drop table prodcut_review;
commit;

-- 상품 리뷰 테이블 시퀀스 코드
create sequence seq_prodcut_review_no nocache;

-- 상품리뷰 이미지 테이블
CREATE TABLE review_product_image (
	attach_no	number		NOT NULL,
	p_review_no	number		NOT NULL,
	original_filename	varchar2(255)		NULL,
	renamed_filename	varchar2(255)		NULL,
	reg_date	date	DEFAULT sysdate,
    constraint pk_review_product_image_no primary key(attach_no), 
    constraint fk_review_product_image_p_review_noforeign key(p_review_no) references prodcut_review(p_review_no)
);

-- 상품 리뷰 이미지  테이블 시퀀스 코드
create sequence seq_review_product_image_no nocache;


-- 배송회사 테이블
CREATE TABLE shipment_company (
	company_code	varchar2(30)		NOT NULL,
	company_name	varchar2(30)		NULL,
    constraint pk_shipment_company_code primary key(company_code)
);

-- 배송상태 테이블
CREATE TABLE shipment_status (
	shipment_status_code	varchar2(30)		NOT NULL,
	shipment_ready	varchar2(30),
	in_transit	varchar2(30),
	deliverd	varchar2(30),
    constraint pk_shipment_status_code primary key(shipment_status_code)
);

-- 배송 테이블
CREATE TABLE shipment (
	tracking_code	number		NOT NULL,
	shipment_status_code	varchar2(30)		NOT NULL,
	company_code	varchar2(30)		NOT NULL,
    constraint pk_shipment_tracking_code primary key(order_sheet),
    constraint fk_shipment_shipment_status_code foreign key(shipment_status_code) references shipment_status(shipment_status_code) ,
    constraint fk_shipment_company_code foreign key(company_code) references shipment_company(company_code)
);


-- 주문서 테이블
CREATE TABLE order (
	order_sheet	vachar2(100)		NOT NULL,
	tracking_code	number		NOT NULL,
	order_list_no	varchar2(255),
	non_member_order_list_no	varchar2(255),
    constraint pk_order_order_sheet primary key(order_sheet),
    constraint fk_order_tracking_code foreign key(tracking_code) references shipment(tracking_code),
    constraint fk_order_order_list_no foreign key(order_list_no) references orderlist(order_list_no),
    constraint fk_order_non_member_order_list_no foreign key(non_member_order_list_no) references non_member_orderlist(non_member_order_list_no)
);


-- 질문과 답변 테이블
CREATE TABLE qa_board (
	board_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	board_title	varchar2(200),
	content	varchar2(1000),
	read_count	number	 DEFAULT 0,
	reg_date	date	DEFAULT sysdate,
    constraint pk_qa_board_no primary key(board_no),
    constraint fk_qa_board_member_id foreign key(member_id) references member(member_id)
);

-- 질문과 답변 테이블 시퀀스 코드
create sequence seq_qa_board_no nocache;

--질문과 답변 댓글 테이블
CREATE TABLE qa_board_reply (
	comment_no	number		NOT NULL,
	board_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	content	varchar2(100)		NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
    constraint pk_qa_board_reply_comment_no primary key(comment_no),
    constraint pk_qa_board_reply_board_no foreign key(board_no) references qa_board(board_no),
    constraint fk_qa_board_reply_member_id foreign key(member_id) references member(member_id)
);
-- 질문과 답변 댓글 테이블 시퀀스 코드
create sequence seq_qa_board_reply_no nocache;


-- 질문과 답변 공지사항 테이블
CREATE TABLE qa_notice (
	notice_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	notice_title	varchar2(200),
	content	varchar2(1000),
	read_count	number 	DEFAULT 0,
	reg_date	date	DEFAULT sysdate,
    constraint pk_qa_notice_no primary key(notice_no),
    constraint fk_qa_notice_member_id foreign key(member_id) references member(member_id)
);

-- 질문과 답변 공지사항 테이블 시퀀스 코드
create sequence seq_qa_notice_no nocache;

-- 질문과 답변 공지사항 댓글 테이블
CREATE TABLE qa_notice_reply (
	comment_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	notice_no	 number		NOT NULL,
	content	varchar2(100)		NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	,
    constraint pk_qa_notice_reply_comment_no primary key(comment_no),
    constraint fk_qa_notice_reply_member_id foreign key(member_id) references member(member_id),
    constraint fk_qa_notice_reply_notice_no foreign key(notice_no) references qa_notice(notice_no)
);
-- 질문과 답변 공지사항 댓글 테이블 시퀀스 코드
create sequence seq_qa_notice_reply_no nocache;

-- 노하우 게시판 테마 테이블
CREATE TABLE community_know_how_theme (
	theme_category_no	number		NOT NULL,
	theme_name	varchar2(20)		NOT NULL,
    constraint pk_community_know_how_theme_no primary key(theme_category)
);
-- 노하우 게시판 테마 테이블 시퀀스 코드
create sequence seq_community_know_how_theme_no nocache;

-- 노하우 게시판 테이블
CREATE TABLE community_know_how (
	knowhow_board_no	number		NULL,
	member_id	varchar2(20)		NOT NULL,
	theme_category_no	number		NOT NULL,
	content	varchar2(1000)		NULL,
	read_count	number	DEFAULT 0	NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
    constraint pk_community_know_how_no primary key(knowhow_board_no),
    constraint fk_community_know_how_member_id foreign key(member_id) references member(member_id),
    constraint fk_community_know_how_theme_category_no foreign key(theme_category_no) references community_know_how_theme(theme_category_no),
);
-- 노하우 게시판 테이블 시퀀스 코드
create sequence seq_community_know_how_no nocache;

-- 노하우 댓글 테이블
CREATE TABLE knowhow_reply (
	comment_no	number		NOT NULL,
	knowhow_board_no	number		NULL,
	member_id	varchar2(20)		NOT NULL,
	content	varchar2(100)	DEFAULT 0	NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate, 
    constraint pk_knowhow_reply_comment_no primary key(comment_no),
    constraint fk_knowhow_reply_knowhow_board_no foreign key(knowhow_board_no) references community_know_how(knowhow_board_no),
    constraint fk_knowhow_reply_member_id foreign key(member_id) references member(member_id),
);

-- 커뮤니티 사진 테이블
CREATE TABLE community_image (
	community_img_no number 	NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	title	varchar2(30)		NULL,
	content	varchar2(3000)		NULL,
	read_count	number	DEFAULT 0	NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
    constraint pk_community_community_img_no primary key(community_img_no),
    constraint fk_community_member_id foreign key(member_id) references member(member_id)
);
-- 커뮤니티 사진 테이블 시퀀스 코드
create sequence seq_community_image_no nocache;

-- 사진 게시판 첨부파일 테이블
CREATE TABLE community_image_attachment (
	attach_no	number		NOT NULL,
	community_img_no 	number		NOT NULL,
	original_filename	varchar2(255)		NULL,
	renamed_filename	varchar2(255)		NULL,
	reg_date	date	DEFAULT sysdate	,
    constraint pk_community_image_attachment_no primary key(attach_no),
    constraint fk_community_image_attachment_img_no foreign key(community_img_no) references community_image(community_img_no)
);
-- 커뮤니티 사진 첨부파일 테이블 시퀀스 코드
create sequence seq_community_image_attachment_no nocache;

-- 동영상 게시판 테이블
CREATE TABLE community_video (
	video_board_no	 number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	video_title	varchar2(200)		NULL,
	content	varchar2(1000)		NULL,
	read_count	number	DEFAULT 0	NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate,
    constraint pk_community_video_no primary key(video_board_no),
    constraint fk_community_video_member_id foreign key(member_id) references member(member_id)
);

-- 커뮤니티 동영상 테이블 시퀀스 코드
create sequence seq_community_video_no nocache;

-- 커뮤니티 동영상 첨부파일 테이블
CREATE TABLE community_video_attachment (
	attach_no	number		NOT NULL,
	video_board_no 	number		NOT NULL,
	original_filename	varchar2(255)		NULL,
	renamed_filename	varchar2(255)		NULL,
	reg_date	date	DEFAULT sysdate,
    constraint pk_community_video_attachment_no primary key(attach_no),
    constraint fk_community_video_attachment_video_board_no foreign key(video_board_no) references community_video(video_board_no)
);

-- 커뮤니티 동영상 테이블 시퀀스 코드
create sequence seq_community_video_attachment_no nocache;

-- 문의사항 게시판 공지사항 테이블
CREATE TABLE questions_notice (
	notice_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	notice_title	varchar2(200)		NULL,
	content	varchar2(1000)		NULL,
	read_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL ,
    constraint pk_questions_notice_no primary key(notice_no),
    constraint fk_questions_notice_member_id foreign key(member_id) references member(member_id)
);

-- 문의사항 게시판 공지사항  시퀀스 코드
create sequence seq_questions_notice_no nocache;

-- 문의사항 게시판 공지사항 댓글 테이블
CREATE TABLE questions_noti_reply (
	comment_no	number		NOT NULL,
	notice_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	content	varchar2(100)		NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
    constraint pk_questions_noti_reply_no primary key(comment_no),
    constraint fk_questions_noti_reply_notice_no foreign key(notice_no) references questions_notice(notice_no),
    constraint fk_questions_noti_reply_member_id foreign key(member_id) references member(member_id)
);

-- 문의사항 게시판 공지사항  댓글 시퀀스 코드
create sequence seq_questions_noti_reply_no nocache;

-- 문의사항 게시판 테이블
CREATE TABLE questions_board (
	board_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	board_title	varchar2(100)		NULL,
	content	varchar2(1000)		NULL,
	read_count	number	DEFAULT 0	NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
     constraint pk_questions_board_no primary key(board_no),
    constraint fk_questions_board_member_id foreign key(member_id) references member(member_id)
);

-- 문의사항 게시판 테이블 시퀀스 코드
create sequence seq_questions_board_no nocache;

-- 문의사항 댓글 테이블
CREATE TABLE questions_board_reply (
	comment_no	number		NOT NULL,
	board_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	content	varchar2(100)		NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
    constraint pk_questions_board_reply_no primary key(comment_no),
    constraint fk_questions_board_reply_board_no foreign key(board_no) references questions_board(board_no),
    constraint fk_questions_board_reply_member_id foreign key(member_id) references member(member_id)
);

-- 문의사항 게시판  댓글 시퀀스 코드
create sequence seq_questions_board_reply_no nocache;

-- 이메일 고객문의 분류 테이블
create table cs_email_type (
    select_type varcxhr2(10) not null,
    select_type_name varchar2(30) not null,
    constraint pk_cs_email_type primary key(select_type)
);
select * from cs_email_type;

insert into cs_email_type values ('0', '회원정보 문의');
insert into cs_email_type values ('1', '쿠폰/포인트 문의');
insert into cs_email_type values ('2', '주문/결제 관련 문의');
insert into cs_email_type values ('3', '취소/환불 관련 문의');
insert into cs_email_type values ('4', '배송 관련 문의');
insert into cs_email_type values ('5', '주문 전 상품 정보 문의');
insert into cs_email_type values ('6', '서비스 개선 제안');
insert into cs_email_type values ('7', '시스템 오류 제보');
insert into cs_email_type values ('8', '불편 신고');
insert into cs_email_type values ('9', '기타문의');

-- 이메일 고객문의 기록 저장용 테이블
create table cs_email_log (
    cs_email_log_no number not null,
    name varchar2(30) not null,
    email varchar2(100) not null,
    title varchar2(500) not null,
    content varchar2(2000) not null,
    select_type varchar2(10) not null,
    reg_date date default sysdate,
    constraint pk_cs_email_log_no primary key(cs_email_log_no),
    constraint fk_cs_email_log_select_type foreign key(select_type) references cs_email_type(select_type)
);
--alter table  cs_email_log modify email varchar2(100);
--alter table  cs_email_log modify content varchar2(2000);
select * from cs_email_log order by cs_email_log_no;
--이메일 고객문의 기록 저장용 테이블 시퀀스 코드
create sequence seq_cs_email_log_no nocache;

-- 이메일 고객문의 이미지 기록 저장용 테이블
create table cs_email_image (
    no	 number		NOT NULL,
	cs_email_log_no 	number		NOT NULL,
	original_filename	varchar2(255)		NULL,
	renamed_filename	varchar2(255)		NULL,
	reg_date	date	DEFAULT sysdate,
    constraint pk_cs_email_image_no primary key(no),
    constraint fk_cs_email_log_no foreign key(cs_email_log_no) references cs_email_log(cs_email_log_no)        
);
--이메일 고객문의 이미지 기록 저장용 테이블 시퀀스 코드
create sequence seq_cs_email_image_no nocache;
select * from cs_email_image;
commit;
---- 매출 테이블
--CREATE TABLE revenue (
--	revenue_code varchar2(100)		NOT NULL,
--	order_sheet	vachar2(100)		NOT NULL,
--	no number		NOT NULL,
--	revenue_val	number		NULL,
--	revenue_date	date	DEFAULT sysdate	NOT NULL,
--    constraint pk_revenue_code primary key(revenue_code),
--    constraint fk_revenue_order_sheet foreign key(order_sheet) references order(order_sheet),
--    constraint fk_revenue_no foreign key(no) references product_io(no)
--);



---- 통계
--CREATE TABLE statistics (
--	stat_id	varchar2(30)		NOT NULL,
--	product_id	varchar2(30)		NOT NULL,
--	member_id	varchar2(20)		NOT NULL,
--	stat_daily	date		NULL,
--	stat_weekly	date		NULL,
--	stat_monthly	date		NULL,
--	product_bought	number		NULL,
--	product_search	varchar2(500)		NULL,
--	main_category	varchar2(30)		NULL,
--	gender	char1		NULL,
--	age	number		NULL,
--	visit_count	number		NULL
--);

--------커뮤니티부분---------------------------

--===================
--질문과 답변
--===================



--질문과답변 게시판
CREATE TABLE qa_board (
	board_no	 number		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
    nickname	 varchar2(100),
	board_title	varchar2(200),
	content	varchar2(1000),
	read_count	number	 DEFAULT 0,
	reg_date	date	DEFAULT sysdate,
    constraint pk_qa_board_no primary key(board_no),
    constraint fk_qa_board_member_id foreign key(member_id) references member(member_id)
);
create sequence seq_qa_board_no nocache;

--질문과 답변_댓글
CREATE TABLE qa_board_reply (
	comment_no	number,
	board_no	number,
	member_id	varchar2(50),
    nickname	 varchar2(100),
	content	varchar2(100),
	like_count	number	DEFAULT 0,
	reg_date	date	DEFAULT sysdate,
    comment_level number default 1,
    comment_ref number,
    constraint pk_qa_board_reply_comment_no primary key(comment_no),
    constraint fk_qa_board_reply_board_no foreign key(board_no) references qa_board(board_no) on delete cascade,
    constraint fk_qa_board_reply_member_id foreign key(member_id) references member(member_id)
);
create sequence seq_qa_board_reply_no nocache;

--공지사항
CREATE TABLE qa_notice (
	notice_no	number		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
    nickname	 varchar2(100),
	notice_title	varchar2(200),
	content	varchar2(1000),
	read_count	number 	DEFAULT 0,
	reg_date	date	DEFAULT sysdate,
    constraint pk_qa_notice_no primary key(notice_no),
    constraint fk_qa_notice_member_id foreign key(member_id) references member(member_id)
);
create sequence seq_qa_notice_no nocache;

--공지사항_댓글
CREATE TABLE qa_notice_reply (
	comment_no	number		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
    nickname varchar2(100),
	notice_no	 number		NOT NULL,
	content	varchar2(100)		NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	,
    comment_level number default 1,
    comment_ref number,
    constraint pk_qa_notice_reply_comment_no primary key(comment_no),
    constraint fk_qa_notice_reply_member_id foreign key(member_id) references member(member_id),
    constraint fk_qa_notice_reply_notice_no foreign key(notice_no) references qa_notice(notice_no)
);
 
create sequence seq_qa_notice_reply_no nocache; 


--질문과답변_첨부파일
create table qa_attachment (
    no number,
    board_no number not null,
    original_filename varchar2(255) not null, -- 업로드한 파일명
    renamed_filename varchar2(255) not null, -- 저장된 파일명
    reg_date date default sysdate,
    constraint pk_qa_attachment_no primary key(no),
    constraint fk_qa_attachment_board_no foreign key(board_no) references qa_board(board_no) on delete cascade
);

--공지사항_첨부파일
create table qa_notice_attachment(
    no number,
    notice_no number not null,
    original_filename varchar2(255) not null,
    renamed_filename varchar2(255) not null,
    constraint pk_qa_attach_no primary key(no),
    constraint fk_qa_notice_attachment_notice_no foreign key(notice_no) references qa_notice(notice_no) on delete cascade
);

create sequence seq_qa_notice_attach_no;


--===================
--이벤트
--===================

--이벤트
create table event(
    event_id varchar2(30) not null, 
    event_title	varchar2(1000), 
	event_content	varchar2(1000),
	event_start_date	date	,
	event_end_date	date	,
	reg_date	date	DEFAULT sysdate,
    no number,
    title_filename varchar2(255),  --이벤트 목록 조회시 썸네일용

   constraint pk_event_no primary key(no)
);

create sequence seq_event_no;


--이벤트 첨부파일
create table event_att(
    no number,
    original_filename varchar2(255) , -- 업로드한 파일명 + 내용
    renamed_filename varchar2(255), -- 저장된 파일명
    event_no number,
    
    constraint pk_event_att_no primary key(no),
    constraint fk_event_no foreign key(event_no) references event(no) on delete cascade
);

create sequence seq_eventatt_no;

--이벤트 참가자
CREATE TABLE event_applicants (
	event_apply_code	varchar2(30)		NOT NULL, --제목에 해당하는 부분입니다.
	no	number	NOT NULL,
	member_id	varchar2(50)		NOT NULL,
    nickname varchar2(100),
    content varchar2(1000),
    event_no number,
    constraint pk_event_applicants_no primary key(no),
    constraint fk_event_applicants_member_id foreign key(member_id) references member(member_id) on delete cascade,
    constraint fk_event_applicants_event_no foreign key(event_no) references event(no) on delete cascade
);
create sequence seq_event_app_no;

--이벤트 참가시 필요한 파일
create table event_app_att(
no number,
original_filename varchar2(255) , 
renamed_filename varchar2(255), 
event_no number,
constraint pk_event_app_att_no primary key(no),
constraint fk_event_app_att_no foreign key(event_no) references event_applicants(no) on delete cascade
);

create sequence seq_eventappatt_no;

---===============================================================================
--노하우 게시판 테마
create table community_know_how_theme(
    theme_category_no number not null,
    theme_name varchar2(20) not null,
    
    constraint pk_community_know_how_theme_no primary key(theme_category_no)
);

--노하우 게시판 테마 시퀀스
create sequence seq_community_know_how_theme_no nocache;

-- 노하우 게시판
create table community_know_how(
    knowhow_board_no number,
    member_id	varchar2(50)		NOT NULL,
    nickname varchar2(100),
	theme_category_no	number		NOT NULL,  --15~17
	content	varchar2(1000),
	read_count	number	DEFAULT 0	NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
    title varchar2(150),
    cover_photo varchar2(255),  -- 목록 조회시 썸네일용
    constraint pk_community_know_how_no primary key(knowhow_board_no),
    constraint fk_community_know_how_member_id foreign key(member_id) references member(member_id),
    constraint fk_community_know_how_theme_category_no foreign key(theme_category_no) references community_know_how_theme(theme_category_no)
);

-- 노하우 게시판 테이블 시퀀스 코드
create sequence seq_community_know_how_no nocache;

--노하우 댓글
CREATE TABLE knowhow_reply(
	comment_no	number,
    comment_level number default 1,
    comment_ref number,
	knowhow_board_no	number,
	member_id	varchar2(50),
    nickname varchar2(1000),
	content	varchar2(100),
	like_count	number	DEFAULT 0,
	reg_date	date	DEFAULT sysdate, 
    constraint pk_knowhow_reply_comment_no primary key(comment_no),
    constraint fk_knowhow_reply_knowhow_board_no foreign key(knowhow_board_no) references community_know_how(knowhow_board_no)  on delete cascade,
    constraint fk_knowhow_reply_member_id foreign key(member_id) references member(member_id)
);

create sequence seq_knowhow_reply_no nocache;

--노하우 첨부파일
create table knowhow_attachment(
    no number,
    knowhow_no number not null,
    original_filename varchar2(255) not null,
    renamed_filename varchar2(255) not null,
    
    constraint pk_knowhow_attachment_no primary key(no),
    constraint fk_qa_knowhow_no foreign key(knowhow_no) references community_know_how(knowhow_board_no) on delete cascade
);
--노하우 첨부파일 시퀀스
create sequence seq_knowhow_attach_no;


-- 커뮤니티 사진 테이블
CREATE TABLE community_image (
	community_img_no number 	NOT NULL,
	member_id	varchar2(50)		NOT NULL,
    nickname varchar2(100),
	title	varchar2(30)		NULL,
	content	varchar2(3000)		NULL,
	read_count	number	DEFAULT 0	NULL,
	like_count	number	DEFAULT 0	NULL,
	reg_date	date	DEFAULT sysdate	NULL,
    constraint pk_community_community_img_no primary key(community_img_no),
    constraint fk_community_member_id foreign key(member_id) references member(member_id)
);
-- 커뮤니티 사진 테이블 시퀀스 코드
create sequence seq_community_image_no nocache;

-- 사진 게시판 첨부파일 테이블
CREATE TABLE community_image_attachment (
	attach_no	number		NOT NULL,
	community_img_no 	number		NOT NULL,
	original_filename	varchar2(255)		NULL,
	renamed_filename	varchar2(255)		NULL,
	reg_date	date	DEFAULT sysdate	,
    constraint pk_community_image_attachment_no primary key(attach_no),
    constraint fk_community_image_attachment_img_no foreign key(community_img_no) references community_image(community_img_no)
);
-- 커뮤니티 사진 첨부파일 테이블 시퀀스 코드
create sequence seq_community_image_attachment_no nocache;


-----------좋아요(노하우게시글 용)------------
create table clike(
        member_id varchar2(50),
        no number,
        likeit varchar2(20),
    
    constraint pk_clikeit primary key(likeit),
    constraint fk_clike_member_id foreign key(member_id) references member(member_id) on delete cascade,
    constraint fk_clike__no foreign key(no) references community_know_how(knowhow_board_no) on delete cascade    
);

-----------------------------------------


commit;

------------------------------------------
-- test table
------------------------------------------
select * from cart_test;
insert into cart_test values (2, 'honggd', 'product3', 3);

commit;
select * from product_test;
desc product_test;
select * from product_test where product_id = 'product1';



