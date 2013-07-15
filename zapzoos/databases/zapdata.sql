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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_abstract`
--

LOCK TABLES `zd_abstract` WRITE;
/*!40000 ALTER TABLE `zd_abstract` DISABLE KEYS */;
INSERT INTO `zd_abstract` VALUES (1,'',104,'db','数据库定义',0),(2,'',104020,'type','数据库类型',104),(3,'',104020013,'mysql','',104020),(4,'',104014,'notnull','非空',104),(5,'',104014001,'yes','非空',104014),(6,'',104014002,'no','允许空',104014),(7,'',104003,'column_type','数据列类型',104),(8,'',104003022,'varchar','',104003),(9,'',104003003,'char','',104003),(10,'',104003009,'int','',104003),(11,'',104003004,'decimal','',104003),(12,'',104003012,'longtext','',104003),(13,'',101,'all','系统定义',0),(14,'',101001,'flag','逻辑标识',101),(15,'',101001001,'yes','是',101001),(16,'',101001002,'no','否',101001),(17,'',104005,'field_type','字段类型',104),(18,'',104005008,'hidden','隐藏域',104005),(19,'',104005019,'select','下拉框',104005),(20,'',104005003,'control','控件',104005),(21,'',104005103,'checkbox','复选框',104005),(22,'',104005009,'input','输入框',104005),(24,'',119,'system','系统定义',0),(25,'',119678,'zapadmin','zapadmin',0),(35,'',116,'page','页面',0),(36,'',116016,'pagetype','页面类型',116),(37,'',116016001,'add','添加页面',116016),(38,'',116016003,'chart','列表页面',116016),(39,'',116016005,'edit','修改页面',116016),(40,'',116015,'operate','页面操作类型',116),(41,'',116015012,'link','链接',116015),(42,'',116015010,'js','脚本',116015),(43,'',116022,'view','视图',116),(44,'',116022001,'add','添加',116022),(45,'',116022005,'edit','修改',116022),(46,'',116022002,'book','展示',116022),(47,'',116022009,'inquery','查询',116022),(48,'',116022003,'chart','列表',116022);
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
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_column`
--

