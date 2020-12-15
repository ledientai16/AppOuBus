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
  `BeginTime` time DEFAULT NULL,
  `XeID` int DEFAULT NULL,
  `GiaTien` int DEFAULT NULL,
  `TuyenDuongID` int DEFAULT NULL,
  `NgayKhoiHanh` date DEFAULT NULL,
  `SoVe` int DEFAULT NULL,
  PRIMARY KEY (`ChuyenXeID`),
  KEY `fk_chuyenxe_toid_xe_idx` (`XeID`),
  KEY `fk_chuyenxe_id_tuyenduong_idx` (`TuyenDuongID`),
  CONSTRAINT `fk_chuyenxe_id_tuyenduong` FOREIGN KEY (`TuyenDuongID`) REFERENCES `tuyenduong` (`TuyenDuongID`),
  CONSTRAINT `fk_chuyenxe_id_xe` FOREIGN KEY (`XeID`) REFERENCES `xe` (`XeID`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyenxe`
--

LOCK TABLES `chuyenxe` WRITE;
/*!40000 ALTER TABLE `chuyenxe` DISABLE KEYS */;
INSERT INTO `chuyenxe` VALUES (60,'03:00:00',1,100000,1,'2020-12-14',39),(61,'05:30:00',1,100000,1001,'2020-12-14',39),(62,'08:00:00',1,100000,1,'2020-12-14',39),(63,'10:30:00',1,100000,1001,'2020-12-14',39),(65,'22:30:00',1,100000,1001,'2020-12-14',39),(66,'03:00:00',2,150000,1001,'2020-12-15',39),(67,'05:30:00',2,150000,1,'2020-12-15',39),(68,'08:00:00',2,150000,1001,'2020-12-15',39),(69,'10:30:00',2,150000,1,'2020-12-15',39),(70,'13:00:00',2,150000,1001,'2020-12-15',39),(71,'15:30:00',2,150000,1,'2020-12-15',39),(72,'03:00:00',3,100000,1,'2020-12-16',39),(73,'05:30:00',3,100000,1001,'2020-12-16',39),(74,'08:00:00',3,100000,1,'2020-12-16',39),(76,'13:00:00',3,100000,1,'2020-12-16',39),(77,'15:30:00',3,100000,1001,'2020-12-16',39),(78,'03:00:00',1,900000,1,'2020-12-24',39),(79,'05:30:00',1,900000,1001,'2020-12-24',39),(80,'08:00:00',1,900000,1,'2020-12-24',39),(81,'10:30:00',1,900000,1001,'2020-12-24',39),(82,'13:00:00',1,900000,1,'2020-12-24',39),(83,'15:30:00',1,900000,1001,'2020-12-24',39);
/*!40000 ALTER TABLE `chuyenxe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EmployeeID` int NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Lê Điền Tài');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khunggio`
--

DROP TABLE IF EXISTS `khunggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khunggio` (
  `KhungGioID` int NOT NULL,
  `EndTime` time DEFAULT NULL,
  `BeginTime` time DEFAULT NULL,
  PRIMARY KEY (`KhungGioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khunggio`
--

LOCK TABLES `khunggio` WRITE;
/*!40000 ALTER TABLE `khunggio` DISABLE KEYS */;
INSERT INTO `khunggio` VALUES (1,'20:00:00','03:00:00');
/*!40000 ALTER TABLE `khunggio` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1213333334 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tram`
--

LOCK TABLES `tram` WRITE;
/*!40000 ALTER TABLE `tram` DISABLE KEYS */;
INSERT INTO `tram` VALUES (1,'An Sương','Củ Chi, TP Hồ Chí Minh'),(2,'Tân Châu','Huyện Tân Châu, Tây Ninh'),(3,'Bến xe Miền Tây','Bình Tân, Tp Hồ Chí Minh'),(4,'Đồng Tháp','tx Đồng Tháp, Đồng Tháp');
/*!40000 ALTER TABLE `tram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuyenduong`
--

DROP TABLE IF EXISTS `tuyenduong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tuyenduong` (
  `TuyenDuongID` int NOT NULL AUTO_INCREMENT,
  `TuyenDuongName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FromTram` int DEFAULT NULL,
  `ToTram` int DEFAULT NULL,
  `Distance` int DEFAULT NULL,
  `TuyenDuongTime` time DEFAULT NULL,
  `TuyenKhuHoiID` int DEFAULT NULL,
  PRIMARY KEY (`TuyenDuongID`),
  KEY `fk_tuyenduong_toid_tram_idx` (`FromTram`,`ToTram`),
  KEY `fk_tuyenkhuhoi_toid_tuyenduong_idx` (`TuyenKhuHoiID`),
  KEY `fk_tuyenduong_toid_tram_idx1` (`ToTram`),
  CONSTRAINT `fk_fromtram_toid_tram` FOREIGN KEY (`FromTram`) REFERENCES `tram` (`TramID`),
  CONSTRAINT `fk_totram_toid_tram` FOREIGN KEY (`ToTram`) REFERENCES `tram` (`TramID`)
) ENGINE=InnoDB AUTO_INCREMENT=34344 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuyenduong`
--

LOCK TABLES `tuyenduong` WRITE;
/*!40000 ALTER TABLE `tuyenduong` DISABLE KEYS */;
INSERT INTO `tuyenduong` VALUES (1,'Tân Châu-An Sương',1,2,120,'01:30:00',1001),(12,'Đồng tháp to cà Mau',3,4,120,'01:30:00',NULL),(15,'Tân Châu-An Sương',1,2,120,'01:30:00',1002),(1001,'Tân Châu to An Sương(1001)',2,1,120,'01:30:00',1),(1002,'Tân Châu to An Sương(1002)',2,1,120,'01:30:00',15);
/*!40000 ALTER TABLE `tuyenduong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `VeXeID` int NOT NULL AUTO_INCREMENT,
  `ChuyenXeID` int DEFAULT NULL,
  `SoGhe` int DEFAULT NULL,
  `GioDat` time DEFAULT NULL,
  `KhachHangName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `VeDat` tinyint DEFAULT NULL,
  PRIMARY KEY (`VeXeID`),
  KEY `fk_vexe_id_chuyenxe_idx` (`ChuyenXeID`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_vexe_id_chuyenxe` FOREIGN KEY (`ChuyenXeID`) REFERENCES `chuyenxe` (`ChuyenXeID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
INSERT INTO `vexe` VALUES (35,70,29,'22:36:49','','',NULL),(36,71,29,'23:00:29','','',0),(37,71,38,'23:00:36','','',1),(39,71,35,'09:44:34','','',0),(40,70,34,'09:44:42','','',0),(41,69,38,'09:44:48','','',0),(42,71,34,'09:45:04','','',1),(44,70,35,'09:50:14','','',0),(45,70,24,'09:50:21','','',0),(46,70,19,'09:50:24','','',0),(47,70,25,'10:44:42','','',0),(49,71,30,'13:31:47','','',1),(50,71,20,'13:31:49','','',0),(51,76,1,'13:36:56','','',1),(52,72,34,'13:39:29','','',1),(53,72,23,'13:39:32','','',0),(54,74,20,'13:42:11','','',0),(55,72,3,'13:42:29','','',1),(56,76,8,'14:01:02','','',1),(58,72,8,'14:21:38','','',1),(59,74,15,'14:24:36','','',0),(60,72,15,'14:26:09','','',1),(62,77,8,'14:28:41','','',1),(63,71,33,'03:01:00','ba den','0192',1),(64,71,33,'03:01:00','ba den','0192',1),(65,71,33,'03:01:00','ba den','0192',1),(68,74,24,'15:24:42','','',1),(69,74,34,'15:26:12','','',0),(70,74,27,'15:26:15','','',1);
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
  `TramID` int DEFAULT NULL,
  PRIMARY KEY (`XeID`),
  KEY `fk_xe_id_tram_idx` (`TramID`),
  CONSTRAINT `fk_xe_id_tram` FOREIGN KEY (`TramID`) REFERENCES `tram` (`TramID`)
) ENGINE=InnoDB AUTO_INCREMENT=77778 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xe`
--

LOCK TABLES `xe` WRITE;
/*!40000 ALTER TABLE `xe` DISABLE KEYS */;
INSERT INTO `xe` VALUES (1,'70k-9999',40,'HondaToYETa','2017-11-26',1),(2,'59e-7777',40,'MercedeVinfast','1970-01-01',1),(3,'99k2-6666',40,'exiyeter','2020-12-01',3),(4,'87-1234',40,'djsoda','1970-01-01',4),(5,'49-3993',40,'Formosa','2020-12-01',1),(19,'kwo',40,'toyota','3920-02-01',3);
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

-- Dump completed on 2020-12-15 16:53:24
