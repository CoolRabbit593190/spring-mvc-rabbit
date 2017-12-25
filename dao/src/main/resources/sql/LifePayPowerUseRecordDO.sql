-- auto Generated on 2017-09-09 13:04:48 
-- DROP TABLE IF EXISTS `lifepay_power_use_record`;
CREATE TABLE `lifepay_power_use_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id(主键)',
  `last_modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `power_use` double(10,2) NOT NULL COMMENT '用电量',
  `record_month` int(2) DEFAULT NULL COMMENT '记录月份',
  `family_id` int(11) DEFAULT NULL COMMENT '家庭id(主键)',
  PRIMARY KEY (`record_id`),
  KEY `FK_mgc38a542fhy40usbq7yxiub0` (`family_id`),
  CONSTRAINT `FK_mgc38a542fhy40usbq7yxiub0` FOREIGN KEY (`family_id`) REFERENCES `lifepay_family` (`family_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
