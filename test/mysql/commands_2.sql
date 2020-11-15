-- Studio inserts

INSERT INTO Studio (shortName, longName) VALUES ('warner','Warner Brothers');

-- Event inserts
-- Movie type

INSERT INTO Event (eventType,name,year,duration,studioShortName,eventLicensingFee) 
VALUES ('movie','Batman v Superman',2016,152,'warner',1000);

--create_stream,hulu,Hulu Plus,11

--offer_movie,hulu,Batman v Superman,2016

-- Demo inserts

INSERT INTO DemographicGroup (shortName, longName) VALUES ('age_20_heroes','Viewers of Marvel/DC under 20',10000);

--watch_event,age_20_heroes,60,hulu,Batman v Superman,2016

--offer_movie,hulu,Batman v Superman,2016

--watch_event,age_20_heroes,30,hulu,Batman v Superman,2016

