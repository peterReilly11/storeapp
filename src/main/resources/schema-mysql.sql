create database if exists store_app;
use store_app;
drop table if exists cart_item;
drop table if exists cart;
create table IF NOT EXISTS cart (id bigint not null auto_increment, date_time_created datetime, date_time_placed datetime, delivery_city varchar(255), email varchar(255), order_placed bit not null, primary key (id)) engine=InnoDB;
create table IF NOT EXISTS cart_item (id bigint not null auto_increment, date_time_created datetime, item_description varchar(255), item_name varchar(255), itemurl varchar(255), cart_id bigint, primary key (id)) engine=InnoDB;
alter table cart_item add constraint FK1uobyhgl1wvgt1jpccia8xxs3 foreign key (cart_id) references cart (id);