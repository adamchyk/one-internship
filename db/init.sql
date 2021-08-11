create table users (
    id              int         not null primary key,
    username        varchar(20) not null,
    password        varchar(20) not null,
    is_admin        boolean,
    account_enabled boolean
);

create table categories (
    category_id int not null primary key,
     owner_id int not null,
     name varchar(20)
);

create table notes (
    note_id int not null primary key,
    category_id int ,
    owner_id int ,
    note text
);