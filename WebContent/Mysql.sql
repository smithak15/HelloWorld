CREATE DATABASE HELLOWORLD;

USE HELLOWORLD;

CREATE TABLE USERS(
USERS_ID INT NOT NULL AUTO_INCREMENT,
FNAME VARCHAR(50) NOT NULL,
LNAME VARCHAR(50) NOT NULL,
ADDRESS1 VARCHAR(100) NOT NULL,
ADDRESS2 VARCHAR(100) NOT NULL,
CITY VARCHAR(50) NOT NULL,
STATE VARCHAR(50) NOT NULL,
ZIP INT(9) NOT NULL,
PRIMARY KEY (USERS_ID),
REGISTRATION_DATE DATETIME NOT NULL
);