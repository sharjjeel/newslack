create table threads (
    thread_name varchar(100) PRIMARY KEY,
    ts varchar(100)
);

create table messages (
    messageId varchar(100) PRIMARY KEY,
    user varchar(100) not null,
    text text not null,
    ts varchar(100) not null,
    thread_name varchar(100) REFERENCES threads(thread_name)
);
create index user_messages on messages (user);
create index thread_messages on messages (thread_name);