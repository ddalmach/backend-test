-- public.users definition

-- Drop table
DROP TABLE IF EXISTS public.users;

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	created_date date NOT NULL DEFAULT CURRENT_DATE,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

-- public.stores definition

-- Drop table
DROP TABLE IF EXISTS public.stores;

-- DROP TABLE public.stores;

CREATE TABLE public.stores (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	category varchar NOT NULL,
	user_id int8 NOT NULL
);


-- public.stores foreign keys

ALTER TABLE public.stores ADD CONSTRAINT store_fk FOREIGN KEY (user_id) REFERENCES public.users(id);

-- public.products definition

-- Drop table
DROP TABLE IF EXISTS public.products;

-- DROP TABLE public.products;

CREATE TABLE public.products (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	code varchar NOT NULL,
	"name" varchar NULL,
	price numeric NOT NULL,
	stock int8 NULL DEFAULT 0,
	CONSTRAINT products_pk PRIMARY KEY (id)
);
CREATE INDEX products_id_idx ON public.products USING btree (id);

-- public.products_by_store definition

-- Drop table
DROP TABLE IF EXISTS public.products_by_store;

-- DROP TABLE public.products_by_store;

CREATE TABLE public.products_by_store (
	id_product int8 NOT NULL,
	id_store int8 NOT NULL,
	stock int8 NULL DEFAULT 0
);

INSERT INTO public.users
("name", created_date)
VALUES('test', CURRENT_DATE);
