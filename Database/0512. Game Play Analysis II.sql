/****************************************************************************************************
512. Game Play Analysis II
512. 游戏玩法分析 II

Difficulty: Easy

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

Write a SQL query that reports the device that is first logged in for each player.
描述每一个玩家首次登陆的设备名称

The query result format is in the following example:

Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result table:
+-----------+-----------+
| player_id | device_id |
+-----------+-----------+
| 1         | 2         |
| 2         | 3         |
| 3         | 1         |
+-----------+-----------+
****************************************************************************************************/


/****************************************************************************************************
解题思路：
在 511 题中已经得到了用户第一次登录的时间 query01。
只需要 query01 与原始表 Activity 相关联，使 用户ID 和 登录时间 匹配，即可得到第一次登录平台使用的设备 device_id。
****************************************************************************************************/


--Method01:
SELECT DISTINCT player_id, device_id
FROM Activity
WHERE (player_id, event_date) IN (
  SELECT player_id, MIN(event_date) AS first_login
  FROM Activity
  GROUP BY player_id
)


/****************************************************************************************************/


--Method02:
SELECT a.player_id, a.device_id
FROM Activity AS a,
(	SELECT b.player_id, MIN(b.event_date) AS first_login
	FROM Activity AS b
	GROUP BY b.player_id
) AS f
WHERE a.player_id = f.player_id
AND a.event_date = f.first_login


