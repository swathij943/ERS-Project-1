create table if not exists employee (
	first_name varchar(128) primary key, 
	last_name varchar(128)
);

insert into employee (first_name, last_name) values 
	('swathi', 'jayasree');
	
insert into employee values 
	('sam', 'leo'),
	('mia', 'angel'),
	('liya', 'joyel'),
	('kio', 'jackson');