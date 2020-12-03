-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: appoubusdb
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `chuyenxe`
--

DROP TABLE IF EXISTS `chuyenxe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyenxe` (
  `ChuyenXeID` int NOT NULL AUTO_INCREMENT,
  `FromTram` int DEFAULT NULL,
  `ToTram` int DEFAULT NULL,
  `BeginTime` date DEFAULT NULL,
  `XeID` int DEFAULT NULL,
  `MoTa` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ChuyenXeID`),
  KEY `fk_chuyenxe_toid_tram_idx` (`ToTram`,`FromTram`),
  KEY `fk_chuyenxe_fromid_tram_idx` (`FromTram`,`ToTram`),
  KEY `fk_chuyenxe_toid_xe_idx` (`XeID`),
  CONSTRAINT `fk_chuyenxe_toid_tram` FOREIGN KEY (`ToTram`) REFERENCES `tram` (`TramID`),
  CONSTRAINT `fk_chuyenxe_toid_xe` FOREIGN KEY (`XeID`) REFERENCES `xe` (`XeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyenxe`
--

LOCK TABLES `chuyenxe` WRITE;
/*!40000 ALTER TABLE `chuyenxe` DISABLE KEYS */;
/*!40000 ALTER TABLE `chuyenxe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tram`
--

DROP TABLE IF EXISTS `tram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tram` (
  `TramID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`TramID`),
  UNIQUE KEY `TramID_UNIQUE` (`TramID`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tram`
--

LOCK TABLES `tram` WRITE;
/*!40000 ALTER TABLE `tram` DISABLE KEYS */;
INSERT INTO `tram` VALUES (50,'56','56'),(51,'56','56'),(52,'56','56');
/*!40000 ALTER TABLE `tram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `idVeXe` int NOT NULL,
  `ChuyenXeID` int DEFAULT NULL,
  `vexecol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idVeXe`),
  UNIQUE KEY `idVeXe_UNIQUE` (`idVeXe`),
  KEY `fk_vexe_id_chuyenxe_idx` (`ChuyenXeID`),
  CONSTRAINT `fk_vexe_id_chuyenxe` FOREIGN KEY (`ChuyenXeID`) REFERENCES `chuyenxe` (`ChuyenXeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
/*!40000 ALTER TABLE `vexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xe`
--

DROP TABLE IF EXISTS `xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xe` (
  `XeID` int NOT NULL AUTO_INCREMENT,
  `BienSo` varchar(45) NOT NULL,
  `SoGhe` int NOT NULL,
  `LoaiXe` varchar(45) DEFAULT NULL,
  `NamSX` date DEFAULT NULL,
  PRIMARY KEY (`XeID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xe`
--

LOCK TABLES `xe` WRITE;
/*!40000 ALTER TABLE `xe` DISABLE KEYS */;
INSERT INTO `xe` VALUES (6,'3',3,'3','2020-12-15'),(7,'3',3,'3','2020-12-15'),(8,'3',3,'3','2020-12-15'),(9,'3',3,'3','2020-12-15');
/*!40000 ALTER TABLE `xe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-03 20:36:06
