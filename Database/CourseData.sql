-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `Assignment`
--

DROP TABLE IF EXISTS `Assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Assignment` (
  `assignmentID` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `Course_courseID` int(11) NOT NULL,
  PRIMARY KEY (`assignmentID`,`Course_courseID`),
  KEY `fk_Assignment_Course1_idx` (`Course_courseID`),
  CONSTRAINT `fk_Assignment_Course1` FOREIGN KEY (`Course_courseID`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Assignment`
--

LOCK TABLES `Assignment` WRITE;
/*!40000 ALTER TABLE `Assignment` DISABLE KEYS */;
INSERT INTO `Assignment` VALUES (1,'5DD Airport Maintenance Assignment (Part 1) - SECTION A','2018-03-12 00:00:00',1);
/*!40000 ALTER TABLE `Assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Campus`
--

DROP TABLE IF EXISTS `Campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Campus` (
  `CampusID` int(11) NOT NULL,
  `campusName` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CampusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Campus`
--

LOCK TABLES `Campus` WRITE;
/*!40000 ALTER TABLE `Campus` DISABLE KEYS */;
INSERT INTO `Campus` VALUES (1,'Adelaide','120 Currie St, Adelaide SA 5000'),(2,'Adelaide College of the Arts','39 Light Square, ADELAIDE SA 5000');
/*!40000 ALTER TABLE `Campus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Campus_has_Course`
--

DROP TABLE IF EXISTS `Campus_has_Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Campus_has_Course` (
  `Campus_CampusID` int(11) NOT NULL,
  `Course_courseID` int(11) NOT NULL,
  PRIMARY KEY (`Campus_CampusID`,`Course_courseID`),
  KEY `fk_Campus_has_Course_Course1_idx` (`Course_courseID`),
  KEY `fk_Campus_has_Course_Campus1_idx` (`Campus_CampusID`),
  CONSTRAINT `fk_Campus_has_Course_Campus1` FOREIGN KEY (`Campus_CampusID`) REFERENCES `campus` (`campusid`),
  CONSTRAINT `fk_Campus_has_Course_Course1` FOREIGN KEY (`Course_courseID`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Campus_has_Course`
--

LOCK TABLES `Campus_has_Course` WRITE;
/*!40000 ALTER TABLE `Campus_has_Course` DISABLE KEYS */;
INSERT INTO `Campus_has_Course` VALUES (1,1);
/*!40000 ALTER TABLE `Campus_has_Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `courseID` int(11) NOT NULL,
  `courseName` varchar(45) DEFAULT NULL,
  `courseCode` varchar(45) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `room` varchar(5) DEFAULT NULL,
  `Lecture_lectureID` int(11) NOT NULL,
  PRIMARY KEY (`courseID`),
  KEY `fk_Course_Lecture1_idx` (`Lecture_lectureID`),
  CONSTRAINT `fk_Course_Lecture1` FOREIGN KEY (`Lecture_lectureID`) REFERENCES `lecture` (`lectureid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'Design a Database','5DD','2018-02-12','2018-06-11','14:00:00','16:00:00','B03',2);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course_has_Student`
--

DROP TABLE IF EXISTS `Course_has_Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course_has_Student` (
  `Course_courseID` int(11) NOT NULL,
  `Student_studentID` int(11) NOT NULL,
  PRIMARY KEY (`Course_courseID`,`Student_studentID`),
  KEY `fk_Course_has_Student_Student1_idx` (`Student_studentID`),
  KEY `fk_Course_has_Student_Course1_idx` (`Course_courseID`),
  CONSTRAINT `fk_Course_has_Student_Course1` FOREIGN KEY (`Course_courseID`) REFERENCES `course` (`courseid`),
  CONSTRAINT `fk_Course_has_Student_Student1` FOREIGN KEY (`Student_studentID`) REFERENCES `student` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course_has_Student`
--

LOCK TABLES `Course_has_Student` WRITE;
/*!40000 ALTER TABLE `Course_has_Student` DISABLE KEYS */;
INSERT INTO `Course_has_Student` VALUES (1,103500);
/*!40000 ALTER TABLE `Course_has_Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lecture`
--

DROP TABLE IF EXISTS `Lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Lecture` (
  `lectureID` int(11) NOT NULL,
  `lectureName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`lectureID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lecture`
--

LOCK TABLES `Lecture` WRITE;
/*!40000 ALTER TABLE `Lecture` DISABLE KEYS */;
INSERT INTO `Lecture` VALUES (1,'Santi Ruiz'),(2,'KT Lau');
/*!40000 ALTER TABLE `Lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session`
--

DROP TABLE IF EXISTS `Session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Session` (
  `sessionID` int(11) NOT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `topic` varchar(500) DEFAULT NULL,
  `sessioncNO` varchar(45) DEFAULT NULL,
  `sessionDate` date DEFAULT NULL,
  `Course_courseID` int(11) NOT NULL,
  PRIMARY KEY (`sessionID`,`Course_courseID`),
  KEY `fk_Session_Course1_idx` (`Course_courseID`),
  CONSTRAINT `fk_Session_Course1` FOREIGN KEY (`Course_courseID`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
INSERT INTO `Session` VALUES (1,'Subject overview, database systems life cycle, terminologies, modelling concepts and Entity Relationship Diagram (ERD)','Subject overview\n\nIntroduction to modelling concepts \n\nRelational database concepts \n\nTerminologies: different record keys \n\nInsert, update and delete anomalies \n\nDatabase requirements \n\nER modelling concept & notations\n\n-DegreeSession of relationships \n\n-Cardinality constraints \n\n-Membership constraints \n\nBusiness rules and assumptions ','1','2018-02-12',1);
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `studentID` int(11) NOT NULL,
  `studentName` varchar(45) DEFAULT NULL,
  `loginPassword` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (103500,'Mei Lung Cheng','12345');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Test`
--

DROP TABLE IF EXISTS `Test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Test` (
  `testID` int(11) NOT NULL,
  `testName` varchar(45) DEFAULT NULL,
  `testDate` datetime DEFAULT NULL,
  `Course_courseID` int(11) NOT NULL,
  PRIMARY KEY (`testID`,`Course_courseID`),
  KEY `fk_Test_Course_idx` (`Course_courseID`),
  CONSTRAINT `fk_Test_Course` FOREIGN KEY (`Course_courseID`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Test`
--

LOCK TABLES `Test` WRITE;
/*!40000 ALTER TABLE `Test` DISABLE KEYS */;
INSERT INTO `Test` VALUES (1,'Normalisation Test','2018-04-06 00:00:00',1);
/*!40000 ALTER TABLE `Test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-23  9:16:30
