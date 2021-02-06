/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50557
Source Host           : localhost:3306
Source Database       : phone

Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001

Date: 2021-01-05 14:25:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_phone`
-- ----------------------------
DROP TABLE IF EXISTS `tb_phone`;
CREATE TABLE `tb_phone` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(15) DEFAULT NULL COMMENT '手机类型',
  `name` varchar(15) NOT NULL,
  `image` varchar(20) NOT NULL,
  `intro` varchar(15) NOT NULL DEFAULT '还没有介绍哦' COMMENT '手机介绍',
  `price` int(10) NOT NULL,
  `recom` char(1) NOT NULL DEFAULT '0' COMMENT '是否推荐1表示推荐0不推荐',
  `act` int(10) DEFAULT '0' COMMENT '活动价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_phone
-- ----------------------------
INSERT INTO `tb_phone` VALUES ('1', 'OPPO', 'OPPOR11', 'hahahah', '还没有介绍哦', '2599', '0', '0');
INSERT INTO `tb_phone` VALUES ('2', 'huawei', 'huawei mate10', 'null', '还没有介绍哦', '4999', '0', '0');
INSERT INTO `tb_phone` VALUES ('3', 'xiaomi', 'xiaomi 10', 'null', '还没有介绍哦', '3999', '0', '0');
INSERT INTO `tb_phone` VALUES ('4', 'oppo', 'oppo Reno十倍变焦版', 'null', '还没有介绍哦', '4799', '0', '0');
