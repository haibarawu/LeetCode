/****************************************************************************************************
626. Exchange Seats

Difficulty: Medium

https://leetcode.com/articles/exchange-seats/

Mary is a teacher in a middle school and she has a table seat storing students' names and their corresponding seat ids.
The column id is continuous increment.
Mary wants to change seats for the adjacent students.
Can you write a SQL query to output the result for Mary?

+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Abbot   |
|    2    | Doris   |
|    3    | Emerson |
|    4    | Green   |
|    5    | Jeames  |
+---------+---------+

For the sample input, the output is:

+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Doris   |
|    2    | Abbot   |
|    3    | Green   |
|    4    | Emerson |
|    5    | Jeames  |
+---------+---------+

Note:
If the number of students is odd, there is no need to change the last one's seat.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）判断 id 是否为偶数，如果是偶数，则 id - 1。
2）判断 id 是否为最后一位，如果是奇数，且为最后一位，则 id + 1。
****************************************************************************************************/

--Method1: 
DECLARE @count int 

SELECT @count = COUNT(id) 
FROM seat

SELECT
    (CASE
        WHEN (id % 2 = 1) AND id != @count THEN (id + 1)
        WHEN (id % 2 = 0) THEN (id - 1)
        ELSE id
    END) AS id,
    student
FROM seat
ORDER BY id


/****************************************************************************************************/


--Method2: 
SELECT IIF((id % 2 = 0), id - 1, IIF((id = (SELECT COUNT(id) FROM seat)), id, id + 1)) AS id, student
FROM seat
ORDER BY id


