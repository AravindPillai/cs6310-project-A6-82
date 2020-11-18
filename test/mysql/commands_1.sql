-- Studio inserts
INSERT INTO studio (createdAt,shortname, longname)
VALUES ('2020-11-17 04:00:00','warner','Warner Brothers');

-- Event inserts
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Batman v Superman',2016,152,'warner',1000);

-- Stream inserts
INSERT INTO streamingservice (createdAt,shortname, longname, subscriptionprice)
VALUES ('2020-11-17 04:00:00','hulu','Hulu Plus',11);

-- Offer movie inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',30,'movie','Batman v Superman',2016,'offer',0,'hulu',0,1000);

-- Demo inserts
INSERT INTO demographicgroup (createdAt,shortname, longname,numberOfAccounts,isArchived)
VALUES ('2020-11-17 04:00:00','age_20_heroes','Viewers of Marvel/DC under 20',10,0);

-- Create accounts
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','age_20_heroes');

-- Transaction inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',60,'stream','Batman v Superman',2016,'watch',0,'hulu',0,0);

-- Offer movie inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',20,'movie','Batman v Superman',2016,'offer',0,'hulu',0,1000);

-- Transaction inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',30,'stream','Batman v Superman',2016,'watch',0,'hulu',0,0);