LOCK TABLES `zd_column` WRITE;
/*!40000 ALTER TABLE `zd_column` DISABLE KEYS */;
INSERT INTO `zd_column` VALUES (217,'e18783beed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','zid','',104003009,104014002,0,0,'1'),(218,'e1878657ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','uid','',104003003,104014002,32,0,'2'),(219,'e1878745ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_serial','购物卡流水号',104003003,104014001,12,0,'3'),(220,'e187882fed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_code','购物卡卡号',104003003,104014002,16,0,'4'),(221,'e18788eced1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_pass','购物卡密码',104003003,104014001,6,0,'5'),(222,'e1879798ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_money','购物卡金额',104003004,104014001,0,2,'6'),(223,'e1879876ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','used_money','已使用金额',104003004,104014001,0,2,'7'),(224,'e18798f1ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','inital_money','初始金额',104003004,104014001,0,2,'8'),(225,'e1879991ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','flag_active','激活标识',104003009,104014001,0,0,'9'),(226,'e1879a66ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','begin_time','开始使用时间',104003003,104014001,18,0,'10'),(227,'e1879b27ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','finish_time','截止使用时间',104003003,104014001,18,0,'11'),(228,'e1879bd0ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo','create_time','创建时间',104003003,104014001,18,0,'12'),(229,'e1879c4fed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','zid','',104003009,104014002,0,0,'1'),(230,'e187a083ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','uid','',104003003,104014001,32,0,'2'),(231,'e187a18ded1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','card_code','卡号',104003022,104014001,45,0,'3'),(232,'e187a252ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_money','变化金额',104003004,104014001,0,2,'4'),(233,'e187a320ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_time','变更时间',104003003,104014001,18,0,'5'),(234,'e187a3e5ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_people','变更人',104003022,104014001,45,0,'6'),(235,'e187a4a6ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_source','变更原因',104003022,104014001,45,0,'7'),(236,'e187a556ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_code','相关单号',104003022,104014001,45,0,'8'),(237,'e187a716ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_remark','变更备注',104003022,104014001,450,0,'9'),(238,'e187a821ed1011e2abe65404a6a9ac98','cardcenter','cc_createlog','zid','',104003009,104014002,0,0,'1'),(239,'e187a8eeed1011e2abe65404a6a9ac98','cardcenter','cc_createlog','uid','',104003003,104014001,32,0,'2'),(240,'e187a9bfed1011e2abe65404a6a9ac98','cardcenter','cc_createlog','task_code','',104003022,104014001,45,0,'3'),(241,'e187aae3ed1011e2abe65404a6a9ac98','cardcenter','cc_createtask','zid','',104003009,104014002,0,0,'1'),(242,'e187abc4ed1011e2abe65404a6a9ac98','cardcenter','cc_createtask','uid','',104003003,104014001,32,0,'2'),(243,'e187ac89ed1011e2abe65404a6a9ac98','cardcenter','cc_createtask','task_code','任务编号',104003022,104014001,45,0,'3'),(244,'e187ad73ed1011e2abe65404a6a9ac98','cardcenter','cc_createtask','task_name','任务名称',104003022,104014001,450,0,'4'),(245,'e187af16ed1011e2abe65404a6a9ac98','cardcenter','cc_createtask','task_remark','备注',104003022,104014001,4500,0,'5'),(246,'e187aff4ed1011e2abe65404a6a9ac98','cardcenter','cc_createtask','create_time','创建时间',104003003,104014001,18,0,'6'),(247,'e187b0d2ed1011e2abe65404a6a9ac98','cardcenter','cc_createtask','create_user','创建人',104003022,104014001,45,0,'7'),(248,'e187b197ed1011e2abe65404a6a9ac98','cardcenter','cc_define','zid','',104003009,104014002,0,0,'1'),(249,'e187b253ed1011e2abe65404a6a9ac98','cardcenter','cc_define','uid','',104003003,104014001,32,0,'2'),(250,'e187b30ced1011e2abe65404a6a9ac98','cardcenter','cc_define','define_dids','定义编号',104003022,104014001,30,0,'3'),(251,'e187b3b9ed1011e2abe65404a6a9ac98','cardcenter','cc_define','define_name','定义名称',104003022,104014001,450,0,'4'),(252,'e187b469ed1011e2abe65404a6a9ac98','cardcenter','cc_define','define_note','描述',104003022,104014001,45,0,'5'),(253,'e187b51eed1011e2abe65404a6a9ac98','cardcenter','cc_define','level_rank','级别',104003009,104014001,0,0,'6'),(254,'e187b5caed1011e2abe65404a6a9ac98','cardcenter','cc_define','parent_did','父编号',104003022,104014001,45,0,'7'),(255,'e187b65eed1011e2abe65404a6a9ac98','cardcenter','cc_moneytype','zid','',104003009,104014002,0,0,'1'),(256,'e187b70fed1011e2abe65404a6a9ac98','cardcenter','cc_moneytype','uid','',104003003,104014001,32,0,'2'),(257,'e187b7c7ed1011e2abe65404a6a9ac98','cardcenter','cc_moneytype','moeney_type','金额类型',104003009,104014001,0,0,'3'),(258,'e187bddbed1011e2abe65404a6a9ac98','cardcenter','cc_moneytype','money_value','金额值',104003009,104014001,0,0,'4'),(259,'e187bee2ed1011e2abe65404a6a9ac98','zapdata','zd_abstract','zid','',104003009,104014002,0,0,'1'),(260,'e187bf86ed1011e2abe65404a6a9ac98','zapdata','zd_abstract','uid','',104003003,104014001,32,0,'2'),(261,'e187c060ed1011e2abe65404a6a9ac98','zapdata','zd_abstract','abstract_aids','编码',104003009,104014001,0,0,'3'),(262,'e187c1bded1011e2abe65404a6a9ac98','zapdata','zd_abstract','abstract_value','值',104003022,104014001,45,0,'4'),(263,'e187c2f5ed1011e2abe65404a6a9ac98','zapdata','zd_abstract','abstract_remark','备注',104003022,104014001,450,0,'5'),(264,'e187c3aded1011e2abe65404a6a9ac98','zapdata','zd_abstract','parent_aid','父编号',104003009,104014001,0,0,'6'),(265,'e187c46eed1011e2abe65404a6a9ac98','zapdata','zd_column','zid','',104003009,104014002,0,0,'1'),(266,'e187c533ed1011e2abe65404a6a9ac98','zapdata','zd_column','uid','',104003003,104014001,32,0,'2'),(267,'e187c62aed1011e2abe65404a6a9ac98','zapdata','zd_column','server_name','库名称',104003022,104014001,45,0,'3'),(268,'e187c6e2ed1011e2abe65404a6a9ac98','zapdata','zd_column','table_name','表名称',104003022,104014001,45,0,'4'),(269,'e187c77eed1011e2abe65404a6a9ac98','zapdata','zd_column','column_name','列名',104003022,104014001,45,0,'5'),(270,'e187c816ed1011e2abe65404a6a9ac98','zapdata','zd_column','column_note','字段描述',104003022,104014001,45,0,'6'),(271,'e187c8f4ed1011e2abe65404a6a9ac98','zapdata','zd_column','column_type_aid','列类型编号',104003009,104014001,0,0,'7'),(272,'e187ca40ed1011e2abe65404a6a9ac98','zapdata','zd_column','null_able_aid','是否允许空',104003009,104014001,0,0,'8'),(273,'e187cb01ed1011e2abe65404a6a9ac98','zapdata','zd_column','column_length','字段长度',104003009,104014001,0,0,'9'),(274,'e187cc87ed1011e2abe65404a6a9ac98','zapdata','zd_column','length_scale','小数点',104003009,104014001,0,0,'10'),(275,'e187cd48ed1011e2abe65404a6a9ac98','zapdata','zd_column','column_sort','排序',104003022,104014001,45,0,'11'),(276,'e187cdf5ed1011e2abe65404a6a9ac98','zapdata','zd_server','zid','',104003009,104014002,0,0,'1'),(277,'e187cea5ed1011e2abe65404a6a9ac98','zapdata','zd_server','uid','',104003003,104014001,32,0,'2'),(278,'e187cf87ed1011e2abe65404a6a9ac98','zapdata','zd_server','server_code','数据库编码',104003022,104014001,45,0,'3'),(279,'e187d0a6ed1011e2abe65404a6a9ac98','zapdata','zd_server','server_name','数据库名称',104003022,104014001,45,0,'4'),(280,'e187d174ed1011e2abe65404a6a9ac98','zapdata','zd_server','server_type_aid','数据库类型',104003009,104014001,0,0,'5'),(281,'e187d239ed1011e2abe65404a6a9ac98','zapdata','zd_server','jdbc_driver','驱动',104003022,104014001,450,0,'6'),(282,'e187d3d7ed1011e2abe65404a6a9ac98','zapdata','zd_server','jdbc_dburl','路径',104003022,104014001,450,0,'7'),(283,'e187d498ed1011e2abe65404a6a9ac98','zapdata','zd_server','jdbc_user','用户名',104003022,104014001,45,0,'8'),(284,'e187d541ed1011e2abe65404a6a9ac98','zapdata','zd_server','jdbc_password','密码',104003022,104014001,45,0,'9'),(285,'e187d5e9ed1011e2abe65404a6a9ac98','zapdata','zd_tables','zid','',104003009,104014002,0,0,'1'),(286,'e187d75aed1011e2abe65404a6a9ac98','zapdata','zd_tables','uid','',104003003,104014001,32,0,'2'),(287,'e187d88eed1011e2abe65404a6a9ac98','zapdata','zd_tables','server_name','数据库名称',104003022,104014001,45,0,'3'),(288,'e187d9a5ed1011e2abe65404a6a9ac98','zapdata','zd_tables','table_name','表名称',104003022,104014001,45,0,'4'),(289,'e187ddf2ed1011e2abe65404a6a9ac98','zapdata','zd_tables','table_remark','备注',104003022,104014001,450,0,'5'),(290,'e187e831ed1011e2abe65404a6a9ac98','zapdata','zw_define','zid','',104003009,104014002,0,0,'1'),(291,'e187eb0ced1011e2abe65404a6a9ac98','zapdata','zw_define','uid','',104003003,104014001,32,0,'2'),(292,'e187ec79ed1011e2abe65404a6a9ac98','zapdata','zw_define','define_dids','定义编号',104003022,104014001,30,0,'3'),(293,'e187ed80ed1011e2abe65404a6a9ac98','zapdata','zw_define','define_name','定义名称',104003022,104014001,450,0,'4'),(294,'e187ee20ed1011e2abe65404a6a9ac98','zapdata','zw_define','define_note','描述',104003022,104014001,45,0,'5'),(295,'e187eec4ed1011e2abe65404a6a9ac98','zapdata','zw_define','level_rank','级别',104003009,104014001,0,0,'6'),(296,'e187ef64ed1011e2abe65404a6a9ac98','zapdata','zw_define','parent_did','父编号',104003022,104014001,45,0,'7'),(297,'e187f004ed1011e2abe65404a6a9ac98','zapdata','zw_field','zid','',104003009,104014002,0,0,'1'),(298,'e187f0aded1011e2abe65404a6a9ac98','zapdata','zw_field','uid','',104003003,104014001,32,0,'2'),(299,'e187f144ed1011e2abe65404a6a9ac98','zapdata','zw_field','view_code','视图编号',104003022,104014001,45,0,'3'),(300,'e187f1e9ed1011e2abe65404a6a9ac98','zapdata','zw_field','column_name','数据列名',104003022,104014001,45,0,'4'),(301,'e187f291ed1011e2abe65404a6a9ac98','zapdata','zw_field','field_note','字段名称',104003022,104014001,45,0,'5'),(302,'e187f346ed1011e2abe65404a6a9ac98','zapdata','zw_field','sort_add','排序添加',104003022,104014001,45,0,'6'),(303,'e187f402ed1011e2abe65404a6a9ac98','zapdata','zw_field','sort_edit','排序修改',104003022,104014001,45,0,'7'),(304,'e187f4c3ed1011e2abe65404a6a9ac98','zapdata','zw_field','sort_chart','排序列表',104003022,104014001,45,0,'8'),(305,'e187f56ced1011e2abe65404a6a9ac98','zapdata','zw_field','sort_book','排序展示',104003022,104014001,45,0,'9'),(306,'e187f620ed1011e2abe65404a6a9ac98','zapdata','zw_field','sort_inquery','排序查询',104003022,104014001,45,0,'10'),(307,'e187f6e1ed1011e2abe65404a6a9ac98','zapdata','zw_field','field_type_aid','字段类型',104003009,104014001,0,0,'11'),(308,'e187f7afed1011e2abe65404a6a9ac98','zapdata','zw_manager','zid','',104003009,104014002,0,0,'1'),(309,'e187f87ced1011e2abe65404a6a9ac98','zapdata','zw_manager','uid','',104003003,104014001,32,0,'2'),(310,'e187f945ed1011e2abe65404a6a9ac98','zapdata','zw_manager','manager_name','',104003022,104014001,45,0,'3'),(311,'e187fa02ed1011e2abe65404a6a9ac98','zapdata','zw_manager','manager_pass','',104003022,104014001,45,0,'4'),(312,'e187faa6ed1011e2abe65404a6a9ac98','zapdata','zw_menu','zid','',104003009,104014002,0,0,'1'),(313,'e187fb4eed1011e2abe65404a6a9ac98','zapdata','zw_menu','uid','',104003003,104014001,32,0,'2'),(314,'e187fbeeed1011e2abe65404a6a9ac98','zapdata','zw_menu','menu_code','菜单编号',104003022,104014001,45,0,'3'),(315,'e187fc8eed1011e2abe65404a6a9ac98','zapdata','zw_menu','menu_name','菜单名称',104003022,104014001,45,0,'4'),(316,'e187fd4bed1011e2abe65404a6a9ac98','zapdata','zw_menu','parent_code','父编号',104003022,104014001,45,0,'5'),(317,'e187fe10ed1011e2abe65404a6a9ac98','zapdata','zw_menu','page_code','页面编号',104003022,104014001,45,0,'6'),(318,'e187fec1ed1011e2abe65404a6a9ac98','zapdata','zw_operate','zid','',104003009,104014002,0,0,'1'),(319,'e187ff7aed1011e2abe65404a6a9ac98','zapdata','zw_operate','uid','',104003003,104014001,32,0,'2'),(320,'e188003bed1011e2abe65404a6a9ac98','zapdata','zw_operate','operate_name','操作名称',104003022,104014001,45,0,'3'),(321,'e18800fced1011e2abe65404a6a9ac98','zapdata','zw_operate','operate_type_aid','操作类型',104003009,104014001,0,0,'4'),(322,'e18801c1ed1011e2abe65404a6a9ac98','zapdata','zw_operate','page_code','页面编码',104003022,104014001,450,0,'5'),(323,'e1880282ed1011e2abe65404a6a9ac98','zapdata','zw_operate','operate_link','操作链接',104003022,104014001,450,0,'6'),(324,'e188032aed1011e2abe65404a6a9ac98','zapdata','zw_operate','flag_enable','是否可用',104003009,104014001,0,0,'7'),(325,'e18803d2ed1011e2abe65404a6a9ac98','zapdata','zw_page','zid','',104003009,104014002,0,0,'1'),(326,'e1880483ed1011e2abe65404a6a9ac98','zapdata','zw_page','uid','',104003003,104014001,32,0,'2'),(327,'e1880533ed1011e2abe65404a6a9ac98','zapdata','zw_page','page_code','页面编号',104003022,104014001,45,0,'3'),(328,'e18805f4ed1011e2abe65404a6a9ac98','zapdata','zw_page','page_name','页面名称',104003022,104014001,450,0,'4'),(329,'e18806a9ed1011e2abe65404a6a9ac98','zapdata','zw_page','page_template','页面调用模板',104003022,104014001,45,0,'5'),(330,'e1880755ed1011e2abe65404a6a9ac98','zapdata','zw_page','view_code','视图编码',104003022,104014001,45,0,'6'),(331,'e1880c7bed1011e2abe65404a6a9ac98','zapdata','zw_page','page_type_aid','页面类型',104003022,104014001,45,0,'7'),(332,'e1880d75ed1011e2abe65404a6a9ac98','zapdata','zw_page','page_group','页面组',104003022,104014001,450,0,'8'),(333,'e1880e3aed1011e2abe65404a6a9ac98','zapdata','zw_role','zid','',104003009,104014002,0,0,'1'),(334,'e1880ee7ed1011e2abe65404a6a9ac98','zapdata','zw_role','uid','',104003003,104014001,32,0,'2'),(335,'e1880f87ed1011e2abe65404a6a9ac98','zapdata','zw_role','role_code','角色编码',104003022,104014001,45,0,'3'),(336,'e1881013ed1011e2abe65404a6a9ac98','zapdata','zw_role','role_name','角色名称',104003022,104014001,45,0,'4'),(337,'e1881096ed1011e2abe65404a6a9ac98','zapdata','zw_role','parent_code','父编码',104003022,104014001,45,0,'5'),(338,'e188112aed1011e2abe65404a6a9ac98','zapdata','zw_source','zid','',104003009,104014002,0,0,'1'),(339,'e18811b1ed1011e2abe65404a6a9ac98','zapdata','zw_source','uid','',104003003,104014001,32,0,'2'),(340,'e1881241ed1011e2abe65404a6a9ac98','zapdata','zw_source','source_code','数据源编编码',104003022,104014001,45,0,'3'),(341,'e18812d5ed1011e2abe65404a6a9ac98','zapdata','zw_source','source_name','数据源名称',104003022,104014001,45,0,'4'),(342,'e1881368ed1011e2abe65404a6a9ac98','zapdata','zw_view','zid','',104003009,104014002,0,0,'1'),(343,'e18813f8ed1011e2abe65404a6a9ac98','zapdata','zw_view','uid','',104003003,104014001,32,0,'2'),(344,'e18814c1ed1011e2abe65404a6a9ac98','zapdata','zw_view','view_code','视图编码',104003022,104014001,45,0,'3'),(345,'e188156eed1011e2abe65404a6a9ac98','zapdata','zw_view','view_name','试图名称',104003022,104014001,45,0,'4'),(346,'e18815f9ed1011e2abe65404a6a9ac98','zapdata','zw_view','table_name','视图表格',104003022,104014001,45,0,'5');
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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_tables`
--

LOCK TABLES `zd_tables` WRITE;
/*!40000 ALTER TABLE `zd_tables` DISABLE KEYS */;
INSERT INTO `zd_tables` VALUES (29,'e17ad317ed1011e2abe65404a6a9ac98','cardcenter','cc_cardinfo',''),(30,'e17ad504ed1011e2abe65404a6a9ac98','cardcenter','cc_cardlog',''),(31,'e17ad6f9ed1011e2abe65404a6a9ac98','cardcenter','cc_createlog',''),(32,'e17ad7ebed1011e2abe65404a6a9ac98','cardcenter','cc_createtask',''),(33,'e17ad8d1ed1011e2abe65404a6a9ac98','cardcenter','cc_define',''),(34,'e17ad9aaed1011e2abe65404a6a9ac98','cardcenter','cc_moneytype',''),(35,'e17ada8ced1011e2abe65404a6a9ac98','zapdata','zd_abstract',''),(36,'e17adb6aed1011e2abe65404a6a9ac98','zapdata','zd_column',''),(37,'e17adc43ed1011e2abe65404a6a9ac98','zapdata','zd_server',''),(38,'e17add19ed1011e2abe65404a6a9ac98','zapdata','zd_tables',''),(39,'e17addf2ed1011e2abe65404a6a9ac98','zapdata','zw_define',''),(40,'e17adec8ed1011e2abe65404a6a9ac98','zapdata','zw_field','字段表'),(41,'e17adfa6ed1011e2abe65404a6a9ac98','zapdata','zw_manager',''),(42,'e17ae07bed1011e2abe65404a6a9ac98','zapdata','zw_menu','菜单表'),(43,'e17ae159ed1011e2abe65404a6a9ac98','zapdata','zw_operate',''),(44,'e17ae22eed1011e2abe65404a6a9ac98','zapdata','zw_page',''),(45,'e17ae308ed1011e2abe65404a6a9ac98','zapdata','zw_role',''),(46,'e17ae3eaed1011e2abe65404a6a9ac98','zapdata','zw_source','数据源表'),(47,'e17ae4d4ed1011e2abe65404a6a9ac98','zapdata','zw_view','视图表');
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
  `field_type_aid` int(11) DEFAULT '104005008' COMMENT '字段类型',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='字段表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_field`
