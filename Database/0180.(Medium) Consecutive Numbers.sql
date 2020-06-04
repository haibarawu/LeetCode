/****************************************************************************************************
180. Consecutive Numbers
180. 连续出现的数字

Difficulty: Medium

Write a SQL query to find all numbers that appear at least three times consecutively.
编写一个 SQL 查询，查找所有至少连续出现三次的数字。

SQL 架构：
Create table If Not Exists Logs (Id int, Num int)
Truncate table Logs
insert into Logs (Id, Num) values ('1', '1')
insert into Logs (Id, Num) values ('2', '1')
insert into Logs (Id, Num) values ('3', '1')
insert into Logs (Id, Num) values ('4', '2')
insert into Logs (Id, Num) values ('5', '1')
insert into Logs (Id, Num) values ('6', '2')
insert into Logs (Id, Num) values ('7', '2')

+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+

For example, given the above Logs table, 
1 is the only number that appears consecutively for at least three times.
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+

****************************************************************************************************/


--Method1:
SELECT DISTINCT Num AS ConsecutiveNums
FROM (
    SELECT Num,
            LAG(Num, 1) OVER(ORDER BY Id) AS lag,
            LEAD(Num, 1) OVER(ORDER BY Id) AS lead
    FROM Logs) AS l
WHERE Num = lag AND Num = lead


/****************************************************************************************************/


--Method2:
SELECT DISTINCT l1.Num AS ConsecutiveNums
FROM Logs AS l1, Logs AS l2, Logs AS l3
WHERE l1.Id = l2.Id - 1 AND l2.Id = l3.Id - 1
AND l1.Num = l2.Num AND l2.Num = l3.Num


