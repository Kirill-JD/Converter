create sequence hibernate_sequence start 1 increment 1;

create table currency (
    id int8 not null,
    char_code varchar(255),
    date varchar(255),
    name varchar(255),
    value varchar(255),
    valute_id varchar(255),
    primary key (id)
);

create table history (
    id int8 not null,
    amount varchar(255),
    date varchar(255),
    result varchar(255),
    source int8,
    target int8,
    user_id int8,
    primary key (id)
);

create table usr (
    id int8 not null,
    active boolean not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

alter table if exists history
    add constraint history_source_currency_fk
    foreign key (source) references currency;

alter table if exists history
    add constraint history_target_currency_fk
    foreign key (target) references currency;

alter table if exists history
    add constraint history_user_fk
    foreign key (user_id) references usr;
