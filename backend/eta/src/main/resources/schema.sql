DROP SCHEMA IF EXISTS `eta-app`;
CREATE SCHEMA `eta-app`;

USE `eta-app` ;

-- -----------------------------------------------------
-- EMPLOYEES TABLE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eta-app`.`employee` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `registration` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `active` BIT DEFAULT 1,
  `date_created` DATETIME(0) DEFAULT NULL,
  `last_updated` DATETIME(0) DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- EQUIPMENT TABLE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eta-app`.`equipment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- LOCATIONS TABLE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eta-app`.`location` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `notification`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `confirmation`;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `notification` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `number` VARCHAR(10) NOT NULL,
    `title` VARCHAR(40) NULL DEFAULT NULL,
    `description` VARCHAR(255) NULL DEFAULT NULL,
    `status` ENUM('PENDING', 'PROCESSING', 'CLOSED'),
    `date_created` DATETIME(0) DEFAULT NULL,
    `date_closed` DATETIME(0) DEFAULT NULL,
    `author_id` bigint DEFAULT NULL,
    `equipment_id` bigint DEFAULT NULL,
    `location_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_notification_author_id` FOREIGN KEY (`author_id`)
        REFERENCES `employee` (`id`),
    CONSTRAINT `FK_equipment_id` FOREIGN KEY (`equipment_id`)
        REFERENCES `equipment` (`id`),
    CONSTRAINT `FK_location_id` FOREIGN KEY (`location_id`)
        REFERENCES `location` (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `number` VARCHAR(10) NOT NULL,
    `title` VARCHAR(40) NULL DEFAULT NULL,
    `description` VARCHAR(255) NULL DEFAULT NULL,
    `status` ENUM('BACKLOG', 'FINISHED', 'ABORTED'),
    `date_created` DATETIME(0) DEFAULT NULL,
    `date_closed` DATETIME(0) DEFAULT NULL,
    `author_id` bigint DEFAULT NULL,
    `notification_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_order_author_id` FOREIGN KEY (`author_id`)
        REFERENCES `employee` (`id`),
    CONSTRAINT `FK_notification_id` FOREIGN KEY (`notification_id`)
        REFERENCES `notification` (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `confirmation` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `number`  VARCHAR(10) NOT NULL,
    `type` ENUM('PARTIAL', 'FINAL'),
    `date_created` DATETIME(0) DEFAULT NULL,
    `date_started` DATETIME(0) DEFAULT NULL,
    `date_finished` DATETIME(0) DEFAULT NULL,
    `executor_id` bigint DEFAULT NULL,
    `order_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_executor_id` FOREIGN KEY (`executor_id`)
        REFERENCES `employee` (`id`),
    CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`)
        REFERENCES `order` (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;