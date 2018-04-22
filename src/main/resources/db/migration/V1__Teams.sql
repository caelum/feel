create table teams (
  id bigint not null auto_increment,
  name varchar(255) not null,

  constraint PK_teams primary key (id),
  constraint CK_name_not_empty check (name <> '')
);

insert into teams(name) values('Caelumweb');
insert into teams(name) values('Casa do código');
insert into teams(name) values('Comercial BSB');
insert into teams(name) values('Comercial Rio');
insert into teams(name) values('Customer Success');
insert into teams(name) values('Design');
insert into teams(name) values('Dev Alura');
insert into teams(name) values('Financeiro');
insert into teams(name) values('Instrutor Alura e Blog');
insert into teams(name) values('Instrutor Alura Design Rio');
insert into teams(name) values('Instrutor Caelum SP e Infra');
insert into teams(name) values('Instrutor Caelum BSB');
insert into teams(name) values('Instrutor Dev e Business Rio (Caelum e Alura)');
insert into teams(name) values('Marketing (Social e Inbound)');
insert into teams(name) values('Musicdot');
insert into teams(name) values('Revisão Alura');
insert into teams(name) values('Transcrição');
insert into teams(name) values('Vendas PF');
insert into teams(name) values('Vendas PJ');
insert into teams(name) values('Vídeos');