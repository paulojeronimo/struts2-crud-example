create user STRUTS2_CRUD_EXAMPLE identified by STRUTS2_CRUD_EXAMPLE default tablespace users;
alter user STRUTS2_CRUD_EXAMPLE quota unlimited on users;
grant create session, create table, create procedure, create trigger, create any directory, create view, create sequence, create synonym, create materialized view to STRUTS2_CRUD_EXAMPLE;
