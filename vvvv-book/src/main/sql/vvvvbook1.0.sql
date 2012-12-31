use master
go
if exists(select * from sysdatabases where name='vvvvbook')
	drop database vvvvbook
go
create database vvvvbook
go
use vvvvbook
go
--树形结构设计
--parentId 0
--小说 0 id 1
--1 id 2
--外国小说 1 id 3
--中国小说 1 id 4
create table bookType(
	bookTypeId int identity primary key,
	parentId int not null,
	bookTypeName nvarchar(20) not null,
	isDelete int not null,--1表示可用的 2表示删除的
	context nvarchar(40)
)
go
alter table bookType add constraint DF_isdelete default(1) for isdelete
go
create table bookInfo(	--图书信息
	bookId int identity primary key,
	bookName nvarchar(20) not null,
	booktypeId int not null,
	pbName nvarchar(20) not null,
	author nvarchar(20) not null,
	context nvarchar(40),
	smallImg nvarchar(20),
	bigImg nvarchar(20),
	price money not null,
	pbdate datetime not null,
	bookStatus int not null,--1未上架 2已上架  3不可用
	vvvvprice money not null,
	--saleNums bigint not null	--累积销售量
)
go
alter table bookInfo
	add constraint FK_booktypeId
	foreign key(booktypeId) references bookType(booktypeId)
alter table bookInfo add constraint DF_smallImg default('nullsmall.png') for smallImg
alter table bookInfo add constraint DF_bigImg default('nullImg.png') for bigImg
alter table bookInfo add constraint DF_bookStatus default(1) for bookStatus

go
insert into booktype(parentId,bookTypeName,context) values(0,N'小说',N'小说')
insert into booktype(parentId,bookTypeName,context) values(1,N'中国小说',N'中国小说')
insert into booktype(parentId,bookTypeName,context) values(1,N'外国小说',N'外国小说')
insert into booktype(parentId,bookTypeName,context) values(2,N'爱情小说',N'爱情小说')
insert into booktype(parentId,bookTypeName,context) values(2,N'科幻小说',N'科幻小说')
insert into booktype(parentId,bookTypeName,context) values(2,N'武侠小说',N'武侠小说')
insert into booktype(parentId,bookTypeName,context) values(0,N'科技',N'科技')
insert into booktype(parentId,bookTypeName,context) values(7,N'计算机',N'计算机')
go
select * from booktype
--drop table booktype
--update bookType set parentId=1,bookTypeName='vvvv',isDelete=1,context='vvvv' where bookTypeId=9
go
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
	values(N'神雕侠侣',6,N'人民电视出版社',N'金庸',N'神雕侠侣,非常好！',default,default,49,'2009/04/01',39)
go
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
	values(N'侠客行',6,N'人民电视出版社',N'金庸',N'侠客行,非常好！',default,default,49,'2009/04/01',39)
go
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
	values(N'易学设计模式',8,N'人民邮电出版社',N'郭志学',N'易学——设计模式,非常好！',default,default,49,'2009/04/01',39)
go
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
	values(N'数据结构(java语言版)',8,N'清华大学出版社',N'黄国瑜',N'数据结构(java语言版),非常好！',default,default,32,'2006/01/01',22)
go
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
	values(N'数据结构(C语言版)',8,N'清华大学出版社',N'严蔚敏',N'数据结构(C语言版),特别好！',default,default,30,'2008/03/01',20)
go
select * from bookInfo
--drop table bookInfo

