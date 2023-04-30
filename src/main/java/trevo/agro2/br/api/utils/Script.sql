create table tb_budget (
    id uuid not null,
    company varchar(100) not null,
    country varchar(50) not null,
    date date,
    email varchar(50) not null,
    name varchar(50) not null,
    phone varchar(50) not null,
    primary key (id)
    );


create table tb_budget_product (
    budget_id uuid not null,
    product_id uuid not null
    );


create table tb_product (
    id uuid not null,
    category smallint,
    date date,
    description varchar(255) not null,
    name varchar(255) not null unique,
    price float(53) not null,
    status smallint,
    primary key (id)
    );


 create table tb_user (
    id uuid not null,
    date varchar(50),
    login varchar(255) not null unique,
    name varchar(255) not null,
    password varchar(255) not null,
    role varchar(255),
    primary key (id)
    );

alter table if exists tb_budget_product
       add constraint FKdxafxq1mvnd0eaa2pbmw0hxpx
       foreign key (product_id)
       references tb_product;

alter table if exists tb_budget_product
       add constraint FK6cugse6nya5og7oe00n5rl04
       foreign key (budget_id)
       references tb_budget;
