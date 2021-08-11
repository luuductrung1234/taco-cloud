delete from Order_Tacos;
delete from Taco_Ingredients;
delete from Tacos;
delete from Orders;
delete from Ingredients;

insert into Ingredients (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredients (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredients (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredients (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredients (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredients (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredients (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredients (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredients (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredients (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');
