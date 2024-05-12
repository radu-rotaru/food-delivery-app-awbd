insert into clients(id, name, email, address)  values (1, 'Andrei', 'andrei@test.com', 'str.Primaverii, nr.7');
insert into clients(id, name, email, address)  values (2, 'Ana', 'ana@test.com', 'str.Lalelelor, nr.10');
insert into clients(id, name, email, address)  values  (3, 'Mihai', 'mihai@test.com', 'str.Soarelui, nr.12');

insert into couriers (id, name, phone_number, available) values (1, 'Dan', '0777777777', 1);
insert into couriers (id, name, phone_number, available) values (2, 'Adrian', '0777888888', 1);

insert into restaurants (id, name, address, email, opening_hours) values (1, 'Nuba', 'bd.Primaverii, nr.7', 'nuba@test.com', '10-23');
insert into restaurants (id, name, address, email, opening_hours) values (2, 'Herestrau', 'bd.Carol, nr.10', 'herestrau@test.com', '9-22');
insert into restaurants (id, name, address, email, opening_hours) values (3, 'Brotacei', 'str.Aviatei, nr.14', 'brotecei@test.com', '10-24');

insert into orders (id, restaurant_id, client_id, courier_id, status) values (1, 1, 1, 1, 'processed');

insert into dishes (id, name, quantity, price, restaurant_id) values (1, 'Pizza', 500, 14.99, 1);
insert into dishes (id, name, quantity, price, restaurant_id) values (2, 'Pasta', 500, 14.99, 1);

insert into order_dish (order_id, dish_id) values (1, 1);

insert into courier_reviews (id, order_id, courier_id, client_id, stars) values (1, 1, 1, 1, 4);