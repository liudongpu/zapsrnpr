CREATE DATABASE  IF NOT EXISTS `zapdata` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `zapdata`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: zapdata
-- ------------------------------------------------------
-- Server version	5.5.32

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
-- Table structure for table `zd_abstract`
--

DROP TABLE IF EXISTS `zd_abstract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zd_abstract` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `abstract_aids` int(11) DEFAULT '0' COMMENT '编码',
  `abstract_value` varchar(45) DEFAULT '' COMMENT '值',
  `abstract_remark` varchar(450) DEFAULT '',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `abstract_aids_UNIQUE` (`abstract_aids`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_abstract`
--

LOCK TABLES `zd_abstract` WRITE;
/*!40000 ALTER TABLE `zd_abstract` DISABLE KEYS */;
INSERT INTO `zd_abstract` VALUES (1,'',104,'db','数据库定义'),(2,'',104020,'type','数据库类型'),(3,'',104020013,'mysql','');
/*!40000 ALTER TABLE `zd_abstract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zd_column`
--

DROP TABLE IF EXISTS `zd_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zd_column` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `server_name` varchar(45) DEFAULT '',
  `table_name` varchar(45) DEFAULT '',
  `column_name` varchar(45) DEFAULT '',
  `column_type` int(11) DEFAULT '0',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_column`
--

LOCK TABLES `zd_column` WRITE;
/*!40000 ALTER TABLE `zd_column` DISABLE KEYS */;
/*!40000 ALTER TABLE `zd_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zd_server`
--

DROP TABLE IF EXISTS `zd_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zd_server` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `server_code` varchar(45) DEFAULT '' COMMENT '数据库编码',
  `server_name` varchar(45) DEFAULT '' COMMENT '数据库名称',
  `server_type_aid` int(11) DEFAULT '0' COMMENT '数据库类型',
  `jdbc_driver` varchar(450) DEFAULT '',
  `jdbc_dburl` varchar(450) DEFAULT '',
  `jdbc_user` varchar(45) DEFAULT '',
  `jdbc_password` varchar(45) DEFAULT '',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `server_code_UNIQUE` (`server_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_server`
--

LOCK TABLES `zd_server` WRITE;
/*!40000 ALTER TABLE `zd_server` DISABLE KEYS */;
INSERT INTO `zd_server` VALUES (1,'','zapdata','zapdata',104020013,'com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/zapdata?useUnicode=true','root','');
/*!40000 ALTER TABLE `zd_server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zd_tables`
--

DROP TABLE IF EXISTS `zd_tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zd_tables` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `server_code` varchar(45) DEFAULT '',
  `table_name` varchar(45) DEFAULT '',
  `table_remark` varchar(450) DEFAULT '',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `table_name_UNIQUE` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_tables`
--

LOCK TABLES `zd_tables` WRITE;
/*!40000 ALTER TABLE `zd_tables` DISABLE KEYS */;
/*!40000 ALTER TABLE `zd_tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'zapdata'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-08 18:00:35
