
DROP SCHEMA my_bank IF EXISTS;

CREATE SCHEMA my_bank;

create table my_bank.USER (
    id uuid not null primary key,
    name varchar(100) not null,
    last_name varchar(100) not null,
    document_type varchar(50) not null,
    document_id varchar(50) not null,
    email varchar(100) not null,
    birth_day date,
    gender char
);

create table my_bank.ACCOUNT (
    id varchar(100) not null primary key,
    user_id uuid not null,
    balance double not null,
    currency varchar(3),
    active boolean,
    foreign key (user_id) references USER(id)
);

create table my_bank.TRANSFER(
 id uuid not null primary key,
 source_account_id varchar(100),
 target_account_id varchar(100),
 amount double,
 date_time timestamp,
 status varchar(50),
 foreign key (source_account_id) references ACCOUNT(id),
 foreign key (target_account_id) references ACCOUNT(id)
)