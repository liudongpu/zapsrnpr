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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_abstract`
--

LOCK TABLES `zd_abstract` WRITE;
/*!40000 ALTER TABLE `zd_abstract` DISABLE KEYS */;
INSERT INTO `zd_abstract` VALUES (1,'',104,'db','数据库定义',100),(2,'',104020,'type','数据库类型',104),(3,'',104020013,'mysql','',104020),(4,'',104014,'notnull','非空',104),(5,'',104014001,'yes','非空',104014),(6,'',104014002,'no','允许空',104014),(7,'',104003,'column_type','数据列类型',104),(8,'',104003022,'varchar','',104003),(9,'',104003003,'char','',104003),(10,'',104003009,'int','',104003),(11,'',104003004,'decimal','',104003),(12,'',104003012,'longtext','',104003),(13,'',101,'all','系统定义',100),(14,'',101001,'flag','逻辑标识',101),(15,'',101001001,'yes','是',101001),(16,'',101001002,'no','否',101001),(17,'',104005,'field_type','字段类型',104),(18,'',104005008,'hidden','隐藏域',104005),(19,'',104005019,'select','下拉框',104005),(20,'',104005003,'control','控件',104005),(21,'',104005103,'checkbox','复选框',104005),(22,'',104005009,'input','输入框',104005),(24,'',119,'system','系统定义',100),(25,'',119678,'zapadmin','zapadmin',100),(35,'',116,'page','页面',100),(36,'',116016,'pagetype','页面类型',116),(37,'',116016001,'add','添加页面',116016),(38,'',116016003,'chart','列表页面',116016),(39,'',116016005,'edit','修改页面',116016),(40,'',116015,'operate','页面操作类型',116),(41,'',116015012,'link','链接',116015),(42,'',116015010,'js','脚本',116015),(43,'',116022,'view','视图',116),(44,'',116022001,'add','添加',116022),(45,'',116022005,'edit','修改',116022),(46,'',116022002,'book','展示',116022),(47,'',116022009,'inquery','查询',116022),(48,'',116022003,'chart','列表',116022),(49,'',101002,'project define','系统定义',101),(50,'',101002677,'zapadmin','超级后台管理',101002),(51,'',101002081,'cardcenter','卡中心系统',101002),(52,'',100,'abstract','原始定义',100),(53,'',1,'yes','是',100),(54,'',0,'no','否',100),(55,'',104005005,'editor','编辑框',104005),(56,'',104005909,'inputbetween','',104005),(57,'',104005904,'datebetween','',104005),(58,'',104009,'inquire type','查询类型',104),(59,'',104009005,'equal','等于',104009),(60,'',104009002,'between','范围',104009),(61,'',104009012,'like','匹配',10400);
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
) ENGINE=InnoDB AUTO_INCREMENT=1557 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_column`
--

