CREATE TABLE IF NOT EXISTS `review` (
    `id` BIGINT AUTO_INCREMENT,
    `content` VARCHAR(1000) NOT NULL,
    `author_id` BIGINT NOT NULL,
    `created_at` VARCHAR(100) NOT NULL,
    `tmdb_author_id` VARCHAR(100) NOT NULL,
    `updated_at` VARCHAR(100) NOT NULL,
    `url` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author_id`) REFERENCES `author_details` (`id`)
);