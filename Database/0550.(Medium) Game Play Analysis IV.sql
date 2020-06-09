/****************************************************************************************************
550. Game Play Analysis IV
550. 游戏玩法分析 IV

Difficulty: Medium

Table: Activity
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
(player_id, event_date) is the primary key of this table.
This table shows the activity of players of some game.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on some day using some device.
表的主键是 (player_id, event_date)。
这张表展示了一些游戏玩家在游戏平台上的行为活动。
每行数据记录了一名玩家在退出平台之前，当天使用同一台设备登录平台后打开的游戏的数目（可能是 0 个）。

Write an SQL query that reports the fraction of players that logged in again on the day after the day they first logged in, rounded to 2 decimal places. 
In other words, you need to count the number of players that logged in for at least two consecutive days starting from their first login date, 
then divide that number by the total number of players.
编写一个 SQL 查询，报告在首次登录的第二天再次登录的玩家的分数，四舍五入到小数点后两位。
换句话说，您需要计算从首次登录日期开始至少连续两天登录的玩家的数量，然后除以玩家总数。

The query result format is in the following example:

Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result table:
+-----------+
| fraction  |
+-----------+
| 0.33      |
+-----------+
Only the player with id 1 logged back in after the first day he had logged in so the answer is 1/3 = 0.33
只有 ID 为 1 的玩家在第一天登录后才重新登录，所以答案是 1/3 = 0.33
****************************************************************************************************/


/****************************************************************************************************
解题思路：
在 511 题中已经得到了用户第一次登录的时间 query01。
根据题意我们要去查询用户在第一次登录的第二天的登录情况和第二天的游戏分数。
只需要 query01 与原始表 Activity 相关联，使 <用户ID>相同 和 <登录时间>相差一天 匹配，得到要查询的目标ID。
再套用一层 COUNT() 来分别统计 第二天也登录了的人数 / 总人数，两者相除，去小数点后两位来得到结果。
****************************************************************************************************/


SELECT ROUND(COUNT(B.player_id) / COUNT(A.player_id) , 2) AS fraction
FROM
(
  SELECT player_id, MIN(event_date) AS event_date
  FROM Activity
  GROUP BY player_id
) AS A
LEFT JOIN Activity AS B
ON A.player_id = B.player_id
AND DATEDIFF(B.event_date, A.event_date) = 1


