--liquibase formatted sql
--changeset jchojdak:3
CREATE TABLE poll_votes (
  id BINARY(16) PRIMARY KEY NOT NULL,
  ip_address VARCHAR(255) DEFAULT NULL,
  poll_option_id BINARY(16),
  FOREIGN KEY (poll_option_id) REFERENCES poll_options(id)
);