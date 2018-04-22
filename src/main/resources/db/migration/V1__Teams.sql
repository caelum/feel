create table teams (
  id bigint not null auto_increment,
  name varchar(255) not null,
  total_expected_people int not null,

  constraint PK_teams primary key (id),
  constraint CK_name_not_empty check (name <> ''),
  constraint CK_total_not_negative check (total_expected_people > 0)
);

insert into teams(name, total_expected_people) values('Caelumweb', 10);
insert into teams(name, total_expected_people) values('Casa do código', 10);
insert into teams(name, total_expected_people) values('Comercial BSB', 10);
insert into teams(name, total_expected_people) values('Comercial Rio', 10);
insert into teams(name, total_expected_people) values('Customer Success', 10);
insert into teams(name, total_expected_people) values('Design', 10);
insert into teams(name, total_expected_people) values('Dev Alura', 10);
insert into teams(name, total_expected_people) values('Financeiro', 10);
insert into teams(name, total_expected_people) values('Instrutor Alura e Blog', 10);
insert into teams(name, total_expected_people) values('Instrutor Alura Design Rio', 10);
insert into teams(name, total_expected_people) values('Instrutor Caelum SP e Infra', 10);
insert into teams(name, total_expected_people) values('Instrutor Caelum BSB', 10);
insert into teams(name, total_expected_people) values('Instrutor Dev e Business Rio (Caelum e Alura)', 10);
insert into teams(name, total_expected_people) values('Marketing (Social e Inbound)', 10);
insert into teams(name, total_expected_people) values('Musicdot', 10);
insert into teams(name, total_expected_people) values('Revisão Alura', 10);
insert into teams(name, total_expected_people) values('Transcrição', 10);
insert into teams(name, total_expected_people) values('Vendas PF', 10);
insert into teams(name, total_expected_people) values('Vendas PJ', 10);
insert into teams(name, total_expected_people) values('Vídeos', 10);