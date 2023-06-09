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

alter alter table board add count int default 0;

--2023-05-18
alter alter table board add likes int default 0;

create table likelist(
bnum int not null,
id varchar(100) not null);

alter table member add primary key(id);
alter table likelist add foreign key(bnum) references board(bnum);
alter table likelist add foreign key(id) references member(id);

--2023-05-19 댓글 table
create table comment(
cnum int auto_increment primary key,
bnum int default 0,
writer varchar(50) default '익명',
comment text,
reg_date datetime default now());

--2023-05-25 board에 imgfile 속성 추가
alter table board add column imgfile varchar(100);

