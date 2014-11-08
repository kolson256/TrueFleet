CREATE TABLE organization (
    "name" text,
    tenantid int DEFAULT RANDOM_UUID() NOT NULL,
    databaseurl text,
    apiversion text
);
CREATE TABLE userlogin (
    username text NOT NULL,
    password text NOT NULL,
    organizationid text NOT NULL
);
