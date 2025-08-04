alter table resposta add column topico_id bigint not null;
alter table resposta add constraint fk_resposta_topico_id foreign key(topico_id) references topicos(id) on delete cascade;