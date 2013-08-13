CREATE DATABASE  IF NOT EXISTS `zapdata` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `zapdata`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 192.168.1.221    Database: zapdata
-- ------------------------------------------------------
-- Server version	5.1.69-log

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
-- Table structure for table `za_define`
--

DROP TABLE IF EXISTS `za_define`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `za_define` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `define_dids` varchar(30) DEFAULT '' COMMENT '定义编号',
  `define_name` varchar(450) DEFAULT '' COMMENT '定义名称',
  `define_note` varchar(45) DEFAULT '' COMMENT '描述',
  `level_rank` int(11) DEFAULT '0' COMMENT '级别',
  `parent_did` varchar(45) DEFAULT '' COMMENT '父编号',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `za_define`
--

LOCK TABLES `za_define` WRITE;
/*!40000 ALTER TABLE `za_define` DISABLE KEYS */;
INSERT INTO `za_define` VALUES (14,'','4699','','定义',1,''),(15,'','46990313','code_menu','菜单编号',2,'');
/*!40000 ALTER TABLE `za_define` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `za_menu`
--

DROP TABLE IF EXISTS `za_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `za_menu` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `menu_code` varchar(45) DEFAULT '' COMMENT '菜单编号',
  `menu_name` varchar(45) DEFAULT '' COMMENT '菜单名称',
  `parent_menu` varchar(45) DEFAULT '' COMMENT '父菜单',
  `menu_link` varchar(450) DEFAULT '' COMMENT '链接地址',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `menu_code_UNIQUE` (`menu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `za_menu`
--

LOCK TABLES `za_menu` WRITE;
/*!40000 ALTER TABLE `za_menu` DISABLE KEYS */;
INSERT INTO `za_menu` VALUES (1,'','46990313','超级菜单','4699',''),(2,'','469903130001','后台菜单','46990313',''),(3,'','4699031300010001','数据维护','469903130001',''),(4,'','46990313000100010001','结构定义','4699031300010001',''),(5,'','469903130001000100010001','视图维护','46990313000100010001','page/page_chart_v_zw_view'),(6,'','469903130001000100010002','字段维护','46990313000100010001','page/page_chart_v_zw_field'),(7,'','46990313000100020002','菜单二号','4699031300010002',''),(8,'','469903130001000200020001','菜单小二','46990313000100010002',''),(9,'','4699031300010002','测试菜单','469903130001',''),(10,'','46990313000100020001','菜单一号','4699031300010002',''),(11,'','469903130001000200010001','菜单小一','46990313000100020001','');
/*!40000 ALTER TABLE `za_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_abstract`
--

LOCK TABLES `zd_abstract` WRITE;
/*!40000 ALTER TABLE `zd_abstract` DISABLE KEYS */;
INSERT INTO `zd_abstract` VALUES (1,'',104,'db','数据库定义',100),(2,'',104020,'type','数据库类型',104),(3,'',104020013,'mysql','',104020),(4,'',104014,'notnull','非空',104),(5,'',104014001,'yes','非空',104014),(6,'',104014002,'no','允许空',104014),(7,'',104003,'column_type','数据列类型',104),(8,'',104003022,'varchar','',104003),(9,'',104003003,'char','',104003),(10,'',104003009,'int','',104003),(11,'',104003004,'decimal','',104003),(12,'',104003012,'longtext','',104003),(13,'',101,'all','系统定义',100),(14,'',101001,'flag','逻辑标识',101),(15,'',101001001,'yes','是',101001),(16,'',101001002,'no','否',101001),(17,'',104005,'field_type','字段类型',104),(18,'',104005008,'hidden','隐藏域',104005),(19,'',104005019,'select','下拉框',104005),(20,'',104005003,'component','组件',104005),(21,'',104005103,'checkbox','复选框',104005),(22,'',104005009,'input','输入框',104005),(24,'',119,'system','系统定义',100),(25,'',119678,'zapadmin','zapadmin',100),(35,'',116,'page','页面',100),(36,'',116016,'pagetype','页面类型',116),(37,'',116016001,'add','添加页面',116016),(38,'',116016003,'chart','列表页面',116016),(39,'',116016005,'edit','修改页面',116016),(40,'',116015,'operate','页面操作类型',116),(41,'',116015012,'link','新窗口链接',116015),(42,'',116015010,'js','脚本',116015),(43,'',116022,'view','视图',116),(44,'',116022001,'add','添加',116022),(45,'',116022005,'edit','修改',116022),(46,'',116022002,'book','展示',116022),(47,'',116022009,'inquery','查询',116022),(48,'',116022003,'chart','列表',116022),(49,'',101002,'project define','系统定义',101),(50,'',101002677,'zapadmin','超级后台管理',101002),(51,'',101002081,'cardcenter','卡中心系统',101002),(52,'',100,'abstract','原始定义',100),(53,'',1,'yes','是',100001),(54,'',0,'no','否',100001),(55,'',104005005,'editor','编辑框',104005),(58,'',104009,'inquire type','查询类型',104),(59,'',104009005,'equal','等于',104009),(60,'',104009002,'between','范围',104009),(61,'',104009012,'like','匹配',104009),(62,'',104009009,'in','在某某之中',104009),(63,'',116001,'areaoperate','按钮区域',116),(64,'',116001016,'operatearea','操作区域',116001),(65,'',116001009,'inquirearea','查询区域',116001),(66,'',116001003,'chartarea','数据区域',116001),(67,'',116001020,'toolarea','工具区域',116001),(69,'',104005020,'textarea','长文本框',104005),(70,'',104005004,'date','日期控件',104005),(71,'',116016109,'index','独立无授权页面',116016),(72,'',116022014,'no','无',116022),(73,'',101002100,'top','顶级系统',101002),(74,'',116018,'result','操作结果',116),(75,'',116018001,'a','默认操作',116018),(76,'',116018010,'js','执行脚本',116018),(77,'',116016116,'page','独立页面',116016),(78,'',100001,'able','是否',100),(79,'',104019,'sign','默认标记',104),(80,'',104005001,'a','内部处理',104005),(81,'',126,'zapconst','系统定义',100),(82,'',126022,'web.zap','网站项目',126),(83,'',126022006,'field','页面字段',126022),(84,'',126022016,'pagination','分页专用',126022),(85,'',126022001,'after','附加标记',126022),(86,'',126022003,'component','组件',126022),(87,'',116015008,'href','本窗口跳转',116015),(88,'',104009001,'a link','链接参数(不显示)',104009);
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
) ENGINE=InnoDB AUTO_INCREMENT=2665 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_column`
--

LOCK TABLES `zd_column` WRITE;
/*!40000 ALTER TABLE `zd_column` DISABLE KEYS */;
INSERT INTO `zd_column` VALUES (2379,'11e2113400a511e3bb73000c298b20fc','cardcenter','cc_activetask','zid','',104003009,104014002,10,0,'1'),(2380,'11e2144000a511e3bb73000c298b20fc','cardcenter','cc_activetask','uid','',104003003,104014001,32,0,'2'),(2381,'11e2150800a511e3bb73000c298b20fc','cardcenter','cc_activetask','task_code','任务编号',104003022,104014001,45,0,'3'),(2382,'11e2159400a511e3bb73000c298b20fc','cardcenter','cc_activetask','serial_begin','开始流水号',104003022,104014001,45,0,'4'),(2383,'11e2162000a511e3bb73000c298b20fc','cardcenter','cc_activetask','serial_finish','结束流水号',104003022,104014001,45,0,'5'),(2384,'11e216a200a511e3bb73000c298b20fc','cardcenter','cc_activetask','agent_code','代理商编号',104003022,104014001,45,0,'6'),(2385,'11e2171a00a511e3bb73000c298b20fc','cardcenter','cc_activetask','create_manager','创建人',104003022,104014001,45,0,'7'),(2386,'11e2179c00a511e3bb73000c298b20fc','cardcenter','cc_activetask','create_time','创建时间',104003003,104014001,19,0,'8'),(2387,'11e2181e00a511e3bb73000c298b20fc','cardcenter','cc_activetask','begin_time','开始使用时间',104003003,104014001,19,0,'9'),(2388,'11e218a000a511e3bb73000c298b20fc','cardcenter','cc_activetask','finish_time','结束使用时间',104003003,104014001,19,0,'10'),(2389,'11e2191800a511e3bb73000c298b20fc','cardcenter','cc_activetask','flag_finish','完成状态',104003009,104014001,10,0,'11'),(2390,'11e2199a00a511e3bb73000c298b20fc','cardcenter','cc_activetask','task_message','执行结果',104003022,104014001,450,0,'12'),(2391,'11e21a1c00a511e3bb73000c298b20fc','cardcenter','cc_agentuser','zid','',104003009,104014002,10,0,'1'),(2392,'11e21a9400a511e3bb73000c298b20fc','cardcenter','cc_agentuser','uid','',104003003,104014001,32,0,'2'),(2393,'11e21cf600a511e3bb73000c298b20fc','cardcenter','cc_agentuser','agent_code','代理商编号',104003022,104014001,450,0,'3'),(2394,'11e21d7800a511e3bb73000c298b20fc','cardcenter','cc_agentuser','agent_name','代理商名称',104003022,104014001,450,0,'4'),(2395,'11e21e0400a511e3bb73000c298b20fc','cardcenter','cc_agentuser','flag_used','是否可用',104003009,104014001,10,0,'5'),(2396,'11e21e8600a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','zid','',104003009,104014002,10,0,'1'),(2397,'11e21f0800a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','uid','',104003003,104014002,32,0,'2'),(2398,'11e21f8000a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','card_serial','购物卡流水号',104003003,104014002,12,0,'3'),(2399,'11e2200200a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','card_code','购物卡卡号',104003003,104014002,16,0,'4'),(2400,'11e2207a00a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','card_pass','购物卡密码',104003022,104014002,200,0,'5'),(2401,'11e220fc00a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','card_money','购物卡金额',104003004,104014001,18,2,'6'),(2402,'11e2217400a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','used_money','已使用金额',104003004,104014001,18,2,'7'),(2403,'11e221f600a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','inital_money','初始金额',104003004,104014001,18,2,'8'),(2404,'11e2226e00a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','flag_active','激活标识',104003009,104014001,10,0,'9'),(2405,'11e222f000a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','begin_time','开始使用时间',104003003,104014001,19,0,'10'),(2406,'11e2236800a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','finish_time','截止使用时间',104003003,104014001,19,0,'11'),(2407,'11e223ea00a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','create_time','创建时间',104003003,104014001,19,0,'12'),(2408,'11e2246200a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','active_time','激活时间',104003003,104014001,19,0,'13'),(2409,'11e225d400a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','send_time','发放时间',104003003,104014001,19,0,'14'),(2410,'11e2266000a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','flag_send','发放标记',104003009,104014001,10,0,'15'),(2411,'11e226ec00a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','cc_taskid','创建任务ID',104003009,104014001,10,0,'16'),(2412,'11e2276e00a511e3bb73000c298b20fc','cardcenter','cc_cardinfo','agent_code','代理商',104003022,104014001,45,0,'17'),(2413,'11e227f000a511e3bb73000c298b20fc','cardcenter','cc_cardlock','zid','自增ID',104003009,104014002,10,0,'1'),(2414,'11e2286800a511e3bb73000c298b20fc','cardcenter','cc_cardlock','uid','UUID',104003022,104014002,32,0,'2'),(2415,'11e228e000a511e3bb73000c298b20fc','cardcenter','cc_cardlock','keycode','锁号',104003022,104014002,50,0,'3'),(2416,'11e2296200a511e3bb73000c298b20fc','cardcenter','cc_cardlock','keyid','关键字',104003009,104014001,10,0,'4'),(2417,'11e22caa00a511e3bb73000c298b20fc','cardcenter','cc_cardlock','create_time','创建时间',104003003,104014001,19,0,'5'),(2418,'11e22d2c00a511e3bb73000c298b20fc','cardcenter','cc_cardlock','flag_active','激活标识',104003009,104014001,10,0,'6'),(2419,'11e22dae00a511e3bb73000c298b20fc','cardcenter','cc_cardlock','begin_time','开始使用时间',104003003,104014001,19,0,'7'),(2420,'11e22e3000a511e3bb73000c298b20fc','cardcenter','cc_cardlock','finish_time','截止使用时间',104003003,104014001,19,0,'8'),(2421,'11e22ea800a511e3bb73000c298b20fc','cardcenter','cc_cardlock','card_now_use_money','使用金额',104003004,104014001,18,2,'9'),(2422,'11e22f2a00a511e3bb73000c298b20fc','cardcenter','cc_cardlock','card_pass','密码',104003022,104014001,200,0,'10'),(2423,'11e22fac00a511e3bb73000c298b20fc','cardcenter','cc_cardlog','zid','',104003009,104014002,10,0,'1'),(2424,'11e2302400a511e3bb73000c298b20fc','cardcenter','cc_cardlog','uid','',104003003,104014001,32,0,'2'),(2425,'11e2309c00a511e3bb73000c298b20fc','cardcenter','cc_cardlog','card_code','卡号',104003022,104014001,45,0,'3'),(2426,'11e2311e00a511e3bb73000c298b20fc','cardcenter','cc_cardlog','change_money','变化金额',104003004,104014001,18,2,'4'),(2427,'11e2319600a511e3bb73000c298b20fc','cardcenter','cc_cardlog','change_time','变更时间',104003003,104014001,19,0,'5'),(2428,'11e2321800a511e3bb73000c298b20fc','cardcenter','cc_cardlog','change_people','变更人',104003022,104014001,45,0,'6'),(2429,'11e2329000a511e3bb73000c298b20fc','cardcenter','cc_cardlog','change_source','变更原因',104003022,104014001,45,0,'7'),(2430,'11e2330800a511e3bb73000c298b20fc','cardcenter','cc_cardlog','change_code','相关单号',104003022,104014001,45,0,'8'),(2431,'11e2338a00a511e3bb73000c298b20fc','cardcenter','cc_cardlog','change_remark','变更备注',104003022,104014001,450,0,'9'),(2432,'11e2340c00a511e3bb73000c298b20fc','cardcenter','cc_cardlog','card_money','当前金额',104003004,104014001,18,2,'10'),(2433,'11e2348400a511e3bb73000c298b20fc','cardcenter','cc_cardlog','change_type_did','变更类型',104003022,104014001,45,0,'11'),(2434,'11e2350600a511e3bb73000c298b20fc','cardcenter','cc_createtask','zid','',104003009,104014002,10,0,'1'),(2435,'11e2357e00a511e3bb73000c298b20fc','cardcenter','cc_createtask','uid','',104003003,104014001,32,0,'2'),(2436,'11e235f600a511e3bb73000c298b20fc','cardcenter','cc_createtask','task_code','任务编号',104003022,104014001,45,0,'3'),(2437,'11e2367800a511e3bb73000c298b20fc','cardcenter','cc_createtask','task_name','任务名称',104003022,104014001,450,0,'4'),(2438,'11e236fa00a511e3bb73000c298b20fc','cardcenter','cc_createtask','card_money','卡金额',104003004,104014001,18,2,'5'),(2439,'11e2377200a511e3bb73000c298b20fc','cardcenter','cc_createtask','card_number','制卡数量',104003009,104014001,10,0,'6'),(2440,'11e237f400a511e3bb73000c298b20fc','cardcenter','cc_createtask','card_make','已生成数量',104003009,104014001,10,0,'7'),(2441,'11e2386c00a511e3bb73000c298b20fc','cardcenter','cc_createtask','create_user','创建人',104003022,104014001,45,0,'8'),(2442,'11e238ee00a511e3bb73000c298b20fc','cardcenter','cc_createtask','create_time','创建时间',104003003,104014001,19,0,'9'),(2443,'11e2397000a511e3bb73000c298b20fc','cardcenter','cc_createtask','serial_begin','开始流水号',104003022,104014001,12,0,'10'),(2444,'11e239e800a511e3bb73000c298b20fc','cardcenter','cc_createtask','serial_finish','结束流水号',104003022,104014001,12,0,'11'),(2445,'11e23e8e00a511e3bb73000c298b20fc','cardcenter','cc_createtask','task_remark','备注',104003022,104014001,4500,0,'12'),(2446,'11e23f1000a511e3bb73000c298b20fc','cardcenter','cc_define','zid','',104003009,104014002,10,0,'1'),(2447,'11e23f8800a511e3bb73000c298b20fc','cardcenter','cc_define','uid','',104003003,104014001,32,0,'2'),(2448,'11e2400a00a511e3bb73000c298b20fc','cardcenter','cc_define','define_dids','定义编号',104003022,104014001,30,0,'3'),(2449,'11e2408c00a511e3bb73000c298b20fc','cardcenter','cc_define','define_name','定义名称',104003022,104014001,450,0,'4'),(2450,'11e2410400a511e3bb73000c298b20fc','cardcenter','cc_define','define_note','描述',104003022,104014001,45,0,'5'),(2451,'11e2417c00a511e3bb73000c298b20fc','cardcenter','cc_define','level_rank','级别',104003009,104014001,10,0,'6'),(2452,'11e241fe00a511e3bb73000c298b20fc','cardcenter','cc_define','parent_did','父编号',104003022,104014001,45,0,'7'),(2453,'11e2427600a511e3bb73000c298b20fc','cardcenter','cc_log','zid','',104003009,104014002,10,0,'1'),(2454,'11e242ee00a511e3bb73000c298b20fc','cardcenter','cc_log','uid','',104003022,104014001,32,0,'2'),(2455,'11e2437000a511e3bb73000c298b20fc','cardcenter','cc_log','log_type','1 查询-2 激活-3 使用-4 发放',104003009,104014001,10,0,'3'),(2456,'11e243f200a511e3bb73000c298b20fc','cardcenter','cc_log','request_url','请求Url',104003022,104014001,500,0,'4'),(2457,'11e2446a00a511e3bb73000c298b20fc','cardcenter','cc_log','request_ip','请求Ip',104003022,104014001,50,0,'5'),(2458,'11e244e200a511e3bb73000c298b20fc','cardcenter','cc_log','log_content','请求或相应内容',104003012,104014001,2147483647,0,'6'),(2459,'11e245f000a511e3bb73000c298b20fc','cardcenter','cc_log','create_time','创建时间',104003022,104014001,19,0,'7'),(2460,'11e2467c00a511e3bb73000c298b20fc','cardcenter','cc_log','inorout','0 输入 1 输出',104003009,104014001,10,0,'8'),(2461,'11e246f400a511e3bb73000c298b20fc','cardcenter','cc_menuinfo','zid','',104003009,104014002,10,0,'1'),(2462,'11e2477600a511e3bb73000c298b20fc','cardcenter','cc_menuinfo','uid','',104003003,104014001,32,0,'2'),(2463,'11e247ee00a511e3bb73000c298b20fc','cardcenter','cc_menuinfo','menu_code','菜单编号',104003022,104014001,40,0,'3'),(2464,'11e2486600a511e3bb73000c298b20fc','cardcenter','cc_menuinfo','menu_name','菜单名称',104003022,104014001,450,0,'4'),(2465,'11e248e800a511e3bb73000c298b20fc','cardcenter','cc_menuinfo','menu_page','对应页面',104003022,104014001,450,0,'5'),(2466,'11e2496000a511e3bb73000c298b20fc','cardcenter','cc_moneytype','zid','',104003009,104014002,10,0,'1'),(2467,'11e249e200a511e3bb73000c298b20fc','cardcenter','cc_moneytype','uid','',104003003,104014001,32,0,'2'),(2468,'11e24a5a00a511e3bb73000c298b20fc','cardcenter','cc_moneytype','moeney_type','金额类型',104003009,104014001,10,0,'3'),(2469,'11e24ad200a511e3bb73000c298b20fc','cardcenter','cc_moneytype','money_value','金额值',104003009,104014001,10,0,'4'),(2470,'11e24b5400a511e3bb73000c298b20fc','cardcenter','cc_reportforday','zid','',104003009,104014002,10,0,'1'),(2471,'11e24c8a00a511e3bb73000c298b20fc','cardcenter','cc_reportforday','uid','',104003022,104014001,32,0,'2'),(2472,'11e24d1600a511e3bb73000c298b20fc','cardcenter','cc_reportforday','report_date','报表日期',104003022,104014001,10,0,'3'),(2473,'11e24d9800a511e3bb73000c298b20fc','cardcenter','cc_reportforday','create_money','创建总钱数',104003004,104014001,18,2,'4'),(2474,'11e24e1a00a511e3bb73000c298b20fc','cardcenter','cc_reportforday','create_count','创建数量',104003009,104014001,10,0,'5'),(2475,'11e24e9c00a511e3bb73000c298b20fc','cardcenter','cc_reportforday','send_money','发放总钱数',104003004,104014001,18,2,'6'),(2476,'11e24f1400a511e3bb73000c298b20fc','cardcenter','cc_reportforday','send_count','发放总数量',104003009,104014001,10,0,'7'),(2477,'11e24f9600a511e3bb73000c298b20fc','cardcenter','cc_reportforday','active_money','激活总钱数',104003004,104014001,18,2,'8'),(2478,'11e2501800a511e3bb73000c298b20fc','cardcenter','cc_reportforday','active_count','激活总数量',104003009,104014001,10,0,'9'),(2479,'11e2564e00a511e3bb73000c298b20fc','cardcenter','cc_reportforday','consume_money','消费总钱数',104003004,104014001,18,2,'10'),(2480,'11e256d000a511e3bb73000c298b20fc','cardcenter','cc_reportforday','consume_count','消费总数量',104003009,104014001,10,0,'11'),(2481,'11e2575200a511e3bb73000c298b20fc','cardcenter','cc_reportforday','create_time','创建时间',104003022,104014001,19,0,'12'),(2482,'11e257d400a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','zid','',104003009,104014002,10,0,'1'),(2483,'11e2584c00a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','uid','',104003022,104014001,32,0,'2'),(2484,'11e258ce00a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','report_date','报表日期',104003022,104014001,10,0,'3'),(2485,'11e2594600a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','create_money','创建总钱数',104003004,104014001,18,2,'4'),(2486,'11e259c800a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','create_count','创建数量',104003009,104014001,10,0,'5'),(2487,'11e25a4a00a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','send_money','发放总钱数',104003004,104014001,18,2,'6'),(2488,'11e25ac200a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','send_count','发放总数量',104003009,104014001,10,0,'7'),(2489,'11e25b3a00a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','active_money','激活总钱数',104003004,104014001,18,2,'8'),(2490,'11e25bbc00a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','active_count','激活总数量',104003009,104014001,10,0,'9'),(2491,'11e25c3400a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','consume_money','消费总钱数',104003004,104014001,18,2,'10'),(2492,'11e25cb600a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','consume_count','消费总数量',104003009,104014001,10,0,'11'),(2493,'11e25d2e00a511e3bb73000c298b20fc','cardcenter','cc_reportformonth','create_time','创建时间',104003022,104014001,19,0,'12'),(2494,'11e2615c00a511e3bb73000c298b20fc','cardcenter','cc_roleinfo','zid','',104003009,104014002,10,0,'1'),(2495,'11e2622e00a511e3bb73000c298b20fc','cardcenter','cc_roleinfo','uid','',104003003,104014001,32,0,'2'),(2496,'11e262b000a511e3bb73000c298b20fc','cardcenter','cc_roleinfo','role_code','',104003022,104014001,45,0,'3'),(2497,'11e2632800a511e3bb73000c298b20fc','cardcenter','cc_roleinfo','role_name','',104003022,104014001,45,0,'4'),(2498,'11e263aa00a511e3bb73000c298b20fc','cardcenter','cc_roleinfo','flag_enable','',104003009,104014001,10,0,'5'),(2499,'11e2641800a511e3bb73000c298b20fc','cardcenter','cc_rolemenu','zid','',104003009,104014002,10,0,'1'),(2500,'11e2648600a511e3bb73000c298b20fc','cardcenter','cc_rolemenu','uid','',104003003,104014001,32,0,'2'),(2501,'11e264f400a511e3bb73000c298b20fc','cardcenter','cc_rolemenu','role_code','角色编号',104003022,104014001,45,0,'3'),(2502,'11e2655800a511e3bb73000c298b20fc','cardcenter','cc_rolemenu','menu_code','菜单编号',104003022,104014001,45,0,'4'),(2503,'11e265d000a511e3bb73000c298b20fc','cardcenter','cc_sendtask','zid','',104003009,104014002,10,0,'1'),(2504,'11e2663400a511e3bb73000c298b20fc','cardcenter','cc_sendtask','uid','',104003003,104014001,32,0,'2'),(2505,'11e266a200a511e3bb73000c298b20fc','cardcenter','cc_sendtask','task_code','任务编号',104003022,104014001,45,0,'3'),(2506,'11e2671000a511e3bb73000c298b20fc','cardcenter','cc_sendtask','serial_begin','开始流水号',104003022,104014001,45,0,'4'),(2507,'11e2679200a511e3bb73000c298b20fc','cardcenter','cc_sendtask','serial_finish','结束流水号',104003022,104014001,45,0,'5'),(2508,'11e267f600a511e3bb73000c298b20fc','cardcenter','cc_sendtask','agent_code','代理商编号',104003022,104014001,45,0,'6'),(2509,'11e2686e00a511e3bb73000c298b20fc','cardcenter','cc_sendtask','create_manager','创建人',104003022,104014001,45,0,'7'),(2510,'11e268d200a511e3bb73000c298b20fc','cardcenter','cc_sendtask','create_time','创建时间',104003003,104014001,19,0,'8'),(2511,'11e2694000a511e3bb73000c298b20fc','cardcenter','cc_sendtask','flag_finish','完成状态',104003009,104014001,10,0,'9'),(2512,'11e269ae00a511e3bb73000c298b20fc','cardcenter','cc_sendtask','task_message','执行结果',104003022,104014001,450,0,'10'),(2513,'11e26a1c00a511e3bb73000c298b20fc','cardcenter','cc_userinfo','zid','',104003009,104014002,10,0,'1'),(2514,'11e26a8000a511e3bb73000c298b20fc','cardcenter','cc_userinfo','uid','',104003003,104014001,32,0,'2'),(2515,'11e26aee00a511e3bb73000c298b20fc','cardcenter','cc_userinfo','user_code','用户编号',104003022,104014001,45,0,'3'),(2516,'11e26b5c00a511e3bb73000c298b20fc','cardcenter','cc_userinfo','user_name','用户名',104003022,104014001,450,0,'4'),(2517,'11e26bca00a511e3bb73000c298b20fc','cardcenter','cc_userinfo','user_password','密码',104003022,104014001,450,0,'5'),(2518,'11e26c3800a511e3bb73000c298b20fc','cardcenter','cc_userinfo','flag_enable','是否可用',104003009,104014001,10,0,'6'),(2519,'11e26ca600a511e3bb73000c298b20fc','cardcenter','cc_userinfo','create_time','创建时间',104003003,104014001,19,0,'7'),(2520,'11e2741c00a511e3bb73000c298b20fc','cardcenter','cc_userrole','zid','',104003009,104014002,10,0,'1'),(2521,'11e274c600a511e3bb73000c298b20fc','cardcenter','cc_userrole','uid','',104003003,104014001,32,0,'2'),(2522,'11e2753400a511e3bb73000c298b20fc','cardcenter','cc_userrole','user_code','用户',104003003,104014001,32,0,'3'),(2523,'11e2759800a511e3bb73000c298b20fc','cardcenter','cc_userrole','role_code','角色编号',104003022,104014001,45,0,'4'),(2524,'11e2761000a511e3bb73000c298b20fc','zapdata','zd_abstract','zid','',104003009,104014002,10,0,'1'),(2525,'11e2767e00a511e3bb73000c298b20fc','zapdata','zd_abstract','uid','',104003003,104014001,32,0,'2'),(2526,'11e276e200a511e3bb73000c298b20fc','zapdata','zd_abstract','abstract_aids','编码',104003009,104014001,10,0,'3'),(2527,'11e2775000a511e3bb73000c298b20fc','zapdata','zd_abstract','abstract_value','值',104003022,104014001,45,0,'4'),(2528,'11e277be00a511e3bb73000c298b20fc','zapdata','zd_abstract','abstract_remark','备注',104003022,104014001,450,0,'5'),(2529,'11e2782c00a511e3bb73000c298b20fc','zapdata','zd_abstract','parent_aid','父编号',104003009,104014001,10,0,'6'),(2530,'11e2789a00a511e3bb73000c298b20fc','zapdata','zd_column','zid','',104003009,104014002,10,0,'1'),(2531,'11e278fe00a511e3bb73000c298b20fc','zapdata','zd_column','uid','',104003003,104014001,32,0,'2'),(2532,'11e2796c00a511e3bb73000c298b20fc','zapdata','zd_column','server_name','库名称',104003022,104014001,45,0,'3'),(2533,'11e279e400a511e3bb73000c298b20fc','zapdata','zd_column','table_name','表名称',104003022,104014001,45,0,'4'),(2534,'11e27a4800a511e3bb73000c298b20fc','zapdata','zd_column','column_name','列名',104003022,104014001,45,0,'5'),(2535,'11e27ab600a511e3bb73000c298b20fc','zapdata','zd_column','column_note','字段描述',104003022,104014001,45,0,'6'),(2536,'11e27b2400a511e3bb73000c298b20fc','zapdata','zd_column','column_type_aid','列类型编号',104003009,104014001,10,0,'7'),(2537,'11e27b9200a511e3bb73000c298b20fc','zapdata','zd_column','null_able_aid','是否允许空',104003009,104014001,10,0,'8'),(2538,'11e27c0000a511e3bb73000c298b20fc','zapdata','zd_column','column_length','字段长度',104003009,104014001,10,0,'9'),(2539,'11e27c6e00a511e3bb73000c298b20fc','zapdata','zd_column','length_scale','小数点',104003009,104014001,10,0,'10'),(2540,'11e27cdc00a511e3bb73000c298b20fc','zapdata','zd_column','column_sort','排序',104003022,104014001,45,0,'11'),(2541,'11e27d4000a511e3bb73000c298b20fc','zapdata','zd_server','zid','',104003009,104014002,10,0,'1'),(2542,'11e27db800a511e3bb73000c298b20fc','zapdata','zd_server','uid','',104003003,104014001,32,0,'2'),(2543,'11e27e1c00a511e3bb73000c298b20fc','zapdata','zd_server','server_code','数据库编码',104003022,104014001,45,0,'3'),(2544,'11e27e8a00a511e3bb73000c298b20fc','zapdata','zd_server','server_name','数据库名称',104003022,104014001,45,0,'4'),(2545,'11e27ef800a511e3bb73000c298b20fc','zapdata','zd_server','server_type_aid','数据库类型',104003009,104014001,10,0,'5'),(2546,'11e27f6600a511e3bb73000c298b20fc','zapdata','zd_server','jdbc_driver','驱动',104003022,104014001,450,0,'6'),(2547,'11e27fd400a511e3bb73000c298b20fc','zapdata','zd_server','jdbc_dburl','路径',104003022,104014001,450,0,'7'),(2548,'11e2803800a511e3bb73000c298b20fc','zapdata','zd_server','jdbc_user','用户名',104003022,104014001,45,0,'8'),(2549,'11e280a600a511e3bb73000c298b20fc','zapdata','zd_server','jdbc_password','密码',104003022,104014001,45,0,'9'),(2550,'11e2811400a511e3bb73000c298b20fc','zapdata','zd_tables','zid','',104003009,104014002,10,0,'1'),(2551,'11e2818200a511e3bb73000c298b20fc','zapdata','zd_tables','uid','',104003003,104014001,32,0,'2'),(2552,'11e281f000a511e3bb73000c298b20fc','zapdata','zd_tables','server_name','数据库名称',104003022,104014001,45,0,'3'),(2553,'11e2825e00a511e3bb73000c298b20fc','zapdata','zd_tables','table_name','表名称',104003022,104014001,45,0,'4'),(2554,'11e282cc00a511e3bb73000c298b20fc','zapdata','zd_tables','table_remark','备注',104003022,104014001,450,0,'5'),(2555,'11e2833a00a511e3bb73000c298b20fc','zapdata','zw_field','zid','',104003009,104014002,10,0,'1'),(2556,'11e2839e00a511e3bb73000c298b20fc','zapdata','zw_field','uid','',104003003,104014001,32,0,'2'),(2557,'11e2840c00a511e3bb73000c298b20fc','zapdata','zw_field','view_code','视图编号',104003022,104014001,45,0,'3'),(2558,'11e2847a00a511e3bb73000c298b20fc','zapdata','zw_field','field_name','字段名称',104003022,104014001,45,0,'4'),(2559,'11e284e800a511e3bb73000c298b20fc','zapdata','zw_field','column_name','数据列名',104003022,104014001,45,0,'5'),(2560,'11e2855600a511e3bb73000c298b20fc','zapdata','zw_field','field_note','字段名称',104003022,104014001,45,0,'6'),(2561,'11e285c400a511e3bb73000c298b20fc','zapdata','zw_field','sort_add','排序添加',104003022,104014001,45,0,'7'),(2562,'11e2863200a511e3bb73000c298b20fc','zapdata','zw_field','sort_edit','排序修改',104003022,104014001,45,0,'8'),(2563,'11e286a000a511e3bb73000c298b20fc','zapdata','zw_field','sort_chart','排序列表',104003022,104014001,45,0,'9'),(2564,'11e2870400a511e3bb73000c298b20fc','zapdata','zw_field','sort_book','排序展示',104003022,104014001,45,0,'10'),(2565,'11e2877200a511e3bb73000c298b20fc','zapdata','zw_field','sort_inquery','排序查询',104003022,104014001,45,0,'11'),(2566,'11e287ea00a511e3bb73000c298b20fc','zapdata','zw_field','field_type_aid','字段类型',104003009,104014001,10,0,'12'),(2567,'11e2884e00a511e3bb73000c298b20fc','zapdata','zw_field','source_code','数据源编号',104003022,104014001,450,0,'13'),(2568,'11e288bc00a511e3bb73000c298b20fc','zapdata','zw_field','source_param','数据源参数',104003022,104014001,45,0,'14'),(2569,'11e2892a00a511e3bb73000c298b20fc','zapdata','zw_field','show_replace','展示替换',104003022,104014001,450,0,'15'),(2570,'11e2899800a511e3bb73000c298b20fc','zapdata','zw_field','default_value','默认值',104003022,104014001,450,0,'16'),(2571,'11e2905000a511e3bb73000c298b20fc','zapdata','zw_field','regex_value','正则验证(+为非空)',104003022,104014001,450,0,'17'),(2572,'11e290d200a511e3bb73000c298b20fc','zapdata','zw_field','query_type_aid','查询类型',104003009,104014001,10,0,'18'),(2573,'11e2914000a511e3bb73000c298b20fc','zapdata','zw_operate','zid','',104003009,104014002,10,0,'1'),(2574,'11e291ae00a511e3bb73000c298b20fc','zapdata','zw_operate','uid','',104003003,104014001,32,0,'2'),(2575,'11e2921c00a511e3bb73000c298b20fc','zapdata','zw_operate','operate_name','操作名称',104003022,104014001,45,0,'3'),(2576,'11e2928a00a511e3bb73000c298b20fc','zapdata','zw_operate','operate_type_aid','操作类型',104003009,104014001,10,0,'4'),(2577,'11e292f800a511e3bb73000c298b20fc','zapdata','zw_operate','page_code','页面编码',104003022,104014001,450,0,'5'),(2578,'11e2936600a511e3bb73000c298b20fc','zapdata','zw_operate','operate_link','操作链接',104003022,104014001,450,0,'6'),(2579,'11e293d400a511e3bb73000c298b20fc','zapdata','zw_operate','flag_enable','是否可用',104003009,104014001,10,0,'7'),(2580,'11e2944200a511e3bb73000c298b20fc','zapdata','zw_operate','operate_func','操作调用方法',104003022,104014001,450,0,'8'),(2581,'11e294b000a511e3bb73000c298b20fc','zapdata','zw_operate','area_type_aid','所在区域',104003009,104014001,10,0,'9'),(2582,'11e2951e00a511e3bb73000c298b20fc','zapdata','zw_page','zid','',104003009,104014002,10,0,'1'),(2583,'11e2958c00a511e3bb73000c298b20fc','zapdata','zw_page','uid','',104003003,104014001,32,0,'2'),(2584,'11e295f000a511e3bb73000c298b20fc','zapdata','zw_page','page_code','页面编号',104003022,104014001,45,0,'3'),(2585,'11e2965e00a511e3bb73000c298b20fc','zapdata','zw_page','page_name','页面名称',104003022,104014001,450,0,'4'),(2586,'11e296cc00a511e3bb73000c298b20fc','zapdata','zw_page','page_template','页面调用模板',104003022,104014001,45,0,'5'),(2587,'11e2973a00a511e3bb73000c298b20fc','zapdata','zw_page','view_code','视图编码',104003022,104014001,45,0,'6'),(2588,'11e2979e00a511e3bb73000c298b20fc','zapdata','zw_page','page_type_aid','页面类型',104003009,104014001,10,0,'7'),(2589,'11e2980c00a511e3bb73000c298b20fc','zapdata','zw_page','view_type_aid','',104003009,104014001,10,0,'8'),(2590,'11e2987a00a511e3bb73000c298b20fc','zapdata','zw_page','page_group','页面组',104003022,104014001,450,0,'9'),(2591,'11e298e800a511e3bb73000c298b20fc','zapdata','zw_page','flag_enable','可用标记',104003009,104014001,10,0,'10'),(2592,'11e2995600a511e3bb73000c298b20fc','zapdata','zw_page','project_aid','所属项目',104003009,104014001,10,0,'11'),(2593,'11e29afa00a511e3bb73000c298b20fc','zapdata','zw_source','zid','',104003009,104014002,10,0,'1'),(2594,'11e29b9a00a511e3bb73000c298b20fc','zapdata','zw_source','uid','',104003003,104014001,32,0,'2'),(2595,'11e29c0800a511e3bb73000c298b20fc','zapdata','zw_source','source_code','数据源编编码',104003022,104014001,45,0,'3'),(2596,'11e29c7600a511e3bb73000c298b20fc','zapdata','zw_source','source_name','数据源名称',104003022,104014001,45,0,'4'),(2597,'11e29ce400a511e3bb73000c298b20fc','zapdata','zw_source','source_from','数据源集合',104003022,104014001,45,0,'5'),(2598,'11e29d5200a511e3bb73000c298b20fc','zapdata','zw_source','field_text','字段名称',104003022,104014001,45,0,'6'),(2599,'11e29dc000a511e3bb73000c298b20fc','zapdata','zw_source','field_value','字段值',104003022,104014001,45,0,'7'),(2600,'11e29e2e00a511e3bb73000c298b20fc','zapdata','zw_source','where_book','展示条件',104003022,104014001,45,0,'8'),(2601,'11e29e9c00a511e3bb73000c298b20fc','zapdata','zw_source','where_edit','修改条件',104003022,104014001,45,0,'9'),(2602,'11e29f0a00a511e3bb73000c298b20fc','zapdata','zw_source','field_sort','排序字段',104003022,104014001,45,0,'10'),(2603,'11e29f7800a511e3bb73000c298b20fc','zapdata','zw_view','zid','',104003009,104014002,10,0,'1'),(2604,'11e29fe600a511e3bb73000c298b20fc','zapdata','zw_view','uid','',104003003,104014001,32,0,'2'),(2605,'11e2a04a00a511e3bb73000c298b20fc','zapdata','zw_view','view_code','视图编码',104003022,104014001,45,0,'3'),(2606,'11e2a0b800a511e3bb73000c298b20fc','zapdata','zw_view','view_name','试图名称',104003022,104014001,45,0,'4'),(2607,'11e2a12600a511e3bb73000c298b20fc','zapdata','zw_view','table_name','视图表格',104003022,104014001,45,0,'5'),(2608,'11e2a19400a511e3bb73000c298b20fc','zapdata','zw_view','project_aid','项目定义',104003009,104014001,10,0,'6'),(2609,'11e2a20200a511e3bb73000c298b20fc','zapdata','zw_webcode','zid','',104003009,104014002,10,0,'1'),(2610,'11e2a27000a511e3bb73000c298b20fc','zapdata','zw_webcode','uid','',104003003,104014001,32,0,'2'),(2611,'11e2a2d400a511e3bb73000c298b20fc','zapdata','zw_webcode','code_start','编码起始',104003022,104014001,100,0,'3'),(2612,'11e2a34200a511e3bb73000c298b20fc','zapdata','zw_webcode','date_apply','日期参数',104003003,104014001,6,0,'4'),(2613,'11e2a3b000a511e3bb73000c298b20fc','zapdata','zw_webcode','min_number','最小数字',104003009,104014001,10,0,'5'),(2614,'11e2a41e00a511e3bb73000c298b20fc','zapdata','zw_webcode','now_number','当前数字',104003009,104014001,10,0,'6'),(2615,'11e2a48c00a511e3bb73000c298b20fc','zapdata','zw_webcode','code_note','备注',104003022,104014001,45,0,'7'),(2634,'6719a43000a711e3bb73000c298b20fc','cardcenter','cc_log','apimethod','值 对应 ： usePresentCard,activeCard,queryCard',104003022,104014001,100,0,'9'),(2635,'a4e02d9600b811e3bb73000c298b20fc','zapdata','zw_define','zid','',104003009,104014002,10,0,'1'),(2636,'a4e031ce00b811e3bb73000c298b20fc','zapdata','zw_define','uid','',104003003,104014001,32,0,'2'),(2637,'a4e0328c00b811e3bb73000c298b20fc','zapdata','zw_define','define_dids','定义编号',104003022,104014001,450,0,'3'),(2638,'a4e0332200b811e3bb73000c298b20fc','zapdata','zw_define','define_name','定义名',104003022,104014001,450,0,'4'),(2639,'a4e033a400b811e3bb73000c298b20fc','zapdata','zw_define','define_note','定义名称中文',104003022,104014001,450,0,'5'),(2640,'a4e0341200b811e3bb73000c298b20fc','zapdata','zw_define','parent_did','父定义',104003022,104014001,450,0,'6'),(2641,'a4e0348000b811e3bb73000c298b20fc','zapdata','zw_define','define_remark','备注',104003022,104014001,450,0,'7'),(2642,'a4e034ee00b811e3bb73000c298b20fc','zapdata','zw_define','define_one','附加参数一号',104003022,104014001,450,0,'8'),(2643,'a4e0356600b811e3bb73000c298b20fc','zapdata','zw_define','define_two','附加参数二号',104003022,104014001,450,0,'9'),(2644,'a4e035de00b811e3bb73000c298b20fc','zapdata','zw_define','define_three','附加参数三号',104003022,104014001,450,0,'10'),(2645,'a4e03a6600b811e3bb73000c298b20fc','zapdata','zw_define','define_four','附加参数四号',104003022,104014001,450,0,'11'),(2646,'a4e03b1a00b811e3bb73000c298b20fc','zapdata','zw_define','define_five','附加参数五号',104003022,104014001,450,0,'12'),(2647,'a4e03b9c00b811e3bb73000c298b20fc','zapdata','zw_operate','init_type_did','初始化类型',104003022,104014001,45,0,'10'),(2650,'f6b54296030b11e3bb73000c298b20fc','zapdata','za_define','zid','',104003009,104014002,10,0,'1'),(2651,'f6b544e4030b11e3bb73000c298b20fc','zapdata','za_define','uid','',104003003,104014001,32,0,'2'),(2652,'f6b5457a030b11e3bb73000c298b20fc','zapdata','za_define','define_dids','定义编号',104003022,104014001,30,0,'3'),(2653,'f6b545fc030b11e3bb73000c298b20fc','zapdata','za_define','define_name','定义名称',104003022,104014001,450,0,'4'),(2654,'f6b54674030b11e3bb73000c298b20fc','zapdata','za_define','define_note','描述',104003022,104014001,45,0,'5'),(2655,'f6b546d8030b11e3bb73000c298b20fc','zapdata','za_define','level_rank','级别',104003009,104014001,10,0,'6'),(2656,'f6b5473c030b11e3bb73000c298b20fc','zapdata','za_define','parent_did','父编号',104003022,104014001,45,0,'7'),(2657,'f6b547a0030b11e3bb73000c298b20fc','zapdata','za_menu','zid','',104003009,104014002,10,0,'1'),(2658,'f6b5480e030b11e3bb73000c298b20fc','zapdata','za_menu','uid','',104003003,104014001,32,0,'2'),(2659,'f6b548d6030b11e3bb73000c298b20fc','zapdata','za_menu','menu_code','菜单编号',104003022,104014001,45,0,'3'),(2660,'f6b54a02030b11e3bb73000c298b20fc','zapdata','za_menu','menu_name','菜单名称',104003022,104014001,45,0,'4'),(2661,'f6b54a84030b11e3bb73000c298b20fc','zapdata','za_menu','parent_menu','父菜单',104003022,104014001,45,0,'5'),(2662,'f6b54af2030b11e3bb73000c298b20fc','zapdata','za_menu','menu_link','链接地址',104003022,104014001,450,0,'6');
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
INSERT INTO `zd_server` VALUES (1,'','zapdata','zapdata',104020013,'com.mysql.jdbc.Driver','jdbc:mysql://db.wct.srnpr.com:3306/zapdata?useUnicode=true','zapdata',''),(2,'','cardcenter','cardcenter',104020013,'com.mysql.jdbc.Driver','jdbc:mysql://db.wct.srnpr.com:3306/cardcenter?useUnicode=true&noAccessToProcedureBodies=true','cardcenter','');
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
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zd_tables`
--

