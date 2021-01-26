drop table if exists pet;
drop table if exists search;
drop table if exists offer;
drop table if exists registered_user;
drop table if exists demand;
-------------pet table definition---------------------------------------------------------------------------------------
drop table if exists pet;
create table pet(
pet_id int primary key,
pet_name varchar(30) not null,
gender varchar(10) not null,
age smallint not null,
coat_length varchar(15) not null,
training text,
description text,
color varchar(15) not null,
size varchar(15) not null,
city varchar(20) not null,
country varchar(20) not null,
pet_type varchar(4),
vaccination text not null,
 CONSTRAINT CK_size CHECK (size IN ('small','medium','large','extra large')),
 CONSTRAINT CK_gender CHECK (gender IN ('male','female')),
 CONSTRAINT CK_coat_length CHECK (coat_length IN ('hairless','short','medium','long','wire','curly')),
 CONSTRAINT CK_pet_type CHECK (pet_type IN ('cat','dog'))
);
insert into pet values
(101,'Cezar','male',36,'medium','Assis Couché Pas-bouger','','black','large','Mohammedia','Morocco','dog','Yes'),
(102,'Catty','female',18,'short','','','White','small','Casablanca','Morocco','cat','Yes'),
(103,'Mimi','female',22,'medium','','','black','large','Ifrane','Morocco','cat','Yes'),
(104,'Paco','male',56,'long','Assis Couché','','black','extra large','Marrakech','Morocco','dog','Yes'),
(105,'Rocky','male',36,'medium','Assis Couché Pas-bouger','','black','large','Tanger','Morocco','dog','Yes');

---------------registered_user table definition--------------------------------------------------------------------------
drop table if exists registered_user;
create table registered_user(
user_id int primary key,
name varchar(40) not null,
gender varchar(10) not null,
age smallint not null,
country varchar(30) not null,
email varchar(50) not null,
phone_number varchar(15) not null,
CONSTRAINT CK_gender CHECK (gender IN ('male','female'))
);

insert into registered_user values
(2001,'Anass Zouine','male',20,'Morocco','anass@gmail.com','+212 0638989398'),
(2002,'Hamza Abidi','male',22,'Morocco','hamza.ab@gmail.com','+212 0638125698'),
(2003,'Salma Kandar','female',18,'Morocco','sal.kan@gmail.com','+212 0699235654'),
(2004,'Abdellah Zahi','male',30,'Morocco','abdo@gmail.com','+212 0645231209'),
(2005,'Ilham fendari','female',26,'Morocco','ilfenda@gmail.com','+212 0629292921');

---------------search table definition-----------------------------------------------------------------------------------
drop table if exists search;
create table search(
search_id int primary key,
ip_address varchar(30) not null,
date date not null,
pet_type varchar(4),
pet_category varchar(30),
gender varchar(10),
size varchar(15),
color varchar(15),
city varchar(20),
country varchar(20),
CONSTRAINT CK_size CHECK (size IN ('small','medium','large','extra large')),
CONSTRAINT CK_gender CHECK (gender IN ('male','female')),
CONSTRAINT CK_pet_type CHECK (pet_type IN ('cat','dog'))
);


---------------offer table definition------------------------------------------------------------------------------------
drop table if exists offer;
create table offer(
	offer_id int primary key,
	pet_id int not null,
	user_id int,
	phone_number varchar(15) unique not null,
	email varchar(50) not null,
	constraint pet_fkey foreign key (pet_id) references pet (pet_id) on delete set null on update cascade,
	constraint user_fkey foreign key (user_id) references registered_user (user_id) on delete set null on update cascade
	
);

insert into offer values
(11,101,2001,'+212 0678989912','anass@gmail.com'),
(12,102,2002,'+212 0677659912','hamza.ab@gmail.com'),
(13,103,2003,'+212 0678987019','sal.kan@gmail.com'),
(14,104,2004,'+212 0678985657','abdo@gmail.com'),
(15,105,2005,'+212 0678983455','ilfenda@gmail.com');


---------------demand table definition-----------------------------------------------------------------------------------
drop table if exists demand;
create table demand(
	demand_id int primary key,
	user_id int,
	gender varchar(10) not null,
	pet_type varchar(4) not null,
	size varchar(15),
	age smallint not null,
	color varchar(15),
	city varchar(20) not null,
	country varchar(20) not null,
	constraint user_fkey foreign key (user_id) references registered_user (user_id) on delete set null on update cascade,
	CONSTRAINT CK_size CHECK (size IN ('small','medium','large','extra large')),
	CONSTRAINT CK_gender CHECK (gender IN ('male','female')),
	CONSTRAINT CK_pet_type CHECK (pet_type IN ('cat','dog'))

);

insert into demand values
(1,2001,'male','dog','small',10,'black','Agadir','Morocco'),
(2,2001,'male','dog','small',10,'white','Agadir','Morocco'),
(3,2001,'male','dog','small',10,'white','Asfi','Morocco'),
(4,2002,'male','cat','small',10,'black','Rabat','Morocco'),
(5,2002,'male','dog','small',10,'black','Rabat','Morocco');
-------------------------Indexes--------------------------------------
------------pet table------------------
create index bylocation on pet (city,country);
create index bygender on pet (gender);
create index byage on pet (age);
----------search table-----------------
create index bydate on search (date);
----------demand table-----------------
create index dem_bylocation on demand (city,country);
create index dem_bygender on demand (gender);
create index dem_byage on demand (age);
------------------------------------------------------------------------  

create table credentials (
	email varchar(50) primary key,
	password varchar(30) not null
);
insert into credentials values
('anass@gmail.com','qpalymdh/'),
('hamza.ab@gmail.com','ababab111'),
('sal.kan@gmail.com','lovedogs123'),
('abdo@gmail.com','2000password'),
('ilfenda@gmail.com','fenda@45');
-----------------------------------------------
alter table registered_user
add constraint emailun_key unique (email);
alter table registered_user
add constraint emailf_key foreign key (email) references credentials (email);
-----------------------------------------------
