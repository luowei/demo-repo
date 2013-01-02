--=============================================
-----------------sql server--------------------
--run:
--sqlcmd -U sa -P luowei -d master


create database test_user;

use test_user;

create table users(
	useId int primary key identity(1,1),--用户id号
	username varchar(20),
	passwd varchar(20),
	email varchar(30),
	grade int --用户的级别
	);

insert into users(username,passwd,email,grade) values("admin","admin","admin@sohu.com","1");
insert into users(username,passwd,email,grade)  values("luowei","luowei","luowei@163.com","1");
insert into users(username,passwd,email,grade)  values("test1","test1","test1@sina.com","5");
insert into users(username,passwd,email,grade)  values("test2","test2","test2@sina.com","5");

select * from users;

--exit

--==============================================
--------------------mysql-----------------------
run: mysql -u root -p
Enter password:root

create database test_user;

use test_user;

create table users(
	useId int primary key AUTO_INCREMENT,#用户id号
	username varchar(20),
	passwd varchar(20),
	email varchar(30),
	grade int #用户的级别
	);

insert into users values(null,"admin","admin","admin@sohu.com","1");
insert into users values(null,"luowei","luowei505050","luowei@163.com","1");
insert into users values(null,"test1","test1","test1@sina.com","5");
insert into users values(null,"test2","test2","test2@sina.com","2");
insert into users values(null,"test3","test3","test3@sina.com","5");
insert into users values(null,"test4","test4","test4@sina.com","6");
insert into users values(null,"test5","test5","test5@sina.com","6");
insert into users values(null,"test6","test6","test6@sina.com","7");
insert into users values(null,"test7","test7","test7@sina.com","7");
insert into users values(null,"test8","test8","test8@sina.com","8");
insert into users values(null,"test9","test9","test9@sina.com","8");
insert into users values(null,"test10","test10","test10@sina.com","9");
insert into users values(null,"test11","test11","test11@sina.com","3");
insert into users values(null,"test12","test12","test12@sina.com","4");

select * from users;

insert into users(username,passwd,email,grade) select username,passwd,email,grade from users;

select  * from users where username=admin and passwd=admin;

#添加新列
alter table users add myPic varchar(30);

#修改数据
update users set myPic='luowei.gif' where userName='luowei';
update users set myPic='admin.gif' where userName='admin';
update users set myPic='test1.gif' where userName='test1';

#drop table users;