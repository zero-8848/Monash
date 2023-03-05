/*
  Databases 2022 NOV12 Assignment 2
  --Monash Oz Gallery Schema File and Initial Data--
  --mog_schema_insert.sql
  
  Description: 
  This file creates the Monash Oz Gallery tables 
  and populates several of the tables (those shown in purple on the supplied model). 
  You should read this schema file carefully 
  and be sure you understand the various data requirements.

Author: FIT Database Teaching Team
License: Copyright Monash University, unless otherwise stated. All Rights Reserved.
COPYRIGHT WARNING
Warning
This material is protected by copyright. For use within Monash University only. NOT FOR RESALE.
Do not remove this notice. 
  
*/

DROP TABLE artist CASCADE CONSTRAINTS;

DROP TABLE artwork CASCADE CONSTRAINTS;

DROP TABLE aw_display CASCADE CONSTRAINTS;

DROP TABLE aw_status CASCADE CONSTRAINTS;

DROP TABLE customer CASCADE CONSTRAINTS;

DROP TABLE gallery CASCADE CONSTRAINTS;

DROP TABLE sale CASCADE CONSTRAINTS;

CREATE TABLE artist (
    artist_code   NUMBER(4) NOT NULL,
    artist_gname  VARCHAR2(50),
    artist_fname  VARCHAR2(50),
    artist_street VARCHAR2(50) NOT NULL,
    artist_town   VARCHAR2(50) NOT NULL,
    artist_state  CHAR(3) NOT NULL,
    artist_phone  CHAR(10)
);

COMMENT ON COLUMN artist.artist_code IS
    'Identifier for artist';

COMMENT ON COLUMN artist.artist_gname IS
    'Artist''s given name';

COMMENT ON COLUMN artist.artist_fname IS
    'Artist''s family name';

COMMENT ON COLUMN artist.artist_street IS
    'Street address of artist';

COMMENT ON COLUMN artist.artist_town IS
    'Town address of artist';

COMMENT ON COLUMN artist.artist_state IS
    'State address of artist';

COMMENT ON COLUMN artist.artist_phone IS
    'Phone number of artist';

ALTER TABLE artist ADD CONSTRAINT artist_pk PRIMARY KEY ( artist_code );

CREATE TABLE artwork (
    artist_code        NUMBER(4) NOT NULL,
    artwork_no         NUMBER(4) NOT NULL,
    artwork_title      VARCHAR2(100) NOT NULL,
    artwork_minprice   NUMBER(9, 2) NOT NULL,
    artwork_submitdate DATE NOT NULL
);

COMMENT ON COLUMN artwork.artist_code IS
    'Identifier for artist';

COMMENT ON COLUMN artwork.artwork_no IS
    'Identifier for artwork within this artist';

COMMENT ON COLUMN artwork.artwork_title IS
    'Title of artwork';

COMMENT ON COLUMN artwork.artwork_minprice IS
    'Minimum price artist is prepared to accept for the artwork';

COMMENT ON COLUMN artwork.artwork_submitdate IS
    'The date when an artwork is submitted';

ALTER TABLE artwork ADD CONSTRAINT artwork_pk PRIMARY KEY ( artwork_no,
                                                            artist_code );


CREATE TABLE customer (
    customer_id      NUMBER(5) NOT NULL,
    customer_gname   VARCHAR2(50),
    customer_fname   VARCHAR2(50),
    customer_busname VARCHAR2(100),
    customer_street  VARCHAR2(50) NOT NULL,
    customer_town    VARCHAR2(50) NOT NULL,
    customer_state   CHAR(3) NOT NULL,
    customer_phone   CHAR(10) NOT NULL
);

COMMENT ON COLUMN customer.customer_id IS
    'Identifier for customer';

COMMENT ON COLUMN customer.customer_gname IS
    'Customers given name';

COMMENT ON COLUMN customer.customer_fname IS
    'Customers family name';

COMMENT ON COLUMN customer.customer_busname IS
    'Business name of customer';

COMMENT ON COLUMN customer.customer_street IS
    'Street address of customer';

COMMENT ON COLUMN customer.customer_town IS
    'Town address of customer';

COMMENT ON COLUMN customer.customer_state IS
    'State address of customer';

COMMENT ON COLUMN customer.customer_phone IS
    'Phone number of customer';

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( customer_id );

