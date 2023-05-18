use mysql;

create user 'jspuser'@'localhost' identified by 'mysql';
grant all privileges on jspdb.* to 'jspuser'@'localhost' with grant option;
flush privileges;

create database jspdb;

--2023-05-12--
create table member(
mno int auto_increment primary key,
id varchar(50) not null,
password varchar(50) not null,
name varchar(20) not null,
age int,
email varchar(100) not null,
addresss varchar(50) not null,
reg_date datetime default now(),
last_login datetime default null
)