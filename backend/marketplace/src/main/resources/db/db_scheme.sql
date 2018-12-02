
drop table if exists order_of_system;
drop table if exists product_in_basket;
drop table if exists basket;
drop table if exists product;
drop table if exists seller;
drop table if exists customer;

drop table if exists analytic_report;
drop table if exists analyst;

drop table if exists user_of_system;
drop type if exists payingType;

CREATE TABLE user_of_system (
  id                   BIGSERIAL primary key,
  createdAt            TIMESTAMP    NOT NULL,
  username             VARCHAR(50) NOT NULL,
  password             VARCHAR(200) NOT NULL,
  phone                VARCHAR(20) NOT NULL,
  email                VARCHAR(254) NOT NULL
);

CREATE TABLE seller (
  user_of_system_id    BIGINT primary KEY references user_of_system,
  createdAt            TIMESTAMP    NOT NULL, 
  companyName          VARCHAR(100) NOT NULL,
  inn                  VARCHAR(12) not null,
  ogrn                 VARCHAR(13) not null,
  legal_address        VARCHAR(200) not null,
  actual_address       VARCHAR(200) null
) ;

CREATE TABLE customer (
  user_of_system_id    BIGINT primary KEY references user_of_system,
  createdAt            TIMESTAMP    NOT NULL,
  fullName             VARCHAR(200) NOT NULL,
  address              VARCHAR(200) not null
);

CREATE TABLE analyst (
  user_of_system_id    BIGINT primary KEY references user_of_system,
  createdAt            TIMESTAMP    NOT NULL,
  fullName             VARCHAR(200) NOT NULL
);

CREATE TABLE product (
  id                   BIGSERIAL primary key,
  createdAt            TIMESTAMP NOT NULL,
  seller_id            BIGINT    not null references SELLER ,
  product_name         VARCHAR(50) NOT NULL,
  main_category        VARCHAR(200) NOT NULL,
  price                NUMERIC(12,2) NOT null,
  description          TEXT null,
  product_color        VARCHAR(100) NOT NULL,
  product_size         VARCHAR(20) NOT null,
  product_count        bigint not null
);

create table  basket (
  id                  BIGSERIAL primary key,
  createdAt           TIMESTAMP    NOT NULL,
  customer_id         BIGINT references customer NOT null,
  active              boolean not null default TRUE 
);

create table  product_in_basket (
  product_id          BIGINT references product NOT null,
  basket_id           BIGINT references basket NOT null,
  product_count       BIGINT not null,
  PRIMARY KEY (product_id,basket_id)
);

CREATE TYPE payingType AS ENUM ('CASH', 'BANK_CARD', 'PAY_PAL');

create table order_of_system (
	id                  BIGSERIAL primary key,
    createdAt           TIMESTAMP    NOT NULL,
    basket_id           BIGINT    references basket not null,
    delivery_address    VARCHAR(200) NOT NULL,
    delivery_time       TIMESTAMP    NULL,
  	paying_type         payingType   NOT null,
    paying_time         TIMESTAMP    NULL
);

create table analytic_report (
	id                  BIGSERIAL primary key,
    createdAt           TIMESTAMP    NOT NULL,
    analyst_id          BIGINT    references analyst not null,
    report              bytea      not null
);


 
