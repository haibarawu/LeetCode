/****************************************************************************************************
1205. Monthly Transactions II

Difficulty: Medium

Table: Transactions
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| id             | int     |
| country        | varchar |
| state          | enum    |
| amount         | int     |
| trans_date     | date    |
+----------------+---------+
id is the primary key of this table.
The table has information about incoming transactions.
The state column is an enum of type ["approved", "declined"].

Table: Chargebacks
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| trans_id       | int     |
| charge_date    | date    |
+----------------+---------+
Chargebacks contains basic information regarding incoming chargebacks from some transactions placed in Transactions table.
trans_id is a foreign key to the id column of Transactions table.
Each chargeback corresponds to a transaction made previously even if they were not approved.

Write an SQL query to find for each month and country, 
the number of approved transactions and their total amount, the number of chargebacks and their total amount.
Note: In your query, given the month and country, ignore rows with all zeros.

The query result format is in the following example:

Transactions table:
+------+---------+----------+--------+------------+
| id   | country | state    | amount | trans_date |
+------+---------+----------+--------+------------+
| 101  | US      | approved | 1000   | 2019-05-18 |
| 102  | US      | declined | 2000   | 2019-05-19 |
| 103  | US      | approved | 3000   | 2019-06-10 |
| 104  | US      | approved | 4000   | 2019-06-13 |
| 105  | US      | approved | 5000   | 2019-06-15 |
+------+---------+----------+--------+------------+

Chargebacks table:
+------------+------------+
| trans_id   | trans_date |
+------------+------------+
| 102        | 2019-05-29 |
| 101        | 2019-06-30 |
| 105        | 2019-09-18 |
+------------+------------+

Result table:
+----------+---------+----------------+-----------------+-------------------+--------------------+
| month    | country | approved_count | approved_amount | chargeback_count  | chargeback_amount  |
+----------+---------+----------------+-----------------+-------------------+--------------------+
| 2019-05  | US      | 1              | 1000            | 1                 | 2000               |
| 2019-06  | US      | 3              | 12000           | 1                 | 1000               |
| 2019-09  | US      | 0              | 0               | 1                 | 5000               |
+----------+---------+----------------+-----------------+-------------------+--------------------+

****************************************************************************************************/


--Method1: 
SELECT month, 
       country, 
       ISNULL(approved_count, 0) AS approved_count, 
       ISNULL(approved_amount, 0) AS approved_amount, 
       ISNULL(chargeback_count, 0) AS chargeback_count, 
       ISNULL(chargeback_amount, 0) AS chargeback_amount
FROM
(
  (
    SELECT FORMAT('yyyy-mm', t.trans_date) AS month,
           t.country,
           COUNT(t.id) AS approved_count,
           SUM(t.amount) AS approved_amount
    FROM Transactions AS t
    LEFT JOIN Chargebacks AS c
    ON t.id = c.trans_id
    WHERE state = 'approved'
    GROUP BY FORMAT('yyyy-mm', trans_date), country
  )
  FULL OUTER JOIN
  (
    SELECT FORMAT('yyyy-mm', c.trans_date) AS month,
           t.country,
           COUNT(t.id) AS chargeback_count,
           SUM(t.amount) AS chargeback_amount
    FROM Transactions AS t
    INNER JOIN Chargebacks AS c
    ON t.id = c.trans_id
    GROUP BY FORMAT('yyyy-mm', trans_date), country
  )
) AS result


/****************************************************************************************************/


--Method2: 
select month, country,
       sum(case when type='approved' then 1 else 0 end) as approved_count, 
       sum(case when type='approved' then amount else 0 end) as approved_amount, 
       sum(case when type='chargeback' then 1 else 0 end) as chargeback_count, 
       sum(case when type='chargeback' then amount else 0 end) as chargeback_amount
from (
  (
    select left(t.trans_date, 7) as month, t.country, amount, 'approved' as type
    from Transactions as t
    where state='approved'
  ) 
  union all 
  (
    select left(c.trans_date, 7) as month, t.country, amount, 'chargeback' as type
    from Transactions as t 
    join Chargebacks as c
    on t.id = c.trans_id
  )
) as tt
group by tt.month, tt.country


