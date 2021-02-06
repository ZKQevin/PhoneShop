/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50557
Source Host           : localhost:3306
Source Database       : phone

Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001

Date: 2021-01-05 14:25:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `username` varchar(10) NOT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('admin', '1', 'admin');
