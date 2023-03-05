/*
Databases SQL Portfolio 1 2022 Nov12
propinspect_schema_insert.sql

Author: FIT Database Teaching Team
License: Copyright © Monash University, unless otherwise stated. All Rights Reserved.
COPYRIGHT WARNING
Warning
This material is protected by copyright. For use within Monash University only. NOT FOR RESALE.
Do not remove this notice.
*/

-- DROP all PROPINSPECT tables

DROP TABLE inspect_item CASCADE CONSTRAINTS PURGE;

DROP TABLE inspection CASCADE CONSTRAINTS PURGE;

DROP TABLE inspector CASCADE CONSTRAINTS PURGE;

DROP TABLE item CASCADE CONSTRAINTS PURGE;

DROP TABLE owner CASCADE CONSTRAINTS PURGE;

DROP TABLE property CASCADE CONSTRAINTS PURGE;


-- CREATING table INSPECTION

CREATE TABLE inspection (
    prop_no          NUMBER(4) NOT NULL,
    inspect_datetime DATE NOT NULL,
    inspector_id     NUMBER(5) NOT NULL
);

COMMENT ON COLUMN inspection.prop_no IS
    'Unique property identifier';

COMMENT ON COLUMN inspection.inspect_datetime IS
    'Inspection date and time';

COMMENT ON COLUMN inspection.inspector_id IS
    'Inspector unique identifier';

ALTER TABLE inspection ADD CONSTRAINT inspection_pk PRIMARY KEY ( prop_no,
                                                                  inspect_datetime );
-- CREATING table INSPECTOR

CREATE TABLE inspector (
    inspector_id      NUMBER(5) NOT NULL,
    inspector_givname VARCHAR2(20),
    inspector_famname VARCHAR2(20)
);

COMMENT ON COLUMN inspector.inspector_id IS
    'Inspector unique identifier';

COMMENT ON COLUMN inspector.inspector_givname IS
    'Inspector given name';

COMMENT ON COLUMN inspector.inspector_famname IS
    'Inspector family name';

ALTER TABLE inspector ADD CONSTRAINT inspector_pk PRIMARY KEY ( inspector_id );

-- CREATING table ITEM

CREATE TABLE item (
    item_code CHAR(3) NOT NULL,
    item_desc VARCHAR2(25) NOT NULL
);

COMMENT ON COLUMN item.item_code IS
    'Item unique identifier';

COMMENT ON COLUMN item.item_desc IS
    'Item Description';

ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ( item_code );

-- CREATING table OWNER

CREATE TABLE owner (
    owner_no      NUMBER(4) NOT NULL,
    owner_givname VARCHAR2(20),
    owner_famname VARCHAR2(20),
    owner_address VARCHAR2(80) NOT NULL
);

COMMENT ON COLUMN owner.owner_no IS
    'Unique identifier for owner';

COMMENT ON COLUMN owner.owner_givname IS
    'Given name of property owner';

COMMENT ON COLUMN owner.owner_famname IS
    'Family name of property owner';

COMMENT ON COLUMN owner.owner_address IS
    'Address of property owner';

ALTER TABLE owner ADD CONSTRAINT owner_pk PRIMARY KEY ( owner_no );

-- CREATING table PROPERTY

CREATE TABLE property (
    prop_no      NUMBER(4) NOT NULL,
    prop_address VARCHAR2(80) NOT NULL,
    prop_value   NUMBER(10, 2) NOT NULL,
    owner_no     NUMBER(4) NOT NULL
);

COMMENT ON COLUMN property.prop_no IS
    'Unique property identifier';

COMMENT ON COLUMN property.prop_address IS
    'Address of property';

COMMENT ON COLUMN property.prop_value IS
    'Value of property';

COMMENT ON COLUMN property.owner_no IS
    'Unique identifier for owner';

ALTER TABLE property ADD CONSTRAINT property_pk PRIMARY KEY ( prop_no );

-- ADDING FK Constraints

ALTER TABLE inspection
    ADD CONSTRAINT inspector_inspection FOREIGN KEY ( inspector_id )
        REFERENCES inspector ( inspector_id );

ALTER TABLE property
    ADD CONSTRAINT owner_property FOREIGN KEY ( owner_no )
        REFERENCES owner ( owner_no );

ALTER TABLE inspection
    ADD CONSTRAINT property_inspection FOREIGN KEY ( prop_no )
        REFERENCES property ( prop_no );
        
-- CREATING sequence for inspector_id
DROP SEQUENCE inspector_seq;
CREATE SEQUENCE inspector_seq START WITH 30000;

