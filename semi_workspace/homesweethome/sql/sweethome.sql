commit;

select * from member;

select * from address;

select * from main_category;
 
select * from sub_category;

select * from brand;

select * from product p left join brand b on p.brand_id = b.brand_id where p.main_code like 'furniture';

select * from cs_email_type;

select * from cs_email_image;

 select * from product_stock;
 select * from product where main_code = 'organizing';
 update product set main_code = 'electronics' where product_id = 'product1';
 commit;
select * from product_io order by io_datetime desc;

select * from today_deal;

select * from product_description_image;
select * from product_image;

delete from product where sub_code = 'table_chair';
delete from product where product_id = 'organizing_module모던 리빙박스';


commit;
select * from today_deal;
insert into today_deal values (seq_today_deal_no.nextval, 'lighting_oa무드등 타이머 높이조절 캔들워머', 33);
insert into today_deal values (seq_today_deal_no.nextval, 'furniture_samik델루나 LED 프리미엄 수납 호텔 침대', 41);
insert into today_deal values (seq_today_deal_no.nextval, 'organizing_item_dearliving논슬립 바지걸이 20개', 61);
insert into today_deal values (seq_today_deal_no.nextval, 'living_cottonliving코마사 수건', 61);
select * from today_deal;
select p.*, b.brand_name, m.main_category_name, s.sub_category_name, t.discount_rate  from ( select row_number() over(order by reg_date desc) rnum, p.* from  product p ) p left join today_deal t  on p.product_id = t.product_id  left join brand b on p.brand_id = b.brand_id  left join main_category m on p.main_code = m.main_code left join sub_category s on p.sub_code = s.sub_code where  p.product_id like t.product_id; 



