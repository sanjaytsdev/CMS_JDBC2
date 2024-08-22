CREATE CONTACTGROUP TABLE
---------------------------
CREATE TABLE contactgrp (grpid SERIAL PRIMARY KEY, grpname TEXT NOT NULL, grpdesc TEXT);

CREATE CONTACT TABLE
--------------------------
CREATE TABLE contacts (contactid SERIAL PRIMARY KEY, contactgrpid INTEGER REFERENCES contactgrp(grpid) ON DELETE SET NULL, contactname TEXT NOT NULL, contactphone TEXT NOT NULL, contactaddress TEXT, contactpin TEXT);
