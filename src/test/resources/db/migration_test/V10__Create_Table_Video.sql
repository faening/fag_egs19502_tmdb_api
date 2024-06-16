CREATE TABLE IF NOT EXISTS video (
    id BIGINT AUTO_INCREMENT,
    movie_id BIGINT,
    iso_639_1 VARCHAR(2) DEFAULT 'pt',
    iso_3166_1 VARCHAR(2) DEFAULT 'BR',
    name VARCHAR(255) NOT NULL,
    video_key VARCHAR(80) NOT NULL,
    site VARCHAR(255) DEFAULT 'YouTube',
    size INT DEFAULT 1080,
    type VARCHAR(255),
    official BOOLEAN DEFAULT false,
    published_at VARCHAR(255),
    tmdb_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);