--Comment out SET ECHO and SPOOL commands before submitting your portfolio
--SET ECHO ON
--SPOOL sql_portfolio2_basic_output.txt

--****PLEASE ENTER YOUR DETAILS BELOW****
--sql_portfolio2_basic.sql

--Student ID:32350686
--Student Name:Zirui Liu
--Unit Code:FIT2094
--Applied Class No:01

/*1*/
-- PLEASE PLACE REQUIRED SQL STATEMENT FOR THIS PART HERE
-- ENSURE that your query is formatted and has a semicolon
-- (;) at the end of this answer
SELECT
    pay_no,
    to_char(pay_date, 'DD-MON-YYYY') AS payment_date,
    pay_type,
    paymethod_name,
    tenant_givname,
    tenant_famname
FROM
    (
        (
                 rent.payment
            NATURAL JOIN rent.paymethod
        )
        NATURAL JOIN rent.rent
        NATURAL JOIN rent.tenant
    )
WHERE
    upper(tenant_famname) LIKE '%D%'
    AND ( pay_date BETWEEN TO_DATE('01/Jan/2021', 'dd/Mon/yyyy') AND TO_DATE('30/Jun/2021'
    , 'dd/Mon/yyyy') )
ORDER BY
    pay_type,
    pay_date;

/*2*/
-- PLEASE PLACE REQUIRED SQL STATEMENT FOR THIS PART HERE
-- ENSURE that your query is formatted and has a semicolon
-- (;) at the end of this answer
SELECT
    maint_id,
    maint_datetime,
    '$' || maint_cost AS maintainence_cost,
    p.prop_no,
    prop_address,
    o.owner_no,
    owner_givname
    || ' '
    || owner_famname  AS fullname
FROM
         rent.owner o
    INNER JOIN rent.property    p
    ON ( o.owner_no = p.owner_no )
    INNER JOIN rent.maintenance m
    ON ( m.prop_no = p.prop_no )
WHERE
    to_char(maint_datetime, 'yyyy') = '2020'
ORDER BY
    maint_cost DESC,
    prop_no;
--Comment out SET ECHO and SPOOL commands before submitting your portfolio
--SPOOL OFF
--SET ECHO OFF