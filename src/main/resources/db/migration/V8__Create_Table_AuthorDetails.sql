CREATE TABLE IF NOT EXISTS `author_details`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `username` VARCHAR(100) NOT NULL,
    `avatar_path` VARCHAR(100),
    `rating` INT DEFAULT 0,
    PRIMARY KEY (`id`)
);