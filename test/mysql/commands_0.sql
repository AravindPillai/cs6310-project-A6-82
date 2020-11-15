-- Demo inserts

INSERT INTO DemographicGroup (shortName, longName) VALUES ('age_40_50','Viewers between 40 and 50',800);
INSERT INTO DemographicGroup (shortName, longName) VALUES ('age_20_anime','Viewers under 20 who watch anime weekly',2000);

-- Studio inserts

INSERT INTO Studio (shortName, longName) VALUES ('disney','Walt Disney Animation Studios');
INSERT INTO Studio (shortName, longName) VALUES ('espn','Entertainment Sports Network Studios');

-- Event inserts
-- Movie type

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Mulan',1998,88,'disney',1000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('ppv','30 for 30: Monaco',2020,106,'espn',3300);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Beauty and the Beast',1991,84,'disney',1000);

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('ppv','MMA Championship',2020,121,'espn',8800);

--create_stream,apv,Amazon Prime Video,12
--create_stream,net,Netflix,14

--offer_movie,apv,Mulan,1998
--offer_movie,apv,The Little Mermaid,1989
--offer_movie,apv,Beauty and the Beast,1991
--offer_ppv,net,30 for 30: Monaco,2020,57

--watch_event,age_40_50,30,apv,Mulan,1998
--watch_event,age_40_50,50,apv,Beauty and the Beast,1991
--watch_event,age_40_50,40,apv,The Little Mermaid,1989
--watch_event,age_40_50,20,net,30 for 30: Monaco,2020
