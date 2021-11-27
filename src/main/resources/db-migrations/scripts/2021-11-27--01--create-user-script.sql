CREATE USER driversandcarsuserusage;
ALTER USER driversandcarsuserusage with PASSWORD '123';
GRANT USAGE ON SCHEMA driversandcarsliqui TO driversandcarsuserusage WITH GRANT OPTION;
GRANT update, select, insert ON driversandcarsliqui.cars to driversandcarsuserusage;
GRANT update, select, insert ON driversandcarsliqui.drivers to driversandcarsuserusage;
GRANT update, select, insert ON driversandcarsliqui.logs to driversandcarsuserusage;