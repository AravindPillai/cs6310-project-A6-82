-- create table demographicgroup (
-- id INT AUTO_INCREMENT PRIMARY KEY
-- ,shortName VARCHAR(100) NOT NULL
-- ,longName VARCHAR(100) NOT NULL
-- ,numberOfAccounts INT NOT NULL);

-- create table event (
-- id INT AUTO_INCREMENT PRIMARY KEY
-- ,name VARCHAR(100) NOT NULL
-- ,eventtype VARCHAR(100) NOT NULL
--      ,studioshortname VARCHAR(100) NOT NULL
--  ,duration INT NOT NULL
--      ,year INT NOT NULL
--      ,eventlicensingfee INT NOT NULL);


--  create table account (
--     id INT AUTO_INCREMENT PRIMARY KEY
--     ,name VARCHAR(100) NOT NULL);

-- create table studio (
--                           id INT AUTO_INCREMENT PRIMARY KEY
--      ,shortname VARCHAR(100) NOT NULL
--      ,longname VARCHAR(100) NOT NULL);

-- create table streamingservice (
--                           id INT AUTO_INCREMENT PRIMARY KEY
--    ,shortname VARCHAR(100) NOT NULL
--     ,longname VARCHAR(100) NOT NULL
--                               ,subscriptionprice INT NOT NULL);

-- create table transaction (
--                                   id INT AUTO_INCREMENT PRIMARY KEY
--     ,transactiontype VARCHAR(100) NULL
--     ,buyer VARCHAR(100) NULL
--     ,vendor VARCHAR(100) NULL
--     ,eventtype VARCHAR(100) NULL
--     ,eventname VARCHAR(100) NULL
--     ,eventyear INT NULL
--     ,ppvcost INT NULL
--     ,percentage INT NULL
--     ,eventduration INT NULL
--     ,transactioncost INT NULL);

-- Demo inserts

INSERT INTO demographicgroup (shortName, longName, numberofaccounts) VALUES ('age_40_50','Viewers between 40 and 50', 10);

-- Create accounts
INSERT INTO account (id, name) VALUES (1,'age_40_50');
INSERT INTO account (id, name) VALUES (2,'age_40_50');
INSERT INTO account (id, name) VALUES (3,'age_40_50');
INSERT INTO account (id, name) VALUES (4,'age_40_50');
INSERT INTO account (id, name) VALUES (5,'age_40_50');
INSERT INTO account (id, name) VALUES (6,'age_40_50');
INSERT INTO account (id, name) VALUES (7,'age_40_50');
INSERT INTO account (id, name) VALUES (8,'age_40_50');
INSERT INTO account (id, name) VALUES (9,'age_40_50');
INSERT INTO account (id, name) VALUES (10,'age_40_50');


INSERT INTO demographicgroup (shortname, longname,numberOfAccounts) VALUES ('age_20_anime','Viewers under 20 who watch anime weekly',20);

-- Create accounts
INSERT INTO account (id, name) VALUES (11,'age_20_anime');
INSERT INTO account (id, name) VALUES (12,'age_20_anime');
INSERT INTO account (id, name) VALUES (13,'age_20_anime');
INSERT INTO account (id, name) VALUES (14,'age_20_anime');
INSERT INTO account (id, name) VALUES (15,'age_20_anime');
INSERT INTO account (id, name) VALUES (16,'age_20_anime');
INSERT INTO account (id, name) VALUES (17,'age_20_anime');
INSERT INTO account (id, name) VALUES (18,'age_20_anime');
INSERT INTO account (id, name) VALUES (19,'age_20_anime');
INSERT INTO account (id, name) VALUES (20,'age_20_anime');
INSERT INTO account (id, name) VALUES (21,'age_20_anime');
INSERT INTO account (id, name) VALUES (22,'age_20_anime');
INSERT INTO account (id, name) VALUES (23,'age_20_anime');
INSERT INTO account (id, name) VALUES (24,'age_20_anime');
INSERT INTO account (id, name) VALUES (25,'age_20_anime');
INSERT INTO account (id, name) VALUES (26,'age_20_anime');
INSERT INTO account (id, name) VALUES (27,'age_20_anime');
INSERT INTO account (id, name) VALUES (28,'age_20_anime');
INSERT INTO account (id, name) VALUES (29,'age_20_anime');
INSERT INTO account (id, name) VALUES (30,'age_20_anime');

-- Studio inserts

INSERT INTO studio (shortname, longname) VALUES ('disney','Walt Disney Animation Studios');
INSERT INTO studio (shortname, longname) VALUES ('espn','Entertainment Sports Network Studios');

-- Event inserts
-- Movie type

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Mulan',1998,88,'disney',1000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('ppv','30 for 30: Monaco',2020,106,'espn',3300);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Beauty and the Beast',1991,84,'disney',1000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('ppv','MMA Championship',2020,121,'espn',8800);

-- Stream inserts

INSERT INTO streamingservice (shortname, longname, subscriptionprice) VALUES ('apv','Amazon Prime Video',12);
INSERT INTO streamingservice (shortname, longname, subscriptionprice) VALUES ('net','Netflix',14);

-- offer_movie,apv,Mulan,1998
-- offer_movie,apv,The Little Mermaid,1989
-- offer_movie,apv,Beauty and the Beast,1991
-- offer_ppv,net,30 for 30: Monaco,2020,57


-- Transaction inserts
INSERT INTO transaction (buyer, percentage, eventtype ,eventname,eventyear) VALUES ('age_40_50',30,'apv','Mulan',1998);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_40_50',50,'apv','Beauty and the Beast',1991);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_40_50',40,'apv','The Little Mermaid',1989);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_40_50',20,'net','30 for 30: Monaco',2020);