DROP SEQUENCE inspitem_seq;
CREATE SEQUENCE inspitem_seq START WITH 1;

-- INSERTING into OWNER
INSERT INTO owner (
    owner_no,
    owner_givname,
    owner_famname,
    owner_address
) VALUES (
    9321,
    'Lilian',
    'Potter',
    '85 Shields Station St, Apt. 273, 8149, D''amoreburgh, Tasmania'
);

INSERT INTO owner (
    owner_no,
    owner_givname,
    owner_famname,
    owner_address
) VALUES (
    564,
    'Ronald',
    'Meere',
    '219 Mikayla View Rd, Suite 632, 1463, Port Jacobtown, Victoria'
);

INSERT INTO owner (
    owner_no,
    owner_givname,
    owner_famname,
    owner_address
) VALUES (
    852,
    'Ludovika',
    'Wiggins',
    '623 Audrey Avenue, Suite 778, 2350, New Lucytown, Queensland'
);

INSERT INTO owner (
    owner_no,
    owner_givname,
    owner_famname,
    owner_address
) VALUES (
    9762,
    'Jasun',
    'Clitsome',
    '2443 Reynolds Road, Suite 925, 1770, Lake Levi, New South Wales'
);

-- INSERTING into PROPERTY
INSERT INTO property (
    prop_no,
    prop_address,
    prop_value,
    owner_no
) VALUES (
    1965,
    '4633 Leannon Crescent, Suite 962, 1791, Lake Evaside, Tasmania',
    610000,
    9321
);

INSERT INTO property (
    prop_no,
    prop_address,
    prop_value,
    owner_no
) VALUES (
    5990,
    '9454 Ebert Crest, Suite 183, 4027, Trompview, New South Wales',
    686000,
    9762
);

INSERT INTO property (
    prop_no,
    prop_address,
    prop_value,
    owner_no
) VALUES (
    6123,
    '26 Hackett Knoll, Apt. 615, 6856, South Annashire, Victoria',
    640000,
    564
);

INSERT INTO property (
    prop_no,
    prop_address,
    prop_value,
    owner_no
) VALUES (
    7145,
    '928 Olivia Crest, Apt. 293, 4328, Connellymouth, Queensland',
    460000,
    852
);

INSERT INTO property (
    prop_no,
    prop_address,
    prop_value,
    owner_no
) VALUES (
    9346,
    '531 Sienna Run, Suite 991, 6792, Boscoburgh, Queensland',
    500000,
    852
);

-- INSERTING into INSPECTOR
INSERT INTO inspector (
    inspector_id,
    inspector_givname,
    inspector_famname
) VALUES (
    29542,
    'Karina',
    'Black'
);

INSERT INTO inspector (
    inspector_id,
    inspector_givname,
    inspector_famname
) VALUES (
    29123,
    'Katherine',
    'Black'
);

-- INSERTING into INSPECTION
INSERT INTO inspection (
    prop_no,
    inspect_datetime,
    inspector_id
) VALUES (
    1965,
    TO_DATE('2021-07-16 10:00', 'yyyy-mm-dd hh24:mi'),
    29542
);

INSERT INTO inspection (
    prop_no,
    inspect_datetime,
    inspector_id
) VALUES (
    1965,
    TO_DATE('2021-07-16 16:00', 'yyyy-mm-dd hh24:mi'),
    29542
);

INSERT INTO inspection (
    prop_no,
    inspect_datetime,
    inspector_id
) VALUES (
    1965,
    TO_DATE('2022-01-21 11:30', 'yyyy-mm-dd hh24:mi'),
    29123
);

-- INSERTING into ITEM
INSERT INTO item (
    item_code,
    item_desc
) VALUES (
    'LCK',
    'Lock'
);

INSERT INTO item (
    item_code,
    item_desc
) VALUES (
    'VRM',
    'Vermin Proof Bins'
);

INSERT INTO item (
    item_code,
    item_desc
) VALUES (
    'TLT',
    'Toilets'
);

INSERT INTO item (
    item_code,
    item_desc
) VALUES (
    'BTR',
    'Bathroom'
);

INSERT INTO item (
    item_code,
    item_desc
) VALUES (
    'KTC',
    'Kitchen'
);

INSERT INTO item (
    item_code,
    item_desc
) VALUES (
    'STS',
    'Structural Soundness'
);

INSERT INTO item (
    item_code,
    item_desc
) VALUES (
    'MLD',
    'Mould and Damp'
);

COMMIT;