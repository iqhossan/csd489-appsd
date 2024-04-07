/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 8.0.33 : Database - dental_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`dental_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dental_db`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `addId` int NOT NULL AUTO_INCREMENT,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `zip` int DEFAULT NULL,
  `companyId` int DEFAULT NULL,
  PRIMARY KEY (`addId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `address` */

insert  into `address`(`addId`,`street1`,`street2`,`city`,`state`,`zip`,`companyId`) values (1,'6200 Wilshire Blvd','#1508, Los Angeles','Plano','TX',90048,NULL),(2,'309 S Vermont Ave',NULL,'Los Angeles','CA',90020,NULL),(3,'30 Central Park','S #13C','New York','NY',10019,NULL),(4,'110 E 40th','St #104','Plano','TX',75024,NULL),(5,'1000 North','4th Street','Fairfield','IA',52557,NULL);

/*Table structure for table `appointments` */

DROP TABLE IF EXISTS `appointments`;

CREATE TABLE `appointments` (
  `appointId` bigint NOT NULL AUTO_INCREMENT,
  `appointDate` date DEFAULT NULL,
  `appointTime` time DEFAULT NULL,
  `patientId` bigint DEFAULT NULL,
  `dentistId` bigint DEFAULT NULL,
  `surgeryLocationId` int DEFAULT NULL,
  `requestAppointId` bigint DEFAULT NULL,
  PRIMARY KEY (`appointId`),
  KEY `FK_patient_appointments` (`patientId`),
  KEY `FK_dentist_appointments` (`dentistId`),
  KEY `FK_surgeryloc_appointments` (`surgeryLocationId`),
  CONSTRAINT `FK_dentist_appointments` FOREIGN KEY (`dentistId`) REFERENCES `dentists` (`dentistId`),
  CONSTRAINT `FK_patient_appointments` FOREIGN KEY (`patientId`) REFERENCES `patients` (`patientId`),
  CONSTRAINT `FK_surgeryloc_appointments` FOREIGN KEY (`surgeryLocationId`) REFERENCES `suregerylocation` (`locationId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `appointments` */

insert  into `appointments`(`appointId`,`appointDate`,`appointTime`,`patientId`,`dentistId`,`surgeryLocationId`,`requestAppointId`) values (1,'2024-07-05','15:45:00',3,2,1,NULL);

/*Table structure for table `dentists` */

DROP TABLE IF EXISTS `dentists`;

CREATE TABLE `dentists` (
  `dentistId` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(100) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `maxAppoint` int DEFAULT '5',
  PRIMARY KEY (`dentistId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `dentists` */

insert  into `dentists`(`dentistId`,`firstName`,`lastName`,`phoneNumber`,`specialization`,`maxAppoint`) values (1,'Sam','Christensen','+1 323-931-2000','FR',5),(2,'Harold','Bernier','+1 310-906-1300','SS',5),(3,'Henry','Aldridge','+1 213-765-0004','FF',5),(4,'Bill','Luveni','+1 323-269-7367','TT',5),(5,'Edward','Fones','+1 310-858-7373','Orthodontics',5),(6,'Arif','Alvi','+1 310-765-7441','Orthodontics',5);

/*Table structure for table `patients` */

DROP TABLE IF EXISTS `patients`;

CREATE TABLE `patients` (
  `patientId` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `billingDue` decimal(18,3) DEFAULT '0.000',
  `addressId` int DEFAULT NULL,
  PRIMARY KEY (`patientId`),
  KEY `FK_patients` (`addressId`),
  CONSTRAINT `FK_patients` FOREIGN KEY (`addressId`) REFERENCES `address` (`addId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `patients` */

insert  into `patients`(`patientId`,`firstName`,`lastName`,`phoneNumber`,`email`,`billingDue`,`addressId`) values (1,'John','Newbrough','+1 212-877-0405','john.newbrough@gmail.com','0.000',2),(2,'Cheddi','Jagan','+1 212-826-2322','cheddi.jagan@gmail.com','0.000',1),(3,'Hugo','Sánchez','+1 212-826-5000','hugo.sánchez@gmail.com','0.000',4),(4,'Gerrit','Wolsink','+1 212-877-0405','gerrit.wolsink@gmail.com','0.000',3),(5,'Ron','Packard','+1 212-265-7724','ron.packard@gmail.com','0.000',5);

/*Table structure for table `suregerylocation` */

DROP TABLE IF EXISTS `suregerylocation`;

CREATE TABLE `suregerylocation` (
  `locationId` int NOT NULL AUTO_INCREMENT,
  `locationName` varchar(255) DEFAULT NULL,
  `addressId` int DEFAULT NULL,
  `companyId` int DEFAULT NULL,
  PRIMARY KEY (`locationId`),
  KEY `FK_suregerylocation` (`addressId`),
  CONSTRAINT `FK_suregerylocation` FOREIGN KEY (`addressId`) REFERENCES `address` (`addId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `suregerylocation` */

insert  into `suregerylocation`(`locationId`,`locationName`,`addressId`,`companyId`) values (1,'Mid-Wilshire Dental',3,NULL),(2,'National Dental Chelsea',4,NULL),(3,'Park South Dentistry',1,NULL),(4,'209 NYC Dental',2,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
