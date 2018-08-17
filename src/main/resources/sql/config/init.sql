
CREATE DATABASE clone_gag;
CREATE USER strautins WITH ENCRYPTED PASSWORD 'PieciKabaci1';
GRANT ALL PRIVILEGES ON DATABASE clone_gag TO strautins;

\connect clone_gag;
CREATE SCHEMA gag;
CREATE TABLE gag.APP_USER(
    id bigint SERIAL PRIMARY KEY,
    sso_id varchar(30),
    password varchar(30),
    user_name varchar(30)
);

CREATE TABLE gag.USER_PROFILE(
    id bigint SERIAL PRIMARY KEY,
    type varchar(30) NOT NULL UNIQUE
);

CREATE TABLE gag.APP_USER_PROFILE (
    user_id bigint REFERENCES gag.APP_USER(id),
    user_proifle_id bigint REFERENCES gag.USER_PROFILE(id)
);