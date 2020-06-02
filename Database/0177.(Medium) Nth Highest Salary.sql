/****************************************************************************************************
177. Nth Highest Salary
177. 第 N 高的薪水

Difficulty: Medium

Write a SQL query to get the nth highest salary from the Employee table.
编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+

SQL 架构：
Create table If Not Exists Employee (Id int, Salary int)
Truncate table Employee
insert into Employee (Id, Salary) values ('1', '100')
insert into Employee (Id, Salary) values ('2', '200')
insert into Employee (Id, Salary) values ('3', '300')

For example, given the above Employee table, the nth highest salary where n = 2 is 200. 
If there is no nth highest salary, then the query should return null.
例如上述 Employee 表，n = 2 时，应返回第二高的薪水 200。如果不存在第 n 高的薪水，那么查询应返回 null。
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+

****************************************************************************************************/


-- Method1:
CREATE FUNCTION getNthHighestSalary(@N INT) RETURNS INT AS
BEGIN
    RETURN (
        /* Write your T-SQL query statement below. */
        SELECT ISNULL(
            (SELECT DISTINCT Salary
             FROM (SELECT Salary, 
                          DENSE_RANK() OVER(ORDER BY Salary DESC) AS rank
                   FROM Employee) AS r
             WHERE r.rank = @N)
                , NULL) AS NthHighestSalary
    );
END


/****************************************************************************************************/


--Method2:
CREATE FUNCTION getNthHighestSalary(@N INT) RETURNS INT AS
BEGIN
    RETURN (
        /* Write your T-SQL query statement below. */
        SELECT MAX(Salary) AS NthHighestSalary
        FROM Employee AS E1
        WHERE @N = 
            (SELECT COUNT(DISTINCT(E2.Salary))
                FROM Employee AS E2
                WHERE E2.Salary >= E1.Salary)
    );
END


/****************************************************************************************************/


--Method3:
CREATE FUNCTION getNthHighestSalary(@N INT) RETURNS INT AS
BEGIN
    RETURN (
        /* Write your T-SQL query statement below. */
        SELECT ISNULL(
          (
            SELECT Salary
            FROM Employee
            ORDER BY Salary DESC
            OFFSET (@N - 1) ROWS 
            FETCH NEXT 1 ROWS ONLY
          ), NULL)
            AS NthHighestSalary
    );
END


