/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50106
Source Host           : localhost:3306
Source Database       : pos

Target Server Type    : MYSQL
Target Server Version : 50106
File Encoding         : 65001

Date: 2015-10-19 23:28:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(40) default NULL,
  `display_order` int(11) default NULL,
  `description` char(255) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '設備租金', '99', '', '0');
INSERT INTO `category` VALUES ('2', '進料成本', '99', '', '0');
INSERT INTO `category` VALUES ('3', '進貨退出及折讓', '99', '', '0');
INSERT INTO `category` VALUES ('4', '進貨折讓', '99', '', '0');
INSERT INTO `category` VALUES ('5', 'OEM成本', '99', '', '0');
INSERT INTO `category` VALUES ('99', '組合商品', '1', '', '1');
INSERT INTO `category` VALUES ('100', '未分類', '1', '', '1');
INSERT INTO `category` VALUES ('104', '泡麵', '12', '', '1');
INSERT INTO `category` VALUES ('105', '小說', '10', '', '1');
INSERT INTO `category` VALUES ('106', '飲料', '10', '', '1');
INSERT INTO `category` VALUES ('107', '包包', '10', '', '1');
INSERT INTO `category` VALUES ('111', '123123', '12', '', '1');
INSERT INTO `category` VALUES ('114', '111111', '12', '', '1');
INSERT INTO `category` VALUES ('115', '222', '22', '', '1');
INSERT INTO `category` VALUES ('117', '44444', '44', '', '0');
INSERT INTO `category` VALUES ('118', '5555', '55', '', '0');

