-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: java
-- ------------------------------------------------------
-- Server version	8.4.1

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
-- Table structure for table `allergies`
--

DROP TABLE IF EXISTS `allergies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergies` (
  `idAllergies` int NOT NULL AUTO_INCREMENT,
  `Allergies` varchar(45) DEFAULT NULL,
  `foodPreference_idFoodPreference` int NOT NULL,
  PRIMARY KEY (`idAllergies`),
  KEY `fk_Allergies_foodPreference1_idx` (`foodPreference_idFoodPreference`),
  CONSTRAINT `fk_Allergies_foodPreference1` FOREIGN KEY (`foodPreference_idFoodPreference`) REFERENCES `foodpreference` (`idFoodPreference`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergies`
--

LOCK TABLES `allergies` WRITE;
/*!40000 ALTER TABLE `allergies` DISABLE KEYS */;
/*!40000 ALTER TABLE `allergies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dietitian`
--

DROP TABLE IF EXISTS `dietitian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dietitian` (
  `dietitianUsername` varchar(45) NOT NULL,
  `price` int DEFAULT NULL,
  `available` tinyint DEFAULT NULL,
  `personalEducation` varchar(400) DEFAULT NULL,
  `workExperience` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`dietitianUsername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dietitian`
--

LOCK TABLES `dietitian` WRITE;
/*!40000 ALTER TABLE `dietitian` DISABLE KEYS */;
/*!40000 ALTER TABLE `dietitian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dislikedfood`
--

DROP TABLE IF EXISTS `dislikedfood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dislikedfood` (
  `idDislikedFood` int NOT NULL AUTO_INCREMENT,
  `DislikedFood` varchar(45) DEFAULT NULL,
  `foodPreference_idFoodPreference` int NOT NULL,
  PRIMARY KEY (`idDislikedFood`),
  KEY `fk_DislikedFood_foodPreference1_idx` (`foodPreference_idFoodPreference`),
  CONSTRAINT `fk_DislikedFood_foodPreference1` FOREIGN KEY (`foodPreference_idFoodPreference`) REFERENCES `foodpreference` (`idFoodPreference`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dislikedfood`
--

LOCK TABLES `dislikedfood` WRITE;
/*!40000 ALTER TABLE `dislikedfood` DISABLE KEYS */;
/*!40000 ALTER TABLE `dislikedfood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `idfood` int NOT NULL,
  `foodName` varchar(45) NOT NULL,
  `calories` decimal(10,2) NOT NULL,
  `protein` decimal(10,2) NOT NULL,
  `fat` decimal(10,2) NOT NULL,
  `carbs` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idfood`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Apple',52.00,0.30,0.20,14.00),(2,'Banana',96.00,1.30,0.30,27.00),(3,'Broccoli',34.00,2.80,0.40,7.00),(4,'Chicken Breast',165.00,31.00,3.60,0.00),(5,'Almonds',579.00,21.00,50.00,22.00),(6,'Orange',47.00,0.90,0.10,12.00),(7,'Salmon',208.00,20.00,13.00,0.00),(8,'Egg',155.00,13.00,11.00,1.10),(9,'Milk',42.00,3.40,1.00,5.00),(10,'Rice',130.00,2.40,0.30,28.00),(11,'Potato',77.00,2.00,0.10,17.00),(12,'Carrot',41.00,0.90,0.20,10.00),(13,'Tomato',18.00,0.90,0.20,3.90),(14,'Spinach',23.00,2.90,0.40,3.60),(15,'Beef',250.00,26.00,15.00,0.00),(16,'Yogurt',59.00,10.00,0.40,3.60),(17,'Cheddar Cheese',402.00,25.00,33.00,1.30),(18,'Peanut Butter',588.00,25.00,50.00,20.00),(19,'Brown Rice',111.00,2.60,0.90,23.00),(20,'Oatmeal',68.00,2.40,1.40,12.00),(21,'Turkey',104.00,17.00,2.10,0.00),(22,'Tofu',76.00,8.00,4.80,1.90),(23,'Lentils',116.00,9.00,0.40,20.00),(24,'Black Beans',132.00,9.00,0.50,23.00),(25,'Chickpeas',164.00,9.00,2.60,27.00),(26,'Quinoa',120.00,4.10,1.90,21.00),(27,'Sweet Potato',86.00,1.60,0.10,20.00),(28,'Cucumber',16.00,0.70,0.10,3.60),(29,'Zucchini',17.00,1.20,0.30,3.10),(30,'Mushrooms',22.00,3.10,0.30,3.30),(31,'Cauliflower',25.00,1.90,0.30,4.90),(32,'Peas',81.00,5.40,0.40,14.00),(33,'Green Beans',31.00,1.80,0.20,7.00),(34,'Strawberries',32.00,0.70,0.30,8.00),(35,'Blueberries',57.00,0.70,0.30,14.00),(36,'Pineapple',50.00,0.50,0.10,13.00),(37,'Watermelon',30.00,0.60,0.20,8.00),(38,'Papaya',43.00,0.50,0.30,11.00),(39,'Grapes',69.00,0.70,0.20,18.00),(40,'Pear',57.00,0.40,0.10,15.00),(41,'Mango',60.00,0.80,0.40,15.00),(42,'Kiwi',41.00,0.80,0.40,10.00),(43,'Pomegranate',83.00,1.70,1.20,19.00),(44,'Avocado',160.00,2.00,15.00,9.00),(45,'Coconut',354.00,3.30,33.00,15.00),(46,'Walnuts',654.00,15.00,65.00,14.00),(47,'Pistachios',562.00,20.00,45.00,28.00),(48,'Cashews',553.00,18.00,44.00,30.00),(49,'Hazelnuts',628.00,15.00,61.00,17.00),(50,'Macadamia Nuts',718.00,8.00,76.00,14.00),(51,'Brazil Nuts',659.00,14.00,66.00,12.00),(52,'Pumpkin Seeds',446.00,19.00,19.00,54.00),(53,'Sunflower Seeds',584.00,20.00,51.00,20.00),(54,'Sesame Seeds',573.00,18.00,50.00,23.00),(55,'Flax Seeds',534.00,18.00,42.00,29.00),(56,'Chia Seeds',486.00,16.00,31.00,42.00),(57,'Hemp Seeds',553.00,31.00,48.00,9.00),(58,'Poppy Seeds',525.00,18.00,42.00,28.00),(59,'Sesame Oil',884.00,0.00,100.00,0.00),(60,'Olive Oil',884.00,0.00,100.00,0.00),(61,'Coconut Oil',892.00,0.00,100.00,0.00),(62,'Butter',717.00,0.90,81.00,0.10),(63,'Cream Cheese',342.00,6.20,34.00,4.30),(64,'Mozzarella',280.00,28.00,17.00,3.00),(65,'Cottage Cheese',98.00,11.00,4.30,3.40),(66,'Feta Cheese',264.00,14.00,21.00,4.10),(67,'Parmesan Cheese',431.00,38.00,29.00,4.10),(68,'Ice Cream',207.00,3.50,11.00,24.00),(69,'Chocolate',546.00,4.90,31.00,61.00),(70,'Dark Chocolate',598.00,7.90,42.00,46.00),(71,'White Chocolate',539.00,5.90,30.00,59.00),(72,'Milk Chocolate',535.00,7.60,31.00,59.00);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodpreference`
--

DROP TABLE IF EXISTS `foodpreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodpreference` (
  `idFoodPreference` int NOT NULL AUTO_INCREMENT,
  `dietType` varchar(45) NOT NULL,
  PRIMARY KEY (`idFoodPreference`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodpreference`
--

LOCK TABLES `foodpreference` WRITE;
/*!40000 ALTER TABLE `foodpreference` DISABLE KEYS */;
/*!40000 ALTER TABLE `foodpreference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `infosport`
--

DROP TABLE IF EXISTS `infosport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `infosport` (
  `idSport` int NOT NULL AUTO_INCREMENT,
  `sport` varchar(45) NOT NULL,
  `frequency` varchar(100) NOT NULL,
  `healthGoal` varchar(100) NOT NULL,
  `alcoholDrinker` tinyint NOT NULL,
  `smoker` tinyint NOT NULL,
  PRIMARY KEY (`idSport`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `infosport`
--

LOCK TABLES `infosport` WRITE;
/*!40000 ALTER TABLE `infosport` DISABLE KEYS */;
/*!40000 ALTER TABLE `infosport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal` (
  `idMeal` int NOT NULL AUTO_INCREMENT,
  `mealType` varchar(45) NOT NULL,
  `request_idRequest` int NOT NULL,
  `foodName` varchar(45) NOT NULL,
  PRIMARY KEY (`idMeal`),
  KEY `fk_meal_request1_idx` (`request_idRequest`),
  KEY `fk_meal_food1_idx` (`foodName`),
  CONSTRAINT `fk_requestId` FOREIGN KEY (`request_idRequest`) REFERENCES `request` (`idRequest`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `patientUsername` varchar(45) NOT NULL,
  `age` int NOT NULL,
  `height` int NOT NULL,
  `weight` int NOT NULL,
  `gender` varchar(45) NOT NULL,
  PRIMARY KEY (`patientUsername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `idRequest` int NOT NULL AUTO_INCREMENT,
  `foodPreference_idFoodPreference` int NOT NULL,
  `Dietitian_dietitianUsername` varchar(45) NOT NULL,
  `patient_patientUsername` varchar(45) NOT NULL,
  `infoSport_idSport` int NOT NULL,
  `requestStatus` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idRequest`),
  KEY `fk_request_foodPreference1_idx` (`foodPreference_idFoodPreference`),
  KEY `fk_request_Dietitian1_idx` (`Dietitian_dietitianUsername`),
  KEY `fk_request_patient1_idx` (`patient_patientUsername`),
  KEY `fk_request_infoSport1_idx` (`infoSport_idSport`),
  CONSTRAINT `fk_request_Dietitian1` FOREIGN KEY (`Dietitian_dietitianUsername`) REFERENCES `dietitian` (`dietitianUsername`),
  CONSTRAINT `fk_request_foodPreference1` FOREIGN KEY (`foodPreference_idFoodPreference`) REFERENCES `foodpreference` (`idFoodPreference`),
  CONSTRAINT `fk_request_infoSport1` FOREIGN KEY (`infoSport_idSport`) REFERENCES `infosport` (`idSport`),
  CONSTRAINT `fk_request_patient1` FOREIGN KEY (`patient_patientUsername`) REFERENCES `patient` (`patientUsername`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `idtransaction` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `cvc` varchar(3) DEFAULT NULL,
  `creditNumber` varchar(45) DEFAULT NULL,
  `typePayment` enum('CreditCard','PayPal') NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`idtransaction`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('DIETITIAN','PATIENT') NOT NULL,
  PRIMARY KEY (`email`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('test','test@gmail.com','testing1','DIETITIAN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-16 17:10:29
