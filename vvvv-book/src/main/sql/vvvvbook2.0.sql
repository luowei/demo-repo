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
-------------------图书类别表------------------------------
create table bookType(		
	bookTypeId int identity primary key,
	parentId int not null,
	bookTypeName nvarchar(20) not null,
	isDelete int not null,--1表示可用的 2表示删除的
	context nvarchar(40)
)
go
--drop table bookType 
go
alter table bookType add constraint DF_isdelete default(1) for isdelete
go
---------------------图书信息-------------------------------
create table bookInfo(	
	bookId int identity primary key,
	bookName nvarchar(20) not null,
	bookTypeId int not null,
	pbName nvarchar(20) not null,
	author nvarchar(20) not null,
	context nvarchar(4000),
	smallImg nvarchar(50),
	bigImg nvarchar(50),
	price money not null,
	pbdate datetime not null,
	bookStatus int not null,--1未上架 2已上架  3不可用
	vvvvprice money not null,
	--saleNums bigint not null	--累积销售量
)
go
--drop table bookInfo
go
alter table bookInfo	
	add constraint FK_bookTypeId
	foreign key(bookTypeId) references bookType(bookTypeId)
alter table bookInfo add constraint DF_smallImg default('nullsmall.png') for smallImg
alter table bookInfo add constraint DF_bigImg default('nullImg.png') for bigImg
alter table bookInfo add constraint DF_bookStatus default(1) for bookStatus

go
------------------用户基本信息表------------------
create table customerInfo(		
	customerId int identity primary key,
	customerName nvarchar(20) not null,
	pwd varchar(20)not null,
	email varchar(20) not null
)
go
--drop table customerInfo
go
------------------用户详细信息表------------------
create table customerDetailInfo(		
	customerId int not null primary key,
	tel varchar(13) ,
	address nvarchar(20) ,
	sex nvarchar(10),
	age int,
	countMoney money,
	qq varchar(12)
)
go
--drop table customerDetailInfo
go
alter table customerDetailInfo add constraint FK_customerId
	foreign key(customerId) references customerInfo(customerId)
go
alter table customerDetailInfo add constraint DK_countMoney
	default (0) for countMoney
go
------------------书评表------------------
create table bookComment(		
	commentId int identity primary key , 
	bookId int not null,
	customerId int not null ,
	context nvarchar(300) not null, 
	Status int not null       --1, 2书评状态，审核后才可见
)
go
--drop table bookComment
go
alter table bookComment add constraint FK_bookDiscuss_bookId
    FOREIGN KEY(bookId) REFERENCES bookInfo(bookId)
go
alter table bookComment add constraint FK_bookDiscuss_customerId
    FOREIGN KEY(customerId) REFERENCES customerInfo(customerId)
go  
------------------库存表------------------
create table bookStock(			
	stockId  int identity primary key ,
	bookId   int not null ,
	bookCount    int ,
	minNum   int not null 
)
go
--drop table bookStock
go
alter table bookStock add constraint FK_bookStock_bookId
    FOREIGN KEY(bookId) REFERENCES bookInfo(bookId)  
go
------------------管理员表------------------
create table sysAdmin(			
	adminId int identity primary key ,
	adminName nvarchar(20) not null ,
	pwd varchar(20) , 
	adminType int not null          --管理员级别
)
go
--drop table sysAdmin
go
------------------收藏夹表------------------
create table favorite(				
	favoriteId int identity primary key ,
	bookId int not null,
	customerId int not null ,
	date datetime not null,
	context nvarchar(50) 
)
go
--drop table favorite
go
alter table favorite add constraint FK_favorite_bookId
    FOREIGN KEY(bookId) REFERENCES bookInfo(bookId)
go
alter table favorite add constraint FK_favorite_customerId
    FOREIGN KEY(customerId) REFERENCES customerInfo(customerId)
