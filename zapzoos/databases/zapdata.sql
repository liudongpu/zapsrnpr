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
  `abstract_remark` varchar(450) DEFAULT '' COMMENT '备注',
  `parent_aid` int(11) DEFAULT '0' COMMENT '父编号',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `abstract_aids_UNIQUE` (`abstract_aids`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_abstract`
--

LOCK TABLES `zd_abstract` WRITE;
/*!40000 ALTER TABLE `zd_abstract` DISABLE KEYS */;
INSERT INTO `zd_abstract` VALUES (1,'',104,'db','数据库定义',0),(2,'',104020,'type','数据库类型',104),(3,'',104020013,'mysql','',104020),(4,'',104014,'notnull','非空',104),(5,'',104014001,'yes','非空',104014),(6,'',104014002,'no','允许空',104014),(7,'',104003,'column_type','数据列类型',104),(8,'',104003022,'varchar','',104003),(9,'',104003003,'char','',104003),(10,'',104003009,'int','',104003),(11,'',104003004,'decimal','',104003),(12,'',104003012,'longtext','',104003),(13,'',101,'all','系统定义',0),(14,'',101001,'flag','逻辑标识',101),(15,'',101001001,'yes','是',101001),(16,'',101001002,'no','否',101001),(17,'',104005,'field_type','字段类型',104),(18,'',104005001,'add','添加',104005),(19,'',104005002,'book','展示',104005),(20,'',104005003,'chart','列表',104005),(21,'',104005005,'edit','编辑',104005),(22,'',104005009,'inqurey','查询',104005),(24,'',119,'system','系统定义',0),(25,'',119678,'zapadmin','zapadmin',0),(26,'',115,'operate','操作',0),(27,'',115015,'operatepage','操作类型',115),(28,'',115015003,'chart','列表页面',115015),(29,'',115015001,'add','添加页面',115015),(30,'',115015005,'edit','修改页面',115015),(31,'',115015004,'操作函数','删除按钮',115015),(34,'',115015006,'func','执行调用按钮',115015);
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
  `server_name` varchar(45) DEFAULT '' COMMENT '库名称',
  `table_name` varchar(45) DEFAULT '' COMMENT '表名称',
  `column_name` varchar(45) DEFAULT '' COMMENT '列名',
  `column_note` varchar(45) DEFAULT '0' COMMENT '字段描述',
  `column_type_aid` int(11) DEFAULT '0' COMMENT '列类型编号',
  `null_able_aid` int(11) DEFAULT '0' COMMENT '是否允许空',
  `column_length` int(11) DEFAULT '0' COMMENT '字段长度',
  `length_scale` int(11) DEFAULT '0' COMMENT '小数点',
  `column_sort` varchar(45) DEFAULT '' COMMENT '排序',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_column`
--

LOCK TABLES `zd_column` WRITE;
/*!40000 ALTER TABLE `zd_column` DISABLE KEYS */;
INSERT INTO `zd_column` VALUES (46,'94329222e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','zid','',104003009,104014002,0,0,'1'),(47,'943294afe87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','uid','',104003003,104014002,32,0,'2'),(48,'94329570e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','card_serial','购物卡流水号',104003003,104014001,12,0,'3'),(49,'94329618e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','card_code','购物卡卡号',104003003,104014002,16,0,'4'),(50,'943296b4e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','card_pass','购物卡密码',104003003,104014001,6,0,'5'),(51,'9432975de87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','card_money','购物卡金额',104003004,104014001,0,2,'6'),(52,'943297f0e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','used_money','已使用金额',104003004,104014001,0,2,'7'),(53,'9432988ce87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','inital_money','初始金额',104003004,104014001,0,2,'8'),(54,'94329931e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','flag_active','激活标识',104003009,104014001,0,0,'9'),(55,'943299c8e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','begin_time','开始使用时间',104003003,104014001,18,0,'10'),(56,'94329a64e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','finish_time','截止使用时间',104003003,104014001,18,0,'11'),(57,'94329b00e87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo','create_time','创建时间',104003003,104014001,18,0,'12'),(58,'94329b98e87111e2b8255404a6a9ac98','zapdata','zd_abstract','zid','',104003009,104014002,0,0,'1'),(59,'94329d8de87111e2b8255404a6a9ac98','zapdata','zd_abstract','uid','',104003003,104014001,32,0,'2'),(60,'94329e31e87111e2b8255404a6a9ac98','zapdata','zd_abstract','abstract_aids','编码',104003009,104014001,0,0,'3'),(61,'94329ec9e87111e2b8255404a6a9ac98','zapdata','zd_abstract','abstract_value','值',104003022,104014001,45,0,'4'),(62,'94329f61e87111e2b8255404a6a9ac98','zapdata','zd_abstract','abstract_remark','备注',104003022,104014001,450,0,'5'),(63,'94329ffde87111e2b8255404a6a9ac98','zapdata','zd_abstract','parent_aid','父编号',104003009,104014001,0,0,'6'),(64,'9432a091e87111e2b8255404a6a9ac98','zapdata','zd_column','zid','',104003009,104014002,0,0,'1'),(65,'9432a135e87111e2b8255404a6a9ac98','zapdata','zd_column','uid','',104003003,104014001,32,0,'2'),(66,'9432a1d5e87111e2b8255404a6a9ac98','zapdata','zd_column','server_name','库名称',104003022,104014001,45,0,'3'),(67,'9432a275e87111e2b8255404a6a9ac98','zapdata','zd_column','table_name','表名称',104003022,104014001,45,0,'4'),(68,'9432a305e87111e2b8255404a6a9ac98','zapdata','zd_column','column_name','列名',104003022,104014001,45,0,'5'),(69,'9432a399e87111e2b8255404a6a9ac98','zapdata','zd_column','column_note','字段描述',104003022,104014001,45,0,'6'),(70,'9432a431e87111e2b8255404a6a9ac98','zapdata','zd_column','column_type_aid','列类型编号',104003009,104014001,0,0,'7'),(71,'9432a4cde87111e2b8255404a6a9ac98','zapdata','zd_column','null_able_aid','是否允许空',104003009,104014001,0,0,'8'),(72,'9432a565e87111e2b8255404a6a9ac98','zapdata','zd_column','column_length','字段长度',104003009,104014001,0,0,'9'),(73,'9432a5f8e87111e2b8255404a6a9ac98','zapdata','zd_column','length_scale','小数点',104003009,104014001,0,0,'10'),(74,'9432a68ce87111e2b8255404a6a9ac98','zapdata','zd_column','column_sort','排序',104003022,104014001,45,0,'11'),(75,'9432a72ce87111e2b8255404a6a9ac98','zapdata','zd_server','zid','',104003009,104014002,0,0,'1'),(76,'9432a7bce87111e2b8255404a6a9ac98','zapdata','zd_server','uid','',104003003,104014001,32,0,'2'),(77,'9432a854e87111e2b8255404a6a9ac98','zapdata','zd_server','server_code','数据库编码',104003022,104014001,45,0,'3'),(78,'9432a8f0e87111e2b8255404a6a9ac98','zapdata','zd_server','server_name','数据库名称',104003022,104014001,45,0,'4'),(79,'9432a98ce87111e2b8255404a6a9ac98','zapdata','zd_server','server_type_aid','数据库类型',104003009,104014001,0,0,'5'),(80,'9432ab95e87111e2b8255404a6a9ac98','zapdata','zd_server','jdbc_driver','驱动',104003022,104014001,450,0,'6'),(81,'9432ac31e87111e2b8255404a6a9ac98','zapdata','zd_server','jdbc_dburl','路径',104003022,104014001,450,0,'7'),(82,'9432acc9e87111e2b8255404a6a9ac98','zapdata','zd_server','jdbc_user','用户名',104003022,104014001,45,0,'8'),(83,'9432ad61e87111e2b8255404a6a9ac98','zapdata','zd_server','jdbc_password','密码',104003022,104014001,45,0,'9'),(84,'9432adfde87111e2b8255404a6a9ac98','zapdata','zd_tables','zid','',104003009,104014002,0,0,'1'),(85,'9432ae91e87111e2b8255404a6a9ac98','zapdata','zd_tables','uid','',104003003,104014001,32,0,'2'),(86,'9432af25e87111e2b8255404a6a9ac98','zapdata','zd_tables','server_name','数据库名称',104003022,104014001,45,0,'3'),(87,'9432afc1e87111e2b8255404a6a9ac98','zapdata','zd_tables','table_name','表名称',104003022,104014001,45,0,'4'),(88,'9432b05de87111e2b8255404a6a9ac98','zapdata','zd_tables','table_remark','备注',104003022,104014001,450,0,'5');
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
  `jdbc_driver` varchar(450) DEFAULT '' COMMENT '驱动',
  `jdbc_dburl` varchar(450) DEFAULT '' COMMENT '路径',
  `jdbc_user` varchar(45) DEFAULT '' COMMENT '用户名',
  `jdbc_password` varchar(45) DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `server_code_UNIQUE` (`server_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_server`
--

LOCK TABLES `zd_server` WRITE;
/*!40000 ALTER TABLE `zd_server` DISABLE KEYS */;
INSERT INTO `zd_server` VALUES (1,'','zapdata','zapdata',104020013,'com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/zapdata?useUnicode=true','root',''),(2,'','cardcenter','cardcenter',104020013,'com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/cardcenter?useUnicode=true','root','');
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
  `server_name` varchar(45) DEFAULT '' COMMENT '数据库名称',
  `table_name` varchar(45) DEFAULT '' COMMENT '表名称',
  `table_remark` varchar(450) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `table_name_UNIQUE` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_tables`
--

LOCK TABLES `zd_tables` WRITE;
/*!40000 ALTER TABLE `zd_tables` DISABLE KEYS */;
INSERT INTO `zd_tables` VALUES (9,'942948ede87111e2b8255404a6a9ac98','cardcenter','cc_cardinfo',''),(10,'94294afae87111e2b8255404a6a9ac98','zapdata','zd_abstract',''),(11,'94294bfde87111e2b8255404a6a9ac98','zapdata','zd_column',''),(12,'94294cdae87111e2b8255404a6a9ac98','zapdata','zd_server',''),(13,'94294dace87111e2b8255404a6a9ac98','zapdata','zd_tables','');
/*!40000 ALTER TABLE `zd_tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_define`
--

DROP TABLE IF EXISTS `zw_define`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_define` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `define_dids` varchar(30) DEFAULT '' COMMENT '定义编号',
  `define_name` varchar(450) DEFAULT '' COMMENT '定义名称',
  `define_note` varchar(45) DEFAULT '' COMMENT '描述',
  `level_rank` int(11) DEFAULT '0' COMMENT '级别',
  `parent_did` varchar(45) DEFAULT '' COMMENT '父编号',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_define`
--

LOCK TABLES `zw_define` WRITE;
/*!40000 ALTER TABLE `zw_define` DISABLE KEYS */;
/*!40000 ALTER TABLE `zw_define` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_field`
--

DROP TABLE IF EXISTS `zw_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_field` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `view_code` varchar(45) DEFAULT '' COMMENT '视图编号',
  `column_name` varchar(45) DEFAULT '' COMMENT '数据列名',
  `field_note` varchar(45) DEFAULT '' COMMENT '字段名称',
  `sort_add` varchar(45) DEFAULT '' COMMENT '排序添加',
  `sort_edit` varchar(45) DEFAULT '' COMMENT '排序修改',
  `sort_chart` varchar(45) DEFAULT '' COMMENT '排序列表',
  `sort_book` varchar(45) DEFAULT '' COMMENT '排序展示',
  `sort_inquery` varchar(45) DEFAULT '' COMMENT '排序查询',
  `field_type_aid` int(11) DEFAULT '0' COMMENT '字段类型',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字段表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_field`
--

LOCK TABLES `zw_field` WRITE;
/*!40000 ALTER TABLE `zw_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `zw_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_manager`
--

DROP TABLE IF EXISTS `zw_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_manager` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `manager_name` varchar(45) DEFAULT '',
  `manager_pass` varchar(45) DEFAULT '',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_manager`
--

LOCK TABLES `zw_manager` WRITE;
/*!40000 ALTER TABLE `zw_manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `zw_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_operate`
--

DROP TABLE IF EXISTS `zw_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_operate` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `operate_name` varchar(45) DEFAULT '' COMMENT '操作名称',
  `operate_type_aid` int(11) DEFAULT '104005' COMMENT '操作类型',
  `page_code` varchar(450) DEFAULT '' COMMENT '页面编码',
  `operate_link` varchar(450) DEFAULT '' COMMENT '操作链接',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_operate`
--

LOCK TABLES `zw_operate` WRITE;
/*!40000 ALTER TABLE `zw_operate` DISABLE KEYS */;
INSERT INTO `zw_operate` VALUES (1,'','数据表维护-列表',115015003,'p_zapadmin_system_view',''),(2,'','添加',115015001,'p_zapadmin_system_view',''),(3,'','修改',115015005,'p_zapadmin_system_view',''),(4,'','删除',115015004,'p_zapadmin_system_view','');
/*!40000 ALTER TABLE `zw_operate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_page`
--

DROP TABLE IF EXISTS `zw_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_page` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `page_code` varchar(45) DEFAULT '' COMMENT '页面编号',
  `page_name` varchar(450) DEFAULT '' COMMENT '页面名称',
  `page_urls` varchar(450) DEFAULT '' COMMENT '页面链接',
  `page_template` varchar(45) DEFAULT '' COMMENT '页面调用模板',
  `view_code` varchar(45) DEFAULT '' COMMENT '视图编码',
  `parent_code` varchar(45) DEFAULT '' COMMENT '父编码',
  `page_operate_aid` varchar(45) DEFAULT '0' COMMENT '页面类型',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `page_code_UNIQUE` (`page_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_page`
--

LOCK TABLES `zw_page` WRITE;
/*!40000 ALTER TABLE `zw_page` DISABLE KEYS */;
INSERT INTO `zw_page` VALUES (1,'','p_zapadmin','超级管理系统','','','','',''),(2,'','p_zapadmin_system','系统设置','','','','p_zapadmin',''),(3,'','p_zapadmin_system_view','数据表维护','','','v_zw_view','p_zapadmin_system','115015003');
/*!40000 ALTER TABLE `zw_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_role`
--

DROP TABLE IF EXISTS `zw_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_role` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `role_code` varchar(45) DEFAULT '' COMMENT '角色编码',
  `role_name` varchar(45) DEFAULT '' COMMENT '角色名称',
  `parent_code` varchar(45) DEFAULT '' COMMENT '父编码',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `role_code_UNIQUE` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_role`
--

LOCK TABLES `zw_role` WRITE;
/*!40000 ALTER TABLE `zw_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `zw_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_source`
--

DROP TABLE IF EXISTS `zw_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_source` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `source_code` varchar(45) DEFAULT '' COMMENT '数据源编编码',
  `source_name` varchar(45) DEFAULT '' COMMENT '数据源名称',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_source`
--

LOCK TABLES `zw_source` WRITE;
/*!40000 ALTER TABLE `zw_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `zw_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_view`
--

DROP TABLE IF EXISTS `zw_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_view` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `view_code` varchar(45) DEFAULT '' COMMENT '视图编码',
  `view_name` varchar(45) DEFAULT '' COMMENT '试图名称',
  `table_name` varchar(45) DEFAULT '' COMMENT '视图表格',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='视图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_view`
--

LOCK TABLES `zw_view` WRITE;
/*!40000 ALTER TABLE `zw_view` DISABLE KEYS */;
INSERT INTO `zw_view` VALUES (1,'','v_zw_view','系统视图','zw_view');
/*!40000 ALTER TABLE `zw_view` ENABLE KEYS */;
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

-- Dump completed on 2013-07-12 18:45:59
CREATE DATABASE  IF NOT EXISTS `cardcenter` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cardcenter`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: cardcenter
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
-- Table structure for table `cc_cardinfo`
--

DROP TABLE IF EXISTS `cc_cardinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_cardinfo` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) NOT NULL DEFAULT '',
  `card_serial` char(12) DEFAULT '' COMMENT '购物卡流水号',
  `card_code` char(16) NOT NULL DEFAULT '' COMMENT '购物卡卡号',
  `card_pass` char(6) DEFAULT '' COMMENT '购物卡密码',
  `card_money` decimal(18,2) DEFAULT '0.00' COMMENT '购物卡金额',
  `used_money` decimal(18,2) DEFAULT '0.00' COMMENT '已使用金额',
  `inital_money` decimal(18,2) DEFAULT '0.00' COMMENT '初始金额',
  `flag_active` int(11) DEFAULT '101001002' COMMENT '激活标识',
  `begin_time` char(18) DEFAULT '' COMMENT '开始使用时间',
  `finish_time` char(18) DEFAULT '' COMMENT '截止使用时间',
  `create_time` char(18) DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `card_code_UNIQUE` (`card_code`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_cardinfo`
--

LOCK TABLES `cc_cardinfo` WRITE;
/*!40000 ALTER TABLE `cc_cardinfo` DISABLE KEYS */;
INSERT INTO `cc_cardinfo` VALUES (27,'df490ec172dc47638f58dd0eed59133b','','aa','',0.00,0.00,0.00,101001002,'','',''),(32,'733044fee7844e50a32d8e592e5237ed','','bb','',0.00,0.00,0.00,101001002,'','','');
/*!40000 ALTER TABLE `cc_cardinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_cardlog`
--

DROP TABLE IF EXISTS `cc_cardlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_cardlog` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `card_code` varchar(45) DEFAULT '' COMMENT '卡号',
  `change_money` decimal(18,2) DEFAULT '0.00' COMMENT '变化金额',
  `change_time` char(18) DEFAULT '' COMMENT '变更时间',
  `change_people` varchar(45) DEFAULT '' COMMENT '变更人',
  `change_source` varchar(45) DEFAULT '' COMMENT '变更原因',
  `change_code` varchar(45) DEFAULT '' COMMENT '相关单号',
  `change_remark` varchar(450) DEFAULT '' COMMENT '变更备注',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_cardlog`
--

LOCK TABLES `cc_cardlog` WRITE;
/*!40000 ALTER TABLE `cc_cardlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_cardlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_createlog`
--

DROP TABLE IF EXISTS `cc_createlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_createlog` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT NULL,
  `task_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_createlog`
--

LOCK TABLES `cc_createlog` WRITE;
/*!40000 ALTER TABLE `cc_createlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_createlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_createtask`
--

DROP TABLE IF EXISTS `cc_createtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_createtask` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `task_code` varchar(45) DEFAULT '' COMMENT '任务编号',
  `task_name` varchar(450) DEFAULT '' COMMENT '任务名称',
  `task_remark` varchar(4500) DEFAULT '' COMMENT '备注',
  `create_time` char(18) DEFAULT '' COMMENT '创建时间',
  `create_user` varchar(45) DEFAULT '' COMMENT '创建人',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_createtask`
--

LOCK TABLES `cc_createtask` WRITE;
/*!40000 ALTER TABLE `cc_createtask` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_createtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_define`
--

DROP TABLE IF EXISTS `cc_define`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_define` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `define_dids` varchar(30) DEFAULT '' COMMENT '定义编号',
  `define_name` varchar(450) DEFAULT '' COMMENT '定义名称',
  `define_note` varchar(45) DEFAULT '' COMMENT '描述',
  `level_rank` int(11) DEFAULT '0' COMMENT '级别',
  `parent_did` varchar(45) DEFAULT '' COMMENT '父编号',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_define`
--

LOCK TABLES `cc_define` WRITE;
/*!40000 ALTER TABLE `cc_define` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_define` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_moneytype`
--

DROP TABLE IF EXISTS `cc_moneytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_moneytype` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `moeney_type` int(11) DEFAULT '0' COMMENT '金额类型',
  `money_value` int(11) DEFAULT '0' COMMENT '金额值',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_moneytype`
--

LOCK TABLES `cc_moneytype` WRITE;
/*!40000 ALTER TABLE `cc_moneytype` DISABLE KEYS */;
INSERT INTO `cc_moneytype` VALUES (1,'',31,100),(2,'',32,200),(3,'',35,500),(4,'',41,1000),(5,'',42,2000),(6,'',45,5000),(7,'',51,10000),(8,'',52,20000),(9,'',55,50000);
/*!40000 ALTER TABLE `cc_moneytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cardcenter'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-12 18:46:00
