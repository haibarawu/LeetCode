/****************************************************************************************************
197. Rising Temperature
197. 上升的温度

Difficulty: Easy

SQL Schema:
Create table If Not Exists Weather (Id int, RecordDate date, Temperature int)
Truncate table Weather
insert into Weather (Id, RecordDate, Temperature) values ('1', '2015-01-01', '10')
insert into Weather (Id, RecordDate, Temperature) values ('2', '2015-01-02', '25')
insert into Weather (Id, RecordDate, Temperature) values ('3', '2015-01-03', '20')
insert into Weather (Id, RecordDate, Temperature) values ('4', '2015-01-04', '30')

Given a Weather table, 
write a SQL query to find all dates' Ids with higher temperature compared to its previous (yesterday's) dates.
给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+

For example, return the following Ids for the above Weather table:
例如，根据上述给定的 Weather 表格，返回如下 Id:
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
SELECT w2.Id
FROM Weather AS w1, Weather AS w2
WHERE w1.Temperature < w2.Temperature
AND DATEDIFF(day, w1.RecordDate, w2.RecordDate) = 1


--Method3:
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


