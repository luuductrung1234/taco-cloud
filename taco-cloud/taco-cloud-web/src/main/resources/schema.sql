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
    tacoId bigint not null,
    tacoKey integer,
    ingredientId varchar(4) not null,
    primary key(tacoId, tacoKey, ingredientId)
);

alter table Taco_Ingredients
    add foreign key (tacoId) references Tacos(id);

alter table Taco_Ingredients
    add foreign key (ingredientId) references Ingredients(id);

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
    orderId bigint not null,
    orderKey integer,
    tacoId bigint not null,
    primary key(orderId, orderKey, tacoId)
);

alter table Order_Tacos
    add foreign key (orderId) references Orders(id);

alter table Order_Tacos
    add foreign key (tacoId) references Tacos(id);
