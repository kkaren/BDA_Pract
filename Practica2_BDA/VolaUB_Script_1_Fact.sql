-- TABLES ----------------------------------------------------

-- Table: date

DROP TABLE date_dimension CASCADE;

CREATE TABLE date_dimension
(
    id_date serial,
    full_date date,
    date_description text,
    day integer,
    month integer,
    month_name text,
    year integer,
    CONSTRAINT id_date PRIMARY KEY(id_date)
);

-- Table: flight

DROP TABLE flight CASCADE;

CREATE TABLE flight
(
  id_flight serial,
  id_origin_airport integer NOT NULL,
  airport_origin_code character varying(3) NOT NULL,
  airport_origin_location character varying(40) NOT NULL,
  id_destination_airport integer NOT NULL,
  airport_destination_code character varying(3) NOT NULL,
  airport_destination_location character varying(40) NOT NULL,
  id_departure_date_time integer NOT NULL,
  flight_cost double precision NOT NULL,
  CONSTRAINT id_flight PRIMARY KEY (id_flight),
  CONSTRAINT id_departure_date_time FOREIGN KEY (id_departure_date_time)
      REFERENCES date_dimension (id_date) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Index: fki_id_departure_date_time

DROP INDEX fki_id_departure_date_time;

CREATE INDEX fki_id_departure_date_time
  ON flight
  USING btree
  (id_departure_date_time);

-- Table: booking_agent

DROP TABLE booking_agent CASCADE;

CREATE TABLE booking_agent
(
  id_agent serial,
  agent_name character varying(40) NOT NULL,
  agent_details character varying(100),
  CONSTRAINT id_agent PRIMARY KEY (id_agent)
)
WITH (
  OIDS=FALSE
);

-- Table: passenger

DROP TABLE passenger CASCADE;

CREATE TABLE passenger
(
  id_passenger serial,
  passenger_name character varying(40) NOT NULL,
  phone_number integer NOT NULL,
  email_address character varying(40) NOT NULL,
  id_geography integer NOT NULL,
  country character varying(20) NOT NULL,
  other_passenger_details character varying(100),
  CONSTRAINT id_passenger PRIMARY KEY (id_passenger)
)
WITH (
  OIDS=FALSE
);

-- Table: status

DROP TABLE status CASCADE;

CREATE TABLE status
(
  id_status INTEGER NOT NULL,
  status_description character varying(40) NOT NULL,
  CONSTRAINT id_status PRIMARY KEY (id_status)
)
WITH (
  OIDS=FALSE
);

-- Table: travel_class

DROP TABLE travel_class CASCADE;

CREATE TABLE travel_class
(
  id_travel_class INTEGER NOT NULL,
  travel_class_description character varying(20) NOT NULL,
  CONSTRAINT id_travel_class PRIMARY KEY (id_travel_class)
)
WITH (
  OIDS=FALSE
);


-- Table: reservation

DROP TABLE reservation CASCADE;

CREATE TABLE reservation
(
  id_reservation serial,
  id_agent integer NOT NULL,
  id_passenger integer NOT NULL,
  id_flight integer NOT NULL,
  id_reservation_status integer NOT NULL,
  id_travel_class integer NOT NULL,
  id_date_reserv_made integer NOT NULL,
  number_in_party integer NOT NULL,
  payment_amount double precision NOT NULL,
  flight_cost double precision NOT NULL,
  CONSTRAINT id_reservation PRIMARY KEY (id_reservation),
  CONSTRAINT id_agent FOREIGN KEY (id_agent)
      REFERENCES booking_agent (id_agent) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_passenger FOREIGN KEY (id_passenger)
      REFERENCES passenger (id_passenger) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_flight FOREIGN KEY (id_flight)
      REFERENCES flight (id_flight) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_reservation_status FOREIGN KEY (id_reservation_status)
      REFERENCES status (id_status) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_travel_class FOREIGN KEY (id_travel_class)
      REFERENCES travel_class (id_travel_class) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_date_reserv_made FOREIGN KEY (id_date_reserv_made)
      REFERENCES date_dimension (id_date) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Index: fki_id_agent

DROP INDEX fki_id_agent;

CREATE INDEX fki_id_agent
  ON reservation
  USING btree
  (id_agent);

-- Index: fki_id_passenger

DROP INDEX fki_id_passenger;

CREATE INDEX fki_id_passenger
  ON reservation
  USING btree
  (id_passenger);

-- Index: fki_id_flight

DROP INDEX fki_id_flight;

CREATE INDEX fki_id_flight
  ON reservation
  USING btree
  (id_flight);

-- Index: fki_id_reservation_status

DROP INDEX fki_id_reservation_status;

CREATE INDEX fki_id_reservation_status
  ON reservation
  USING btree
  (id_reservation_status);

-- Index: fki_id_travel_class

DROP INDEX fki_id_travel_class;

CREATE INDEX fki_id_travel_class
  ON reservation
  USING btree
  (id_travel_class);

-- Index: fki_id_date_reserv_made

DROP INDEX fki_id_date_reserv_made;

CREATE INDEX fki_id_date_reserv_made
  ON reservation
  USING btree
  (id_date_reserv_made);

-- INSERTS ----------------------------------------------------

INSERT INTO date_dimension
    (full_date,date_description,day,month,month_name,year)
SELECT
    day,
    rtrim(to_char(day, 'Month')) || to_char(day, ' DD, YYYY'),
    date_part('day', day),
    date_part('month', day),
    rtrim(to_char(day, 'Month')),
    date_part('year', day)

FROM
    generate_series('2015-01-01'::date, '2025-12-31'::date, '1 day') day;

INSERT INTO passenger(passenger_name, phone_number,email_address,id_geography, country, other_passenger_details)
 VALUES ('John Smith', 555001245, 'lucymarie@gmail.com', 1, 'EEUU', 'Allergic to nuts'),
 ('Mari Madalenas', 505051234, 'magdalenasmari@outlook.com', 2, 'Spain', ''),
 ('Adolfo Cabrales', 575757579, 'marabao@outlook.com', 2, 'Spain',' '),
 ('Roberto Iniesta', 98675431, 'deltouya@gamail.com', 4, 'Argentina',''),
 ('Andrea Motis', 12375431, 'feelinggood@gmail.com', 3, 'Mexico',''),
 ('Janis Joplin', 55555579, 'summertime@gmail.com', 1, 'EEUU',''),
 ('Amy Winehouse', 543555789, 'rehabisnotbad@gmail.com', 5, 'UK',''),
 ('Aretha Franklin', 555534789, 'respect@gmail.com', 5, 'UK',''),
 ('David Bowie', 54321245, 'majortom@nasa.org', 1, 'EEUU','Far above the moon'),
 ('Leonard Cohen', 64312345, 'lehonard@gmail.com', 1, 'EEUU',''),
 ('Bob Dylan', 64312245, 'ihaveanobel@gmail.com', 3, 'Australia',''),
 ('Adele Lastname', 612342345, 'lettheskyfall@gmail.com', 3, 'Australia',''),
 ('Roger Waters', 613242354, 'thewall@gmail.com', 5, 'UK',''),
 ('John Lennon', 61328354, 'imagine@gmail.com', 5, 'UK',''),
 ('Freddie Mercury', 6884354, 'bohemian@gmail.com', 1, 'EEUU','He wants to break free'),
 ('Joaquin Sabina', 63384354, '19dias@gmail.com', 2, 'Spain',''),
 ('Eleanor Rigby', 63384312, 'lonleypeople@gmail.com', 4, 'Argentina',''),
 ('Jim Morrison', 66684312, 'jimmypuertas@gmail.com', 1, 'EEUU',''),
 ('Jimmy Hendrix', 69984312, 'purplehaze@gmail.com', 3, 'Australia',''),
 ('Frank Zappa', 612344312, 'smokeonthewater@gmail.com', 6, 'Netherlands',''),
 ('Bobby Brown', 612344312, 'illberealrich@gmail.com', 6, 'Netherlands',''),
 ('Jude Hey', 612344312, 'hey_jude@gmail.com', 6, 'Netherlands','');


INSERT INTO booking_agent(agent_name, agent_details)
VALUES ('Samantha Bones', '2 years in the company'),
 ('Thomas Smith', '10 years in the company'),
 ('Spencer Grimes', '3.5 years in the company'),
 ('Harry Potter', 'First year'),
 ('Peter Griffin', '7.5 years in the company');

INSERT INTO status(id_status, status_description) VALUES (1, 'Paid/Confirmed'), (2, 'Not Paid/Cancelled');

INSERT INTO travel_class(id_travel_class, travel_class_description) VALUES (1, 'Tourist'), (2, 'Business'), (3, 'First class');

INSERT INTO flight(id_origin_airport, airport_origin_code, airport_origin_location, id_destination_airport, airport_destination_code, airport_destination_location, id_departure_date_time, flight_cost)
 VALUES (1, 'BCN', 'Barcelona, Spain', 2, 'LAX', 'Los Angeles, EEUU', 3, 800),
 (1, 'BCN', 'Barcelona, Spain', 3, 'FCO', 'Roma, Italia', 15, 400), 
 (2, 'LAX', 'Los Angeles, EEUU', 1, 'BCN', 'Barcelona, Spain', 6, 900), 
 (2, 'LAX', 'Los Angeles, EEUU', 3, 'FCO', 'Roma, Italia', 78, 800),
 (3, 'FCO', 'Roma, Italia', 1, 'BCN', 'Barcelona, Spain', 57, 350),
 (3, 'FCO', 'Roma, Italia', 2, 'LAX', 'Los Angeles, EEUU', 90, 750);

INSERT INTO reservation(id_agent, id_passenger, id_reservation_status, id_travel_class, id_date_reserv_made, number_in_party, payment_amount, id_flight)
 VALUES ( 1, 1, 1, 2, 3, 1, 10, 1),
 ( 1, 1, 1, 2, 305, 1, 30, 1),
 ( 1, 2, 1, 1, 57, 2, 30, 2),
 ( 1, 2, 1, 1, 67, 2, 35, 1),
 ( 1, 2, 1, 1, 68, 2, 23, 2),
 ( 1, 3, 2, 3, 40, 5, 100, 3),
 ( 1, 3, 2, 3, 70, 5, 10, 3),
 ( 1, 4, 2, 1, 170, 5, 51, 5),
 ( 1, 4, 2, 1, 177, 5, 21, 5),
 ( 1, 5, 2, 1, 201, 3, 23, 4),
 ( 1, 5, 2, 1, 207, 3, 22, 3),
 ( 1, 5, 2, 1, 267, 3, 24, 6),
 ( 2, 6, 1, 1, 45, 4, 21, 2),
 ( 2, 6, 1, 1, 50, 4, 101, 4),
 ( 2, 7, 2, 1, 10, 4, 16, 4),
 ( 2, 7, 2, 1, 15, 4, 71, 4),
 ( 2, 7, 2, 1, 27, 4, 16, 6),
 ( 2, 8, 1, 1, 37, 4, 81, 1),
 ( 2, 8, 1, 1, 47, 4, 81, 1),
 ( 2, 9, 1, 3, 505, 4, 1, 5),
 ( 2, 9, 1, 3, 506, 4, 19, 2),
 ( 2, 10, 1, 3, 370, 4, 100, 3),
 ( 3, 11, 1, 3, 8, 3, 23, 4),
 ( 3, 12, 2, 1, 9, 2, 44, 2),
 ( 2, 13, 1, 2, 15, 4, 51, 5),
 ( 2, 14, 2, 1, 25, 4, 71, 5),
 ( 2, 14, 2, 1, 37, 4, 17, 5),
 ( 4, 15, 2, 1, 77, 2, 45, 3),
 ( 5, 15, 2, 1, 77, 2, 45, 1),
 ( 1, 15, 2, 1, 257, 3, 42, 3),
 ( 2, 16, 1, 3, 225, 4, 51, 4),
 ( 2, 17, 1, 3, 525, 4, 10, 2),
 ( 3, 18, 1, 2, 745, 1, 200, 5),
 ( 4, 19, 2, 1, 77, 2, 87, 6),
 ( 5, 20, 2, 1, 77, 2, 156, 1),
 ( 1, 21, 2, 1, 257, 3, 145, 3),
 ( 2, 22, 1, 3, 225, 4, 74, 1);

CREATE TABLE full_reservation AS (SELECT reservation.*, flight.flight_cost FROM reservation INNER JOIN flight ON (reservation.id_flight = flight.id_flight)); 
