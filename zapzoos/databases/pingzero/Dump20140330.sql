CREATE DATABASE  IF NOT EXISTS `pingzero` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pingzero`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: pingzero
-- ------------------------------------------------------
-- Server version	5.5.36

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
-- Table structure for table `pp_webpage`
--

DROP TABLE IF EXISTS `pp_webpage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pp_webpage` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `page_url` varchar(450) DEFAULT '' COMMENT '链接地址',
  `page_content` longtext COMMENT '页面内容',
  `create_time` char(20) DEFAULT '' COMMENT '创建时间',
  `last_spider_time` char(20) DEFAULT '' COMMENT '最近一次索引时间',
  `update_time` char(20) DEFAULT '' COMMENT '最近一次内容更新时间',
  `page_for_define` varchar(45) DEFAULT '' COMMENT '页面用途',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pp_webpage`
--

LOCK TABLES `pp_webpage` WRITE;
/*!40000 ALTER TABLE `pp_webpage` DISABLE KEYS */;
/*!40000 ALTER TABLE `pp_webpage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pz_define`
--

DROP TABLE IF EXISTS `pz_define`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pz_define` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `define_code` varchar(45) DEFAULT '' COMMENT '定义编码',
  `define_name` varchar(45) DEFAULT '' COMMENT '定义名称',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `define_code_UNIQUE` (`define_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pz_define`
--

LOCK TABLES `pz_define` WRITE;
/*!40000 ALTER TABLE `pz_define` DISABLE KEYS */;
INSERT INTO `pz_define` VALUES (1,'','4442','pingzero定义'),(2,'','44420001','索引网站类型'),(3,'','444200010001','京东');
/*!40000 ALTER TABLE `pz_define` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pz_spiderjob`
--

DROP TABLE IF EXISTS `pz_spiderjob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pz_spiderjob` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT NULL,
  `job_title` varchar(450) DEFAULT '' COMMENT '任务名称',
  `job_content` varchar(450) DEFAULT '',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='蜘蛛任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pz_spiderjob`
--

LOCK TABLES `pz_spiderjob` WRITE;
/*!40000 ALTER TABLE `pz_spiderjob` DISABLE KEYS */;
/*!40000 ALTER TABLE `pz_spiderjob` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-30 22:17:17
