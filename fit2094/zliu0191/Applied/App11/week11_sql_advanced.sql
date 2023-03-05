/*
Databases Week 11 Applied Class
week11_sql_advanced.sql

student id: 
student name:
last modified date:

*/
--1
select 
    unitcode,
    unitname,
    to_char(ofyear,'yyyy') as year,
    ofsemester,
    enrolmark,
    case upper(enrolgrade)
    when 'N' then 'Fail'
    when 'P' then 'Pass'
    when 'C' then 'Credit'
    when 'D' then 'Distinction'
    when 'HD' then 'High Distinction'
    end as expland_grade
from uni.enrolment natural join uni.unit
where stuid = (select stuid
    from  uni.student where upper(stufname) = upper('Claudette') and upper(stulname)= upper('Serman'))
order by ofyear, ofsemester, unitcode;

--2



--3


--4
--Display the unit code and unit name for units that do not have a prerequisite. Order the list by
--unit code. There are many approaches that you can take in writing an SQL statement to answer
--this query. You can use the SET OPERATORS, OUTER JOIN and a SUBQUERY. Write SQL
--statements based on all three approaches.

/* Using outer join */



/* Using set operator MINUS */



/* Using subquery */
select unit_code,unit_name
from 


--5



--6



--7



--8


    
--9


    
--10







