-- -----------------------------------------------------
-- FIRST LOAD OF EMPLOYEES
-- -----------------------------------------------------

INSERT INTO employee (registration, name, username, email, active, date_created)
VALUES ('00001','Otavio','otavio','otavio@eta.com', 1, NOW());

INSERT INTO employee (registration, name, username, email, active, date_created)
VALUES ('00002','Heitor','heitor','heitor@eta.com', 1, NOW());

INSERT INTO employee (registration, name, username, email, active, date_created)
VALUES ('00003','Danielle','danielle','danielle@eta.com', 1, NOW());

INSERT INTO employee (registration, name, username, email, active, date_created)
VALUES ('00004','Elis','elis','elis@eta.com', 1, NOW());

INSERT INTO employee (registration, name, username, email, active, date_created)
VALUES ('00005','Antonio','antonio','antonio@eta.com', 1, NOW());

-- -----------------------------------------------------
-- FIRST LOAD OF EQUIPMENT
-- -----------------------------------------------------

INSERT INTO equipment (code, name)
VALUES ('EP-01-MM','MM Electric Pump');

INSERT INTO equipment (code, name)
VALUES ('EP-02-WE','WEG Electric Pump');

INSERT INTO equipment (code, name)
VALUES ('HP-01-YE','Yeoshee Hydraulic Pump');

INSERT INTO equipment (code, name)
VALUES ('RC-01-IR','Interroll Powered Roller Conveyor');


-- -----------------------------------------------------
-- FIRST LOAD OF LOCATIONS
-- -----------------------------------------------------

INSERT INTO location (code, name)
VALUES ('DEP-001','Main Deposit');

INSERT INTO location (code, name)
VALUES ('CON-001','Containment Building');

INSERT INTO location (code, name)
VALUES ('HYD-001','Hydraulic Center');

