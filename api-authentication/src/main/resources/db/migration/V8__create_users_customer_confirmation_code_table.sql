CREATE TABLE IF NOT EXISTS users_customer_confirmation_code (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `confirmation_code` VARCHAR(100),
    `creation_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `user_customer_id` bigint,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_users_customer_confirmation_code_user_customer_id` FOREIGN KEY (`user_customer_id`)
    REFERENCES `users_customer` (`id`) -- ON DELETE CASCADE
) ENGINE=InnoDB;
