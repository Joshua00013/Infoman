-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: scholarshipdb
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
  `ApplicantID` int NOT NULL AUTO_INCREMENT,
  `ScholarshipID` char(9) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Sex` char(1) NOT NULL,
  `Birthday` date NOT NULL,
  `Birthplace` varchar(75) NOT NULL,
  `Citizenship` varchar(20) NOT NULL,
  `DualCitizStatus` char(3) NOT NULL,
  `ContactNo` varchar(15) NOT NULL,
  `EmailAddress` varchar(50) NOT NULL,
  `PermAddress` varchar(100) NOT NULL,
  `NoSiblings` int NOT NULL,
  `BirthOrder` int NOT NULL,
  `Course` varchar(20) NOT NULL,
  `UnivName` varchar(50) NOT NULL,
  `UnivAddress` varchar(100) NOT NULL,
  `PassportStatus` char(3) NOT NULL,
  PRIMARY KEY (`ApplicantID`),
  KEY `ScholarshipID_idx` (`ScholarshipID`),
  CONSTRAINT `ScholarshipID` FOREIGN KEY (`ScholarshipID`) REFERENCES `scholarshiptype` (`ScholarshipID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
INSERT INTO `applicant` VALUES (2,'MERIT','Joshua','M','2024-06-04','Manila','Filipino','No','09164608064','joshuamadrilejos@gmail.com','Blk 6 Lot 4',1,1,'BSIT','PUP','PUP MAIN','No'),(3,'RA 10612','a','M','2024-06-03','a','a','Yes','09164608064','a@gmail.com','blk6 lot 4',1,1,'BSIT','PUP','PUP MAIN','No'),(4,'MERIT','b','M','2024-06-04','b','b','No','097777777','b@gmail.com','blk 5 lot 6',1,1,'BSIT','PUP','PUP MAIN','No'),(5,'MERIT','g','M','2024-06-08','g','g','No','0955555555','g@gmail.com','g',1,1,'BSIT','PUP','PUPMAIN','No'),(6,'RA 10612','a','M','2024-06-01','a','a','No','0933445566','aak@gmail.com','houze',5,4,'BSIT','PUP','PUP MAIN','No');
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parentguardian_info`
--

DROP TABLE IF EXISTS `parentguardian_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parentguardian_info` (
  `ParentGuardianID` int NOT NULL AUTO_INCREMENT,
  `ApplicantID` int NOT NULL,
  `ParentGuardianName` varchar(50) DEFAULT NULL,
  `Relationship` varchar(10) DEFAULT NULL,
  `EducAttainment` varchar(50) DEFAULT NULL,
  `Occupation` varchar(20) DEFAULT NULL,
  `EmployeeName` varchar(50) DEFAULT NULL,
  `AnnualIncome` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`ParentGuardianID`),
  KEY `ApplicantID_idx` (`ApplicantID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parentguardian_info`
--

LOCK TABLES `parentguardian_info` WRITE;
/*!40000 ALTER TABLE `parentguardian_info` DISABLE KEYS */;
INSERT INTO `parentguardian_info` VALUES (1,-1,'a','Mother','a','a',NULL,3.00),(2,-1,'b','Father','b','b',NULL,4.00),(3,5,'h','Mother','h','h',NULL,3.00),(4,5,'j','Father','j','j',NULL,6.00),(5,6,'m','Mother','m','m',NULL,25.00),(6,6,'m','Mother','m','m',NULL,13.00);
/*!40000 ALTER TABLE `parentguardian_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scholarshiptype`
--

DROP TABLE IF EXISTS `scholarshiptype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scholarshiptype` (
  `ScholarshipID` char(9) NOT NULL,
  `Scholarshipname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ScholarshipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scholarshiptype`
--

LOCK TABLES `scholarshiptype` WRITE;
/*!40000 ALTER TABLE `scholarshiptype` DISABLE KEYS */;
INSERT INTO `scholarshiptype` VALUES ('MERIT','DOST-SEI Merit Scholarship Program'),('RA 10612','Fast-Tracked S&T Scholarship Act of 2013 '),('RA 7687','Science and Technology Scholarship Act of 1994');
/*!40000 ALTER TABLE `scholarshiptype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-12 10:48:29
