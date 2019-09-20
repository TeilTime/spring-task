/*
 Navicat Premium Data Transfer

 Source Server         : 47.93.151.74
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.93.151.74:13306
 Source Schema         : fastdev

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 20/09/2019 15:49:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `id` decimal(20, 0) NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` decimal(20, 0) NULL DEFAULT NULL,
  `job_class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parameter` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` decimal(20, 0) NULL DEFAULT NULL,
  `CRNUMBER` bigint(20) NULL DEFAULT NULL COMMENT '创建人Id',
  `CRTIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) NULL DEFAULT NULL COMMENT '最后修改人Id',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  `cruser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_job_class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES (1169524268174659586, NULL, NULL, 0, 'com.jnetdata.simple.manage.plantask.job.SampleJob', '0/10 * * * * ? ', 'execute', '测试用例2', 0, NULL, '2019-09-05 16:14:49', NULL, '2019-09-20 15:48:07', NULL, 'com.jnetdata.simple.manage.plantask.job.SampleJob');
INSERT INTO `sys_quartz_job` VALUES (1170147233513816066, NULL, NULL, 0, 'com.jnetdata.simple.manage.plantask.job.SampleParamJob', '0/10 * * * * ? ', 'execute', '测试用例', -1, NULL, '2019-09-07 09:30:15', NULL, '2019-09-17 16:12:02', NULL, 'com.jnetdata.simple.manage.plantask.job.SampleParamJob');

SET FOREIGN_KEY_CHECKS = 1;
