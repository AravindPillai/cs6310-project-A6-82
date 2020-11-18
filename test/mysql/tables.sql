-- create table demographicgroup (
-- id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
-- ,shortName VARCHAR(100) NOT NULL
-- ,longName VARCHAR(100) NOT NULL
-- ,numberOfAccounts INT NOT NULL);

-- create table event (
-- id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
-- ,name VARCHAR(100) NOT NULL
-- ,eventtype VARCHAR(100) NOT NULL
--      ,studioshortname VARCHAR(100) NOT NULL
--  ,duration INT NOT NULL
--      ,year INT NOT NULL
--      ,eventlicensingfee INT NOT NULL);


--  create table account (
--     id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--     ,name VARCHAR(100) NOT NULL);

-- create table studio (
--                           id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--      ,shortname VARCHAR(100) NOT NULL
--      ,longname VARCHAR(100) NOT NULL);

-- create table streamingservice (
--                           id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--    ,shortname VARCHAR(100) NOT NULL
--     ,longname VARCHAR(100) NOT NULL
--                               ,subscriptionprice INT NOT NULL);

-- id/createdAt/currentMonthYear/updated_at/buyer/eventDuration/eventName/eventType/eventYear/percentage/ppvCost/transactionCost/transactionType/vendor
-- create table transaction (
--     id INT AUTO_INCREMENT PRIMARY KEY
--     ,createdAt DATETIME NULL
--     ,currentMonthYear VARCHAR(100) NULL
--     ,updatedAt DATETIME NULL
--     ,transactionType VARCHAR(100) NULL
--     ,buyer VARCHAR(100) NULL
--     ,vendor VARCHAR(100) NULL
--     ,eventType VARCHAR(100) NULL
--     ,eventName VARCHAR(100) NULL
--     ,eventYear INT NULL
--     ,ppvCost INT NULL
--     ,percentage INT NULL
--     ,eventduration INT NULL
--     ,transactionCost INT NULL);