--

LOCK TABLES `zw_field` WRITE;
/*!40000 ALTER TABLE `zw_field` DISABLE KEYS */;
INSERT INTO `zw_field` VALUES (22,'32a3ea49ecfd11e2abe65404a6a9ac98','v_zw_view','zid','','0','0','0','0','0',104005008),(23,'32a3eecaecfd11e2abe65404a6a9ac98','v_zw_view','uid','','0','1002','0','0','0',104005008),(24,'32a3f2d9ecfd11e2abe65404a6a9ac98','v_zw_view','view_code','视图编码','1003','1003','1003','1003','1003',104005009),(25,'32a3f490ecfd11e2abe65404a6a9ac98','v_zw_view','view_name','试图名称','1004','1004','1004','1004','1004',104005009),(26,'32a3f626ecfd11e2abe65404a6a9ac98','v_zw_view','table_name','视图表格','1005','1005','1005','1005','1005',104005009),(29,'f9744298ed1011e2abe65404a6a9ac98','v_zw_field','zid','','0','0','0','0','0',104005008),(30,'f974449eed1011e2abe65404a6a9ac98','v_zw_field','uid','','0','1002','0','0','0',104005008),(31,'f974458ced1011e2abe65404a6a9ac98','v_zw_field','view_code','视图编号','1003','1003','1003','1003','1003',104005009),(32,'f9744676ed1011e2abe65404a6a9ac98','v_zw_field','column_name','数据列名','1004','1004','1004','1004','1004',104005009),(33,'f974474fed1011e2abe65404a6a9ac98','v_zw_field','field_note','字段名称','1005','1005','1005','1005','1005',104005009),(34,'f9744829ed1011e2abe65404a6a9ac98','v_zw_field','sort_add','排序添加','1006','1006','1006','1006','1006',104005009),(35,'f97448faed1011e2abe65404a6a9ac98','v_zw_field','sort_edit','排序修改','1007','1007','1007','1007','1007',104005009),(36,'f97449c7ed1011e2abe65404a6a9ac98','v_zw_field','sort_chart','排序列表','1008','1008','1008','1008','1008',104005009),(37,'f9744a91ed1011e2abe65404a6a9ac98','v_zw_field','sort_book','排序展示','1009','1009','1009','1009','1009',104005009),(38,'f9744b5eed1011e2abe65404a6a9ac98','v_zw_field','sort_inquery','排序查询','1010','1010','1010','1010','1010',104005009),(39,'f9744c27ed1011e2abe65404a6a9ac98','v_zw_field','field_type_aid','字段类型','1011','1011','1011','1011','1011',104005009);
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
-- Table structure for table `zw_menu`
--