ALTER TABLE customer ADD CONSTRAINT customer_phone_uq UNIQUE ( customer_phone );

CREATE TABLE gallery (
    gallery_id           NUMBER(3) NOT NULL,
    gallery_name         VARCHAR2(100) NOT NULL,
    gallery_manager      VARCHAR2(60) NOT NULL,
    gallery_street       VARCHAR2(50) NOT NULL,
    gallery_town         VARCHAR2(50) NOT NULL,
    gallery_state        CHAR(3) NOT NULL,
    gallery_phone        CHAR(10) NOT NULL,
    gallery_sale_percent NUMBER(4, 1) NOT NULL,
    gallery_open         DATE NOT NULL,
    gallery_close        DATE NOT NULL
);

COMMENT ON COLUMN gallery.gallery_id IS
    'Identifier for Gallery';

COMMENT ON COLUMN gallery.gallery_name IS
    'Name of gallery';

COMMENT ON COLUMN gallery.gallery_manager IS
    'Name of gallery manager';

COMMENT ON COLUMN gallery.gallery_street IS
    'Street address of gallery';

COMMENT ON COLUMN gallery.gallery_town IS
    'Town address of gallery';

COMMENT ON COLUMN gallery.gallery_state IS
    'State address of gallery';

COMMENT ON COLUMN gallery.gallery_phone IS
    'Phone number of gallery';

COMMENT ON COLUMN gallery.gallery_sale_percent IS
    'Percentage of sale for gallery';

COMMENT ON COLUMN gallery.gallery_open IS
    'Open time of gallery';

COMMENT ON COLUMN gallery.gallery_close IS
    'Close time of gallery';

ALTER TABLE gallery ADD CONSTRAINT gallery_pk PRIMARY KEY ( gallery_id );

ALTER TABLE gallery ADD CONSTRAINT gallery_phone_uq UNIQUE ( gallery_phone );

ALTER TABLE artwork
    ADD CONSTRAINT artist_artwork FOREIGN KEY ( artist_code )
        REFERENCES artist ( artist_code );

--------------------------------------
--INSERT INTO artist
--------------------------------------
INSERT INTO artist VALUES (1, 'Martainn', 'Jenteau', '328 Forest Pass', 'Melbourne', 'VIC', '0495300384');
INSERT INTO artist VALUES (2, 'Kipp', 'Grellis', '2755 Briar Crest Place', 'South Yarra', 'VIC', '0468858093');
INSERT INTO artist VALUES (3, 'Jessi', 'Allward', '9 Becker Plaza', 'Wallan', 'VIC', '0438843662');
INSERT INTO artist VALUES (4, 'Rosalinda', 'Zavattiero', '1 Del Mar Avenue', 'Malvern East', 'VIC', '');
INSERT INTO artist VALUES (5, 'Neda', 'Mylchreest', '327 Caliangt Street', 'Clayton South', 'VIC', '0409562816');
INSERT INTO artist VALUES (6, 'Elga', 'Yakolev', '6 Hanson Park', 'Lysterfield', 'VIC', '0496667027');
INSERT INTO artist VALUES (7, 'Weston', 'Stearndale', '39512 Kipling Road', 'Leongatha', 'VIC', '0417905216');
INSERT INTO artist VALUES (8, 'Reeba', 'Wildman', '92542 Service Junction', 'Malvern East', 'VIC', '0493427245');
INSERT INTO artist VALUES (9, 'Marlee', 'Champerlen', '64201 Carey Circle', 'Clayton South', 'VIC', '0427832032');
INSERT INTO artist VALUES (10, 'Dorette', '', '87596 Porter Place', 'Lysterfield', 'VIC', '0487345845');
INSERT INTO artist VALUES (11, 'Westley', 'Oakenford', '137 Tennessee Street', 'Attwood', 'VIC', '0418289108');
INSERT INTO artist VALUES (12, 'Kilian', 'Moisey', '05422 Pearson Avenue', 'Melbourne', 'VIC', '0429418600');

