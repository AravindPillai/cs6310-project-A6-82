-- Studio inserts
INSERT INTO studio (shortname, longname) VALUES ('warner','Warner Brothers');

-- Event inserts
-- Movie type
INSERT INTO event (eventtype,name,year,duration,studioshortname,eventlicensingfee)
VALUES ('movie','Batman v Superman',2016,152,'warner',1000);

-- Stream inserts
INSERT INTO streamingservice (shortname, longname, subscriptionprice) VALUES ('hulu','Hulu Plus',11);

-- Offer Movie 
-- offer_movie,hulu,Batman v Superman,2016

-- Demo inserts
INSERT INTO DemographicGroup (shortName, longName) VALUES ('age_20_heroes','Viewers of Marvel/DC under 20',10);

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

-- Transaction inserts
INSERT INTO transaction (buyer, percentage, eventtype ,eventname,eventyear) VALUES ('age_20_heroes',60,'hulu','Batman v Superman',2016);

-- Offer Movie 
-- offer_movie,hulu,Batman v Superman,2016

-- Transaction inserts
INSERT INTO transaction (buyer, percentage, eventtype ,eventname,eventyear) VALUES ('age_20_heroes',30,'hulu','Batman v Superman',2016);
