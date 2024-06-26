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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-12 10:47:27