DROP TABLE IF EXISTS `zw_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_menu` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `menu_code` varchar(45) DEFAULT '' COMMENT '菜单编号',
  `menu_name` varchar(45) DEFAULT '' COMMENT '菜单名称',
  `parent_code` varchar(45) DEFAULT '' COMMENT '父编号',
  `page_code` varchar(45) DEFAULT '' COMMENT '页面编号',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_menu`
--

LOCK TABLES `zw_menu` WRITE;
/*!40000 ALTER TABLE `zw_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `zw_menu` ENABLE KEYS */;
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
  `flag_enable` int(11) DEFAULT '101001001' COMMENT '是否可用',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_operate`
--

LOCK TABLES `zw_operate` WRITE;
/*!40000 ALTER TABLE `zw_operate` DISABLE KEYS */;
INSERT INTO `zw_operate` VALUES (17,'f5ca23eeed0e11e2abe65404a6a9ac98','添加',116015012,'page_chart_v_zw_view','page_add_v_zw_view',101001001),(18,'f5ca7dfced0e11e2abe65404a6a9ac98','修改',116015012,'page_chart_v_zw_view','page_chart_v_zw_view',101001001),(19,'f5ca9c60ed0e11e2abe65404a6a9ac98','删除',116015010,'page_chart_v_zw_view','zenjs.zw.func_delete(this)',101001001),(20,'f5cabadced0e11e2abe65404a6a9ac98','提交新增',116015010,'page_add_v_zw_view','zenjs.zw.func_add(this)',101001001),(21,'f5cae2a5ed0e11e2abe65404a6a9ac98','提交修改',116015010,'page_edit_v_zw_view','zenjs.zw.func_edit(this)',101001001),(22,'a9381ba9ed1111e2abe65404a6a9ac98','添加',116015012,'page_chart_v_zw_field','page_add_v_zw_field',101001001),(23,'a9390830ed1111e2abe65404a6a9ac98','修改',116015012,'page_chart_v_zw_field','page_chart_v_zw_field',101001001),(24,'a93930cfed1111e2abe65404a6a9ac98','删除',116015010,'page_chart_v_zw_field','zenjs.zw.func_delete(this)',101001001),(25,'a9397224ed1111e2abe65404a6a9ac98','提交新增',116015010,'page_add_v_zw_field','zenjs.zw.func_add(this)',101001001),(26,'a939b24ded1111e2abe65404a6a9ac98','提交修改',116015010,'page_edit_v_zw_field','zenjs.zw.func_edit(this)',101001001);
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
  `page_template` varchar(45) DEFAULT '' COMMENT '页面调用模板',
  `view_code` varchar(45) DEFAULT '' COMMENT '视图编码',
  `page_type_aid` varchar(45) DEFAULT '0' COMMENT '页面类型',
  `page_group` varchar(450) DEFAULT '' COMMENT '页面组',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `page_code_UNIQUE` (`page_code`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_page`
