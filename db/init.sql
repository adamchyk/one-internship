create table users (
   id              int         not null primary key,
   username        varchar(20) not null,
   password        varchar(20) not null,
   is_admin        boolean not null,
   account_enabled boolean not null,
       foreign key(id) references users(id)
           on delete cascade
);

create table categories (
    category_id int not null primary key,
    owner_id int not null,
    name varchar(20)
);

create table notes
(
    note_id     int not null primary key,
    category_id int not null,
    owner_id    int not null,
    note        text,
    constraint fk_notes_categories,
    foreign key (category_id) references categories (category_id)
        on delete cascade,
    constraint fk_notes_users,
    foreign key(owner_id) references users (id)
        on delete cascade
);


