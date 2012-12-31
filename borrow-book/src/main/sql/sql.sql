create table book(
id number primary key,
name varchar2(50) not null,
author varchar2(50) not null,
img varchar2(100),
detail varchar2(1500)
)

--drop table  book

create table customer(
id number primary key,
name varchar2(50) not null,
password varchar2(50) not null,
zip varchar2(100),
address varchar2(200),
telephone varchar2(50),
email varchar2(100));

--drop table  customer

create table brwBook(
id number primary key,
brwBookDate date,
customer_id references customer(id));

--drop table  brwBook

create table brwlist(
id number primary key,
book_id number references book(id),
brwBook_id number references brwBook(id),
num number not null);

--drop table  orderline

create sequence brwBook_seq;
--drop sequence order_seq;

create sequence brwlist_seq;
--drop sequence orderLine_seq;

create sequence customer_seq;


select * from customer
--delete from customer

insert into book values(1,'Java编程思想','埃史尔','big019.png','包含了Java语言基础语法以及高级特性');
insert into book values(2,'冰心散文集','冰心','big001.png','小桔灯是冰心散文集里的一篇文章');
insert into book values(3,'徐志摩散文','徐志摩','big002.png','轻轻的我走了');
insert into book values(4,'JAVA核心技术','昊斯特曼','big020.png','与《Java编程思想》齐名的Java图书泰山北斗');
insert into book values(5,'易学设计模式','郭志学','big026.png','易学——设计模式,非常好！');
insert into book values(6,'数据结构(java语言版)','黄国瑜','big027.png','数据结构(java语言版),非常好！');

select * from book

--delete from book;
