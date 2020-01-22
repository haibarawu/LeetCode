/****************************************************************************************************
1179. Reformat Department Table

Difficulty: Easy

Table: Department
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| revenue       | int     |
| month         | varchar |
+---------------+---------+
(id, month) is the primary key of this table.
The table has information about the revenue of each department per month.
The month has values in ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"].

Write an SQL query to reformat the table such that there is a department id column and a revenue column for each month.

The query result format is in the following example:

Department table:
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+

Result table:
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+

Note that the result table has 13 columns (1 for the department id + 12 for the months).

****************************************************************************************************/


SELECT
    id,
    SUM(IIF(month = 'Jan', revenue, null)) AS Jan_Revenue,
    SUM(IIF(month = 'Feb', revenue, null)) AS Feb_Revenue,
    SUM(IIF(month = 'Mar', revenue, null)) AS Mar_Revenue,
    SUM(IIF(month = 'Apr', revenue, null)) AS Apr_Revenue,
    SUM(IIF(month = 'May', revenue, null)) AS May_Revenue,
    SUM(IIF(month = 'Jun', revenue, null)) AS Jun_Revenue,
    SUM(IIF(month = 'Jul', revenue, null)) AS Jul_Revenue,
    SUM(IIF(month = 'Aug', revenue, null)) AS Aug_Revenue,
    SUM(IIF(month = 'Sep', revenue, null)) AS Sep_Revenue,
    SUM(IIF(month = 'Oct', revenue, null)) AS Oct_Revenue,
    SUM(IIF(month = 'Nov', revenue, null)) AS Nov_Revenue,
    SUM(IIF(month = 'Dec', revenue, null)) AS Dec_Revenue
FROM Department
GROUP BY id

