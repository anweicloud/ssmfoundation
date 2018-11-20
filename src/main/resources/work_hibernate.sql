/*
Navicat MySQL Data Transfer

Source Server         : LocalMysql
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : work_hibernate

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2018-11-20 10:23:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for access_permission
-- ----------------------------
DROP TABLE IF EXISTS `access_permission`;
CREATE TABLE `access_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of access_permission
-- ----------------------------

-- ----------------------------
-- Table structure for access_role
-- ----------------------------
DROP TABLE IF EXISTS `access_role`;
CREATE TABLE `access_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of access_role
-- ----------------------------

-- ----------------------------
-- Table structure for access_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `access_role_permission`;
CREATE TABLE `access_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perm_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of access_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for access_user
-- ----------------------------
DROP TABLE IF EXISTS `access_user`;
CREATE TABLE `access_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `registerTime` datetime DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of access_user
-- ----------------------------
INSERT INTO `access_user` VALUES ('1', 'Anwei', '123', '18286176666', '0', '2018-11-20 10:16:56', '2018-11-20 10:16:53', '2018-11-20 10:16:58');

-- ----------------------------
-- Table structure for access_user_role
-- ----------------------------
DROP TABLE IF EXISTS `access_user_role`;
CREATE TABLE `access_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of access_user_role
-- ----------------------------
