/****************************************************************************************************
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

****************************************************************************************************/


--Method1: 
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


/****************************************************************************************************/


--Method2: 
/****************************************************************************************************
解题思路：
1、查询人流程量>=100的记录。
2、按ID或日期顺序使用row_number得到顺序号。
3、用ID或日期 减 顺序号，连续的记录与顺序号相减后结果值相同。
4、统计结果值相同的记录个数。
5、where 连续个数>=3 的记录。
****************************************************************************************************/


SELECT id, visit_date, people
FROM
(
    SELECT id, visit_date, people, 
            COUNT(diff) OVER(PARTITION BY diff) AS ct
    FROM
    (
        SELECT id, visit_date, people, (id - rank) AS diff
        FROM
        (
            SELECT id, visit_date, people, 
                    ROW_NUMBER() OVER(ORDER BY visit_date) AS rank
            FROM stadium
            WHERE people >= 100
        ) AS r
    ) AS rr
) AS rrr
WHERE ct >= 3


/****************************************************************************************************/


--Method3:
WITH ThreeDayPeople AS
(
    SELECT day1.id
    FROM stadium AS day1
    INNER JOIN stadium AS day2
    ON day1.id + 1 =  day2.id
    INNER JOIN stadium AS day3
    ON day1.id + 2 =  day3.id
    WHERE day1.people >= 100 
        AND day2.people >= 100 
        AND day3.people >= 100 
)

SELECT id, visit_date, people
FROM stadium
WHERE id IN (SELECT id FROM ThreeDayPeople)
OR id - 1 IN (SELECT id FROM ThreeDayPeople)
OR id - 2 IN (SELECT id FROM ThreeDayPeople)


