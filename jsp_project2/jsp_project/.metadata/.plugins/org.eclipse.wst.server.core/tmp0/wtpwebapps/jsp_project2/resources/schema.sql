create database jspdb;

create user 'jspuser'@'localhost' identified by 'mysql' ;

grant all privileges on javadb.* to 'jspuser'@'localhost' with grant option;

flush privileges;


use jspdb;

-- 2023-05-12
create table member(
id varchar(100) not null,
password varchar(100) not null,
email varchar(100) not null,
age int,
reg_date datetime default now(),
last_login datetime default null);

-- 2023-05-16
create table board(
bno int primary key auto_increment,
title varchar(100) not null,
writer varchar(100) not null,
write_date datetime default now(),
post text);

