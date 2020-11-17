-- Studio inserts
INSERT INTO studio (shortname, longname) VALUES ('warner','Warner Brothers');
INSERT INTO studio (shortname, longname) VALUES ('columbia','Columbia Pictures');
INSERT INTO studio (shortname, longname) VALUES ('20cent','20th Century Fox');
INSERT INTO studio (shortname, longname) VALUES ('espn','Entertainment Sports Network');

-- Event inserts
-- Movie type
INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Batman Begins',2005,140,'warner',1000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Tenet',2020,150,'warner',4000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Wonder Woman',2017,114,'warner',2000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Bad Boys for Life',2020,124,'columbia',1000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Hollow Man',2000,112,'columbia',1000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','The Grudge',2004,91,'columbia',1000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Spy',2015,120,'20cent',2000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','The New Mutants',2020,94,'20cent',3000);

-- PPV type

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('ppv','Justice League Live',2020,180,'warner',12000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('ppv','Hamilton Live',2020,150,'20cent',8000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('ppv','World Blitz Championship',2020,180,'espn',3000);

INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('ppv','EuroCup Football',2020,120,'espn',5000);

-- Offer Movie inserts

-- offer_movie,net,Hollow Man,2000
-- offer_movie,net,Spy,2015
-- offer_movie,net,Tenet,2020
-- offer_movie,net,The Grudge,2004
-- offer_movie,apv,Bad Boys for Life,2020
-- offer_movie,apv,Hollow Man,2000
-- offer_movie,apv,The Grudge,2004
-- offer_movie,apv,Spy,2015
-- offer_movie,apv,The New Mutants,2020
-- offer_movie,hulu,Batman Begins,2005
-- offer_movie,hulu,Batman v Superman,2016
-- offer_movie,hulu,The New Mutants,2020
-- offer_movie,hulu,Wonder Woman,2017
-- offer_ppv,net,World Blitz Championship,2020,30
-- offer_ppv,apv,World Blitz Championship,2020,40
-- offer_ppv,apv,EuroCup Football,2020,60
-- offer_ppv,hulu,Hamilton Live,2020,100
-- offer_ppv,hulu,EuroCup Football,2020,30
-- offer_ppv,hulu,Justice League Live,2020,80

-- Demo inserts
INSERT INTO demographicgroup (shortName, longName, numberofaccounts) VALUES ('age_40_50','Viewers between 40 and 50', 10);

-- Create accounts
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');
INSERT INTO account (name) VALUES ('age_40_50');

INSERT INTO demographicgroup (shortname, longname,numberOfAccounts) VALUES ('age_20_heroes','Viewers of Marvel/DC under 20',20);

-- Create accounts
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');
INSERT INTO account (name) VALUES ('age_20_heroes');

INSERT INTO demographicgroup (shortname, longname,numberOfAccounts) VALUES ('sci_fi','Viewers of Science Fiction',5);

-- Create accounts
INSERT INTO account (name) VALUES ('sci_fi');
INSERT INTO account (name) VALUES ('sci_fi');
INSERT INTO account (name) VALUES ('sci_fi');
INSERT INTO account (name) VALUES ('sci_fi');
INSERT INTO account (name) VALUES ('sci_fi');

-- Transaction inserts
INSERT INTO transaction (buyer, percentage, eventtype ,eventname,eventyear) VALUES ('age_40_50',40,'net','Spy',2015);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',30,'hulu','Batman Begins',2005);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_40_50',70,'net','Tenet',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',60,'hulu','Batman v Superman',2016);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',50,'apv','The New Mutants',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('sci_fi',30,'net','Hollow Man',2000);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('sci_fi',30,'apv','The Grudge',2004);



-- Offer movie inserts

-- offer_movie,net,Hollow Man,2000
-- offer_movie,net,Spy,2015
-- offer_movie,net,Tenet,2020
-- offer_movie,net,The Grudge,2004
-- offer_movie,apv,Bad Boys for Life,2020
-- offer_movie,apv,Hollow Man,2000
-- offer_movie,apv,The Grudge,2004
-- offer_movie,apv,Spy,2015
-- offer_movie,apv,The New Mutants,2020
-- offer_movie,hulu,Batman Begins,2005
-- offer_movie,hulu,Batman v Superman,2016
-- offer_movie,hulu,The New Mutants,2020
-- offer_movie,hulu,Wonder Woman,2017
-- offer_ppv,net,World Blitz Championship,2020,30
-- offer_ppv,apv,World Blitz Championship,2020,40
-- offer_ppv,apv,EuroCup Football,2020,60
-- offer_ppv,hulu,Hamilton Live,2020,100
-- offer_ppv,hulu,EuroCup Football,2020,30
-- offer_ppv,hulu,Justice League Live,2020,80

-- Transaction inserts
INSERT INTO transaction (buyer, percentage, eventtype ,eventname,eventyear) VALUES ('age_40_50',70,'net','Tenet',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_40_50',20,'net','World Blitz Championship',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',60,'hulu','Batman v Superman',2016);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',50,'apv','The New Mutants',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',40,'hulu','Wonder Woman',2017);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',40,'hulu','Justice League Live',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('sci_fi',30,'apv','The Grudge',2004);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('sci_fi',30,'hulu','The New Mutants',2020);


-- Offer movie inserts

-- offer_movie,net,Hollow Man,2000
-- offer_movie,net,Spy,2015
-- offer_movie,net,Tenet,2020
-- offer_movie,net,The Grudge,2004
-- offer_movie,apv,Bad Boys for Life,2020
-- offer_movie,apv,Hollow Man,2000
-- offer_movie,apv,The Grudge,2004
-- offer_movie,apv,Spy,2015
-- offer_movie,apv,The New Mutants,2020
-- offer_movie,hulu,Batman Begins,2005
-- offer_movie,hulu,Batman v Superman,2016
-- offer_movie,hulu,The New Mutants,2020
-- offer_movie,hulu,Wonder Woman,2017
-- offer_ppv,net,World Blitz Championship,2020,30
-- offer_ppv,apv,World Blitz Championship,2020,40
-- offer_ppv,apv,EuroCup Football,2020,60
-- offer_ppv,hulu,Hamilton Live,2020,100
-- offer_ppv,hulu,EuroCup Football,2020,30
-- offer_ppv,hulu,Justice League Live,2020,80

-- Transaction inserts
INSERT INTO transaction (buyer, percentage, eventtype ,eventname,eventyear) VALUES ('age_40_50',20,'net','World Blitz Championship',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_40_50',50,'hulu','Hamilton Live',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',40,'hulu','Wonder Woman',2017);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',40,'hulu','Justice League Live',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('age_20_heroes',40,'hulu','EuroCup Football',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('sci_fi',30,'hulu','The New Mutants',2020);
INSERT INTO transaction (buyer, percentage, eventtype,eventname,eventyear) VALUES ('sci_fi',30,'net','Tenet',2020);