LOCK TABLES `zd_column` WRITE;
/*!40000 ALTER TABLE `zd_column` DISABLE KEYS */;
INSERT INTO `zd_column` VALUES (1268,'a06671feedca11e2abe65404a6a9ac98','cardcenter','cc_agentuser','zid','',104003009,104014002,10,0,'1'),(1269,'a06673fbedca11e2abe65404a6a9ac98','cardcenter','cc_agentuser','uid','',104003003,104014001,32,0,'2'),(1270,'a0667486edca11e2abe65404a6a9ac98','cardcenter','cc_agentuser','agent_code','代理商编号',104003022,104014001,450,0,'3'),(1271,'a06674feedca11e2abe65404a6a9ac98','cardcenter','cc_agentuser','agent_name','代理商名称',104003022,104014001,450,0,'4'),(1272,'a066779fedca11e2abe65404a6a9ac98','cardcenter','cc_agentuser','flag_used','是否可用',104003009,104014001,10,0,'5'),(1273,'a066780eedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','zid','',104003009,104014002,10,0,'1'),(1274,'a0667870edca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','uid','',104003003,104014002,32,0,'2'),(1275,'a06678cfedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_serial','购物卡流水号',104003003,104014002,12,0,'3'),(1276,'a0667939edca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_code','购物卡卡号',104003003,104014002,16,0,'4'),(1277,'a066799cedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_pass','购物卡密码',104003022,104014002,200,0,'5'),(1278,'a06679feedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','card_money','购物卡金额',104003004,104014001,18,2,'6'),(1279,'a0667a5dedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','used_money','已使用金额',104003004,104014001,18,2,'7'),(1280,'a0667abfedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','inital_money','初始金额',104003004,104014001,18,2,'8'),(1281,'a0667b1eedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','flag_active','激活标识',104003009,104014001,10,0,'9'),(1282,'a0667b80edca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','begin_time','开始使用时间',104003003,104014001,18,0,'10'),(1283,'a0667bdfedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','finish_time','截止使用时间',104003003,104014001,18,0,'11'),(1284,'a0667c45edca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','create_time','创建时间',104003003,104014001,18,0,'12'),(1285,'a0667ca8edca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','active_time','激活时间',104003003,104014001,18,0,'13'),(1286,'a0667d0aedca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','flag_send','发放标记',104003009,104014001,10,0,'14'),(1287,'a0667d69edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','zid','',104003009,104014002,10,0,'1'),(1288,'a0667dcbedca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','uid','',104003003,104014001,32,0,'2'),(1289,'a0667e2aedca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','card_code','卡号',104003022,104014001,45,0,'3'),(1290,'a0667e94edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_money','变化金额',104003004,104014001,18,2,'4'),(1291,'a0667ef7edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_time','变更时间',104003003,104014001,18,0,'5'),(1292,'a0667f55edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_people','变更人',104003022,104014001,45,0,'6'),(1293,'a0667fb0edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_source','变更原因',104003022,104014001,45,0,'7'),(1294,'a0668016edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_code','相关单号',104003022,104014001,45,0,'8'),(1295,'a0668071edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_remark','变更备注',104003022,104014001,450,0,'9'),(1296,'a06680d7edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','card_money','当前金额',104003004,104014001,18,2,'10'),(1297,'a066813aedca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','change_type_did','变更类型',104003022,104014001,45,0,'11'),(1298,'a0668395edca11e2abe65404a6a9ac98','cardcenter','cc_createtask','zid','',104003009,104014002,10,0,'1'),(1299,'a06683fcedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','uid','',104003003,104014001,32,0,'2'),(1300,'a066845eedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','task_code','任务编号',104003022,104014001,45,0,'3'),(1301,'a06684bdedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','task_name','任务名称',104003022,104014001,450,0,'4'),(1302,'a066851fedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','task_remark','备注',104003022,104014001,4500,0,'5'),(1303,'a066857eedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','card_money','卡金额',104003004,104014001,18,2,'6'),(1304,'a06685dcedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','card_number','制卡数量',104003009,104014001,10,0,'7'),(1305,'a066863fedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','card_make','已生成数量',104003009,104014001,10,0,'8'),(1306,'a06686a1edca11e2abe65404a6a9ac98','cardcenter','cc_createtask','create_user','创建人',104003022,104014001,45,0,'9'),(1307,'a0668700edca11e2abe65404a6a9ac98','cardcenter','cc_createtask','create_time','创建时间',104003003,104014001,18,0,'10'),(1308,'a0668762edca11e2abe65404a6a9ac98','cardcenter','cc_createtask','serial_begin','开始流水号',104003022,104014001,12,0,'11'),(1309,'a06687c9edca11e2abe65404a6a9ac98','cardcenter','cc_createtask','serial_finish','结束流水号',104003022,104014001,12,0,'12'),(1310,'a066882bedca11e2abe65404a6a9ac98','cardcenter','cc_define','zid','',104003009,104014002,10,0,'1'),(1311,'a066888aedca11e2abe65404a6a9ac98','cardcenter','cc_define','uid','',104003003,104014001,32,0,'2'),(1312,'a06688ecedca11e2abe65404a6a9ac98','cardcenter','cc_define','define_dids','定义编号',104003022,104014001,30,0,'3'),(1313,'a066894bedca11e2abe65404a6a9ac98','cardcenter','cc_define','define_name','定义名称',104003022,104014001,450,0,'4'),(1314,'a06689b1edca11e2abe65404a6a9ac98','cardcenter','cc_define','define_note','描述',104003022,104014001,45,0,'5'),(1315,'a0668a14edca11e2abe65404a6a9ac98','cardcenter','cc_define','level_rank','级别',104003009,104014001,10,0,'6'),(1316,'a0668a6eedca11e2abe65404a6a9ac98','cardcenter','cc_define','parent_did','父编号',104003022,104014001,45,0,'7'),(1317,'a0668acdedca11e2abe65404a6a9ac98','cardcenter','cc_moneytype','zid','',104003009,104014002,10,0,'1'),(1318,'a0668b2fedca11e2abe65404a6a9ac98','cardcenter','cc_moneytype','uid','',104003003,104014001,32,0,'2'),(1319,'a0668b92edca11e2abe65404a6a9ac98','cardcenter','cc_moneytype','moeney_type','金额类型',104003009,104014001,10,0,'3'),(1320,'a0668d66edca11e2abe65404a6a9ac98','cardcenter','cc_moneytype','money_value','金额值',104003009,104014001,10,0,'4'),(1321,'a0668e1aedca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','zid','',104003009,104014002,10,0,'1'),(1322,'a0668e89edca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','uid','',104003003,104014001,32,0,'2'),(1323,'a0668ef8edca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','serial_begin','开始流水号',104003022,104014001,45,0,'3'),(1324,'a0669294edca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','serial_finish','结束流水号',104003022,104014001,45,0,'4'),(1325,'a06692f6edca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','agent_code','代理商编号',104003022,104014001,45,0,'5'),(1326,'a0669359edca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','create_manager','创建人',104003022,104014001,45,0,'6'),(1327,'a06693bbedca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','create_time','创建时间',104003003,104014001,18,0,'7'),(1328,'a0669416edca11e2abe65404a6a9ac98','zapdata','zd_abstract','zid','',104003009,104014002,10,0,'1'),(1329,'a0669474edca11e2abe65404a6a9ac98','zapdata','zd_abstract','uid','',104003003,104014001,32,0,'2'),(1330,'a06694d2edca11e2abe65404a6a9ac98','zapdata','zd_abstract','abstract_aids','编码',104003009,104014001,10,0,'3'),(1331,'a0669535edca11e2abe65404a6a9ac98','zapdata','zd_abstract','abstract_value','值',104003022,104014001,45,0,'4'),(1332,'a0669597edca11e2abe65404a6a9ac98','zapdata','zd_abstract','abstract_remark','备注',104003022,104014001,450,0,'5'),(1333,'a06695faedca11e2abe65404a6a9ac98','zapdata','zd_abstract','parent_aid','父编号',104003009,104014001,10,0,'6'),(1334,'a0669654edca11e2abe65404a6a9ac98','zapdata','zd_column','zid','',104003009,104014002,10,0,'1'),(1335,'a06696b3edca11e2abe65404a6a9ac98','zapdata','zd_column','uid','',104003003,104014001,32,0,'2'),(1336,'a066970dedca11e2abe65404a6a9ac98','zapdata','zd_column','server_name','库名称',104003022,104014001,45,0,'3'),(1337,'a0669767edca11e2abe65404a6a9ac98','zapdata','zd_column','table_name','表名称',104003022,104014001,45,0,'4'),(1338,'a06697caedca11e2abe65404a6a9ac98','zapdata','zd_column','column_name','列名',104003022,104014001,45,0,'5'),(1339,'a066982cedca11e2abe65404a6a9ac98','zapdata','zd_column','column_note','字段描述',104003022,104014001,45,0,'6'),(1340,'a0669893edca11e2abe65404a6a9ac98','zapdata','zd_column','column_type_aid','列类型编号',104003009,104014001,10,0,'7'),(1341,'a06698f1edca11e2abe65404a6a9ac98','zapdata','zd_column','null_able_aid','是否允许空',104003009,104014001,10,0,'8'),(1342,'a0669950edca11e2abe65404a6a9ac98','zapdata','zd_column','column_length','字段长度',104003009,104014001,10,0,'9'),(1343,'a0669ab5edca11e2abe65404a6a9ac98','zapdata','zd_column','length_scale','小数点',104003009,104014001,10,0,'10'),(1344,'a0669b65edca11e2abe65404a6a9ac98','zapdata','zd_column','column_sort','排序',104003022,104014001,45,0,'11'),(1345,'a0669bd0edca11e2abe65404a6a9ac98','zapdata','zd_server','zid','',104003009,104014002,10,0,'1'),(1346,'a0669c37edca11e2abe65404a6a9ac98','zapdata','zd_server','uid','',104003003,104014001,32,0,'2'),(1347,'a0669c9dedca11e2abe65404a6a9ac98','zapdata','zd_server','server_code','数据库编码',104003022,104014001,45,0,'3'),(1348,'a0669d04edca11e2abe65404a6a9ac98','zapdata','zd_server','server_name','数据库名称',104003022,104014001,45,0,'4'),(1349,'a0669d67edca11e2abe65404a6a9ac98','zapdata','zd_server','server_type_aid','数据库类型',104003009,104014001,10,0,'5'),(1350,'a0669dc9edca11e2abe65404a6a9ac98','zapdata','zd_server','jdbc_driver','驱动',104003022,104014001,450,0,'6'),(1351,'a0669e30edca11e2abe65404a6a9ac98','zapdata','zd_server','jdbc_dburl','路径',104003022,104014001,450,0,'7'),(1352,'a0669e92edca11e2abe65404a6a9ac98','zapdata','zd_server','jdbc_user','用户名',104003022,104014001,45,0,'8'),(1353,'a0669ef5edca11e2abe65404a6a9ac98','zapdata','zd_server','jdbc_password','密码',104003022,104014001,45,0,'9'),(1354,'a0669f57edca11e2abe65404a6a9ac98','zapdata','zd_tables','zid','',104003009,104014002,10,0,'1'),(1355,'a0669fc2edca11e2abe65404a6a9ac98','zapdata','zd_tables','uid','',104003003,104014001,32,0,'2'),(1356,'a066a021edca11e2abe65404a6a9ac98','zapdata','zd_tables','server_name','数据库名称',104003022,104014001,45,0,'3'),(1357,'a066a07fedca11e2abe65404a6a9ac98','zapdata','zd_tables','table_name','表名称',104003022,104014001,45,0,'4'),(1358,'a066a5c1edca11e2abe65404a6a9ac98','zapdata','zd_tables','table_remark','备注',104003022,104014001,450,0,'5'),(1359,'a066a62cedca11e2abe65404a6a9ac98','zapdata','zw_define','zid','',104003009,104014002,10,0,'1'),(1360,'a066a68bedca11e2abe65404a6a9ac98','zapdata','zw_define','uid','',104003003,104014001,32,0,'2'),(1361,'a066a6e9edca11e2abe65404a6a9ac98','zapdata','zw_define','define_dids','定义编号',104003022,104014001,30,0,'3'),(1362,'a066a74cedca11e2abe65404a6a9ac98','zapdata','zw_define','define_name','定义名称',104003022,104014001,450,0,'4'),(1363,'a066a7aeedca11e2abe65404a6a9ac98','zapdata','zw_define','define_note','描述',104003022,104014001,45,0,'5'),(1364,'a066a80dedca11e2abe65404a6a9ac98','zapdata','zw_define','level_rank','级别',104003009,104014001,10,0,'6'),(1365,'a066a86bedca11e2abe65404a6a9ac98','zapdata','zw_define','parent_did','父编号',104003022,104014001,45,0,'7'),(1366,'a066a8c9edca11e2abe65404a6a9ac98','zapdata','zw_field','zid','',104003009,104014002,10,0,'1'),(1367,'a066a928edca11e2abe65404a6a9ac98','zapdata','zw_field','uid','',104003003,104014001,32,0,'2'),(1368,'a066a986edca11e2abe65404a6a9ac98','zapdata','zw_field','view_code','视图编号',104003022,104014001,45,0,'3'),(1369,'a066a9e9edca11e2abe65404a6a9ac98','zapdata','zw_field','column_name','数据列名',104003022,104014001,45,0,'4'),(1370,'a066aa47edca11e2abe65404a6a9ac98','zapdata','zw_field','field_note','字段名称',104003022,104014001,45,0,'5'),(1371,'a066aaaaedca11e2abe65404a6a9ac98','zapdata','zw_field','sort_add','排序添加',104003022,104014001,45,0,'6'),(1372,'a066ab08edca11e2abe65404a6a9ac98','zapdata','zw_field','sort_edit','排序修改',104003022,104014001,45,0,'7'),(1373,'a066ab62edca11e2abe65404a6a9ac98','zapdata','zw_field','sort_chart','排序列表',104003022,104014001,45,0,'8'),(1374,'a066abc1edca11e2abe65404a6a9ac98','zapdata','zw_field','sort_book','排序展示',104003022,104014001,45,0,'9'),(1375,'a066ac23edca11e2abe65404a6a9ac98','zapdata','zw_field','sort_inquery','排序查询',104003022,104014001,45,0,'10'),(1376,'a066ac86edca11e2abe65404a6a9ac98','zapdata','zw_field','field_type_aid','字段类型',104003009,104014001,10,0,'11'),(1377,'a066acecedca11e2abe65404a6a9ac98','zapdata','zw_manager','zid','',104003009,104014002,10,0,'1'),(1378,'a066ad47edca11e2abe65404a6a9ac98','zapdata','zw_manager','uid','',104003003,104014001,32,0,'2'),(1379,'a066ada9edca11e2abe65404a6a9ac98','zapdata','zw_manager','manager_name','',104003022,104014001,45,0,'3'),(1380,'a066ae08edca11e2abe65404a6a9ac98','zapdata','zw_manager','manager_pass','',104003022,104014001,45,0,'4'),(1381,'a066ae62edca11e2abe65404a6a9ac98','zapdata','zw_menu','zid','',104003009,104014002,10,0,'1'),(1382,'a066aec0edca11e2abe65404a6a9ac98','zapdata','zw_menu','uid','',104003003,104014001,32,0,'2'),(1383,'a066af23edca11e2abe65404a6a9ac98','zapdata','zw_menu','menu_code','菜单编号',104003022,104014001,45,0,'3'),(1384,'a066af81edca11e2abe65404a6a9ac98','zapdata','zw_menu','menu_name','菜单名称',104003022,104014001,45,0,'4'),(1385,'a066afe0edca11e2abe65404a6a9ac98','zapdata','zw_menu','parent_code','父编号',104003022,104014001,45,0,'5'),(1386,'a066b046edca11e2abe65404a6a9ac98','zapdata','zw_menu','page_code','页面编号',104003022,104014001,45,0,'6'),(1387,'a066b0adedca11e2abe65404a6a9ac98','zapdata','zw_operate','zid','',104003009,104014002,10,0,'1'),(1388,'a066b107edca11e2abe65404a6a9ac98','zapdata','zw_operate','uid','',104003003,104014001,32,0,'2'),(1389,'a066b166edca11e2abe65404a6a9ac98','zapdata','zw_operate','operate_name','操作名称',104003022,104014001,45,0,'3'),(1390,'a066b1d9edca11e2abe65404a6a9ac98','zapdata','zw_operate','operate_type_aid','操作类型',104003009,104014001,10,0,'4'),(1391,'a066b23fedca11e2abe65404a6a9ac98','zapdata','zw_operate','page_code','页面编码',104003022,104014001,450,0,'5'),(1392,'a066b29eedca11e2abe65404a6a9ac98','zapdata','zw_operate','operate_link','操作链接',104003022,104014001,450,0,'6'),(1393,'a066b300edca11e2abe65404a6a9ac98','zapdata','zw_operate','flag_enable','是否可用',104003009,104014001,10,0,'7'),(1394,'a066b35fedca11e2abe65404a6a9ac98','zapdata','zw_page','zid','',104003009,104014002,10,0,'1'),(1395,'a066b3c5edca11e2abe65404a6a9ac98','zapdata','zw_page','uid','',104003003,104014001,32,0,'2'),(1396,'a066b42cedca11e2abe65404a6a9ac98','zapdata','zw_page','page_code','页面编号',104003022,104014001,45,0,'3'),(1397,'a066b48aedca11e2abe65404a6a9ac98','zapdata','zw_page','page_name','页面名称',104003022,104014001,450,0,'4'),(1398,'a066b4ededca11e2abe65404a6a9ac98','zapdata','zw_page','page_template','页面调用模板',104003022,104014001,45,0,'5'),(1399,'a066b550edca11e2abe65404a6a9ac98','zapdata','zw_page','view_code','视图编码',104003022,104014001,45,0,'6'),(1400,'a066b5aeedca11e2abe65404a6a9ac98','zapdata','zw_page','page_type_aid','页面类型',104003022,104014001,45,0,'7'),(1401,'a066b610edca11e2abe65404a6a9ac98','zapdata','zw_page','page_group','页面组',104003022,104014001,450,0,'8'),(1402,'a066b973edca11e2abe65404a6a9ac98','zapdata','zw_role','zid','',104003009,104014002,10,0,'1'),(1403,'a066b9d9edca11e2abe65404a6a9ac98','zapdata','zw_role','uid','',104003003,104014001,32,0,'2'),(1404,'a066ba38edca11e2abe65404a6a9ac98','zapdata','zw_role','role_code','角色编码',104003022,104014001,45,0,'3'),(1405,'a066ba96edca11e2abe65404a6a9ac98','zapdata','zw_role','role_name','角色名称',104003022,104014001,45,0,'4'),(1406,'a066baf5edca11e2abe65404a6a9ac98','zapdata','zw_role','parent_code','父编码',104003022,104014001,45,0,'5'),(1407,'a066bb57edca11e2abe65404a6a9ac98','zapdata','zw_source','zid','',104003009,104014002,10,0,'1'),(1408,'a066bbbaedca11e2abe65404a6a9ac98','zapdata','zw_source','uid','',104003003,104014001,32,0,'2'),(1409,'a066bc14edca11e2abe65404a6a9ac98','zapdata','zw_source','source_code','数据源编编码',104003022,104014001,45,0,'3'),(1410,'a066bc6eedca11e2abe65404a6a9ac98','zapdata','zw_source','source_name','数据源名称',104003022,104014001,45,0,'4'),(1411,'a066bcd5edca11e2abe65404a6a9ac98','zapdata','zw_view','zid','',104003009,104014002,10,0,'1'),(1412,'a066bd33edca11e2abe65404a6a9ac98','zapdata','zw_view','uid','',104003003,104014001,32,0,'2'),(1413,'a066bd8eedca11e2abe65404a6a9ac98','zapdata','zw_view','view_code','视图编码',104003022,104014001,45,0,'3'),(1414,'a066bdecedca11e2abe65404a6a9ac98','zapdata','zw_view','view_name','试图名称',104003022,104014001,45,0,'4'),(1415,'a066be4aedca11e2abe65404a6a9ac98','zapdata','zw_view','table_name','视图表格',104003022,104014001,45,0,'5'),(1544,'d2b2cd12ef9411e2abe65404a6a9ac98','zapdata','zw_field','source_code','数据源编号',104003022,104014001,45,0,'12'),(1545,'d2b2cd74ef9411e2abe65404a6a9ac98','zapdata','zw_field','source_param','数据源参数',104003022,104014001,45,0,'13'),(1546,'d2b2cddbef9411e2abe65404a6a9ac98','zapdata','zw_field','show_replace','展示替换',104003022,104014001,450,0,'14'),(1547,'d2b2d032ef9411e2abe65404a6a9ac98','zapdata','zw_field','default_value','默认值',104003022,104014001,450,0,'15'),(1548,'d2b2d099ef9411e2abe65404a6a9ac98','zapdata','zw_operate','operate_func','操作调用方法',104003022,104014001,450,0,'8'),(1549,'d2b2d0fbef9411e2abe65404a6a9ac98','zapdata','zw_page','flag_enable','可用标记',104003009,104014001,10,0,'9'),(1550,'d2b2d16aef9411e2abe65404a6a9ac98','zapdata','zw_source','source_from','数据源集合',104003022,104014001,45,0,'5'),(1551,'d2b2d1cdef9411e2abe65404a6a9ac98','zapdata','zw_source','field_text','字段名称',104003022,104014001,45,0,'6'),(1552,'d2b2d22fef9411e2abe65404a6a9ac98','zapdata','zw_source','field_value','字段值',104003022,104014001,45,0,'7'),(1553,'d2b2d296ef9411e2abe65404a6a9ac98','zapdata','zw_source','where_book','展示条件',104003022,104014001,45,0,'8'),(1554,'d2b2d2f4ef9411e2abe65404a6a9ac98','zapdata','zw_source','where_edit','修改条件',104003022,104014001,45,0,'9'),(1555,'d2b2d35bef9411e2abe65404a6a9ac98','zapdata','zw_view','project_aid','项目定义',104003009,104014001,10,0,'6'),(1556,'32e5c95df4ce11e2833f5404a6a9ac98','zapdata','zw_page','view_type_aid','',104003009,104014001,10,0,'8');
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
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_tables`
--

LOCK TABLES `zd_tables` WRITE;
/*!40000 ALTER TABLE `zd_tables` DISABLE KEYS */;
INSERT INTO `zd_tables` VALUES (156,'a05bd2b8edca11e2abe65404a6a9ac98','cardcenter','cc_agentuser','代理商表'),(157,'a05bd4a9edca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','卡信息表'),(158,'a05bd5d5edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','卡日志表'),(159,'a05bd6bbedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','创建任务表'),(160,'a05bd798edca11e2abe65404a6a9ac98','cardcenter','cc_define','卡中心定义表'),(161,'a05bd86eedca11e2abe65404a6a9ac98','cardcenter','cc_moneytype','卡金额类型表'),(162,'a05bd948edca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','购物卡发放表'),(163,'a05bda21edca11e2abe65404a6a9ac98','zapdata','zd_abstract',''),(164,'a05bdb0bedca11e2abe65404a6a9ac98','zapdata','zd_column',''),(165,'a05bdbdcedca11e2abe65404a6a9ac98','zapdata','zd_server',''),(166,'a05bdcb6edca11e2abe65404a6a9ac98','zapdata','zd_tables',''),(167,'a05bdd98edca11e2abe65404a6a9ac98','zapdata','zw_define',''),(168,'a05bde7eedca11e2abe65404a6a9ac98','zapdata','zw_field','字段表'),(169,'a05bdf57edca11e2abe65404a6a9ac98','zapdata','zw_manager',''),(170,'a05be03dedca11e2abe65404a6a9ac98','zapdata','zw_menu','菜单表'),(171,'a05be123edca11e2abe65404a6a9ac98','zapdata','zw_operate',''),(172,'a05be20dedca11e2abe65404a6a9ac98','zapdata','zw_page',''),(173,'a05be2ebedca11e2abe65404a6a9ac98','zapdata','zw_role',''),(174,'a05be4f0edca11e2abe65404a6a9ac98','zapdata','zw_source','数据源表'),(175,'a05be5ffedca11e2abe65404a6a9ac98','zapdata','zw_view','视图表');
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
  `source_code` varchar(45) DEFAULT '' COMMENT '数据源编号',
  `source_param` varchar(45) DEFAULT '' COMMENT '数据源参数',
  `show_replace` varchar(450) DEFAULT '' COMMENT '展示替换',
  `default_value` varchar(450) DEFAULT '' COMMENT '默认值',
  `regex_value` varchar(450) DEFAULT '' COMMENT '正则验证(+为非空)',
  `query_type_aid` int(11) DEFAULT '104009005' COMMENT '查询类型',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='字段表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_field`
