-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: 401_Platform
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Projects`
--

DROP TABLE IF EXISTS `Projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Projects` (
  `Project_id` int(11) NOT NULL,
  `Project_number` int(11) DEFAULT NULL,
  `Project_name` varchar(45) DEFAULT NULL,
  `Stakeholder_name` varchar(45) DEFAULT NULL,
  `Stakeholder_userno` varchar(45) DEFAULT NULL,
  `Leader_userno` varchar(45) DEFAULT NULL,
  `Leader_name` varchar(45) DEFAULT NULL,
  `Project_status` varchar(45) DEFAULT NULL,
  `Due_date` date DEFAULT NULL,
  `Semester` varchar(45) DEFAULT NULL,
  `Project_size` int(11) DEFAULT NULL,
  `Technologies Expected` varchar(500) DEFAULT NULL,
  `BackgroundRequested` varchar(500) DEFAULT NULL,
  `Description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Projects`
--

LOCK TABLES `Projects` WRITE;
/*!40000 ALTER TABLE `Projects` DISABLE KEYS */;
/*!40000 ALTER TABLE `Projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `User_id` int(11) NOT NULL,
  `User_number` int(11) NOT NULL,
  `User_type` varchar(45) DEFAULT NULL,
  `Full_name` varchar(45) DEFAULT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Phone_number` varchar(45) DEFAULT NULL,
  `Project_number` int(11) DEFAULT NULL,
  `Company_name` varchar(45) DEFAULT NULL,
  `USC_ID` varchar(45) DEFAULT NULL,
  `Semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`User_id`),
  UNIQUE KEY `User_number_UNIQUE` (`User_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,1,'Student','Joan Hong','Senior','joanhong@usc.edu','joanhong','2eg9hfb4',NULL,27,NULL,NULL,'Spring 2018'),(2,2,'Student','Tony Elevathingal','Senior','elevathi@usc.edu','elevathi','3r5bfh6fd',NULL,27,NULL,NULL,NULL),(3,3,'Student','Shantanu Gupta','Senior','shantang@usc.edu','shantang','3hfk5sm0','2132455631',27,NULL,NULL,'Spring 2018');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-01 12:57:36
