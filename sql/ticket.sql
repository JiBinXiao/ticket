/*
 Navicat Premium Data Transfer

 Source Server         : 49.232.45.219
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 49.232.45.219:3306
 Source Schema         : ticket

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 13/08/2019 23:01:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `clientIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端请求ip',
  `uri` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端请求路径',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '终端请求方式,普通请求,ajax请求',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式method,post,get等',
  `classMethod` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的类及方法',
  `paramData` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数内容,json',
  `sessionId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求接口唯一session标识',
  `createBy` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问女用户',
  `createTime` datetime NULL DEFAULT NULL COMMENT '请求时间',
  `returnTime` datetime NULL DEFAULT NULL COMMENT '接口返回时间',
  `timeConsuming` int(11) NULL DEFAULT NULL COMMENT '请求耗时毫秒单位',
  `httpStatusCode` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求时httpStatusCode代码，如：200,400,404等',
  `exceptionMessage` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3920 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` int(11) NOT NULL COMMENT '分类id和ticket_category主键关联',
  `price` decimal(10, 2) NOT NULL COMMENT '单价',
  `aisle` int(255) NULL DEFAULT NULL COMMENT '所属通道',
  `floor` int(255) NULL DEFAULT NULL COMMENT '所属楼层',
  `rowNum` int(255) NULL DEFAULT NULL COMMENT '所属行数',
  `columnNum` int(255) NULL DEFAULT NULL COMMENT '所属列数',
  `st` int(255) NOT NULL DEFAULT 1 COMMENT '状态',
  `cuser` int(11) NOT NULL COMMENT '创建人',
  `uuser` int(11) NOT NULL COMMENT '修改人',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `reSeller` int(11) NOT NULL DEFAULT 2 COMMENT '是否转票 1是 2否',
  `secrecy` int(11) NOT NULL DEFAULT 1 COMMENT '是否保密个人信息  1是 2 否',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `queryAll_index`(`category_id`, `price`, `aisle`, `floor`, `rowNum`, `columnNum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ticket_attention
-- ----------------------------
DROP TABLE IF EXISTS `ticket_attention`;
CREATE TABLE `ticket_attention`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '关联用户表id',
  `tid` int(11) NOT NULL COMMENT '关联门票id',
  `st` int(11) NOT NULL DEFAULT 1 COMMENT '状态 1 使用 2未使用',
  `ctime` datetime NOT NULL,
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ticket_category
-- ----------------------------
DROP TABLE IF EXISTS `ticket_category`;
CREATE TABLE `ticket_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `sdate` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `edate` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `property` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演出性质',
  `st` int(255) NOT NULL DEFAULT 1 COMMENT '状态',
  `cuser` int(11) NOT NULL COMMENT '创建人',
  `uuser` int(11) NOT NULL COMMENT '修改人',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ticket_user
-- ----------------------------
DROP TABLE IF EXISTS `ticket_user`;
CREATE TABLE `ticket_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `password` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` int(11) NOT NULL DEFAULT 0 COMMENT '性别 0保密 1 男 2 女',
  `wxnum` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `ltime` datetime NULL DEFAULT NULL COMMENT '上一次登录时间',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `st` int(11) NOT NULL DEFAULT 1 COMMENT '状态 1使用中',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
