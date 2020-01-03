/*
0177. Nth Highest Salary

Difficulty: Medium

Write a SQL query to get the nth highest salary from the Employee table.
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+

For example, given the above Employee table, the nth highest salary where n = 2 is 200. 
If there is no nth highest salary, then the query should return null.
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+

*/

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

