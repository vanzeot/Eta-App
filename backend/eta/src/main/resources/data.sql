-- -----------------------------------------------------
-- EMPLOYEES
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
-- EQUIPMENT
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
-- LOCATIONS
-- -----------------------------------------------------

INSERT INTO location (code, name)
VALUES ('DEP-001','Main Deposit');

INSERT INTO location (code, name)
VALUES ('CON-001','Containment Building');

INSERT INTO location (code, name)
VALUES ('HYD-001','Hydraulic Center');

-- -----------------------------------------------------
-- NOTIFICATION
-- -----------------------------------------------------

INSERT INTO notification (number, title, description, status, date_created, date_closed, author_id, equipment_id, location_id)
VALUES ('NTF000001','OVERHEATING DURING OPERATION','Probably caused by dust.','CLOSED','2023-10-10 12:07:12','2023-10-14 15:07:12', 1, 1, 1);

INSERT INTO notification (number, title, description, status, date_created, date_closed, author_id, equipment_id, location_id)
VALUES ('NTF000002','SPARKLING NOISE DURING STARTUP','Need to investigate further.','CLOSED','2023-10-10 10:07:30','2023-10-10 10:07:30', 1, 1, 1);

INSERT INTO notification (number, title, description, status, date_created, author_id, equipment_id, location_id)
VALUES ('NTF000003','DRY PUMP - ALMOST NO OIL','Something caused it to dry earlier than usual.','PENDING','2023-10-14 15:07:12', 2, 3, 3);

INSERT INTO notification (number, title, description, status, date_created, author_id, equipment_id, location_id)
VALUES ('NTF000004','DUST IN THE ROLLER','Caused by lack of cleaning in the room','PENDING','2023-10-14 15:07:12', 3, 4, 2);

INSERT INTO notification (number, title, description, status, date_created, author_id, equipment_id, location_id)
VALUES ('NTF000005','RED LED IS DOWN','Investigate and repair.','PENDING','2023-10-14 15:07:12', 4, 4, 2);