insert into address (address_id,available, city, country, state, street,street_number)
values (1,1,"Aberdeen", "Unite States", "Washington", "123 Grunge", 123),
(2, "Jacksonville","Unite States", "Florida", "Haouse of Paine", 987),
(3, "Bakersfield","Unite States", "California", "Jonathan Davis", 993),
(4, "East Bay","Unite States", "California", "Sweet Children", 342),
(5, "Los Angeles","Unite States", "California", "Freaky Style", 67),
(6, "Ditroit","Unite States", "Michigan", "eminemrap", 313);

insert into organizations(org_id,cuit,email,org_key,org_name,org_status,phone,address_id)
values(1, "30-98765412-4", "nirvana@gmail.com", "kurtcobain", "Nirvana",1, "34651987654", 1),
(2, "30-87512378-4", "limpbizkit@gmail.com", "freddurts", "Limp Bizkit",1, "34656427654", 4),
(3, "30-12345678-4", "greenday@gmail.com", "billiejoel", "Green Day",1, "55551987654", 3),
(4, "30-67236710-4", "rhcp@gmail.com", "rhcp", "Red Hot Chilli Peppers",1, "34651987654", 5);