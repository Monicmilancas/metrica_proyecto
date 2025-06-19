-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ddb253208
-- ------------------------------------------------------
-- Server version	8.4.3

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
-- Table structure for table `alimentacion`
--

DROP TABLE IF EXISTS `alimentacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alimentacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `codigo` char(6) NOT NULL,
  `caducidad` date NOT NULL,
  `precio` float NOT NULL,
  `imagen` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alimentacion`
--

LOCK TABLES `alimentacion` WRITE;
/*!40000 ALTER TABLE `alimentacion` DISABLE KEYS */;
INSERT INTO `alimentacion` VALUES (1,'Chocolate','AL0010','2025-12-01',1.2,'multimedia/Chocolate.jpeg'),(2,'Pan','AL0011','2025-05-20',0.8,'multimedia/Pan.jpeg'),(3,'Leche','AL0012','2025-06-10',1.1,'multimedia/Leche.jpeg'),(4,'Queso','AL0013','2025-06-15',2.5,'multimedia/Queso.jpeg'),(5,'Yogur','AL0014','2025-05-18',0.9,'multimedia/Yogur.jpeg');
/*!40000 ALTER TABLE `alimentacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electronica`
--

DROP TABLE IF EXISTS `electronica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electronica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `codigo` char(6) NOT NULL,
  `garantia` int NOT NULL,
  `precio` float NOT NULL,
  `imagen` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electronica`
--

LOCK TABLES `electronica` WRITE;
/*!40000 ALTER TABLE `electronica` DISABLE KEYS */;
INSERT INTO `electronica` VALUES (1,'Radio','EL1001',12,25.99,'multimedia/Radio.jpeg'),(2,'Tablet','EL1002',24,149.99,'multimedia/Tablet.jpeg'),(3,'Auriculares','EL1003',6,19.5,'multimedia/Auriculares.jpeg'),(4,'TV','EL1004',36,399,'multimedia/TV.jpeg'),(5,'Laptop','EL1005',24,699.99,'multimedia/Laptop.jpeg');
/*!40000 ALTER TABLE `electronica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `textil`
--

DROP TABLE IF EXISTS `textil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `textil` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `codigo` char(6) NOT NULL,
  `composicion` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `imagen` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `textil`
--

LOCK TABLES `textil` WRITE;
/*!40000 ALTER TABLE `textil` DISABLE KEYS */;
INSERT INTO `textil` VALUES (1,'Camiseta','TX0001','100% Algodón',9.99,'multimedia/Camiseta.jpeg'),(2,'Pantalón','TX0002','80% Algodón, 20% Poliéster',19.99,'multimedia/Pantalón.jpeg'),(3,'Chaqueta','TX0003','100% Poliéster',49.99,'multimedia/Chaqueta.jpeg'),(4,'Bufanda','TX0004','70% Lana, 30% Acrílico',14.99,'multimedia/Bufanda.jpeg'),(5,'Vestido','TX0005','95% Algodón, 5% Elastano',29.99,'multimedia/Vestido.jpeg');
/*!40000 ALTER TABLE `textil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ddb253208'
--

--
-- Dumping routines for database 'ddb253208'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-18 15:12:25
