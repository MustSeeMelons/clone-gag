
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

-- Setup accounts
INSERT INTO gag.USERS(user_name, password, enabled) VALUES('admin', 'admin', TRUE);
INSERT INTO gag.USERS(user_name, password, enabled) VALUES('test', 'test', TRUE);

INSERT INTO gag.USER_ROLES(user_name, role) VALUES('admin', 'ROLE_USER');
INSERT INTO gag.USER_ROLES(user_name, role) VALUES('test', 'ROLE_TEST');

CREATE TABLE gag.POSTS(
    id SERIAL PRIMARY KEY,
    owner bigint REFERENCES gag.USERS(id),
    title varchar(30) NOT NULL,
    image bytea NOT NULL,
    tags text,
    points bigint NOT NULL,
    c_date TIMESTAMP NOT NULL,
    m_date TIMESTAMP
);

CREATE TABLE gag.POST_VOTES(
    id SERIAL PRIMARY KEY,
    owner bigint REFERENCES gag.USERS(id),
    post_id bigint REFERENCES gag.POSTS(id),
    point SMALLINT NOT NULL
);

CREATE TABLE gag.COMMENTS(
    id SERIAL PRIMARY KEY,
    owner bigint REFERENCES gag.USERS(id) NOT NULL,
    post_id bigint REFERENCES gag.POSTS(id),
    parent_comment_id bigint REFERENCES gag.COMMENTS(id),
    value text NOT NULL,
    points bigint NOT NULL,
    c_date TIMESTAMP NOT NULL
);

CREATE TABLE gag.COMMENT_VOTES(
    id SERIAL PRIMARY KEY,
    owner bigint REFERENCES gag.USERS(id),
    comment_id bigint REFERENCES gag.COMMENTS(id),
    point SMALLINT NOT NULL
);

-- Permisions to schema and tables
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA gag TO strautins;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA gag TO strautins;