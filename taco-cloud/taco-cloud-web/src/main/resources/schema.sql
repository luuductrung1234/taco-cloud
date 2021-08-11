create table if not exists Ingredients
(
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null,
    primary key(id)
);

create table if not exists Tacos
(
    id bigint not null auto_increment,
    name varchar(50) not null,
    createdAt timestamp not null,
    primary key(id)
);

create table if not exists Taco_Ingredients
(
    taco bigint not null,
    ingredient varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (taco) references Tacos(id);

alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredients(id);

create table if not exists Orders
(
    id bigint not null auto_increment,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    createdAt timestamp not null,
    primary key(id)
);

create table if not exists Order_Tacos
(
    tacoOrder bigint not null,
    taco bigint not null
);

alter table Order_Tacos
    add foreign key (tacoOrder) references Orders(id);

alter table Order_Tacos
    add foreign key (taco) references Tacos(id);
