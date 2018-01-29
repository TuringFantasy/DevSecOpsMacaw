DROP KEYSPACE IF EXISTS imagemanager;
CREATE KEYSPACE imagemanager WITH replication = {'class':'SimpleStrategy','replication_factor' : 1};
USE imagemanager;


CREATE TABLE image (
 id     uuid,
 name     text,
 description     text,
 object     blob,
 url     text,
 createddate     timestamp,
 customerid     text,
 PRIMARY KEY ( (id) )
 );


CREATE TABLE customer (
 customerid     text,
 name     text,
 address     text,
 description     text,
 dob     bigint,
 emailid     text,
 PRIMARY KEY ( (customerid) )
 );


CREATE TABLE imageapproval (
 customerid     text,
 imageid     uuid,
 status     text,
 reason     text,
 updateat     bigint,
 PRIMARY KEY ( customerid,imageid )
 );


CREATE  INDEX  ON image ( customerid );


CREATE  INDEX  ON imageapproval ( status );


