alter table users
add constraint uc_users_username unique (username);