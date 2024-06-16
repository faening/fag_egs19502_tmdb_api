CREATE TABLE IF NOT EXISTS movie_crew (
    movie_id BIGINT NOT NULL,
    crew_id BIGINT NOT NULL,
    PRIMARY KEY (movie_id, crew_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (crew_id) REFERENCES crew(id)
);