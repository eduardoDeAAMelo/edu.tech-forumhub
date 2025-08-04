create table resposta(

    id bigint not null auto_increment,
    mensagem text not null,
    data_criacao datetime not null,
    solucao boolean,
    usuario_id bigint not null,

    primary key(id),
    constraint fk_resposta_usuario_id foreign key(usuario_id) references usuarios(id)

);