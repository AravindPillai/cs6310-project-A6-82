-- Set DB if needed
USE main;

-- Demo inserts

INSERT INTO demographicgroup (shortName, longName, numberofaccounts) VALUES ('age_40_50','Viewers between 40 and 50',10);

-- Create accounts 10 for testing
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


INSERT INTO demographicgroup (shortName, longName, numberofaccounts) VALUES ('age_20_anime','Viewers under 20 who watch anime weekly',20);

-- Create accounts 10 for testing
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

INSERT INTO studio (shortName, longName) VALUES ('disney','Walt Disney Animation Studios');
INSERT INTO studio (shortName, longName) VALUES ('espn','Entertainment Sports Network Studios');

-- Event inserts
-- Movie type

INSERT INTO event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Mulan',1998,88,'disney',1000);

INSERT INTO event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('ppv','30 for 30: Monaco',2020,106,'espn',3300);

INSERT INTO event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Beauty and the Beast',1991,84,'disney',1000);

INSERT INTO event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('ppv','MMA Championship',2020,121,'espn',8800);

-- Stream inserts

--INSERT INTO Studio (shortName, longName, streamFee) VALUES ('apv','Amazon Prime Video',12);
--INSERT INTO Studio (shortName, longName, streamFee) VALUES ('net','Netflix',14);

--offer_movie,apv,Mulan,1998
--offer_movie,apv,The Little Mermaid,1989
--offer_movie,apv,Beauty and the Beast,1991
--offer_ppv,net,30 for 30: Monaco,2020,57

--watch_event,age_40_50,30,apv,Mulan,1998
--watch_event,age_40_50,50,apv,Beauty and the Beast,1991
--watch_event,age_40_50,40,apv,The Little Mermaid,1989
--watch_event,age_40_50,20,net,30 for 30: Monaco,2020
