create table questions(
  id bigint not null auto_increment,
  explanation longtext not null,
  statement varchar(255) not null,
  description_of_lower_value varchar(255) not null,
  description_of_highest_value varchar(255) not null,
  due_date date not null,


  constraint PK_questions primary key (id),
  constraint CK_due_date_in_the_future check (due_date > curdate())
);