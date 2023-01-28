-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPRbaza12
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `date_time` datetime NOT NULL,
  `duration` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Amsterdam','Action','2022-12-21 19:10:00',120),(2,'Avatar','Sci-Fi','2022-12-07 18:30:00',200),(3,'Don\'t worry darling','Drama','2022-12-22 20:20:00',100),(5,'Home for Christmas','Family, Comedy','2022-12-07 23:20:00',150),(6,'Ticket to paradise','Comedy','2023-01-08 18:20:00',120),(8,'Transformers1','Action, Sci-Fi','2023-01-07 14:30:00',120),(9,'Avatar','Action','2022-12-05 19:10:00',170),(12,'Legally Blonde','Comedy','2023-01-25 16:20:00',120),(13,'Barbie','Animated','2023-01-25 17:10:00',100),(14,'Barbie: The Nutcracker','Animated','2023-01-19 18:10:00',100),(16,'One piece','Anime','2023-01-17 20:20:00',140),(19,'Deset u pola','Comedy','2023-02-01 16:30:00',100),(20,'M3gan','Horror','2023-01-19 18:30:00',120),(21,'Home Alone 1','Comedy, Family','2023-01-01 19:20:00',130);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `sector` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `movie_id_idx` (`movie_id`),
  CONSTRAINT `movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,11,1,'B6'),(2,11,1,'B9'),(3,11,1,'C8'),(4,11,8,'B6'),(5,11,5,'B6'),(6,22,8,'C4'),(7,22,8,'C4'),(8,22,9,'A10'),(9,11,8,'C4'),(10,11,8,'C4'),(11,11,1,'C3'),(12,11,8,'C5'),(13,11,8,'B5'),(14,11,12,'B7'),(15,11,1,'B3'),(16,11,6,'A10'),(17,22,5,'B10'),(18,22,5,'B9'),(19,11,16,'D5'),(20,11,16,'D6'),(21,11,8,'B10'),(22,14,2,'B6'),(23,14,2,'B5'),(24,14,2,'B4'),(25,23,13,'B5'),(26,23,13,'B6'),(27,11,13,'B10'),(28,11,13,'B9'),(29,11,8,'C1'),(30,11,8,'C2'),(31,11,20,'C5'),(32,11,20,'A5'),(33,24,12,'B5'),(34,24,20,'B8'),(35,24,12,'B6'),(36,11,1,'B10');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `admin` tinyint NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Emina Efendic','0603130228','efendicx@gmail.com',1,'eefendic1','nekipass'),(2,'Peter Smitz','854985985945','peter@mail.com',0,'peter','peter123'),(3,'Sofka Macic','54849949','sofka@mail.com',0,'sofka','sofka123'),(4,'Kinsey Locke','6849407','kinsey@gmail.com',0,'kinsey','12345'),(5,'Dennis Bramenn','6548494949','fggg@gmail.com',0,'dennis','98764'),(6,'Mark Johnson','445678','mark@gmail.com',0,'mark','qdd'),(7,'Emina Eminic','335678','emina@gmail.com',0,'emina','emina'),(8,'Ed Nelson','23445555','ed@gmail.com',0,'eee','kskssks'),(9,'Miley Cyrus','3456898','miley@gmail.com',0,'miley','miley'),(10,'User Useric','9595884994','user@gmail.com',0,'imuser','ttt'),(11,'Nedim Trapo','123469087','nedimt@gmail.com',0,'nedimt','sifrazafilm'),(12,'Irma Efendic','061351102','efendic@gmail.com',0,'irma','emina123'),(14,'Tajra Selimovic','1234589595','tajras@gmail.com',0,'tajra','123456'),(15,'Hana Mahmutovic','087940404','hana@gmail.com',0,'hmahmutovi3','12345678'),(16,'Dalila Krslak','449400505','dalilak@gmail.com',0,'dalila','12345678'),(18,'Justin Blake','133894034','justinblake@gmail.com',0,'justine','justin1223'),(19,'Neko Nekic','84940404','neko@gmail.com',0,'neko','password'),(20,'Ime Prezime','8393933003','ime@gmail.com',0,'ime','password'),(21,'Senadin Efendic','098680088','efendic@gmail.com',0,'eefendic2','nekipass'),(22,'Tony Stark','346565','tony@starkindustries.de',0,'Ironman4','iamgreat'),(23,'Hana Mahmutovic','78559404004','hmahmutovi2@etf.unsa.ba',0,'hmahmutovi2','hanahana'),(24,'Hana Hadzic','86969005','hana@gmail.com',0,'hana1','hana123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-28 12:30:11
