/****************************************************************************************************
1127. User Purchase Platform

Difficulty: Hard

Table: Spending
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| spend_date  | date    |
| platform    | enum    | 
| amount      | int     |
+-------------+---------+
The table logs the spendings history of users that make purchases from an online shopping website which has a desktop and a mobile application.
(user_id, spend_date, platform) is the primary key of this table.
The platform column is an ENUM type of ('desktop', 'mobile').

Write an SQL query to find the total number of users 
and the total amount spent using mobile only, desktop only and both mobile and desktop together for each date.

The query result format is in the following example:

Spending table:
+---------+------------+----------+--------+
| user_id | spend_date | platform | amount |
+---------+------------+----------+--------+
| 1       | 2019-07-01 | mobile   | 100    |
| 1       | 2019-07-01 | desktop  | 100    |
| 2       | 2019-07-01 | mobile   | 100    |
| 2       | 2019-07-02 | mobile   | 100    |
| 3       | 2019-07-01 | desktop  | 100    |
| 3       | 2019-07-02 | desktop  | 100    |
+---------+------------+----------+--------+

Result table:
+------------+----------+--------------+-------------+
| spend_date | platform | total_amount | total_users |
+------------+----------+--------------+-------------+
| 2019-07-01 | desktop  | 100          | 1           |
| 2019-07-01 | mobile   | 100          | 1           |
| 2019-07-01 | both     | 200          | 1           |
| 2019-07-02 | desktop  | 100          | 1           |
| 2019-07-02 | mobile   | 100          | 1           |
| 2019-07-02 | both     | 0            | 0           |
+------------+----------+--------------+-------------+ 

On 2019-07-01, 
user 1 purchased using both desktop and mobile, 
user 2 purchased using mobile only 
and user 3 purchased using desktop only.
On 2019-07-02, 
user 2 purchased using mobile only, 
user 3 purchased using desktop only 
and no one purchased using both platforms.

****************************************************************************************************/


/****************************************************************************************************
******************** TABLE DDL & DML ********************
DROP TABLE IF EXISTS Spending;

CREATE TABLE Spending (
  user_id INT,
  spend_date DATE,
  platform VARCHAR(255),
  amount INT
) ;

INSERT INTO Spending ( user_id, spend_date, platform, amount ) VALUES (1, '2019-07-01', 'mobile', 100) ; 
INSERT INTO Spending ( user_id, spend_date, platform, amount ) VALUES (1, '2019-07-01', 'desktop', 100) ; 
INSERT INTO Spending ( user_id, spend_date, platform, amount ) VALUES (2, '2019-07-01', 'mobile', 100) ; 
INSERT INTO Spending ( user_id, spend_date, platform, amount ) VALUES (2, '2019-07-02', 'mobile', 100) ; 
INSERT INTO Spending ( user_id, spend_date, platform, amount ) VALUES (3, '2019-07-01', 'desktop', 100) ; 
INSERT INTO Spending ( user_id, spend_date, platform, amount ) VALUES (3, '2019-07-02', 'desktop', 100) ;  

****************************************************************************************************/


SELECT t1.spend_date, 
       t1.platform,
       ISNULL(total_amount, 0) AS total_amount,
       ISNULL(total_users, 0) AS total_users
FROM
-- display all 3 platform with each date
(
  SELECT DISTINCT spend_date, 
                  p.platform
  FROM Spending 
  JOIN (
          SELECT 'desktop' AS platform UNION
          SELECT 'mobile' AS platform UNION
          SELECT 'both' AS platform
       ) AS p
) AS t1

LEFT JOIN
-- display total_amount and user_counts for each date and platform. 
(
  SELECT spend_date, 
         platform, 
         SUM(amount) AS total_amount,
         COUNT(user_id) AS total_users
  FROM
  (
    SELECT spend_date, 
           user_id, 
           (CASE COUNT(DISTINCT platform) 
            WHEN 1 THEN platform
            WHEN 2 THEN 'both' END) AS platform,
           SUM(amount) AS amount
    FROM Spending
    GROUP BY spend_date, user_id
  ) AS p
  GROUP BY spend_date, platform
) AS t2

ON t1.spend_date = t2.spend_date
   AND t1.platform = t2.platform

