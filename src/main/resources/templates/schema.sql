CREATE TABLE people (
    id   INTEGER      NOT NULL,
    name VARCHAR(255) NOT NULL,
    birth_year VARCHAR(255) NOT NULL,
    eye_color VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    hair_color VARCHAR(255) NOT NULL,
    height VARCHAR(255) NOT NULL,
    home_world VARCHAR(255) NOT NULL,
    mass VARCHAR(255) NOT NULL,
    skin_color VARCHAR(255) NOT NULL,
    created VARCHAR(255) NOT NULL,
    edited VARCHAR(255) NOT NULL,
    url VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE planet (
    id   INTEGER      NOT NULL,
    climate VARCHAR(255) NOT NULL,
    diameter VARCHAR(255) NOT NULL,
    gravity VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    population VARCHAR(255) NOT NULL,
    residents VARCHAR(255) NOT NULL,
    terrain VARCHAR(255) NOT NULL,
    url VARCHAR(255) NULL,
    PRIMARY KEY (id)
);
