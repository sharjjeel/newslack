create table threads (
    thread_name varchar(100) PRIMARY KEY,
    ts varchar(100)
);

create table messages (
    message_id varchar(100) PRIMARY KEY,
    user_name varchar(100) not null,
    text text not null,
    ts varchar(100) not null,
    thread_name varchar(100) REFERENCES threads(thread_name)
);
create index user_messages on messages (user);
create index thread_messages on messages (thread_name);