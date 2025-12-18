CREATE TABLE `poc_order_db`.`orders`  (
  `oid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `name` varchar(100) NULL,
  `create_time` bigint NULL,
  `total_price` decimal(10, 2) NULL,
  `state` varchar(20),
  PRIMARY KEY (`oid`)
);