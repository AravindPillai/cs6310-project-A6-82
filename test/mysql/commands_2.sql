-- Studio inserts

INSERT INTO Studio (shortName, longName) VALUES ('warner','Warner Brothers');

-- Event inserts
-- Movie type

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Batman v Superman',2016,152,'warner',1000);

-- Stream inserts

--INSERT INTO StreamingServices (shortName, longName, streamFee) VALUES ('hulu','Hulu Plus',11);

-- Offer Movie 

--offer_movie,hulu,Batman v Superman,2016

-- Demo inserts

INSERT INTO DemographicGroup (shortName, longName) VALUES ('age_20_heroes','Viewers of Marvel/DC under 20',10000);

-- Watch Event

--watch_event,age_20_heroes,60,hulu,Batman v Superman,2016

-- Offer Movie 

--offer_movie,hulu,Batman v Superman,2016

-- Watch Event

--watch_event,age_20_heroes,30,hulu,Batman v Superman,2016

