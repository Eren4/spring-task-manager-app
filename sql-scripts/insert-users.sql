DELETE FROM USERS;

ALTER SEQUENCE USERS_USER_ID_SEQ RESTART WITH 1;

INSERT INTO USERS (USERNAME, EMAIL, PASSWORD)
VALUES ('eren', 'eren@email.com', '$2a$10$hLv6pj/REtt0bF.2sHS0aeMiY5PBdoq4.lscqSMuaoN38m8O5u1cq'),
('jack', 'jack@email.com', '$2a$10$1SD4hbRTlKarCOgY4Iqdk.srAcLy1O/mn.TAOQnUVKAusOz8HghL.'),
('zoe', 'zoe@email.com', '$2a$10$CxXJFobb.VNFDWkgXbfA2OMwe4Ze4CItTGybOOVY18eya81VVwyMy');