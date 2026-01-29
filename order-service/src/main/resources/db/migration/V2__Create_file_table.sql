CREATE TABLE `poc_order_db`.`files`  (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(255) NOT NULL,
  `file_size` BIGINT DEFAULT 0,
  `file_content` MEDIUMBLOB,
  `upload_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);