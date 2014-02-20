create user 'training'@'%' identified by 'rootpass';
create user 'training'@'localhost' identified by 'rootpass';

create database traininghandler;
create database db_bootstrap;

grant all on traininghandler.* to training;
grant all on db_bootstrap.* to training;