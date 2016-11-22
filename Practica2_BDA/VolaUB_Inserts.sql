INSERT INTO date_dimension
    ("Date", "Full Day Description", "Day Of Week", "Calendar Month",
    "Calendar Year", "Fiscal Year Month", "Holiday Indicator",
    "Weekday Indicator")
SELECT
    day,
    rtrim(to_char(day, 'Month')) || to_char(day, ' DD, YYYY'),
    to_char(day, 'Day'),
    rtrim(to_char(day, 'Month')),
    date_part('year', day),
    'F' || to_char(day, 'YYYY-MM'),
    '', --omitting (trivial 'Holiday'/'Non-Holiday, but how to get this ??),
    CASE
        WHEN date_part('isodow', day) IN (6, 7) THEN 'Weekend'
        ELSE 'Weekday'
    END
FROM
    generate_series('2015-01-01'::date, '2025-12-31'::date, '1 day') day;



INSERT INTO passenger(
id_passenger, first_name, second_name, last_name, phone_number, email_address, id_geography, city, state_province_country, country, other_passenger_details)
 VALUES (1, 'Lucy', 'Marie', 'Jones', 555001245, 'lucymarie@gmail.com', 1, 'Dallas', 'Texas', 'EEUU', '');


INSERT INTO passenger(id_passenger, first_name, second_name, last_name, phone_number, email_address, id_geography, city, state_province_country, country, other_passenger_details)
 VALUES (2, 'Tom', 'Mark', 'Morgan', 645874210, 'imtom@gmail.com', 2, 'Austin', 'Texas', 'EEUU', '');


INSERT INTO passenger(id_passenger, first_name, second_name, last_name, phone_number, email_address, id_geography, city, state_province_country, country, other_passenger_details)
 VALUES (3, 'Stacy', 'Ann', 'Campbell', 555071144, 'stannbell@gmail.com', 3, 'Houston', 'Texas', 'EEUU', '');


INSERT INTO passenger(id_passenger, first_name, second_name, last_name, phone_number, email_address, id_geography, city, state_province_country, country, other_passenger_details)
 VALUES (4, 'Frank', 'Lloyd', 'Kirk', 555071144, 'frankie@gmail.com', 4, 'Los Angeles', 'California', 'EEUU', '');

INSERT INTO booking_agent(id_agent, agent_name, agent_details) VALUES (1, 'Samantha Bones', '2 years in the company');
INSERT INTO booking_agent(id_agent, agent_name, agent_details) VALUES (2, 'Thomas Smith', '10 years in the company');
INSERT INTO booking_agent(id_agent, agent_name, agent_details) VALUES (3, 'Spencer Grimes', '3.5 years in the company');

INSERT INTO status(id_status, status_description) VALUES (1, 'Accepted');
INSERT INTO status(id_status, status_description) VALUES (2, 'Cancelled');

INSERT INTO travel_class(id_travel_class, travel_class_description) VALUES (1, 'Tourist');
INSERT INTO travel_class(id_travel_class, travel_class_description) VALUES (2, 'Business');
INSERT INTO travel_class(id_travel_class, travel_class_description) VALUES (3, 'First class');

INSERT INTO reservation(id_reservation, id_agent, id_passenger, id_reservation_status, id_travel_class, id_date_reserv_made, id_geography_passenger, number_in_party) VALUES (1, 1, 1, 1, 2, 3, 1, 1);
INSERT INTO reservation(id_reservation, id_agent, id_passenger, id_reservation_status, id_travel_class, id_date_reserv_made, id_geography_passenger, number_in_party) VALUES (2, 3, 3, 1, 3, 8, 3, 2);
INSERT INTO reservation(id_reservation, id_agent, id_passenger, id_reservation_status, id_travel_class, id_date_reserv_made, id_geography_passenger, number_in_party) VALUES (3, 3, 2, 2, 1, 9, 2, 4);
INSERT INTO reservation(id_reservation, id_agent, id_passenger, id_reservation_status, id_travel_class, id_date_reserv_made, id_geography_passenger, number_in_party) VALUES (4, 2, 4, 1, 2, 15, 4, 1);

INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (1, 1, 1, 3, 240.75);
INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (2, 1, 3, 8, 2450.99);
INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (3, 2, 2, 9, 3995.30);
INSERT INTO payment(id_payment, id_payment_status, id_passenger, id_date_payment_made, payment_amount) VALUES (4, 1, 4, 15, 215.10);
