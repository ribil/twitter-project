drop table if exists hibernate_sequence;
drop table if exists message;
drop table if exists message_retweets;
drop table if exists reply_pool;
drop table if exists user_role;
drop table if exists usr;
create table hibernate_sequence (next_val bigint) engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table message (
    id integer not null,
    date datetime,
    text varchar(255),
    user_id integer,
    replypool_id integer,
    primary key (id)
    ) engine=MyISAM;

create table message_retweets (
    user_id integer not null,
    message_id integer not null,
    primary key (user_id, message_id)
    ) engine=MyISAM;

create table reply_pool (
    id integer not null, primary key (id)
    ) engine=MyISAM;

create table user_role (
    user_id integer not null,
    roles varchar(255)
    ) engine=MyISAM;

create table usr (
    id integer not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)) engine=MyISAM;

alter table message add constraint FK70bv6o4exfe3fbrho7nuotopf
foreign key (user_id) references usr (id);

alter table message add constraint FK97d976ywoukdmcg21hib6ivem
foreign key (replypool_id) references reply_pool (id);

alter table message_retweets add constraint FKf96rk1p5g60wh9jjievmnn0nq
foreign key (message_id) references message (id);

alter table message_retweets add constraint FK51hvvcw6vbkufsyikct8dle4h
foreign key (user_id) references usr (id);

alter table user_role add constraint FKfpm8swft53ulq2hl11yplpr5
foreign key (user_id) references usr (id);