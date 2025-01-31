CREATE TABLE IF NOT EXISTS `users_customer` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100),
    `email` VARCHAR(63),
    `phone_number` VARCHAR(15),
    `active` CHAR(1),
    `user_token_id` bigint,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_email` (`email`),
    UNIQUE KEY `uk_phone_number` (`phone_number`),
    CONSTRAINT `fk_users_customer_user_token_id` FOREIGN KEY (`user_token_id`)
    REFERENCES `users` (`id`) -- ON DELETE CASCADE
) ENGINE=InnoDB;
