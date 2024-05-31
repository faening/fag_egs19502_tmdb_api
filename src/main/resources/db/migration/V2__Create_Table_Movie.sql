CREATE TABLE IF NOT EXISTS `movie`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `tmdb_id` INT DEFAULT NULL,
    `adult` BOOLEAN DEFAULT FALSE,
    `backdrop_path` VARCHAR(255) NOT NULL,
    `original_language` VARCHAR(10) DEFAULT NULL,
    `original_title` VARCHAR(100) DEFAULT NULL,
    `overview` VARCHAR(1000) DEFAULT NULL,
    `popularity` DOUBLE DEFAULT NULL,
    `poster_path` VARCHAR(255) NOT NULL,
    `release_date` DATE NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `video` BOOLEAN DEFAULT FALSE,
    `vote_average` DOUBLE DEFAULT NULL,
    `vote_count` INT DEFAULT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);