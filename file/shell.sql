/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : shell

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 24/04/2021 15:00:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_form_conf
-- ----------------------------
DROP TABLE IF EXISTS `gen_form_conf`;
CREATE TABLE `gen_form_conf` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_id` bigint DEFAULT NULL COMMENT '表名id',
  `form_info` json NOT NULL COMMENT '表单信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0',
  `tenant_id` int DEFAULT NULL COMMENT '所属租户',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `table_name` (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表单配置';

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '表描述',
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `parent_menu_id` bigint DEFAULT NULL COMMENT '上级菜单id',
  `gen_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1383305324206878723 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------
BEGIN;
INSERT INTO `gen_table` VALUES (1349919143557627905, 'sys_log', '系统日志', 'SysLog', 'crud', 'me.zrxjava.system', 'system', 'log', '系统日志', 'zrxjava', '0', NULL, '/', NULL, 'admin', '2020-11-22 06:11:56', '', '2021-01-14 04:18:19', NULL);
INSERT INTO `gen_table` VALUES (1359681214683930625, 'sys_menu', '系统菜单', 'Menu', 'crud', 'me.zrxjava.system.modules.ums', 'system', 'menu', '系统菜单', 'zrxjava', '0', NULL, '/Users/zhongrongxin/work/project/shell/shell/system', NULL, 'admin', '2021-01-27 03:54:49', 'admin', '2021-03-07 06:13:05', '');
INSERT INTO `gen_table` VALUES (1378612558440812546, 'sys_dict', '数据字典', 'Dict', 'crud', 'me.zrxjava.system.modules.ums', 'system', 'dict', '数据字典', 'zrxjava', '0', 1, '/Users/zhongrongxin/work/project/shell/shell/system', NULL, 'admin', '2020-09-26 02:30:05', 'admin', '2021-04-05 00:27:57', '');
INSERT INTO `gen_table` VALUES (1378969825459568642, 'sys_dict_detail', '数据字典详情', 'DictDetail', 'crud', 'me.zrxjava.system.modules.ums', 'system', 'detail', '数据字典详情', 'zrxjava', '0', 1, '/Users/zhongrongxin/work/project/shell/shell/system', NULL, 'admin', '2021-04-05 01:57:57', 'admin', '2021-04-05 04:10:35', '');
INSERT INTO `gen_table` VALUES (1381094249193332738, 'sys_dept', '部门', 'Dept', 'crud', 'me.zrxjava.system.modules.ums', 'system', 'dept', '部门', 'zrxjava', '0', 1, '/Users/zhongrongxin/work/project/shell/shell/system', NULL, 'admin', '2021-04-10 02:25:51', 'admin', '2021-04-10 23:02:30', '');
INSERT INTO `gen_table` VALUES (1381244387643645953, 'sys_role', '角色表', 'Role', 'crud', 'me.zrxjava.system.modules.ums', 'system', 'role', '角色', 'zrxjava', '0', 1, '/Users/zhongrongxin/work/project/shell/shell/system', NULL, 'admin', '2021-04-11 08:49:58', 'admin', '2021-04-12 01:11:11', '');
INSERT INTO `gen_table` VALUES (1383305324206878722, 'sys_user', '系统用户', 'User', 'crud', 'me.zrxjava.system.modules.ums', 'system', 'user', '系统用户', 'zrxjava', '0', 1, '/Users/zhongrongxin/work/project/shell/shell/system', NULL, 'admin', '2020-09-26 02:30:05', 'admin', '2021-04-17 02:37:18', '');
COMMIT;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否必填（1是）',
  `is_import` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否导入字段（1是）',
  `is_export` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否导出字段（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1383305326371139586 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