-- ----------------------------
-- Table structure for `checkout`
-- ----------------------------
DROP TABLE IF EXISTS `checkout`;
CREATE TABLE `checkout` (
  `id` int(11) NOT NULL,
  `user_id` int(11) default NULL,
  `cash_count` int(11) default NULL,
  `cash_item` int(11) default NULL,
  `cash_amount` double(11,2) default NULL,
  `invoice_count` int(11) default NULL,
  `invoice_item` int(11) default NULL,
  `invoice_amount` double(11,2) default NULL,
  `total_count` int(11) default NULL,
  `total_item` int(11) default NULL,
  `total_amount` double(11,2) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_5` USING BTREE (`user_id`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of checkout
-- ----------------------------
INSERT INTO `checkout` VALUES ('8', '10', '8', '50', '2774.00', '0', null, null, '8', '76', '3290.00', '2013-06-09 22:30:40');
INSERT INTO `checkout` VALUES ('11', '10', '3', '15', '320.00', '0', null, null, '3', '50', '1766.00', '2013-06-13 01:42:32');
INSERT INTO `checkout` VALUES ('12', '10', '0', null, null, '0', null, null, '0', '0', '0.00', '2013-06-13 01:48:59');
INSERT INTO `checkout` VALUES ('13', '10', '5', '44', '785.00', '0', null, null, '5', '44', '785.00', '2013-07-02 23:24:53');
INSERT INTO `checkout` VALUES ('14', '10', '3', '30', '531.00', '0', null, null, '3', '30', '531.00', '2013-07-07 01:19:37');
INSERT INTO `checkout` VALUES ('15', '10', '7', '62', '1451.00', '0', null, null, '7', '62', '1451.00', '2013-07-09 20:47:55');
INSERT INTO `checkout` VALUES ('16', '10', '4', '115', '2370.00', '0', null, null, '4', '115', '2370.00', '2013-07-09 22:45:35');
INSERT INTO `checkout` VALUES ('17', '10', '1', '7', '109.00', '0', null, null, '1', '7', '109.00', '2013-07-09 22:53:54');
INSERT INTO `checkout` VALUES ('18', '10', '5', '57', '1146.00', '0', null, null, '5', '57', '1146.00', '2013-07-10 21:24:38');
INSERT INTO `checkout` VALUES ('19', '10', '0', null, null, '0', null, null, '0', '0', '0.00', '2013-07-10 21:24:47');
INSERT INTO `checkout` VALUES ('20', '10', '2', '10', '187.00', '0', null, null, '2', '10', '187.00', '2013-07-11 20:52:04');
INSERT INTO `checkout` VALUES ('21', '10', '0', null, null, '0', null, null, '0', '4', '55.00', '2013-07-12 16:29:05');
INSERT INTO `checkout` VALUES ('22', '10', '0', null, null, '0', null, null, '0', '4', '55.00', '2013-07-12 16:30:04');
INSERT INTO `checkout` VALUES ('23', '10', '0', null, null, '0', null, null, '0', '4', '55.00', '2013-07-12 16:30:24');
INSERT INTO `checkout` VALUES ('24', '10', '0', null, null, '0', null, null, '0', '4', '55.00', '2013-07-12 16:31:11');
INSERT INTO `checkout` VALUES ('25', '10', '0', null, null, '0', null, null, '0', '4', '55.00', '2013-07-12 16:32:54');
INSERT INTO `checkout` VALUES ('26', '10', '0', null, null, '0', null, null, '0', '0', '0.00', '2013-07-12 16:35:25');
INSERT INTO `checkout` VALUES ('27', '10', '0', null, null, '0', null, null, '0', '9', '183.00', '2013-07-12 16:37:01');
INSERT INTO `checkout` VALUES ('28', '10', '0', null, null, '0', null, null, '0', '9', '183.00', '2013-07-12 16:37:04');
INSERT INTO `checkout` VALUES ('29', '10', '0', null, null, '0', null, null, '0', '9', '183.00', '2013-07-12 16:42:28');
INSERT INTO `checkout` VALUES ('30', '10', '0', null, null, '0', null, null, '0', '9', '183.00', '2013-07-12 16:42:44');
INSERT INTO `checkout` VALUES ('31', '10', '0', null, null, '0', null, null, '0', '9', '183.00', '2013-07-12 16:45:05');
INSERT INTO `checkout` VALUES ('32', '10', '0', null, null, '0', null, null, '0', '9', '183.00', '2013-07-12 16:46:12');
INSERT INTO `checkout` VALUES ('33', '10', '0', null, null, '0', null, null, '0', '9', '183.00', '2013-07-12 16:50:37');
INSERT INTO `checkout` VALUES ('34', '10', '2', '8', '84.00', '0', null, null, '2', '8', '84.00', '2013-07-12 17:11:54');
INSERT INTO `checkout` VALUES ('35', '10', '1', '2', '20.00', '0', null, null, '1', '2', '20.00', '2013-07-12 17:22:01');
INSERT INTO `checkout` VALUES ('36', '12', '4', '17', '797.00', '0', null, null, '4', '17', '797.00', '2013-07-12 18:26:06');
INSERT INTO `checkout` VALUES ('37', '10', '2', '5', '66.00', '0', null, null, '2', '5', '66.00', '2013-07-12 19:36:48');
INSERT INTO `checkout` VALUES ('38', '11', '0', null, null, '0', null, null, '0', '0', '0.00', '2013-07-12 23:47:08');
INSERT INTO `checkout` VALUES ('39', '10', '2', '27', '285.00', '0', null, null, '2', '27', '285.00', '2013-07-13 17:50:21');
INSERT INTO `checkout` VALUES ('40', '10', '3', '120', '414.00', '0', null, null, '3', '120', '414.00', '2013-07-14 13:04:49');
INSERT INTO `checkout` VALUES ('41', '10', '4', '341', '8064.00', '0', null, null, '4', '341', '8064.00', '2013-07-15 23:03:45');
INSERT INTO `checkout` VALUES ('42', '10', '2', '116', '381.92', '0', null, null, '2', '116', '381.92', '2013-07-22 19:33:22');
INSERT INTO `checkout` VALUES ('43', '10', '1', '9', '103.30', '0', null, null, '1', '9', '103.30', '2013-07-22 20:43:47');
INSERT INTO `checkout` VALUES ('44', '10', '19', '1198', '10923.00', '0', null, null, '19', '1198', '10923.00', '2013-07-25 02:40:20');
INSERT INTO `checkout` VALUES ('45', '10', '1', '2', '501.00', '0', null, null, '1', '2', '501.00', '2013-07-26 23:03:20');
INSERT INTO `checkout` VALUES ('46', '10', '1', '3', '511.00', '0', null, null, '1', '3', '511.00', '2013-07-27 18:00:10');
INSERT INTO `checkout` VALUES ('47', '10', '1', '2', '11.00', '0', null, null, '1', '2', '11.00', '2013-08-12 23:46:57');
INSERT INTO `checkout` VALUES ('48', '15', '1', '1', '500.00', '0', null, null, '1', '1', '500.00', '2013-08-28 22:04:59');
INSERT INTO `checkout` VALUES ('49', '10', '3', '23', '5271.00', '0', null, null, '3', '23', '5271.00', '2013-09-10 22:22:59');
INSERT INTO `checkout` VALUES ('50', '10', '5', '23', '3453.00', '3', '6', '1756.00', '8', '29', '5209.00', '2013-09-22 19:25:30');
INSERT INTO `checkout` VALUES ('51', '10', '3', '9', '411.00', '1', '3', '178.00', '4', '12', '589.00', '2013-09-23 00:09:20');
INSERT INTO `checkout` VALUES ('52', '10', '4', '8', '116.00', '52', '127', '3224.00', '56', '135', '3340.00', '2013-10-07 12:51:32');
INSERT INTO `checkout` VALUES ('53', '10', '5', '8', '1065.00', '0', '0', '0.00', '5', '8', '1065.00', '2013-10-21 21:43:18');
INSERT INTO `checkout` VALUES ('54', '10', '1', '2', '2.00', '1', '3', '120.00', '2', '5', '122.00', '2013-10-21 22:05:34');
INSERT INTO `checkout` VALUES ('55', '10', '0', '0', '0.00', '0', '0', '0.00', '0', '0', '0.00', '2013-10-21 22:08:14');
INSERT INTO `checkout` VALUES ('56', '10', '1', '2', '2.00', '1', '10', '100.00', '2', '12', '102.00', '2013-10-21 22:54:24');
INSERT INTO `checkout` VALUES ('57', '10', '3', '17', '139.00', '1', '3', '116.00', '4', '20', '255.00', '2013-10-23 02:23:59');
INSERT INTO `checkout` VALUES ('58', '10', '14', '70', '2724.00', '7', '24', '1068.00', '21', '94', '3792.00', '2015-02-22 21:37:16');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `company_code` varchar(40) default NULL,
  `name` varchar(40) default NULL,
  `contact` varchar(40) default NULL,
  `phone` varchar(40) default NULL,
  `fax` varchar(40) default NULL,
  `email` varchar(90) default NULL,
  `address` varchar(90) default NULL,
  `description` char(255) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('302', '11111177777777', '資管系', '范剛順', '91981981', '961919', 'xx.321@jhotmail.com22', '林口長庚', '', '1');
INSERT INTO `customer` VALUES ('303', '123345678', '餐飲科', '友敬', '1', '', '', '', '', '1');
INSERT INTO `customer` VALUES ('304', '', '資管系2222222', '友敬222222', '098765432122222', '', '', '', '', '1');

-- ----------------------------
-- Table structure for `goods_item`
-- ----------------------------
DROP TABLE IF EXISTS `goods_item`;
CREATE TABLE `goods_item` (
  `id` int(11) NOT NULL,
  `product_id` int(11) default NULL,
  `into_goods_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  `purchase_price` double(11,5) default NULL,
  `total` double(11,2) default NULL,
  `total_amount` double(11,2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_9` USING BTREE (`product_id`),
  KEY `FK_Relationship_10` USING BTREE (`into_goods_id`),
  CONSTRAINT `FK_Relationship_10` FOREIGN KEY (`into_goods_id`) REFERENCES `into_goods` (`id`),
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_item
-- ----------------------------
INSERT INTO `goods_item` VALUES ('399', '223', '338', '10', '100.00000', '1000.00', '1000.00');
INSERT INTO `goods_item` VALUES ('402', '223', '339', '4', '100.00000', '400.00', '420.00');
INSERT INTO `goods_item` VALUES ('403', '209', '341', '10', '3.00000', '30.00', '30.00');
INSERT INTO `goods_item` VALUES ('404', '206', '341', '10', '0.70000', '7.00', '7.00');
INSERT INTO `goods_item` VALUES ('406', '224', '343', '1', '10.00000', '10.00', '10.50');
INSERT INTO `goods_item` VALUES ('407', '224', '342', '12', '10.00000', '120.00', '126.00');
INSERT INTO `goods_item` VALUES ('415', '223', '345', '10', '100.00000', '1000.00', '1050.00');
INSERT INTO `goods_item` VALUES ('416', '223', '346', '10', '100.00000', '1000.00', '1050.00');
INSERT INTO `goods_item` VALUES ('417', '223', '347', '1', '100.00000', '100.00', '100.00');
INSERT INTO `goods_item` VALUES ('418', '215', '348', '250', '38.10000', '9524.00', '10000.20');
INSERT INTO `goods_item` VALUES ('419', '202', '349', '250', '38.10000', '9524.00', '10000.20');
INSERT INTO `goods_item` VALUES ('425', '212', '351', '250', '38.09523', '9524.00', '10000.20');
INSERT INTO `goods_item` VALUES ('426', '224', '344', '10', '10.00000', '100.00', '105.00');
INSERT INTO `goods_item` VALUES ('431', '241', '352', '1', '12.00000', '12.00', '12.60');
INSERT INTO `goods_item` VALUES ('432', '211', '350', '250', '38.09800', '9525.00', '10001.25');
INSERT INTO `goods_item` VALUES ('433', '241', '353', '5', '12.00000', '60.00', '63.00');
INSERT INTO `goods_item` VALUES ('434', '224', '340', '11', '10.00000', '110.00', '115.50');
INSERT INTO `goods_item` VALUES ('435', '223', '340', '2', '100.00000', '200.00', '210.00');
INSERT INTO `goods_item` VALUES ('436', '241', '354', '100', '12.00000', '1200.00', '1260.00');
INSERT INTO `goods_item` VALUES ('437', '235', '355', '10', '20.00000', '200.00', '210.00');
INSERT INTO `goods_item` VALUES ('438', '234', '356', '3', '10.00000', '30.00', '31.50');
INSERT INTO `goods_item` VALUES ('439', '235', '356', '2', '20.00000', '40.00', '42.00');
INSERT INTO `goods_item` VALUES ('440', '239', '356', '1', '300.00000', '300.00', '315.00');
INSERT INTO `goods_item` VALUES ('441', '234', '357', '20', '10.00000', '200.00', '210.00');
INSERT INTO `goods_item` VALUES ('442', '241', '358', '2', '12.00000', '24.00', '25.20');

-- ----------------------------
-- Table structure for `income`
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` int(11) NOT NULL,
  `income_amount` double(11,2) default NULL,
  `income_time` datetime default NULL,
  `income_personnel` int(11) default NULL,
  `income_mode` int(11) default NULL,
  `description` char(255) default NULL,
  `check_number` char(20) default NULL,
  `check_amount` double(11,2) default NULL,
  `check_date` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_35` USING BTREE (`income_personnel`),
  CONSTRAINT `FK_Relationship_35` FOREIGN KEY (`income_personnel`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income
-- ----------------------------
INSERT INTO `income` VALUES ('1', '5985.00', '2014-03-08 00:00:00', '10', '1', '123', null, null, null);
INSERT INTO `income` VALUES ('2', '9975.00', '2014-03-08 00:00:00', '10', '1', '', null, null, null);
INSERT INTO `income` VALUES ('3', '1838.00', '2014-04-04 00:00:00', '10', '1', '測試 : 2014.4.4 新增功能', null, null, null);
INSERT INTO `income` VALUES ('4', '1201.00', '2014-04-04 00:00:00', '10', '0', '測試2 : 2414.4.4 新增功能', null, null, null);
INSERT INTO `income` VALUES ('5', '4200.00', '2014-04-05 00:00:00', '10', '0', '新增測試', 'AE1507207', '150000.00', '2014-06-05 00:00:00');
INSERT INTO `income` VALUES ('6', '105.00', '2014-04-05 00:00:00', '10', '0', '2014.4.5', '1234567', '123.00', '2014-04-05 00:00:00');

-- ----------------------------
-- Table structure for `into_goods`
-- ----------------------------
DROP TABLE IF EXISTS `into_goods`;
CREATE TABLE `into_goods` (
  `id` int(11) NOT NULL,
  `user_id` int(11) default NULL,
  `supplier_id` int(11) default NULL,
  `invoice_number` varchar(40) default NULL,
  `total` double(11,2) default NULL,
  `tax` double(11,2) default NULL,
  `sales_tax` double(11,2) default NULL,
  `total_amount` double(11,2) default NULL,
  `create_time` datetime default NULL,
  `status` int(11) default NULL,
  `checkout_id` int(11) default NULL,
  `description` char(255) default NULL,
  `closed` int(11) default NULL,
  `payment_time` datetime default NULL,
  `payment_personnel` int(11) default NULL,
  `payment_mode` int(11) default NULL,
  `payment_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_7` USING BTREE (`user_id`),
  KEY `FK_Relationship_8` USING BTREE (`checkout_id`),
  KEY `FK_Relationship_11` USING BTREE (`supplier_id`),
  KEY `FK_Relationship_19` USING BTREE (`payment_personnel`),
  KEY `FK_Relationship_27` USING BTREE (`payment_id`),
  CONSTRAINT `FK_Relationship_11` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FK_Relationship_19` FOREIGN KEY (`payment_personnel`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Relationship_27` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Relationship_8` FOREIGN KEY (`checkout_id`) REFERENCES `checkout` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of into_goods
-- ----------------------------
INSERT INTO `into_goods` VALUES ('338', '10', '304', '', '1000.00', '0.00', '0.00', '1000.00', '2013-08-15 01:37:49', '1', null, '', '1', '2013-12-21 23:19:00', '10', '0', null);
INSERT INTO `into_goods` VALUES ('339', '10', '300', '', '400.00', '0.05', '20.00', '420.00', '2013-08-20 01:20:56', '1', null, '', '1', '2013-08-22 22:18:35', '10', '0', null);
INSERT INTO `into_goods` VALUES ('340', '10', '304', '', '310.00', '0.05', '16.00', '326.00', '2013-12-23 00:00:00', '1', null, '', '1', '2013-12-31 00:00:00', '10', '1', '1');
INSERT INTO `into_goods` VALUES ('341', '10', '304', '', '37.00', '0.00', '0.00', '37.00', '2013-08-22 21:13:03', '1', null, '', '1', '2013-12-31 00:00:00', '10', '0', '1');
INSERT INTO `into_goods` VALUES ('342', '10', '303', '', '120.00', '0.05', '6.00', '126.00', '2013-08-12 01:04:28', '1', null, '', '1', '2013-12-25 00:00:00', '10', '1', '2');
INSERT INTO `into_goods` VALUES ('343', '10', '303', '', '10.00', '0.05', '0.50', '10.50', '2013-08-22 22:38:36', '1', null, '', '1', '2013-12-25 00:00:00', '10', '0', '2');
INSERT INTO `into_goods` VALUES ('344', '10', '303', '', '100.00', '0.05', '5.00', '105.00', '2013-08-20 00:00:00', '0', null, '', '0', null, null, '0', null);
INSERT INTO `into_goods` VALUES ('345', '10', '303', '', '1000.00', '0.05', '50.00', '1050.00', '2013-08-24 02:47:23', '1', null, '', '1', '2013-12-25 00:00:00', '10', '0', '2');
INSERT INTO `into_goods` VALUES ('346', '10', '303', '', '1000.00', '0.05', '50.00', '1050.00', '2013-08-24 03:01:18', '1', null, '', '1', '2013-12-25 00:00:00', '10', '0', '2');
INSERT INTO `into_goods` VALUES ('347', '10', '304', '12345678', '100.00', '0.00', '0.00', '100.00', '2013-09-02 00:00:00', '1', null, '', '1', '2013-12-31 00:00:00', '10', '0', '1');
INSERT INTO `into_goods` VALUES ('348', '10', '301', '', '9524.00', '0.05', '476.20', '10000.00', '2013-10-30 00:00:00', '1', null, '', '1', '2014-02-03 00:00:00', '10', '0', '4');
INSERT INTO `into_goods` VALUES ('349', '10', '303', '', '9524.00', '0.05', '476.20', '10000.00', '2013-10-30 00:00:00', '1', null, '', '1', '2013-12-25 00:00:00', '10', '0', '2');
INSERT INTO `into_goods` VALUES ('350', '10', '300', '', '9525.00', '0.05', '476.00', '10001.00', '2013-10-30 00:00:00', '0', null, '', '1', null, null, '1', null);
INSERT INTO `into_goods` VALUES ('351', '10', '303', '', '9524.00', '0.05', '476.00', '10000.00', '2013-10-31 00:00:00', '1', null, '', '1', '2013-12-25 00:00:00', '10', '0', '2');
INSERT INTO `into_goods` VALUES ('352', '10', '305', '', '12.00', '0.05', '1.00', '13.00', '2013-12-09 00:00:00', '1', null, '', '1', '2014-01-24 00:00:00', '10', '1', '3');
INSERT INTO `into_goods` VALUES ('353', '10', '304', '', '60.00', '0.05', '3.00', '63.00', null, '1', null, '', '1', '2013-12-31 00:00:00', '10', '1', '1');
INSERT INTO `into_goods` VALUES ('354', '10', '304', '', '1200.00', '0.05', '60.00', '1260.00', '2014-03-06 00:00:00', '1', null, '', '1', '2014-03-06 00:00:00', '10', '0', '6');
INSERT INTO `into_goods` VALUES ('355', '10', '301', '', '200.00', '0.05', '10.00', '210.00', '2014-03-06 00:00:00', '1', null, '', '1', '2014-03-06 00:00:00', '10', '0', '5');
INSERT INTO `into_goods` VALUES ('356', '10', '303', '', '370.00', '0.05', '18.00', '388.00', '2014-03-06 00:00:00', '0', null, '', '1', null, null, '0', null);
INSERT INTO `into_goods` VALUES ('357', '10', '305', '', '200.00', '0.05', '10.00', '210.00', '2014-03-06 00:00:00', '0', null, '', '1', null, null, '0', null);
INSERT INTO `into_goods` VALUES ('358', '10', '301', '', '24.00', '0.05', '1.00', '25.00', '2014-03-06 00:00:00', '0', null, '', '1', null, null, '0', null);

-- ----------------------------
-- Table structure for `invoice_for_front`
-- ----------------------------
DROP TABLE IF EXISTS `invoice_for_front`;
CREATE TABLE `invoice_for_front` (
  `id` int(11) NOT NULL,
  `invoice_coding` varchar(40) default NULL,
  `invoice_number` int(11) default NULL,
  `quantity` int(11) default NULL,
  `update_time` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invoice_for_front
-- ----------------------------
INSERT INTO `invoice_for_front` VALUES ('1', 'PL', '70858250', '12', '2014-06-10 00:25:29');

-- ----------------------------
-- Table structure for `payment`
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `payment_amount` double(11,2) default NULL,
  `payment_time` datetime default NULL,
  `payment_personnel` int(11) default NULL,
  `payment_mode` int(11) default NULL,
  `description` char(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_26` USING BTREE (`payment_personnel`),
  CONSTRAINT `FK_Relationship_26` FOREIGN KEY (`payment_personnel`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES ('1', '526.00', '2013-12-31 00:00:00', '10', '1', '測試!');
INSERT INTO `payment` VALUES ('2', '22236.00', '2013-12-25 00:00:00', '10', '1', '');
INSERT INTO `payment` VALUES ('3', '13.00', '2014-01-24 00:00:00', '10', '0', '');
INSERT INTO `payment` VALUES ('4', '10000.00', '2014-02-03 00:00:00', '10', '1', '');
INSERT INTO `payment` VALUES ('5', '210.00', '2014-03-06 00:00:00', '10', '0', '');
INSERT INTO `payment` VALUES ('6', '1260.00', '2014-03-06 00:00:00', '10', '1', '');

-- ----------------------------
-- Table structure for `petty_cash`
-- ----------------------------
DROP TABLE IF EXISTS `petty_cash`;
CREATE TABLE `petty_cash` (
  `id` int(11) NOT NULL,
  `username` varchar(40) default NULL,
  `supplier_id` int(11) default NULL,
  `total` double(11,2) default NULL,
  `tax` double(11,2) default NULL,
  `sales_tax` double(11,2) default NULL,
  `total_amount` double(11,2) default NULL,
  `create_time` datetime default NULL,
  `status` int(11) default NULL,
  `description` char(255) default NULL,
  `payment_time` datetime default NULL,
  `payment_account` varchar(40) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_20` USING BTREE (`supplier_id`),
  CONSTRAINT `FK_Relationship_20` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petty_cash
-- ----------------------------
INSERT INTO `petty_cash` VALUES ('1', '建龍', null, '8300.00', '0.05', '415.00', '8715.00', '2013-09-01 00:00:00', '1', '', '2013-09-01 00:00:00', '現金支出');
INSERT INTO `petty_cash` VALUES ('2', '建龍', '304', '25000.00', '0.00', '0.00', '25000.00', '2013-09-01 00:00:00', '0', '', '2013-09-01 00:00:00', '12345678');
INSERT INTO `petty_cash` VALUES ('3', '建龍', null, '8400.00', '0.00', '0.00', '8400.00', '2013-09-01 00:00:00', '0', '', '2013-09-01 00:00:00', '10827827');
INSERT INTO `petty_cash` VALUES ('4', '建龍', '300', '20000.00', '0.05', '1000.00', '21000.00', '2013-09-01 00:00:00', '0', '', '2013-09-01 00:00:00', '現金支出');
INSERT INTO `petty_cash` VALUES ('5', '建龍2', '304', '70.00', '0.05', '3.50', '73.50', '2013-09-04 00:00:00', '0', '', '2013-09-05 00:00:00', '現金支出');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category_id` int(11) default NULL,
  `name` varchar(40) default NULL,
  `price` double(11,2) default NULL,
  `display_order` int(11) default NULL,
  `description` char(255) default NULL,
  `status` int(11) default NULL,
  `stock_number` int(11) default NULL,
  `unit` varchar(20) default NULL,
  `purchase_price` double(11,5) default NULL,
  `type` int(11) default NULL,
  `barcode_number` varchar(90) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_1` (`category_id`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('202', '106', '綠茶', '25.00', '3', '', '1', '9', '瓶', '38.10000', '1', null);
INSERT INTO `product` VALUES ('204', '100', '餅乾1', '15.00', '12', '', '1', '993', '包', '10.00000', '1', null);
INSERT INTO `product` VALUES ('205', '100', '餅乾2', '20.00', '12', '', '1', '991', '包', '10.00000', '1', null);
INSERT INTO `product` VALUES ('206', '100', 'A4列印', '1.00', '9', '', '1', '488', '張', '0.70000', '1', null);
INSERT INTO `product` VALUES ('207', '100', '麵包1', '15.00', '13', '', '1', '0', '個', '10.00000', '1', null);
INSERT INTO `product` VALUES ('209', '100', '彩色列印', '10.00', '11', '', '1', '476', '張', '3.00000', '1', null);
INSERT INTO `product` VALUES ('210', '104', '泡麵1', '55.00', '15', '', '1', '1', '碗', '10.00000', '1', null);
INSERT INTO `product` VALUES ('211', '106', '紅茶', '25.00', '1', '', '1', '9', '瓶', '38.09800', '1', null);
INSERT INTO `product` VALUES ('212', '106', '咖啡', '30.00', '2', '', '0', '8', '瓶', '38.09523', '1', null);
INSERT INTO `product` VALUES ('214', '105', '哈利波特', '500.00', '10', '', '1', '9', '本', '120.00000', '1', null);
INSERT INTO `product` VALUES ('215', '106', '奶茶', '20.00', '5', '', '1', '22', '瓶', '38.10000', '1', null);
INSERT INTO `product` VALUES ('216', '100', '1111-1111', '11.00', '11', '', '1', '0', '11', '0.00000', '0', null);
INSERT INTO `product` VALUES ('217', '100', '22222-222', '22.00', '22', '', '1', '0', '式', '0.00000', '0', null);
INSERT INTO `product` VALUES ('218', '111', '1313', '13.00', '13', '', '0', '0', '個', '0.00000', '0', null);
INSERT INTO `product` VALUES ('221', '100', '111', '13.00', '23', '', '1', '30', '個', '9.00000', '0', null);
INSERT INTO `product` VALUES ('222', '100', '餅乾3', '99.90', '10', '', '1', '47', '包', '10.00000', '1', null);
INSERT INTO `product` VALUES ('223', '100', '電影票', '500.00', '12', '', '1', '4', '張', '100.00000', '1', null);
INSERT INTO `product` VALUES ('224', '100', '餅乾3', '21.46', '12', '', '1', '52', '包', '10.00000', '1', null);
INSERT INTO `product` VALUES ('225', '100', '1111111', '11.00', '12', '', '1', '0', '張', '11.00000', '0', null);
INSERT INTO `product` VALUES ('226', '100', '餅乾4', '56.30', '12', '', '1', '74', '包', '12.00000', '1', null);
INSERT INTO `product` VALUES ('227', '100', '11111', '11.00', '11', '', '1', '0', '11', '0.00000', '0', null);
INSERT INTO `product` VALUES ('228', '100', '22222', '22.00', '22', '', '1', '0', '22', '0.00000', '0', null);
INSERT INTO `product` VALUES ('229', '100', '333333', '33.00', '33', '', '1', '0', '33', '0.00000', '0', null);
INSERT INTO `product` VALUES ('230', '100', '44444', '44.00', '44', '', '1', '0', '44', '0.00000', '0', null);
INSERT INTO `product` VALUES ('231', '100', '55555', '5555.00', '55', '', '1', '0', '55', '0.00000', '0', null);
INSERT INTO `product` VALUES ('232', '99', '組合A', '555.00', '50', '測試', '1', '0', '組', '0.00000', '2', null);
INSERT INTO `product` VALUES ('233', '99', '組合B', '11.00', '50', '測試', '1', '0', '組', '0.00000', '2', '');
INSERT INTO `product` VALUES ('234', '100', 'A4列印(包)', '200.00', '50', '', '1', '0', '包', '10.00000', '1', null);
INSERT INTO `product` VALUES ('235', '100', 'A4列印(箱)', '1000.00', '50', '', '1', '0', '箱', '20.00000', '1', null);
INSERT INTO `product` VALUES ('236', '106', '奶茶(箱)', '480.00', '50', '', '1', '0', '箱', '0.00000', '1', null);
INSERT INTO `product` VALUES ('237', '105', '哈利波特(冊)', '1800.00', '50', '', '1', '1', '套', '0.00000', '1', null);
INSERT INTO `product` VALUES ('238', '104', '泡麵(箱)', '300.00', '50', '', '1', '0', '箱', '0.00000', '1', '');
INSERT INTO `product` VALUES ('239', '100', '電影票(套)', '1200.00', '50', '', '1', '1', '套', '300.00000', '1', '');
INSERT INTO `product` VALUES ('240', '104', '泡麵(組)', '150.00', '50', '', '1', '0', '組', '0.00000', '1', '');
INSERT INTO `product` VALUES ('241', '100', '測試商品', '100.00', '50', '', '1', '67', '個', '12.00000', '1', '10212YL128113183106');
INSERT INTO `product` VALUES ('242', '105', '123', '0.00', '12', '', '1', '9', '11', '0.00000', '1', '242');
INSERT INTO `product` VALUES ('243', '99', 'A包', '100.00', '1', ' 也不知道在包什麼的', '1', '0', '包', '0.00000', '2', '');

-- ----------------------------
-- Table structure for `product_box`
-- ----------------------------
DROP TABLE IF EXISTS `product_box`;
CREATE TABLE `product_box` (
  `id` int(11) NOT NULL,
  `product_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_25` USING BTREE (`product_id`),
  CONSTRAINT `FK_Relationship_25` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_box
-- ----------------------------
INSERT INTO `product_box` VALUES ('206', '234', '500');
INSERT INTO `product_box` VALUES ('209', '234', '500');
INSERT INTO `product_box` VALUES ('210', '240', '3');
INSERT INTO `product_box` VALUES ('214', '237', '6');
INSERT INTO `product_box` VALUES ('215', '236', '24');
INSERT INTO `product_box` VALUES ('223', '239', '3');
INSERT INTO `product_box` VALUES ('234', '235', '5');
INSERT INTO `product_box` VALUES ('240', '238', '0');

-- ----------------------------
-- Table structure for `product_item`
-- ----------------------------
DROP TABLE IF EXISTS `product_item`;
CREATE TABLE `product_item` (
  `id` int(11) NOT NULL,
  `product_id` int(11) default NULL,
  `item_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_23` USING BTREE (`product_id`),
  KEY `FK_Relationship_24` USING BTREE (`item_id`),
  CONSTRAINT `FK_Relationship_23` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_Relationship_24` FOREIGN KEY (`item_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_item
-- ----------------------------
INSERT INTO `product_item` VALUES ('9', '232', '214', '1');
INSERT INTO `product_item` VALUES ('10', '232', '223', '1');
INSERT INTO `product_item` VALUES ('11', '233', '206', '10');
INSERT INTO `product_item` VALUES ('12', '233', '209', '1');
INSERT INTO `product_item` VALUES ('13', '243', '211', '1');
INSERT INTO `product_item` VALUES ('14', '243', '212', '2');
INSERT INTO `product_item` VALUES ('15', '243', '215', '1');
INSERT INTO `product_item` VALUES ('16', '243', '202', '1');

-- ----------------------------
-- Table structure for `sell`
-- ----------------------------
DROP TABLE IF EXISTS `sell`;
CREATE TABLE `sell` (
  `id` int(11) NOT NULL default '0',
  `user_id` int(11) default NULL,
  `customer_id` int(11) default NULL,
  `invoice_number` varchar(40) default NULL,
  `total` double(11,2) default NULL,
  `tax` double(11,2) default NULL,
  `sales_tax` double(11,2) default NULL,
  `total_amount` double(11,2) default NULL,
  `create_time` datetime default NULL,
  `status` int(11) default NULL,
  `checkout_id` int(11) default NULL,
  `description` char(255) default NULL,
  `closed` int(11) default NULL,
  `income_time` datetime default NULL,
  `income_personnel` int(11) default NULL,
  `delivery` varchar(90) default NULL,
  `sid` varchar(90) default NULL,
  `income_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_13` USING BTREE (`user_id`),
  KEY `FK_Relationship_14` USING BTREE (`checkout_id`),
  KEY `FK_Relationship_15` USING BTREE (`customer_id`),
  KEY `FK_Relationship_18` USING BTREE (`income_personnel`),
  KEY `FK_Relationship_36` USING BTREE (`income_id`),
  CONSTRAINT `FK_Relationship_13` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_Relationship_14` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Relationship_18` FOREIGN KEY (`income_personnel`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Relationship_36` FOREIGN KEY (`income_id`) REFERENCES `income` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell
-- ----------------------------
INSERT INTO `sell` VALUES ('1', '10', '302', '', '4500.00', '0.05', '225.00', '4725.00', '2013-08-14 23:13:19', '0', null, '', '0', null, null, null, null, null);
INSERT INTO `sell` VALUES ('2', '10', '302', '', '1500.00', '0.05', '75.00', '1575.00', '2013-08-15 01:44:59', '0', null, '', '0', null, null, null, null, null);
INSERT INTO `sell` VALUES ('3', '10', '303', '', '5076.00', '0.05', '253.80', '5329.80', '2013-08-20 00:57:57', '1', null, '', '1', '2013-09-21 16:49:22', '10', null, null, null);
INSERT INTO `sell` VALUES ('4', '10', '302', '', '500.00', '0.00', '0.00', '500.00', '2013-08-15 02:02:43', '1', null, '', '1', '2013-08-22 22:39:15', '10', null, null, null);
INSERT INTO `sell` VALUES ('5', '10', '303', '', '21.46', '0.00', '0.00', '21.46', '2013-08-15 02:08:54', '1', null, '', '1', '2014-03-08 00:00:00', '10', null, null, null);
INSERT INTO `sell` VALUES ('6', '10', '303', '', '100.00', '0.00', '0.00', '100.00', '2013-08-20 00:27:18', '0', null, '', '0', null, null, null, null, null);
INSERT INTO `sell` VALUES ('7', '10', '302', '', '200.00', '0.05', '10.00', '210.00', '2013-08-26 00:24:02', '1', null, '', '1', '2014-03-08 00:00:00', '10', '', null, '1');
INSERT INTO `sell` VALUES ('8', '10', '302', '', '5000.00', '0.05', '250.00', '5250.00', '2013-08-23 22:04:03', '1', null, '', '1', '2014-03-08 00:00:00', '10', null, null, '1');
INSERT INTO `sell` VALUES ('9', '10', '302', '發票編號測試!', '500.00', '0.05', '25.00', '525.00', '2013-08-24 00:38:46', '1', null, '', '1', '2014-03-08 00:00:00', '10', '發票地址測試!', null, '1');
INSERT INTO `sell` VALUES ('10', '10', '303', '', '1750.00', '0.05', '87.50', '1837.50', '2013-08-24 00:40:22', '1', null, '', '1', '2014-04-04 00:00:00', '10', '', null, '3');
INSERT INTO `sell` VALUES ('11', '10', '303', '', '3500.00', '0.05', '175.00', '3675.00', '2013-08-24 00:00:00', '1', null, '', '1', '2013-12-09 01:48:20', '10', '', null, null);
INSERT INTO `sell` VALUES ('12', '10', '302', '', '4500.00', '0.05', '225.00', '4725.00', '2013-08-24 02:48:38', '1', null, '', '1', '2014-03-08 00:00:00', '10', '', null, '2');
INSERT INTO `sell` VALUES ('13', '10', '302', '', '5000.00', '0.05', '250.00', '5250.00', '2013-08-24 03:01:50', '1', null, '', '1', '2014-03-08 00:00:00', '10', '', null, '2');
INSERT INTO `sell` VALUES ('14', '10', '303', '', '1143.46', '0.05', '57.17', '1200.63', '2013-08-27 22:51:37', '1', null, '', '1', '2014-04-04 00:00:00', '10', '', null, '4');
INSERT INTO `sell` VALUES ('15', '10', '302', '', '1000.00', '0.05', '50.00', '1050.00', '2013-09-23 00:00:00', '1', null, '', '1', '2014-03-06 22:58:31', '10', '', null, null);
INSERT INTO `sell` VALUES ('16', '10', '303', '', '9524.00', '0.05', '476.00', '10000.00', '2013-10-31 00:00:00', '1', null, '', '1', '2014-03-06 22:58:31', '10', '', null, null);
INSERT INTO `sell` VALUES ('17', '10', '302', '', '9525.00', '0.05', '476.00', '10001.00', '2013-10-31 00:00:00', '1', null, '', '1', '2014-03-06 22:58:31', '10', '', null, null);
INSERT INTO `sell` VALUES ('18', '10', '303', '1234567', '4000.00', '0.05', '200.00', '4200.00', '2014-04-04 00:00:00', '1', null, '', '1', '2014-04-05 00:00:00', '10', '', 'SEnull', '5');
INSERT INTO `sell` VALUES ('19', '10', '304', '', '100.00', '0.05', '5.00', '105.00', '2014-04-05 00:00:00', '1', null, '', '1', '2014-04-05 00:00:00', '10', '', 'SEnull', '6');

-- ----------------------------
-- Table structure for `sellitem`
-- ----------------------------
DROP TABLE IF EXISTS `sellitem`;
CREATE TABLE `sellitem` (
  `id` int(11) NOT NULL,
  `product_id` int(11) default NULL,
  `sell_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  `purchase_price` double(11,5) default NULL,
  `total` double(11,2) default NULL,
  `total_amount` double(11,2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_16` USING BTREE (`product_id`),
  KEY `FK_Relationship_17` USING BTREE (`sell_id`),
  CONSTRAINT `FK_Relationship_16` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_Relationship_17` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellitem
-- ----------------------------
INSERT INTO `sellitem` VALUES ('34', '223', '2', '3', '500.00000', '1500.00', '1575.00');
INSERT INTO `sellitem` VALUES ('35', '209', '6', '10', '10.00000', '100.00', '100.00');
INSERT INTO `sellitem` VALUES ('37', '214', '1', '10', '450.00000', '4500.00', '4725.00');
INSERT INTO `sellitem` VALUES ('40', '209', '3', '7', '10.00000', '70.00', '73.50');
INSERT INTO `sellitem` VALUES ('41', '223', '3', '10', '500.00000', '5000.00', '5250.00');
INSERT INTO `sellitem` VALUES ('42', '206', '3', '6', '1.00000', '6.00', '6.30');
INSERT INTO `sellitem` VALUES ('44', '223', '4', '1', '500.00000', '500.00', '500.00');
INSERT INTO `sellitem` VALUES ('45', '224', '5', '1', '21.46000', '21.46', '21.46');
INSERT INTO `sellitem` VALUES ('46', '223', '8', '10', '500.00000', '5000.00', '5250.00');
INSERT INTO `sellitem` VALUES ('56', '202', '10', '70', '25.00000', '1750.00', '1837.50');
INSERT INTO `sellitem` VALUES ('59', '223', '12', '9', '500.00000', '4500.00', '4725.00');
INSERT INTO `sellitem` VALUES ('60', '223', '13', '10', '500.00000', '5000.00', '5250.00');
INSERT INTO `sellitem` VALUES ('61', '206', '7', '100', '1.00000', '100.00', '105.00');
INSERT INTO `sellitem` VALUES ('62', '209', '7', '10', '10.00000', '100.00', '105.00');
INSERT INTO `sellitem` VALUES ('63', '202', '9', '20', '25.00000', '500.00', '525.00');
INSERT INTO `sellitem` VALUES ('73', '223', '14', '1', '500.00000', '500.00', '525.00');
INSERT INTO `sellitem` VALUES ('74', '206', '14', '1', '1.00000', '1.00', '1.05');
INSERT INTO `sellitem` VALUES ('75', '215', '14', '1', '20.00000', '20.00', '21.00');
INSERT INTO `sellitem` VALUES ('76', '212', '14', '1', '30.00000', '30.00', '31.50');
INSERT INTO `sellitem` VALUES ('77', '202', '14', '1', '25.00000', '25.00', '26.25');
INSERT INTO `sellitem` VALUES ('78', '214', '14', '1', '500.00000', '500.00', '525.00');
INSERT INTO `sellitem` VALUES ('79', '224', '14', '1', '21.46000', '21.46', '22.53');
INSERT INTO `sellitem` VALUES ('80', '225', '14', '1', '11.00000', '11.00', '11.55');
INSERT INTO `sellitem` VALUES ('81', '211', '14', '1', '25.00000', '25.00', '26.25');
INSERT INTO `sellitem` VALUES ('82', '209', '14', '1', '10.00000', '10.00', '10.50');
INSERT INTO `sellitem` VALUES ('83', '223', '15', '2', '500.00000', '1000.00', '1050.00');
INSERT INTO `sellitem` VALUES ('84', '223', '11', '7', '500.00000', '3500.00', '3675.00');
INSERT INTO `sellitem` VALUES ('86', '211', '16', '250', '38.09523', '9524.00', '10000.20');
INSERT INTO `sellitem` VALUES ('91', '215', '17', '250', '38.09900', '9525.00', '10001.25');
INSERT INTO `sellitem` VALUES ('92', '241', '18', '40', '100.00000', '4000.00', '4200.00');
INSERT INTO `sellitem` VALUES ('93', '241', '19', '1', '100.00000', '100.00', '105.00');

-- ----------------------------
-- Table structure for `spending_item`
-- ----------------------------
DROP TABLE IF EXISTS `spending_item`;
CREATE TABLE `spending_item` (
  `id` int(11) NOT NULL,
  `subject_id` int(11) default NULL,
  `petty_cash_id` int(11) default NULL,
  `total` double(11,2) default NULL,
  `total_amount` double(11,2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_21` USING BTREE (`subject_id`),
  KEY `FK_Relationship_22` USING BTREE (`petty_cash_id`),
  CONSTRAINT `FK_Relationship_21` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FK_Relationship_22` FOREIGN KEY (`petty_cash_id`) REFERENCES `petty_cash` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spending_item
-- ----------------------------
INSERT INTO `spending_item` VALUES ('14', '5', '5', '20.00', '21.00');
INSERT INTO `spending_item` VALUES ('15', '4', '5', '20.00', '21.00');
INSERT INTO `spending_item` VALUES ('16', '3', '5', '30.00', '31.50');
INSERT INTO `spending_item` VALUES ('17', '4', '4', '20000.00', '21000.00');
INSERT INTO `spending_item` VALUES ('18', '3', '3', '100.00', '100.00');
INSERT INTO `spending_item` VALUES ('19', '5', '3', '8300.00', '8300.00');
INSERT INTO `spending_item` VALUES ('20', '3', '2', '25000.00', '25000.00');
INSERT INTO `spending_item` VALUES ('21', '5', '1', '8300.00', '8715.00');

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `name` varchar(40) default NULL,
  `display_order` int(11) default NULL,
  `description` char(255) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('3', '租金費用', '11', 'ok !', '1');
INSERT INTO `subject` VALUES ('4', '薪資費用', '20', '', '1');
INSERT INTO `subject` VALUES ('5', '工讀費', '10', '', '1');
INSERT INTO `subject` VALUES ('6', '1111111', '11', '', '1');
INSERT INTO `subject` VALUES ('7', '2222222', '22', '', '0');
INSERT INTO `subject` VALUES ('8', '3333333333', '33', '', '1');

-- ----------------------------
-- Table structure for `supplier`
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL,
  `company_code` varchar(40) default NULL,
  `name` varchar(40) default NULL,
  `contact` varchar(40) default NULL,
  `phone` varchar(40) default NULL,
  `fax` varchar(40) default NULL,
  `email` varchar(90) default NULL,
  `address` varchar(90) default NULL,
  `description` char(255) default NULL,
  `status` int(11) default NULL,
  `account_username` varchar(50) default NULL,
  `agency_name` varchar(50) default NULL,
  `agency_code` varchar(20) default NULL,
  `account` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('300', '16487295', '五洲', '吳建龍', '0938006312', '', 'xx.321@hotmail.com', '', '', '0', null, null, null, null);
INSERT INTO `supplier` VALUES ('301', '11340678', '醒吾科大', '邱世偉', '123456789', '', '', '', '', '1', null, null, null, null);
INSERT INTO `supplier` VALUES ('303', '12345678911', 'admin11', '友敬11', '098765432111', '111', '111', '1111', '1111', '1', null, null, null, null);
INSERT INTO `supplier` VALUES ('304', '12345678', '中信金', '亦聖', '098191961', '919191498', '', '', 'ok!', '0', null, null, null, null);
INSERT INTO `supplier` VALUES ('305', '27898105', '伍洲科技工程行', '吳建龍', '0938006312', '', 'xx.321@hotmail.com', '新北市新莊區\r\n五工一路80巷2號4樓', '測試', '1', '伍洲科技工程行-吳建龍', '台北富邦銀行-城中分行', '012-5000', '5001-20-00268--9');

-- ----------------------------
-- Table structure for `trade`
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `id` int(11) NOT NULL,
  `user_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  `total` double(11,2) default NULL,
  `create_time` datetime default NULL,
  `status` int(11) default NULL,
  `checkout_id` int(11) default NULL,
  `type` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_4` (`user_id`),
  KEY `FK_Relationship_6` USING BTREE (`checkout_id`),
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`checkout_id`) REFERENCES `checkout` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade
-- ----------------------------
INSERT INTO `trade` VALUES ('1', '10', '9', '230.00', '2013-06-07 17:34:20', '1', '8', '0');
INSERT INTO `trade` VALUES ('2', '10', '7', '61.00', '2013-06-07 17:34:33', '1', '8', '0');
INSERT INTO `trade` VALUES ('3', '10', '4', '2000.00', '2013-06-07 17:34:37', '1', '8', '0');
INSERT INTO `trade` VALUES ('4', '10', '5', '50.00', '2013-06-07 17:39:50', '1', '8', '0');
INSERT INTO `trade` VALUES ('5', '10', '3', '80.00', '2013-06-07 17:40:49', '1', '8', '0');
INSERT INTO `trade` VALUES ('6', '10', '6', '105.00', '2013-06-07 18:05:23', '1', '8', '0');
INSERT INTO `trade` VALUES ('7', '10', '7', '43.00', '2013-06-09 22:11:59', '1', '8', '0');
INSERT INTO `trade` VALUES ('8', '10', '9', '205.00', '2013-06-09 22:12:16', '1', '8', '0');
INSERT INTO `trade` VALUES ('26', '10', '10', '215.00', '2013-06-13 01:13:58', '1', '11', '0');
INSERT INTO `trade` VALUES ('27', '10', '1', '15.00', '2013-06-13 01:42:06', '1', '11', '0');
INSERT INTO `trade` VALUES ('28', '10', '4', '90.00', '2013-06-13 01:42:31', '1', '11', '0');
INSERT INTO `trade` VALUES ('29', '10', '5', '50.00', '2013-06-30 00:11:23', '1', '13', '0');
INSERT INTO `trade` VALUES ('30', '10', '11', '136.00', '2013-06-30 00:49:27', '1', '13', '0');
INSERT INTO `trade` VALUES ('31', '10', '15', '505.00', '2013-07-01 19:15:47', '1', '13', '0');
INSERT INTO `trade` VALUES ('32', '10', '9', '54.00', '2013-07-02 21:27:09', '1', '13', '0');
INSERT INTO `trade` VALUES ('33', '10', '4', '40.00', '2013-07-02 21:27:15', '1', '13', '0');
INSERT INTO `trade` VALUES ('34', '10', '9', '190.00', '2013-07-04 22:50:38', '1', '14', '0');
INSERT INTO `trade` VALUES ('35', '10', '11', '166.00', '2013-07-04 23:06:11', '1', '14', '0');
INSERT INTO `trade` VALUES ('36', '10', '10', '175.00', '2013-07-04 23:41:48', '1', '14', '0');
INSERT INTO `trade` VALUES ('37', '10', '3', '12.00', '2013-07-09 00:19:43', '1', '15', '0');
INSERT INTO `trade` VALUES ('38', '10', '7', '130.00', '2013-07-09 00:19:50', '1', '15', '0');
INSERT INTO `trade` VALUES ('39', '10', '6', '140.00', '2013-07-09 00:19:54', '1', '15', '0');
INSERT INTO `trade` VALUES ('40', '10', '7', '105.00', '2013-07-09 00:21:13', '1', '15', '0');
INSERT INTO `trade` VALUES ('41', '10', '4', '31.00', '2013-07-09 00:27:10', '1', '15', '0');
INSERT INTO `trade` VALUES ('42', '10', '27', '947.00', '2013-07-09 19:54:49', '1', '15', '0');
INSERT INTO `trade` VALUES ('43', '10', '8', '86.00', '2013-07-09 19:55:10', '1', '15', '0');
INSERT INTO `trade` VALUES ('44', '10', '17', '699.00', '2013-07-09 20:50:31', '1', '16', '0');
INSERT INTO `trade` VALUES ('45', '10', '21', '439.00', '2013-07-09 21:15:34', '1', '16', '0');
INSERT INTO `trade` VALUES ('46', '10', '26', '350.00', '2013-07-09 22:38:16', '1', '16', '0');
INSERT INTO `trade` VALUES ('47', '10', '51', '882.00', '2013-07-09 22:41:58', '1', '16', '0');
INSERT INTO `trade` VALUES ('48', '10', '7', '109.00', '2013-07-09 22:53:53', '1', '17', '0');
INSERT INTO `trade` VALUES ('49', '10', '37', '415.00', '2013-07-09 23:00:42', '1', '18', '0');
INSERT INTO `trade` VALUES ('50', '10', '11', '639.00', '2013-07-09 23:25:27', '1', '18', '0');
INSERT INTO `trade` VALUES ('51', '10', '2', '11.00', '2013-07-09 23:51:07', '1', '18', '0');
INSERT INTO `trade` VALUES ('52', '10', '3', '33.00', '2013-07-10 00:10:00', '1', '18', '0');
INSERT INTO `trade` VALUES ('53', '10', '4', '48.00', '2013-07-10 20:23:38', '1', '18', '0');
INSERT INTO `trade` VALUES ('54', '10', '4', '52.00', '2013-07-11 20:29:23', '1', '20', '0');
INSERT INTO `trade` VALUES ('55', '10', '6', '135.00', '2013-07-11 20:29:38', '1', '20', '0');
INSERT INTO `trade` VALUES ('56', '10', '4', '53.00', '2013-07-12 16:56:57', '1', '34', '0');
INSERT INTO `trade` VALUES ('57', '10', '4', '31.00', '2013-07-12 17:11:53', '1', '34', '0');
INSERT INTO `trade` VALUES ('61', '10', '2', '20.00', '2013-07-12 17:22:00', '1', '35', '0');
INSERT INTO `trade` VALUES ('62', '14', '9', '161.00', '2013-07-12 18:25:19', '1', '36', '0');
INSERT INTO `trade` VALUES ('63', '14', '1', '500.00', '2013-07-12 18:25:26', '1', '36', '0');
INSERT INTO `trade` VALUES ('64', '13', '3', '31.00', '2013-07-12 18:25:43', '1', '36', '0');
INSERT INTO `trade` VALUES ('65', '12', '4', '105.00', '2013-07-12 18:26:05', '1', '36', '0');
INSERT INTO `trade` VALUES ('66', '10', '1', '22.00', '2013-07-12 19:36:41', '1', '37', '0');
INSERT INTO `trade` VALUES ('67', '10', '4', '44.00', '2013-07-12 19:36:47', '1', '37', '0');
INSERT INTO `trade` VALUES ('68', '10', '18', '168.00', '2013-07-13 14:56:50', '1', '39', '0');
INSERT INTO `trade` VALUES ('69', '10', '9', '117.00', '2013-07-13 17:43:05', '1', '39', '0');
INSERT INTO `trade` VALUES ('70', '10', '3', '33.00', '2013-07-14 00:18:19', '1', '40', '0');
INSERT INTO `trade` VALUES ('71', '10', '17', '281.00', '2013-07-14 11:35:34', '1', '40', '0');
INSERT INTO `trade` VALUES ('72', '10', '100', '100.00', '2013-07-14 13:04:47', '1', '40', '0');
INSERT INTO `trade` VALUES ('73', '10', '100', '1000.00', '2013-07-14 13:05:10', '1', '41', '0');
INSERT INTO `trade` VALUES ('74', '10', '110', '1630.00', '2013-07-14 13:44:48', '1', '41', '0');
INSERT INTO `trade` VALUES ('75', '10', '26', '5260.00', '2013-07-14 13:46:03', '1', '41', '0');
INSERT INTO `trade` VALUES ('76', '10', '105', '174.00', '2013-07-14 13:48:42', '1', '41', '0');
INSERT INTO `trade` VALUES ('77', '10', '111', '340.00', '2013-07-21 22:52:25', '1', '42', '0');
INSERT INTO `trade` VALUES ('78', '10', '5', '41.92', '2013-07-22 19:30:38', '1', '42', '0');
INSERT INTO `trade` VALUES ('79', '10', '9', '103.30', '2013-07-22 20:43:46', '1', '43', '0');
INSERT INTO `trade` VALUES ('80', '10', '20', '110.00', '2013-07-23 22:45:05', '1', '44', '0');
INSERT INTO `trade` VALUES ('81', '10', '20', '110.00', '2013-07-23 22:47:12', '1', '44', '0');
INSERT INTO `trade` VALUES ('82', '10', '20', '110.00', '2013-07-23 22:50:14', '1', '44', '0');
INSERT INTO `trade` VALUES ('83', '10', '200', '1100.00', '2013-07-23 23:26:17', '1', '44', '0');
INSERT INTO `trade` VALUES ('84', '10', '2', '11.00', '2013-07-23 23:50:00', '1', '44', '0');
INSERT INTO `trade` VALUES ('85', '10', '198', '1089.00', '2013-07-23 23:54:36', '1', '44', '0');
INSERT INTO `trade` VALUES ('86', '10', '60', '150.00', '2013-07-23 23:56:44', '1', '44', '0');
INSERT INTO `trade` VALUES ('87', '10', '200', '1100.00', '2013-07-24 00:04:21', '1', '44', '0');
INSERT INTO `trade` VALUES ('88', '10', '1', '500.00', '2013-07-24 00:06:05', '1', '44', '0');
INSERT INTO `trade` VALUES ('89', '10', '110', '200.00', '2013-07-24 00:07:09', '1', '44', '0');
INSERT INTO `trade` VALUES ('90', '10', '130', '1300.00', '2013-07-24 00:07:40', '1', '44', '0');
INSERT INTO `trade` VALUES ('91', '10', '1', '1.00', '2013-07-24 00:20:11', '1', '44', '0');
INSERT INTO `trade` VALUES ('92', '10', '200', '4500.00', '2013-07-24 00:42:33', '1', '44', '0');
INSERT INTO `trade` VALUES ('93', '10', '10', '200.00', '2013-07-25 00:27:42', '1', '44', '0');
INSERT INTO `trade` VALUES ('94', '10', '2', '11.00', '2013-07-25 00:28:57', '1', '44', '0');
INSERT INTO `trade` VALUES ('95', '10', '10', '200.00', '2013-07-25 00:36:44', '1', '44', '0');
INSERT INTO `trade` VALUES ('96', '10', '10', '200.00', '2013-07-25 00:37:12', '1', '44', '0');
INSERT INTO `trade` VALUES ('97', '10', '3', '21.00', '2013-07-25 00:39:38', '1', '44', '0');
INSERT INTO `trade` VALUES ('98', '10', '1', '10.00', '2013-07-25 00:43:29', '1', '44', '0');
INSERT INTO `trade` VALUES ('99', '10', '2', '501.00', '2013-07-26 23:03:18', '1', '45', '0');
INSERT INTO `trade` VALUES ('100', '10', '3', '511.00', '2013-07-27 17:59:47', '1', '46', '0');
INSERT INTO `trade` VALUES ('101', '10', '2', '11.00', '2013-08-04 22:48:55', '1', '47', '0');
INSERT INTO `trade` VALUES ('102', '15', '1', '500.00', '2013-08-28 22:04:57', '1', '48', '0');
INSERT INTO `trade` VALUES ('103', '15', '10', '5000.00', '2013-08-28 22:14:54', '1', '49', '0');
INSERT INTO `trade` VALUES ('104', '10', '2', '11.00', '2013-09-10 18:57:13', '1', '49', '0');
INSERT INTO `trade` VALUES ('105', '10', '11', '260.00', '2013-09-10 21:21:33', '1', '49', '0');
INSERT INTO `trade` VALUES ('106', '10', '4', '1020.00', '2013-09-10 22:23:17', '1', '50', '0');
INSERT INTO `trade` VALUES ('108', '10', '4', '2000.00', '2013-09-10 22:27:24', '1', '50', '0');
INSERT INTO `trade` VALUES ('109', '10', '10', '100.00', '2013-09-19 23:18:52', '1', '50', '0');
INSERT INTO `trade` VALUES ('113', '10', '3', '213.00', '2013-09-22 16:34:51', '1', '50', '0');
INSERT INTO `trade` VALUES ('114', '10', '1', '500.00', '2013-09-22 19:16:35', '1', '50', '1');
INSERT INTO `trade` VALUES ('115', '10', '3', '256.00', '2013-09-22 19:22:45', '1', '50', '1');
INSERT INTO `trade` VALUES ('116', '10', '2', '1000.00', '2013-09-22 19:23:10', '1', '50', '1');
INSERT INTO `trade` VALUES ('117', '10', '2', '120.00', '2013-09-22 19:23:46', '1', '50', '0');
INSERT INTO `trade` VALUES ('118', '10', '3', '178.00', '2013-09-22 19:29:17', '1', '51', '1');
INSERT INTO `trade` VALUES ('119', '10', '2', '200.00', '2013-09-22 22:16:04', '1', '51', '0');
INSERT INTO `trade` VALUES ('120', '10', '5', '55.00', '2013-09-22 22:20:41', '1', '51', '0');
INSERT INTO `trade` VALUES ('121', '10', '2', '156.00', '2013-09-23 00:09:18', '1', '51', '0');
INSERT INTO `trade` VALUES ('122', '10', '2', '101.00', '2013-09-23 23:28:23', '1', '52', '1');
INSERT INTO `trade` VALUES ('123', '10', '3', '21.00', '2013-09-24 23:13:13', '1', '52', '1');
INSERT INTO `trade` VALUES ('124', '10', '2', '156.00', '2013-09-24 23:17:57', '1', '52', '1');
INSERT INTO `trade` VALUES ('125', '10', '2', '11.00', '2013-09-24 23:39:07', '1', '52', '1');
INSERT INTO `trade` VALUES ('126', '10', '2', '156.00', '2013-09-25 01:38:22', '1', '52', '1');
INSERT INTO `trade` VALUES ('127', '10', '3', '213.00', '2013-09-25 01:41:15', '1', '52', '1');
INSERT INTO `trade` VALUES ('131', '10', '2', '2.00', '2013-09-25 01:51:22', '1', '52', '1');
INSERT INTO `trade` VALUES ('132', '10', '2', '2.00', '2013-09-25 01:54:29', '1', '52', '1');
INSERT INTO `trade` VALUES ('133', '10', '2', '57.00', '2013-09-25 01:57:53', '1', '52', '1');
INSERT INTO `trade` VALUES ('134', '10', '6', '209.00', '2013-09-28 22:59:04', '1', '52', '1');
INSERT INTO `trade` VALUES ('135', '10', '6', '209.00', '2013-09-28 23:00:00', '1', '52', '1');
INSERT INTO `trade` VALUES ('136', '10', '7', '219.00', '2013-09-28 23:03:50', '1', '52', '1');
INSERT INTO `trade` VALUES ('137', '10', '6', '223.00', '2013-09-28 23:10:29', '1', '52', '1');
INSERT INTO `trade` VALUES ('138', '10', '3', '111.00', '2013-09-28 23:12:57', '1', '52', '1');
INSERT INTO `trade` VALUES ('139', '10', '3', '111.00', '2013-09-28 23:15:10', '1', '52', '1');
INSERT INTO `trade` VALUES ('140', '10', '3', '111.00', '2013-09-28 23:17:37', '1', '52', '1');
INSERT INTO `trade` VALUES ('141', '10', '3', '111.00', '2013-09-28 23:19:09', '1', '52', '0');
INSERT INTO `trade` VALUES ('142', '10', '1', '1.00', '2013-09-28 23:24:34', '1', '52', '0');
INSERT INTO `trade` VALUES ('143', '10', '2', '2.00', '2013-09-28 23:24:40', '1', '52', '1');
INSERT INTO `trade` VALUES ('144', '10', '1', '1.00', '2013-09-28 23:25:01', '1', '52', '1');
INSERT INTO `trade` VALUES ('145', '10', '1', '1.00', '2013-09-28 23:25:27', '1', '52', '1');
INSERT INTO `trade` VALUES ('146', '10', '1', '1.00', '2013-09-28 23:25:56', '1', '52', '1');
INSERT INTO `trade` VALUES ('147', '10', '1', '10.00', '2013-09-28 23:27:08', '1', '52', '1');
INSERT INTO `trade` VALUES ('148', '10', '1', '1.00', '2013-09-28 23:54:41', '1', '52', '1');
INSERT INTO `trade` VALUES ('149', '10', '1', '10.00', '2013-09-29 00:01:17', '1', '52', '1');
INSERT INTO `trade` VALUES ('150', '10', '1', '10.00', '2013-09-29 00:12:50', '1', '52', '1');
INSERT INTO `trade` VALUES ('151', '10', '3', '111.00', '2013-09-29 00:13:20', '1', '52', '1');
INSERT INTO `trade` VALUES ('152', '10', '1', '10.00', '2013-09-29 00:15:01', '1', '52', '1');
INSERT INTO `trade` VALUES ('153', '10', '1', '10.00', '2013-09-29 00:17:11', '1', '52', '1');
INSERT INTO `trade` VALUES ('154', '10', '2', '11.00', '2013-09-29 00:23:56', '1', '52', '1');
INSERT INTO `trade` VALUES ('155', '10', '2', '20.00', '2013-09-29 01:03:10', '1', '52', '1');
INSERT INTO `trade` VALUES ('156', '10', '2', '20.00', '2013-09-29 01:04:30', '1', '52', '1');
INSERT INTO `trade` VALUES ('157', '10', '1', '10.00', '2013-10-03 21:19:50', '1', '52', '1');
INSERT INTO `trade` VALUES ('158', '10', '2', '11.00', '2013-10-03 21:22:10', '1', '52', '1');
INSERT INTO `trade` VALUES ('159', '10', '10', '10.00', '2013-10-03 21:22:50', '1', '52', '1');
INSERT INTO `trade` VALUES ('160', '10', '5', '50.00', '2013-10-03 21:43:02', '1', '52', '1');
INSERT INTO `trade` VALUES ('161', '10', '3', '64.00', '2013-10-03 21:44:10', '1', '52', '1');
INSERT INTO `trade` VALUES ('162', '10', '2', '20.00', '2013-10-03 21:45:24', '1', '52', '1');
INSERT INTO `trade` VALUES ('163', '10', '2', '40.00', '2013-10-03 21:46:35', '1', '52', '1');
INSERT INTO `trade` VALUES ('164', '10', '2', '20.00', '2013-10-03 21:49:41', '1', '52', '1');
INSERT INTO `trade` VALUES ('165', '10', '1', '10.00', '2013-10-03 21:50:27', '1', '52', '1');
INSERT INTO `trade` VALUES ('166', '10', '1', '21.00', '2013-10-03 21:51:50', '1', '52', '1');
INSERT INTO `trade` VALUES ('167', '10', '2', '20.00', '2013-10-03 21:52:06', '1', '52', '1');
INSERT INTO `trade` VALUES ('168', '10', '1', '10.00', '2013-10-03 21:52:19', '1', '52', '1');
INSERT INTO `trade` VALUES ('169', '10', '1', '10.00', '2013-10-03 21:52:35', '1', '52', '1');
INSERT INTO `trade` VALUES ('170', '10', '3', '32.00', '2013-10-03 21:52:51', '1', '52', '1');
INSERT INTO `trade` VALUES ('171', '10', '2', '43.00', '2013-10-03 21:53:09', '1', '52', '1');
INSERT INTO `trade` VALUES ('172', '10', '2', '30.00', '2013-10-03 21:53:55', '1', '52', '1');
INSERT INTO `trade` VALUES ('173', '10', '2', '2.00', '2013-10-03 21:54:42', '1', '52', '1');
INSERT INTO `trade` VALUES ('174', '10', '7', '139.00', '2013-10-03 21:55:10', '1', '52', '1');
INSERT INTO `trade` VALUES ('175', '10', '1', '21.00', '2013-10-03 21:56:14', '1', '52', '1');
INSERT INTO `trade` VALUES ('176', '10', '1', '500.00', '2013-10-03 22:02:43', '1', '52', '1');
INSERT INTO `trade` VALUES ('177', '10', '1', '21.00', '2013-10-03 22:08:36', '1', '52', '1');
INSERT INTO `trade` VALUES ('178', '10', '1', '10.00', '2013-10-03 22:10:21', '1', '52', '1');
INSERT INTO `trade` VALUES ('179', '10', '2', '2.00', '2013-10-07 12:41:09', '1', '52', '0');
INSERT INTO `trade` VALUES ('180', '10', '2', '2.00', '2013-10-07 12:41:54', '1', '52', '0');
INSERT INTO `trade` VALUES ('181', '10', '1', '55.00', '2013-10-07 12:55:35', '1', '53', '0');
INSERT INTO `trade` VALUES ('182', '10', '1', '150.00', '2013-10-07 12:57:28', '1', '53', '0');
INSERT INTO `trade` VALUES ('183', '10', '1', '55.00', '2013-10-08 01:05:38', '1', '53', '0');
INSERT INTO `trade` VALUES ('184', '10', '2', '300.00', '2013-10-08 01:07:17', '1', '53', '0');
INSERT INTO `trade` VALUES ('185', '10', '3', '505.00', '2013-10-08 01:34:15', '1', '53', '0');
INSERT INTO `trade` VALUES ('186', '10', '3', '120.00', '2013-10-21 22:04:58', '1', '54', '1');
INSERT INTO `trade` VALUES ('187', '10', '2', '2.00', '2013-10-21 22:05:02', '1', '54', '0');
INSERT INTO `trade` VALUES ('188', '10', '2', '2.00', '2013-10-21 22:08:33', '1', '56', '0');
INSERT INTO `trade` VALUES ('189', '10', '10', '100.00', '2013-10-21 22:09:50', '1', '56', '1');
INSERT INTO `trade` VALUES ('190', '10', '3', '12.00', '2013-10-21 23:23:24', '1', '57', '0');
INSERT INTO `trade` VALUES ('191', '10', '3', '116.00', '2013-10-21 23:23:27', '1', '57', '1');
INSERT INTO `trade` VALUES ('192', '10', '3', '116.00', '2013-10-21 23:23:31', '1', '57', '0');
INSERT INTO `trade` VALUES ('193', '10', '11', '11.00', '2013-10-21 23:23:34', '1', '57', '0');
INSERT INTO `trade` VALUES ('194', '10', '6', '6.00', '2013-10-21 23:23:38', '2', null, '1');
INSERT INTO `trade` VALUES ('195', '10', '5', '50.00', '2013-10-23 02:24:49', '2', null, '1');
INSERT INTO `trade` VALUES ('196', '10', '3', '300.00', '2013-10-23 02:24:52', '1', '58', '0');
INSERT INTO `trade` VALUES ('197', '10', '6', '6.00', '2013-10-23 02:24:55', '1', '58', '1');
INSERT INTO `trade` VALUES ('198', '10', '1', '500.00', '2013-10-23 02:24:57', '2', null, '0');
INSERT INTO `trade` VALUES ('199', '10', '3', '116.00', '2013-11-09 21:53:28', '1', '58', '0');
INSERT INTO `trade` VALUES ('200', '10', '5', '272.00', '2013-11-09 22:00:19', '1', '58', '0');
INSERT INTO `trade` VALUES ('201', '10', '1', '0.00', '2014-03-27 19:24:44', '1', '58', '0');
INSERT INTO `trade` VALUES ('202', '10', '3', '201.00', '2014-05-01 00:02:53', '1', '58', '1');
INSERT INTO `trade` VALUES ('203', '10', '3', '201.00', '2014-05-05 00:20:04', '1', '58', '0');
INSERT INTO `trade` VALUES ('204', '10', '3', '201.00', '2014-05-05 00:27:08', '1', '58', '0');
INSERT INTO `trade` VALUES ('205', '10', '3', '201.00', '2014-05-05 01:30:14', '1', '58', '0');
INSERT INTO `trade` VALUES ('206', '10', '6', '510.00', '2014-05-05 01:30:22', '1', '58', '1');
INSERT INTO `trade` VALUES ('207', '10', '3', '201.00', '2014-05-05 01:43:19', '1', '58', '0');
INSERT INTO `trade` VALUES ('208', '10', '3', '30.00', '2014-05-05 01:43:24', '1', '58', '1');
INSERT INTO `trade` VALUES ('209', '10', '2', '110.00', '2014-06-10 00:15:54', '1', '58', '1');
INSERT INTO `trade` VALUES ('210', '10', '3', '111.00', '2014-06-10 00:19:50', '1', '58', '1');
INSERT INTO `trade` VALUES ('211', '10', '1', '100.00', '2014-06-10 00:25:29', '1', '58', '1');
INSERT INTO `trade` VALUES ('212', '10', '4', '131.00', '2014-06-11 01:24:53', '1', '58', '0');
INSERT INTO `trade` VALUES ('213', '10', '1', '100.00', '2014-06-11 01:51:12', '1', '58', '0');
INSERT INTO `trade` VALUES ('214', '10', '2', '40.00', '2014-06-11 01:54:49', '1', '58', '0');
INSERT INTO `trade` VALUES ('215', '10', '10', '10.00', '2014-06-11 01:58:17', '1', '58', '0');
INSERT INTO `trade` VALUES ('216', '10', '20', '101.00', '2015-02-22 21:36:59', '1', '58', '0');
INSERT INTO `trade` VALUES ('217', '10', '9', '850.00', '2015-02-22 21:37:12', '1', '58', '0');

-- ----------------------------
-- Table structure for `trade_invoice`
-- ----------------------------
DROP TABLE IF EXISTS `trade_invoice`;
CREATE TABLE `trade_invoice` (
  `id` int(11) NOT NULL,
  `company_code` varchar(40) default NULL,
  `invoice_number` varchar(40) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_invoice
-- ----------------------------
INSERT INTO `trade_invoice` VALUES ('114', '16487295', 'NQ10000001', '1');
INSERT INTO `trade_invoice` VALUES ('115', '16487295', 'NQ10000002', '1');
INSERT INTO `trade_invoice` VALUES ('116', '21345678', 'NQ10000003', '1');
INSERT INTO `trade_invoice` VALUES ('118', '12345678', 'NQ10000250', '1');
INSERT INTO `trade_invoice` VALUES ('122', '', 'NQ10000001', '1');
INSERT INTO `trade_invoice` VALUES ('123', '', 'NQ10000001', '1');
INSERT INTO `trade_invoice` VALUES ('124', '', 'NQ10000002', '1');
INSERT INTO `trade_invoice` VALUES ('125', '', 'NQ10000003', '1');
INSERT INTO `trade_invoice` VALUES ('126', '', 'NQ10000004', '1');
INSERT INTO `trade_invoice` VALUES ('127', '', 'NQ10000005', '1');
INSERT INTO `trade_invoice` VALUES ('131', '', 'NQ10000006', '1');
INSERT INTO `trade_invoice` VALUES ('132', '', 'NQ10000007', '1');
INSERT INTO `trade_invoice` VALUES ('133', '', 'NQ10000008', '1');
INSERT INTO `trade_invoice` VALUES ('134', '', 'NQ10000009', '1');
INSERT INTO `trade_invoice` VALUES ('135', '', 'NQ10000010', '1');
INSERT INTO `trade_invoice` VALUES ('136', '', 'NQ10000011', '1');
INSERT INTO `trade_invoice` VALUES ('137', '', 'NQ10000012', '1');
INSERT INTO `trade_invoice` VALUES ('138', '', 'NQ10000013', '1');
INSERT INTO `trade_invoice` VALUES ('139', '', 'NQ10000014', '1');
INSERT INTO `trade_invoice` VALUES ('140', '', 'NQ10000015', '1');
INSERT INTO `trade_invoice` VALUES ('143', '', 'NQ10000016', '1');
INSERT INTO `trade_invoice` VALUES ('144', '', 'NQ10000017', '1');
INSERT INTO `trade_invoice` VALUES ('145', '', 'NQ10000018', '1');
INSERT INTO `trade_invoice` VALUES ('146', '', 'NQ10000019', '1');
INSERT INTO `trade_invoice` VALUES ('147', '', 'NQ10000020', '1');
INSERT INTO `trade_invoice` VALUES ('148', '', 'NQ10000021', '1');
INSERT INTO `trade_invoice` VALUES ('149', '', 'NQ10000022', '1');
INSERT INTO `trade_invoice` VALUES ('150', '', 'NQ10000023', '1');
INSERT INTO `trade_invoice` VALUES ('151', '', 'NQ10000024', '1');
INSERT INTO `trade_invoice` VALUES ('152', '', 'NQ10000025', '1');
INSERT INTO `trade_invoice` VALUES ('153', '', 'NQ10000026', '1');
INSERT INTO `trade_invoice` VALUES ('154', '', 'NQ10000027', '1');
INSERT INTO `trade_invoice` VALUES ('155', '', 'NQ10000028', '1');
INSERT INTO `trade_invoice` VALUES ('156', '', 'NQ10000029', '1');
INSERT INTO `trade_invoice` VALUES ('157', '', 'NQ10000030', '1');
INSERT INTO `trade_invoice` VALUES ('158', '', 'NQ10000031', '1');
INSERT INTO `trade_invoice` VALUES ('159', '', 'NQ10000032', '1');
INSERT INTO `trade_invoice` VALUES ('160', '', 'NQ10000033', '1');
INSERT INTO `trade_invoice` VALUES ('161', '', 'NQ10000034', '1');
INSERT INTO `trade_invoice` VALUES ('162', '', 'NQ10000035', '1');
INSERT INTO `trade_invoice` VALUES ('163', '', 'NQ10000036', '1');
INSERT INTO `trade_invoice` VALUES ('164', '', 'NQ10000037', '1');
INSERT INTO `trade_invoice` VALUES ('165', '', 'NQ10000038', '1');
INSERT INTO `trade_invoice` VALUES ('166', '', 'NQ10000039', '1');
INSERT INTO `trade_invoice` VALUES ('167', '', 'NQ10000040', '1');
INSERT INTO `trade_invoice` VALUES ('168', '', 'NQ10000041', '1');
INSERT INTO `trade_invoice` VALUES ('169', '', 'NQ10000042', '1');
INSERT INTO `trade_invoice` VALUES ('170', '', 'NQ10000043', '1');
INSERT INTO `trade_invoice` VALUES ('171', '', 'NQ10000044', '1');
INSERT INTO `trade_invoice` VALUES ('172', '', 'NQ10000045', '1');
INSERT INTO `trade_invoice` VALUES ('173', '', 'NQ10000046', '1');
INSERT INTO `trade_invoice` VALUES ('174', '', 'NQ10000047', '1');
INSERT INTO `trade_invoice` VALUES ('175', '', 'NQ10000048', '1');
INSERT INTO `trade_invoice` VALUES ('176', '', 'NQ10000049', '1');
INSERT INTO `trade_invoice` VALUES ('177', '', 'NQ10000050', '1');
INSERT INTO `trade_invoice` VALUES ('178', '', 'NQ10000051', '1');
INSERT INTO `trade_invoice` VALUES ('186', '', 'PL70858250', '1');
INSERT INTO `trade_invoice` VALUES ('189', '', 'PL70858251', '1');
INSERT INTO `trade_invoice` VALUES ('191', '', 'PL70858252', '1');
INSERT INTO `trade_invoice` VALUES ('194', '', 'PL70858253', '1');
INSERT INTO `trade_invoice` VALUES ('195', '', 'PL70858254', '1');
INSERT INTO `trade_invoice` VALUES ('197', '', 'PL70858255', '1');
INSERT INTO `trade_invoice` VALUES ('202', '', 'PL70858256', '1');
INSERT INTO `trade_invoice` VALUES ('203', null, null, null);
INSERT INTO `trade_invoice` VALUES ('206', '', 'PL70858257', '1');
INSERT INTO `trade_invoice` VALUES ('208', '', 'PL70858258', '1');
INSERT INTO `trade_invoice` VALUES ('209', '', 'PL70858259', '1');
INSERT INTO `trade_invoice` VALUES ('210', '', 'PL70858260', '1');
INSERT INTO `trade_invoice` VALUES ('211', '', 'PL70858261', '0');

-- ----------------------------
-- Table structure for `tradeitem`
-- ----------------------------
DROP TABLE IF EXISTS `tradeitem`;
CREATE TABLE `tradeitem` (
  `id` int(11) NOT NULL,
  `product_id` int(11) default NULL,
  `trade_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  `purchase_price` double(11,2) default NULL,
  `total` double(11,2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Relationship_2` (`product_id`),
  KEY `FK_Relationship_3` (`trade_id`),
  CONSTRAINT `FK_Relationship_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_Relationship_3` FOREIGN KEY (`trade_id`) REFERENCES `trade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tradeitem
-- ----------------------------
INSERT INTO `tradeitem` VALUES ('1', '202', '1', '7', '25.00', '175.00');
INSERT INTO `tradeitem` VALUES ('2', '211', '1', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('3', '212', '1', '1', '30.00', '30.00');
INSERT INTO `tradeitem` VALUES ('4', '209', '2', '6', '10.00', '60.00');
INSERT INTO `tradeitem` VALUES ('5', '206', '2', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('6', '214', '3', '4', '500.00', '2000.00');
INSERT INTO `tradeitem` VALUES ('7', '209', '4', '5', '10.00', '50.00');
INSERT INTO `tradeitem` VALUES ('8', '202', '5', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('9', '211', '5', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('10', '212', '5', '1', '30.00', '30.00');
INSERT INTO `tradeitem` VALUES ('11', '205', '6', '3', '20.00', '60.00');
INSERT INTO `tradeitem` VALUES ('12', '204', '6', '3', '15.00', '45.00');
INSERT INTO `tradeitem` VALUES ('13', '209', '7', '4', '10.00', '40.00');
INSERT INTO `tradeitem` VALUES ('14', '206', '7', '3', '1.00', '3.00');
INSERT INTO `tradeitem` VALUES ('15', '202', '8', '4', '25.00', '100.00');
INSERT INTO `tradeitem` VALUES ('16', '205', '8', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('17', '204', '8', '2', '15.00', '30.00');
INSERT INTO `tradeitem` VALUES ('18', '211', '8', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('19', '212', '8', '1', '30.00', '30.00');
INSERT INTO `tradeitem` VALUES ('55', '202', '26', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('56', '212', '26', '1', '30.00', '30.00');
INSERT INTO `tradeitem` VALUES ('57', '215', '26', '8', '20.00', '160.00');
INSERT INTO `tradeitem` VALUES ('58', '207', '27', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('59', '211', '28', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('60', '202', '28', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('61', '215', '28', '2', '20.00', '40.00');
INSERT INTO `tradeitem` VALUES ('62', '209', '29', '5', '10.00', '50.00');
INSERT INTO `tradeitem` VALUES ('63', '209', '30', '6', '10.00', '60.00');
INSERT INTO `tradeitem` VALUES ('64', '206', '30', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('65', '205', '30', '3', '20.00', '60.00');
INSERT INTO `tradeitem` VALUES ('66', '204', '30', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('67', '207', '31', '8', '15.00', '120.00');
INSERT INTO `tradeitem` VALUES ('68', '210', '31', '7', '55.00', '385.00');
INSERT INTO `tradeitem` VALUES ('69', '206', '32', '4', '1.00', '4.00');
INSERT INTO `tradeitem` VALUES ('70', '209', '32', '5', '10.00', '50.00');
INSERT INTO `tradeitem` VALUES ('71', '209', '33', '4', '10.00', '40.00');
INSERT INTO `tradeitem` VALUES ('72', '215', '34', '7', '20.00', '140.00');
INSERT INTO `tradeitem` VALUES ('73', '211', '34', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('74', '202', '34', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('75', '207', '35', '4', '15.00', '60.00');
INSERT INTO `tradeitem` VALUES ('76', '206', '35', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('77', '210', '35', '1', '55.00', '55.00');
INSERT INTO `tradeitem` VALUES ('78', '209', '35', '5', '10.00', '50.00');
INSERT INTO `tradeitem` VALUES ('79', '204', '36', '5', '15.00', '75.00');
INSERT INTO `tradeitem` VALUES ('80', '205', '36', '5', '20.00', '100.00');
INSERT INTO `tradeitem` VALUES ('81', '206', '37', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('82', '209', '37', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('83', '204', '38', '2', '15.00', '30.00');
INSERT INTO `tradeitem` VALUES ('84', '205', '38', '5', '20.00', '100.00');
INSERT INTO `tradeitem` VALUES ('85', '211', '39', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('86', '215', '39', '2', '20.00', '40.00');
INSERT INTO `tradeitem` VALUES ('87', '202', '39', '3', '25.00', '75.00');
INSERT INTO `tradeitem` VALUES ('88', '207', '40', '7', '15.00', '105.00');
INSERT INTO `tradeitem` VALUES ('89', '209', '41', '3', '10.00', '30.00');
INSERT INTO `tradeitem` VALUES ('90', '206', '41', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('91', '215', '42', '4', '20.00', '80.00');
INSERT INTO `tradeitem` VALUES ('92', '204', '42', '3', '15.00', '45.00');
INSERT INTO `tradeitem` VALUES ('93', '202', '42', '3', '25.00', '75.00');
INSERT INTO `tradeitem` VALUES ('94', '206', '42', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('95', '209', '42', '9', '10.00', '90.00');
INSERT INTO `tradeitem` VALUES ('96', '211', '42', '4', '25.00', '100.00');
INSERT INTO `tradeitem` VALUES ('97', '210', '42', '1', '55.00', '55.00');
INSERT INTO `tradeitem` VALUES ('98', '214', '42', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('99', '206', '43', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('100', '209', '43', '4', '10.00', '40.00');
INSERT INTO `tradeitem` VALUES ('101', '207', '43', '3', '15.00', '45.00');
INSERT INTO `tradeitem` VALUES ('102', '206', '44', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('103', '215', '44', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('104', '214', '44', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('105', '217', '44', '2', '22.00', '44.00');
INSERT INTO `tradeitem` VALUES ('106', '209', '44', '6', '10.00', '60.00');
INSERT INTO `tradeitem` VALUES ('107', '211', '44', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('108', '218', '44', '2', '13.00', '26.00');
INSERT INTO `tradeitem` VALUES ('109', '216', '44', '2', '11.00', '22.00');
INSERT INTO `tradeitem` VALUES ('110', '218', '45', '1', '13.00', '13.00');
INSERT INTO `tradeitem` VALUES ('111', '217', '45', '18', '22.00', '396.00');
INSERT INTO `tradeitem` VALUES ('112', '207', '45', '2', '15.00', '30.00');
INSERT INTO `tradeitem` VALUES ('113', '215', '46', '6', '20.00', '120.00');
INSERT INTO `tradeitem` VALUES ('114', '202', '46', '2', '25.00', '50.00');
INSERT INTO `tradeitem` VALUES ('115', '209', '46', '18', '10.00', '180.00');
INSERT INTO `tradeitem` VALUES ('116', '210', '47', '4', '55.00', '220.00');
INSERT INTO `tradeitem` VALUES ('117', '202', '47', '14', '25.00', '350.00');
INSERT INTO `tradeitem` VALUES ('118', '206', '47', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('119', '209', '47', '31', '10.00', '310.00');
INSERT INTO `tradeitem` VALUES ('120', '206', '48', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('121', '209', '48', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('122', '217', '48', '4', '22.00', '88.00');
INSERT INTO `tradeitem` VALUES ('123', '206', '49', '3', '1.00', '3.00');
INSERT INTO `tradeitem` VALUES ('124', '217', '49', '6', '22.00', '132.00');
INSERT INTO `tradeitem` VALUES ('125', '209', '49', '28', '10.00', '280.00');
INSERT INTO `tradeitem` VALUES ('126', '209', '50', '5', '10.00', '50.00');
INSERT INTO `tradeitem` VALUES ('127', '217', '50', '4', '22.00', '88.00');
INSERT INTO `tradeitem` VALUES ('128', '214', '50', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('129', '206', '50', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('130', '206', '51', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('131', '209', '51', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('132', '209', '52', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('133', '206', '52', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('134', '217', '52', '1', '22.00', '22.00');
INSERT INTO `tradeitem` VALUES ('135', '217', '53', '1', '22.00', '22.00');
INSERT INTO `tradeitem` VALUES ('136', '207', '53', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('137', '209', '53', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('138', '206', '53', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('139', '217', '54', '1', '22.00', '22.00');
INSERT INTO `tradeitem` VALUES ('140', '209', '54', '3', '10.00', '30.00');
INSERT INTO `tradeitem` VALUES ('141', '205', '55', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('142', '202', '55', '2', '25.00', '50.00');
INSERT INTO `tradeitem` VALUES ('143', '204', '55', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('144', '211', '55', '2', '25.00', '50.00');
INSERT INTO `tradeitem` VALUES ('145', '206', '57', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('146', '209', '57', '3', '10.00', '30.00');
INSERT INTO `tradeitem` VALUES ('149', '209', '61', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('150', '206', '62', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('151', '215', '62', '8', '20.00', '160.00');
INSERT INTO `tradeitem` VALUES ('152', '214', '63', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('153', '209', '64', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('154', '221', '64', '1', '11.00', '11.00');
INSERT INTO `tradeitem` VALUES ('155', '204', '65', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('156', '207', '65', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('157', '210', '65', '1', '55.00', '55.00');
INSERT INTO `tradeitem` VALUES ('158', '205', '65', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('159', '217', '66', '1', '22.00', '22.00');
INSERT INTO `tradeitem` VALUES ('160', '209', '67', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('161', '217', '67', '1', '22.00', '22.00');
INSERT INTO `tradeitem` VALUES ('162', '221', '67', '1', '11.00', '11.00');
INSERT INTO `tradeitem` VALUES ('163', '206', '67', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('164', '209', '68', '12', '10.00', '120.00');
INSERT INTO `tradeitem` VALUES ('165', '206', '68', '4', '1.00', '4.00');
INSERT INTO `tradeitem` VALUES ('166', '217', '68', '2', '22.00', '44.00');
INSERT INTO `tradeitem` VALUES ('167', '209', '69', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('168', '217', '69', '1', '22.00', '22.00');
INSERT INTO `tradeitem` VALUES ('169', '206', '69', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('170', '207', '69', '3', '15.00', '45.00');
INSERT INTO `tradeitem` VALUES ('171', '221', '69', '3', '13.00', '39.00');
INSERT INTO `tradeitem` VALUES ('172', '206', '70', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('173', '209', '70', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('174', '217', '70', '1', '22.00', '22.00');
INSERT INTO `tradeitem` VALUES ('175', '209', '71', '6', '10.00', '60.00');
INSERT INTO `tradeitem` VALUES ('176', '206', '71', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('177', '217', '71', '10', '22.00', '220.00');
INSERT INTO `tradeitem` VALUES ('178', '206', '72', '100', '1.00', '100.00');
INSERT INTO `tradeitem` VALUES ('179', '209', '73', '100', '10.00', '1000.00');
INSERT INTO `tradeitem` VALUES ('180', '207', '74', '100', '15.00', '1500.00');
INSERT INTO `tradeitem` VALUES ('181', '221', '74', '10', '13.00', '130.00');
INSERT INTO `tradeitem` VALUES ('182', '205', '75', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('183', '202', '75', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('184', '209', '75', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('185', '204', '75', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('186', '210', '75', '1', '55.00', '55.00');
INSERT INTO `tradeitem` VALUES ('187', '211', '75', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('188', '215', '75', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('189', '214', '75', '10', '500.00', '5000.00');
INSERT INTO `tradeitem` VALUES ('190', '217', '76', '2', '22.00', '44.00');
INSERT INTO `tradeitem` VALUES ('191', '209', '76', '3', '10.00', '30.00');
INSERT INTO `tradeitem` VALUES ('192', '206', '76', '100', '1.00', '100.00');
INSERT INTO `tradeitem` VALUES ('193', '202', '77', '1', '25.00', '25.00');
INSERT INTO `tradeitem` VALUES ('194', '211', '77', '3', '25.00', '75.00');
INSERT INTO `tradeitem` VALUES ('195', '215', '77', '7', '20.00', '140.00');
INSERT INTO `tradeitem` VALUES ('196', '206', '77', '100', '1.00', '100.00');
INSERT INTO `tradeitem` VALUES ('197', '206', '78', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('198', '223', '78', '4', '10.23', '40.92');
INSERT INTO `tradeitem` VALUES ('199', '223', '79', '8', '10.23', '81.84');
INSERT INTO `tradeitem` VALUES ('200', '224', '79', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('201', '206', '80', '10', '1.00', '10.00');
INSERT INTO `tradeitem` VALUES ('202', '209', '80', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('203', '206', '81', '10', '1.00', '10.00');
INSERT INTO `tradeitem` VALUES ('204', '209', '81', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('205', '209', '82', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('206', '206', '82', '10', '1.00', '10.00');
INSERT INTO `tradeitem` VALUES ('207', '206', '83', '100', '1.00', '100.00');
INSERT INTO `tradeitem` VALUES ('208', '209', '83', '100', '10.00', '1000.00');
INSERT INTO `tradeitem` VALUES ('209', '209', '84', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('210', '206', '84', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('211', '209', '85', '99', '10.00', '990.00');
INSERT INTO `tradeitem` VALUES ('212', '206', '85', '99', '1.00', '99.00');
INSERT INTO `tradeitem` VALUES ('213', '206', '86', '50', '1.00', '50.00');
INSERT INTO `tradeitem` VALUES ('214', '209', '86', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('215', '209', '87', '100', '10.00', '1000.00');
INSERT INTO `tradeitem` VALUES ('216', '206', '87', '100', '1.00', '100.00');
INSERT INTO `tradeitem` VALUES ('217', '223', '88', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('218', '206', '89', '100', '1.00', '100.00');
INSERT INTO `tradeitem` VALUES ('219', '209', '89', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('220', '209', '90', '130', '10.00', '1300.00');
INSERT INTO `tradeitem` VALUES ('221', '206', '91', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('222', '215', '92', '100', '20.00', '2000.00');
INSERT INTO `tradeitem` VALUES ('223', '202', '92', '100', '25.00', '2500.00');
INSERT INTO `tradeitem` VALUES ('224', '215', '93', '10', '20.00', '200.00');
INSERT INTO `tradeitem` VALUES ('225', '209', '94', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('226', '206', '94', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('227', '215', '95', '10', '20.00', '200.00');
INSERT INTO `tradeitem` VALUES ('228', '215', '96', '10', '20.00', '200.00');
INSERT INTO `tradeitem` VALUES ('229', '206', '97', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('230', '209', '97', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('231', '209', '98', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('232', '206', '99', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('233', '223', '99', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('234', '206', '100', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('235', '223', '100', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('236', '209', '100', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('237', '206', '101', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('238', '209', '101', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('239', '223', '102', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('240', '223', '103', '10', '500.00', '5000.00');
INSERT INTO `tradeitem` VALUES ('241', '206', '104', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('242', '209', '104', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('243', '211', '105', '5', '25.00', '125.00');
INSERT INTO `tradeitem` VALUES ('244', '202', '105', '3', '25.00', '75.00');
INSERT INTO `tradeitem` VALUES ('245', '215', '105', '3', '20.00', '60.00');
INSERT INTO `tradeitem` VALUES ('246', '223', '106', '2', '500.00', '1000.00');
INSERT INTO `tradeitem` VALUES ('247', '209', '106', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('249', '223', '108', '4', '500.00', '2000.00');
INSERT INTO `tradeitem` VALUES ('250', '209', '109', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('257', '226', '113', '2', '56.30', '112.60');
INSERT INTO `tradeitem` VALUES ('258', '222', '113', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('259', '223', '114', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('260', '222', '115', '2', '99.90', '199.80');
INSERT INTO `tradeitem` VALUES ('261', '226', '115', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('262', '223', '116', '2', '500.00', '1000.00');
INSERT INTO `tradeitem` VALUES ('263', '205', '117', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('264', '222', '117', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('265', '222', '118', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('266', '224', '118', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('267', '226', '118', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('268', '233', '119', '1', '100.00', '100.00');
INSERT INTO `tradeitem` VALUES ('269', '232', '119', '1', '100.00', '100.00');
INSERT INTO `tradeitem` VALUES ('270', '233', '120', '5', '11.00', '55.00');
INSERT INTO `tradeitem` VALUES ('271', '226', '121', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('272', '222', '121', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('273', '206', '122', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('274', '222', '122', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('275', '206', '123', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('276', '209', '123', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('277', '222', '124', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('278', '226', '124', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('279', '209', '125', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('280', '206', '125', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('281', '222', '126', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('282', '226', '126', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('283', '226', '127', '2', '56.30', '112.60');
INSERT INTO `tradeitem` VALUES ('284', '222', '127', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('285', '206', '131', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('286', '206', '132', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('287', '206', '133', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('288', '226', '133', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('289', '205', '134', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('290', '222', '134', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('291', '224', '134', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('292', '226', '134', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('293', '209', '134', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('294', '206', '134', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('295', '222', '135', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('296', '205', '135', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('297', '226', '135', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('298', '224', '135', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('299', '209', '135', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('300', '206', '135', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('301', '209', '136', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('302', '224', '136', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('303', '205', '136', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('304', '206', '136', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('305', '222', '136', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('306', '226', '136', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('307', '222', '137', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('308', '224', '137', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('309', '205', '137', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('310', '204', '137', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('311', '209', '137', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('312', '226', '137', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('313', '209', '138', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('314', '206', '138', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('315', '222', '138', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('316', '206', '139', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('317', '222', '139', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('318', '209', '139', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('319', '222', '140', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('320', '209', '140', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('321', '206', '140', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('322', '206', '141', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('323', '222', '141', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('324', '209', '141', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('325', '206', '142', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('326', '206', '143', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('327', '206', '144', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('328', '206', '145', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('329', '206', '146', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('330', '209', '147', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('331', '206', '148', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('332', '209', '149', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('333', '209', '150', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('334', '209', '151', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('335', '206', '151', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('336', '222', '151', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('337', '209', '152', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('338', '209', '153', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('339', '209', '154', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('340', '206', '154', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('341', '209', '155', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('342', '209', '156', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('343', '209', '157', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('344', '206', '158', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('345', '209', '158', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('346', '206', '159', '10', '1.00', '10.00');
INSERT INTO `tradeitem` VALUES ('347', '209', '160', '5', '10.00', '50.00');
INSERT INTO `tradeitem` VALUES ('348', '224', '161', '3', '21.46', '64.38');
INSERT INTO `tradeitem` VALUES ('349', '209', '162', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('350', '205', '163', '2', '20.00', '40.00');
INSERT INTO `tradeitem` VALUES ('351', '209', '164', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('352', '209', '165', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('353', '224', '166', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('354', '209', '167', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('355', '209', '168', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('356', '209', '169', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('357', '224', '170', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('358', '206', '170', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('359', '209', '170', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('360', '224', '171', '2', '21.46', '42.92');
INSERT INTO `tradeitem` VALUES ('361', '207', '172', '2', '15.00', '30.00');
INSERT INTO `tradeitem` VALUES ('362', '206', '173', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('363', '209', '174', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('364', '204', '174', '2', '15.00', '30.00');
INSERT INTO `tradeitem` VALUES ('365', '226', '174', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('366', '205', '174', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('367', '224', '174', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('368', '206', '174', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('369', '224', '175', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('370', '223', '176', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('371', '224', '177', '1', '21.46', '21.46');
INSERT INTO `tradeitem` VALUES ('372', '209', '178', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('373', '206', '179', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('374', '206', '180', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('375', '210', '181', '1', '55.00', '55.00');
INSERT INTO `tradeitem` VALUES ('376', '240', '182', '1', '150.00', '150.00');
INSERT INTO `tradeitem` VALUES ('377', '210', '183', '1', '55.00', '55.00');
INSERT INTO `tradeitem` VALUES ('378', '240', '184', '2', '150.00', '300.00');
INSERT INTO `tradeitem` VALUES ('379', '238', '185', '1', '300.00', '300.00');
INSERT INTO `tradeitem` VALUES ('380', '240', '185', '1', '150.00', '150.00');
INSERT INTO `tradeitem` VALUES ('381', '210', '185', '1', '55.00', '55.00');
INSERT INTO `tradeitem` VALUES ('382', '222', '186', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('383', '209', '186', '2', '10.00', '20.00');
INSERT INTO `tradeitem` VALUES ('384', '206', '187', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('385', '206', '188', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('386', '209', '189', '10', '10.00', '100.00');
INSERT INTO `tradeitem` VALUES ('387', '209', '190', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('388', '206', '190', '2', '1.00', '2.00');
INSERT INTO `tradeitem` VALUES ('389', '206', '191', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('390', '204', '191', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('391', '222', '191', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('392', '222', '192', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('393', '204', '192', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('394', '206', '192', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('395', '206', '193', '11', '1.00', '11.00');
INSERT INTO `tradeitem` VALUES ('396', '206', '194', '6', '1.00', '6.00');
INSERT INTO `tradeitem` VALUES ('397', '209', '195', '5', '10.00', '50.00');
INSERT INTO `tradeitem` VALUES ('398', '222', '196', '3', '99.90', '299.70');
INSERT INTO `tradeitem` VALUES ('399', '206', '197', '6', '1.00', '6.00');
INSERT INTO `tradeitem` VALUES ('400', '223', '198', '1', '500.00', '500.00');
INSERT INTO `tradeitem` VALUES ('401', '206', '199', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('402', '222', '199', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('403', '204', '199', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('404', '222', '200', '2', '99.90', '199.80');
INSERT INTO `tradeitem` VALUES ('405', '226', '200', '1', '56.30', '56.30');
INSERT INTO `tradeitem` VALUES ('406', '204', '200', '1', '15.00', '15.00');
INSERT INTO `tradeitem` VALUES ('407', '206', '200', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('408', '242', '201', '1', '0.00', '0.00');
INSERT INTO `tradeitem` VALUES ('409', '222', '202', '2', '99.90', '199.80');
INSERT INTO `tradeitem` VALUES ('410', '206', '202', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('411', '206', '203', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('412', '222', '203', '2', '99.90', '199.80');
INSERT INTO `tradeitem` VALUES ('413', '206', '204', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('414', '222', '204', '2', '99.90', '199.80');
INSERT INTO `tradeitem` VALUES ('415', '222', '205', '2', '99.90', '199.80');
INSERT INTO `tradeitem` VALUES ('416', '206', '205', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('417', '222', '206', '5', '99.90', '499.50');
INSERT INTO `tradeitem` VALUES ('418', '209', '206', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('419', '206', '207', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('420', '222', '207', '2', '99.90', '199.80');
INSERT INTO `tradeitem` VALUES ('421', '209', '208', '3', '10.00', '30.00');
INSERT INTO `tradeitem` VALUES ('422', '222', '209', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('423', '209', '209', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('424', '209', '210', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('425', '222', '210', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('426', '206', '210', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('427', '222', '211', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('428', '205', '212', '1', '20.00', '20.00');
INSERT INTO `tradeitem` VALUES ('429', '222', '212', '1', '99.90', '99.90');
INSERT INTO `tradeitem` VALUES ('430', '206', '212', '1', '1.00', '1.00');
INSERT INTO `tradeitem` VALUES ('431', '209', '212', '1', '10.00', '10.00');
INSERT INTO `tradeitem` VALUES ('432', '243', '213', '1', '100.00', '100.00');
INSERT INTO `tradeitem` VALUES ('433', '215', '214', '2', '20.00', '40.00');
INSERT INTO `tradeitem` VALUES ('434', '206', '215', '10', '1.00', '10.00');
INSERT INTO `tradeitem` VALUES ('435', '206', '216', '11', '1.00', '11.00');
INSERT INTO `tradeitem` VALUES ('436', '209', '216', '9', '10.00', '90.00');
INSERT INTO `tradeitem` VALUES ('437', '234', '217', '4', '200.00', '800.00');
INSERT INTO `tradeitem` VALUES ('438', '209', '217', '5', '10.00', '50.00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(40) default NULL,
  `account` varchar(40) default NULL,
  `password` varchar(40) default NULL,
  `purview` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', '建龍', 'xx321', '1234', '1', '1');
INSERT INTO `user` VALUES ('11', '世偉', 'bb321', '1234', '0', '1');
INSERT INTO `user` VALUES ('12', '友敬', 'dd321', '4321', '0', '1');
INSERT INTO `user` VALUES ('13', '亦聖', 'ee321', '4321', '0', '1');
INSERT INTO `user` VALUES ('14', '剛順', 'ss321', '1234', '0', '1');
INSERT INTO `user` VALUES ('15', '葉德慶', 'qq123', '1234', '0', '0');
