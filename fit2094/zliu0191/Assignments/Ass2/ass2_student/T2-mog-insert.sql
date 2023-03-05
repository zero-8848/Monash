--*****PLEASE ENTER YOUR DETAILS BELOW*****
--T2-mog-insert.sql

--Student ID:32350686
--Student Name:Zirui Liu
--Unit Code:FIT2094
--Applied Class No:A01

/* Comments for your marker:
The artwork can be displayed by a gallery -> return to the MOG -> then display by another gallery

*/

-- Task 2 Load the AW_DISPLAY, AW_STATUS and SALE tables with your own
-- test data following the data requirements expressed in the brief

-- =======================================
-- AW_DISPLAY
-- =======================================
--store 
INSERT INTO aw_display VALUES (
    1,
    1,
    1,
    TO_DATE('02-Sep-2022', 'dd-Mon-yyyy'),
    TO_DATE('02-Oct-2022', 'dd-Mon-yyyy'),
    1
);

INSERT INTO aw_display VALUES (
    2,
    2,
    1,
    TO_DATE('02-Sep-2022', 'dd-Mon-yyyy'),
    TO_DATE('02-Oct-2022', 'dd-Mon-yyyy'),
    1
);

INSERT INTO aw_display VALUES (
    3,
    3,
    1,
    TO_DATE('02-Sep-2022', 'dd-Mon-yyyy'),
    TO_DATE('02-Oct-2022', 'dd-Mon-yyyy'),
    1
);

INSERT INTO aw_display VALUES (
    4,
    4,
    1,
    TO_DATE('04-Sep-2022', 'dd-Mon-yyyy'),
    TO_DATE('04-Oct-2022', 'dd-Mon-yyyy'),
    1
);

INSERT INTO aw_display VALUES (
    5,
    5,
    1,
    TO_DATE('04-Sep-2022', 'dd-Mon-yyyy'),
    TO_DATE('04-Oct-2022', 'dd-Mon-yyyy'),
    1
);
--2nd time 
INSERT INTO aw_display VALUES (
    6,
    1,
    1,
    TO_DATE('04-Nov-2022', 'dd-Mon-yyyy'),
    TO_DATE('04-Dec-2022', 'dd-Mon-yyyy'),
    2
);
--sold ones 
INSERT INTO aw_display VALUES (
    7,
    2,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    2
);

INSERT INTO aw_display VALUES (
    8,
    3,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    2
);

INSERT INTO aw_display VALUES (
    9,
    4,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    2
);

INSERT INTO aw_display VALUES (
    10,
    5,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    2
);