go
----------------------------------向图书类别表插入数据--------------------------------
--drop table booktype
--update bookType set parentId=1,bookTypeName='vvvv',isDelete=1,context='vvvv' where bookTypeId=9
insert into booktype(parentId, bookTypeName, context) values(0,N'小说',N'小说')	--1
insert into booktype(parentId, bookTypeName, context) values(1,N'中国小说',N'中国小说')	--2
insert into booktype(parentId, bookTypeName, context) values(1,N'外国小说',N'外国小说')	--3
insert into booktype(parentId, bookTypeName, context) values(2,N'爱情小说',N'爱情小说')	--4
insert into booktype(parentId, bookTypeName, context) values(2,N'散文小说',N'科幻小说')	--5
insert into booktype(parentId, bookTypeName, context) values(2,N'小小小说',N'小小小说')	--6
insert into booktype(parentId, bookTypeName, context) values(2,N'现实小说',N'现实小说')	--7
insert into booktype(parentId, bookTypeName, context) values(2,N'文化小说',N'文化小说')	--8
insert into booktype(parentId, bookTypeName, context) values(0,N'科技',N'科技')	--9
insert into booktype(parentId, bookTypeName, context) values(7,N'计算机',N'计算机')	--10
insert into booktype(parentId, bookTypeName, context) values(7,N'医学',N'现代医学理论')	--11
go
select * from booktype
go
------------------------------向图书信息表插入数据---------------------------------
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'暂无此书',11,N'暂无此书',N'暂无此书',N'暂无此书',N'nothisbookSmall.png',N'nothisbookBig.png',0,'2000/01/01',0)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'冰心散文集',4,N'太白文艺出版社',N'冰心',N'小桔灯',N'bingxin1.png',N'bingxin2.png',13.80,'2008/10/1',10)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'徐志摩散文',4,N'太白文艺出版社',N'徐志摩',N'轻轻的我走了',N'xuzhimo1.png',N'xuzhimo2.png',13.20,'1950/1/1',10)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'围城',5,N'生活.读书.新知三联书店',N'钱钟书',N'不错',N'weicheng1.png',N'weicheng2.png',14.00,'2002/5/1',8.00)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'27楼204',6,N'天涯出版社',N'孔庆东',N'不错',N'27楼2041.png',N'27楼2042.png',50,'2011/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'美国说客',5,N'中国商业出版社',N'弗·李·贝利 ',N'不错',N'美国说客1.png',N'美国说客1.png',50,'2011/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'支离破碎',5,N'华夏出版社',N'石康',N'不错',N'支离破碎1.png',N'支离破碎2.png',21.80 ,'2004/1/1',13.30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'动物凶猛',5,N'云工厂出版社',N'王朔',N'不错',N'动物凶猛1.png',N'动物凶猛2.png',50,'2006/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'三重门',6,N'万卷出版公司',N'韩寒',N'不错',N'三重门1.png',N'三重门2.png',50,'2009/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'铁屋中的呐喊《动物庄园》',6,N'上海三联书店',N'余杰',N'不错',N'动物庄园1.png',N'动物庄园2.png',50,'2011/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'圈子圈套',5,N'清华大学出版社',N'王强',N'软件相关产品销售',N'圈子圈套1.png',N'圈子圈套2.png',50,'2011/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'做单',5,N'云工厂出版社',N'王强',N'软件相关产品销售',N'做单1.png',N'做单2.png',28.00,'2011/1/1',20)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'黑道风云20年',6,N'重庆出版社',N'孔二狗',N'讲述的黑道故事',N'黑道风云20年1.png',N'黑道风云20年2.png',28.00 ,'2009/5/1',14.80)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'明朝那些事',7,N'中国友谊出版公司',N'当年明月',N'从朱元璋的出身到永乐大帝夺位',N'明朝那些事1.png',N'明朝那些事2.png',50,'2009/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'文化苦旅',8,N'东方出版中心',N'余秋雨',N'余秋雨教授第一本文化散文集',N'文化苦旅1.png',N'文化苦旅2.png',22.00 ,'2011/1/1',16.00 )
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'八月未央',8,N'作家出版社',N'安妮宝贝',N'安妮宝贝第一本小说散文集',N'八月未央1.png',N'八月未央.png',25.00,N'2011/1/1',16.30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'基督山伯爵',8,N'上海译文出版社',N'大仲马',N'化身为神秘的基督山伯爵，踏人巴黎上流社会',N'基督山伯爵1.png',N'基督山伯爵2.png',59.00,'2011/1/1',38.40 )
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'三个火枪手',8,N'上海译文出版社',N'大仲马',N'达尔大尼央来到巴黎后加入了国王路易十三的火枪队',N'三个火枪手1.png',N'三个火枪手2.png',30.00,'2011/1/1',24.00)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'java程序设计',10,N'机械工业出版社',N'Y.Daniel Liang ',N'介绍了语法结构、面向对象程序设计基础',N'java程序设计',N'java程序设计',66.00,'2011/1/1',49.00)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'Java编程思想',10,N'机械工业出版社',N'埃史尔 ',N'包含了Java语言基础语法以及高级特性',N'Java编程思想1.png',N'Java编程思想2.png',108.00,'2011/1/1',76.70 )
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'JAVA核心技术',10,N'机械工业出版社',N'昊斯特曼 ',N'与《Java编程思想》齐名的Java图书泰山北斗',N'JAVA核心技术1.png',N'JAVA核心技术2.png',98.00,'2011/1/1',67.00)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'疯狂Java讲义',10,N'电子工业出版社',N'李刚',N'从解决问题的角度来介绍Java语言',N'疯狂Java讲义1.png',N'疯狂Java讲义2.png',50,'2008/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'Effective Java中文版',10,N'机械工业出版社',N'Joshua Bloch ',N'容全面，结构清晰，讲解详细',N'Effective1.png',N'Effective2.png',50,'2011/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'Java 面向对象编程',10,N'电子工业出版社',N'孙卫琴 ',N'详细讲解Java面向对象的编程思想、编程语法和设计模式',N'Java 面向对象编程','NJava 面向对象编程',50,'2011/1/1',30)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'神雕侠侣',6,N'人民电视出版社',N'金庸',N'神雕侠侣,非常好！',N'神雕侠侣1.png',N'神雕侠侣2.png',49,'2009/04/01',39)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'侠客行',6,N'人民电视出版社',N'金庸',N'侠客行,非常好！',N'侠客行1.png',N'侠客行2.png',49,'2009/04/01',39)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'易学设计模式',10,N'人民邮电出版社',N'郭志学',N'易学——设计模式,非常好！',N'yixuexhejimoshi1.png',N'yixuexhejimoshi2.png',49,'2009/04/01',39)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'数据结构(java语言版)',10,N'清华大学出版社',N'黄国瑜',N'数据结构(java语言版),非常好！',N'数据结构(java语言版)1.png',N'数据结构(java语言版)2.png',32,'2006/01/01',22)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'数据结构(C语言版)',10,N'清华大学出版社',N'严蔚敏',N'数据结构(C语言版),特别好！',N'数据结构(C语言版)1.png',N'数据结构(C语言版)2.png',30,'2008/03/01',20)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'临床医技学',11,N'广东高等教育出版社',N'周伟生',N'《临床医技学》是一门应用现代医疗技术诊断和治疗疾病的临床学科！',N'临床医技学1.png',N'临床医技学2.png',30,'2007/08/01',20)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'张聿青医案',11,N'人民卫生出版社',N'清·张乃修',N'作者临证善于辨证察色，长于脉诊、舌诊，重视四诊合参',N'张聿青医案1.png',N'张聿青医案2.png',29.00,'2006/06/01',21.80)
insert into bookInfo(bookName,bookTypeId,pbName,author,context,smallImg,bigImg,price,pbdate,vvvvprice)
values(N'黄志强临床医案100例',11,N'中国中医药出版社',N'黄志强',N'本书包含了临床医案100例！',N'黄志强临床医案100例1.png',N'黄志强临床医案100例2.png',28.00,'2009/02/01',20.00)