--

LOCK TABLES `zw_page` WRITE;
/*!40000 ALTER TABLE `zw_page` DISABLE KEYS */;
INSERT INTO `zw_page` VALUES (30,'3b8eb875ed1211e2abe65404a6a9ac98','page_chart_v_zw_view','系统视图-列表','chart','v_zw_view','116016003','grouppage_v_zw_view'),(31,'3b90a04eed1211e2abe65404a6a9ac98','page_add_v_zw_view','系统视图-新增','add','v_zw_view','116016001','grouppage_v_zw_view'),(32,'3b90c452ed1211e2abe65404a6a9ac98','page_edit_v_zw_view','系统视图-修改','edit','v_zw_view','116016005','grouppage_v_zw_view'),(33,'3b912b00ed1211e2abe65404a6a9ac98','page_chart_v_zw_field','字段视图-列表','chart','v_zw_field','116016003','grouppage_v_zw_field'),(34,'3b915484ed1211e2abe65404a6a9ac98','page_add_v_zw_field','字段视图-新增','add','v_zw_field','116016001','grouppage_v_zw_field'),(35,'3b9179feed1211e2abe65404a6a9ac98','page_edit_v_zw_field','字段视图-修改','edit','v_zw_field','116016005','grouppage_v_zw_field');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='视图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_view`
--

