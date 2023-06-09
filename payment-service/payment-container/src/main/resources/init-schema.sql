
DROP SCHEMA IF EXISTS payment CASCADE;

CREATE SCHEMA payment;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS payment_status;

CREATE TYPE payment_status AS ENUM ('COMPLETED', 'CANCELLED', 'FAILED');

CREATE TABLE "payment".payments
(
 id uuid,
 customer_id uuid NOT NULL,
 order_id uuid NOT NULL,
 price numeric(10, 2) NOT NULL,
 created_at TIMESTAMP WITH TIME ZONE NOT NULL,
 status payment_status NOT NULL,
 CONSTRAINT payments_pkey PRIMARY KEY (id)
);

CREATE TABLE "payment".credit_entry
(
 id uuid,
 customer_id uuid NOT NULL,
 total_credit_amount numeric(10, 2) NOT NULL,
 CONSTRAINT credit_entry_pkey PRIMARY KEY (id)
);

DROP TYPE IF EXISTS transaction_type;
CREATE TYPE transaction_type as ENUM('DEBIT', 'CREDIT');
