DROP SCHEMA IF EXISTS UTP10 CASCADE;
CREATE SCHEMA IF NOT EXISTS UTP10;
SET search_path TO UTP10;

DROP SEQUENCE IF EXISTS USERS_SEQ;
DROP SEQUENCE IF EXISTS GROUPS_SEQ;
DROP SEQUENCE IF EXISTS USERS_GROUPS_SEQ;

DROP TABLE IF EXISTS USERS_GROUPS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS GROUPS;

SET search_path TO UTP10;

CREATE TABLE USERS (
	USER_ID INTEGER NOT NULL
	, USER_LOGIN VARCHAR(20) NOT NULL
	, USER_PASSWORD VARCHAR(20) NOT NULL

	, CONSTRAINT USERS_PK PRIMARY KEY(USER_ID) 	
);
CREATE SEQUENCE USERS_SEQ;


CREATE TABLE GROUPS (
	GROUP_ID INTEGER NOT NULL
	, GROUP_NAME VARCHAR(20) NOT NULL
	, GROUP_DESCRIPTION TEXT NOT NULL

	, CONSTRAINT GROUPS_PK PRIMARY KEY(GROUP_ID)
);
CREATE SEQUENCE GROUPS_SEQ;


CREATE TABLE USERS_GROUPS (
	USER_GROUP_ID INTEGER NOT NULL
	, USER_ID INTEGER NOT NULL
	, GROUP_ID INTEGER NOT NULL
	
	, CONSTRAINT USERS_GROUPS_PK PRIMARY KEY(USER_GROUP_ID)
	, CONSTRAINT USERS_GROUPS_USERS_FK FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID)
	, CONSTRAINT USERS_GROUPS_GROUPS_FK FOREIGN KEY(GROUP_ID) REFERENCES GROUPS(GROUP_ID)
);
CREATE SEQUENCE USERS_GROUPS_SEQ;