LOCK TABLES `zw_view` WRITE;
/*!40000 ALTER TABLE `zw_view` DISABLE KEYS */;
INSERT INTO `zw_view` VALUES (1,'','v_zw_view','系统视图','zw_view'),(2,'','v_zw_field','字段视图','zw_field');
/*!40000 ALTER TABLE `zw_view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'zapdata'
--
/*!50003 DROP PROCEDURE IF EXISTS `proc_zw_allview` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `proc_zw_allview`()
begin

declare p_min int;
declare p_vc varchar(100);

set p_min=(select min(zid) from zw_view);

while p_min>0 do
set p_vc=(select view_code from zw_view where zid=p_min);

call proc_zw_initview(p_vc);
set p_min=ifnull((select min(zid) from zw_view where zid>p_min),0);
end while;




end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_zw_initview` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `proc_zw_initview`(in p_view_code varchar(100))
begin


INSERT INTO `zw_field`
(
`uid`,
`view_code`,
`column_name`,
`field_note`,
`sort_add`,
`sort_edit`,
`sort_chart`,
`sort_book`,
`sort_inquery`,
`field_type_aid`)

select replace(uuid(),'-','') as uid
,zwv.view_code as view_code
,zdc.column_name as column_name
,zdc.column_note as field_note
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 1000+zdc.column_sort end)  as sort_add
,(case zdc.column_name when 'zid' then 0 else 1000+zdc.column_sort end) as sort_edit
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 1000+zdc.column_sort end) as sort_chart
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 1000+zdc.column_sort end) as sort_book
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 1000+zdc.column_sort end) as sort_inquery
,(case zdc.column_name when 'zid' then 104005008 when 'uid' then 104005008 else 104005009 end) as field_type_aid
from zapdata.zd_column zdc left join zw_view zwv
on zdc.table_name=zwv.table_name
where concat(zwv.view_code,zdc.column_name)
not in(select concat(view_code,column_name) from zw_field)
and zwv.view_code=p_view_code;



#chart
INSERT INTO `zapdata`.`zw_page`
(
`uid`,
`page_code`,
`page_name`,
`page_template`,
`view_code`,
`page_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_chart_',zwv.view_code) as page_code
,concat(zwv.view_name,'-列表') as page_name
,'chart' as page_template
,zwv.view_code as view_code
,116016003 as page_type_aid
,concat('grouppage_',zwv.view_code) as page_code
from zw_view zwv
where 
(select count(1) from zapdata.zw_page where page_code=concat('page_chart_',zwv.view_code))=0
and zwv.view_code=p_view_code;

#add
INSERT INTO `zapdata`.`zw_page`
(
`uid`,
`page_code`,
`page_name`,
`page_template`,
`view_code`,
`page_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_add_',zwv.view_code) as page_code
,concat(zwv.view_name,'-新增') as page_name
,'add' as page_template
,zwv.view_code as view_code
,116016001 as page_type_aid
,concat('grouppage_',zwv.view_code) as page_code
from zw_view zwv
where 
(select count(1) from zapdata.zw_page where page_code=concat('page_add_',zwv.view_code))=0
and zwv.view_code=p_view_code;

#edit
INSERT INTO `zapdata`.`zw_page`
(
`uid`,
`page_code`,
`page_name`,
`page_template`,
`view_code`,
`page_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_edit_',zwv.view_code) as page_code
,concat(zwv.view_name,'-修改') as page_name
,'edit' as page_template
,zwv.view_code as view_code
,116016005 as page_type_aid
,concat('grouppage_',zwv.view_code) as page_code
from zw_view zwv
where 
(select count(1) from zapdata.zw_page where page_code=concat('page_edit_',zwv.view_code))=0
and zwv.view_code=p_view_code;





INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`)
select
replace(uuid(),'-','') as uid
,'添加' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016001 and view_code=zwp.view_code) as operate_link
,'101001001' as flag_enable
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('添加',zwp.page_code))=0
and zwp.page_type_aid=116016003 and  zwp.view_code=p_view_code;

INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`)
select
replace(uuid(),'-','') as uid
,'修改' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016003 and view_code=zwp.view_code) as operate_link
,'101001001' as flag_enable
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('修改',zwp.page_code))=0
and zwp.page_type_aid=116016003 and  zwp.view_code=p_view_code;


INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`)
select
replace(uuid(),'-','') as uid
,'删除' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zenjs.zw.func_delete(this)' as operate_link
,'101001001' as flag_enable
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('删除',zwp.page_code))=0
and zwp.page_type_aid=116016003 and  zwp.view_code=p_view_code;


INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`)
select
replace(uuid(),'-','') as uid
,'提交新增' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zenjs.zw.func_add(this)' as operate_link
,'101001001' as flag_enable
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('提交新增',zwp.page_code))=0
and zwp.page_type_aid=116016001 and  zwp.view_code=p_view_code;


INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`)
select
replace(uuid(),'-','') as uid
,'提交修改' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zenjs.zw.func_edit(this)' as operate_link
,'101001001' as flag_enable
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('提交修改',zwp.page_code))=0
and zwp.page_type_aid=116016005 and  zwp.view_code=p_view_code;























































end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-15 18:33:14
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
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

-- Dump completed on 2013-07-15 18:33:15
