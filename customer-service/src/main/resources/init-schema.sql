DROP SCHEMA IF EXISTS customer CASCADE;

CREATE SCHEMA customer;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table customer.customers
(
    id uuid NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying collate pg_catalog."default" not null,
    CONSTRAINT customers_key PRIMARY KEY (id)
);

DROP MATERIALIZED VIEW IF EXISTS customer.order_customer_m_view;

create materialized view customer.order_customer_m_view
tablespace pg_default
as
 select id,
    username,
    first_name,
    last_name
   from customer.customers
with DATA;

refresh materialized view customer.order_customer_m_view;

DROP function IF EXISTS restaurant.refresh_order_customer_m_view;

CREATE OR replace function restaurant.refresh_order_customer_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW customer.order_customer_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_order_customer_m_view ON customer.customers;

CREATE trigger refresh_order_customer_m_view
after INSERT OR UPDATE OR DELETE OR truncate
ON customer.customers FOR each statement
EXECUTE PROCEDURE restaurant.refresh_order_customer_m_view();
