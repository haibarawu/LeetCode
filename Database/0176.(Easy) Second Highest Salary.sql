/****************************************************************************************************
176. Second Highest Salary
176. 第二高的薪水

Difficulty: Easy

SQL Schema:
Create table If Not Exists Employee (Id int, Salary int)
Truncate table Employee
insert into Employee (Id, Salary) values ('1', '100')
insert into Employee (Id, Salary) values ('2', '200')
insert into Employee (Id, Salary) values ('3', '300')

Write a SQL query to get the second highest salary from the Employee table.
编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+

For example, given the above Employee table, the query should return 200 as the second highest salary. 
If there is no second highest salary, then the query should return null.
例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
****************************************************************************************************/


--Method1:
SELECT MAX(Salary) AS SecondHighestSalary 
FROM Employee 
WHERE Salary < (SELECT MAX(Salary) FROM Employee)


/****************************************************************************************************/


--Method2:
SELECT MAX(e1.Salary) AS SecondHighestSalary 
FROM Employee AS e1
WHERE 2 = (
    SELECT COUNT(DISTINCT e2.Salary)
    FROM Employee AS e2
    WHERE e2.Salary >= e1.Salary)


/****************************************************************************************************/


--Method3:
WITH salaryRank AS
(
    SELECT *, 
            DENSE_RANK() OVER(ORDER BY Salary DESC) AS rank
    FROM Employee
)

SELECT ISNULL(
    (SELECT DISTINCT Salary 
     FROM salaryRank
     WHERE rank = 2)
        , NULL) AS SecondHighestSalary


