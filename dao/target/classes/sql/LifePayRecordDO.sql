-- auto Generated on 2017-09-09 13:04:48 
-- DROP TABLE IF EXISTS `lifepay_record`;
CREATE TABLE `lifepay_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id(主键)',
  `last_modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `power_cost` double(10,2) DEFAULT NULL COMMENT '电费',
  `record_month` int(2) DEFAULT NULL COMMENT '记录月份',
  `water_cost` double(10,2) DEFAULT NULL COMMENT '水费',
  `family_id` int(11) NOT NULL COMMENT '家庭id(主键)',
  PRIMARY KEY (`record_id`),
  KEY `FK_7pa8dcrwwy9cadf6dc2ecvvya` (`family_id`),
  CONSTRAINT `FK_7pa8dcrwwy9cadf6dc2ecvvya` FOREIGN KEY (`family_id`) REFERENCES `lifepay_family` (`family_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
