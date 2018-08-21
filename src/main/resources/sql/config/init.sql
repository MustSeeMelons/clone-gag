
CREATE DATABASE clone_gag;
CREATE USER strautins WITH ENCRYPTED PASSWORD 'PieciKabaci1';
GRANT ALL PRIVILEGES ON DATABASE clone_gag TO strautins;

\connect clone_gag;

CREATE SCHEMA gag;
GRANT ALL PRIVILEGES ON SCHEMA gag TO strautins;

CREATE TABLE gag.USERS(
    id SERIAL PRIMARY KEY,
    user_name varchar(30) UNIQUE,
    password varchar(30),
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE gag.USER_ROLES(
    id SERIAL PRIMARY KEY,
    user_name varchar(30) REFERENCES gag.USERS(user_name) NOT NULL,
    role varchar(30) NOT NULL,
    UNIQUE(user_name, role)
);

-- Setup admin account
INSERT INTO gag.USERS(user_name, password, enabled) VALUES('admin', 'admin', TRUE);
INSERT INTO gag.USER_ROLES(user_name, role) VALUES('admin', 'ROLE_USER');

CREATE TABLE gag.POSTS(
    id SERIAL PRIMARY KEY,
    owner bigint REFERENCES gag.USERS(id),
    title varchar(30) NOT NULL,
    image bytea NOT NULL,
    tags text,
    c_date TIMESTAMP NOT NULL,
    m_date TIMESTAMP
);

-- Permisions to schema and tables
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA gag TO strautins;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA gag TO strautins;