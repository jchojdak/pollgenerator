--liquibase formatted sql
--changeset jchojdak:4
SET @poll_id = UNHEX(REPLACE(UUID(), '-', ''));

INSERT INTO polls (id, created_at, description, title, type)
VALUES (
  @poll_id,
  NOW(),
  'Description of example single choice poll',
  'Example poll',
  'SINGLE_CHOICE'
);

INSERT INTO poll_options (id, value, poll_id)
VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), 'First option', @poll_id),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Second option', @poll_id),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Third option', @poll_id),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Fourth option', @poll_id);


SET @poll_id = UNHEX(REPLACE(UUID(), '-', ''));

INSERT INTO polls (id, created_at, description, title, type)
VALUES (
  @poll_id,
  NOW(),
  'Description of example multiple choice poll',
  'Example poll',
  'MULTIPLE_CHOICE'
);

INSERT INTO poll_options (id, value, poll_id)
VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), 'First option', @poll_id),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Second option', @poll_id),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Third option', @poll_id),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Fourth option', @poll_id);