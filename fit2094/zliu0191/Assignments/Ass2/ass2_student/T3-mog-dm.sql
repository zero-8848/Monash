--*****PLEASE ENTER YOUR DETAILS BELOW*****
--T3-mog-dm.sql

--Student ID:32350686
--Student Name:Zirui Liu
--Unit Code:FIT2094
--Applied Class No:01

/* Comments for your marker: 

*/
--(a) Oracle sequences are going to be implemented in the database for the subsequent
--insertion of records into the database for the AW_DISPLAY, AW_STATUS and SALE tables
--3(a)
Drop SEQUENCE AW_DISPLAY_seq;
CREATE SEQUENCE AW_DISPLAY_seq START WITH 100 INCREMENT BY 10;
Drop SEQUENCE AW_STATUS_seq;
CREATE SEQUENCE AW_STATUS_seq  INCREMENT BY 10 START WITH 100;
Drop SEQUENCE SALE_seq;
CREATE SEQUENCE SALE_seq START WITH 100 INCREMENT BY 10;
--3(b)
INSERT INTO artwork values((
    SELECT 
        artist_code
    FROM
        artist
    WHERE 
        artist_code = 1
),3,'The Sunny Christmas in Oz',25000,TO_DATE('30-Dec-2022 11:00 AM','dd-Mon-yyyy hh:mi AM'));
--status
INSERT INTO aw_status values(aw_status_seq.nextval,(
    SELECT 
        artist_code
    FROM
        artist
    WHERE 
        artist_code = 1
),(select artwork_no from artwork where artist_code=1 and artwork_title='The Sunny Christmas in Oz'),TO_DATE('30-Dec-2022 11:00 AM','dd-Mon-yyyy hh:mi AM'),'W',null);

Commit;
--3(c)
--transit
INSERT INTO aw_status values(aw_status_seq.nextval,(
    SELECT 
        artist_code
    FROM
        artist
    WHERE 
        artist_code = 1
),(select artwork_no from artwork where artist_code=1 and artwork_title='The Sunny Christmas in Oz'),TO_DATE('01-Jan-2023 01:00 PM','dd-Mon-yyyy hh:mi PM'),'T',(select gallery_id from gallery where  gallery_phone='0490556646'));
--arrived
INSERT INTO aw_status values(aw_status_seq.nextval,(
    SELECT 
        artist_code
    FROM
        artist
    WHERE 
        artist_code = 1
),(select artwork_no from artwork where artist_code=1 and artwork_title='The Sunny Christmas in Oz'),TO_DATE('01-Jan-2023 01:00 PM','dd-Mon-yyyy hh:mi PM')+(2.5/24),'G',(select gallery_id from gallery where  gallery_phone='0490556646'));


INSERT INTO aw_display VALUES (
    aw_display_seq.nextval,
    (SELECT 
        artist_code
    FROM
        artist
    WHERE 
        artist_code = 1),
    (select artwork_no from artwork where artist_code=1 and artwork_title='The Sunny Christmas in Oz'),
    TO_DATE('01-Jan-2023','dd-Mon-yyyy')+1,
    TO_DATE('01-Jan-2023','dd-Mon-yyyy')+1+14,
    (select gallery_id from gallery where gallery_phone='0490556646')
);
commit;
--

--3(d)
INSERT INTO sale VALUES (
    sale_seq.nextval,
    TO_DATE('04-Jan-2023 11:30 AM','dd-Mon-yyyy hh:mi AM')+(2.5/24),
    29499.99,
    (select customer_id from customer where customer_phone='0480445917'),
    aw_display_seq.currval
);

UPDATE aw_display
SET
    aw_display_end_date = TO_DATE('04-Jan-2023', 'dd-Mon-yyyy')
WHERE
        artist_code = (
            SELECT
                artist_code
            FROM
                artist
            WHERE
                artist_code = 1
        )
    AND artwork_no = (
        SELECT
            artwork_no
        FROM
            artwork
        WHERE
                artist_code = 1
            AND artwork_title = 'The Sunny Christmas in Oz'
    )
    AND aw_display_start_date = TO_DATE('01-Jan-2023', 'dd-Mon-yyyy') + 1;
    


INSERT INTO aw_status VALUES (
    aw_status_seq.NEXTVAL,
    (
        SELECT
            artist_code
        FROM
            artist
        WHERE
            artist_code = 1
    ),
    (
        SELECT
            artwork_no
        FROM
            artwork
        WHERE
                artist_code = 1
            AND artwork_title = 'The Sunny Christmas in Oz'
    ),
    TO_DATE('04-Jan-2023 11:30 AM', 'dd-Mon-yyyy hh:mi AM'),
    'S',
    (
        SELECT
            gallery_id
        FROM
            gallery
        WHERE
            gallery_phone = '0490556646'
    )
);
commit;   
--3(e)
--delete sale
delete from sale where aw_display_id=(SELECT 
        aw_display_id
    FROM
        aw_display
    WHERE 
        artist_code=(
        SELECT
            artist_code
        FROM
            artist
        WHERE
            artist_code = 1
    )
         and 
        artwork_no=(select artwork_no from artwork where artist_code=1 and artwork_title='The Sunny Christmas in Oz')
    and
    aw_display_start_date=TO_DATE('01-Jan-2023','dd-Mon-yyyy')+1);


--update back 
UPDATE aw_display
SET
    aw_display_end_date =     TO_DATE('01-Jan-2023','dd-Mon-yyyy')+1+14
WHERE
        artist_code = (
            SELECT
                artist_code
            FROM
                artist
            WHERE
                artist_code = 1
        )
    AND artwork_no = (
        SELECT
            artwork_no
        FROM
            artwork
        WHERE
                artist_code = 1
            AND artwork_title = 'The Sunny Christmas in Oz'
    )
    AND aw_display_start_date = TO_DATE('01-Jan-2023', 'dd-Mon-yyyy') + 1;
delete from aw_status where 
artist_code=(
        SELECT
            artist_code
        FROM
            artist
        WHERE
            artist_code = 1
    ) and
    artwork_no=(
        SELECT
            artwork_no
        FROM
            artwork
        WHERE
                artist_code = 1
            AND artwork_title = 'The Sunny Christmas in Oz'
    )and
    aws_date_time=TO_DATE('04-Jan-2023 11:30 AM', 'dd-Mon-yyyy hh:mi AM');
commit;