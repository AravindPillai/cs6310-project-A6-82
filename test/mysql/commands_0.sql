-- create table demographicgroup (
-- id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
-- ,shortName VARCHAR(100) NOT NULL
-- ,longName VARCHAR(100) NOT NULL
-- ,numberOfAccounts INT NOT NULL);

-- create table event (
-- id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
-- ,name VARCHAR(100) NOT NULL
-- ,eventtype VARCHAR(100) NOT NULL
--      ,studioshortname VARCHAR(100) NOT NULL
--  ,duration INT NOT NULL
--      ,year INT NOT NULL
--      ,eventlicensingfee INT NOT NULL);


--  create table account (
--     id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--     ,name VARCHAR(100) NOT NULL);

-- create table studio (
--                           id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--      ,shortname VARCHAR(100) NOT NULL
--      ,longname VARCHAR(100) NOT NULL);

-- create table streamingservice (
--                           id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--    ,shortname VARCHAR(100) NOT NULL
--     ,longname VARCHAR(100) NOT NULL
--                               ,subscriptionprice INT NOT NULL);

-- id/createdAt/currentMonthYear/updated_at/buyer/eventDuration/eventName/eventType/eventYear/percentage/ppvCost/transactionCost/transactionType/vendor
-- create table transaction (
--     id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--     ,currentMonthYear VARCHAR(100) NULL
--     ,updatedAt DATETIME NULL
--     ,transactionType VARCHAR(100) NULL
--     ,buyer VARCHAR(100) NULL
--     ,vendor VARCHAR(100) NULL
--     ,eventType VARCHAR(100) NULL
--     ,eventName VARCHAR(100) NULL
--     ,eventYear INT NULL
--     ,ppvCost INT NULL
--     ,percentage INT NULL
--     ,eventduration INT NULL
--     ,transactionCost INT NULL);

-- Demo inserts
INSERT INTO demographicgroup (createdAt,shortName, longName, numberofaccounts,isArchived)
VALUES ('2020-11-17 04:00:00','age_40_50','Viewers between 40 and 50', 10,0);

-- Create accounts
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_40_50');

INSERT INTO demographicgroup (createdAt,shortname, longname,numberOfAccounts,isArchived)
VALUES ('2020-11-17 04:00:00','age_20_anime','Viewers under 20 who watch anime weekly',20,0);

-- Create accounts
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_anime');

-- Studio inserts
INSERT INTO studio (createdAt,shortname, longname)
VALUES ('2020-11-17 04:00:00','disney','Walt Disney Animation Studios');

INSERT INTO studio (createdAt,shortname, longname)
VALUES ('2020-11-17 04:00:00','espn','Entertainment Sports Network Studios');

-- Event inserts
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Mulan',1998,88,'disney',1000);

INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','ppv','30 for 30: Monaco',2020,106,'espn',3300);

INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Beauty and the Beast',1991,84,'disney',1000);

INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','ppv','MMA Championship',2020,121,'espn',8800);

-- Stream inserts
INSERT INTO streamingservice (createdAt,shortname, longname, subscriptionprice)
VALUES ('2020-11-17 04:00:00','apv','Amazon Prime Video',12);

INSERT INTO streamingservice (createdAt,shortname, longname, subscriptionprice)
VALUES ('2020-11-17 04:00:00','net','Netflix',14);

-- Offer movie inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Mulan',1998,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',50,'movie','Beauty and the Beast',1991,'offer',0,'apv',0,2000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',40,'movie','The Little Mermaid',1989,'offer',0,'apv',0,1500);

-- Offer ppv inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',20,'ppv','30 for 30: Monaco',2020,'offer',0,'net',1000,0);

-- Transaction inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'stream','Mulan',1998,'watch',0,'apv',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',50,'stream','Beauty and the Beast',1991,'watch',0,'apv',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',40,'stream','The Little Mermaid',1989,'watch',0,'apv',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',20,'stream','30 for 30: Monaco',2020,'watch',0,'net',0,0);



