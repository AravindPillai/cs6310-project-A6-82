-- Set DB if needed
USE db;

-- Studio inserts

INSERT INTO Studio (shortName, longName) VALUES ('warner','Warner Brothers');
INSERT INTO Studio (shortName, longName) VALUES ('columbia','Columbia Pictures');
INSERT INTO Studio (shortName, longName) VALUES ('20cent','20th Century Fox');
INSERT INTO Studio (shortName, longName) VALUES ('espn','Entertainment Sports Network');


-- Event inserts
-- Movie type

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Batman Begins',2005,140,'warner',1000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Tenet',2020,150,'warner',4000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Wonder Woman',2017,114,'warner',2000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Bad Boys for Life',2020,124,'columbia',1000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Hollow Man',2000,112,'columbia',1000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','The Grudge',2004,91,'columbia',1000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Spy',2015,120,'20cent',2000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','The New Mutants',2020,94,'20cent',3000);

-- PPV type

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('ppv','Justice League Live',2020,180,'warner',12000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('ppv','Hamilton Live',2020,150,'20cent',8000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('ppv','World Blitz Championship',2020,180,'espn',3000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
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

INSERT INTO DemographicGroup (shortName, longName) VALUES ('age_40_50','Viewers between 40 and 50');

-- Create accounts 10 for testing
INSERT INTO Account (id, name) VALUES (1,'age_40_50');
INSERT INTO Account (id, name) VALUES (2,'age_40_50');
INSERT INTO Account (id, name) VALUES (3,'age_40_50');
INSERT INTO Account (id, name) VALUES (4,'age_40_50');
INSERT INTO Account (id, name) VALUES (5,'age_40_50');
INSERT INTO Account (id, name) VALUES (6,'age_40_50');
INSERT INTO Account (id, name) VALUES (7,'age_40_50');
INSERT INTO Account (id, name) VALUES (8,'age_40_50');
INSERT INTO Account (id, name) VALUES (9,'age_40_50');
INSERT INTO Account (id, name) VALUES (10,'age_40_50');

INSERT INTO DemographicGroup (shortName, longName) VALUES ('age_20_heroes','Viewers of Marvel/DC under 20');

-- Create accounts 10 for testing
INSERT INTO Account (id, name) VALUES (11,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (12,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (13,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (14,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (15,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (16,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (17,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (18,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (19,'age_20_heroes');
INSERT INTO Account (id, name) VALUES (20,'age_20_heroes');

INSERT INTO DemographicGroup (shortName, longName) VALUES ('sci_fi','Viewers of Science Fiction');

-- Create accounts 10 for testing
INSERT INTO Account (id, name) VALUES (21,'sci_fi');
INSERT INTO Account (id, name) VALUES (22,'sci_fi');
INSERT INTO Account (id, name) VALUES (23,'sci_fi');
INSERT INTO Account (id, name) VALUES (24,'sci_fi');
INSERT INTO Account (id, name) VALUES (25,'sci_fi');
INSERT INTO Account (id, name) VALUES (26,'sci_fi');
INSERT INTO Account (id, name) VALUES (27,'sci_fi');
INSERT INTO Account (id, name) VALUES (28,'sci_fi');
INSERT INTO Account (id, name) VALUES (29,'sci_fi');
INSERT INTO Account (id, name) VALUES (30,'sci_fi');


-- Watch event inserts

-- watch_event,age_40_50,40,net,Spy,2015
-- watch_event,age_40_50,70,net,Tenet,2020
-- watch_event,age_20_heroes,30,hulu,Batman Begins,2005
-- watch_event,age_20_heroes,60,hulu,Batman v Superman,2016
-- watch_event,age_20_heroes,50,apv,The New Mutants,2020
-- watch_event,sci_fi,30,net,Hollow Man,2000
-- watch_event,sci_fi,30,apv,The Grudge,2004

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

-- Watch event inserts

-- watch_event,age_40_50,70,net,Tenet,2020
-- watch_event,age_40_50,20,net,World Blitz Championship,2020
-- watch_event,age_20_heroes,60,hulu,Batman v Superman,2016
-- watch_event,age_20_heroes,50,apv,The New Mutants,2020
-- watch_event,age_20_heroes,40,hulu,Wonder Woman,2017
-- watch_event,age_20_heroes,40,hulu,Justice League Live,2020
-- watch_event,sci_fi,30,apv,The Grudge,2004
-- watch_event,sci_fi,30,hulu,The New Mutants,2020


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

-- Watch event inserts

-- watch_event,age_40_50,20,net,World Blitz Championship,2020
-- watch_event,age_40_50,50,hulu,Hamilton Live,2020
-- watch_event,age_20_heroes,40,hulu,Wonder Woman,2017
-- watch_event,age_20_heroes,40,hulu,Justice League Live,2020
-- watch_event,age_20_heroes,40,hulu,EuroCup Football,2020
-- watch_event,sci_fi,30,hulu,The New Mutants,2020
-- watch_event,sci_fi,30,net,Tenet,2020




