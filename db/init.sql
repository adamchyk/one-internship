create table users (
    id              int         not null primary key,
    username        varchar(20) not null,
    password        varchar(20) not null,
    is_admin        boolean not null,
    account_enabled boolean not null
);

create table categories (
    category_id int not null primary key,
     owner_id int not null,
     name varchar(20)
);

create table notes (
    note_id int not null primary key,
    category_id int not null,
    owner_id int not null,
    note text
    Primary key (id)
    CONSTRAINT fk_categories
    Foreign key (owner_id) REFERENCES notes(owner_id)
    Foreign key (owner_id) REFERENCES categories (owner_id)
    ON DELETE CASCADE;

    Primary key (category_id)
    CONSTRAINT fk_notes
    Foreign key (category_id) REFERENCES notes (category_id)
    ON DELETE CASCADE
);

