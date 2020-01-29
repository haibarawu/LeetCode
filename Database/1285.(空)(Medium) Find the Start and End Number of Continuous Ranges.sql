/****************************************************************************************************
1285. Find the Start and End Number of Continuous Ranges

Difficulty: Medium

Table: Logs
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| log_id        | int     |
+---------------+---------+
id is the primary key for this table.
Each row of this table contains the ID in a log Table.

Since some IDs have been removed from Logs. 
Write an SQL query to find the start and end number of continuous ranges in table Logs.
Order the result table by start_id.

The query result format is in the following example:

Logs table:
+------------+
| log_id     |
+------------+
| 1          |
| 2          |
| 3          |
| 7          |
| 8          |
| 10         |
+------------+

Result table:
+------------+--------------+
| start_id   | end_id       |
+------------+--------------+
| 1          | 3            |
| 7          | 8            |
| 10         | 10           |
+------------+--------------+
The result table should contain all ranges in table Logs.
From 1 to 3 is contained in the table.
From 4 to 6 is missing in the table
From 7 to 8 is contained in the table.
Number 9 is missing in the table.
Number 10 is contained in the table.

****************************************************************************************************/


--Method1:
## https://code.dennyzhang.com/find-the-start-and-end-number-of-continuous-ranges
## find the starting sequence: 1, 7, 10
## find the ending sequence: 3, 8, 10
## merge them as one table
select start_id, min(end_id) as end_id
from (select t1.log_id as start_id
    from logs as t1 left join logs as t2
    on t1.log_id-1 = t2.log_id
    where t2.log_id is null) tt_start join
    (select t1.log_id as end_id
    from logs as t1 left join logs as t2
    on t1.log_id+1 = t2.log_id
    where t2.log_id is null) tt_end
where start_id<=end_id
group by start_id


/****************************************************************************************************/


--Method2:
SELECT
    s.log_id AS start_id,
    e.log_id AS end_id
FROM Logs s
JOIN Logs e
WHERE s.log_id <= e.log_id
AND e.log_id - s.log_id + 1 = (
    SELECT COUNT(1)
    FROM Logs
    WHERE log_id BETWEEN s.log_id AND e.log_id
)
AND NOT EXISTS (
    SELECT 1
    FROM Logs s1
    JOIN Logs e1
    WHERE s1.log_id <= s.log_id
    AND e1.log_id >= e.log_id
    AND NOT (s1.log_id = s.log_id AND e1.log_id = e.log_id)
    AND e1.log_id - s1.log_id + 1 = (
        SELECT COUNT(1)
        FROM Logs
        WHERE log_id BETWEEN s1.log_id AND e1.log_id
    )
)
ORDER BY start_id


