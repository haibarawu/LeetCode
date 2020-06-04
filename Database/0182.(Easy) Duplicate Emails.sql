/********************************************************************************
182. Duplicate Emails
182. 查找重复的电子邮箱

Difficulty: Easy

Write a SQL query to find all duplicate emails in a table named Person.
编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+

SQL Schema:
Create table If Not Exists Person (Id int, Email varchar(255))
Truncate table Person
insert into Person (Id, Email) values ('1', 'a@b.com')
insert into Person (Id, Email) values ('2', 'c@d.com')
insert into Person (Id, Email) values ('3', 'a@b.com')

For example, your query should return the following for the above table:
根据以上输入，你的查询应返回以下结果：
+---------+
| Email   |
+---------+
| a@b.com |
+---------+

Note: All emails are in lowercase.
说明：所有电子邮箱都是小写字母。
********************************************************************************/


--Method1:
WITH find_dups AS (
    SELECT Email, ROW_NUMBER() OVER(PARTITION BY Email ORDER BY Email) AS Dups
    FROM Person
)

SELECT DISTINCT Email
FROM find_dups
WHERE Dups > 1


--Method2:
SELECT Email
FROM Person
GROUP BY Email
HAVING COUNT(Email) > 1


