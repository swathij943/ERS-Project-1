-- 1st table --users

-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	user_id serial4 NOT NULL,
	username varchar NULL,
	"password" varchar NULL,
	first_name varchar NULL,
	last_name varchar NULL,
	email varchar NULL,
	"role" int4 NULL,
	CONSTRAINT users_pk PRIMARY KEY (user_id),
	CONSTRAINT users_fk FOREIGN KEY ("role") REFERENCES public.user_roles(role_id)
);

-- 2nd table --user roles

-- public.user_roles definition

-- Drop table

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles (
	role_id serial4 NOT NULL,
	"role" varchar NULL,
	CONSTRAINT user_roles_pk PRIMARY KEY (role_id)
);

-- 3rd table --reimbursement_status

-- public.reimbursement_status definition

-- Drop table

-- DROP TABLE public.reimbursement_status;

CREATE TABLE public.reimbursement_status (
	status_id serial4 NOT NULL,
	status varchar NULL,
	CONSTRAINT reimbursement_status_pk PRIMARY KEY (status_id)
);


-- 4th table --reimbursement_type

-- public.reimbursement_type definition

-- Drop table

-- DROP TABLE public.reimbursement_type;

CREATE TABLE public.reimbursement_type (
	type_id serial4 NOT NULL,
	"type" varchar NULL,
	CONSTRAINT reimbursement_type_pk PRIMARY KEY (type_id)
);


-- 5th table --reimbursement_type
-- public.reimbursement definition

-- Drop table

-- DROP TABLE public.reimbursement;

CREATE TABLE public.reimbursement (
	reimbursement_id serial4 NOT NULL,
	amount numeric NULL,
	submitted_date date NULL,
	resolved_date date NULL,
	description varchar NULL,
	reimbursement_author int4 NULL,
	reimbursement_resolver int4 NULL,
	reimbursement_status int4 NULL,
	reimbursement_type int4 NULL,
	CONSTRAINT reimbursement_pk PRIMARY KEY (reimbursement_id),
	CONSTRAINT reimbursement_fk FOREIGN KEY (reimbursement_status) REFERENCES public.reimbursement_status(status_id),
	CONSTRAINT reimbursement_fk1 FOREIGN KEY (reimbursement_type) REFERENCES public.reimbursement_type(type_id),
	CONSTRAINT reimbursement_fk2 FOREIGN KEY (reimbursement_author) REFERENCES public.users(user_id),
	CONSTRAINT reimbursement_fk3 FOREIGN KEY (reimbursement_resolver) REFERENCES public.users(user_id)
);

select submitted_date, reimbursement_id, users.first_name, users.last_name , amount, description, reimbursement_type.type, reimbursement_status.status , reimbursement_resolver, resolved_date from reimbursement
join users on users.user_id = reimbursement_author
join reimbursement_type on reimbursement.reimbursement_type = reimbursement_type.type_id
join reimbursement_status on reimbursement.reimbursement_status = reimbursement_status.status_id
where reimbursement_author = 2;

select submitted_date, reimbursement_id, users.first_name, users.last_name , amount, description, reimbursement_type.type, reimbursement_status.status , reimbursement_resolver, resolved_date from reimbursement
join users on users.user_id = reimbursement_author
join reimbursement_type on reimbursement.reimbursement_type = reimbursement_type.type_id
join reimbursement_status on reimbursement.reimbursement_status = reimbursement_status.status_id
where reimbursement_author = 2;


