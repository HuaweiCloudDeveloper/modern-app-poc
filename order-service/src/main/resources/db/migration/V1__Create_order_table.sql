CREATE TABLE `poc_order_db`.`orders`  (
  `oid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `name` nvarchar(100) NULL,
  `create_time` bigint(0) NULL,
  `total_price` decimal(10, 2) NULL,
  `state` varchar(20),
  PRIMARY KEY (`oid`)
);

CREATE TABLE `poc_order_db`.`order_items`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `oid` int(0) NOT NULL,
  `pid` int(0) NOT NULL,
  `name` nvarchar(100) NULL,
  `price` decimal(10, 2) NULL,
  `amount` int(0),
  PRIMARY KEY (`id`)
);
