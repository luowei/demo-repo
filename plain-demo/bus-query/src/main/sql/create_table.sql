use mytest;
drop table car;
drop table cartype;

create table cartype(
id int primary key auto_increment not null,
name varchar(20)
);

show tables;
desc cartype;

create table car(
id int primary key auto_increment not null,
name varchar(20),
remark varchar(200),
type_id int
);

show tables;
desc car;

alter table car add constraint type_fk foreign key(type_id)
references cartype(id);

desc cartype;

#drop table car;
#drop table cartype;

insert into cartype(id,name) values(1,'9路');

insert into car(name,remark,type_id)
values('9路','火车站,长岛饭店', 1);

select * from cartype;
select * from car;

show variables like 'port';