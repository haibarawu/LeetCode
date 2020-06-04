/****************************************************************************************************
175. Combine Two Tables
175. 组合两个表

Difficulty: Easy

Create table Person (PersonId int, FirstName varchar(255), LastName varchar(255))
Create table Address (AddressId int, PersonId int, City varchar(255), State varchar(255))
Truncate table Person
Insert into Person (PersonId, LastName, FirstName) values ('1', 'Wang', 'Allen')
Truncate table Address
Insert into Address (AddressId, PersonId, City, State) values ('1', '2', 'New York City', 'New York')

Table: Person
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId is the primary key column for this table.

Table: Address
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId is the primary key column for this table.

Write a SQL query for a report that provides the following information for each person in the Person table, 
regardless if there is an address for each of those people:
FirstName, LastName, City, State

编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：
FirstName, LastName, City, State
****************************************************************************************************/


SELECT FirstName, LastName, City, State
FROM Person AS p
LEFT JOIN Address AS a
ON p.PersonId = a.PersonId


