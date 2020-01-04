/********************************************************************************
0182. Duplicate Emails

Difficulty: Easy

Write a SQL query to find all duplicate emails in a table named Person.
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+

For example, your query should return the following for the above table:
+---------+
| Email   |
+---------+
| a@b.com |
+---------+

Note: All emails are in lowercase.
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

