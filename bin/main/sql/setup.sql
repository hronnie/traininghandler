create user 'training'@'%' identified by 'rootpass';
create user 'training'@'localhost' identified by 'rootpass';

create database traininghandler;

grant all on traininghandler.* to training;
