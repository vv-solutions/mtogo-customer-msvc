
CREATE TABLE IF NOT EXISTS customer
(
    id SERIAL PRIMARY KEY,
    first_name varchar,
    last_name varchar,
    create_stamp timestamp(6),
    active boolean,
    email varchar
);

CREATE TABLE IF NOT EXISTS address
(
    id SERIAL PRIMARY KEY,
    zip_code integer,
    address varchar,
    city varchar,
    customer_id integer,
    create_stamp timestamp(6),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
    );