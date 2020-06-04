/****************************************************************************************************
626. Exchange Seats
626. 换座位

Difficulty: Medium

https://leetcode.com/articles/exchange-seats/

Mary is a teacher in a middle school and she has a table seat storing students' names and their corresponding seat ids.
The column id is continuous increment.
Mary wants to change seats for the adjacent students.
Can you write a SQL query to output the result for Mary?
小美是一所中学的信息科技老师，她有一张 seat 座位表，平时用来储存学生名字和与他们相对应的座位 id。
其中纵列的 id 是连续递增的
小美想改变相邻俩学生的座位。
你能不能帮她写一个 SQL query 来输出小美想要的结果呢？

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
注意：
如果学生人数是奇数，则不需要改变最后一个同学的座位。
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）判断 id 是否为偶数，如果是偶数，则 id - 1。
2）判断 id 是否为最后一位，如果是奇数，且为最后一位，则 id + 1。
****************************************************************************************************/

--Method1:
SELECT
    (CASE
        WHEN (id % 2 = 0) THEN (id - 1)
        WHEN (id % 2 = 1) AND id != (SELECT COUNT(id) FROM seat) THEN (id + 1)
        ELSE id
    END) AS id,
    student
FROM seat
ORDER BY id


/****************************************************************************************************/


--Method2: 
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


--Method3: 
SELECT IIF((id % 2 = 0), id - 1, IIF((id = (SELECT COUNT(id) FROM seat)), id, id + 1)) AS id, student
FROM seat
ORDER BY id


