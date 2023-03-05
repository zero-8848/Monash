/*
Databases Week 2 Tutorial
week2_student.sql

student id:
student name:
last modified date:

*/

DROP TABLE student CASCADE CONSTRAINTS;

CREATE TABLE student (
    studid      NUMBER(8) NOT NULL,
    studfname   VARCHAR2(20) NOT NULL,
    studlname   VARCHAR2(20) NOT NULL,
    studdob     DATE NOT NULL,
    studaddress VARCHAR2(100) NOT NULL,
    studphone   CHAR(15) NOT NULL,
    studemail   VARCHAR2(50) NOT NULL
);

COMMENT ON COLUMN student.studid IS
    'Student Identifier';

COMMENT ON COLUMN student.studfname IS
    'Student first name';

COMMENT ON COLUMN student.studlname IS
    'Student last name';

COMMENT ON COLUMN student.studdob IS
    'Student date of birth';

COMMENT ON COLUMN student.studaddress IS
    'Student address';

COMMENT ON COLUMN student.studphone IS
    'Student contact phone number';

COMMENT ON COLUMN student.studemail IS
    'Student email';

ALTER TABLE student ADD CONSTRAINT student_pk PRIMARY KEY ( studid );

--Populate table insert
INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    12489379,
    'Gilberto',
    'Bwy',
    TO_DATE('30-Aug-1992', 'dd-Mon-yyyy'),
    '5664 Loomis Parkway, Melbourne',
    '7037621034',
    'Gilberto.Bwy@student.monash.edu'
);

INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    12511467,
    'Francyne',
    'Rigney',
    TO_DATE('18-Jan-1992', 'dd-Mon-yyyy'),
    '75 Buhler Street, Mulgrave',
    '6994152403',
    'Francyne.Rigney@student.monash.edu'
);

INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    12609485,
    'Cassondra',
    'Sedcole',
    TO_DATE('07-Aug-1990', 'dd-Mon-yyyy'),
    '6507 Tennessee Alley, Melbourne',
    '8343944500',
    'Cassondra.Sedcole@student.monash.edu'
);

INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    12802225,
    'Friedrick',
    'Geist',
    TO_DATE('02-Mar-1997', 'dd-Mon-yyyy'),
    '99271 Eliot Pass, Dingley',
    '6787553656',
    'Friedrick.Geist@student.monash.edu'
);

INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    12842838,
    'Herminia',
    'Mendus',
    TO_DATE('25-Apr-1996', 'dd-Mon-yyyy'),
    '64186 East Lane, Moorabbin',
    '4896374903',
    'Herminia.Mendus@student.monash.edu'
);

INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    13028303,
    'Herculie',
    'Mendus',
    TO_DATE('02-Aug-1998', 'dd-Mon-yyyy'),
    '44 Becker Street, Mulgrave',
    '2309618710',
    'Herculie.Mendus@student.monash.edu'
);

INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    13119134,
    'Shandra',
    'Lindblom',
    TO_DATE('20-Apr-2000', 'dd-Mon-yyyy'),
    '9241 Rieder Parkway, Chelsea',
    '4384142213',
    'Shandra.Lindblom@student.monash.edu'
);

INSERT INTO student (
    studid,
    studfname,
    studlname,
    studdob,
    studaddress,
    studphone,
    studemail
) VALUES (
    13390148,
    'Brier',
    'Kilgour',
    TO_DATE('21-Feb-1995', 'dd-Mon-yyyy'),
    '79776 Dryden Plaza, Moorabbin',
    '6981280319',
    'Brier.Kilgour@student.monash.edu'
);

COMMIT;