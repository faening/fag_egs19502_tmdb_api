CREATE TABLE IF NOT EXISTS `review` (
    `id` BIGINT AUTO_INCREMENT,
    `author` VARCHAR(100) NOT NULL,
    `author_details_id` BIGINT,
    `content` TEXT NOT NULL,
    `tmdb_id` VARCHAR(100),
    `url` VARCHAR(100),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author_details_id`) REFERENCES `author_details` (`id`)
);