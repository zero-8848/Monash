--*****PLEASE ENTER YOUR DETAILS BELOW*****
--T4-mog-alter.sql

--Student ID:32350686
--Student Name:Zirui Liu
--Unit Code:FIT2094
--Applied Class No:A01

/* Comments for your marker:
purchase_no in follow_artist table stores number of artworks that this customer has purchased from this artist
*/

--4(a)

ALTER TABLE artist ADD total_number_of_sold_or_availale_artworks NUMERIC(4);

COMMENT ON COLUMN artist.total_number_of_sold_or_availale_artworks IS
    ' the total number of artworks that each artist has submitted which have been sold or are currently available for sale'
    ;

UPDATE artist
SET
    total_number_of_sold_or_availale_artworks = (
        SELECT
            COUNT(DISTINCT artwork_no) AS total_number_of_sold_or_availale_artworks
        FROM
--all works 
            (
                (
                    SELECT
                        a.artist_code,
                        w.artwork_no
                    FROM
                        artist    a
                        LEFT OUTER JOIN artwork   w
                        ON a.artist_code = w.artist_code
                        LEFT OUTER JOIN aw_status s
                        ON ( w.artist_code = s.artist_code
                             AND w.artwork_no = s.artwork_no )
                )
                MINUS
--having R  ones 
                (
                    SELECT
                        a.artist_code,
                        w.artwork_no
                    FROM
                        artist    a
                        LEFT OUTER JOIN artwork   w
                        ON a.artist_code = w.artist_code
                        LEFT OUTER JOIN aw_status s
                        ON ( w.artist_code = s.artist_code
                             AND w.artwork_no = s.artwork_no )
                    WHERE
                        s.aws_status = 'R'
                )
            )
        WHERE
            artist_code = artist.artist_code
        GROUP BY
            artist_code
    );

COMMIT;

--4(b)
ALTER TABLE customer ADD customer_loyalty_level CHAR(1);

COMMENT ON COLUMN customer.customer_loyalty_level IS
    'classify customers into three different levels based on the amount of money they have
spent at MOG using the following criteria:
D: Diamond customer who have spent more than or equal to $100000
P: Platinum customer who have spent more than $50000 but less than $100000
G: Gold customer who have spent less than and equal to $50000 including the customers
who haven¡¯t spent anything (e.g., a newly registered customer).';

ALTER TABLE customer
    ADD CONSTRAINT ck_customer_loyalty_level CHECK ( customer_loyalty_level IN ( 'D',
    'P', 'G' ) );

UPDATE customer
SET
    customer_loyalty_level = (
        SELECT
            CASE
                WHEN total_spent >= 100000           THEN
                    'D'
                WHEN total_spent > 50000
                     AND total_spent < 100000 THEN
                    'P'
                WHEN total_spent BETWEEN 0 AND 50000 THEN
                    'G'
            END AS customer_loyalty_level
        FROM
            (
                SELECT
                    c.customer_id,
                    nvl(SUM(sale_price),
                        0) AS total_spent
                FROM
                    customer c
                    LEFT OUTER JOIN sale     s
                    ON c.customer_id = s.customer_id
                GROUP BY
                    c.customer_id
            )
        WHERE
            customer_id = customer.customer_id
    );

COMMIT;
--4(c)
DROP TABLE follow_artist CASCADE CONSTRAINTS;

CREATE TABLE follow_artist (
    customer_id NUMBER(5) NOT NULL,
    artist_code NUMBER(4) NOT NULL,
    purchase_no NUMBER(4)
);

ALTER TABLE follow_artist ADD CONSTRAINT follow_artist_pk PRIMARY KEY ( customer_id,
                                                                        artist_code )
                                                                        ;

ALTER TABLE follow_artist
    ADD CONSTRAINT customer_follow_artist_fk FOREIGN KEY ( customer_id )
        REFERENCES customer ( customer_id );

ALTER TABLE follow_artist
    ADD CONSTRAINT artist_follow_artist_fk FOREIGN KEY ( artist_code )
        REFERENCES artist ( artist_code );

COMMENT ON COLUMN follow_artist.customer_id IS
    'Identifier for customer';

COMMENT ON COLUMN follow_artist.artist_code IS
    'Identifier for artist';

COMMENT ON COLUMN follow_artist.purchase_no IS
    'number of artworks that this customer has purchased from this artist';

INSERT INTO follow_artist (
    customer_id,
    artist_code,
    purchase_no
)
    SELECT
        c.customer_id,
        a.artist_code,
        nvl(sold_no, 0)
    FROM
             customer c
        NATURAL JOIN artist a
        LEFT OUTER JOIN (
            SELECT
                customer_id,
                artist_code,
                COUNT(sale_id) AS sold_no
            FROM
                     customer
                NATURAL JOIN sale
                NATURAL JOIN aw_display
            GROUP BY
                customer_id,
                artist_code
        )      b
        ON ( c.customer_id = b.customer_id
             AND a.artist_code = b.artist_code );

COMMIT;