--

LOCK TABLES `zw_field` WRITE;
/*!40000 ALTER TABLE `zw_field` DISABLE KEYS */;
INSERT INTO `zw_field` VALUES (59,'01a17605edcb11e2abe65404a6a9ac98','v_zw_view','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(60,'01a17840edcb11e2abe65404a6a9ac98','v_zw_view','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(61,'01a17905edcb11e2abe65404a6a9ac98','v_zw_view','view_code','视图编码','100300','100300','100300','100300','100300',104005009,'','','','','',104009005),(62,'01a179b6edcb11e2abe65404a6a9ac98','v_zw_view','view_name','试图名称','100400','100400','100400','100400','100400',104005009,'','','','','',104009005),(63,'01a17a5eedcb11e2abe65404a6a9ac98','v_zw_view','table_name','视图表格','100500','100500','100500','100500','100500',104005009,'','','','','',104009005),(66,'01a36be2edcb11e2abe65404a6a9ac98','v_zw_field','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(67,'01a36f0fedcb11e2abe65404a6a9ac98','v_zw_field','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(68,'01a3c689edcb11e2abe65404a6a9ac98','v_zw_field','view_code','视图编号','100300','100300','100300','100300','100300',104005009,'','','','','',104009005),(69,'01a3c89aedcb11e2abe65404a6a9ac98','v_zw_field','column_name','数据列名','100400','100400','100400','100400','100400',104005009,'','','','','',104009005),(70,'01a3c95bedcb11e2abe65404a6a9ac98','v_zw_field','field_note','字段名称','100500','100500','100500','100500','100500',104005009,'','','','','',104009005),(71,'01a3c9f7edcb11e2abe65404a6a9ac98','v_zw_field','sort_add','排序添加','100600','100600','100600','100600','100600',104005009,'','','','','',104009005),(72,'01a3ca8fedcb11e2abe65404a6a9ac98','v_zw_field','sort_edit','排序修改','100700','100700','100700','100700','100700',104005009,'','','','','',104009005),(73,'01a3cb2bedcb11e2abe65404a6a9ac98','v_zw_field','sort_chart','排序列表','100800','100800','100800','100800','100800',104005009,'','','','','',104009005),(74,'01a3cbcbedcb11e2abe65404a6a9ac98','v_zw_field','sort_book','排序展示','100900','100900','100900','100900','100900',104005009,'','','','','',104009005),(75,'01a3cd72edcb11e2abe65404a6a9ac98','v_zw_field','sort_inquery','排序查询','101000','101000','101000','101000','101000',104005009,'','','','','',104009005),(76,'01a3ce12edcb11e2abe65404a6a9ac98','v_zw_field','field_type_aid','字段类型','101100','101100','101100','101100','101100',104005009,'','','','','',104009005),(81,'01a4a3feedcb11e2abe65404a6a9ac98','v_cc_createtask','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(82,'01a4a5deedcb11e2abe65404a6a9ac98','v_cc_createtask','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(83,'01a4a6a3edcb11e2abe65404a6a9ac98','v_cc_createtask','task_code','任务编号','0','100300','100300','100300','100300',104005009,'','','','','',104009005),(84,'01a4a750edcb11e2abe65404a6a9ac98','v_cc_createtask','task_name','任务名称','100400','100400','100400','100400','100400',104005009,'','','','','+',104009005),(85,'01a4a7f8edcb11e2abe65404a6a9ac98','v_cc_createtask','task_remark','备注','100500','100500','100500','100500','100500',104005009,'','','','','',104009005),(86,'01a4ac61edcb11e2abe65404a6a9ac98','v_cc_createtask','card_money','卡金额','100600','100600','100600','100600','100600',104005009,'source_cc_moneytype','','','','^\\d{1,10}$',104009005),(87,'01a4ad5bedcb11e2abe65404a6a9ac98','v_cc_createtask','card_number','制卡数量','100700','100700','100700','100700','100700',104005009,'','','','','^\\d{1,10}$',104009005),(88,'01a4ae10edcb11e2abe65404a6a9ac98','v_cc_createtask','card_make','已生成数量','0','100800','100800','100800','100800',104005009,'','','','','',104009005),(89,'01a4aec0edcb11e2abe65404a6a9ac98','v_cc_createtask','create_user','创建人','0','100900','100900','100900','100900',104005009,'','','','','',104009005),(90,'01a4af58edcb11e2abe65404a6a9ac98','v_cc_createtask','create_time','创建时间','0','101000','101000','101000','101000',104005009,'','','','','',104009002),(91,'01a4aff4edcb11e2abe65404a6a9ac98','v_cc_createtask','serial_begin','开始流水号','0','101100','101100','101100','101100',104005009,'','','','','',104009005),(92,'01a4b094edcb11e2abe65404a6a9ac98','v_cc_createtask','serial_finish','结束流水号','0','101200','101200','101200','101200',104005009,'','','','','',104009005),(93,'a5ad0a96f4ce11e2833f5404a6a9ac98','v_zw_view','project_aid','项目定义','100600','100600','100600','100600','100600',104005009,'','','','','',104009005),(94,'a5b2adb4f4ce11e2833f5404a6a9ac98','v_zw_field','source_code','数据源编号','101200','101200','101200','101200','101200',104005009,'','','','','',104009005),(95,'a5b2af42f4ce11e2833f5404a6a9ac98','v_zw_field','source_param','数据源参数','101300','101300','101300','101300','101300',104005009,'','','','','',104009005),(96,'a5b2afdaf4ce11e2833f5404a6a9ac98','v_zw_field','show_replace','展示替换','101400','101400','101400','101400','101400',104005009,'','','','','',104009005),(97,'a5b2b055f4ce11e2833f5404a6a9ac98','v_zw_field','default_value','默认值','101500','101500','101500','101500','101500',104005009,'','','','','',104009005);
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
  `flag_enable` int(11) DEFAULT '1' COMMENT '是否可用',
  `operate_func` varchar(450) DEFAULT '' COMMENT '操作调用方法',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_operate`
--

LOCK TABLES `zw_operate` WRITE;
/*!40000 ALTER TABLE `zw_operate` DISABLE KEYS */;
INSERT INTO `zw_operate` VALUES (77,'889a8f7cf4d611e2833f5404a6a9ac98','添加',116015012,'page_chart_v_zw_view','page_add_v_zw_view',1,''),(78,'889d95c6f4d611e2833f5404a6a9ac98','修改',116015012,'page_chart_v_zw_view','page_chart_v_zw_view',1,''),(79,'889dc1e0f4d611e2833f5404a6a9ac98','删除',116015010,'page_chart_v_zw_view','zapjs.zw.func_delete(this)',1,'com.srnpr.zapweb.webfunc.FuncDelete'),(80,'889def3df4d611e2833f5404a6a9ac98','提交新增',116015010,'page_add_v_zw_view','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd'),(81,'889e1daaf4d611e2833f5404a6a9ac98','提交修改',116015010,'page_edit_v_zw_view','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit'),(82,'889e9e66f4d611e2833f5404a6a9ac98','添加',116015012,'page_chart_v_zw_field','page_add_v_zw_field',1,''),(83,'889ed821f4d611e2833f5404a6a9ac98','修改',116015012,'page_chart_v_zw_field','page_chart_v_zw_field',1,''),(84,'889f05bcf4d611e2833f5404a6a9ac98','删除',116015010,'page_chart_v_zw_field','zapjs.zw.func_delete(this)',1,'com.srnpr.zapweb.webfunc.FuncDelete'),(85,'889f4c09f4d611e2833f5404a6a9ac98','提交新增',116015010,'page_add_v_zw_field','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd'),(86,'889f8d9ff4d611e2833f5404a6a9ac98','提交修改',116015010,'page_edit_v_zw_field','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit'),(87,'88a01e40f4d611e2833f5404a6a9ac98','添加',116015012,'page_chart_v_cc_createtask','page_add_v_cc_createtask',1,''),(88,'88a0555df4d611e2833f5404a6a9ac98','修改',116015012,'page_chart_v_cc_createtask','page_chart_v_cc_createtask',1,''),(89,'88a09217f4d611e2833f5404a6a9ac98','删除',116015010,'page_chart_v_cc_createtask','zapjs.zw.func_delete(this)',1,'com.srnpr.zapweb.webfunc.FuncDelete'),(90,'88a0c80df4d611e2833f5404a6a9ac98','提交新增',116015010,'page_add_v_cc_createtask','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd'),(91,'88a0f3f5f4d611e2833f5404a6a9ac98','提交修改',116015010,'page_edit_v_cc_createtask','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit');
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
  `page_type_aid` int(11) DEFAULT '0' COMMENT '页面类型',
  `view_type_aid` int(11) DEFAULT '0',
  `page_group` varchar(450) DEFAULT '' COMMENT '页面组',
  `flag_enable` int(11) DEFAULT '1' COMMENT '可用标记',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `page_code_UNIQUE` (`page_code`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_page`
--

LOCK TABLES `zw_page` WRITE;
/*!40000 ALTER TABLE `zw_page` DISABLE KEYS */;
INSERT INTO `zw_page` VALUES (39,'a5b17039f4ce11e2833f5404a6a9ac98','page_chart_v_zw_view','系统视图-列表','chart','v_zw_view',116016003,116022003,'grouppage_v_zw_view',1),(40,'a5b2002ef4ce11e2833f5404a6a9ac98','page_add_v_zw_view','系统视图-新增','add','v_zw_view',116016001,116022001,'grouppage_v_zw_view',1),(41,'a5b22c3ff4ce11e2833f5404a6a9ac98','page_edit_v_zw_view','系统视图-修改','edit','v_zw_view',116016005,116022005,'grouppage_v_zw_view',1),(42,'a5b2dd50f4ce11e2833f5404a6a9ac98','page_chart_v_zw_field','字段视图-列表','chart','v_zw_field',116016003,116022003,'grouppage_v_zw_field',1),(43,'a5b30883f4ce11e2833f5404a6a9ac98','page_add_v_zw_field','字段视图-新增','add','v_zw_field',116016001,116022001,'grouppage_v_zw_field',1),(44,'a5b32977f4ce11e2833f5404a6a9ac98','page_edit_v_zw_field','字段视图-修改','edit','v_zw_field',116016005,116022005,'grouppage_v_zw_field',1),(45,'a5b39a22f4ce11e2833f5404a6a9ac98','page_chart_v_cc_createtask','购物卡-列表','chart','v_cc_createtask',116016003,116022003,'grouppage_v_cc_createtask',1),(46,'a5b3b859f4ce11e2833f5404a6a9ac98','page_add_v_cc_createtask','购物卡-新增','add','v_cc_createtask',116016001,116022001,'grouppage_v_cc_createtask',1),(47,'a5b3deacf4ce11e2833f5404a6a9ac98','page_edit_v_cc_createtask','购物卡-修改','edit','v_cc_createtask',116016005,116022005,'grouppage_v_cc_createtask',1);
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
  `source_from` varchar(45) DEFAULT '' COMMENT '数据源集合',
  `field_text` varchar(45) DEFAULT '' COMMENT '字段名称',
  `field_value` varchar(45) DEFAULT '' COMMENT '字段值',
  `where_book` varchar(45) DEFAULT '' COMMENT '展示条件',
  `where_edit` varchar(45) DEFAULT '' COMMENT '修改条件',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='数据源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_source`
--

LOCK TABLES `zw_source` WRITE;
/*!40000 ALTER TABLE `zw_source` DISABLE KEYS */;
INSERT INTO `zw_source` VALUES (1,'1','source_cc_moneytype','购物卡类型','cc_moneytype','money_value','money_value','','');
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
  `project_aid` int(11) DEFAULT '101002677' COMMENT '项目定义',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='视图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_view`
--

LOCK TABLES `zw_view` WRITE;
/*!40000 ALTER TABLE `zw_view` DISABLE KEYS */;
INSERT INTO `zw_view` VALUES (1,'','v_zw_view','系统视图','zw_view',101002677),(2,'','v_zw_field','字段视图','zw_field',101002677),(3,'','v_cc_createtask','购物卡','cc_createtask',101002081);
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
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end)  as sort_add
,(case zdc.column_name when 'zid' then 0 else 100000+100*zdc.column_sort end) as sort_edit
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_chart
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_book
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_inquery
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
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_chart_',zwv.view_code) as page_code
,concat(zwv.view_name,'-列表') as page_name
,'chart' as page_template
,zwv.view_code as view_code
,116016003 as page_type_aid
,116022003 as view_type_aid
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
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_add_',zwv.view_code) as page_code
,concat(zwv.view_name,'-新增') as page_name
,'add' as page_template
,zwv.view_code as view_code
,116016001 as page_type_aid
,116022001 as view_type_aid
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
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_edit_',zwv.view_code) as page_code
,concat(zwv.view_name,'-修改') as page_name
,'edit' as page_template
,zwv.view_code as view_code
,116016005 as page_type_aid
,116022005 as view_type_aid
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'添加' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016001 and view_code=zwp.view_code) as operate_link
,'1' as flag_enable
,'' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'修改' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016003 and view_code=zwp.view_code) as operate_link
,'1' as flag_enable
,'' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'删除' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_delete(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncDelete' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'提交新增' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_add(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncAdd' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'提交修改' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_edit(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncEdit' as operate_func
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

-- Dump completed on 2013-07-26 18:39:39
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
-- Table structure for table `cc_agentuser`
--

DROP TABLE IF EXISTS `cc_agentuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_agentuser` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `agent_code` varchar(450) DEFAULT '' COMMENT '代理商编号',
  `agent_name` varchar(450) DEFAULT '' COMMENT '代理商名称',
  `flag_used` int(11) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_agentuser`
--

LOCK TABLES `cc_agentuser` WRITE;
/*!40000 ALTER TABLE `cc_agentuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_agentuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_cardinfo`
--

DROP TABLE IF EXISTS `cc_cardinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_cardinfo` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) NOT NULL DEFAULT '',
  `card_serial` char(12) NOT NULL DEFAULT '' COMMENT '购物卡流水号',
  `card_code` char(16) NOT NULL DEFAULT '' COMMENT '购物卡卡号',
  `card_pass` varchar(200) NOT NULL DEFAULT '' COMMENT '购物卡密码',
  `card_money` decimal(18,2) DEFAULT '0.00' COMMENT '购物卡金额',
  `used_money` decimal(18,2) DEFAULT '0.00' COMMENT '已使用金额',
  `inital_money` decimal(18,2) DEFAULT '0.00' COMMENT '初始金额',
  `flag_active` int(11) DEFAULT '0' COMMENT '激活标识',
  `begin_time` char(18) DEFAULT '' COMMENT '开始使用时间',
  `finish_time` char(18) DEFAULT '' COMMENT '截止使用时间',
  `create_time` char(18) DEFAULT '' COMMENT '创建时间',
  `active_time` char(18) DEFAULT '' COMMENT '激活时间',
  `flag_send` int(11) DEFAULT '0' COMMENT '发放标记',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `card_code_UNIQUE` (`card_code`),
  UNIQUE KEY `card_serial_UNIQUE` (`card_serial`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='卡信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_cardinfo`
--

LOCK TABLES `cc_cardinfo` WRITE;
/*!40000 ALTER TABLE `cc_cardinfo` DISABLE KEYS */;
INSERT INTO `cc_cardinfo` VALUES (27,'df490ec172dc47638f58dd0eed59133b','1','aa','',0.00,0.00,0.00,0,'','','','',0),(32,'733044fee7844e50a32d8e592e5237ed','2','bb','',0.00,0.00,0.00,0,'','','','',0);
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
  `card_money` decimal(18,2) DEFAULT '0.00' COMMENT '当前金额',
  `change_type_did` varchar(45) DEFAULT '40810312' COMMENT '变更类型',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_cardlog`
--

LOCK TABLES `cc_cardlog` WRITE;
/*!40000 ALTER TABLE `cc_cardlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_cardlog` ENABLE KEYS */;
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
  `card_money` decimal(18,2) DEFAULT '0.00' COMMENT '卡金额',
  `card_number` int(11) DEFAULT '0' COMMENT '制卡数量',
  `card_make` int(11) DEFAULT '0' COMMENT '已生成数量',
  `create_user` varchar(45) DEFAULT '' COMMENT '创建人',
  `create_time` char(19) DEFAULT '' COMMENT '创建时间',
  `serial_begin` varchar(12) DEFAULT '' COMMENT '开始流水号',
  `serial_finish` varchar(12) DEFAULT '' COMMENT '结束流水号',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='创建任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_createtask`
--

LOCK TABLES `cc_createtask` WRITE;
/*!40000 ALTER TABLE `cc_createtask` DISABLE KEYS */;
INSERT INTO `cc_createtask` VALUES (1,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(6,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(7,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(8,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(9,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(10,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(11,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(12,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(13,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(14,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(15,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(16,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(17,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(18,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(19,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(20,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(21,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(22,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(23,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(24,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(25,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(26,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(27,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(28,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(29,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(30,'1ea73f6e973a4166a37640fe936c625b','','','',100.00,100,0,'','','',''),(31,'055a9bb74ba244b685a0130cd5ac0544','','11','22',33.00,44,0,'','','',''),(32,'c919bf5689d34c3b8948d99c7b03a5f5','','11','22',33.00,44,0,'','','',''),(33,'158c9845066f48c89507d1218a70fcb2','','','',22.00,22,0,'','','','');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='卡中心定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_define`
--

LOCK TABLES `cc_define` WRITE;
/*!40000 ALTER TABLE `cc_define` DISABLE KEYS */;
INSERT INTO `cc_define` VALUES (1,'','4081','cardcenter','',0,''),(2,'','40810312','cardlog','卡变更原因',0,''),(3,'','408103120001','create card','创建卡',0,''),(4,'','408103120002','send card','发放卡',0,''),(5,'','408103120003','active card','激活卡',0,''),(6,'','408103120004','use card','使用卡',0,''),(7,'','408103120005','lock card','锁定卡',0,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='卡金额类型表';
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
-- Table structure for table `cc_sendtask`
--

DROP TABLE IF EXISTS `cc_sendtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_sendtask` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `serial_begin` varchar(45) DEFAULT '' COMMENT '开始流水号',
  `serial_finish` varchar(45) DEFAULT '' COMMENT '结束流水号',
  `agent_code` varchar(45) DEFAULT '' COMMENT '代理商编号',
  `create_manager` varchar(45) DEFAULT '' COMMENT '创建人',
  `create_time` char(18) DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物卡发放表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_sendtask`
--

LOCK TABLES `cc_sendtask` WRITE;
/*!40000 ALTER TABLE `cc_sendtask` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_sendtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cardcenter'
--
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
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end)  as sort_add
,(case zdc.column_name when 'zid' then 0 else 100000+100*zdc.column_sort end) as sort_edit
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_chart
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_book
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_inquery
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
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_chart_',zwv.view_code) as page_code
,concat(zwv.view_name,'-列表') as page_name
,'chart' as page_template
,zwv.view_code as view_code
,116016003 as page_type_aid
,116022003 as view_type_aid
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
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_add_',zwv.view_code) as page_code
,concat(zwv.view_name,'-新增') as page_name
,'add' as page_template
,zwv.view_code as view_code
,116016001 as page_type_aid
,116022001 as view_type_aid
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
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_edit_',zwv.view_code) as page_code
,concat(zwv.view_name,'-修改') as page_name
,'edit' as page_template
,zwv.view_code as view_code
,116016005 as page_type_aid
,116022005 as view_type_aid
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'添加' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016001 and view_code=zwp.view_code) as operate_link
,'1' as flag_enable
,'' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'修改' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016003 and view_code=zwp.view_code) as operate_link
,'1' as flag_enable
,'' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'删除' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_delete(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncDelete' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'提交新增' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_add(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncAdd' as operate_func
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
`flag_enable`,
`operate_func`)
select
replace(uuid(),'-','') as uid
,'提交修改' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_edit(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncEdit' as operate_func
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

-- Dump completed on 2013-07-26 18:39:40
