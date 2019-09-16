CREATE SCHEMA IF NOT EXISTS USER;

DROP TABLE IF EXISTS `user`.`address`;

CREATE TABLE `user`.`address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr_line_1` varchar(45) DEFAULT NULL,
  `addr_line_2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `country` varchar(45) NOT NULL,
  `zip` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user`.`user`;

CREATE TABLE `user`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tel_num` decimal(11,0) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `address_ref_key` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
);