BEGIN;
INSERT INTO `gen_table_column` VALUES (1349919143662485505, '1349919143557627905', 'log_id', 'ID', 'bigint', 'Long', 'LogId', '1', '1', NULL, NULL, NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143683457025, '1349919143557627905', 'title', '操作模块', 'varchar(255)', 'String', 'Title', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143700234242, '1349919143557627905', 'method', '操作方式', 'varchar(255)', 'String', 'Method', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143721205762, '1349919143557627905', 'request_uri', '请求URI', 'varchar(255)', 'String', 'RequestUri', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143733788673, '1349919143557627905', 'params', '操作提交的数据', 'text', 'String', 'Params', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143754760193, '1349919143557627905', 'ip', '操作IP', 'varchar(255)', 'String', 'Ip', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143792508929, '1349919143557627905', 'json_result', '返回参数', 'mediumtext', 'String', 'JsonResult', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143834451970, '1349919143557627905', 'cost_time', '执行时间 单位ms', 'int', 'Long', 'CostTime', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143914143745, '1349919143557627905', 'user_agent', '操作浏览器', 'varchar(255)', 'String', 'UserAgent', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143930920961, '1349919143557627905', 'error_msg', '异常信息', 'text', 'String', 'ErrorMsg', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143951892482, '1349919143557627905', 'operator_type', '操作类别（0其它 1后台用户 2手机端用户）', 'tinyint(1)', 'Integer', 'OperatorType', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'select', '', 11, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919143981252610, '1349919143557627905', 'business_type', '业务类型（0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据）', 'tinyint', 'Long', 'BusinessType', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'select', '', 12, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919144006418434, '1349919143557627905', 'status', '操作状态（0正常 1异常）', 'tinyint(1)', 'Integer', 'Status', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 13, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919144023195649, '1349919143557627905', 'remark', '备注', 'varchar(255)', 'String', 'Remark', '0', '0', NULL, NULL, NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 14, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919144031584257, '1349919143557627905', 'createBy', '创建人', 'varchar(64)', 'String', 'CreateBy', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919144044167169, '1349919143557627905', 'create_time', '创建时间', 'datetime', 'LocalDateTime', 'CreateTime', '0', '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919144065138690, '1349919143557627905', 'service_id', '服务id', 'varchar(255)', 'String', 'ServiceId', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1349919144077721601, '1349919143557627905', 'version', '版本号', 'varchar(64)', 'String', 'Version', '0', '0', NULL, NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2021-01-14 21:19:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (1359681214935588866, '1359681214683930625', 'menu_id', 'ID', 'bigint', 'Long', 'menuId', '1', '1', '0', NULL, NULL, '0', '1', '0', '0', 'EQ', 'input', '', 1, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681214948171777, '1359681214683930625', 'pid', '上级菜单ID', 'bigint', 'Long', 'pid', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 2, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681214964948994, '1359681214683930625', 'sub_count', '子菜单数目', 'int', 'Long', 'subCount', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 3, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681214977531905, '1359681214683930625', 'position', '菜单位置', 'varchar(20)', 'String', 'position', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681214994309121, '1359681214683930625', 'type', '菜单类型0目录1菜单2按钮', 'int', 'Long', 'type', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215019474946, '1359681214683930625', 'label', '菜单标题', 'varchar(128)', 'String', 'label', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215036252162, '1359681214683930625', 'component_name', '组件名称', 'varchar(128)', 'String', 'componentName', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'LIKE', 'input', '', 7, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215053029378, '1359681214683930625', 'path', '组件路径', 'varchar(128)', 'String', 'path', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 8, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215069806594, '1359681214683930625', 'sort', '排序', 'int', 'Long', 'sort', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 9, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215086583810, '1359681214683930625', 'icon', '图标', 'varchar(128)', 'String', 'icon', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 10, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215103361025, '1359681214683930625', 'link_path', '链接地址', 'varchar(255)', 'String', 'linkPath', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 11, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215115943937, '1359681214683930625', 'i_frame', '是否外链0不是1是', 'bit(1)', 'Integer', 'iFrame', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 12, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215124332545, '1359681214683930625', 'cache', '缓存0否1是', 'bit(1)', 'Integer', 'cache', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 13, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215136915458, '1359681214683930625', 'hidden', '隐藏0否1是', 'bit(1)', 'Integer', 'hidden', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 14, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215149498370, '1359681214683930625', 'permission', '权限', 'varchar(64)', 'String', 'permission', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 15, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215162081282, '1359681214683930625', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 16, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215174664193, '1359681214683930625', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 17, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215183052802, '1359681214683930625', 'create_time', '创建日期', 'datetime', 'LocalDateTime', 'createTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 18, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1359681215195635713, '1359681214683930625', 'update_time', '更新时间', 'datetime', 'LocalDateTime', 'updateTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 19, 'admin', '2021-02-10 19:50:38', 'admin', '2021-03-07 06:13:05');
INSERT INTO `gen_table_column` VALUES (1378612559137067010, '1378612558440812546', 'dict_id', 'ID', 'bigint', 'Long', 'dictId', '1', '1', '1', NULL, NULL, '0', '1', '1', '0', 'EQ', 'input', '', 1, 'admin', '2021-04-04 02:37:02', 'admin', '2021-04-05 00:27:57');
INSERT INTO `gen_table_column` VALUES (1378612559246118914, '1378612558440812546', 'name', '字典名称', 'varchar(255)', 'String', 'name', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2021-04-04 02:37:02', 'admin', '2021-04-05 00:27:57');
INSERT INTO `gen_table_column` VALUES (1378612559258701825, '1378612558440812546', 'description', '描述', 'varchar(255)', 'String', 'description', '0', '0', '0', NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-04-04 02:37:02', 'admin', '2021-04-05 00:27:58');
INSERT INTO `gen_table_column` VALUES (1378612559267090433, '1378612558440812546', 'create_by', '创建者', 'varchar(255)', 'String', 'createBy', '0', '0', '0', NULL, NULL, '0', '0', '1', '0', 'EQ', 'input', '', 4, 'admin', '2021-04-04 02:37:02', 'admin', '2021-04-05 00:27:58');
INSERT INTO `gen_table_column` VALUES (1378612559288061954, '1378612558440812546', 'update_by', '更新者', 'varchar(255)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 5, 'admin', '2021-04-04 02:37:02', 'admin', '2021-04-05 00:27:58');
INSERT INTO `gen_table_column` VALUES (1378612559304839169, '1378612558440812546', 'create_time', '创建日期', 'datetime', 'LocalDateTime', 'createTime', '0', '0', '0', NULL, NULL, '0', '0', '1', '0', 'EQ', 'datetime', '', 6, 'admin', '2021-04-04 02:37:02', 'admin', '2021-04-05 00:27:58');
INSERT INTO `gen_table_column` VALUES (1378612559321616386, '1378612558440812546', 'update_time', '更新时间', 'datetime', 'LocalDateTime', 'updateTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 7, 'admin', '2021-04-04 02:37:02', 'admin', '2021-04-05 00:27:58');
INSERT INTO `gen_table_column` VALUES (1378705600464039938, '1359681214683930625', 'delete_flag', '', 'bit(1)', 'Integer', 'deleteFlag', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2021-04-04 08:46:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (1378969825505705986, '1378969825459568642', 'dict_detail_id', 'ID', 'bigint', 'Long', 'dictDetailId', '1', '1', '1', NULL, NULL, '0', '1', '1', '0', 'EQ', 'input', '', 1, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825539260418, '1378969825459568642', 'dict_id', '字典id', 'bigint', 'Long', 'dictId', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 2, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825551843330, '1378969825459568642', 'label', '字典标签', 'varchar(255)', 'String', 'label', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825606369282, '1378969825459568642', 'value', '字典值', 'varchar(255)', 'String', 'value', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825631535106, '1378969825459568642', 'dict_sort', '排序', 'int', 'Integer', 'dictSort', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 5, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825652506626, '1378969825459568642', 'create_by', '创建者', 'varchar(255)', 'String', 'createBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 6, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825694449665, '1378969825459568642', 'update_by', '更新者', 'varchar(255)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 7, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825711226881, '1378969825459568642', 'create_time', '创建日期', 'datetime', 'LocalDateTime', 'createTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 8, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1378969825728004097, '1378969825459568642', 'update_time', '更新时间', 'datetime', 'LocalDateTime', 'updateTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 9, 'admin', '2021-04-05 02:16:41', 'admin', '2021-04-05 04:10:35');
INSERT INTO `gen_table_column` VALUES (1381094249684066305, '1381094249193332738', 'dept_id', 'ID', 'bigint', 'Long', 'deptId', '1', '1', '1', NULL, NULL, '0', '1', '0', '0', 'EQ', 'input', '', 1, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094249730203650, '1381094249193332738', 'pid', '上级部门', 'bigint', 'Long', 'pid', '0', '0', '1', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 2, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094249742786562, '1381094249193332738', 'sub_count', '子部门数目', 'int', 'Long', 'subCount', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 3, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094250015416321, '1381094249193332738', 'name', '名称', 'varchar(128)', 'String', 'name', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094250065747969, '1381094249193332738', 'sort', '排序', 'int', 'Long', 'sort', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 5, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094250095108098, '1381094249193332738', 'enabled', '状态1：有效0：无效', 'bit(1)', 'Integer', 'enabled', '0', '0', '1', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', 'dept_status', 6, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094250120273921, '1381094249193332738', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 7, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094250141245441, '1381094249193332738', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 8, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094250158022657, '1381094249193332738', 'create_time', '创建日期', 'datetime', 'LocalDateTime', 'createTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 9, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381094250170605569, '1381094249193332738', 'update_time', '更新时间', 'datetime', 'LocalDateTime', 'updateTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 10, 'admin', '2021-04-10 22:58:23', 'admin', '2021-04-10 23:02:30');
INSERT INTO `gen_table_column` VALUES (1381244387995967490, '1381244387643645953', 'role_id', 'ID', 'bigint', 'Long', 'roleId', '1', '1', '1', NULL, NULL, '0', '1', '1', '0', 'EQ', 'input', '', 1, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388029521921, '1381244387643645953', 'name', '名称', 'varchar(64)', 'String', 'name', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388042104834, '1381244387643645953', 'code', '角色编码', 'varchar(64)', 'String', 'code', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388054687745, '1381244387643645953', 'level', '角色级别', 'int', 'Long', 'level', '0', '0', '1', NULL, NULL, '1', '1', '1', '0', 'EQ', 'number', '', 4, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388071464962, '1381244387643645953', 'description', '描述', 'varchar(128)', 'String', 'description', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'EQ', 'input', '', 5, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388092436482, '1381244387643645953', 'data_scope', '数据权限', 'varchar(32)', 'String', 'dataScope', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'select', 'data_scope', 6, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388109213697, '1381244387643645953', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 7, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388125990914, '1381244387643645953', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 8, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388138573826, '1381244387643645953', 'create_time', '创建日期', 'datetime', 'LocalDateTime', 'createTime', '0', '0', '0', NULL, NULL, '0', '0', '1', '0', 'EQ', 'datetime', '', 9, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1381244388151156738, '1381244387643645953', 'update_time', '更新时间', 'datetime', 'LocalDateTime', 'updateTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 10, 'admin', '2021-04-11 08:54:59', 'admin', '2021-04-12 01:11:11');
INSERT INTO `gen_table_column` VALUES (1383305325083488258, '1383305324206878722', 'user_id', 'ID', 'bigint', 'Long', 'userId', '1', '1', '1', NULL, NULL, '0', '1', '1', '0', 'EQ', 'input', '', 1, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325268037633, '1383305324206878722', 'dept_id', '部门名称', 'bigint', 'Long', 'deptId', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'tree', '', 2, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325364506625, '1383305324206878722', 'username', '用户名', 'varchar(64)', 'String', 'username', '0', '0', '1', NULL, NULL, '1', '1', '1', '0', 'LIKE', 'input', '', 3, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325590999042, '1383305324206878722', 'nick_name', '昵称', 'varchar(128)', 'String', 'nickName', '0', '0', '0', NULL, NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325641330689, '1383305324206878722', 'gender', '性别', 'varchar(2)', 'String', 'gender', '0', '0', '1', NULL, NULL, '1', '1', '0', '0', 'EQ', 'radio', '', 5, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325695856642, '1383305324206878722', 'phone', '手机号码', 'varchar(12)', 'String', 'phone', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 6, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325725216769, '1383305324206878722', 'email', '邮箱', 'varchar(64)', 'String', 'email', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 7, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325783937026, '1383305324206878722', 'avatar_name', '头像地址', 'varchar(128)', 'String', 'avatarName', '0', '0', '0', NULL, NULL, '1', '1', '1', '0', 'LIKE', 'uploadImage', '', 8, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325821685761, '1383305324206878722', 'avatar_path', '头像真实路径', 'varchar(128)', 'String', 'avatarPath', '0', '0', '0', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 9, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325863628802, '1383305324206878722', 'password', '密码', 'varchar(128)', 'String', 'password', '0', '0', '1', NULL, NULL, '1', '1', '0', '0', 'EQ', 'input', '', 10, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305325905571842, '1383305324206878722', 'is_admin', '是否为admin账号', 'bit(1)', 'Integer', 'isAdmin', '0', '0', '1', NULL, NULL, '1', '1', '1', '0', 'EQ', 'switch', '', 11, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305326027206657, '1383305324206878722', 'enabled', '状态：1启用、0禁用', 'bit(1)', 'Integer', 'enabled', '0', '0', '1', NULL, NULL, '1', '1', '1', '1', 'EQ', 'switch', 'user_status', 12, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305326140452866, '1383305324206878722', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 13, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305326199173121, '1383305324206878722', 'update_by', '更新着', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'input', '', 14, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305326266281986, '1383305324206878722', 'pwd_reset_time', '修改密码的时间', 'datetime', 'LocalDateTime', 'pwdResetTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 15, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305326308225026, '1383305324206878722', 'create_time', '创建日期', 'datetime', 'LocalDateTime', 'createTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 16, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
INSERT INTO `gen_table_column` VALUES (1383305326371139585, '1383305324206878722', 'update_time', '更新时间', 'datetime', 'LocalDateTime', 'updateTime', '0', '0', '0', NULL, NULL, '0', '0', '0', '0', 'EQ', 'datetime', '', 17, 'admin', '2021-04-17 01:24:25', 'admin', '2021-04-17 02:37:18');
COMMIT;

-- ----------------------------
-- Table structure for mnt_app
-- ----------------------------
DROP TABLE IF EXISTS `mnt_app`;
CREATE TABLE `mnt_app` (
  `app_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `upload_path` varchar(255) DEFAULT NULL COMMENT '上传目录',
  `deploy_path` varchar(255) DEFAULT NULL COMMENT '部署路径',
  `backup_path` varchar(255) DEFAULT NULL COMMENT '备份路径',
  `port` int DEFAULT NULL COMMENT '应用端口',
  `start_script` varchar(4000) DEFAULT NULL COMMENT '启动脚本',
  `deploy_script` varchar(4000) DEFAULT NULL COMMENT '部署脚本',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='应用管理';

-- ----------------------------
-- Table structure for mnt_database
-- ----------------------------
DROP TABLE IF EXISTS `mnt_database`;
CREATE TABLE `mnt_database` (
  `db_id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `jdbc_url` varchar(255) NOT NULL COMMENT 'jdbc连接',
  `user_name` varchar(255) NOT NULL COMMENT '账号',
  `pwd` varchar(255) NOT NULL COMMENT '密码',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`db_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据库管理';

-- ----------------------------
-- Table structure for mnt_deploy
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy`;
CREATE TABLE `mnt_deploy` (
  `deploy_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `app_id` bigint DEFAULT NULL COMMENT '应用编号',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`deploy_id`) USING BTREE,
  KEY `FK6sy157pseoxx4fmcqr1vnvvhy` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部署管理';

-- ----------------------------
-- Table structure for mnt_deploy_history
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_history`;
CREATE TABLE `mnt_deploy_history` (
  `history_id` varchar(50) NOT NULL COMMENT 'ID',
  `app_name` varchar(255) NOT NULL COMMENT '应用名称',
  `deploy_date` datetime NOT NULL COMMENT '部署日期',
  `deploy_user` varchar(50) NOT NULL COMMENT '部署用户',
  `ip` varchar(20) NOT NULL COMMENT '服务器IP',
  `deploy_id` bigint DEFAULT NULL COMMENT '部署编号',
  PRIMARY KEY (`history_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部署历史管理';

-- ----------------------------
-- Table structure for mnt_deploy_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_server`;
CREATE TABLE `mnt_deploy_server` (
  `deploy_id` bigint NOT NULL COMMENT '部署ID',
  `server_id` bigint NOT NULL COMMENT '服务ID',
  PRIMARY KEY (`deploy_id`,`server_id`) USING BTREE,
  KEY `FKeaaha7jew9a02b3bk9ghols53` (`server_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='应用与服务器关联';

-- ----------------------------
-- Table structure for mnt_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_server`;
CREATE TABLE `mnt_server` (
  `server_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `port` int DEFAULT NULL COMMENT '端口',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`server_id`) USING BTREE,
  KEY `idx_ip` (`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='服务器管理';

-- ----------------------------
-- Records of mnt_server
-- ----------------------------
BEGIN;
INSERT INTO `mnt_server` VALUES (1, 'root', '132.232.129.20', '腾讯云', 'Dqjdda1996.', 8013, NULL, NULL, '2019-11-24 20:35:02', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '上级部门',
  `sub_count` int DEFAULT '0' COMMENT '子部门数目',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `sort` int DEFAULT '999' COMMENT '排序',
  `enabled` char(1) NOT NULL DEFAULT '' COMMENT '状态1：有效0：无效',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1381189577242353666 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (2, 7, 0, '研发部', 3, '1', NULL, 'admin', '2019-03-25 09:15:32', '2020-05-10 17:37:58');
INSERT INTO `sys_dept` VALUES (5, 7, 0, '运维部', 4, '1', NULL, NULL, '2019-03-25 09:20:44', NULL);
INSERT INTO `sys_dept` VALUES (6, 8, 0, '测试部', 6, '1', NULL, NULL, '2019-03-25 09:52:18', NULL);
INSERT INTO `sys_dept` VALUES (7, 0, 2, '华南分部', 0, '1', NULL, 'admin', '2019-03-25 11:04:50', '2020-05-10 19:59:12');
INSERT INTO `sys_dept` VALUES (8, 0, 2, '华北分部', 1, '1', NULL, 'admin', '2019-03-25 11:04:53', '2020-05-14 12:54:00');
INSERT INTO `sys_dept` VALUES (15, 8, 0, 'UI部门', 7, '1', 'admin', 'admin', '2020-05-13 22:56:53', '2020-05-14 12:54:13');
INSERT INTO `sys_dept` VALUES (1381189577242353665, 2, 0, '测试部', 1, '1', 'admin', 'admin', '2021-04-11 05:17:11', '2021-04-11 08:39:40');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '字典名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1381260254930542595 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (1, 'user_status', '用户状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (4, 'dept_status', '部门状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '岗位状态', NULL, 'admin', '2019-10-27 20:31:36', '2021-04-05 01:32:22');
INSERT INTO `sys_dict` VALUES (1381260254930542594, 'data_scope', '数据权限', 'admin', NULL, '2021-04-11 09:58:02', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `dict_detail_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_name` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `dict_id` bigint DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) NOT NULL COMMENT '字典标签',
  `value` varchar(255) NOT NULL COMMENT '字典值',
  `dict_sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_detail_id`) USING BTREE,
  KEY `FK5tpkputc6d9nboxojdbgnpmyb` (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1381260847417925635 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典详情';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_detail` VALUES (1, 'user_status', 1, '激活', '1', 1, NULL, 'admin', '2019-10-27 20:31:36', '2021-04-17 09:01:11');
INSERT INTO `sys_dict_detail` VALUES (2, 'user_status', 1, '禁用', '0', 2, NULL, 'admin', NULL, '2021-04-17 09:01:18');
INSERT INTO `sys_dict_detail` VALUES (3, 'dept_status', 4, '启用', '1', 1, NULL, 'admin', NULL, '2021-04-11 05:09:01');
INSERT INTO `sys_dict_detail` VALUES (4, 'dept_status', 4, '停用', '0', 2, NULL, 'admin', '2019-10-27 20:31:36', '2021-04-11 05:09:08');
INSERT INTO `sys_dict_detail` VALUES (5, 'job_status', 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (6, 'job_status', 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (1381260327609442305, 'data_scope', 1381260254930542594, '全部', '0', 0, 'admin', NULL, '2021-04-11 09:58:19', NULL);
INSERT INTO `sys_dict_detail` VALUES (1381260643822215170, 'data_scope', 1381260254930542594, '自定义', '1', 1, 'admin', NULL, '2021-04-11 09:59:35', NULL);
INSERT INTO `sys_dict_detail` VALUES (1381260730463952898, 'data_scope', 1381260254930542594, '本级及以下部门', '2', 2, 'admin', NULL, '2021-04-11 09:59:55', NULL);
INSERT INTO `sys_dict_detail` VALUES (1381260786126561281, 'data_scope', 1381260254930542594, '本级', '3', 3, 'admin', NULL, '2021-04-11 10:00:09', NULL);
INSERT INTO `sys_dict_detail` VALUES (1381260847417925634, 'data_scope', 1381260254930542594, '仅本人', '4', 4, 'admin', NULL, '2021-04-11 10:00:23', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(64) NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `job_sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE,
  UNIQUE KEY `uniq_name` (`name`),
  KEY `idx_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='岗位';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job` VALUES (8, '人事专员', b'1', 3, NULL, NULL, '2019-03-29 14:52:28', NULL);
INSERT INTO `sys_job` VALUES (10, '产品经理', b'1', 4, NULL, NULL, '2019-03-29 14:55:51', NULL);
INSERT INTO `sys_job` VALUES (11, '全栈开发', b'1', 2, NULL, 'admin', '2019-03-31 13:39:30', '2020-05-05 11:33:43');
INSERT INTO `sys_job` VALUES (12, '软件测试', b'1', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作模块',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作方式',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求URI',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '操作提交的数据',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作IP',
  `json_result` mediumtext COMMENT '返回参数',
  `cost_time` int DEFAULT NULL COMMENT '执行时间 单位ms',
  `user_agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作浏览器',
  `error_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常信息',
  `operator_type` tinyint(1) DEFAULT NULL COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `business_type` tinyint DEFAULT NULL COMMENT '业务类型（0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据）',
  `status` tinyint(1) DEFAULT NULL COMMENT '操作状态（0正常 1异常）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `createBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `service_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务id',
  `version` varchar(64) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`log_id`) USING BTREE,
  KEY `log_create_time_index` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1384088018335334402 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int DEFAULT '0' COMMENT '子菜单数目',
  `position` varchar(20) NOT NULL DEFAULT 'left' COMMENT '菜单位置',
  `type` int DEFAULT NULL COMMENT '菜单类型0目录1菜单2按钮',
  `label` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单标题',
  `component_name` varchar(128) DEFAULT NULL COMMENT '组件名称',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件路径',
  `sort` int DEFAULT NULL COMMENT '排序',
  `icon` varchar(128) DEFAULT NULL COMMENT '图标',
  `link_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '链接地址',
  `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链0不是1是',
  `cache` bit(1) DEFAULT b'0' COMMENT '缓存0否1是',
  `hidden` bit(1) DEFAULT b'0' COMMENT '隐藏0否1是',
  `permission` varchar(64) DEFAULT NULL COMMENT '权限',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE KEY `uniq_title` (`label`),
  KEY `inx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=1378604945321873411 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, 7, 'left', 0, '系统管理', '', '/sys', 1, 'icon-quanxianguanli', 'system', b'0', b'0', b'0', '', NULL, 'admin', '2018-12-18 15:11:00', '2021-04-17 01:35:38', b'0');
INSERT INTO `sys_menu` VALUES (2, 1, 3, 'left', 1, '用户管理', 'User', '/system/user/index', 2, 'icon-caidan', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '2018-12-18 15:14:44', NULL, b'0');
INSERT INTO `sys_menu` VALUES (3, 1, 3, 'left', 1, '角色管理', 'Role', '/system/role/index', 3, 'icon-caidan', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '2018-12-18 15:16:07', NULL, b'0');
INSERT INTO `sys_menu` VALUES (5, 1, 3, 'left', 1, '菜单管理', 'Menu', '/system/menu/index', 5, 'icon-caidan', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '2018-12-18 15:17:28', NULL, b'0');
INSERT INTO `sys_menu` VALUES (6, 0, 5, 'left', 0, '系统监控', '', '/monitor', 14, 'icon-caidan', 'monitor', b'0', b'0', b'0', '', NULL, 'admin', '2018-12-18 15:17:48', '2021-04-17 01:35:57', b'0');
INSERT INTO `sys_menu` VALUES (7, 6, 0, 'left', 1, '操作日志', 'Log', '/monitor/log/index', 11, 'icon-caidan', 'logs', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:18:26', NULL, b'0');
INSERT INTO `sys_menu` VALUES (9, 6, 0, 'left', 1, 'SQL监控', 'Sql', '/monitor/sql/index', 18, 'icon-caidan', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:19:34', NULL, b'0');
INSERT INTO `sys_menu` VALUES (21, 0, 2, 'left', 0, '多级菜单', NULL, '/menu', 900, 'icon-caidan', 'nested', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:22:03', NULL, b'0');
INSERT INTO `sys_menu` VALUES (22, 21, 2, 'left', 1, '二级菜单1', NULL, '/nested/menu1/index', 999, 'icon-caidan', 'menu1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:29', NULL, b'0');
INSERT INTO `sys_menu` VALUES (23, 21, 0, 'left', 1, '二级菜单2', NULL, '/nested/menu2/index', 999, 'icon-caidan', 'menu2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:57', NULL, b'0');
INSERT INTO `sys_menu` VALUES (24, 22, 0, 'left', 1, '三级菜单1', NULL, '/nested/menu1/menu1-1', 999, 'icon-caidan', 'menu1-1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:24:48', NULL, b'0');
INSERT INTO `sys_menu` VALUES (27, 22, 0, 'left', 1, '三级菜单2', NULL, '/nested/menu1/menu1-2', 999, 'icon-caidan', 'menu1-2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-07 17:27:32', NULL, b'0');
INSERT INTO `sys_menu` VALUES (30, 36, 0, 'left', 1, '代码生成', 'GeneratorIndex', '/dev/gen/index', 32, 'icon-caidan', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '2019-01-11 15:45:55', NULL, b'0');
INSERT INTO `sys_menu` VALUES (32, 6, 0, 'left', 1, '异常日志', 'ErrorLog', '/monitor/log/errorLog', 12, 'icon-caidan', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-13 13:49:03', NULL, b'0');
INSERT INTO `sys_menu` VALUES (35, 1, 3, 'left', 1, '部门管理', 'Dept', '/system/dept/index', 6, 'icon-caidan', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '2019-03-25 09:46:00', NULL, b'0');
INSERT INTO `sys_menu` VALUES (36, 0, 8, 'left', 0, '敏捷开发', '', '/dev', 30, 'icon-caidan', 'sys-tools', b'0', b'0', b'0', '', NULL, 'admin', '2019-03-29 10:57:35', '2021-04-17 01:38:02', b'0');
INSERT INTO `sys_menu` VALUES (37, 1, 3, 'left', 1, '岗位管理', 'Job', '/system/job/index', 7, 'icon-caidan', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '2019-03-29 13:51:18', NULL, b'0');
INSERT INTO `sys_menu` VALUES (38, 36, 0, 'left', 1, '接口文档', 'Swagger', '/dev/doc/index', 36, 'icon-caidan', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 19:57:53', NULL, b'0');
INSERT INTO `sys_menu` VALUES (39, 1, 3, 'left', 1, '字典管理', 'Dict', '/system/dict/index', 8, 'icon-caidan', 'dict', b'0', b'0', b'0', 'dict:list', NULL, 'admin', '2019-04-10 11:49:04', '2021-04-04 02:28:29', b'0');
INSERT INTO `sys_menu` VALUES (41, 6, 0, 'left', 1, '在线用户', 'OnlineUser', '/monitor/online/index', 10, 'icon-caidan', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '2019-10-26 22:08:43', NULL, b'0');
INSERT INTO `sys_menu` VALUES (44, 2, 0, 'left', 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '2019-10-29 10:59:46', NULL, b'0');
INSERT INTO `sys_menu` VALUES (45, 2, 0, 'left', 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '2019-10-29 11:00:08', NULL, b'0');
INSERT INTO `sys_menu` VALUES (46, 2, 0, 'left', 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '2019-10-29 11:00:23', NULL, b'0');
INSERT INTO `sys_menu` VALUES (48, 3, 0, 'left', 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '2019-10-29 12:45:34', NULL, b'0');
INSERT INTO `sys_menu` VALUES (49, 3, 0, 'left', 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '2019-10-29 12:46:16', NULL, b'0');
INSERT INTO `sys_menu` VALUES (50, 3, 0, 'left', 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '2019-10-29 12:46:51', NULL, b'0');
INSERT INTO `sys_menu` VALUES (52, 5, 0, 'left', 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '2019-10-29 12:55:07', NULL, b'0');
INSERT INTO `sys_menu` VALUES (53, 5, 0, 'left', 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '2019-10-29 12:55:40', NULL, b'0');
INSERT INTO `sys_menu` VALUES (54, 5, 0, 'left', 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '2019-10-29 12:56:00', NULL, b'0');
INSERT INTO `sys_menu` VALUES (56, 35, 0, 'left', 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '2019-10-29 12:57:09', NULL, b'0');
INSERT INTO `sys_menu` VALUES (57, 35, 0, 'left', 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '2019-10-29 12:57:27', NULL, b'0');
INSERT INTO `sys_menu` VALUES (58, 35, 0, 'left', 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '2019-10-29 12:57:41', NULL, b'0');
INSERT INTO `sys_menu` VALUES (60, 37, 0, 'left', 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '2019-10-29 12:58:27', NULL, b'0');
INSERT INTO `sys_menu` VALUES (61, 37, 0, 'left', 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '2019-10-29 12:58:45', NULL, b'0');
INSERT INTO `sys_menu` VALUES (62, 37, 0, 'left', 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '2019-10-29 12:59:04', NULL, b'0');
INSERT INTO `sys_menu` VALUES (64, 39, 0, 'left', 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '2019-10-29 13:00:17', NULL, b'0');
INSERT INTO `sys_menu` VALUES (65, 39, 0, 'left', 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '2019-10-29 13:00:42', NULL, b'0');
INSERT INTO `sys_menu` VALUES (66, 39, 0, 'left', 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '2019-10-29 13:00:59', NULL, b'0');
INSERT INTO `sys_menu` VALUES (80, 6, 0, 'left', 1, '服务监控', 'ServerMonitor', '/monitor/server/index', 14, 'icon-caidan', 'server', b'0', b'0', b'0', 'monitor:list', NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50', b'0');
INSERT INTO `sys_menu` VALUES (117, 36, 0, 'left', 1, '表单设计', 'form-design', '/dev/form/formDesign', 999, 'icon-caidan', NULL, b'0', b'0', b'0', NULL, NULL, NULL, '2021-01-22 11:24:55', NULL, b'0');
INSERT INTO `sys_menu` VALUES (118, 36, 0, 'left', 1, '表格设计', 'crud-design', '/dev/crud/crudDesign', 999, 'icon-caidan', NULL, b'0', b'0', b'0', NULL, NULL, NULL, '2021-01-25 13:56:15', NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(100) DEFAULT NULL COMMENT '负责人',
  `email` varchar(100) DEFAULT NULL COMMENT '报警邮箱',
  `sub_task` varchar(100) DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` bit(1) DEFAULT NULL COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE,
  KEY `inx_is_pause` (`is_pause`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, NULL, NULL, 'admin', '2019-08-22 14:08:29', '2020-05-05 17:26:19');
INSERT INTO `sys_quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', 'Zheng Jie', '', '2,6', b'1', NULL, 'admin', '2019-09-26 16:44:39', '2020-05-05 20:45:39');
INSERT INTO `sys_quartz_job` VALUES (5, 'Test', '0/5 * * * * ?', b'1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:32:41', '2020-05-05 20:36:13');
INSERT INTO `sys_quartz_job` VALUES (6, 'testTask', '0/5 * * * * ?', b'1', '测试3', 'run2', NULL, '测试3', 'Zheng Jie', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:35:41', '2020-05-05 20:36:07');
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `is_success` bit(1) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `time` bigint DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务日志';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '角色编码',
  `level` int DEFAULT NULL COMMENT '角色级别',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `data_scope` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE KEY `uniq_name` (`name`),
  KEY `role_name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1383252458704318467 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'super_admin', 1, '-', '0', NULL, 'admin', '2018-11-23 11:04:00', '2021-04-12 05:26:49');
INSERT INTO `sys_role` VALUES (2, '测试', 'test', 1, '2', '0', 'admin', NULL, '2021-04-16 10:43:02', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL,
  `dept_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`dept_id`) USING BTREE,
  KEY `idx_dept_id` (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色部门关联';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色菜单关联';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (5, 1);
INSERT INTO `sys_role_menu` VALUES (6, 1);
INSERT INTO `sys_role_menu` VALUES (7, 1);
INSERT INTO `sys_role_menu` VALUES (9, 1);
INSERT INTO `sys_role_menu` VALUES (21, 1);
INSERT INTO `sys_role_menu` VALUES (22, 1);
INSERT INTO `sys_role_menu` VALUES (23, 1);
INSERT INTO `sys_role_menu` VALUES (24, 1);
INSERT INTO `sys_role_menu` VALUES (27, 1);
INSERT INTO `sys_role_menu` VALUES (30, 1);
INSERT INTO `sys_role_menu` VALUES (32, 1);
INSERT INTO `sys_role_menu` VALUES (35, 1);
INSERT INTO `sys_role_menu` VALUES (36, 1);
INSERT INTO `sys_role_menu` VALUES (37, 1);
INSERT INTO `sys_role_menu` VALUES (38, 1);
INSERT INTO `sys_role_menu` VALUES (39, 1);
INSERT INTO `sys_role_menu` VALUES (41, 1);
INSERT INTO `sys_role_menu` VALUES (80, 1);
INSERT INTO `sys_role_menu` VALUES (117, 1);
INSERT INTO `sys_role_menu` VALUES (118, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (5, 2);
INSERT INTO `sys_role_menu` VALUES (21, 2);
INSERT INTO `sys_role_menu` VALUES (36, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门名称',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `nick_name` varchar(128) DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(12) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `avatar_name` varchar(128) DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(128) DEFAULT NULL COMMENT '头像真实路径',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `is_admin` bit(1) DEFAULT b'0' COMMENT '是否为admin账号',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '状态：1启用、0禁用',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新着',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `uniq_username` (`username`),
  UNIQUE KEY `uniq_email` (`email`),
  KEY `idx_dept_id` (`dept_id`) USING BTREE,
  KEY `idx_avatar_name` (`avatar_name`) USING BTREE,
  KEY `inx_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 2, 'admin', '管理员', '男', '18888888888', '201507802@qq.com', NULL, NULL, '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'1', b'1', NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2020-05-05 10:12:21');
INSERT INTO `sys_user` VALUES (2, 2, 'test', '测试', '男', '18888888888', '231@qq.com', NULL, NULL, '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'0', b'1', 'admin', 'admin', NULL, '2020-05-05 11:15:49', '2020-05-05 11:20:51');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_job`;
CREATE TABLE `sys_user_job` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `job_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_job` VALUES (1, 11);
INSERT INTO `sys_user_job` VALUES (1, 12);
INSERT INTO `sys_user_job` VALUES (2, 12);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
COMMIT;

-- ----------------------------
-- Table structure for tool_alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_alipay_config`;
CREATE TABLE `tool_alipay_config` (
  `config_id` bigint NOT NULL COMMENT 'ID',
  `app_id` varchar(255) DEFAULT NULL COMMENT '应用ID',
  `charset` varchar(255) DEFAULT NULL COMMENT '编码',
  `format` varchar(255) DEFAULT NULL COMMENT '类型 固定格式json',
  `gateway_url` varchar(255) DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '异步回调',
  `private_key` text COMMENT '私钥',
  `public_key` text COMMENT '公钥',
  `return_url` varchar(255) DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) DEFAULT NULL COMMENT '商户号',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='支付宝配置类';

-- ----------------------------
-- Records of tool_alipay_config
-- ----------------------------
BEGIN;
INSERT INTO `tool_alipay_config` VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');
COMMIT;

-- ----------------------------
-- Table structure for tool_email_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_email_config`;
CREATE TABLE `tool_email_config` (
  `config_id` bigint NOT NULL COMMENT 'ID',
  `from_user` varchar(255) DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) DEFAULT NULL COMMENT '密码',
  `port` varchar(255) DEFAULT NULL COMMENT '端口',
  `user` varchar(255) DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='邮箱配置';

-- ----------------------------
-- Table structure for tool_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `tool_local_storage`;
CREATE TABLE `tool_local_storage` (
  `storage_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `real_name` varchar(255) DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `size` varchar(100) DEFAULT NULL COMMENT '大小',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='本地存储';

-- ----------------------------
-- Table structure for tool_picture
-- ----------------------------
DROP TABLE IF EXISTS `tool_picture`;
CREATE TABLE `tool_picture` (
  `picture_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `filename` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `md5code` varchar(255) DEFAULT NULL COMMENT '文件的MD5值',
  `size` varchar(255) DEFAULT NULL COMMENT '图片大小',
  `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `delete_url` varchar(255) DEFAULT NULL COMMENT '删除的URL',
  `height` varchar(255) DEFAULT NULL COMMENT '图片高度',
  `width` varchar(255) DEFAULT NULL COMMENT '图片宽度',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `create_time` datetime DEFAULT NULL COMMENT '上传日期',
  PRIMARY KEY (`picture_id`) USING BTREE,
  UNIQUE KEY `uniq_md5_code` (`md5code`),
  KEY `inx_url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Sm.Ms图床';

-- ----------------------------
-- Table structure for tool_qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_config`;
CREATE TABLE `tool_qiniu_config` (
  `config_id` bigint NOT NULL COMMENT 'ID',
  `access_key` text COMMENT 'accessKey',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) NOT NULL COMMENT '外链域名',
  `secret_key` text COMMENT 'secretKey',
  `type` varchar(255) DEFAULT NULL COMMENT '空间类型',
  `zone` varchar(255) DEFAULT NULL COMMENT '机房',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='七牛云配置';

-- ----------------------------
-- Table structure for tool_qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_content`;
CREATE TABLE `tool_qiniu_content` (
  `content_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型：私有或公开',
  `url` varchar(255) DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `update_time` datetime DEFAULT NULL COMMENT '上传或同步的时间',
  PRIMARY KEY (`content_id`) USING BTREE,
  UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='七牛云文件存储';

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file` (
  `uuid` varchar(32) NOT NULL COMMENT '编号',
  `rel_obj_type` varchar(50) DEFAULT NULL COMMENT '关联对象类',
  `rel_obj_id` varchar(32) DEFAULT NULL COMMENT '关联对象ID',
  `rel_obj_field` varchar(50) DEFAULT NULL COMMENT '关联对象属性名称',
  `file_name` varchar(100) NOT NULL COMMENT '文件名',
  `storage_path` varchar(200) NOT NULL COMMENT '存储路径',
  `access_url` varchar(200) DEFAULT NULL COMMENT '访问地址',
  `file_type` varchar(20) DEFAULT NULL COMMENT '文件类型',
  `data_count` int DEFAULT '0' COMMENT '数据量',
  `description` varchar(100) DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `idx_upload_file` (`rel_obj_type`,`rel_obj_id`,`rel_obj_field`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传文件';

SET FOREIGN_KEY_CHECKS = 1;
