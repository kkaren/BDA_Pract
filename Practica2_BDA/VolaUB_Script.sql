CREATE TABLE booking_agent
(
id_agent integer NOT NULL,
agent_name varchar(40) NOT NULL,
CONSTRAINT id_agent PRIMARY KEY (id_agent)
);

CREATE TABLE passenger
(
id_passenger integer NOT NULL,
first_name varchar(20)  NOT NULL,
CONSTRAINT id_passenger PRIMARY KEY (id_passenger)
);

CREATE TABLE reservation
(
id_reservation integer NOT NULL,
id_agent integer NOT NULL,
id_passenger integer NOT NULL,
num_reservations integer NOT NULL,
CONSTRAINT id_reservation PRIMARY KEY (id_reservation),
CONSTRAINT id_agent FOREIGN KEY (id_agent) REFERENCES booking_agents(id_agent),
CONSTRAINT id_passenger FOREIGN KEY (id_passenger) REFERENCES passengers(id_passenger)
);

INSERT INTO booking_agent VALUES (1, 'Lola');
INSERT INTO booking_agent VALUES (2, 'Maria');


INSERT INTO passenger VALUES (1, 'Pedro');
INSERT INTO passenger VALUES (2, 'Berta');


INSERT INTO reservation VALUES (1, 1, 1, 1);
INSERT INTO reservation VALUES (2, 2, 2, 1);