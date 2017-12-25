-- auto Generated on 2017-09-09 13:04:48 
-- DROP TABLE IF EXISTS `lifepay_bill`;
CREATE TABLE `lifepay_bill` (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账单id(主键)',
  `last_modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `power_bill` double(10,2) DEFAULT NULL COMMENT '电费账单',
  `power_rate` double(10,2) DEFAULT NULL COMMENT '用电量',
  `record_month` int(2) DEFAULT NULL COMMENT '记录月份',
  `water_bill` double(10,2) DEFAULT NULL COMMENT '水费账单',
  `water_rate` double(10,2) DEFAULT NULL COMMENT '用水量',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
