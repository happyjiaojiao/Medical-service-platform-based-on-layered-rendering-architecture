create table image
(
    id    int auto_increment,
    image mediumblob null,
    constraint id_UNIQUE
        unique (id)
);

alter table image
    add primary key (id);

create table user
(
    id                int auto_increment,
    name              varchar(45) default '用户'                               null,
    sex               enum ('male', 'female')                                null,
    email             varchar(45)                                            null,
    wechat_id         varchar(45)                                            null,
    head_portrait_id  int                                                    null,
    password          varchar(255)                                           null,
    registration_time datetime    default CURRENT_TIMESTAMP                  not null,
    phone             varchar(45)                                            null,
    type              enum ('user', 'medical_institutions', 'administrator') null,
    status            varchar(45)                                            null,
    constraint email_UNIQUE
        unique (email),
    constraint idUser_UNIQUE
        unique (id),
    constraint wechat_id_UNIQUE
        unique (wechat_id)
);

alter table user
    add primary key (id);


