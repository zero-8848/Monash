--Comment out SET ECHO and SPOOL commands before submitting your portfolio
--SET ECHO ON
--SPOOL sql_portfolio3_intermediate_output.txt

--****PLEASE ENTER YOUR DETAILS BELOW****
--sql_portfolio3_intermediate.sql

--Student ID:32350686
--Student Name:Zirui Liu
--Unit Code:FIT2094
--Applied Class No:01

/*1*/
-- PLEASE PLACE REQUIRED SQL STATEMENT FOR THIS PART HERE
-- ENSURE that your query is formatted and has a semicolon
-- (;) at the end of this answer

--1. List the ,  and total number of rents in a column named
-- for each property 
-- List the property with the highest total number
--of rents first. For properties with the same number of rents, order them based on property
--number.
SELECT
    prop_no,
    prop_address,
    COUNT(rent.rent_agreement_no) AS "Number of Rents"
FROM
         rent.property
    NATURAL JOIN rent.rent
WHERE
    rent_lease_start BETWEEN TO_DATE('01-01-2020', 'dd-mm-yyyy') AND TO_DATE('31-12-2021'
    , 'dd-mm-yyyy')
GROUP BY
    prop_no,
    prop_address
HAVING
    COUNT(rent.rent_agreement_no) > 2
ORDER BY
    "Number of Rents" DESC,
    prop_no;

/*2*/
-- PLEASE PLACE REQUIRED SQL STATEMENT FOR THIS PART HERE
-- ENSURE that your query is formatted and has a semicolon
-- (;) at the end of this answer
--2. For each property which has recorded maintenance in 2022, list property number, property
--address, the number of maintenance, total amount of maintenance cost (with a leading $
--sign) and the maximum maintenance cost paid by the owner (with a leading $ sign) . Order
--the list by the descending order of total number of maintenance made then by the property
--number.

SELECT
    prop_no,
    prop_address,
    COUNT(maint_id) AS number_of_maintenance,
    '$' || sum(maint_cost) as total_amount_of_maintenance_cost,
    '$' || max(maint_cost) as maximum_maintenance_cost_paid
FROM
         rent.property
    NATURAL JOIN rent.maintenance
WHERE
    to_char(maint_datetime, 'yyyy') = 2022
GROUP BY
    prop_no,
    prop_address
ORDER BY
    number_of_maintenance DESC,
    prop_no;
    
--select * from rent.maintenance
--Comment out SET ECHO and SPOOL commands before submitting your portfolio
--SPOOL OFF
--SET ECHO OFF