go
select * from bookInfo where bookName=N'暂无此书'
go
update bookInfo set smallImg='small001.png',bigImg='big001.png' where bookId=2
update bookInfo set smallImg='small002.png',bigImg='big002.png' where bookId=3
update bookInfo set smallImg='small003.png',bigImg='big003.png' where bookId=4
update bookInfo set smallImg='small004.png',bigImg='big004.png' where bookId=5
update bookInfo set smallImg='small005.png',bigImg='big005.png' where bookId=6
update bookInfo set smallImg='small006.png',bigImg='big006.png' where bookId=7
update bookInfo set smallImg='small007.png',bigImg='big007.png' where bookId=8
update bookInfo set smallImg='small008.png',bigImg='big008.png' where bookId=9
update bookInfo set smallImg='small009.png',bigImg='big009.png' where bookId=10
update bookInfo set smallImg='small010.png',bigImg='big010.png' where bookId=11
update bookInfo set smallImg='small011.png',bigImg='big011.png' where bookId=12
update bookInfo set smallImg='small012.png',bigImg='big012.png' where bookId=13
update bookInfo set smallImg='small013.png',bigImg='big013.png' where bookId=14
update bookInfo set smallImg='small014.png',bigImg='big014.png' where bookId=15
update bookInfo set smallImg='small015.png',bigImg='big015.png' where bookId=16
update bookInfo set smallImg='small016.png',bigImg='big016.png' where bookId=17
update bookInfo set smallImg='small017.png',bigImg='big017.png' where bookId=18
update bookInfo set smallImg='small018.png',bigImg='big018.png' where bookId=19
update bookInfo set smallImg='small019.png',bigImg='big019.png' where bookId=20
update bookInfo set smallImg='small020.png',bigImg='big020.png' where bookId=21
update bookInfo set smallImg='small021.png',bigImg='big021.png' where bookId=22
update bookInfo set smallImg='small022.png',bigImg='big022.png' where bookId=23
update bookInfo set smallImg='small023.png',bigImg='big023.png' where bookId=24
update bookInfo set smallImg='small024.png',bigImg='big024.png' where bookId=25
update bookInfo set smallImg='small025.png',bigImg='big025.png' where bookId=26
update bookInfo set smallImg='small026.png',bigImg='big026.png' where bookId=27
update bookInfo set smallImg='small027.png',bigImg='big027.png' where bookId=28
update bookInfo set smallImg='small028.png',bigImg='big028.png' where bookId=29
update bookInfo set smallImg='small029.png',bigImg='big029.png' where bookId=30
update bookInfo set smallImg='small030.png',bigImg='big030.png' where bookId=31
update bookInfo set smallImg='small031.png',bigImg='big031.png' where bookId=32

