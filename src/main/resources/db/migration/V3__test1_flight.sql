-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: test1
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `delay_started_at` datetime DEFAULT NULL,
  `departure_country` varchar(255) DEFAULT NULL,
  `destination_country` varchar(255) DEFAULT NULL,
  `distance` int DEFAULT NULL,
  `ended_at` datetime DEFAULT NULL,
  `estimated_flight_time` time DEFAULT NULL,
  `flight_status` varchar(255) DEFAULT NULL,
  `air_company_id` bigint DEFAULT NULL,
  `airplane_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKebvst1vfvhmhgn73mqs1vt8m7` (`air_company_id`),
  KEY `FKb8t4272gfgo1feyyidvscbjm0` (`airplane_id`),
  CONSTRAINT `FKb8t4272gfgo1feyyidvscbjm0` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`),
  CONSTRAINT `FKebvst1vfvhmhgn73mqs1vt8m7` FOREIGN KEY (`air_company_id`) REFERENCES `air_company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--
--
LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'2019-12-27 12:31:00',NULL,'country1','country2',542,'2020-08-16 22:31:00','04:22:00','COMPLETED',1,1),(2,'2020-12-11 04:05:00',NULL,'country1','country2',254,NULL,NULL,'PENDING',1,2),(3,'2020-05-09 04:41:00',NULL,'country1','country2',7855,'2020-05-09 21:41:00','01:54:00','COMPLETED',2,3),(4,'2020-08-16 19:18:00',NULL,'country1','country2',663,'2020-08-16 22:31:00','08:34:00','COMPLETED',5,1),(5,'2020-09-16 20:12:00',NULL,'country1','country2',221,NULL,'04:01:00','ACTIVE',4,2),(6,'2020-05-05 17:31:00','2020-07-21 16:22:00','country1','country2',788,NULL,NULL,'DELAYED',3,4);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-01 19:03:34
