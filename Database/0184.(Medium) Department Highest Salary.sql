/****************************************************************************************************
184. Department Highest Salary
184. 部门工资最高的员工

Difficulty: Medium

SQL Schema:
Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, DepartmentId int)
Create table If Not Exists Department (Id int, Name varchar(255))
Truncate table Employee
insert into Employee (Id, Name, Salary, DepartmentId) values ('1', 'Joe', '70000', '1')
insert into Employee (Id, Name, Salary, DepartmentId) values ('2', 'Jim', '90000', '1')
insert into Employee (Id, Name, Salary, DepartmentId) values ('3', 'Henry', '80000', '2')
insert into Employee (Id, Name, Salary, DepartmentId) values ('4', 'Sam', '60000', '2')
insert into Employee (Id, Name, Salary, DepartmentId) values ('5', 'Max', '90000', '1')
Truncate table Department
insert into Department (Id, Name) values ('1', 'IT')
insert into Department (Id, Name) values ('2', 'Sales')

The Employee table holds all employees. Every employee has an Id, a salary, and there is also a column for the department Id.
Employee 表包含所有员工信息，每个员工有其对应的 Id, salary 和 department Id。
+----+-------+--------+--------------+
| Id | Name  | Salary | DepartmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+

The Department table holds all departments of the company.
Department 表包含公司所有部门的信息。
+----+----------+
| Id | Name     |
+----+----------+
| 1  | IT       |
| 2  | Sales    |
+----+----------+

Write a SQL query to find employees who have the highest salary in each of the departments. 
For the above tables, your SQL query should return the following rows (order of rows does not matter).
编写一个 SQL 查询，找出每个部门工资最高的员工。
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
+------------+----------+--------+

Explanation:
Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.
例如，根据上述给定的表格，Max 和 Jim 在 IT 部门有最高工资，Henry 在 Sales 部门有最高工资。
****************************************************************************************************/


--Method1: 
SELECT Department,
       Employee,
       Salary
FROM (
  SELECT e.Name AS Employee,
         e.Salary, 
         d.Name AS Department, 
         DENSE_RANK() OVER(PARTITION BY DepartmentId ORDER BY Salary DESC) AS Rank
  FROM Employee AS e
  INNER JOIN Department AS d
  ON e.DepartmentId = d.Id
) AS rank
WHERE Rank = 1
ORDER BY Department


--Method2:
SELECT
    Department.name AS 'Department',
    Employee.name AS 'Employee',
    Salary
FROM
    Employee
    JOIN
    Department 
    ON Employee.DepartmentId = Department.Id
WHERE
    (Employee.DepartmentId , Salary) IN (   
        SELECT
            DepartmentId, MAX(Salary)
        FROM
            Employee
        GROUP BY DepartmentId
	)
  