--------------------------------------
--INSERT INTO customer
--------------------------------------
INSERT INTO customer VALUES (1, 'Florida', 'Goldhawk', '', '904 Talmadge Lane', 'Belgrave', 'VIC', '0454762942');
INSERT INTO customer VALUES (2, 'Marina', 'Clements', '', '4632 Monica Plaza', 'Belgrave South', 'VIC', '0425271315');
INSERT INTO customer VALUES (3, 'Stefanie', 'Wilstead', '', '1723 Dottie Parkway', 'Pakenham', 'VIC', '0452272267');
INSERT INTO customer VALUES (4, '', '', 'Quinlan Temperley Pvt. Ltd.', '1 Kinsman Terrace', 'Melbourne', 'VIC', '0475110074');
INSERT INTO customer VALUES (5, 'Lois', 'Hawkshaw', '', '7480 Center Crossing', 'Pakenham Upper', 'VIC', '0458708402');
INSERT INTO customer VALUES (6, 'Reinald', 'Sedwick', '', '2422 Calypso Circle', 'Leongatha', 'VIC', '0489832003');
INSERT INTO customer VALUES (7, 'Jobie', 'Pheazey', '', '03 New Castle Center', 'Leongatha', 'VIC', '0475761206');
INSERT INTO customer VALUES (8, '', '', 'Rochelle Zecchinii Pvt. Ltd.', '10 Forest Dale Terrace', 'Melbourne', 'VIC', '0409646679');
INSERT INTO customer VALUES (9, 'Danila', 'Geraldo', '', '9 Londonderry Parkway', 'Leongatha', 'VIC', '0464179129');
INSERT INTO customer VALUES (10, 'Haleigh', 'Bonifacio', '', '93 Buell Lane', 'Malvern East', 'VIC', '0480445917');


--------------------------------------
--INSERT INTO gallery
--------------------------------------
INSERT INTO gallery VALUES (1, 'Karma Art', 'Seline Fortey', '9 Mallory Court', 'Attwood', 'VIC', '0413432569', 5.6, TO_DATE('9:30', 'HH24:MI'), TO_DATE('17:30', 'HH24:MI'));
INSERT INTO gallery VALUES (2, 'Artology', 'Charmaine Gehrtz', '9 Gerald Park', 'Melbourne', 'VIC', '0474980815', 10.3, TO_DATE('9:00', 'HH24:MI'), TO_DATE('18:00', 'HH24:MI'));
INSERT INTO gallery VALUES (3, 'Inspire Art', 'Malissa McGlynn', '327 Rigney Plaza', 'Camira', 'VIC', '0417407587', 7.5, TO_DATE('10:00', 'HH24:MI'), TO_DATE('18:00', 'HH24:MI'));
INSERT INTO gallery VALUES (4, 'Art Smart', 'Ferrell Byard', '04 Florence Alley', 'Richmond', 'VIC', '0490556646', 9.6, TO_DATE('10:00', 'HH24:MI'), TO_DATE('18:00', 'HH24:MI'));
INSERT INTO gallery VALUES (5, 'Art Temple', 'Bernard Msard', '123 Hyde Park', 'South Yarra', 'VIC', '0438093219', 10.5, TO_DATE('10:00', 'HH24:MI'), TO_DATE('18:00', 'HH24:MI'));


--------------------------------------
--INSERT INTO artwork
--------------------------------------
INSERT INTO artwork VALUES (1, 1, 'The Creation of Adam', 30000, TO_DATE('2-Jun-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (2, 1, 'The Starry Night', 55400, TO_DATE('4-Jun-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (3, 1, 'Saint Francis of Assisi', 24500, TO_DATE('5-Jun-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (4, 1, 'The Last Supper', 17900, TO_DATE('6-Jun-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (7, 1, 'Sunday Afternoon on an Island', 12900, TO_DATE('7-Jun-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (8, 1, 'Girl with a Pearl Earring', 23100, TO_DATE('8-Jun-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (5, 1, 'The Lady of Shalott', 45000, TO_DATE('15-Jul-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (9, 1, 'The Mystic', 34000, TO_DATE('14-Aug-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (10, 1, 'The Scientist', 24000, TO_DATE('1-Sep-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (1, 2, 'Luncheon of the Boating Party', 14500, TO_DATE('18-Oct-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (7, 2, 'Saint Francis of Assisi', 34536.9, TO_DATE('19-Oct-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (8, 2, 'Cafe Terrace at Night', 45600.35, TO_DATE('24-Oct-2022', 'DD-MON-YYYY'));
INSERT INTO artwork VALUES (5, 2, 'The Sojourn', 46700.45, TO_DATE('27-Oct-2022', 'DD-MON-YYYY'));

commit;