DROP TABLE IF EXISTS manager;
create table manager
(
    id serial not null,
    account varchar(64),
    password varchar(512),
    domain varchar(64),
    updated_date timestamp,
    created_date timestamp,
        primary key (id)
);

grant usage on SEQUENCE manager_id_seq TO myUser;
grant select,update,insert,delete on table manager TO myUser;