go
--select * from bookInfo
--update bookInfo set bookName=N'八月未央',pbName=N'作家出版社',author=N'安妮宝贝',context=N'安妮宝贝第一本小说散文集',smallImg=N'八月未央1.png'
--drop table bookInfo
select bookId,bookName,booktypeId,pbName,author,context,smallImg,bigImg,price,pbdate,
bookStatus,vvvvprice from bookInfo where booktypeId=10 and bookStatus=2
go
----------------------------向用户基本信息表插入数据----------------------------
insert into customerInfo(customerName,pwd,email) values(N'luowei','luowei','luowei505050@126.com')--1
insert into customerInfo(customerName,pwd,email) values(N'rowin','rowin','rowin010101@sina.com')--2
insert into customerInfo(customerName,pwd,email) values(N'admin','admin','admin@163.com')--3
insert into customerInfo(customerName,pwd,email) values(N'root','root','root@qq.com')--4
insert into customerInfo(customerName,pwd,email) values(N'test','test','test@sohu.com')--5
go
select * from customerInfo
go
----------------------------向用户详细信息表插入数据----------------------------
insert into customerDetailInfo(customerId,tel,address,sex,age,countMoney,qq) 
values(1,13875408888,N'湖南省娄底市娄星区',N'男',22,default,745054025)
insert into customerDetailInfo(customerId,tel,address,sex,age,countMoney,qq) 
values(2,13812345678,N'湖南省衡阳市石鼓区',N'男',52,default,123456789)
insert into customerDetailInfo(customerId,tel,address,sex,age,countMoney,qq) 
values(3,13812345678,N'湖南省长沙市雨花区',N'女',16,default,666666666)
insert into customerDetailInfo(customerId,tel,address,sex,age,countMoney,qq) 
values(4,13812345678,N'广东省广州市白云区',N'女',40,default,777777777)
insert into customerDetailInfo(customerId,tel,address,sex,age,countMoney,qq) 
values(5,13812345678,N'湖南省衡阳市雁峰区',N'男',35,default,777777777)
go
select * from customerDetailInfo
go
----------------------------向书评表插入数据----------------------------
insert into bookComment(bookId,customerId,context,Status) 
values(12,1,N'《黑道风云20年》维唯为为认为很好',1)
insert into bookComment(bookId,customerId,context,Status) 
values(13,2,N'《明朝那些事》rowin认为很直得看',2)
insert into bookComment(bookId,customerId,context,Status) 
values(19,3,N'《Java编程思想》admin告诉大家是学习Java好书',2)
insert into bookComment(bookId,customerId,context,Status) 
values(24,3,N'《神雕侠侣》很好看————root评论',1)
insert into bookComment(bookId,customerId,context,Status) 
values(23,1,N'《Java 面向对象编程》对java学习有很大的用处',1)
insert into bookComment(bookId,customerId,context,Status) 
values(26,1,N'我最看好的书就是易学设计模式《易学设计模式》',2)
insert into bookComment(bookId,customerId,context,Status) 
values(29,1,N'《临床医技学》是一本很不错的临床医学书',1)
go
select * from bookComment
go
-----------------------向库存表插入数据--------------------------------
insert into bookStock(bookId,bookCount,minNum) values(1,100,2)
insert into bookStock(bookId,bookCount,minNum) values(3,50,3)
insert into bookStock(bookId,bookCount,minNum) values(10,5,0)
insert into bookStock(bookId,bookCount,minNum) values(11,20,0)
insert into bookStock(bookId,bookCount,minNum) values(17,100,1)
insert into bookStock(bookId,bookCount,minNum) values(18,100,0)
insert into bookStock(bookId,bookCount,minNum) values(22,100,0)
insert into bookStock(bookId,bookCount,minNum) values(27,100,2)
go
insert into bookStock(bookId,bookCount,minNum) values(12,10,1)
insert into bookStock(bookId,bookCount,minNum) values(13,10,0)
insert into bookStock(bookId,bookCount,minNum) values(19,20,5)
insert into bookStock(bookId,bookCount,minNum) values(24,10,0)
insert into bookStock(bookId,bookCount,minNum) values(23,10,2)
insert into bookStock(bookId,bookCount,minNum) values(26,25,0)
insert into bookStock(bookId,bookCount,minNum) values(29,30,2)
go
-----------------------向管理员表插入数据--------------------------------
insert into sysAdmin(adminName,pwd,adminType) values('system','system',0)
insert into sysAdmin(adminName,pwd,adminType) values('admin','admin',1)
insert into sysAdmin(adminName,pwd,adminType) values('luowei','luowei',2)
insert into sysAdmin(adminName,pwd,adminType) values('test','test',3)
go
select * from sysAdmin
go

-----------------------向收藏夹表插入数据--------------------------------
insert into favorite(bookId,customerId,date,context ) values(1,1,'2011/10/16',N'luowei收藏的《冰心散文集》')
insert into favorite(bookId,customerId,date,context ) values(2,1,'2011/10/16',N'luowei收藏的《三重门》')
insert into favorite(bookId,customerId,date,context ) values(17,2,'2010/10/1',N'rowin收藏的《三个火枪手》')
go
select * from favorite
go
----===========================================================================
--分页查询
select top 3 * from bookInfo where bookId not in(
	select top 3 bookId from bookInfo where bookTypeId=0
	) and bookTypeId=8
go
--同上面的查询
select top 3 * from bookInfo  where bookTypeId=8 and
	 bookId not in(
	select top 3 bookId from bookInfo where bookTypeId=0
	)
go

























