--2023-05-17
사용 db : javadb
id : javauser

create table member(
id varchar(100) not null,
password varchar(100) not null,
email varchar(100) not null,
age int default 0,
reg_date datetime default now(),
last_login datetime default null);

create table board(
bnum int primary key auto_increment,
title varchar(100) not null,
writer varchar(100) not null,
write_date datetime default now(),
post text,
count int default 0);
