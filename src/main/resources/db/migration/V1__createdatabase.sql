DROP TABLE IF EXISTS anime CASCADE;

CREATE TABLE anime (
    animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year int,
    image text);

CREATE TABLE file (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype TEXT,
    data bytea);

CREATE TABLE usser (
  userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
  username varchar(24) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  role varchar(10),
  enabled boolean DEFAULT true
);

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE author (
    authorid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    image text);

CREATE TABLE anime_author (
    animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    authorid uuid REFERENCES author(authorid) ON DELETE CASCADE,
    PRIMARY KEY(animeid,authorid));

CREATE TABLE genre (
    genreid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    label text,
    image text);

CREATE TABLE anime_genre (
    animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    genreid uuid REFERENCES genre(genreid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, genreid));

CREATE TABLE favorite (
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    PRIMARY KEY (userid, animeid));

CREATE TABLE block_anime(
     userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
     animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
     PRIMARY KEY (userid, animeid));

CREATE TABLE follow_user(
    followuser uuid REFERENCES usser(userid) ON DELETE CASCADE,
    follower uuid REFERENCES usser(userid) ON DELETE CASCADE,
    PRIMARY KEY(followuser,follower));

CREATE TABLE images(
    animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    imageid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    imageurl text);



