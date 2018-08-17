
CREATE DATABASE clone_gag;
CREATE USER strautins WITH ENCRYPTED PASSWORD 'PieciKabaci1';
GRANT ALL PRIVILEGES ON DATABASE clone_gag TO strautins;

\connect clone_gag;
CREATE SCHEMA gag;
CREATE TABLE gag.test(id int);