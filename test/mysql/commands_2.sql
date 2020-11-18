-- Studio inserts
INSERT INTO studio (createdAt,shortname, longname)
VALUES ('2020-11-17 04:00:00','warner','Warner Brothers');
INSERT INTO studio (createdAt,shortname, longname)
VALUES ('2020-11-17 04:00:00','columbia','Columbia Pictures');
INSERT INTO studio (createdAt,shortname, longname)
VALUES ('2020-11-17 04:00:00','20cent','20th Century Fox');
INSERT INTO studio (createdAt,shortname, longname)
VALUES ('2020-11-17 04:00:00','espn','Entertainment Sports Network');

-- Event inserts
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Batman Begins',2005,140,'warner',1000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Tenet',2020,150,'warner',4000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Wonder Woman',2017,114,'warner',2000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Bad Boys for Life',2020,124,'columbia',1000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Hollow Man',2000,112,'columbia',1000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','The Grudge',2004,91,'columbia',1000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','Spy',2015,120,'20cent',2000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','movie','The New Mutants',2020,94,'20cent',3000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','ppv','Justice League Live',2020,180,'warner',12000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','ppv','Hamilton Live',2020,150,'20cent',8000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','ppv','World Blitz Championship',2020,180,'espn',3000);
INSERT INTO event (createdAt,eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('2020-11-17 04:00:00','ppv','EuroCup Football',2020,120,'espn',5000);

-- Offer movie inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Hollow Man',2000,'offer',0,'net',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Spy',2015,'offer',0,'net',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Tenet',2020,'offer',0,'net',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','The Grudge',2004,'offer',0,'net',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Bad Boys for Life',2020,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Hollow Man',2000,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','The Grudge',2004,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Spy',2015,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','The New Mutants',2020,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Batman Begins',2005,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Batman v Superman',2016,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','The New Mutants',2020,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Wonder Woman',2017,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'ppv','EuroCup Football',2020,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',80,'ppv','Justice League Live',2020,'offer',0,'hulu',0,1000);


-- Demo inserts
INSERT INTO demographicgroup (createdAt,shortname, longname,numberOfAccounts,isArchived)
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
VALUES ('2020-11-17 04:00:00','age_20_heroes','Viewers of Marvel/DC under 20',20,0);

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

INSERT INTO demographicgroup (createdAt,shortname, longname,numberOfAccounts,isArchived)
VALUES ('2020-11-17 04:00:00','sci_fi','Viewers of Science Fiction',5,0);

-- Create accounts
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','sci_fi');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','sci_fi');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','sci_fi');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','sci_fi');
INSERT INTO account (createdAt,name) VALUES ('2020-11-17 04:00:00','sci_fi');


-- Transaction inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'stream','Spy',2015,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',50,'stream','Batman Begins',2005,'watch',0,'hulu',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',70,'stream','Tenet',2020,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',20,'stream','30 for 30: Monaco',2020,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','sci_fi',30,'stream','Hollow Man',2000,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','sci_fi',30,'stream','The Grudge',2004,'watch',0,'apv',0,0);

-- Offer movie inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Hollow Man',2000,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','The Grudge',2004,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','The New Mutants',2020,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Wonder Woman',2017,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'ppv','EuroCup Football',2020,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',80,'ppv','Justice League Live',2020,'offer',0,'hulu',0,1000);

-- Transaction inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',70,'stream','Spy',2015,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',20,'stream','Batman Begins',2005,'watch',0,'hulu',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',60,'stream','Tenet',2020,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'stream','30 for 30: Monaco',2020,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','sci_fi',50,'stream','Hollow Man',2000,'watch',0,'net',0,0);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','sci_fi',20,'stream','The Grudge',2004,'watch',0,'apv',0,0);

-- Offer movie inserts
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_40_50',30,'movie','Hollow Man',2000,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',30,'movie','The Grudge',2004,'offer',0,'apv',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','sci_fi',30,'movie','The New Mutants',2020,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','sci_fi',30,'movie','Wonder Woman',2017,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','sci_fi',30,'ppv','EuroCup Football',2020,'offer',0,'hulu',0,1000);
INSERT INTO transaction (createdAt,currentMonthYear,buyer, percentage, eventtype ,eventname,eventyear,transactiontype,ppvcost,vendor,eventduration,transactionCost)
VALUES ('2020-11-17 04:00:00','11-2020','age_20_heroes',80,'ppv','Justice League Live',2020,'offer',0,'hulu',0,1000);



