/****************************************************************************************************
569. Median Employee Salary
569. 员工薪水中位数

Difficulty: Hard

The Employee table holds all employees. 
The employee table has three columns: Employee Id, Company Name, and Salary.
Employee 表包含所有员工。Employee 表有三列：员工Id，公司名和薪水。
+-----+------------+--------+
|Id   | Company    | Salary |
+-----+------------+--------+
|1    | A          | 2341   |
|2    | A          | 341    |
|3    | A          | 15     |
|4    | A          | 15314  |
|5    | A          | 451    |
|6    | A          | 513    |
|7    | B          | 15     |
|8    | B          | 13     |
|9    | B          | 1154   |
|10   | B          | 1345   |
|11   | B          | 1221   |
|12   | B          | 234    |
|13   | C          | 2345   |
|14   | C          | 2645   |
|15   | C          | 2645   |
|16   | C          | 2652   |
|17   | C          | 65     |
+-----+------------+--------+

Write a SQL query to find the median salary of each company. 
请编写SQL查询来查找每个公司的薪水中位数。

Bonus points if you can solve it without using any built-in SQL functions.
挑战点：你是否可以在不使用任何内置的SQL函数的情况下解决此问题。
+-----+------------+--------+
|Id   | Company    | Salary |
+-----+------------+--------+
|5    | A          | 451    |
|6    | A          | 513    |
|12   | B          | 234    |
|9    | B          | 1154   |
|14   | C          | 2645   |
+-----+------------+--------+
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）每个 company 中对 salary 排序 rank。
2）找出每个 company 的 rank的中间数 avg_rank。
3）找出与中间数 avg_rank 相差不到 1 的 rank。（如果总个数为奇数，则中位数就为中间的数；如果总个数为偶数，则中位数为中间的两个数。）
****************************************************************************************************/


WITH ranks AS (
  SELECT
  Id, Company, Salary,
  1.0 * ROW_NUMBER() OVER(PARTITION BY Company ORDER BY Salary) AS rank
  FROM Employee
),
avg_ranks AS (
  SELECT Company, AVG(rank) AS avg_rank
  FROM ranks
  GROUP BY Company
)

SELECT r.Id, r.Company, r.Salary
FROM ranks AS r, avg_ranks AS a
WHERE r.Company = a.Company AND ABS(r.rank - a.avg_rank) < 1


