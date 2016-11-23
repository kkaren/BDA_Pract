INSERT INTO date_dimension
    (full_date,date_description,weekday,month,year)
SELECT
    day,
    rtrim(to_char(day, 'Month')) || to_char(day, ' DD, YYYY'),
    to_char(day, 'Day'),
    date_part('month', day),
    date_part('year', day)

FROM
    generate_series('2015-01-01'::date, '2025-12-31'::date, '1 day') day;

INSERT INTO airport(id_airport, airport_name, airport_location, other_airport_details) VALUES (1, 'BCN', 3, 'Barcelona, Spain');
INSERT INTO airport(id_airport, airport_name, airport_location, other_airport_details) VALUES (2, 'LAX', 2, 'Los Angeles, EEUU');
INSERT INTO airport(id_airport, airport_name, airport_location, other_airport_details) VALUES (3, 'FCO', 1, 'Roma, Italia');



INSERT INTO passenger(
 passenger_name, phone_number,email_address,id_geography,other_passenger_details)
 VALUES ('John Smith', 555001245, 'lucymarie@gmail.com', 1, 'alergic to nuts'),
 ('Mari Madalenas', 505051234, 'magdalenasmari@outlook.com',1,' '),
 ('Adolfo Cabrales', 575757579, 'marabao@outlook.com',2, ' '),
 ('Roberto Iniesta', 98675431, 'deltouya@gamail.com',4,''),
 ('Andrea Motis', 12375431, 'feelinggood@gmail.com',3,''),
 ('Janis Joplin', 55555579, 'summertime@gmail.com',1,''),
 ('Amy Winehouse', 543555789, 'rehabisnotbad@gmail.com',5,''),
 ('Aretha Franklin', 555534789, 'respect@gmail.com',5,''),
 ('David Bowie', 54321245, 'majortom@nasa.org',1,'Far above the moon'),
 ('Leonard Cohen', 64312345, 'lehonard@gmail.com',1,''),
 ('Bob Dylan', 64312245, 'ihaveanobel@gmail.com',3,''),
 ('Adele Lastname', 612342345, 'lettheskyfall@gmail.com',3,''),
 ('Roger Waters', 613242354, 'thewall@gmail.com',2,''),
 ('John Lennon', 61328354, 'imagine@gmail.com',2,''),
 ('Freddie Mercury', 6884354, 'bohemian@gmail.com',2,'He wants to break free'),
 ('Joaquin Sabina', 63384354, '19dias@gmail.com',5,''),
 ('Eleanor Rigby', 63384312, 'lonleypeople@gmail.com',5,''),
 ('Jim Morrison', 66684312, 'jimmypuertas@gmail.com',4,''),
 ('Jimmy Hendrix', 69984312, 'purplehaze@gmail.com',6,''),
 ('Frank Zappa', 612344312, 'smokeonthewater@gmail.com',6,''),
 ('Bobby Brown', 612344312, 'illberealrich@gmail.com',6,''),
 ('Jude Hey', 612344312, 'hey_jude@gmail.com',6,'');



INSERT INTO booking_agent( agent_name, agent_details)
VALUES ('Samantha Bones', '2 years in the company'),
 ('Thomas Smith', '10 years in the company'),
 ('Spencer Grimes', '3.5 years in the company'),
 ('Harry Potter', 'First year'),
 ('Peter Griffin', '7.5 years in the company');

INSERT INTO status(id_status, status_description) VALUES (1, 'Accepted');
INSERT INTO status(id_status, status_description) VALUES (2, 'Cancelled');

INSERT INTO travel_class(id_travel_class, travel_class_description) VALUES (1, 'Tourist');
INSERT INTO travel_class(id_travel_class, travel_class_description) VALUES (2, 'Business');
INSERT INTO travel_class(id_travel_class, travel_class_description) VALUES (3, 'First class');

INSERT INTO reservation( id_agent, id_passenger, id_reservation_status, id_travel_class, id_date_reserv_made, number_in_party, payment_amount, id_origin_airport,id_destination_airport, id_payment_status)
 VALUES ( 1, 1, 1, 2, 3, 1, 10, 1, 2, 1),
 ( 1, 1, 1, 2, 305, 1, 30, 1, 2, 2),
 ( 1, 2, 1, 1, 57, 2, 30, 2, 1, 1),
 ( 1, 2, 1, 1, 67, 2, 35, 1, 2, 1),
 ( 1, 2, 1, 1, 68, 2, 23, 1, 3, 1),
 ( 1, 3, 2, 3, 40, 5, 100, 2, 3, 2),
 ( 1, 3, 2, 3, 70, 5, 10, 3, 2, 1),
 ( 1, 4, 2, 1, 170, 5, 51, 2, 1, 1),
 ( 1, 4, 2, 1, 177, 5, 21, 1, 2, 1),
 ( 1, 5, 2, 1, 201, 3, 23, 2, 1, 2),
 ( 1, 5, 2, 1, 207, 3, 22, 3, 1, 1),
 ( 1, 5, 2, 1, 257, 3, 42, 3, 1, 1),
 ( 1, 5, 2, 1, 267, 3, 24, 3, 2, 1),
 ( 2, 6, 1, 1, 45, 4, 21, 1, 3, 1),
 ( 2, 6, 1, 1, 50, 4, 101, 1, 3, 2),
 ( 2, 7, 2, 1, 10, 4, 16, 2, 1, 1),
 ( 2, 7, 2, 1, 15, 4, 71, 1, 3, 1),
 ( 2, 7, 2, 1, 25, 4, 71, 1, 3, 1),
 ( 2, 7, 2, 1, 27, 4, 16, 2, 1, 1),
 ( 2, 7, 2, 1, 37, 4, 17, 2, 3, 1),
 ( 2, 8, 1, 1, 37, 4, 81, 1, 3, 1),
 ( 2, 8, 1, 1, 47, 4, 81, 1, 3, 1),
 ( 2, 3, 1, 3, 225, 4, 51, 1, 3, 1),
 ( 2, 3, 1, 3, 525, 4, 10, 2, 3, 1),
 ( 2, 9, 1, 3, 505, 4, 1, 1, 3, 1),
 ( 2, 9, 1, 3, 506, 4, 19, 2, 3, 1),
 ( 2, 10, 1, 3, 370, 4, 100, 1, 2, 2),
 ( 3, 11, 1, 3, 8, 3, 23, 2, 3, 1),
 ( 3, 12, 2, 1, 9, 2, 44, 3, 2, 1),
 ( 4, 15, 2, 1, 77, 2, 45, 3, 1, 1),
 ( 5, 15, 2, 1, 77, 2, 45, 1, 2, 1),
 ( 2, 13 , 1, 2, 15, 4, 51, 1, 3, 2);

--INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (1, 1, 1, 3, 240.75);
--INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (2, 1, 3, 8, 2450.99);
--INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (3, 2, 2, 9, 3995.30);
--INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (4, 1, 4, 15, 215.10);
