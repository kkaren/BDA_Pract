CREATE TABLE aeroport(
id_aeroport integer NOT NULL,
codi_int varchar(20) NOT NULL,
nom varchar(40) NOT NULL,
ciutat varchar(20) NOT NULL,
cost_handling decimal(10,2) NOT NULL,
CONSTRAINT aeroport_pk PRIMARY KEY(id_aeroport)
);

CREATE TABLE model_avio(
id_model integer NOT NULL,
nom varchar(30) NOT NULL,
descripcio varchar(200) NOT NULL,
places integer NOT NULL,
pes decimal(10,2) NOT NULL,
CONSTRAINT model_pk PRIMARY KEY(id_model)
);

CREATE TABLE avio(
id_avio integer NOT NULL,
matricula varchar(20) NOT NULL,
id_model integer NOT NULL REFERENCES modelAvio(id_model),
CONSTRAINT avio_pk PRIMARY KEY(id_avio)
);


CREATE TABLE pilot(
	id_pilot INTEGER PRIMARY KEY,
	nom VARCHAR(30) NOT NULL,
	cognom VARCHAR(30) NOT NULL,
	hores_vol INTEGER NOT NULL,
	id_aeroport INT REFERENCES aeroport(id_aeroport)
);

CREATE TABLE pilota(
id_pilot integer NOT NULL,
id_model integer NOT NULL,
CONSTRAINT grup PRIMARY KEY(id_pilot, id_model)
);



CREATE TABLE ruta(
	id_ruta INTEGER PRIMARY KEY,
	dia varchar(10) NOT NULL,
	hora TIME NOT NULL,
	id_aeroport_origen INT REFERENCES aeroport(id_aeroport),
	d_aeroport_desti INT REFERENCES aeroport(id_aeroport),
	id_model INT REFERENCES modelAvio(id_model),
	data DATE,
	id_avio INT REFERENCES avio(id_avio),
	id_pilot INT REFERENCES pilot(id_pilot),
	incidencies BOOLEAN
);
