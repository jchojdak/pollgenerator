--liquibase formatted sql
--changeset jchojdak:1
CREATE TABLE polls (
  id BINARY(16) PRIMARY KEY NOT NULL,
  created_at DATETIME(6) DEFAULT NULL,
  description VARCHAR(255) DEFAULT NULL,
  title VARCHAR(255) DEFAULT NULL,
  type ENUM('SINGLE_CHOICE','MULTIPLE_CHOICE') DEFAULT NULL
);