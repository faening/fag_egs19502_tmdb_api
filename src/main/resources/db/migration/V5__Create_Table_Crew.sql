CREATE TABLE IF NOT EXISTS `crew`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `adult` BOOLEAN DEFAULT FALSE,
    `gender` TINYINT(2) NOT NULL DEFAULT 0,
    `known_for_department` VARCHAR(100) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `original_name` VARCHAR(100) NOT NULL,
    `popularity` DOUBLE DEFAULT 0.0,
    `profile_path` VARCHAR(100),
    `credit_id` VARCHAR(100),
    `department` VARCHAR(100),
    `job` VARCHAR(100),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);