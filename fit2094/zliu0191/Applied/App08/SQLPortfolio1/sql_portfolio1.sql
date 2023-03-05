--Comment out SET ECHO and SPOOL commands before submitting your portfolio
SET ECHO ON
SPOOL sql_portfolio1_output.txt

--****PLEASE ENTER YOUR DETAILS BELOW****
--sql_portfolio1.sql

--Student ID:32350686
--Student Name:Zirui Liu
--Unit Code:FIT2094
--Applied Class No:A01

/*Task 1: CREATE table INSPECT_ITEM and non FK constraints*/
-- CREATING table INSPECTION_ITEM
CREATE TABLE inspect_item (
    inspitem_id        NUMBER(7) NOT NULL,
    prop_no            NUMBER(4) NOT NULL,
    inspect_datetime   DATE NOT NULL,
    item_code          CHAR(3) NOT NULL,
    inspitem_result    CHAR(1) NOT NULL,
    inspitem_statement VARCHAR2(200)
);

COMMENT ON COLUMN inspect_item.inspitem_id IS
    'Inspect item unique identifier (surrogate key - unique for each inspect item
record)';

COMMENT ON COLUMN inspect_item.prop_no IS
    'Property identifier';

COMMENT ON COLUMN inspect_item.inspect_datetime IS
    'Inspection date and time';

COMMENT ON COLUMN inspect_item.item_code IS
    'Item identifier';

COMMENT ON COLUMN inspect_item.inspitem_result IS
    'Result of inspection item:
U: Urgent Action
S: Standard Met
N: Standard Not Met';

COMMENT ON COLUMN inspect_item.inspitem_statement IS
    'Brief statement of inspection item (when required)';

ALTER TABLE inspect_item ADD CONSTRAINT inspect_item_pk PRIMARY KEY ( inspitem_id );

ALTER TABLE inspect_item
    ADD CONSTRAINT uq_inspect_item UNIQUE ( prop_no,
                                            inspect_datetime,
                                            item_code );

ALTER TABLE inspect_item
    ADD CONSTRAINT ck_inspitem_result CHECK ( inspitem_result IN ( 'U', 'S', 'N' ) );
/*Task 1: Add FK constraints*/
ALTER TABLE inspect_item
    ADD CONSTRAINT inspect_item_inspection_fk FOREIGN KEY ( prop_no,
                                                            inspect_datetime )
        REFERENCES inspection ( prop_no,
                                inspect_datetime );

ALTER TABLE inspect_item
    ADD CONSTRAINT inspect_item_item_fk FOREIGN KEY ( item_code )
        REFERENCES item ( item_code );

/*Task 2: Insert INSPECTOR, INSPECTION and INSPECT_ITEMs*/
INSERT INTO inspector VALUES (
    inspector_seq.NEXTVAL,
    'James',
    'Knight'
);

INSERT INTO inspection VALUES (
    9346,
    TO_DATE('15-Dec-2022  10:00', 'dd-mm-yyyy hh24:mi'),
    inspector_seq.CURRVAL
);

INSERT INTO inspect_item VALUES (
    inspitem_seq.NEXTVAL,
    9346,
    TO_DATE('15-Dec-2022 10:00 AM', 'dd-Mon-yyyy hh:mi AM'),
    'LCK',
    'U',
    'A locksmith has been called to fix the lock'
);

INSERT INTO inspect_item VALUES (
    inspitem_seq.NEXTVAL,
    9346,
    TO_DATE('15-Dec-2022 10:00 AM', 'dd-Mon-yyyy hh:mi AM'),
    'MLD',
    'S',
    NULL
);

COMMIT;
/*Task 3: Change of status for mould and damp to Not Met*/
UPDATE inspect_item
SET
    inspect_item.inspitem_result = 'N',
    inspect_item.inspitem_statement = 'all the rooms and found a small patch of mould is present in the main
bathroom. Tenant has been advised to remove the mould with chemicals.'
WHERE
        prop_no = 9346
    AND item_code = 'MLD'
    AND inspect_datetime = TO_DATE('15-Dec-2022 10:00 AM', 'dd-Mon-yyyy hh:mi AM');

COMMIT;
/*Task 4: Removal of inspection*/
DELETE inspect_item
WHERE
        prop_no = 9346
    AND inspect_datetime = TO_DATE('15-Dec-2022 10:00 AM', 'dd-Mon-yyyy hh:mi AM');

DELETE inspection
WHERE
        prop_no = 9346
    AND inspect_datetime = TO_DATE('15-Dec-2022 10:00 AM', 'dd-Mon-yyyy hh:mi AM');

COMMIT;
--Comment out SET ECHO and SPOOL commands before submitting your portfolio
--SPOOL OFF
--SET ECHO OFF