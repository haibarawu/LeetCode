/********************************************************************************
601. Human Traffic of Stadium

Difficulty: Hard

X city built a new stadium, each day many people visit it and the stats are saved as these columns: id, visit_date, people

Please write a query to display the records which have 3 or more consecutive rows and the amount of people more than 100(inclusive).

For example, the table stadium:
+------+------------+-----------+
| id   | visit_date | people    |
+------+------------+-----------+
| 1    | 2017-01-01 | 10        |
| 2    | 2017-01-02 | 109       |
| 3    | 2017-01-03 | 150       |
| 4    | 2017-01-04 | 99        |
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-08 | 188       |
+------+------------+-----------+

For the sample data above, the output is:
+------+------------+-----------+
| id   | visit_date | people    |
+------+------------+-----------+
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-08 | 188       |
+------+------------+-----------+

Note:
Each day only have one row record, and the dates are increasing with id increasing.

********************************************************************************/


WITH ThreeDayPeople (id1, visit1, people1, id2, visit2, people2, id3, visit3, people3) AS
(
    SELECT *
    FROM stadium day1
    INNER JOIN stadium day2
    ON day1.id + 1 =  day2.id
    INNER JOIN stadium day3
    ON day1.id + 2 =  day3.id
    WHERE day1.people >= 100 
        AND day2.people >= 100 
        AND day3.people >= 100 
)

SELECT id1 AS id, visit1 AS visit_date, people1 AS people
FROM ThreeDayPeople
UNION
SELECT id2, visit2, people2
FROM ThreeDayPeople
UNION
SELECT id3, visit3, people3
FROM ThreeDayPeople

