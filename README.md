# training-ints
study about spring boot
Create data base on Sql serve :
create database company
use company

create table EMPLOYEES
(
	Id int NOT NULL,
	FullName varchar(50) NOT NULL,
	Gender varchar(6) NOT NULL,
	BirthDate DATE NOT NULL,
	DepartmentId int NOT NULL,
	PRIMARY KEY(Id)
)

create table DEPARTMENT
(
	DepartmentId int NOT NULL,
	DepartmentName varchar(30) NOT NULL,
	Duty varchar(70),
	LocationId int NOT NULL,
	PRIMARY KEY(DepartmentId)
)

create table DEPARTMENT_LOCATION
(
	LocationId int NOT NULL,
	Adress varchar(60) NOT NULL,
	City varchar(30),
	Country varchar(30),
	PRIMARY KEY(LocationId)
)

CREATE TABLE refresh_token (
	Id numeric(18, 0) NOT NULL,
	Token nvarchar(256) NULL,
	Expiry_DT date NULL,
	User_Id numeric(18, 0) NOT NULL,
	PRIMARY KEY (Id)
);

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE User_Company (
	user_id numeric(18, 0) NOT NULL,
	full_name nvarchar(50) NULL,
	username nvarchar(10) NOT NULL,
	password nvarchar(256) NULL,
	enabled bit NULL,
    account_non_expired bit NULL,
    credentials_non_expired bit NULL,
    account_non_locked bit NULL,
    PRIMARY KEY (user_id)
);
GO

INSERT INTO User_Company (user_id, full_name, [username], [password], [enabled], account_non_expired, credentials_non_expired, account_non_locked) VALUES (1, 'Admin', 'admin', '$2a$10$HeAsLAsudoGguPoqfzsemONLq4lyKTqHAzEN9Ynvv4Ol2R50UypYy', 1, 1, 1, 1);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
