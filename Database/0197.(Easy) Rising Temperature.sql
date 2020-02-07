/****************************************************************************************************
197. Rising Temperature

Difficulty: Easy

Given a Weather table, 
write a SQL query to find all dates' Ids with higher temperature compared to its previous (yesterday's) dates.
+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+

For example, return the following Ids for the above Weather table:
+----+
| Id |
+----+
|  2 |
|  4 |
+----+
****************************************************************************************************/


--Method1:
SELECT w2.Id
FROM Weather AS w1
INNER JOIN Weather AS w2
ON w1.Temperature < w2.Temperature
AND DATEDIFF(day, w1.RecordDate, w2.RecordDate) = 1


--Method2:
/****************************************************************************************************
--This won't work, if the date is not consist. For example: 
+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2000-12-14 |                3 |
|       2 |       2000-12-16 |                5 |
+---------+------------------+------------------+
****************************************************************************************************/
SELECT Id
FROM (
SELECT Id,
       RecordDate,
       Temperature,
       LAG(Temperature) OVER(ORDER BY RecordDate) AS TemperaturePerviousDay
FROM Weather
) AS lag
WHERE Temperature > TemperaturePerviousDay


