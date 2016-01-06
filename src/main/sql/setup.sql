create user 'training'@'%' identified by 'rootpass';
create user 'training'@'localhost' identified by 'rootpass';

create database seboattiladb CHARACTER SET utf8 COLLATE utf8_general_ci;
SET SQL_SAFE_UPDATES = 0;
create database db_bootstrap;

grant all on seboattiladb.* to training;
grant all on db_bootstrap.* to training;