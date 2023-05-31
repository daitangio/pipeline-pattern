create sequence s_surf start with 1 increment by 50;
create table surf_guy (id bigint not null, name varchar(255), surname varchar(255), primary key (id));
create table t_guy_owned_table (surf_guy_id bigint not null, owned_tables_id bigint not null);
create table t_surf (id bigint not null, length_cm integer not null, table_name varchar(255) not null, primary key (id));
alter table t_guy_owned_table add constraint UK_j5bwb9gg5q9g339v4cw5y6kn3 unique (owned_tables_id);
alter table t_guy_owned_table add constraint FKnke0eoard2as1k7wt2t2dudh1 foreign key (owned_tables_id) references t_surf;
alter table t_guy_owned_table add constraint FKj8r89s9ui2ruk8vp93eecbp15 foreign key (surf_guy_id) references surf_guy;
