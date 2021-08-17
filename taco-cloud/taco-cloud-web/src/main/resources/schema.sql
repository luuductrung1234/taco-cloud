create table if not exists Ingredients
(
    id varchar(4) not null,
    name varchar(50) not null,
    type varchar(10) not null,
    primary key(id)
);

create table if not exists Tacos
(
    id uuid default random_uuid(),
    name varchar(50) not null,
    createdAt timestamp not null,
    primary key(id)
);

create table if not exists Taco_Ingredients
(
    tacoId uuid not null,
    tacoKey integer,
    ingredientId varchar(4) not null,
    primary key(tacoId, ingredientId)
);

alter table Taco_Ingredients
    add foreign key (tacoId) references Tacos(id);

alter table Taco_Ingredients
    add foreign key (ingredientId) references Ingredients(id);

create table if not exists Orders
(
    id uuid default random_uuid(),
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
    orderId uuid not null,
    orderKey integer,
    tacoId uuid not null,
    primary key(orderId, tacoId)
);

alter table Order_Tacos
    add foreign key (orderId) references Orders(id);

alter table Order_Tacos
    add foreign key (tacoId) references Tacos(id);
