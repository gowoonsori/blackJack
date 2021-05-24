create table free_hes
(
    name  varchar(45) not null
        primary key,
    money bigint      null
);
 

-- 랜덤 유저 정보 생성
insert into free_hes (name, money) values ('hes', 570000);
insert into free_hes (name, money) values ('hi', 4230000);
insert into free_hes (name, money) values ('홍길동', 12500000);

-- table 삭제
drop table free_hes; 
