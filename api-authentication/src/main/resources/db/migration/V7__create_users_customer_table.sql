CREATE TABLE IF NOT EXISTS `users_customer` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100),
    `email` VARCHAR(63),
    `phone_number` VARCHAR(15),
    `active` CHAR(1),
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_email` (`email`),
    UNIQUE KEY `uk_phone_number` (`phone_number`)
) ENGINE=InnoDB;
