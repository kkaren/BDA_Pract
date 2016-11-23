-- Table: date

DROP TABLE date_dimension CASCADE;

CREATE TABLE date_dimension
(
    id_date serial,
    full_date date,
    date_description text,
    weekday text,
    month integer,
    month_name text,
    year integer,
    constraint id_date PRIMARY KEY(id_date)
);
-- Table: airport

DROP TABLE airport CASCADE;

CREATE TABLE airport
(
  id_airport serial,
  airport_name character varying(15) NOT NULL,
  airport_location character varying(40) NOT NULL,
  other_airport_details character varying(100),
  CONSTRAINT id_airport PRIMARY KEY (id_airport)
)
WITH (
  OIDS=FALSE
);

-- Table: flight

DROP TABLE flight CASCADE;

CREATE TABLE flight
(
  id_flight serial,
  id_origin_airport integer NOT NULL,
  id_destination_airport integer NOT NULL,
  id_departure_date_time integer NOT NULL,
  id_arrival_date_time integer NOT NULL,
  flight_cost double precision NOT NULL,
  CONSTRAINT id_flight PRIMARY KEY (id_flight),
  CONSTRAINT id_destination_airport FOREIGN KEY (id_destination_airport)
      REFERENCES airport (id_airport) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_origin_airport FOREIGN KEY (id_origin_airport)
      REFERENCES airport (id_airport) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_departure_date_time FOREIGN KEY (id_departure_date_time)
      REFERENCES date_dimension (id_date) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_arrival_date_time FOREIGN KEY (id_arrival_date_time)
      REFERENCES date_dimension (id_date) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Index: fki_id_origin_airport

DROP INDEX fki_id_origin_airport;

CREATE INDEX fki_id_origin_airport
  ON flight
  USING btree
  (id_origin_airport);

-- Index: fki_id_destination_airport

DROP INDEX fki_id_destination_airport;

CREATE INDEX fki_id_destination_airport
  ON flight
  USING btree
  (id_destination_airport);

-- Index: fki_id_departure_date_time

DROP INDEX fki_id_departure_date_time;

CREATE INDEX fki_id_departure_date_time
  ON flight
  USING btree
  (id_departure_date_time);

-- Index: fki_id_arrival_date_time

DROP INDEX fki_id_arrival_date_time;

CREATE INDEX fki_id_arrival_date_time
  ON flight
  USING btree
  (id_arrival_date_time);

-- Table: geography

DROP TABLE geography CASCADE;

CREATE TABLE geography
(
  id_geography serial,
  state character varying(40) NOT NULL,
  country character varying(40) NOT NULL,
  CONSTRAINT id_geography PRIMARY KEY (id_geography)
)
WITH (
  OIDS=FALSE
);

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
  id_origin_airport integer NOT NULL,
  id_destination_airport integer NOT NULL,
  id_reservation_status integer NOT NULL,
  id_payment_status integer NOT NULL,
  id_travel_class integer NOT NULL,
  id_date_reserv_made integer NOT NULL,
  number_in_party integer NOT NULL,
  payment_amount double precision NOT NULL,
  CONSTRAINT id_reservation PRIMARY KEY (id_reservation),
  CONSTRAINT id_agent FOREIGN KEY (id_agent)
      REFERENCES booking_agent (id_agent) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_passenger FOREIGN KEY (id_passenger)
      REFERENCES passenger (id_passenger) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_origin_airport FOREIGN KEY (id_origin_airport)
      REFERENCES airport (id_airport) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_destination_airport FOREIGN KEY (id_destination_airport)
      REFERENCES airport (id_airport) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_reservation_status FOREIGN KEY (id_reservation_status)
      REFERENCES status (id_status) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_payment_status FOREIGN KEY  (id_payment_status)
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

-- Index: fki_id_origin_airport

DROP INDEX fki_id_origin_airport;

CREATE INDEX fki_id_origin_airport
  ON reservation
  USING btree
  (id_origin_airport);

-- Index: fki_id_destination_airport

DROP INDEX fki_id_destination_airport;

CREATE INDEX fki_id_destination_airport
  ON reservation
  USING btree
  (id_destination_airport);

-- Index: fki_id_reservation_status

DROP INDEX fki_id_reservation_status;

CREATE INDEX fki_id_reservation_status
  ON reservation
  USING btree
  (id_reservation_status);

-- Index: fki_id_payment_status

DROP INDEX fki_id_payment_status;

CREATE INDEX fki_id_payment_status
  ON reservation
  USING btree
  (id_payment_status);

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
