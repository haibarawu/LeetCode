/****************************************
183. Customers Who Never Order
183. 从不订购的客户

Difficulty: Easy

Suppose that a website contains two tables, the Customers table and the Orders table. 
Write a SQL query to find all customers who never order anything.
某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。

SQL Schema:
Create table If Not Exists Customers (Id int, Name varchar(255))
Create table If Not Exists Orders (Id int, CustomerId int)
Truncate table Customers
insert into Customers (Id, Name) values ('1', 'Joe')
insert into Customers (Id, Name) values ('2', 'Henry')
insert into Customers (Id, Name) values ('3', 'Sam')
insert into Customers (Id, Name) values ('4', 'Max')
Truncate table Orders
insert into Orders (Id, CustomerId) values ('1', '3')
insert into Orders (Id, CustomerId) values ('2', '1')

Table: Customers.
+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+

Table: Orders.
+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+

Using the above tables as example, return the following:
例如给定上述表格，你的查询应返回：
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
****************************************/


--Method1:
SELECT c.Name AS 'Customers'
FROM Customers AS c
LEFT JOIN Orders AS o
ON c.Id = o.CustomerId
WHERE o.CustomerId IS NULL


--Method2:
SELECT Name AS Customers
FROM Customers
WHERE Customers.Id NOT IN (
    SELECT CustomerId 
    FROM Orders
)


