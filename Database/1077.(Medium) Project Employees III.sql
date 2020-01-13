/********************************************************************************
1077. Project Employees III

Difficulty: Medium

https://leetcode.com/problems/project-employees-iii/

Table: Project
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
+-------------+---------+
(project_id, employee_id) is the primary key of this table.
employee_id is a foreign key to Employee table.

Table: Employee
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |
+------------------+---------+
employee_id is the primary key of this table.

Write an SQL query that reports the most experienced employees in each project. 
In case of a tie, report all employees with the maximum number of experience years.

The query result format is in the following example:

Project table:
+-------------+-------------+
| project_id  | employee_id |
+-------------+-------------+
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |
+-------------+-------------+

Employee table:
+-------------+--------+------------------+
| employee_id | name   | experience_years |
+-------------+--------+------------------+
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 3                |
| 4           | Doe    | 2                |
+-------------+--------+------------------+

Result table:
+-------------+---------------+
| project_id  | employee_id   |
+-------------+---------------+
| 1           | 1             |
| 1           | 3             |
| 2           | 1             |
+-------------+---------------+
Both employees with id 1 and 3 have the most experience among the employees of the first project. 
For the second project, the employee with id 1 has the most experience.

********************************************************************************/


--Method1:
SELECT P.project_id, P.employee_id
FROM Project AS P
LEFT JOIN Employee AS E
ON P.employee_id = E.employee_id
LEFT JOIN
(
  SELECT P.project_id, MAX(E.experience_years) AS max_year
  FROM Project AS P
  LEFT JOIN Employee AS E
  ON P.employee_id = E.employee_id
  GROUP BY P.project_id
) AS M
ON P.project_id = M.project_id AND E.experience_years = M.max_year


--Method2:
SELECT p.project_id, e.employee_id
FROM Project p 
LEFT JOIN Employee e 
ON p.employee_id = e.employee_id
WHERE (p.project_id, e.experience_years) IN 
(
  SELECT p.project_id, MAX(e.experience_years)
  FROM Project p 
  LEFT JOIN Employee e 
  ON p.employee_id = e.employee_id
  GROUP BY p.project_id
)

