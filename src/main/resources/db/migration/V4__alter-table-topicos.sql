alter table topicos add column autor_id bigint not null;
alter table topicos add  constraint fk_topicos_autor_id foreign key (autor_id) references usuarios(id);