LOCK TABLES `zd_tables` WRITE;
/*!40000 ALTER TABLE `zd_tables` DISABLE KEYS */;
INSERT INTO `zd_tables` VALUES (156,'a05bd2b8edca11e2abe65404a6a9ac98','cardcenter','cc_agentuser','代理商表'),(157,'a05bd4a9edca11e2abe65404a6a9ac98','cardcenter','cc_cardinfo','卡信息表'),(158,'a05bd5d5edca11e2abe65404a6a9ac98','cardcenter','cc_cardlog','卡日志表'),(159,'a05bd6bbedca11e2abe65404a6a9ac98','cardcenter','cc_createtask','创建任务表'),(160,'a05bd798edca11e2abe65404a6a9ac98','cardcenter','cc_define','卡中心定义表'),(161,'a05bd86eedca11e2abe65404a6a9ac98','cardcenter','cc_moneytype','卡金额类型表'),(162,'a05bd948edca11e2abe65404a6a9ac98','cardcenter','cc_sendtask','购物卡发放表'),(163,'a05bda21edca11e2abe65404a6a9ac98','zapdata','zd_abstract',''),(164,'a05bdb0bedca11e2abe65404a6a9ac98','zapdata','zd_column',''),(165,'a05bdbdcedca11e2abe65404a6a9ac98','zapdata','zd_server',''),(166,'a05bdcb6edca11e2abe65404a6a9ac98','zapdata','zd_tables',''),(167,'a05bdd98edca11e2abe65404a6a9ac98','zapdata','zw_define',''),(168,'a05bde7eedca11e2abe65404a6a9ac98','zapdata','zw_field','字段表'),(169,'a05bdf57edca11e2abe65404a6a9ac98','zapdata','zw_manager',''),(170,'a05be03dedca11e2abe65404a6a9ac98','zapdata','zw_menu','菜单表'),(171,'a05be123edca11e2abe65404a6a9ac98','zapdata','zw_operate',''),(172,'a05be20dedca11e2abe65404a6a9ac98','zapdata','zw_page',''),(173,'a05be2ebedca11e2abe65404a6a9ac98','zapdata','zw_role',''),(174,'a05be4f0edca11e2abe65404a6a9ac98','zapdata','zw_source','数据源表'),(175,'a05be5ffedca11e2abe65404a6a9ac98','zapdata','zw_view','视图表'),(176,'aedff286f9c911e29b7a000c298b20fc','cardcenter','cc_cardlock',''),(177,'aedff736f9c911e29b7a000c298b20fc','cardcenter','cc_menu',''),(178,'aedff826f9c911e29b7a000c298b20fc','cardcenter','cc_userinfo','用户信息表'),(179,'13a4ae52fa6311e29b7a000c298b20fc','cardcenter','cc_menuinfo',''),(180,'13a4b0c8fa6311e29b7a000c298b20fc','cardcenter','cc_roleinfo','角色表'),(181,'13a4b17cfa6311e29b7a000c298b20fc','cardcenter','cc_rolemenu','角色菜单表'),(182,'13a4b1fefa6311e29b7a000c298b20fc','cardcenter','cc_userrole','用户角色表'),(183,'93120c8efb4911e2ac71000c298b20fc','zapdata','zw_webcode','系统编码表'),(184,'3a7fdaaefda811e2ac71000c298b20fc','cardcenter','cc_activetask','购物卡激活表'),(185,'0f9cf2aa00a411e3bb73000c298b20fc','cardcenter','cc_log',''),(186,'0f9cf5a200a411e3bb73000c298b20fc','cardcenter','cc_reportforday',''),(187,'0f9cf69c00a411e3bb73000c298b20fc','cardcenter','cc_reportformonth',''),(188,'f6a98028030b11e3bb73000c298b20fc','zapdata','za_define','定义表'),(189,'f6a984e2030b11e3bb73000c298b20fc','zapdata','za_menu','菜单表');
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
  `define_dids` varchar(450) DEFAULT '' COMMENT '定义编号',
  `define_name` varchar(450) DEFAULT '' COMMENT '定义名',
  `define_note` varchar(450) DEFAULT '' COMMENT '定义名称中文',
  `parent_did` varchar(450) DEFAULT '' COMMENT '父定义',
  `define_remark` varchar(450) DEFAULT '' COMMENT '备注',
  `define_one` varchar(450) DEFAULT '' COMMENT '附加参数一号',
  `define_two` varchar(450) DEFAULT '' COMMENT '附加参数二号',
  `define_three` varchar(450) DEFAULT '' COMMENT '附加参数三号',
  `define_four` varchar(450) DEFAULT '' COMMENT '附加参数四号',
  `define_five` varchar(450) DEFAULT '' COMMENT '附加参数五号',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_define`
--

LOCK TABLES `zw_define` WRITE;
/*!40000 ALTER TABLE `zw_define` DISABLE KEYS */;
INSERT INTO `zw_define` VALUES (1,'','4699','zapweb','系统定义','','','','','','',''),(2,'','46991615','proc_operate','存储过程-按钮','4699','该定义用于初始化zw_operate','','','','',''),(3,'','469916150004','default','默认','46991615','','','','','',''),(4,'','469916151601','page_add','添加','46991615','','116015008','116016003','116001020','',''),(5,'','469916151605','page_edit','修改','46991615','','116015008','116016003','116001003','',''),(6,'','469916151604','page_delete','删除','46991615','','116015010','116016003','116001003','com.srnpr.zapweb.webfunc.FuncDelete','zapjs.zw.func_delete(this)'),(7,'','469916151609','page_inquire','查询','46991615','','116015010','116016003','116001009','','zapjs.zw.func_inquire(this)'),(8,'','469916151901','submit_add','提交新增','46991615','','116015010','116016001','116001016','com.srnpr.zapweb.webfunc.FuncAdd','zapjs.zw.func_add(this)'),(9,'','469916151905','submit_edit','提交修改','46991615','','116015010','116016005','116001016','com.srnpr.zapweb.webfunc.FuncEdit','zapjs.zw.func_edit(this)'),(10,'','469916150503','export_chart','导出','46991615','','116015010','116016003','116001009','','zapjs.zw.func_export(this)'),(11,'','46991616','proc_page','存储过程-页面','4699','','','','','',''),(12,'','469916160004','default','默认','46991616','','','','','',''),(13,'','469916161601','add','新增','46991616','','-新增','page_add_','../zappage/add','116016001','116022001'),(14,'','469916161603','chart','列表','46991616','','-列表','page_chart_','../zappage/chart','116016003','116022003'),(15,'','469916161605','edit','修改','46991616','','-修改','page_edit_','../zappage/edit','116016005','116022005');
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
  `field_name` varchar(45) DEFAULT '' COMMENT '字段名称',
  `column_name` varchar(45) DEFAULT '' COMMENT '数据列名',
  `field_note` varchar(45) DEFAULT '' COMMENT '字段名称',
  `sort_add` varchar(45) DEFAULT '' COMMENT '排序添加',
  `sort_edit` varchar(45) DEFAULT '' COMMENT '排序修改',
  `sort_chart` varchar(45) DEFAULT '' COMMENT '排序列表',
  `sort_book` varchar(45) DEFAULT '' COMMENT '排序展示',
  `sort_inquery` varchar(45) DEFAULT '' COMMENT '排序查询',
  `field_type_aid` int(11) DEFAULT '104005008' COMMENT '字段类型',
  `source_code` varchar(450) DEFAULT '' COMMENT '数据源编号',
  `source_param` varchar(45) DEFAULT '' COMMENT '数据源参数',
  `show_replace` varchar(450) DEFAULT '' COMMENT '展示替换',
  `default_value` varchar(450) DEFAULT '' COMMENT '默认值',
  `regex_value` varchar(450) DEFAULT '' COMMENT '正则验证(+为非空)',
  `query_type_aid` int(11) DEFAULT '104009005' COMMENT '查询类型',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8 COMMENT='字段表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_field`
