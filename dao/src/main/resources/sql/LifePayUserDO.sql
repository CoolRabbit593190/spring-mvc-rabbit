-- auto Generated on 2017-09-09 13:04:48 
-- DROP TABLE IF EXISTS `lifepay_user`;
CREATE TABLE `lifepay_user` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id(主键)',
  `last_modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) NOT NULL COMMENT '用户姓名',
  `family_id` int(11) NOT NULL COMMENT '家庭id(主键)',
  PRIMARY KEY (`usr_id`),
  KEY `FK_s8bf59e36ktfnl4rqgfr2on1f` (`family_id`),
  CONSTRAINT `FK_s8bf59e36ktfnl4rqgfr2on1f` FOREIGN KEY (`family_id`) REFERENCES `lifepay_family` (`family_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