-- =======================================
-- AW_STATUS
-- =======================================
--from warehouse to display
INSERT INTO aw_status VALUES (
    1,
    1,
    1,
    TO_DATE('20-Aug-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    2,
    2,
    1,
    TO_DATE('20-Aug-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    3,
    3,
    1,
    TO_DATE('20-Aug-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    4,
    4,
    1,
    TO_DATE('20-Aug-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    5,
    5,
    1,
    TO_DATE('20-Aug-2022', 'dd-Mon-yyyy'),
    'T',
    1
);
--first time display
INSERT INTO aw_status VALUES (
    6,
    1,
    1,
    TO_DATE('02-Sep-2022', 'dd-Mon-yyyy'),
    'G',
    1
);

INSERT INTO aw_status VALUES (
    7,
    2,
    1,
    TO_DATE('02-Sep-2022', 'dd-Mon-yyyy'),
    'G',
    1
);

INSERT INTO aw_status VALUES (
    8,
    3,
    1,
    TO_DATE('02-Sep-2022', 'dd-Mon-yyyy'),
    'G',
    1
);

INSERT INTO aw_status VALUES (
    9,
    4,
    1,
    TO_DATE('04-Sep-2022', 'dd-Mon-yyyy'),
    'G',
    1
);

INSERT INTO aw_status VALUES (
    10,
    5,
    1,
    TO_DATE('04-Sep-2022', 'dd-Mon-yyyy'),
    'G',
    1
);
--back to warhouse transit 
INSERT INTO aw_status VALUES (
    11,
    1,
    1,
    TO_DATE('02-Oct-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    12,
    2,
    1,
    TO_DATE('02-Oct-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    13,
    3,
    1,
    TO_DATE('02-Oct-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    14,
    4,
    1,
    TO_DATE('04-Oct-2022', 'dd-Mon-yyyy'),
    'T',
    1
);

INSERT INTO aw_status VALUES (
    15,
    5,
    1,
    TO_DATE('04-Oct-2022', 'dd-Mon-yyyy'),
    'T',
    1
);
--Store at central warehouse 
INSERT INTO aw_status VALUES (
    16,
    1,
    1,
    TO_DATE('10-Oct-2022', 'dd-Mon-yyyy'),
    'W',
    NULL
);

INSERT INTO aw_status VALUES (
    17,
    2,
    1,
    TO_DATE('10-Oct-2022', 'dd-Mon-yyyy'),
    'W',
    NULL
);

INSERT INTO aw_status VALUES (
    18,
    3,
    1,
    TO_DATE('10-Oct-2022', 'dd-Mon-yyyy'),
    'W',
    NULL
);

INSERT INTO aw_status VALUES (
    19,
    4,
    1,
    TO_DATE('10-Oct-2022', 'dd-Mon-yyyy'),
    'W',
    NULL
);

INSERT INTO aw_status VALUES (
    20,
    5,
    1,
    TO_DATE('10-Oct-2022', 'dd-Mon-yyyy'),
    'W',
    NULL
);
--transit to second sell
INSERT INTO aw_status VALUES (
    21,
    1,
    1,
    TO_DATE('01-Nov-2022', 'dd-Mon-yyyy'),
    'T',
    2
);

INSERT INTO aw_status VALUES (
    22,
    2,
    1,
    TO_DATE('01-Nov-2022', 'dd-Mon-yyyy'),
    'T',
    2
);

INSERT INTO aw_status VALUES (
    23,
    3,
    1,
    TO_DATE('01-Nov-2022', 'dd-Mon-yyyy'),
    'T',
    2
);

INSERT INTO aw_status VALUES (
    24,
    4,
    1,
    TO_DATE('01-Nov-2022', 'dd-Mon-yyyy'),
    'T',
    2
);

INSERT INTO aw_status VALUES (
    25,
    5,
    1,
    TO_DATE('01-Nov-2022', 'dd-Mon-yyyy'),
    'T',
    2
); 
--arrived at 2nd gallery
INSERT INTO aw_status VALUES (
    26,
    1,
    1,
    TO_DATE('04-Nov-2022', 'dd-Mon-yyyy'),
    'G',
    2
);

INSERT INTO aw_status VALUES (
    27,
    2,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    'G',
    2
);

INSERT INTO aw_status VALUES (
    28,
    3,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    'G',
    2
);

INSERT INTO aw_status VALUES (
    29,
    4,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    'G',
    2
);

INSERT INTO aw_status VALUES (
    30,
    5,
    1,
    TO_DATE('06-Nov-2022', 'dd-Mon-yyyy'),
    'G',
    2
); 
--sold or reutrn 
INSERT INTO aw_status VALUES (
    31,
    1,
    1,
    TO_DATE('04-Dec-2022', 'dd-Mon-yyyy'),
    'R',
    2
);

INSERT INTO aw_status VALUES (
    32,
    2,
    1,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    'S',
    2
);

INSERT INTO aw_status VALUES (
    33,
    3,
    1,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    'S',
    2
);

INSERT INTO aw_status VALUES (
    34,
    4,
    1,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    'S',
    2
);

INSERT INTO aw_status VALUES (
    35,
    5,
    1,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    'S',
    2
); 
-- =======================================
-- SALE
-- =======================================
INSERT INTO sale VALUES (
    1,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    100001,
    1,
    7
);

INSERT INTO sale VALUES (
    2,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    24501,
    1,
    8
);

INSERT INTO sale VALUES (
    3,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    17901,
    3,
    9
);

INSERT INTO sale VALUES (
    4,
    TO_DATE('06-Dec-2022', 'dd-Mon-yyyy'),
    50001,
    4,
    10
);
commit;