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
VALUES ('NTF000001','OVERHEATING DURING OPERATION','Probably caused by dust.','CLOSED','2023-09-10 12:07:12','2023-09-10 17:10:12', 1, 1, 1);

INSERT INTO notification (number, title, description, status, date_created, date_closed, author_id, equipment_id, location_id)
VALUES ('NTF000002','SPARKLING NOISE DURING STARTUP','Need to investigate further.','CLOSED','2023-09-13 10:07:30','2023-09-13 18:09:00', 1, 1, 1);

INSERT INTO notification (number, title, description, status, date_created,  date_closed, author_id, equipment_id, location_id)
VALUES ('NTF000003','PUMP STOPPED OPERATING','Suddenly the pump has stopped. Urgent maintenance needed','CLOSED','2023-09-19 14:30:02', '2023-09-19 16:32:09', 1, 1, 3);

INSERT INTO notification (number, title, description, status, date_created, author_id, equipment_id, location_id)
VALUES ('NTF000004','ROLLER W/ HIGH CONSUMPTION','Investigate and repair.','PROCESSING','2023-09-29 15:07:12', 4, 4, 2);

INSERT INTO notification (number, title, description, status, date_created, author_id, equipment_id, location_id)
VALUES ('NTF000005','DRY PUMP - ALMOST NO OIL','Something caused it to dry earlier than usual.','PENDING','2023-10-01 15:07:12', 2, 3, 3);

INSERT INTO notification (number, title, description, status, date_created, author_id, equipment_id, location_id)
VALUES ('NTF000006','DUST IN THE ROLLER','Caused by lack of cleaning in the room','PENDING','2023-10-14 15:07:12', 3, 4, 2);

INSERT INTO notification (number, title, description, status, date_created, author_id, equipment_id, location_id)
VALUES ('NTF000007','GREEN LED IS DOWN','Investigate and repair.','PENDING','2023-10-14 16:07:12', 4, 4, 2);

-- -----------------------------------------------------
-- ORDER
-- -----------------------------------------------------

INSERT INTO `order` (number, title, description, status, date_created, date_closed, author_id, notification_id)
VALUES ('ORD000001','PUMP AND ROOM CLEANING','Room and equipment cleaning required.','FINISHED','2023-09-10 12:12:50','2023-09-10 17:10:12', 1, 1);

INSERT INTO `order` (number, title, description, status, date_created, date_closed, author_id, notification_id)
VALUES ('ORD000002','OPEN THE PUMP AND REPAIR','Investigate the sparkling noises and fix them.','FINISHED','2023-09-13 11:02:34','2023-09-13 18:09:00', 1, 2);

INSERT INTO `order` (number, title, description, status, date_created, date_closed, author_id, notification_id)
VALUES ('ORD000003','INVESTIGATE AND REPAIR THE PUMP','Investigate and fix. It has stopped suddenly.','ABORTED','2023-09-19 14:39:45','2023-09-19 16:32:09', 1, 3);

INSERT INTO `order` (number, title, description, status, date_created, author_id, notification_id)
VALUES ('ORD000004','ENERGY ANALYZER ON ROLLER','Install and observe for 2 hours.','BACKLOG','2023-09-29 15:42:07', 4, 4);

-- -----------------------------------------------------
-- CONFIRMATION
-- -----------------------------------------------------

INSERT INTO confirmation (number, type, date_created, date_started, date_finished, executor_id, order_id)
VALUES ('CNF000001','FINAL','2023-09-10 17:21:34','2023-09-10 13:15:00', '2023-09-10 17:10:00',2,1);

INSERT INTO confirmation (number, type, date_created, date_started, date_finished, executor_id, order_id)
VALUES ('CNF000002','PARTIAL','2023-09-13 12:02:11','2023-09-13 11:30:00', '2023-09-13 12:00:00',2,2);

INSERT INTO confirmation (number, type, date_created, date_started, date_finished, executor_id, order_id)
VALUES ('CNF000003','FINAL','2023-09-13 13:52:54','2023-09-13 13:15:00', '2023-09-13 13:45:00',2,2);