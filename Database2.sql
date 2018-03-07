-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: 401_platform
-- ------------------------------------------------------
-- Server version	5.6.27

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
-- Table structure for table `ProjectAssignmentsTable`
--

DROP TABLE IF EXISTS `ProjectAssignmentsTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectAssignmentsTable` (
  `studentNumber` int(11) NOT NULL,
  `projectNumber` varchar(45) DEFAULT NULL,
  `projectName` varchar(45) DEFAULT NULL,
  `studentName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`studentNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectAssignmentsTable`
--

LOCK TABLES `ProjectAssignmentsTable` WRITE;
/*!40000 ALTER TABLE `ProjectAssignmentsTable` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProjectAssignmentsTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectRankings`
--

DROP TABLE IF EXISTS `ProjectRankings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectRankings` (
  `studentNumber` int(11) NOT NULL,
  `studentName` varchar(45) DEFAULT NULL,
  `projectNumber` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `projectName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectRankings`
--

LOCK TABLES `ProjectRankings` WRITE;
/*!40000 ALTER TABLE `ProjectRankings` DISABLE KEYS */;
INSERT INTO `ProjectRankings` VALUES (1,'Student1',2,3,'SummerCampReg'),(1,'Student1',21,4,'PositionControl'),(1,'Student1',3,1,'Sigma'),(1,'Student1',17,2,'Prop47Website'),(1,'Student1',16,5,'eFormsProject'),(2,'Student2',2,2,'SummerCampReg'),(2,'Student2',3,1,'Sigma'),(2,'Student2',17,5,'Prop47Website'),(2,'Student2',11,3,'PredictingElections'),(2,'Student2',4,4,'P2P4Health'),(3,'Student3',8,1,'AutismSocietyApp'),(3,'Student3',1,5,'Quizzly'),(3,'Student3',28,2,'LetterOfRecGenerator'),(3,'Student3',26,4,'iLegal'),(3,'Student3',15,3,'Cosmic-System'),(4,'Student4',8,2,'AutismSocietyApp'),(4,'Student4',1,5,'Quizzly'),(4,'Student4',21,4,'PositionControl'),(4,'Student4',26,1,'iLegal'),(4,'Student4',25,3,'Diabetes'),(5,'Student5',8,1,'AutismSocietyApp'),(5,'Student5',1,4,'Quizzly'),(5,'Student5',26,2,'iLegal'),(5,'Student5',25,5,'Diabetes'),(5,'Student5',6,3,'ChangingTheTutoringSphere'),(6,'Student6',13,3,'MarketingTechnologyPlatform'),(6,'Student6',7,4,'TrojanMotors'),(6,'Student6',17,2,'Prop47Website'),(6,'Student6',5,1,'SociallyConsciousMachineLearningBots'),(6,'Student6',4,5,'P2P4Health'),(7,'Student7',13,3,'MarketingTechnologyPlatform'),(7,'Student7',18,5,'ProstheticSpinalCord'),(7,'Student7',23,2,'LogoDetection'),(7,'Student7',24,1,'AIMedicalImaging'),(7,'Student7',11,4,'PredictingElections'),(8,'Student8',28,2,'LetterOfRecGenerator'),(8,'Student8',20,5,'QuantitativeTrading_DataExtractionPipeline'),(8,'Student8',23,3,'LogoDetection'),(8,'Student8',26,1,'iLegal'),(8,'Student8',11,4,'PredictingElections'),(9,'Student9',8,2,'AutismSocietyApp'),(9,'Student9',27,4,'401ProjectPlatform'),(9,'Student9',17,5,'Prop47Website'),(9,'Student9',22,3,'SpoilerAlert'),(9,'Student9',9,1,'AutismSocietyDB'),(10,'Student10',1,1,'Quizzly'),(10,'Student10',2,3,'SummerCampReg'),(10,'Student10',3,4,'Sigma'),(10,'Student10',22,5,'SpoilerAlert'),(10,'Student10',4,2,'P2P4Health'),(11,'Student11',23,2,'LogoDetection'),(11,'Student11',24,1,'AIMedicalImaging'),(11,'Student11',6,4,'ChangingTheTutoringSphere'),(11,'Student11',14,5,'PureFocusiOSApp'),(11,'Student11',4,3,'P2P4Health'),(12,'Student12',8,1,'AutismSocietyApp'),(12,'Student12',23,3,'LogoDetection'),(12,'Student12',24,5,'AIMedicalImaging'),(12,'Student12',26,2,'iLegal'),(12,'Student12',19,4,'QuantitativeTrading_DataScience'),(13,'Student13',13,4,'MarketingTechnologyPlatform'),(13,'Student13',18,1,'ProstheticSpinalCord'),(13,'Student13',23,3,'LogoDetection'),(13,'Student13',22,5,'SpoilerAlert'),(13,'Student13',29,2,'AutonomousDroneControl'),(14,'Student14',27,4,'401ProjectPlatform'),(14,'Student14',28,3,'LetterOfRecGenerator'),(14,'Student14',3,1,'Sigma'),(14,'Student14',17,2,'Prop47Website'),(14,'Student14',25,5,'Diabetes'),(15,'Student15',27,2,'401ProjectPlatform'),(15,'Student15',1,4,'Quizzly'),(15,'Student15',28,3,'LetterOfRecGenerator'),(15,'Student15',12,1,'Perfit'),(15,'Student15',4,5,'P2P4Health'),(16,'Student16',13,2,'MarketingTechnologyPlatform'),(16,'Student16',27,1,'401ProjectPlatform'),(16,'Student16',26,5,'iLegal'),(16,'Student16',19,3,'QuantitativeTrading_DataScience'),(16,'Student16',4,4,'P2P4Health'),(17,'Student17',13,4,'MarketingTechnologyPlatform'),(17,'Student17',18,2,'ProstheticSpinalCord'),(17,'Student17',22,5,'SpoilerAlert'),(17,'Student17',29,1,'AutonomousDroneControl'),(17,'Student17',19,3,'QuantitativeTrading_DataScience'),(18,'Student18',13,3,'MarketingTechnologyPlatform'),(18,'Student18',18,1,'ProstheticSpinalCord'),(18,'Student18',23,5,'LogoDetection'),(18,'Student18',11,2,'PredictingElections'),(18,'Student18',5,4,'SociallyConsciousMachineLearningBots'),(19,'Student19',13,1,'MarketingTechnologyPlatform'),(19,'Student19',8,4,'AutismSocietyApp'),(19,'Student19',2,5,'SummerCampReg'),(19,'Student19',22,3,'SpoilerAlert'),(19,'Student19',5,2,'SociallyConsciousMachineLearningBots'),(20,'Student20',8,1,'AutismSocietyApp'),(20,'Student20',26,2,'iLegal'),(20,'Student20',6,4,'ChangingTheTutoringSphere'),(20,'Student20',9,5,'AutismSocietyDB'),(20,'Student20',4,3,'P2P4Health'),(21,'Student21',8,4,'AutismSocietyApp'),(21,'Student21',18,1,'ProstheticSpinalCord'),(21,'Student21',17,5,'Prop47Website'),(21,'Student21',24,2,'AIMedicalImaging'),(21,'Student21',6,3,'ChangingTheTutoringSphere'),(22,'Student22',18,3,'ProstheticSpinalCord'),(22,'Student22',24,2,'AIMedicalImaging'),(22,'Student22',29,1,'AutonomousDroneControl'),(22,'Student22',11,4,'PredictingElections'),(22,'Student22',15,5,'Cosmic-System'),(23,'Student23',1,5,'Quizzly'),(23,'Student23',25,2,'Diabetes'),(23,'Student23',26,4,'iLegal'),(23,'Student23',14,1,'PureFocusiOSApp'),(23,'Student23',6,3,'ChangingTheTutoringSphere'),(24,'Student24',13,1,'MarketingTechnologyPlatform'),(24,'Student24',20,5,'QuantitativeTrading_DataExtractionPipeline'),(24,'Student24',24,3,'AIMedicalImaging'),(24,'Student24',5,2,'SociallyConsciousMachineLearningBots'),(24,'Student24',11,4,'PredictingElections'),(25,'Student25',28,5,'LetterOfRecGenerator'),(25,'Student25',26,3,'iLegal'),(25,'Student25',14,1,'PureFocusiOSApp'),(25,'Student25',6,2,'ChangingTheTutoringSphere'),(25,'Student25',12,4,'Perfit'),(26,'Student26',13,3,'MarketingTechnologyPlatform'),(26,'Student26',20,1,'QuantitativeTrading_DataExtractionPipeline'),(26,'Student26',17,2,'Prop47Website'),(26,'Student26',29,5,'AutonomousDroneControl'),(26,'Student26',11,4,'PredictingElections'),(27,'Student27',28,2,'LetterOfRecGenerator'),(27,'Student27',10,1,'SchoolOfFishApp'),(27,'Student27',22,5,'SpoilerAlert'),(27,'Student27',24,3,'AIMedicalImaging'),(27,'Student27',6,4,'ChangingTheTutoringSphere'),(28,'Student28',28,2,'LetterOfRecGenerator'),(28,'Student28',10,1,'SchoolOfFishApp'),(28,'Student28',22,5,'SpoilerAlert'),(28,'Student28',24,3,'AIMedicalImaging'),(28,'Student28',6,4,'ChangingTheTutoringSphere'),(29,'Student29',28,2,'LetterOfRecGenerator'),(29,'Student29',10,1,'SchoolOfFishApp'),(29,'Student29',22,5,'SpoilerAlert'),(29,'Student29',24,3,'AIMedicalImaging'),(29,'Student29',11,4,'PredictingElections'),(30,'Student30',13,1,'MarketingTechnologyPlatform'),(30,'Student30',20,3,'QuantitativeTrading_DataExtractionPipeline'),(30,'Student30',23,5,'LogoDetection'),(30,'Student30',29,4,'AutonomousDroneControl'),(30,'Student30',11,2,'PredictingElections'),(31,'Student31',13,4,'MarketingTechnologyPlatform'),(31,'Student31',18,3,'ProstheticSpinalCord'),(31,'Student31',20,5,'QuantitativeTrading_DataExtractionPipeline'),(31,'Student31',19,2,'QuantitativeTrading_DataScience'),(31,'Student31',11,1,'PredictingElections'),(32,'Student32',13,3,'MarketingTechnologyPlatform'),(32,'Student32',10,2,'SchoolOfFishApp'),(32,'Student32',24,5,'AIMedicalImaging'),(32,'Student32',12,1,'Perfit'),(32,'Student32',14,4,'PureFocusiOSApp'),(33,'Student33',18,2,'ProstheticSpinalCord'),(33,'Student33',7,4,'TrojanMotors'),(33,'Student33',10,3,'SchoolOfFishApp'),(33,'Student33',22,5,'SpoilerAlert'),(33,'Student33',29,1,'AutonomousDroneControl'),(34,'Student34',1,1,'Quizzly'),(34,'Student34',27,3,'401ProjectPlatform'),(34,'Student34',17,5,'Prop47Website'),(34,'Student34',25,2,'Diabetes'),(34,'Student34',26,4,'iLegal'),(35,'Student35',21,1,'PositionControl'),(35,'Student35',2,4,'SummerCampReg'),(35,'Student35',3,2,'Sigma'),(35,'Student35',17,3,'Prop47Website'),(35,'Student35',15,5,'Cosmic-System'),(36,'Student36',8,1,'AutismSocietyApp'),(36,'Student36',27,3,'401ProjectPlatform'),(36,'Student36',28,2,'LetterOfRecGenerator'),(36,'Student36',24,5,'AIMedicalImaging'),(36,'Student36',9,4,'AutismSocietyDB'),(37,'Student37',13,3,'MarketingTechnologyPlatform'),(37,'Student37',3,4,'Sigma'),(37,'Student37',16,5,'eFormsProject'),(37,'Student37',25,1,'Diabetes'),(37,'Student37',5,2,'SociallyConsciousMachineLearningBots'),(38,'Student38',13,1,'MarketingTechnologyPlatform'),(38,'Student38',7,2,'TrojanMotors'),(38,'Student38',28,4,'LetterOfRecGenerator'),(38,'Student38',19,3,'QuantitativeTrading_DataScience'),(38,'Student38',15,5,'Cosmic-System'),(39,'Student39',13,1,'MarketingTechnologyPlatform'),(39,'Student39',20,5,'QuantitativeTrading_DataExtractionPipeline'),(39,'Student39',24,3,'AIMedicalImaging'),(39,'Student39',11,2,'PredictingElections'),(39,'Student39',5,4,'SociallyConsciousMachineLearningBots'),(40,'Student40',13,2,'MarketingTechnologyPlatform'),(40,'Student40',21,1,'PositionControl'),(40,'Student40',3,3,'Sigma'),(40,'Student40',17,4,'Prop47Website'),(40,'Student40',14,5,'PureFocusiOSApp'),(41,'Student41',8,3,'AutismSocietyApp'),(41,'Student41',7,2,'TrojanMotors'),(41,'Student41',10,1,'SchoolOfFishApp'),(41,'Student41',9,4,'AutismSocietyDB'),(41,'Student41',15,5,'Cosmic-System'),(42,'Student42',1,5,'Quizzly'),(42,'Student42',28,4,'LetterOfRecGenerator'),(42,'Student42',2,2,'SummerCampReg'),(42,'Student42',3,1,'Sigma'),(42,'Student42',17,3,'Prop47Website'),(43,'Student43',27,1,'401ProjectPlatform'),(43,'Student43',28,2,'LetterOfRecGenerator'),(43,'Student43',21,4,'PositionControl'),(43,'Student43',3,3,'Sigma'),(43,'Student43',15,5,'Cosmic-System'),(44,'Student44',13,5,'MarketingTechnologyPlatform'),(44,'Student44',18,1,'ProstheticSpinalCord'),(44,'Student44',23,4,'LogoDetection'),(44,'Student44',29,2,'AutonomousDroneControl'),(44,'Student44',11,3,'PredictingElections'),(45,'Student45',10,3,'SchoolOfFishApp'),(45,'Student45',17,2,'Prop47Website'),(45,'Student45',23,5,'LogoDetection'),(45,'Student45',26,1,'iLegal'),(45,'Student45',29,4,'AutonomousDroneControl'),(46,'Student46',10,3,'SchoolOfFishApp'),(46,'Student46',17,2,'Prop47Website'),(46,'Student46',26,1,'iLegal'),(46,'Student46',15,4,'Cosmic-System'),(46,'Student46',5,5,'SociallyConsciousMachineLearningBots'),(47,'Student47',27,2,'401ProjectPlatform'),(47,'Student47',3,1,'Sigma'),(47,'Student47',17,3,'Prop47Website'),(47,'Student47',5,5,'SociallyConsciousMachineLearningBots'),(47,'Student47',4,4,'P2P4Health'),(48,'Student48',13,4,'MarketingTechnologyPlatform'),(48,'Student48',20,2,'QuantitativeTrading_DataExtractionPipeline'),(48,'Student48',23,3,'LogoDetection'),(48,'Student48',24,5,'AIMedicalImaging'),(48,'Student48',11,1,'PredictingElections'),(49,'Student49',13,4,'MarketingTechnologyPlatform'),(49,'Student49',20,2,'QuantitativeTrading_DataExtractionPipeline'),(49,'Student49',23,3,'LogoDetection'),(49,'Student49',24,5,'AIMedicalImaging'),(49,'Student49',11,1,'PredictingElections'),(50,'Student50',13,4,'MarketingTechnologyPlatform'),(50,'Student50',20,2,'QuantitativeTrading_DataExtractionPipeline'),(50,'Student50',23,3,'LogoDetection'),(50,'Student50',24,5,'AIMedicalImaging'),(50,'Student50',11,1,'PredictingElections'),(51,'Student51',10,4,'SchoolOfFishApp'),(51,'Student51',17,2,'Prop47Website'),(51,'Student51',22,3,'SpoilerAlert'),(51,'Student51',29,1,'AutonomousDroneControl'),(51,'Student51',5,5,'SociallyConsciousMachineLearningBots'),(52,'Student52',27,1,'401ProjectPlatform'),(52,'Student52',1,4,'Quizzly'),(52,'Student52',20,5,'QuantitativeTrading_DataExtractionPipeline'),(52,'Student52',23,3,'LogoDetection'),(52,'Student52',19,2,'QuantitativeTrading_DataScience'),(53,'Student53',27,2,'401ProjectPlatform'),(53,'Student53',28,3,'LetterOfRecGenerator'),(53,'Student53',10,5,'SchoolOfFishApp'),(53,'Student53',17,1,'Prop47Website'),(53,'Student53',3,4,'Sigma'),(54,'Student54',13,2,'MarketingTechnologyPlatform'),(54,'Student54',28,5,'LetterOfRecGenerator'),(54,'Student54',24,1,'AIMedicalImaging'),(54,'Student54',9,4,'AutismSocietyDB'),(54,'Student54',5,3,'SociallyConsciousMachineLearningBots'),(55,'Student55',8,1,'AutismSocietyApp'),(55,'Student55',28,4,'LetterOfRecGenerator'),(55,'Student55',26,5,'iLegal'),(55,'Student55',14,2,'PureFocusiOSApp'),(55,'Student55',9,3,'AutismSocietyDB'),(56,'Student56',13,1,'MarketingTechnologyPlatform'),(56,'Student56',20,2,'QuantitativeTrading_DataExtractionPipeline'),(56,'Student56',24,4,'AIMedicalImaging'),(56,'Student56',12,5,'Perfit'),(56,'Student56',19,3,'QuantitativeTrading_DataScience'),(57,'Student57',1,5,'Quizzly'),(57,'Student57',20,2,'QuantitativeTrading_DataExtractionPipeline'),(57,'Student57',3,4,'Sigma'),(57,'Student57',22,1,'SpoilerAlert'),(57,'Student57',24,3,'AIMedicalImaging'),(58,'Student58',7,3,'TrojanMotors'),(58,'Student58',24,1,'AIMedicalImaging'),(58,'Student58',29,2,'AutonomousDroneControl'),(58,'Student58',9,4,'AutismSocietyDB'),(58,'Student58',5,5,'SociallyConsciousMachineLearningBots'),(59,'Student59',8,1,'AutismSocietyApp'),(59,'Student59',28,4,'LetterOfRecGenerator'),(59,'Student59',26,5,'iLegal'),(59,'Student59',14,2,'PureFocusiOSApp'),(59,'Student59',9,3,'AutismSocietyDB'),(60,'Student60',1,3,'Quizzly'),(60,'Student60',28,2,'LetterOfRecGenerator'),(60,'Student60',22,5,'SpoilerAlert'),(60,'Student60',29,1,'AutonomousDroneControl'),(60,'Student60',6,4,'ChangingTheTutoringSphere'),(61,'Student61',13,4,'MarketingTechnologyPlatform'),(61,'Student61',28,1,'LetterOfRecGenerator'),(61,'Student61',20,5,'QuantitativeTrading_DataExtractionPipeline'),(61,'Student61',24,2,'AIMedicalImaging'),(61,'Student61',11,3,'PredictingElections'),(62,'Student62',1,5,'Quizzly'),(62,'Student62',10,2,'SchoolOfFishApp'),(62,'Student62',29,1,'AutonomousDroneControl'),(62,'Student62',26,3,'iLegal'),(62,'Student62',14,4,'PureFocusiOSApp'),(63,'Student63',27,1,'401ProjectPlatform'),(63,'Student63',28,2,'LetterOfRecGenerator'),(63,'Student63',10,3,'SchoolOfFishApp'),(63,'Student63',17,4,'Prop47Website'),(63,'Student63',26,5,'iLegal'),(64,'Student64',25,2,'Diabetes'),(64,'Student64',26,4,'iLegal'),(64,'Student64',14,1,'PureFocusiOSApp'),(64,'Student64',6,3,'ChangingTheTutoringSphere'),(64,'Student64',4,5,'P2P4Health'),(65,'Student65',2,3,'SummerCampReg'),(65,'Student65',21,5,'PositionControl'),(65,'Student65',17,2,'Prop47Website'),(65,'Student65',3,4,'Sigma'),(65,'Student65',22,1,'SpoilerAlert'),(66,'Student66',8,1,'AutismSocietyApp'),(66,'Student66',28,4,'LetterOfRecGenerator'),(66,'Student66',26,5,'iLegal'),(66,'Student66',14,2,'PureFocusiOSApp'),(66,'Student66',9,3,'AutismSocietyDB'),(67,'Student67',13,5,'MarketingTechnologyPlatform'),(67,'Student67',1,1,'Quizzly'),(67,'Student67',2,3,'SummerCampReg'),(67,'Student67',25,4,'Diabetes'),(67,'Student67',4,2,'P2P4Health'),(68,'Student68',8,4,'AutismSocietyApp'),(68,'Student68',18,2,'ProstheticSpinalCord'),(68,'Student68',10,5,'SchoolOfFishApp'),(68,'Student68',29,1,'AutonomousDroneControl'),(68,'Student68',6,3,'ChangingTheTutoringSphere'),(69,'Student69',13,3,'MarketingTechnologyPlatform'),(69,'Student69',27,1,'401ProjectPlatform'),(69,'Student69',21,2,'PositionControl'),(69,'Student69',20,5,'QuantitativeTrading_DataExtractionPipeline'),(69,'Student69',19,4,'QuantitativeTrading_DataScience');
/*!40000 ALTER TABLE `ProjectRankings` ENABLE KEYS */;
UNLOCK TABLES;

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
  `Max_size` int(11) DEFAULT NULL,
  `Min_size` int(11) DEFAULT NULL,
  PRIMARY KEY (`Project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Projects`
--

LOCK TABLES `Projects` WRITE;
/*!40000 ALTER TABLE `Projects` DISABLE KEYS */;
INSERT INTO `Projects` VALUES (0,0,'Quizzly',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,8,6),(1,1,'SummerCampReg',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,8,4),(2,2,'Sigma',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(3,3,'P2P4Health',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,12,9),(4,4,'SociallyConsciousMachineLearningBots',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(5,5,'ChangingTheTutoringSphere',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,5,3),(6,6,'TrojanMotors',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,6,4),(7,7,'AutismSocietyApp',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,3,2),(8,8,'AutismSocietyDB',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,3,2),(9,9,'SchoolOfFishApp',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,3,2),(10,10,'PredictingElections',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,5,4),(11,11,'Perfit',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,6,4),(12,12,'MarketingTechnologyPlatform',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,6,4),(13,13,'PureFocusiOSApp',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(14,14,'Cosmic-System',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(15,15,'eFormsProject',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(16,16,'Prop47Website',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,3,2),(17,17,'ProstheticSpinalCord',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,6,3),(18,18,'QuantitativeTrading_DataScience',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(19,19,'QuantitativeTrading_DataExtractionPipeline',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(20,20,'PositionControl',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(21,21,'SpoilerAlert',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(22,22,'LogoDetection',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(23,23,'AIMedicalImaging',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(24,24,'Diabetes',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,5,3),(25,25,'iLegal',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(26,26,'401ProjectPlatform',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(27,27,'LetterOfRecGenerator',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(28,28,'AutonomousDroneControl',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,4,3),(29,29,'The Cyborg Project',NULL,NULL,NULL,NULL,'Pending Approval',NULL,NULL,NULL,NULL,NULL,NULL,6,3);
/*!40000 ALTER TABLE `Projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RankingData`
--

DROP TABLE IF EXISTS `RankingData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RankingData` (
  `projectNumber` int(11) NOT NULL,
  `projectName` varchar(45) DEFAULT NULL,
  `sum_p` varchar(45) DEFAULT NULL,
  `p_max` varchar(45) DEFAULT NULL,
  `n_interested` varchar(45) DEFAULT NULL,
  `cutoff` varchar(45) DEFAULT NULL,
  `popularity` varchar(45) DEFAULT NULL,
  `projectSatScore` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`projectNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RankingData`
--

LOCK TABLES `RankingData` WRITE;
/*!40000 ALTER TABLE `RankingData` DISABLE KEYS */;
/*!40000 ALTER TABLE `RankingData` ENABLE KEYS */;
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
INSERT INTO `Users` VALUES (1,1,'Student','Joan Hong','Senior','joanhong@usc.edu','joanhong','2eg9hfb4',NULL,27,NULL,NULL,'Spring 2018'),(2,2,'Student','Tony Elevathingal','Senior','elevathi@usc.edu','elevathi','3r5bfh6fd',NULL,27,NULL,NULL,NULL),(3,3,'Student','Shantanu Gupta','Senior','shantang@usc.edu','shantang','3hfk5sm0','2132455631',27,NULL,NULL,'Spring 2018'),(4,4,'Student','Ingrid Wang','Senior','ingridwa@usc.edu','ingridwa','7ydui3id4',NULL,27,NULL,NULL,'Spring 2018'),(5,5,'Student','Navneeth Pillai','Senior','ndpillai@usc.edu','ndpillai','8yutg7bs',NULL,27,NULL,NULL,'Spring 2018'),(6,6,'Admin','Jeffrey Miller',NULL,'jeffrey.miller@usc.edu','jeffrey.miller','9xyz9fxu',NULL,NULL,'USC',NULL,NULL);
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

-- Dump completed on 2018-03-06 16:56:26