--

LOCK TABLES `zw_field` WRITE;
/*!40000 ALTER TABLE `zw_field` DISABLE KEYS */;
INSERT INTO `zw_field` VALUES (59,'01a17605edcb11e2abe65404a6a9ac98','v_zw_view','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(60,'01a17840edcb11e2abe65404a6a9ac98','v_zw_view','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(61,'01a17905edcb11e2abe65404a6a9ac98','v_zw_view','view_code','view_code','视图编码','100300','100300','100300','100300','100300',104005009,'','','','','',104009005),(62,'01a179b6edcb11e2abe65404a6a9ac98','v_zw_view','view_name','view_name','视图名称','100400','100400','100400','100400','100400',104005009,'','','','','',104009005),(63,'01a17a5eedcb11e2abe65404a6a9ac98','v_zw_view','table_name','table_name','视图表格','100500','100500','100500','100500','100500',104005009,'','','','','',104009005),(66,'01a36be2edcb11e2abe65404a6a9ac98','v_zw_field','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(67,'01a36f0fedcb11e2abe65404a6a9ac98','v_zw_field','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(68,'01a3c689edcb11e2abe65404a6a9ac98','v_zw_field','view_code','view_code','视图编号','100300','100300','100300','100300','100300',104005009,'','','','','',104009001),(69,'01a3c89aedcb11e2abe65404a6a9ac98','v_zw_field','column_name','column_name','数据列名','100400','100400','100400','100400','100400',104005009,'','','','','',104009005),(70,'01a3c95bedcb11e2abe65404a6a9ac98','v_zw_field','field_note','field_note','字段名称','100500','100500','100500','100500','100500',104005009,'','','','','',104009005),(71,'01a3c9f7edcb11e2abe65404a6a9ac98','v_zw_field','sort_add','sort_add','排序添加','100600','100600','100600','100600','0',104005009,'','','','','',104009005),(72,'01a3ca8fedcb11e2abe65404a6a9ac98','v_zw_field','sort_edit','sort_edit','排序修改','100700','100700','100700','100700','0',104005009,'','','','','',104009005),(73,'01a3cb2bedcb11e2abe65404a6a9ac98','v_zw_field','sort_chart','sort_chart','排序列表','100800','100800','100800','100800','0',104005009,'','','','','',104009005),(74,'01a3cbcbedcb11e2abe65404a6a9ac98','v_zw_field','sort_book','sort_book','排序展示','100900','100900','100900','100900','0',104005009,'','','','','',104009005),(75,'01a3cd72edcb11e2abe65404a6a9ac98','v_zw_field','sort_inquery','sort_inquery','排序查询','101000','101000','101000','101000','0',104005009,'','','','','',104009005),(76,'01a3ce12edcb11e2abe65404a6a9ac98','v_zw_field','field_type_aid','field_type_aid','字段类型','101100','101100','101100','101100','101100',104005019,'source_zd_abstract','104005','','','',104009005),(81,'01a4a3feedcb11e2abe65404a6a9ac98','v_cc_createtask','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(82,'01a4a5deedcb11e2abe65404a6a9ac98','v_cc_createtask','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(83,'01a4a6a3edcb11e2abe65404a6a9ac98','v_cc_createtask','task_code','task_code','任务编号','0','0','0','0','0',104005009,'','','','','',104009005),(84,'01a4a750edcb11e2abe65404a6a9ac98','v_cc_createtask','task_name','task_name','任务名称','100400','100400','100400','100400','100400',104005009,'','','','','+',104009005),(85,'01a4a7f8edcb11e2abe65404a6a9ac98','v_cc_createtask','task_remark','task_remark','备注','101300','101300','0','101300','0',104005020,'','','','','',104009005),(86,'01a4ac61edcb11e2abe65404a6a9ac98','v_cc_createtask','card_money','card_money','卡金额','100600','100600','100600','100600','0',104005019,'source_cc_moneytype','','','','^\\d{1,10}$',104009005),(87,'01a4ad5bedcb11e2abe65404a6a9ac98','v_cc_createtask','card_number','card_number','制卡数量','100700','100700','100700','100700','0',104005009,'','','','','^\\d{1,10}$',104009005),(88,'01a4ae10edcb11e2abe65404a6a9ac98','v_cc_createtask','card_make','card_make','已生成数量','0','100800','100800','100800','0',104005009,'','','','','',104009005),(89,'01a4aec0edcb11e2abe65404a6a9ac98','v_cc_createtask','create_user','create_user','创建人','0','100900','100900','100900','100900',104005009,'','','','','',104009005),(90,'01a4af58edcb11e2abe65404a6a9ac98','v_cc_createtask','create_time','create_time','创建时间','0','101000','101000','101000','101000',104005004,'','','','','',104009002),(91,'01a4aff4edcb11e2abe65404a6a9ac98','v_cc_createtask','serial_begin','serial_begin','开始流水号','0','101100','101100','101100','0',104005009,'','','','','^\\d{1,9}$',104009005),(92,'01a4b094edcb11e2abe65404a6a9ac98','v_cc_createtask','serial_finish','serial_finish','结束流水号','0','101200','101200','101200','0',104005009,'','','','','^\\d{1,9}$',104009005),(93,'a5ad0a96f4ce11e2833f5404a6a9ac98','v_zw_view','project_aid','project_aid','项目定义','100600','100600','100600','100600','100600',104005019,'source_zd_abstract','101002','','','',104009005),(94,'a5b2adb4f4ce11e2833f5404a6a9ac98','v_zw_field','source_code','source_code','数据源编号','101200','101200','101200','101200','0',104005009,'','','','','',104009005),(95,'a5b2af42f4ce11e2833f5404a6a9ac98','v_zw_field','source_param','source_param','数据源参数','101300','101300','101300','101300','0',104005009,'','','','','',104009005),(96,'a5b2afdaf4ce11e2833f5404a6a9ac98','v_zw_field','show_replace','show_replace','展示替换','101400','101400','101400','101400','0',104005009,'','','','','',104009005),(97,'a5b2b055f4ce11e2833f5404a6a9ac98','v_zw_field','default_value','default_value','默认值','101500','101500','101500','101500','0',104005009,'','','','','',104009005),(101,'fa6ad404fa7411e29b7a000c298b20fc','v_zw_field','regex_value','regex_value','正则验证(+为非空)','101600','101600','101600','101600','0',104005009,'','','','','',104009005),(102,'fa6ad6b6fa7411e29b7a000c298b20fc','v_zw_field','query_type_aid','query_type_aid','查询类型','101700','101700','101700','101700','101700',104005019,'source_zd_abstract','104009','','','',104009005),(104,'fa6c2444fa7411e29b7a000c298b20fc','v_cc_cardinfo','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(105,'fa6c2610fa7411e29b7a000c298b20fc','v_cc_cardinfo','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(106,'fa6c26e2fa7411e29b7a000c298b20fc','v_cc_cardinfo','card_serial','card_serial','购物卡流水号','100300','100300','100300','100300','100300',104005009,'','','','','',104009005),(107,'fa6c27a0fa7411e29b7a000c298b20fc','v_cc_cardinfo','card_code','card_code','购物卡卡号','100400','100400','100400','100400','100400',104005009,'','','','','',104009005),(108,'fa6c284afa7411e29b7a000c298b20fc','v_cc_cardinfo','card_pass','card_pass','购物卡密码','0','0','0','0','0',104005009,'','','','','',104009005),(109,'fa6c28e0fa7411e29b7a000c298b20fc','v_cc_cardinfo','card_money','card_money','购物卡金额','100600','100600','100600','100600','0',104005009,'','','','','',104009005),(110,'fa6c2980fa7411e29b7a000c298b20fc','v_cc_cardinfo','used_money','used_money','已使用金额','100700','100700','0','100700','0',104005009,'','','','','',104009005),(111,'fa6c2a20fa7411e29b7a000c298b20fc','v_cc_cardinfo','inital_money','inital_money','初始金额','100800','100800','100800','100800','0',104005009,'','','','','',104009005),(112,'fa6c2ac0fa7411e29b7a000c298b20fc','v_cc_cardinfo','flag_active','flag_active','激活标识','100900','100900','0','100900','0',104005009,'','','','','',104009005),(113,'fa6c2b60fa7411e29b7a000c298b20fc','v_cc_cardinfo','begin_time','begin_time','开始使用时间','101000','101000','101000','101000','0',104005009,'','','','','',104009005),(114,'fa6c2bf6fa7411e29b7a000c298b20fc','v_cc_cardinfo','finish_time','finish_time','截止使用时间','101100','101100','101100','101100','0',104005009,'','','','','',104009005),(115,'fa6c2c96fa7411e29b7a000c298b20fc','v_cc_cardinfo','create_time','create_time','创建时间','101200','101200','0','101200','0',104005009,'','','','','',104009005),(116,'fa6c2d2cfa7411e29b7a000c298b20fc','v_cc_cardinfo','active_time','active_time','激活时间','101300','101300','101700','101300','101700',104005004,'','','','','',104009002),(117,'fa6c2dd6fa7411e29b7a000c298b20fc','v_cc_cardinfo','flag_send','flag_send','发放标记','101400','101400','0','101400','0',104005009,'','','','','',104009005),(118,'fa6c2e6cfa7411e29b7a000c298b20fc','v_cc_cardinfo','cc_taskid','cc_taskid','table cc_receatetask 的 zid','0','0','0','0','0',104005009,'','','','','',104009005),(119,'78d7d180fb2311e2ac71000c298b20fc','v_cc_sendtask','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(120,'78d7d45afb2311e2ac71000c298b20fc','v_cc_sendtask','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(121,'78d7d55efb2311e2ac71000c298b20fc','v_cc_sendtask','serial_begin','serial_begin','开始流水号','100300','100300','100300','100300','0',104005009,'','','','','^\\d{1,10}$',104009005),(122,'78d7d6bcfb2311e2ac71000c298b20fc','v_cc_sendtask','serial_finish','serial_finish','结束流水号','100400','100400','100400','100400','0',104005009,'','','','','^\\d{1,10}$',104009005),(123,'78d7d798fb2311e2ac71000c298b20fc','v_cc_sendtask','agent_code','agent_code','代理商','100500','100500','100500','100500','0',104005019,'source_cc_agentuser','','','','',104009005),(124,'78d7d856fb2311e2ac71000c298b20fc','v_cc_sendtask','create_manager','create_manager','创建人','0','100600','100600','100600','100600',104005009,'','','','','',104009005),(125,'78d7d900fb2311e2ac71000c298b20fc','v_cc_sendtask','create_time','create_time','创建时间','0','100700','100700','100700','100600',104005004,'','','','','',104009002),(126,'ef78c6b4fb2311e2ac71000c298b20fc','v_cc_sendtask','task_code','task_code','任务编号','0','100200','100200','100200','0',104005009,'','','','','',104009005),(127,'ef78ca42fb2311e2ac71000c298b20fc','v_cc_sendtask','flag_finish','flag_finish','完成状态','0','100900','0','100900','0',104005009,'','','','','',104009005),(128,'ef78cb6efb2311e2ac71000c298b20fc','v_cc_sendtask','task_message','task_message','执行结果','0','101000','101000','101000','0',104005009,'','','','','',104009005),(129,'23735216fd6f11e2ac71000c298b20fc','v_cc_userinfo','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(130,'237356d0fd6f11e2ac71000c298b20fc','v_cc_userinfo','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(131,'237357f2fd6f11e2ac71000c298b20fc','v_cc_userinfo','user_name','user_name','用户名','100300','100300','100300','100300','100300',104005009,'','','','@unique$','+',104009005),(132,'237358c4fd6f11e2ac71000c298b20fc','v_cc_userinfo','user_password','user_password','密码','100400','0','0','0','0',104005009,'','','','','+',104009005),(133,'237359a0fd6f11e2ac71000c298b20fc','v_cc_userinfo','flag_enable','flag_enable','是否可用','100500','100500','100500','100500','0',104005019,'source_zd_abstract','100001','','','',104009005),(134,'23735a68fd6f11e2ac71000c298b20fc','v_cc_userinfo','create_time','create_time','创建时间','0','0','100600','100600','0',104005009,'','','','@datenow$','',104009005),(135,'23735b12fd6f11e2ac71000c298b20fc','v_cc_userinfo','component_role','','用户角色','100700','100700','100700','100700','0',104005003,'com.srnpr.cardcenter.webcomponent.ComponentUserrole','','','','',104009005),(136,'8411dcb2fda811e2ac71000c298b20fc','v_cc_userinfo','user_code','user_code','用户编号','0','0','0','0','0',104005009,'','','','@code$US','',104009005),(139,'841311e0fda811e2ac71000c298b20fc','v_cc_activetask','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(140,'841313defda811e2ac71000c298b20fc','v_cc_activetask','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(141,'841314c4fda811e2ac71000c298b20fc','v_cc_activetask','task_code','task_code','任务编号','0','0','100300','100300','0',104005009,'','','','','',104009005),(142,'8413158cfda811e2ac71000c298b20fc','v_cc_activetask','serial_begin','serial_begin','开始流水号','100400','100400','100400','100400','0',104005009,'','','','','^\\d{1,9}$',104009005),(143,'8413164afda811e2ac71000c298b20fc','v_cc_activetask','serial_finish','serial_finish','结束流水号','100500','100500','100500','100500','0',104005009,'','','','','^\\d{1,9}$',104009005),(144,'841316f4fda811e2ac71000c298b20fc','v_cc_activetask','agent_code','agent_code','代理商','0','0','0','0','0',104005009,'','','','','',104009005),(145,'8413179efda811e2ac71000c298b20fc','v_cc_activetask','create_manager','create_manager','创建人','0','0','0','0','0',104005009,'','','','','',104009005),(146,'84131848fda811e2ac71000c298b20fc','v_cc_activetask','create_time','create_time','创建时间','0','0','0','0','100800',104005004,'','','','','',104009002),(147,'841318fcfda811e2ac71000c298b20fc','v_cc_activetask','flag_finish','flag_finish','完成状态','0','0','0','0','0',104005009,'','','','','',104009005),(148,'841319b0fda811e2ac71000c298b20fc','v_cc_activetask','task_message','task_message','执行结果','0','0','100900','0','0',104005009,'','','','','',104009005),(154,'5d9df542fda911e2ac71000c298b20fc','v_cc_activetask','begin_time','begin_time','开始使用时间','101100','101100','101100','101100','0',104005004,'','','','','+',104009005),(155,'5d9df7aefda911e2ac71000c298b20fc','v_cc_activetask','finish_time','finish_time','结束使用时间','101200','101200','101200','101200','0',104005004,'','','','','+',104009005),(158,'b05586f6fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(159,'b05588aefe4911e2ac71000c298b20fc','v_export_cc_cardinfo','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(160,'b05589b2fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','card_serial','card_serial','购物卡流水号','100300','100300','100300','100300','0',104005009,'','','','','',104009005),(161,'b0558a84fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','card_code','card_code','购物卡卡号','100400','100400','100400','100400','0',104005009,'','','','','',104009005),(162,'b0558b4cfe4911e2ac71000c298b20fc','v_export_cc_cardinfo','card_pass','card_pass','购物卡密码','100500','100500','100500','100500','0',104005009,'','','','','',104009005),(163,'b0558c00fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','card_money','card_money','购物卡金额','100600','100600','100600','100600','0',104005009,'','','','','',104009005),(164,'b0558cbefe4911e2ac71000c298b20fc','v_export_cc_cardinfo','used_money','used_money','已使用金额','100700','100700','100700','100700','0',104005009,'','','','','',104009005),(165,'b0558d72fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','inital_money','inital_money','初始金额','100800','100800','100800','100800','0',104005009,'','','','','',104009005),(166,'b0558e26fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','flag_active','flag_active','激活标识','0','100900','100900','100900','0',104005009,'','','','','',104009005),(167,'b0558ed0fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','begin_time','begin_time','开始使用时间','101000','101000','101000','101000','0',104005009,'','','','','',104009005),(168,'b0558f7afe4911e2ac71000c298b20fc','v_export_cc_cardinfo','finish_time','finish_time','截止使用时间','101100','101100','101100','101100','0',104005009,'','','','','',104009005),(169,'b055902efe4911e2ac71000c298b20fc','v_export_cc_cardinfo','create_time','create_time','创建时间','101200','101200','101200','101200','0',104005009,'','','','','',104009005),(170,'b05590e2fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','active_time','active_time','激活时间','101300','101300','101300','101300','0',104005009,'','','','','',104009005),(171,'b055918cfe4911e2ac71000c298b20fc','v_export_cc_cardinfo','flag_send','flag_send','发放标记','0','101400','101400','101400','0',104005009,'','','','','',104009005),(172,'b0559240fe4911e2ac71000c298b20fc','v_export_cc_cardinfo','cc_taskid','cc_taskid','创建任务ID','0','0','0','0','101500',104005009,'','','','','',104009005),(174,'28faea8afefc11e2bb73000c298b20fc','v_zw_field','field_name','field_name','字段名称','100400','100400','100400','100400','0',104005009,'','','','','',104009005),(175,'28fd0130fefc11e2bb73000c298b20fc','v_cc_cardinfo','agent_code','agent_code','代理商','0','0','0','101600','0',104005009,'','','','','',104009005),(176,'29014560fefc11e2bb73000c298b20fc','v_export_cc_cardinfo','agent_code','agent_code','代理商','0','0','0','101600','0',104005009,'','','','','',104009005),(177,'600a812eff2a11e2bb73000c298b20fc','v_cc_cardinfo','send_time','send_time','发放时间','0','0','0','0','0',104005009,'','','','','',104009002),(178,'600ef448ff2a11e2bb73000c298b20fc','v_export_cc_cardinfo','send_time','send_time','发放时间','0','0','0','0','0',104005009,'','','','','',104009005),(179,'4b244654033111e3bb73000c298b20fc','v_zw_operate','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(180,'4b244a82033111e3bb73000c298b20fc','v_zw_operate','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(181,'4b244b90033111e3bb73000c298b20fc','v_zw_operate','operate_name','operate_name','操作名称','100300','100300','100300','100300','0',104005009,'','','','','',104009005),(182,'4b244c6c033111e3bb73000c298b20fc','v_zw_operate','operate_type_aid','operate_type_aid','操作类型','100400','100400','100400','100400','0',104005019,'source_zd_abstract','116015','','','',104009005),(183,'4b244d34033111e3bb73000c298b20fc','v_zw_operate','page_code','page_code','页面编码','100500','100500','100500','100500','100500',104005009,'','','','','',104009001),(184,'4b24593c033111e3bb73000c298b20fc','v_zw_operate','operate_link','operate_link','操作链接','100600','100600','100600','100600','0',104005009,'','','','','',104009005),(185,'4b245a40033111e3bb73000c298b20fc','v_zw_operate','flag_enable','flag_enable','是否可用','100700','100700','100700','100700','0',104005019,'source_zd_abstract','100001','','','',104009005),(186,'4b245af4033111e3bb73000c298b20fc','v_zw_operate','operate_func','operate_func','操作调用方法','100800','100800','100800','100800','0',104005009,'','','','','',104009005),(187,'4b245bb2033111e3bb73000c298b20fc','v_zw_operate','area_type_aid','area_type_aid','所在区域','100900','100900','100900','100900','0',104005019,'source_zd_abstract','116001','','','',104009005),(188,'4b245c5c033111e3bb73000c298b20fc','v_zw_operate','init_type_did','init_type_did','初始化类型','101000','101000','101000','101000','0',104005019,'source_zw_define','46991615','','','',104009005),(194,'55ff6634033211e3bb73000c298b20fc','v_zw_page','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(195,'55ff6a08033211e3bb73000c298b20fc','v_zw_page','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(196,'55ff6b2a033211e3bb73000c298b20fc','v_zw_page','page_code','page_code','页面编号','100300','100300','100300','100300','0',104005009,'','','','','',104009005),(197,'55ff6be8033211e3bb73000c298b20fc','v_zw_page','page_name','page_name','页面名称','100400','100400','100400','100400','0',104005009,'','','','','',104009005),(198,'55ff6ca6033211e3bb73000c298b20fc','v_zw_page','page_template','page_template','页面调用模板','100500','100500','100500','100500','0',104005009,'','','','','',104009005),(199,'55ff6d5a033211e3bb73000c298b20fc','v_zw_page','view_code','view_code','视图编码','100600','100600','100600','100600','100600',104005009,'','','','','',104009001),(200,'55ff6e0e033211e3bb73000c298b20fc','v_zw_page','page_type_aid','page_type_aid','页面类型','100700','100700','100700','100700','0',104005019,'source_zd_abstract','116016','','','',104009005),(201,'55ff6f12033211e3bb73000c298b20fc','v_zw_page','view_type_aid','view_type_aid','视图类型','100800','100800','100800','100800','0',104005019,'source_zd_abstract','116022','','','',104009005),(202,'55ff6fda033211e3bb73000c298b20fc','v_zw_page','page_group','page_group','页面组','100900','100900','100900','100900','0',104005009,'','','','','',104009005),(203,'55ff708e033211e3bb73000c298b20fc','v_zw_page','flag_enable','flag_enable','可用标记','101000','101000','101000','101000','0',104005019,'source_zd_abstract','100001','','','',104009005),(204,'55ff7142033211e3bb73000c298b20fc','v_zw_page','project_aid','project_aid','所属项目','101100','101100','101100','101100','0',104005019,'source_zd_abstract','101002','','','',104009005),(209,'4d243f0e03c711e3bb73000c298b20fc','v_cc_reportformonth','zid','zid','','0','0','0','0','0',104005008,'','','','','',104009005),(210,'4d24425603c711e3bb73000c298b20fc','v_cc_reportformonth','uid','uid','','0','100200','0','0','0',104005008,'','','','','',104009005),(211,'4d24435a03c711e3bb73000c298b20fc','v_cc_reportformonth','report_date','report_date','报表日期','100300','100300','100300','100300','100300',104005004,'','','','','',104009002),(212,'4d24442c03c711e3bb73000c298b20fc','v_cc_reportformonth','create_money','create_money','统计创建的总钱数','100400','100400','100400','0','0',104005009,'','','','','',104009005),(213,'4d2444ea03c711e3bb73000c298b20fc','v_cc_reportformonth','create_count','create_count','统计创建的数量','100500','100500','100500','0','0',104005009,'','','','','',104009005),(214,'4d2445a803c711e3bb73000c298b20fc','v_cc_reportformonth','send_money','send_money','统计发放的总钱数','100600','100600','100600','0','0',104005009,'','','','','',104009005),(215,'4d24465c03c711e3bb73000c298b20fc','v_cc_reportformonth','send_count','send_count','统计发放的总数量','100700','100700','100700','0','0',104005009,'','','','','',104009005),(216,'4d24470603c711e3bb73000c298b20fc','v_cc_reportformonth','active_money','active_money','统计激活的总钱数','100800','100800','100800','0','0',104005009,'','','','','',104009005),(217,'4d2447ba03c711e3bb73000c298b20fc','v_cc_reportformonth','active_count','active_count','统计激活的总数量','100900','100900','100900','0','0',104005009,'','','','','',104009005),(218,'4d24486e03c711e3bb73000c298b20fc','v_cc_reportformonth','consume_money','consume_money','统计消费的总钱数','101000','101000','101000','0','0',104005009,'','','','','',104009005),(219,'4d24492203c711e3bb73000c298b20fc','v_cc_reportformonth','consume_count','consume_count','统计消费的总数量','101100','101100','101100','0','0',104005009,'','','','','',104009005),(220,'4d2449cc03c711e3bb73000c298b20fc','v_cc_reportformonth','create_time','create_time','创建时间','0','101200','0','0','0',104005009,'','','','','',104009005);
/*!40000 ALTER TABLE `zw_field` ENABLE KEYS */;
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
  `operate_type_aid` int(11) DEFAULT '104005009' COMMENT '操作类型',
  `page_code` varchar(450) DEFAULT '' COMMENT '页面编码',
  `operate_link` varchar(450) DEFAULT '' COMMENT '操作链接',
  `flag_enable` int(11) DEFAULT '1' COMMENT '是否可用',
  `operate_func` varchar(450) DEFAULT '' COMMENT '操作调用方法',
  `area_type_aid` int(11) DEFAULT '116001016' COMMENT '所在区域',
  `init_type_did` varchar(45) DEFAULT '469916150004' COMMENT '初始化类型',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_operate`
--

LOCK TABLES `zw_operate` WRITE;
/*!40000 ALTER TABLE `zw_operate` DISABLE KEYS */;
INSERT INTO `zw_operate` VALUES (95,'f9981164f8c011e29b7a000c298b20fc','添加',116015012,'page_chart_v_zw_view','page_add_v_zw_view',1,'',116001020,'469916151601'),(96,'f99848c8f8c011e29b7a000c298b20fc','修改',116015008,'page_chart_v_zw_view','page_edit_v_zw_view?zw_f_uid=[@this$uid]',1,'',116001003,'469916151605'),(97,'f998697af8c011e29b7a000c298b20fc','删除',116015010,'page_chart_v_zw_view','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(98,'f9987f1ef8c011e29b7a000c298b20fc','查询',116015010,'page_chart_v_zw_view','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(99,'f998a034f8c011e29b7a000c298b20fc','提交新增',116015010,'page_add_v_zw_view','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(100,'f998c0bef8c011e29b7a000c298b20fc','提交修改',116015010,'page_edit_v_zw_view','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(101,'f9991ebaf8c011e29b7a000c298b20fc','添加',116015012,'page_chart_v_zw_field','page_add_v_zw_field',1,'',116001020,'469916151601'),(102,'f9994052f8c011e29b7a000c298b20fc','修改',116015012,'page_chart_v_zw_field','page_edit_v_zw_field?zw_f_uid=[@this$uid]',1,'',116001003,'469916151605'),(103,'f9995fe2f8c011e29b7a000c298b20fc','删除',116015010,'page_chart_v_zw_field','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(104,'f9998076f8c011e29b7a000c298b20fc','查询',116015010,'page_chart_v_zw_field','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(105,'f9999f5cf8c011e29b7a000c298b20fc','提交新增',116015010,'page_add_v_zw_field','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(106,'f999b51ef8c011e29b7a000c298b20fc','提交修改',116015010,'page_edit_v_zw_field','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(107,'f99a123ef8c011e29b7a000c298b20fc','添加',116015012,'page_chart_v_cc_createtask','page_add_v_cc_createtask',1,'',116001020,'469916151601'),(108,'f99a3d22f8c011e29b7a000c298b20fc','修改',116015012,'page_chart_v_cc_createtask','page_chart_v_cc_createtask',0,'',116001003,'469916151605'),(109,'f99a5366f8c011e29b7a000c298b20fc','删除',116015010,'page_chart_v_cc_createtask','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(110,'f99a73e6f8c011e29b7a000c298b20fc','查询',116015010,'page_chart_v_cc_createtask','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(111,'f99a960af8c011e29b7a000c298b20fc','提交新增',116015010,'page_add_v_cc_createtask','zapjs.zw.func_add(this)',1,'com.srnpr.cardcenter.webfunc.CreateCard',116001016,'469916151901'),(112,'f99ab73ef8c011e29b7a000c298b20fc','提交修改',116015010,'page_edit_v_cc_createtask','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(113,'f99ab73ef8c011e29b7a000c298b20fg','登录',116015010,'cardcenter_index_login','zapjs.zw.func_add(this)',1,'com.srnpr.cardcenter.webfunc.FuncLogin',116001016,'469916150004'),(114,'fa6cbb16fa7411e29b7a000c298b20fc','添加',116015012,'page_chart_v_cc_cardinfo','page_add_v_cc_cardinfo',1,'',116001020,'469916151601'),(115,'fa6ce0befa7411e29b7a000c298b20fc','修改',116015012,'page_chart_v_cc_cardinfo','page_chart_v_cc_cardinfo',0,'',116001003,'469916151605'),(116,'fa6d0328fa7411e29b7a000c298b20fc','删除',116015010,'page_chart_v_cc_cardinfo','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(117,'fa6d2556fa7411e29b7a000c298b20fc','查询',116015010,'page_chart_v_cc_cardinfo','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(118,'fa6d4874fa7411e29b7a000c298b20fc','提交新增',116015010,'page_add_v_cc_cardinfo','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(119,'fa6d6b7efa7411e29b7a000c298b20fc','提交修改',116015010,'page_edit_v_cc_cardinfo','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(120,'78d95f82fb2311e2ac71000c298b20fc','添加',116015012,'page_chart_v_cc_sendtask','page_add_v_cc_sendtask',1,'',116001020,'469916151601'),(121,'78d99038fb2311e2ac71000c298b20fc','修改',116015012,'page_chart_v_cc_sendtask','page_chart_v_cc_sendtask',0,'',116001003,'469916151605'),(122,'78d9e880fb2311e2ac71000c298b20fc','删除',116015010,'page_chart_v_cc_sendtask','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(123,'78da1dc8fb2311e2ac71000c298b20fc','查询',116015010,'page_chart_v_cc_sendtask','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(124,'78da4780fb2311e2ac71000c298b20fc','提交新增',116015010,'page_add_v_cc_sendtask','zapjs.zw.func_add(this)',1,'com.srnpr.cardcenter.webfunc.SendCard',116001016,'469916151901'),(125,'78da9f96fb2311e2ac71000c298b20fc','提交修改',116015010,'page_edit_v_cc_sendtask','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(126,'2374981afd6f11e2ac71000c298b20fc','添加',116015012,'page_chart_v_cc_userinfo','page_add_v_cc_userinfo',1,'',116001020,'469916151601'),(127,'2374d3c0fd6f11e2ac71000c298b20fc','修改',116015008,'page_chart_v_cc_userinfo','page_edit_v_cc_userinfo?zw_f_uid=[@this$uid]',1,'',116001003,'469916151605'),(128,'23751c04fd6f11e2ac71000c298b20fc','删除',116015010,'page_chart_v_cc_userinfo','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(129,'23755ef8fd6f11e2ac71000c298b20fc','查询',116015010,'page_chart_v_cc_userinfo','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(130,'2375a70afd6f11e2ac71000c298b20fc','提交新增',116015010,'page_add_v_cc_userinfo','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(131,'2375d860fd6f11e2ac71000c298b20fc','提交修改',116015010,'page_edit_v_cc_userinfo','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(132,'8413c7e8fda811e2ac71000c298b20fc','添加',116015012,'page_chart_v_cc_activetask','page_add_v_cc_activetask',1,'',116001020,'469916151601'),(133,'841402bcfda811e2ac71000c298b20fc','修改',116015012,'page_chart_v_cc_activetask','page_chart_v_cc_activetask',0,'',116001003,'469916151605'),(134,'84142fe4fda811e2ac71000c298b20fc','删除',116015010,'page_chart_v_cc_activetask','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(135,'84145d02fda811e2ac71000c298b20fc','查询',116015010,'page_chart_v_cc_activetask','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(136,'84148c64fda811e2ac71000c298b20fc','提交新增',116015010,'page_add_v_cc_activetask','zapjs.zw.func_add(this)',1,'com.srnpr.cardcenter.webfunc.ActiveCard',116001016,'469916151901'),(137,'8414bb76fda811e2ac71000c298b20fc','提交修改',116015010,'page_edit_v_cc_activetask','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(138,'b0564d52fe4911e2ac71000c298b20fc','添加',116015012,'page_chart_v_export_cc_cardinfo','page_add_v_export_cc_cardinfo',1,'',116001020,'469916151601'),(139,'b0568d4efe4911e2ac71000c298b20fc','修改',116015012,'page_chart_v_export_cc_cardinfo','page_chart_v_export_cc_cardinfo',0,'',116001003,'469916151605'),(140,'b056c14cfe4911e2ac71000c298b20fc','删除',116015010,'page_chart_v_export_cc_cardinfo','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(141,'b056ffa4fe4911e2ac71000c298b20fc','查询',116015010,'page_chart_v_export_cc_cardinfo','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(142,'b057351efe4911e2ac71000c298b20fc','提交新增',116015010,'page_add_v_export_cc_cardinfo','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(143,'b0576bbafe4911e2ac71000c298b20fc','提交修改',116015010,'page_edit_v_export_cc_cardinfo','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(144,'f99a960af8c011e29b7a000c298b20fx','导出',116015012,'page_chart_v_cc_createtask','../export/page_chart_v_export_cc_cardinfo?zw_f_cc_taskid=[@this$zid]',1,'',116001003,'469916150004'),(154,'30ea740200b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_zw_view','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(155,'30eb5b1000b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_zw_field','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(156,'30ec225c00b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_cc_createtask','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(157,'30ed0a3c00b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_cc_cardinfo','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(158,'30ede0ce00b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_cc_sendtask','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(159,'30eebd3200b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_cc_userinfo','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(160,'30efab6600b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_cc_activetask','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(161,'30f0b9ac00b611e3bb73000c298b20fc','导出',116015010,'page_chart_v_export_cc_cardinfo','zapjs.zw.func_export(this)',1,'',116001009,'469916150503'),(162,'f99848c8f8c011e29b7a000c298b20fv','视图字段',116015012,'page_chart_v_zw_view','page_chart_v_zw_field?zw_f_view_code=[@this$view_code]&zw_p_size=0',1,'',116001003,'469916151604'),(163,'4b2565de033111e3bb73000c298b20fc','提交新增',116015010,'page_add_v_zw_operate','zapjs.zw.func_add(this)',0,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(164,'4b256764033111e3bb73000c298b20fc','添加',116015008,'page_chart_v_zw_operate','page_add_v_zw_operate',0,'',116001020,'469916151601'),(165,'4b256804033111e3bb73000c298b20fc','修改',116015008,'page_chart_v_zw_operate','page_edit_v_zw_operate?zw_f_uid=[@this$uid]',1,'',116001003,'469916151605'),(166,'4b256886033111e3bb73000c298b20fc','删除',116015010,'page_chart_v_zw_operate','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(167,'4b2568fe033111e3bb73000c298b20fc','查询',116015010,'page_chart_v_zw_operate','zapjs.zw.func_inquire(this)',0,'',116001009,'469916151609'),(168,'4b256976033111e3bb73000c298b20fc','导出',116015010,'page_chart_v_zw_operate','zapjs.zw.func_export(this)',0,'',116001009,'469916150503'),(169,'4b2569da033111e3bb73000c298b20fc','提交修改',116015010,'page_edit_v_zw_operate','zapjs.zw.func_edit(this)',1,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(170,'56007a10033211e3bb73000c298b20fc','提交新增',116015010,'page_add_v_zw_page','zapjs.zw.func_add(this)',1,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(171,'56007b50033211e3bb73000c298b20fc','添加',116015008,'page_chart_v_zw_page','page_add_v_zw_page',0,'',116001020,'469916151601'),(172,'56007bf0033211e3bb73000c298b20fc','修改',116015008,'page_chart_v_zw_page','page_edit_v_zw_page?zw_f_uid=[@this$uid]',0,'',116001003,'469916151605'),(173,'56007c72033211e3bb73000c298b20fc','删除',116015010,'page_chart_v_zw_page','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(174,'56007cea033211e3bb73000c298b20fc','查询',116015010,'page_chart_v_zw_page','zapjs.zw.func_inquire(this)',0,'',116001009,'469916151609'),(175,'56007d62033211e3bb73000c298b20fc','导出',116015010,'page_chart_v_zw_page','zapjs.zw.func_export(this)',0,'',116001009,'469916150503'),(176,'56007dd0033211e3bb73000c298b20fc','提交修改',116015010,'page_edit_v_zw_page','zapjs.zw.func_edit(this)',0,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905'),(177,'f99848c8f8c011e29b7a000c298b20fu','相关页面',116015012,'page_chart_v_zw_view','page_chart_v_zw_page?zw_f_view_code=[@this$view_code]&zw_p_size=0',1,'',116001003,'469916151604'),(178,'f99848c8f8c011e29b7a000c298b20x','刷新缓存',116015010,'page_chart_v_zw_view','zapjs.zw.func_do(\'f99848c8f8c011e29b7a000c298b20x\')',1,'com.srnpr.zapweb.webfunc.FuncRefreshCache',116001016,'469916151604'),(179,'f99848c8f8c011e29b7a000c298b20fm','页面按钮',116015012,'page_chart_v_zw_page','page_chart_v_zw_operate?zw_f_page_code=[@this$page_code]&zw_p_size=0',1,'',116001003,'469916151604'),(180,'4d2571da03c711e3bb73000c298b20fc','提交新增',116015010,'page_add_v_cc_reportformonth','zapjs.zw.func_add(this)',0,'com.srnpr.zapweb.webfunc.FuncAdd',116001016,'469916151901'),(181,'4d25736a03c711e3bb73000c298b20fc','添加',116015008,'page_chart_v_cc_reportformonth','page_add_v_cc_reportformonth',0,'',116001020,'469916151601'),(182,'4d25741403c711e3bb73000c298b20fc','修改',116015008,'page_chart_v_cc_reportformonth','page_edit_v_cc_reportformonth?zw_f_uid=[@this$uid]',0,'',116001003,'469916151605'),(183,'4d2574aa03c711e3bb73000c298b20fc','删除',116015010,'page_chart_v_cc_reportformonth','zapjs.zw.func_delete(this)',0,'com.srnpr.zapweb.webfunc.FuncDelete',116001003,'469916151604'),(184,'4d25753603c711e3bb73000c298b20fc','查询',116015010,'page_chart_v_cc_reportformonth','zapjs.zw.func_inquire(this)',1,'',116001009,'469916151609'),(185,'4d2575b803c711e3bb73000c298b20fc','选择系列',116015010,'page_chart_v_cc_reportformonth','selectChartSeris()',1,'',116001009,'469916150503'),(186,'4d25763003c711e3bb73000c298b20fc','提交修改',116015010,'page_edit_v_cc_reportformonth','zapjs.zw.func_edit(this)',0,'com.srnpr.zapweb.webfunc.FuncEdit',116001016,'469916151905');
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
  `view_type_aid` int(11) DEFAULT '0' COMMENT '视图类型',
  `page_group` varchar(450) DEFAULT '' COMMENT '页面组',
  `flag_enable` int(11) DEFAULT '1' COMMENT '可用标记',
  `project_aid` int(11) DEFAULT '101002677' COMMENT '所属项目',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `page_code_UNIQUE` (`page_code`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_page`
--

LOCK TABLES `zw_page` WRITE;
/*!40000 ALTER TABLE `zw_page` DISABLE KEYS */;
INSERT INTO `zw_page` VALUES (48,'0247a1f4f8ca11e29b7a000c298b20fc','page_chart_v_zw_view','系统视图-列表','../zappage/chart','v_zw_view',116016003,116022003,'grouppage_v_zw_view',1,101002677),(49,'0247e6f0f8ca11e29b7a000c298b20fc','page_add_v_zw_view','系统视图-新增','../zappage/add','v_zw_view',116016001,116022001,'grouppage_v_zw_view',1,101002677),(50,'02480b4ef8ca11e29b7a000c298b20fc','page_edit_v_zw_view','系统视图-修改','../zappage/edit','v_zw_view',116016005,116022005,'grouppage_v_zw_view',1,101002677),(51,'02488790f8ca11e29b7a000c298b20fc','page_chart_v_zw_field','字段视图-列表','../zappage/chart','v_zw_field',116016003,116022003,'grouppage_v_zw_field',1,101002677),(52,'0248accaf8ca11e29b7a000c298b20fc','page_add_v_zw_field','字段视图-新增','../zappage/add','v_zw_field',116016001,116022001,'grouppage_v_zw_field',1,101002677),(53,'0248d038f8ca11e29b7a000c298b20fc','page_edit_v_zw_field','字段视图-修改','../zappage/edit','v_zw_field',116016005,116022005,'grouppage_v_zw_field',1,101002677),(54,'02494f68f8ca11e29b7a000c298b20fc','page_chart_v_cc_createtask','购物卡创建-列表','../zappage/chart','v_cc_createtask',116016003,116022003,'grouppage_v_cc_createtask',1,101002081),(55,'02498546f8ca11e29b7a000c298b20fc','page_add_v_cc_createtask','购物卡创建-新增','../zappage/add','v_cc_createtask',116016001,116022001,'grouppage_v_cc_createtask',1,101002081),(56,'02499c20f8ca11e29b7a000c298b20fc','page_edit_v_cc_createtask','购物卡创建-修改','../zappage/edit','v_cc_createtask',116016005,116022005,'grouppage_v_cc_createtask',1,101002081),(57,'02499c20f8ca11e29b7a000c298b20fx','cardcenter_index_login','登录','../index/login','',116016109,116022014,'grouppage_cardcenter_index',1,101002081),(58,'02499c20f8ca11e29b7a000c298b20fy','cardcenter_page_center','购物卡中心','../page/center','',116016116,116022014,'grouppage_cardcenter_cardcenter_page',1,101002081),(59,'fa6c5a68fa7411e29b7a000c298b20fc','page_chart_v_cc_cardinfo','购物卡信息-列表','../zappage/chart','v_cc_cardinfo',116016003,116022003,'grouppage_v_cc_cardinfo',1,101002081),(60,'fa6c7b7efa7411e29b7a000c298b20fc','page_add_v_cc_cardinfo','购物卡信息-新增','../zappage/add','v_cc_cardinfo',116016001,116022001,'grouppage_v_cc_cardinfo',1,101002081),(61,'fa6c9230fa7411e29b7a000c298b20fc','page_edit_v_cc_cardinfo','购物卡信息-修改','../zappage/edit','v_cc_cardinfo',116016005,116022005,'grouppage_v_cc_cardinfo',1,101002081),(62,'78d8702cfb2311e2ac71000c298b20fc','page_chart_v_cc_sendtask','购物卡发放-列表','../zappage/chart','v_cc_sendtask',116016003,116022003,'grouppage_v_cc_sendtask',1,101002081),(63,'78d8ef16fb2311e2ac71000c298b20fc','page_add_v_cc_sendtask','购物卡发放-新增','../zappage/add','v_cc_sendtask',116016001,116022001,'grouppage_v_cc_sendtask',1,101002081),(64,'78d9127afb2311e2ac71000c298b20fc','page_edit_v_cc_sendtask','购物卡发放-修改','../zappage/edit','v_cc_sendtask',116016005,116022005,'grouppage_v_cc_sendtask',1,101002081),(65,'2373cfdefd6f11e2ac71000c298b20fc','page_chart_v_cc_userinfo','用户管理-列表','../zappage/chart','v_cc_userinfo',116016003,116022003,'grouppage_v_cc_userinfo',1,101002081),(66,'23741d40fd6f11e2ac71000c298b20fc','page_add_v_cc_userinfo','用户管理-新增','../zappage/add','v_cc_userinfo',116016001,116022001,'grouppage_v_cc_userinfo',1,101002081),(67,'23746548fd6f11e2ac71000c298b20fc','page_edit_v_cc_userinfo','用户管理-修改','../zappage/edit','v_cc_userinfo',116016005,116022005,'grouppage_v_cc_userinfo',1,101002081),(68,'8413435efda811e2ac71000c298b20fc','page_chart_v_cc_activetask','购物卡激活-列表','../zappage/chart','v_cc_activetask',116016003,116022003,'grouppage_v_cc_activetask',1,101002081),(69,'84136c62fda811e2ac71000c298b20fc','page_add_v_cc_activetask','购物卡激活-新增','../zappage/add','v_cc_activetask',116016001,116022001,'grouppage_v_cc_activetask',1,101002081),(70,'84139534fda811e2ac71000c298b20fc','page_edit_v_cc_activetask','购物卡激活-修改','../zappage/edit','v_cc_activetask',116016005,116022005,'grouppage_v_cc_activetask',1,101002081),(71,'b055c544fe4911e2ac71000c298b20fc','page_chart_v_export_cc_cardinfo','购物卡导出-列表','../zappage/chart','v_export_cc_cardinfo',116016003,116022003,'grouppage_v_export_cc_cardinfo',1,101002081),(72,'b055f1b8fe4911e2ac71000c298b20fc','page_add_v_export_cc_cardinfo','购物卡导出-新增','../zappage/add','v_export_cc_cardinfo',116016001,116022001,'grouppage_v_export_cc_cardinfo',1,101002081),(73,'b0561828fe4911e2ac71000c298b20fc','page_edit_v_export_cc_cardinfo','购物卡导出-修改','../zappage/edit','v_export_cc_cardinfo',116016005,116022005,'grouppage_v_export_cc_cardinfo',1,101002081),(74,'4b24a022033111e3bb73000c298b20fc','page_add_v_zw_operate','按钮视图-新增','../zappage/add','v_zw_operate',116016001,116022001,'grouppage_v_zw_operate',1,101002677),(75,'4b24a2d4033111e3bb73000c298b20fc','page_chart_v_zw_operate','按钮视图-列表','../zappage/chart','v_zw_operate',116016003,116022003,'grouppage_v_zw_operate',1,101002677),(76,'4b24a3ec033111e3bb73000c298b20fc','page_edit_v_zw_operate','按钮视图-修改','../zappage/edit','v_zw_operate',116016005,116022005,'grouppage_v_zw_operate',1,101002677),(77,'55ffae32033211e3bb73000c298b20fc','page_add_v_zw_page','页面视图-新增','../zappage/add','v_zw_page',116016001,116022001,'grouppage_v_zw_page',1,101002677),(78,'55ffb01c033211e3bb73000c298b20fc','page_chart_v_zw_page','页面视图-列表','../zappage/chart','v_zw_page',116016003,116022003,'grouppage_v_zw_page',1,101002677),(79,'55ffb134033211e3bb73000c298b20fc','page_edit_v_zw_page','页面视图-修改','../zappage/edit','v_zw_page',116016005,116022005,'grouppage_v_zw_page',1,101002677),(80,'4d248f4a03c711e3bb73000c298b20fc','page_add_v_cc_reportformonth','礼品卡月报-新增','../zappage/add','v_cc_reportformonth',116016001,116022001,'grouppage_v_cc_reportformonth',1,101002081),(81,'4d2491de03c711e3bb73000c298b20fc','page_chart_v_cc_reportformonth','礼品卡月报-报表','../zappage/report','v_cc_reportformonth',116016003,116022003,'grouppage_v_cc_reportformonth',1,101002081),(82,'4d24930003c711e3bb73000c298b20fc','page_edit_v_cc_reportformonth','礼品卡月报-修改','../zappage/edit','v_cc_reportformonth',116016005,116022005,'grouppage_v_cc_reportformonth',1,101002081);
/*!40000 ALTER TABLE `zw_page` ENABLE KEYS */;
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
  `field_sort` varchar(45) DEFAULT '' COMMENT '排序字段',
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数据源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_source`
--

LOCK TABLES `zw_source` WRITE;
/*!40000 ALTER TABLE `zw_source` DISABLE KEYS */;
INSERT INTO `zw_source` VALUES (1,'1','source_cc_moneytype','购物卡类型','cc_moneytype','money_value','money_value','','','money_value'),(2,'2','source_zd_abstract','系统参数','zd_abstract','abstract_remark','abstract_aids','abstract_aids={0}','parent_aid={0}',''),(3,'3','source_cc_agentuser','代理商','cc_agentuser','agent_name','agent_code','agent_code={0}','',''),(4,'','source_zw_define','zapweb定义','zw_define','define_note','define_dids','define_dids={0}','parent_did={0}','');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='视图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_view`
--

LOCK TABLES `zw_view` WRITE;
/*!40000 ALTER TABLE `zw_view` DISABLE KEYS */;
INSERT INTO `zw_view` VALUES (1,'1','v_zw_view','系统视图','zw_view',101002677),(2,'2','v_zw_field','字段视图','zw_field',101002677),(3,'3','v_cc_createtask','购物卡','cc_createtask',101002081),(4,'4','v_cc_cardinfo','购物卡信息','cc_cardinfo',101002081),(5,'5','v_cc_sendtask','购物卡发送','cc_sendtask',101002081),(6,'6','v_cc_userinfo','用户管理','cc_userinfo',101002081),(7,'7','v_cc_activetask','购物卡激活','cc_activetask',101002081),(8,'8','v_export_cc_cardinfo','购物卡导出','cc_cardinfo',101002081),(9,'9','v_zw_operate','按钮视图','zw_operate',101002677),(10,'10','v_zw_page','页面视图','zw_page',101002677),(11,'11','v_cc_reportformonth','礼品卡月报','cc_reportformonth',101002081);
/*!40000 ALTER TABLE `zw_view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zw_webcode`
--

DROP TABLE IF EXISTS `zw_webcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zw_webcode` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `code_start` varchar(100) DEFAULT '' COMMENT '编码起始',
  `date_apply` char(6) DEFAULT '' COMMENT '日期参数',
  `min_number` int(11) DEFAULT '100000' COMMENT '最小数字',
  `now_number` int(11) DEFAULT '100000' COMMENT '当前数字',
  `code_note` varchar(45) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `code_start_UNIQUE` (`code_start`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='系统编码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zw_webcode`
--

LOCK TABLES `zw_webcode` WRITE;
/*!40000 ALTER TABLE `zw_webcode` DISABLE KEYS */;
INSERT INTO `zw_webcode` VALUES (14,'75e615befb4a11e2ac71000c298b20fc','test','130802',100000,100033,''),(15,'77eaacbcfb5411e2ac71000c298b20fc','FS','130802',100000,100007,''),(16,'cbd34e62fb5711e2ac71000c298b20fc','FF','130802',100000,100066,''),(17,'9490c998fb5c11e2ac71000c298b20fc','aabbcc','130802',100000,100059,''),(18,'419785c6fd6c11e2ac71000c298b20fc','CJ','130805',100000,100064,''),(19,'1f4a34cafdab11e2ac71000c298b20fc','JH','130805',100000,100042,''),(20,'b4018c38fe6b11e2bb73000c298b20fc','US','130806',100000,100008,'');
/*!40000 ALTER TABLE `zw_webcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'zapdata'
--
/*!50003 DROP PROCEDURE IF EXISTS `proc_zd_allfield` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`srnpr`@`%`*/ /*!50003 PROCEDURE `proc_zd_allfield`()
begin


#delete from zapdata.zd_tables;
#delete from zapdata.zd_column;


########  初始化表
insert into zapdata.zd_tables (`uid`,`server_name`,`table_name`,`table_remark`)
select replace(uuid(),'-',''),TABLE_SCHEMA,TABLE_NAME,TABLE_COMMENT from information_schema.TABLES where TABLE_SCHEMA in
(select server_name from zapdata.zd_server)
and TABLE_NAME not in(select table_name from zapdata.zd_tables);




insert into zapdata.zd_column
(`uid`,`server_name`,`table_name`,`column_name`,`null_able_aid`,`column_type_aid`,`column_length`,`length_scale`,`column_note`,`column_sort`)
SELECT 
replace(uuid(),'-',''),
a.TABLE_SCHEMA server_name,
a.TABLE_NAME table_name,
a.column_name column_name,
(case a.IS_Nullable when 'YES' then 104014001 else 104014002 end)  null_able_aid,
(select b.abstract_aids from zapdata.zd_abstract b where rtrim(b.abstract_value)=rtrim(a.Data_type) and left(b.abstract_aids,6)=104003) column_type_aid,
ifnull(a.CHARACTER_MAXIMUM_LENGTH,ifnull(NUMERIC_PRECISION,0)) column_length,
ifnull(a.NUMERIC_SCALE,0) length_scale,
a.COLUMN_COMMENT column_note,
a.ORDINAL_POSITION column_sort

FROM information_schema.COLUMNS a
where a.TABLE_SCHEMA in
(select server_name from zapdata.zd_server)
and concat(a.TABLE_NAME,a.column_name) not in
(select  concat(table_name,column_name)  from  zapdata.zd_column );


end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_zw_allview` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`srnpr`@`%`*/ /*!50003 PROCEDURE `proc_zw_allview`()
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
/*!50003 DROP PROCEDURE IF EXISTS `proc_zw_getcode` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`srnpr`@`%`*/ /*!50003 PROCEDURE `proc_zw_getcode`(in p_code_start varchar(100))
begin

declare p_date char(6);
declare p_now char(6);
declare p_return varchar(30);

declare p_nowno int;

set p_now=DATE_FORMAt(now(), '%y%m%d') ;


#查询系统时间
set p_date=ifnull((select a.date_apply from zw_webcode a where  a.code_start=p_code_start),'');

if(p_date='')
THEN 
	INSERT INTO `zw_webcode`
	(
	`uid`,
	`code_start`,
	`date_apply`,
	`min_number`,
	`now_number`)
	VALUES
	(
		replace(uuid(),'-',''),
		p_code_start,
		p_now,
		100000,
		100000
	);
	set p_date=p_now;

end if;

 if(p_date!=p_now) then

 update zw_webcode set now_number=min_number where zid=p_code_start;

end if;

start transaction; 

set p_return=(select now_number from zw_webcode zwwc  where zwwc.code_start=p_code_start for update);
set p_return=p_return+1;
update zw_webcode set now_number=p_return where code_start=p_code_start;

commit;

select concat(p_code_start,p_now,p_return) as webcode;



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
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`srnpr`@`%`*/ /*!50003 PROCEDURE `proc_zw_initview`(in p_view_code varchar(100))
begin


declare p_exit int;
set p_exit=(select count(1) from zw_field where view_code=p_view_code);





INSERT INTO `zw_field`
(
`uid`,
`view_code`,
`field_name`,
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
,zdc.column_name as field_name
,zdc.column_name as column_name
,zdc.column_note as field_note
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end)  as sort_add
,(case zdc.column_name when 'zid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end) as sort_edit
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end) as sort_chart
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end) as sort_book
#,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_inquery
,0 as sort_inquery
,(case zdc.column_name when 'zid' then 104005008 when 'uid' then 104005008 else 104005009 end) as field_type_aid
from zapdata.zd_column zdc left join zw_view zwv
on zdc.table_name=zwv.table_name
where concat(zwv.view_code,zdc.column_name)
not in(select concat(view_code,column_name) from zw_field)
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
`page_group`,
`project_aid`)
select replace(uuid(),'-','') as uid
,concat(zwd.define_two,zwv.view_code) as page_code
,concat(zwv.view_name,zwd.define_one) as page_name
,zwd.define_three as page_template
,zwv.view_code as view_code
,zwd.define_four as page_type_aid
,zwd.define_five as view_type_aid
,concat('grouppage_',zwv.view_code) as page_code
,project_aid
from zw_view zwv
join zw_define zwd
on zwd.define_dids in('469916161601','469916161603','469916161605')
where

(select count(1) from zapdata.zw_page where page_code=concat(zwd.define_two,zwv.view_code))=0 and  
zwv.view_code=p_view_code;






INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`,
`init_type_did`)
select
replace(uuid(),'-','') as uid
,zwd.define_note as operate_name
,zwd.define_one as operate_type_aid
,zwp.page_code as page_code
,
	(case zwd.define_dids 
	when 469916151601 then (select page_code from zw_page where page_type_aid=116016001 and view_code=zwp.view_code) 
	when 469916151605 then concat((select page_code from zw_page where page_type_aid=116016005 and view_code=zwp.view_code),'?zw_f_uid=[@this$uid]')
	else zwd.define_five end)
 as operate_link
,'0' as flag_enable
,zwd.define_four as operate_func
,zwd.define_three as area_type_aid
,zwd.define_dids as init_type_did

from zw_page zwp 
 join zw_define zwd
on zwp.page_type_aid=zwd.define_two

where

(select count(1) from zw_operate zwo where concat(zwo.init_type_did,zwo.page_code)=concat(zwd.define_dids,zwp.page_code))=0 
and zwp.view_code=p_view_code;
























































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

-- Dump completed on 2013-08-13 16:02:49
