CREATE TABLE `movie_cast` (
    `movie_id` BIGINT NOT NULL,
    `cast_id` BIGINT NOT NULL,
    PRIMARY KEY (`movie_id`, `cast_id`),
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
    FOREIGN KEY (`cast_id`) REFERENCES `cast` (`id`)
);