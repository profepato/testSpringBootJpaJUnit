CREATE DATABASE testJUnit;

USE testJUnit;

CREATE TABLE band(
	id INTEGER AUTO_INCREMENT,
	name TEXT,
	creationYear INTEGER,
	PRIMARY KEY(id)
);

INSERT INTO band VALUES
(NULL, 'Meshuggah', 1987),
(NULL, 'Beheaded', 1991),
(NULL, 'Deicide', 1987),
(NULL, 'Cannibal Corpse', 1988),
(NULL, 'Criminal', 1991);

SELECT * FROM band;