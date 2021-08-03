CREATE TABLE IF NOT EXISTS competition (id INT  NOT NULL AUTO_INCREMENT,
description VARCHAR(255),
registration_open DATETIME,
registration_close DATETIME,
event_start_date DATETIME,
max_number_of_registrations INT,
PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS registration_fee (competition_id INT  NOT NULL,
`ord` INT NOT NULL,
`name` VARCHAR(255) NOT NULL,
amount DECIMAL(15,2) UNSIGNED,
start_date DATETIME,
end_date DATETIME,
PRIMARY KEY (competition_id,`ord`),
FOREIGN KEY (competition_id) REFERENCES competition(id));


CREATE TABLE IF NOT EXISTS climber (id INT  NOT NULL AUTO_INCREMENT,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
year_of_birth INT,
PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS registration (
competition_id INT NOT NULL,
climber_id INT NOT NULL,
start_number INT,
total_ord INT,
paid BOOLEAN,
created_date DATETIME,
paid_date DATETIME,
fee_ord INT,
PRIMARY KEY (competition_id, climber_id),
FOREIGN KEY (competition_id) REFERENCES competition(id),
FOREIGN KEY (climber_id) REFERENCES climber(id));



CREATE TABLE IF NOT EXISTS route (
competition_id INT NOT NULL,
`ord` INT NOT NULL,
`name` VARCHAR(255),
grade VARCHAR(255),
PRIMARY KEY (competition_id,`ord`),
FOREIGN KEY (competition_id) REFERENCES competition(id));

												   
												
CREATE TABLE IF NOT EXISTS route_climbed (
competition_id INT NOT NULL,
route_ord INT NOT NULL,
climber_id INT NOT NULL,
from_attempt INT,
PRIMARY KEY (competition_id, route_ord, climber_id),
FOREIGN KEY (competition_id, route_ord) REFERENCES route(competition_id,ORD),
FOREIGN KEY (climber_id) REFERENCES climber(id));

												
CREATE TABLE IF NOT EXISTS `user` (
id INT  NOT NULL AUTO_INCREMENT,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
username VARCHAR(255) NOT NULL,
`password` VARCHAR(255) NOT NULL,
PRIMARY KEY (id));
