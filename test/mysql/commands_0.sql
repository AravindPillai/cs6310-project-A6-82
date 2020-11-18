-- Demo inserts
INSERT INTO demographicgroup (id,createdAt,shortName, longName, numberofaccounts,isArchived,description)
VALUES (1,'2020-11-17 04:00:00','age_40_50','Viewers between 40 and 50', 10,0,'age_40_501');

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

INSERT INTO demographicgroup (id,createdAt,shortname, longname,numberOfAccounts,isArchived,description)
VALUES (2,'2020-11-17 04:00:00','age_20_anime','Viewers under 20 who watch anime weekly',20,0,'age_20_anime1');

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



