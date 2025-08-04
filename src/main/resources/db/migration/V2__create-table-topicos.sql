create table topicos(

    id bigint not null auto_increment,
    titulo text not null,
    mensagem varchar(255) not null,
    data_criacao datetime not null,
    status varchar(50) not null,
    curso varchar(100) not null,

    primary key(id)
);