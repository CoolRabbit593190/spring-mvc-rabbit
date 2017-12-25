-- auto Generated on 2017-09-09 13:04:48 
DROP TABLE IF EXISTS `lifepay_family_desc`;
CREATE TABLE `lifepay_family_desc`(
    `desc_id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '描述Id(主键)',
    `content` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '内容',
    PRIMARY KEY (`desc_id`),
    `family_id` int(11) NOT NULL COMMENT '家庭id(主键)',
    KEY `FK_s8bb59e36smeel4rqgfr2ds1n` (`family_id`),
    CONSTRAINT `FK_s8bb59e36smeel4rqgfr2ds1n` FOREIGN KEY (`family_id`) REFERENCES `lifepay_family` (`family_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`life_pay_family_desc`';
