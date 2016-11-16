CREATE TABLE reservations(
id_fact integer NOT NULL,
id_passenger integer NOT NULL,
id_status integer NOT NULL,
n_reservations integer NOT NULL,
CONSTRAINT fact_pk PRIMARY KEY(id_fact)
);


CREATE TABLE passengers(
id_passenger integer NOT NULL,
desc_passenger varchar(40) NOT NULL,
CONSTRAINT passenger_pk PRIMARY KEY(id_passenger)
);


CREATE TABLE status(
id_status integer NOT NULL,
desc_status varchar(40) NOT NULL,
CONSTRAINT status_pk PRIMARY KEY(id_status)
);


INSERT INTO passengers VALUES (1, 'Karen');
INSERT INTO passengers VALUES (2, 'Judit');

INSERT INTO status VALUES (1, 'Payed');
INSERT INTO status VALUES (2, 'Cancelled');

INSERT INTO reservations VALUES (1, 1, 1, 5);
INSERT INTO reservations VALUES (2, 2, 1, 3);