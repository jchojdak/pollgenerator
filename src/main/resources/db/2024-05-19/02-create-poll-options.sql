--liquibase formatted sql
--changeset jchojdak:2
CREATE TABLE poll_options (
  id BINARY(16) PRIMARY KEY NOT NULL,
  value VARCHAR(255) DEFAULT NULL,
  poll_id BINARY(16),
  FOREIGN KEY (poll_id) REFERENCES polls(id)
);