DELETE FROM USERS;

ALTER SEQUENCE USERS_USER_ID_SEQ RESTART WITH 1;

INSERT INTO USERS (USERNAME, EMAIL, PASSWORD)
VALUES ('eren', 'eren@email.com', 'eren123'),
('jack', 'jack@email.com', 'jack123'),
('zoe', 'zoe@email.com', 'zoe123');