/*
Navicat MySQL Data Transfer

Source Server         : mysql_127.0.0.1:3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : jorian_framework

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2019-05-13 16:43:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` varchar(32) NOT NULL COMMENT '文件md5',
  `contentType` varchar(128) NOT NULL,
  `size` int(11) NOT NULL,
  `path` varchar(255) NOT NULL COMMENT '物理路径',
  `url` varchar(1024) NOT NULL,
  `type` int(1) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of file_info
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR_NAME` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `CRON_EXPRESSION` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `ENTRY_ID` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `JOB_GROUP` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `LOCK_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('adminQuartzScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('adminQuartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('adminQuartzScheduler', '8P2SVHKYT6ML1AX1555401160408', '1555402717550', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(30) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `ajax` int(1) DEFAULT NULL,
  `api` varchar(250) DEFAULT NULL,
  `params` varchar(500) DEFAULT NULL,
  `http_method` varchar(20) DEFAULT NULL,
  `class_method` varchar(100) DEFAULT NULL,
  `action_name` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1125990910630989826', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125990538713665538', '[\"1125990538713665538\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:08:47', null);
INSERT INTO `sys_log` VALUES ('1125990934748237826', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125675154978537474', '[\"1125675154978537474\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:08:53', null);
INSERT INTO `sys_log` VALUES ('1125990942931324930', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125675155024674817', '[\"1125675155024674817\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:08:55', null);
INSERT INTO `sys_log` VALUES ('1125990947893186561', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125675163362951170', '[\"1125675163362951170\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:08:56', null);
INSERT INTO `sys_log` VALUES ('1125990953496776705', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125675177325789185', '[\"1125675177325789185\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:08:57', null);
INSERT INTO `sys_log` VALUES ('1125675215158411265', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-07 16:14:19', null);
INSERT INTO `sys_log` VALUES ('1125675292300050434', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-07 16:14:38', null);
INSERT INTO `sys_log` VALUES ('1125675292316827649', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-07 16:14:38', null);
INSERT INTO `sys_log` VALUES ('1125675304681635841', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 16:14:41', null);
INSERT INTO `sys_log` VALUES ('1125675359564103682', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/system/user/index\",\"createTime\":\"2019-04-25 17:41:52\",\"icon\":\"user\",\"id\":\"1121348591713665026\",\"isVerify\":true,\"name\":\"User\",\"path\":\"/user\",\"permission\":\"[system:user]\",\"pid\":\"1121348570280771586\",\"sort\":1,\"title\":\"用户管理\",\"type\":1,\"updateTime\":\"2019-04-25 17:41:52\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-07 16:14:54', null);
INSERT INTO `sys_log` VALUES ('1125991017082425346', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125675359891259393', '[\"1125675359891259393\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:09:13', null);
INSERT INTO `sys_log` VALUES ('1125676242100191233', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:18:24', null);
INSERT INTO `sys_log` VALUES ('1125991014544871426', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125676291051913217', '[\"1125676291051913217\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:09:12', null);
INSERT INTO `sys_log` VALUES ('1125991006256926721', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125676291198713857', '[\"1125676291198713857\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:09:10', null);
INSERT INTO `sys_log` VALUES ('1125676303693545473', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:18:39', null);
INSERT INTO `sys_log` VALUES ('1125990988494049282', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125676773002608641', '[\"1125676773002608641\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:09:06', null);
INSERT INTO `sys_log` VALUES ('1125990993757900801', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125678915646021633', '[\"1125678915646021633\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:09:07', null);
INSERT INTO `sys_log` VALUES ('1125991002427527170', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125680431870140418', '[\"1125680431870140418\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:09:09', null);
INSERT INTO `sys_log` VALUES ('1125991011734687746', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125680867335364609', '[\"1125680867335364609\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:09:11', null);
INSERT INTO `sys_log` VALUES ('1125681216926408706', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:38:10', null);
INSERT INTO `sys_log` VALUES ('1125681402587275265', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:38:55', null);
INSERT INTO `sys_log` VALUES ('1125683347469262849', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:46:38', null);
INSERT INTO `sys_log` VALUES ('1125683398321004546', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"游客\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:46:51', null);
INSERT INTO `sys_log` VALUES ('1125683445435621377', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:47:02', null);
INSERT INTO `sys_log` VALUES ('1125685619532128257', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:55:40', null);
INSERT INTO `sys_log` VALUES ('1125686204209717250', '游客', '匿名', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-07 16:57:59', null);
INSERT INTO `sys_log` VALUES ('1125686204348129282', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 16:58:00', null);
INSERT INTO `sys_log` VALUES ('1125686212304723969', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:58:01', null);
INSERT INTO `sys_log` VALUES ('1125686485769150465', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:59:07', null);
INSERT INTO `sys_log` VALUES ('1125686680158363650', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 16:59:53', null);
INSERT INTO `sys_log` VALUES ('1125687759478669315', '游客', '匿名', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-07 17:04:10', null);
INSERT INTO `sys_log` VALUES ('1125687759478669314', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:04:10', null);
INSERT INTO `sys_log` VALUES ('1125687762616008706', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:04:11', null);
INSERT INTO `sys_log` VALUES ('1125687769083625474', '游客', '匿名', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-07 17:04:13', null);
INSERT INTO `sys_log` VALUES ('1125687769217843201', '游客', '匿名', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-07 17:04:13', null);
INSERT INTO `sys_log` VALUES ('1125687987527172097', '游客', '匿名', '127.0.0.1', '1', '/system/log/1125687917989806082', '[\"1125687917989806082\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-07 17:05:05', null);
INSERT INTO `sys_log` VALUES ('1125687935249367041', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":2,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 17:04:52', null);
INSERT INTO `sys_log` VALUES ('1125688933804097538', '游客', '匿名', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-07 17:08:50', null);
INSERT INTO `sys_log` VALUES ('1125688933955092481', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:08:50', null);
INSERT INTO `sys_log` VALUES ('1125688939051171842', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 17:08:52', null);
INSERT INTO `sys_log` VALUES ('1125689180458532865', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:09:49', null);
INSERT INTO `sys_log` VALUES ('1125689261928693762', '游客', '匿名', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-07 17:10:08', null);
INSERT INTO `sys_log` VALUES ('1125689535938379778', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:11:14', null);
INSERT INTO `sys_log` VALUES ('1125691554413625346', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:19:15', null);
INSERT INTO `sys_log` VALUES ('1125692467106758657', '游客', '匿名', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:22:53', null);
INSERT INTO `sys_log` VALUES ('1125695209753755650', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-07 17:33:47', null);
INSERT INTO `sys_log` VALUES ('1125695211796381698', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:33:47', null);
INSERT INTO `sys_log` VALUES ('1125695228732981249', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-07 17:33:51', null);
INSERT INTO `sys_log` VALUES ('1125695228795895810', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:33:51', null);
INSERT INTO `sys_log` VALUES ('1125695239193575425', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-07 17:33:54', null);
INSERT INTO `sys_log` VALUES ('1125929272661954561', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 09:03:51', null);
INSERT INTO `sys_log` VALUES ('1125929293188878338', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:03:56', null);
INSERT INTO `sys_log` VALUES ('1125929520172027905', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '数据过大，不给予记录', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:04:51', null);
INSERT INTO `sys_log` VALUES ('1125929520364965889', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:04:51', null);
INSERT INTO `sys_log` VALUES ('1125929650774265858', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:05:22', null);
INSERT INTO `sys_log` VALUES ('1125929711562313729', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/system/log/index\",\"createTime\":\"2019-05-07 14:34:31\",\"icon\":\"bug\",\"id\":\"1125650097057189889\",\"isVerify\":true,\"name\":\"Log \",\"path\":\"/log\",\"permission\":\"[system:log]\",\"pid\":\"1121348570280771586\",\"sort\":10005,\"title\":\"日志管理\",\"type\":1,\"updateTime\":\"2019-05-07 14:34:31\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:05:36', null);
INSERT INTO `sys_log` VALUES ('1125929711738474498', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:05:36', null);
INSERT INTO `sys_log` VALUES ('1125929837240438785', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/system/organization/index\",\"createTime\":\"2019-05-07 14:37:22\",\"icon\":\"peoples\",\"id\":\"1125650815872815106\",\"isVerify\":true,\"name\":\"Organization\",\"path\":\"/organization\",\"permission\":\"[system:organization]\",\"pid\":\"1121348570280771586\",\"sort\":10006,\"title\":\"组织机构\",\"type\":1,\"updateTime\":\"2019-05-07 14:37:22\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:06:06', null);
INSERT INTO `sys_log` VALUES ('1125929837471125506', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:06:06', null);
INSERT INTO `sys_log` VALUES ('1125930241978191873', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/article/article-kinds/index\",\"createTime\":\"2019-04-28 15:11:26\",\"icon\":\"document-kinds\",\"id\":\"1122397899180732417\",\"isVerify\":true,\"name\":\"ArticleKinds\",\"path\":\"/kindManage\",\"permission\":\"[kindManage]\",\"pid\":\"1121788789530923010\",\"sort\":51,\"title\":\"文章分类\",\"type\":1,\"updateTime\":\"2019-04-28 15:11:26\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:07:43', null);
INSERT INTO `sys_log` VALUES ('1125930242208878594', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:07:43', null);
INSERT INTO `sys_log` VALUES ('1125930351197868033', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/article/article-manage/index\",\"createTime\":\"2019-04-26 23:10:21\",\"icon\":\"document\",\"id\":\"1121793643787685890\",\"isVerify\":true,\"name\":\"ArticleManage\",\"path\":\"/articleManage\",\"permission\":\"[article:manage]\",\"pid\":\"1121788789530923010\",\"sort\":52,\"title\":\"文章管理\",\"type\":1,\"updateTime\":\"2019-04-26 23:10:21\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:08:09', null);
INSERT INTO `sys_log` VALUES ('1125930351399194625', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:08:09', null);
INSERT INTO `sys_log` VALUES ('1125930426582093825', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/article/article-analysi/index\",\"createTime\":\"2019-05-06 11:02:22\",\"icon\":\"document-analysis1\",\"id\":\"1125234322459983873\",\"isVerify\":true,\"name\":\"ArticleAnalysis\",\"path\":\"/articleAnalysis\",\"permission\":\"[article:articleManage]\",\"pid\":\"1121788789530923010\",\"sort\":2001,\"title\":\"文章分析\",\"type\":1,\"updateTime\":\"2019-05-06 11:02:22\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:08:27', null);
INSERT INTO `sys_log` VALUES ('1125930426754060289', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:08:27', null);
INSERT INTO `sys_log` VALUES ('1125930463563272194', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '数据过大，不给予记录', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:08:35', null);
INSERT INTO `sys_log` VALUES ('1125930463722655746', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:08:35', null);
INSERT INTO `sys_log` VALUES ('1125930565891706881', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '数据过大，不给予记录', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:09:00', null);
INSERT INTO `sys_log` VALUES ('1125930566046896129', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:09:00', null);
INSERT INTO `sys_log` VALUES ('1125930608526807042', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:09:10', null);
INSERT INTO `sys_log` VALUES ('1125930646531395585', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:09:19', null);
INSERT INTO `sys_log` VALUES ('1125930744619388929', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":2,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:09:42', null);
INSERT INTO `sys_log` VALUES ('1125930763036577793', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":3,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:09:47', null);
INSERT INTO `sys_log` VALUES ('1125930839704260610', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":4,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:10:05', null);
INSERT INTO `sys_log` VALUES ('1125931068499349506', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:11:00', null);
INSERT INTO `sys_log` VALUES ('1125931068507738113', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:11:00', null);
INSERT INTO `sys_log` VALUES ('1125931102812950529', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:11:08', null);
INSERT INTO `sys_log` VALUES ('1125931177739997186', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '数据过大，不给予记录', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:11:26', null);
INSERT INTO `sys_log` VALUES ('1125931177920352257', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:11:26', null);
INSERT INTO `sys_log` VALUES ('1125931269217767425', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/article/article-manage/index\",\"createTime\":\"2019-04-26 23:10:21\",\"icon\":\"document-edit\",\"id\":\"1121793643787685890\",\"isVerify\":true,\"name\":\"ArticleManage\",\"path\":\"/articleManage\",\"permission\":\"[article:manage]\",\"pid\":\"1121788789530923010\",\"sort\":52,\"title\":\"文章管理\",\"type\":1,\"updateTime\":\"2019-04-26 23:10:21\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 09:11:48', null);
INSERT INTO `sys_log` VALUES ('1125931269377150977', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:11:48', null);
INSERT INTO `sys_log` VALUES ('1125931285021904897', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:11:51', null);
INSERT INTO `sys_log` VALUES ('1125931399845171201', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:12:19', null);
INSERT INTO `sys_log` VALUES ('1125931637205028865', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:13:15', null);
INSERT INTO `sys_log` VALUES ('1125931637293109249', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 09:13:15', null);
INSERT INTO `sys_log` VALUES ('1125932226609598466', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:15:36', null);
INSERT INTO `sys_log` VALUES ('1125932226630569985', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 09:15:36', null);
INSERT INTO `sys_log` VALUES ('1125932232234160130', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:15:37', null);
INSERT INTO `sys_log` VALUES ('1125932243890130945', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:15:40', null);
INSERT INTO `sys_log` VALUES ('1125932243999182849', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:15:40', null);
INSERT INTO `sys_log` VALUES ('1125932284797177857', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree/1', '[\"1\"]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUserTree()', '查看角色资源', '2019-05-08 09:15:50', null);
INSERT INTO `sys_log` VALUES ('1125932315591757826', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree/2', '[\"2\"]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUserTree()', '查看角色资源', '2019-05-08 09:15:57', null);
INSERT INTO `sys_log` VALUES ('1125932385749880833', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:16:14', null);
INSERT INTO `sys_log` VALUES ('1125932842132099074', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 09:18:03', null);
INSERT INTO `sys_log` VALUES ('1125932843767877633', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:18:03', null);
INSERT INTO `sys_log` VALUES ('1125932872465305602', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:18:10', null);
INSERT INTO `sys_log` VALUES ('1125932872465305603', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 09:18:10', null);
INSERT INTO `sys_log` VALUES ('1125932887799681025', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:18:13', null);
INSERT INTO `sys_log` VALUES ('1125932916169953281', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:18:20', null);
INSERT INTO `sys_log` VALUES ('1125934276965748738', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:23:45', null);
INSERT INTO `sys_log` VALUES ('1125934578775281665', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:24:57', null);
INSERT INTO `sys_log` VALUES ('1125934578972413954', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:24:57', null);
INSERT INTO `sys_log` VALUES ('1125934707850792961', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:25:27', null);
INSERT INTO `sys_log` VALUES ('1125934791845924865', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:25:47', null);
INSERT INTO `sys_log` VALUES ('1125934791980142593', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:25:47', null);
INSERT INTO `sys_log` VALUES ('1125934799970291714', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree/1', '[\"1\"]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUserTree()', '查看角色资源', '2019-05-08 09:25:49', null);
INSERT INTO `sys_log` VALUES ('1125934821134749698', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:25:54', null);
INSERT INTO `sys_log` VALUES ('1125934835286331394', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 09:25:58', null);
INSERT INTO `sys_log` VALUES ('1125934835353440257', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:25:58', null);
INSERT INTO `sys_log` VALUES ('1125934950604525570', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"飞哥\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 09:26:25', null);
INSERT INTO `sys_log` VALUES ('1125934950604525571', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:26:25', null);
INSERT INTO `sys_log` VALUES ('1125934975044734977', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 09:26:31', null);
INSERT INTO `sys_log` VALUES ('1125934975044734978', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:26:31', null);
INSERT INTO `sys_log` VALUES ('1125935416650420226', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:28:16', null);
INSERT INTO `sys_log` VALUES ('1125935465530839042', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":6,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 09:28:28', null);
INSERT INTO `sys_log` VALUES ('1125935665431367681', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:29:16', null);
INSERT INTO `sys_log` VALUES ('1125935665565585409', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:29:16', null);
INSERT INTO `sys_log` VALUES ('1125935680568610818', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 09:29:19', null);
INSERT INTO `sys_log` VALUES ('1125935689020133378', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 09:29:21', null);
INSERT INTO `sys_log` VALUES ('1125935689020133379', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 09:29:21', null);
INSERT INTO `sys_log` VALUES ('1125935705398890498', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 09:29:25', null);
INSERT INTO `sys_log` VALUES ('1125935751527845890', 'article', 'article', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"article\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 09:29:36', null);
INSERT INTO `sys_log` VALUES ('1125937234793123843', 'article', 'article', '127.0.0.1', '1', '/system/user', '[{\"avatar\":\"blob:http://localhost:9527/8b135416-8b0b-43d1-bf4d-b49fc19de06c\",\"createTime\":\"2019-05-07 11:53:38\",\"id\":\"1125609610363441154\",\"nickname\":\"大编辑\",\"password\":\"fa73aefd7cc112312cb6b6b26848df7c\",\"phone\":\"18552365234\",\"roles\":[{\"createTime\":\"2017-05-01 13:25:39\",\"description\":\"编辑文章\",\"id\":\"2\",\"name\":\"ARTICLE\",\"updateTime\":\"2017-10-05 21:59:18\"}],\"sex\":0,\"status\":1,\"updateTime\":\"2019-05-07 11:53:38\",\"username\":\"article\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.UserController.roleUpdate()', '更新用户', '2019-05-08 09:35:30', null);
INSERT INTO `sys_log` VALUES ('1125960799399628802', 'article', 'article', '127.0.0.1', '1', '/system/user', '[{\"avatar\":\"blob:http://localhost:9527/8b135416-8b0b-43d1-bf4d-b49fc19de06c\",\"createTime\":\"2019-05-07 11:53:38\",\"id\":\"1125609610363441154\",\"nickname\":\"大编辑\",\"password\":\"fa73aefd7cc112312cb6b6b26848df7c\",\"phone\":\"18552365234\",\"roles\":[{\"createTime\":\"2017-05-01 13:25:39\",\"description\":\"编辑文章\",\"id\":\"2\",\"name\":\"ARTICLE\",\"updateTime\":\"2017-10-05 21:59:18\"}],\"sex\":0,\"status\":1,\"updateTime\":\"2019-05-08 09:35:30\",\"username\":\"article\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.UserController.roleUpdate()', '更新用户', '2019-05-08 11:09:08', null);
INSERT INTO `sys_log` VALUES ('1125963132871299074', 'article', 'article', '127.0.0.1', '1', '/system/user', '[{\"avatar\":\"http://image.com/2019/05/08/11/4274a561e3a9564847968113dac43a3323e.jpg\",\"createTime\":\"2019-05-07 11:53:38\",\"id\":\"1125609610363441154\",\"nickname\":\"大编辑\",\"password\":\"fa73aefd7cc112312cb6b6b26848df7c\",\"phone\":\"18552365234\",\"roles\":[{\"createTime\":\"2017-05-01 13:25:39\",\"description\":\"编辑文章\",\"id\":\"2\",\"name\":\"ARTICLE\",\"updateTime\":\"2017-10-05 21:59:18\"}],\"sex\":0,\"status\":1,\"updateTime\":\"2019-05-08 11:09:08\",\"username\":\"article\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.UserController.roleUpdate()', '更新用户', '2019-05-08 11:18:24', null);
INSERT INTO `sys_log` VALUES ('1125963975402115073', 'article', 'article', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 11:21:45', null);
INSERT INTO `sys_log` VALUES ('1125963985229369345', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 11:21:48', null);
INSERT INTO `sys_log` VALUES ('1125964001478103042', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:21:52', null);
INSERT INTO `sys_log` VALUES ('1125964122626379778', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/1125612449483624450', '[\"1125612449483624450\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceDelete()', '删除资源', '2019-05-08 11:22:20', null);
INSERT INTO `sys_log` VALUES ('1125964122756403201', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:22:20', null);
INSERT INTO `sys_log` VALUES ('1125964128783618050', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/1125611949086380033', '[\"1125611949086380033\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceDelete()', '删除资源', '2019-05-08 11:22:22', null);
INSERT INTO `sys_log` VALUES ('1125964128913641474', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:22:22', null);
INSERT INTO `sys_log` VALUES ('1125964145816686594', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:22:26', null);
INSERT INTO `sys_log` VALUES ('1125964196924280833', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:22:38', null);
INSERT INTO `sys_log` VALUES ('1125965204173803521', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:26:38', null);
INSERT INTO `sys_log` VALUES ('1125965253838557186', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:26:50', null);
INSERT INTO `sys_log` VALUES ('1125965699311390721', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 11:28:36', null);
INSERT INTO `sys_log` VALUES ('1125965789006581763', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user', '[{\"avatar\":\"http://image.com/2019/05/08/11/1145c5ebed0aa76775254922db3fda42e25.gif\",\"createTime\":\"2019-04-28 14:00:27\",\"id\":\"1122380033870000129\",\"nickname\":\"飞哥\",\"password\":\"2a3a8a2f710d3a9f95f6a09dc745dc86\",\"phone\":\"18525463256\",\"roles\":[{\"createTime\":\"2017-05-01 13:25:39\",\"description\":\"管理员\",\"id\":\"1\",\"name\":\"ADMIN\",\"updateTime\":\"2017-10-05 21:59:18\"}],\"sex\":0,\"status\":1,\"updateTime\":\"2019-05-07 11:47:37\",\"username\":\"jorian4\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.UserController.roleUpdate()', '更新用户', '2019-05-08 11:28:58', null);
INSERT INTO `sys_log` VALUES ('1125965815938207746', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 11:29:04', null);
INSERT INTO `sys_log` VALUES ('1125965816001122306', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 11:29:04', null);
INSERT INTO `sys_log` VALUES ('1125983929048403970', '游客', '匿名', '127.0.0.1', '0', '/system/log/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 12:41:02', null);
INSERT INTO `sys_log` VALUES ('1125984301699731458', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 12:42:31', null);
INSERT INTO `sys_log` VALUES ('1125984317017329665', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 12:42:35', null);
INSERT INTO `sys_log` VALUES ('1125984317055078402', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 12:42:35', null);
INSERT INTO `sys_log` VALUES ('1125984340094390273', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 12:42:41', null);
INSERT INTO `sys_log` VALUES ('1125984367617413122', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":3,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 12:42:47', null);
INSERT INTO `sys_log` VALUES ('1125984411896680450', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 12:42:58', null);
INSERT INTO `sys_log` VALUES ('1125984418414628865', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 12:42:59', null);
INSERT INTO `sys_log` VALUES ('1125984418448183297', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 12:42:59', null);
INSERT INTO `sys_log` VALUES ('1125984424282460162', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 12:43:01', null);
INSERT INTO `sys_log` VALUES ('1125984516573925378', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource', '[{\"children\":[],\"component\":\"/article/article-kinds/index\",\"createTime\":\"2019-04-28 15:11:26\",\"icon\":\"edit\",\"id\":\"1122397899180732417\",\"isVerify\":true,\"name\":\"ArticleKinds\",\"path\":\"/kindManage\",\"permission\":\"[kindManage]\",\"pid\":\"1121788789530923010\",\"sort\":51,\"title\":\"文章分类\",\"type\":1,\"updateTime\":\"2019-04-28 15:11:26\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUpdate()', '更新资源', '2019-05-08 12:43:23', null);
INSERT INTO `sys_log` VALUES ('1125984516901081090', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 12:43:23', null);
INSERT INTO `sys_log` VALUES ('1125984600711663617', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 12:43:43', null);
INSERT INTO `sys_log` VALUES ('1125985114010587137', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user', '[{\"avatar\":\"http://image.com/2019/05/08/12/6341d4a9ee41e8d38aeb0ffa3008a203885.jpg\",\"createTime\":\"2019-04-28 14:00:27\",\"id\":\"1122380033870000129\",\"nickname\":\"飞哥\",\"password\":\"2a3a8a2f710d3a9f95f6a09dc745dc86\",\"phone\":\"18525463256\",\"roles\":[{\"createTime\":\"2017-05-01 13:25:39\",\"description\":\"管理员\",\"id\":\"1\",\"name\":\"ADMIN\",\"updateTime\":\"2017-10-05 21:59:18\"}],\"sex\":0,\"status\":1,\"updateTime\":\"2019-05-08 11:28:58\",\"username\":\"jorian4\"}]', 'PUT', 'cn.jorian.jorianframework.core.system.controller.UserController.roleUpdate()', '更新用户', '2019-05-08 12:45:45', null);
INSERT INTO `sys_log` VALUES ('1125985135003078658', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 12:45:50', null);
INSERT INTO `sys_log` VALUES ('1125985135032438785', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 12:45:50', null);
INSERT INTO `sys_log` VALUES ('1125985255077613570', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 12:46:19', null);
INSERT INTO `sys_log` VALUES ('1125985306227150849', 'article', 'article', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"article\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 12:46:31', null);
INSERT INTO `sys_log` VALUES ('1125986757275979778', 'article', 'article', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 12:52:17', null);
INSERT INTO `sys_log` VALUES ('1125987473877012481', 'article', 'article', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 12:55:08', null);
INSERT INTO `sys_log` VALUES ('1125990485257261057', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125987493892231169', '[\"1125987493892231169\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:07:06', null);
INSERT INTO `sys_log` VALUES ('1125990223037763586', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 13:06:03', null);
INSERT INTO `sys_log` VALUES ('1125990455821635585', 'article', 'article', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 13:06:59', null);
INSERT INTO `sys_log` VALUES ('1125990461882404866', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 13:07:00', null);
INSERT INTO `sys_log` VALUES ('1125990476902207489', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 13:07:04', null);
INSERT INTO `sys_log` VALUES ('1125990923314565122', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/1125990587795410946', '[\"1125990587795410946\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '删除日志', '2019-05-08 13:08:50', null);
INSERT INTO `sys_log` VALUES ('1125990874283151361', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 13:08:38', null);
INSERT INTO `sys_log` VALUES ('1125990899855822850', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 13:08:45', null);
INSERT INTO `sys_log` VALUES ('1125990899901960194', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 13:08:45', null);
INSERT INTO `sys_log` VALUES ('1125990903035105282', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 13:08:45', null);
INSERT INTO `sys_log` VALUES ('1125991058476011521', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":9,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 13:09:22', null);
INSERT INTO `sys_log` VALUES ('1125991213661065218', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 13:09:59', null);
INSERT INTO `sys_log` VALUES ('1126036875769470978', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 16:11:26', null);
INSERT INTO `sys_log` VALUES ('1126036945533329409', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 16:11:43', null);
INSERT INTO `sys_log` VALUES ('1126036945554300930', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 16:11:43', null);
INSERT INTO `sys_log` VALUES ('1126037681260388354', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 16:14:38', null);
INSERT INTO `sys_log` VALUES ('1126042454873268226', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 16:33:36', null);
INSERT INTO `sys_log` VALUES ('1126042468458618882', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 16:33:39', null);
INSERT INTO `sys_log` VALUES ('1126042468513144833', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 16:33:40', null);
INSERT INTO `sys_log` VALUES ('1126042576189317121', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 16:34:05', null);
INSERT INTO `sys_log` VALUES ('1126043164692111362', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 16:36:25', null);
INSERT INTO `sys_log` VALUES ('1126043188507369474', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 16:36:31', null);
INSERT INTO `sys_log` VALUES ('1126043245268885505', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 16:36:45', null);
INSERT INTO `sys_log` VALUES ('1126043245294051330', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 16:36:45', null);
INSERT INTO `sys_log` VALUES ('1126045740657430530', '游客', '匿名', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 16:46:39', null);
INSERT INTO `sys_log` VALUES ('1126045758734880770', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 16:46:44', null);
INSERT INTO `sys_log` VALUES ('1126045810022830082', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 16:46:56', null);
INSERT INTO `sys_log` VALUES ('1126045810039607298', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 16:46:56', null);
INSERT INTO `sys_log` VALUES ('1126046172934983681', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 16:48:23', null);
INSERT INTO `sys_log` VALUES ('1126048547103997954', '游客', '匿名', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 16:57:48', null);
INSERT INTO `sys_log` VALUES ('1126048570030063617', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 16:57:54', null);
INSERT INTO `sys_log` VALUES ('1126048677106450433', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 16:58:20', null);
INSERT INTO `sys_log` VALUES ('1126054509617524738', '游客', '匿名', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 17:21:30', null);
INSERT INTO `sys_log` VALUES ('1126054540818952193', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 17:21:38', null);
INSERT INTO `sys_log` VALUES ('1126054596229902338', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 17:21:51', null);
INSERT INTO `sys_log` VALUES ('1126112962503802882', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 21:13:45', null);
INSERT INTO `sys_log` VALUES ('1126112978517655553', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 21:13:50', null);
INSERT INTO `sys_log` VALUES ('1126120136269008897', '游客', '匿名', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 21:42:17', null);
INSERT INTO `sys_log` VALUES ('1126120188093829122', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 21:42:29', null);
INSERT INTO `sys_log` VALUES ('1126120268947427329', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 21:42:49', null);
INSERT INTO `sys_log` VALUES ('1126120268947427330', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 21:42:49', null);
INSERT INTO `sys_log` VALUES ('1126120641753944066', 'jorian4', 'jorian4', '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 21:44:17', null);
INSERT INTO `sys_log` VALUES ('1126123168750800897', null, null, '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 21:54:20', null);
INSERT INTO `sys_log` VALUES ('1126123184148090882', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 21:54:24', null);
INSERT INTO `sys_log` VALUES ('1126133802360094721', '游客', '匿名', '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-08 22:36:35', null);
INSERT INTO `sys_log` VALUES ('1126133817170182146', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-08 22:36:39', null);
INSERT INTO `sys_log` VALUES ('1126133844558987266', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-08 22:36:45', null);
INSERT INTO `sys_log` VALUES ('1126133874170773506', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 22:36:52', null);
INSERT INTO `sys_log` VALUES ('1126133874170773507', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 22:36:52', null);
INSERT INTO `sys_log` VALUES ('1126133906605326338', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 22:37:00', null);
INSERT INTO `sys_log` VALUES ('1126134054551011330', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 22:37:35', null);
INSERT INTO `sys_log` VALUES ('1126134054681034754', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 22:37:35', null);
INSERT INTO `sys_log` VALUES ('1126134156564873217', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 22:38:00', null);
INSERT INTO `sys_log` VALUES ('1126135795258363907', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 22:44:30', null);
INSERT INTO `sys_log` VALUES ('1126135795258363906', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 22:44:30', null);
INSERT INTO `sys_log` VALUES ('1126136195743117314', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-08 22:46:05', null);
INSERT INTO `sys_log` VALUES ('1126136207977902082', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-08 22:46:09', null);
INSERT INTO `sys_log` VALUES ('1126136207977902083', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-08 22:46:09', null);
INSERT INTO `sys_log` VALUES ('1126279575795388418', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-09 08:15:50', null);
INSERT INTO `sys_log` VALUES ('1126299467399516162', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-09 09:34:53', null);
INSERT INTO `sys_log` VALUES ('1126299850712764417', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 09:36:24', null);
INSERT INTO `sys_log` VALUES ('1126299850750513154', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 09:36:24', null);
INSERT INTO `sys_log` VALUES ('1126299874049871873', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 09:36:30', null);
INSERT INTO `sys_log` VALUES ('1126324609177604097', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-09 11:14:47', null);
INSERT INTO `sys_log` VALUES ('1126399501348569089', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-09 16:12:23', null);
INSERT INTO `sys_log` VALUES ('1126400034096480258', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:14:30', null);
INSERT INTO `sys_log` VALUES ('1126400034130034690', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:14:30', null);
INSERT INTO `sys_log` VALUES ('1126400045593067521', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:14:33', null);
INSERT INTO `sys_log` VALUES ('1126402675199377409', null, null, '127.0.0.1', '1', '/system/resource/1125234322459983873', '[\"1125234322459983873\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceDelete()', '删除资源', '2019-05-09 16:24:59', null);
INSERT INTO `sys_log` VALUES ('1126402675455229954', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:00', null);
INSERT INTO `sys_log` VALUES ('1126402687446745089', null, null, '127.0.0.1', '1', '/system/resource/1121793643787685890', '[\"1121793643787685890\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceDelete()', '删除资源', '2019-05-09 16:25:02', null);
INSERT INTO `sys_log` VALUES ('1126402687601934338', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:02', null);
INSERT INTO `sys_log` VALUES ('1126402697596960769', null, null, '127.0.0.1', '1', '/system/resource/1122397899180732417', '[\"1122397899180732417\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceDelete()', '删除资源', '2019-05-09 16:25:05', null);
INSERT INTO `sys_log` VALUES ('1126402697735372801', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:05', null);
INSERT INTO `sys_log` VALUES ('1126402708237910018', null, null, '127.0.0.1', '1', '/system/resource/1121788789530923010', '[\"1121788789530923010\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceDelete()', '删除资源', '2019-05-09 16:25:07', null);
INSERT INTO `sys_log` VALUES ('1126402708384710658', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:07', null);
INSERT INTO `sys_log` VALUES ('1126402790148472834', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:25:27', null);
INSERT INTO `sys_log` VALUES ('1126402790198804481', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:25:27', null);
INSERT INTO `sys_log` VALUES ('1126402795311661058', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:28', null);
INSERT INTO `sys_log` VALUES ('1126402800609067010', null, null, '127.0.0.1', '1', '/account', '[]', 'DELETE', 'cn.jorian.jorianframework.core.account.controller.AccountController.logout()', '登出', '2019-05-09 16:25:29', null);
INSERT INTO `sys_log` VALUES ('1126402807420616705', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-09 16:25:31', null);
INSERT INTO `sys_log` VALUES ('1126402829793034241', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:25:36', null);
INSERT INTO `sys_log` VALUES ('1126402829801422849', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:25:36', null);
INSERT INTO `sys_log` VALUES ('1126402837565079554', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:38', null);
INSERT INTO `sys_log` VALUES ('1126402843520991234', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:25:40', null);
INSERT INTO `sys_log` VALUES ('1126402843525185537', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:40', null);
INSERT INTO `sys_log` VALUES ('1126402849984413697', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-09 16:25:41', null);
INSERT INTO `sys_log` VALUES ('1126402874701447170', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:25:47', null);
INSERT INTO `sys_log` VALUES ('1126402874709835778', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:25:47', null);
INSERT INTO `sys_log` VALUES ('1126402894859272194', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:52', null);
INSERT INTO `sys_log` VALUES ('1126402901620490241', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:25:53', null);
INSERT INTO `sys_log` VALUES ('1126402901633073154', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:25:53', null);
INSERT INTO `sys_log` VALUES ('1126402908142632961', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-09 16:25:55', null);
INSERT INTO `sys_log` VALUES ('1126402934679994370', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:26:01', null);
INSERT INTO `sys_log` VALUES ('1126402934705160193', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:26:01', null);
INSERT INTO `sys_log` VALUES ('1126402944402391041', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:26:04', null);
INSERT INTO `sys_log` VALUES ('1126402951780171777', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:26:05', null);
INSERT INTO `sys_log` VALUES ('1126402951792754689', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:26:05', null);
INSERT INTO `sys_log` VALUES ('1126402959992619010', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-09 16:26:07', null);
INSERT INTO `sys_log` VALUES ('1126403135125782529', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:26:49', null);
INSERT INTO `sys_log` VALUES ('1126403135163531265', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:26:49', null);
INSERT INTO `sys_log` VALUES ('1126403739483045889', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:29:13', null);
INSERT INTO `sys_log` VALUES ('1126403739537571842', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:29:13', null);
INSERT INTO `sys_log` VALUES ('1126403757405306882', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-09 16:29:17', null);
INSERT INTO `sys_log` VALUES ('1126403763239583746', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:29:19', null);
INSERT INTO `sys_log` VALUES ('1126403763247972353', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:29:19', null);
INSERT INTO `sys_log` VALUES ('1126403898237452289', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-09 16:29:51', null);
INSERT INTO `sys_log` VALUES ('1126403900942778370', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-09 16:29:52', null);
INSERT INTO `sys_log` VALUES ('1126403900951166978', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-09 16:29:52', null);
INSERT INTO `sys_log` VALUES ('1126407805902651394', null, null, '127.0.0.1', '1', '/system/user', '[{\"nickname\":\"若流风之回雪\",\"password\":\"******\",\"phone\":\"18523657845\",\"roles\":[{\"createTime\":\"2017-05-01 13:25:39\",\"description\":\"管理员\",\"id\":\"1\",\"name\":\"ADMIN\",\"updateTime\":\"2017-10-05 21:59:18\"}],\"status\":1,\"username\":\"jorianye\"}]', 'POST', 'cn.jorian.jorianframework.core.system.controller.UserController.add()', '新增用户', '2019-05-09 16:45:23', null);
INSERT INTO `sys_log` VALUES ('1126517668329488386', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-10 00:01:56', null);
INSERT INTO `sys_log` VALUES ('1126517692186689538', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-10 00:02:02', null);
INSERT INTO `sys_log` VALUES ('1126517692220243969', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-10 00:02:02', null);
INSERT INTO `sys_log` VALUES ('1126517697148551170', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-10 00:02:03', null);
INSERT INTO `sys_log` VALUES ('1126517703158988801', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-10 00:02:04', null);
INSERT INTO `sys_log` VALUES ('1126517703158988802', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-10 00:02:04', null);
INSERT INTO `sys_log` VALUES ('1126517709295255554', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-10 00:02:06', null);
INSERT INTO `sys_log` VALUES ('1126517730426159106', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-10 00:02:11', null);
INSERT INTO `sys_log` VALUES ('1126517754706984961', null, null, '127.0.0.1', '1', '/system/resource/1125650815872815106', '[\"1125650815872815106\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceDelete()', '删除资源', '2019-05-10 00:02:17', null);
INSERT INTO `sys_log` VALUES ('1126517754837008385', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-10 00:02:17', null);
INSERT INTO `sys_log` VALUES ('1127746235071606786', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-13 09:23:49', null);
INSERT INTO `sys_log` VALUES ('1127746251748159490', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:23:53', null);
INSERT INTO `sys_log` VALUES ('1127746251798491138', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-13 09:23:53', null);
INSERT INTO `sys_log` VALUES ('1127746259297906690', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:23:55', null);
INSERT INTO `sys_log` VALUES ('1127746268156276738', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:23:57', null);
INSERT INTO `sys_log` VALUES ('1127746268181442562', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:23:57', null);
INSERT INTO `sys_log` VALUES ('1127746276117065730', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-13 09:23:59', null);
INSERT INTO `sys_log` VALUES ('1127746285080293377', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:24:01', null);
INSERT INTO `sys_log` VALUES ('1127746285105459201', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:24:01', null);
INSERT INTO `sys_log` VALUES ('1127746290830684162', null, null, '127.0.0.1', '1', '/system/resource/1', '[\"1\"]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUserTree()', '查看角色资源', '2019-05-13 09:24:02', null);
INSERT INTO `sys_log` VALUES ('1127746301656182786', null, null, '127.0.0.1', '1', '/system/resource/2', '[\"2\"]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUserTree()', '查看角色资源', '2019-05-13 09:24:05', null);
INSERT INTO `sys_log` VALUES ('1127746435227987969', null, null, '127.0.0.1', '1', '/system/resource/2', '[\"2\"]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUserTree()', '查看角色资源', '2019-05-13 09:24:37', null);
INSERT INTO `sys_log` VALUES ('1127746539376750594', null, null, '127.0.0.1', '1', '/system/role/2', '[\"2\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleDelete()', '删除角色', '2019-05-13 09:25:02', null);
INSERT INTO `sys_log` VALUES ('1127746554631434241', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-13 09:25:05', null);
INSERT INTO `sys_log` VALUES ('1127746736102191106', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:25:49', null);
INSERT INTO `sys_log` VALUES ('1127746736207048705', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:25:49', null);
INSERT INTO `sys_log` VALUES ('1127746762035572737', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-13 09:25:55', null);
INSERT INTO `sys_log` VALUES ('1127746782315032578', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:26:00', null);
INSERT INTO `sys_log` VALUES ('1127746782361169921', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:26:00', null);
INSERT INTO `sys_log` VALUES ('1127746876183556098', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:26:22', null);
INSERT INTO `sys_log` VALUES ('1127746876212916226', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-13 09:26:22', null);
INSERT INTO `sys_log` VALUES ('1127746910639763457', null, null, '127.0.0.1', '1', '/system/user/1125609610363441154', '[\"1125609610363441154\"]', 'DELETE', 'cn.jorian.jorianframework.core.system.controller.UserController.roleDelete()', '删除用户', '2019-05-13 09:26:30', null);
INSERT INTO `sys_log` VALUES ('1127746943896399874', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:26:38', null);
INSERT INTO `sys_log` VALUES ('1127746949281886209', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:26:39', null);
INSERT INTO `sys_log` VALUES ('1127746949298663425', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:26:39', null);
INSERT INTO `sys_log` VALUES ('1127747013186301954', null, null, '127.0.0.1', '1', '/system/role', '[{\"description\":\"admin2\",\"name\":\"admin2\",\"resources\":[{\"id\":\"1121348570280771586\"},{\"id\":\"1121348591713665026\"},{\"id\":\"1121792385039306754\"},{\"id\":\"1122396157441470466\"},{\"id\":\"1125650097057189889\"}]}]', 'POST', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleAdd()', '新增角色', '2019-05-13 09:26:55', null);
INSERT INTO `sys_log` VALUES ('1127747103699382273', null, null, '127.0.0.1', '1', '/system/role', '[{\"description\":\"admin3\",\"name\":\"admin3\",\"resources\":[{\"id\":\"1121348570280771586\"},{\"id\":\"1121348591713665026\"},{\"id\":\"1121792385039306754\"},{\"id\":\"1122396157441470466\"},{\"id\":\"1125650097057189889\"}]}]', 'POST', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleAdd()', '新增角色', '2019-05-13 09:27:16', null);
INSERT INTO `sys_log` VALUES ('1127749159231303682', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:35:26', null);
INSERT INTO `sys_log` VALUES ('1127749159248080898', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:35:26', null);
INSERT INTO `sys_log` VALUES ('1127749493991288833', null, null, '127.0.0.1', '1', '/system/resource/1127747013114998785', '[\"1127747013114998785\"]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceUserTree()', '查看角色资源', '2019-05-13 09:36:46', null);
INSERT INTO `sys_log` VALUES ('1127749528019677186', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-13 09:36:54', null);
INSERT INTO `sys_log` VALUES ('1127749528845955074', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:36:54', null);
INSERT INTO `sys_log` VALUES ('1127749528892092417', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:36:54', null);
INSERT INTO `sys_log` VALUES ('1127749574320599042', 'jorian4', 'jorian4', '127.0.0.1', '1', '/account', '[{\"password\":\"******\",\"username\":\"jorian4\"}]', 'POST', 'cn.jorian.jorianframework.core.account.controller.AccountController.login()', '登入', '2019-05-13 09:37:05', null);
INSERT INTO `sys_log` VALUES ('1127749575088156673', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:37:05', null);
INSERT INTO `sys_log` VALUES ('1127749575109128194', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:37:05', null);
INSERT INTO `sys_log` VALUES ('1127749628376788994', null, null, '127.0.0.1', '1', '/system/role', '[{\"description\":\"admin\",\"name\":\"admin4\",\"resources\":[{\"id\":\"1121348570280771586\"},{\"id\":\"1121348591713665026\"},{\"id\":\"1121792385039306754\"},{\"id\":\"1122396157441470466\"},{\"id\":\"1125650097057189889\"}]}]', 'POST', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleAdd()', '新增角色', '2019-05-13 09:37:18', null);
INSERT INTO `sys_log` VALUES ('1127751798211883009', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:45:55', null);
INSERT INTO `sys_log` VALUES ('1127751806839566338', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-13 09:45:58', null);
INSERT INTO `sys_log` VALUES ('1127751831221055490', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-13 09:46:03', null);
INSERT INTO `sys_log` VALUES ('1127751842285629442', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:46:06', null);
INSERT INTO `sys_log` VALUES ('1127751847859859458', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-13 09:46:07', null);
INSERT INTO `sys_log` VALUES ('1127751847868248065', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:46:07', null);
INSERT INTO `sys_log` VALUES ('1127751852981104642', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-13 09:46:09', null);
INSERT INTO `sys_log` VALUES ('1127751908543049729', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:46:22', null);
INSERT INTO `sys_log` VALUES ('1127752034246340610', null, null, '127.0.0.1', '1', '/system/log/list', '[{\"limit\":20,\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.LogController.roleList()', '查询日志', '2019-05-13 09:46:52', null);
INSERT INTO `sys_log` VALUES ('1127752054492246017', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":10,\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:46:57', null);
INSERT INTO `sys_log` VALUES ('1127752054500634625', null, null, '127.0.0.1', '1', '/system/user/list', '[{\"company\":\"\",\"limit\":20,\"nickname\":\"\",\"page\":1,\"sort\":false,\"username\":\"\"}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.UserController.roleList()', '查询用户列表', '2019-05-13 09:46:57', null);
INSERT INTO `sys_log` VALUES ('1127752069918896129', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:47:00', null);
INSERT INTO `sys_log` VALUES ('1127752414090899458', null, null, '127.0.0.1', '1', '/system/role/list', '[{\"limit\":20,\"name\":\"\",\"page\":1,\"sort\":false}]', 'GET', 'cn.jorian.jorianframework.core.system.controller.RoleController.roleList()', '角色列表查询', '2019-05-13 09:48:22', null);
INSERT INTO `sys_log` VALUES ('1127752414137036801', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:48:22', null);
INSERT INTO `sys_log` VALUES ('1127752443492970498', null, null, '127.0.0.1', '1', '/system/resource/tree', '[]', 'GET', 'cn.jorian.jorianframework.core.system.controller.ResourceController.resourceTree()', '查询资源树', '2019-05-13 09:48:29', null);

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(30) NOT NULL,
  `pid` varchar(30) NOT NULL,
  `title` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `icon` varchar(30) DEFAULT NULL,
  `redirect` varchar(30) DEFAULT NULL COMMENT '面包屑重定向',
  `path` varchar(1000) DEFAULT NULL,
  `component` varchar(30) DEFAULT NULL COMMENT '组件地址',
  `type` int(1) NOT NULL,
  `permission` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `sort` int(11) NOT NULL,
  `isVerify` tinyint(1) DEFAULT NULL COMMENT '是否校验',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `hidden` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1121348570280771586', '0', '系统管理', 'System', 'system', '/system/user', '/system', '@/layout', '1', '[system]', '0', '1', '2019-04-25 17:41:47', '2019-04-25 17:41:47', null);
INSERT INTO `sys_resource` VALUES ('1121348591713665026', '1121348570280771586', '用户管理', 'User', 'user', null, '/user', '/system/user/index', '1', '[system:user]', '1', '1', '2019-04-25 17:41:52', '2019-04-25 17:41:52', null);
INSERT INTO `sys_resource` VALUES ('1121792385039306754', '1121348570280771586', '资源管理', 'Resource', 'permission', null, '/resource', '/system/resource/index', '1', '[system:resource]', '3', '1', '2019-04-26 23:05:21', '2019-04-26 23:05:21', null);
INSERT INTO `sys_resource` VALUES ('1122396157441470466', '1121348570280771586', '角色管理', 'Role', 'role', null, '/role', '/system/role/index', '1', '[system:role]', '45', '1', '2019-04-28 15:04:31', '2019-04-28 15:04:31', null);
INSERT INTO `sys_resource` VALUES ('1125650097057189889', '1121348570280771586', '日志管理', 'Log ', 'bug', null, '/log', '/system/log/index', '1', '[system:log]', '10005', '1', '2019-05-07 14:34:31', '2019-05-07 14:34:31', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2017-05-01 13:25:39', '2017-10-05 21:59:18');
INSERT INTO `sys_role` VALUES ('1127747013114998785', 'admin2', 'admin2', '2019-05-13 09:26:55', '2019-05-13 09:26:55');
INSERT INTO `sys_role` VALUES ('1127747103653244930', 'admin3', 'admin3', '2019-05-13 09:27:16', '2019-05-13 09:27:16');
INSERT INTO `sys_role` VALUES ('1127749628334845953', 'admin4', 'admin', '2019-05-13 09:37:18', '2019-05-13 09:37:18');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` varchar(30) NOT NULL,
  `roleId` varchar(30) NOT NULL,
  `resourceId` varchar(30) NOT NULL,
  PRIMARY KEY (`id`,`roleId`,`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1125650888362971138', '1', '1121348570280771586');
INSERT INTO `sys_role_resource` VALUES ('1125650888371359745', '1', '1121348591713665026');
INSERT INTO `sys_role_resource` VALUES ('1125650888371359746', '1', '1121792385039306754');
INSERT INTO `sys_role_resource` VALUES ('1125650888379748354', '1', '1122396157441470466');
INSERT INTO `sys_role_resource` VALUES ('1125650888379748355', '1', '1125650097057189889');
INSERT INTO `sys_role_resource` VALUES ('1127747013123387394', '1127747013114998785', '1121348570280771586');
INSERT INTO `sys_role_resource` VALUES ('1127747013140164610', '1127747013114998785', '1121348591713665026');
INSERT INTO `sys_role_resource` VALUES ('1127747013140164611', '1127747013114998785', '1121792385039306754');
INSERT INTO `sys_role_resource` VALUES ('1127747013148553218', '1127747013114998785', '1122396157441470466');
INSERT INTO `sys_role_resource` VALUES ('1127747013156941826', '1127747013114998785', '1125650097057189889');
INSERT INTO `sys_role_resource` VALUES ('1127747103661633537', '1127747103653244930', '1121348570280771586');
INSERT INTO `sys_role_resource` VALUES ('1127747103670022145', '1127747103653244930', '1121348591713665026');
INSERT INTO `sys_role_resource` VALUES ('1127747103678410753', '1127747103653244930', '1121792385039306754');
INSERT INTO `sys_role_resource` VALUES ('1127747103678410754', '1127747103653244930', '1122396157441470466');
INSERT INTO `sys_role_resource` VALUES ('1127747103686799362', '1127747103653244930', '1125650097057189889');
INSERT INTO `sys_role_resource` VALUES ('1127749628343234562', '1127749628334845953', '1121348570280771586');
INSERT INTO `sys_role_resource` VALUES ('1127749628351623169', '1127749628334845953', '1121348591713665026');
INSERT INTO `sys_role_resource` VALUES ('1127749628351623170', '1127749628334845953', '1121792385039306754');
INSERT INTO `sys_role_resource` VALUES ('1127749628360011777', '1127749628334845953', '1122396157441470466');
INSERT INTO `sys_role_resource` VALUES ('1127749628368400385', '1127749628334845953', '1125650097057189889');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1122380033870000129', 'jorian4', '2a3a8a2f710d3a9f95f6a09dc745dc86', '飞哥', 'http://image.com/2019/05/08/12/6341d4a9ee41e8d38aeb0ffa3008a203885.jpg', '18525463256', null, null, null, '0', '1', '2019-04-28 14:00:27', '2019-05-08 12:45:45', null);
INSERT INTO `sys_user` VALUES ('1126407805504192514', 'jorianye', '476575131ed95856b7c50908f912d1c2', '若流风之回雪', null, '18523657845', null, null, null, '0', '1', '2019-05-09 16:45:22', '2019-05-09 16:45:22', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(20) NOT NULL,
  `uid` varchar(20) NOT NULL,
  `rid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`,`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1125985113960255490', '1122380033870000129', '1');
INSERT INTO `sys_user_role` VALUES ('1126407805558718465', '1126407805504192514', '1');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL,
  `k` varchar(16) NOT NULL,
  `val` varchar(64) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`k`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', 'sex', '0', '女', '2017-11-17 09:58:24', '2017-11-18 14:21:05');
INSERT INTO `t_dict` VALUES ('2', 'sex', '1', '男', '2017-11-17 10:03:46', '2017-11-17 10:03:46');
INSERT INTO `t_dict` VALUES ('3', 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('4', 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('5', 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('6', 'noticeStatus', '0', '草稿', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('7', 'noticeStatus', '1', '发布', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('8', 'isRead', '0', '未读', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('9', 'isRead', '1', '已读', '2017-11-17 16:26:06', '2017-11-17 16:26:09');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cron` varchar(64) NOT NULL,
  `springBeanName` varchar(64) NOT NULL COMMENT 'springBean名',
  `methodName` varchar(64) NOT NULL COMMENT '方法名',
  `isSysJob` tinyint(1) NOT NULL COMMENT '是否是系统job',
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jobName` (`jobName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_job
-- ----------------------------

-- ----------------------------
-- Table structure for t_mail
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '发送人',
  `subject` varchar(255) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '正文',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_mail
-- ----------------------------

-- ----------------------------
-- Table structure for t_mail_to
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_to`;
CREATE TABLE `t_mail_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mailId` int(11) NOT NULL,
  `toUser` varchar(128) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1成功，0失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_mail_to
-- ----------------------------

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `content` text NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_notice_read
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_read`;
CREATE TABLE `t_notice_read` (
  `noticeId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`noticeId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_notice_read
-- ----------------------------

-- ----------------------------
-- Table structure for t_token
-- ----------------------------
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token` (
  `id` varchar(36) NOT NULL COMMENT 'token',
  `val` text NOT NULL COMMENT 'LoginUser的json串',
  `expireTime` datetime NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_token
-- ----------------------------
