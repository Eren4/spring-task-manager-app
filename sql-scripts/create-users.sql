DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS TASKS;

CREATE TABLE USERS
(
USER_ID SERIAL PRIMARY KEY,
USERNAME VARCHAR(255),
EMAIL VARCHAR(255),
PASSWORD VARCHAR(255)
);

CREATE TABLE TASKS
(
TASK_ID SERIAL PRIMARY KEY,
TASK_DESCRIPTION VARCHAR(255) NOT NULL,
COMPLETED BOOLEAN,
USER_ID INT,
FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);