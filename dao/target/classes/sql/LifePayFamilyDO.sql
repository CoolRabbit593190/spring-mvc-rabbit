-- auto Generated on 2017-09-09 13:04:48 
-- DROP TABLE IF EXISTS `lifepay_family`;
CREATE TABLE `lifepay_family` (
  `family_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '家庭id(主键)',
  `housemaster_name` varchar(50) NOT NULL COMMENT '户主姓名',
  `last_modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`family_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
