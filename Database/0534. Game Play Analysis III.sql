/****************************************************************************************************
534. Game Play Analysis III
534. 游戏玩法分析 III

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

Write an SQL query that reports for each player and date, how many games played so far by the player. 
That is, the total number of games played by the player until that date. Check the example for clarity.
编写一个 SQL 查询，同时报告每组玩家和日期，以及玩家到目前为止玩了多少游戏。
也就是说，在此日期之前玩家所玩的游戏总数。详细情况请查看示例。

The query result format is in the following example:

Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 1         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result table:
+-----------+------------+---------------------+
| player_id | event_date | games_played_so_far |
+-----------+------------+---------------------+
| 1         | 2016-03-01 | 5                   |
| 1         | 2016-05-02 | 11                  |
| 1         | 2017-06-25 | 12                  |
| 3         | 2016-03-02 | 0                   |
| 3         | 2018-07-03 | 5                   |
+-----------+------------+---------------------+
For the player with id 1, 5 + 6 = 11 games played by 2016-05-02, and 5 + 6 + 1 = 12 games played by 2017-06-25.
For the player with id 3, 0 + 5 = 5 games played by 2018-07-03.
Note that for each player we only care about the days when the player logged in.

对于 ID 为 1 的玩家，2016-05-02 共玩了 5+6=11 个游戏，2017-06-25 共玩了 5+6+1=12 个游戏。
对于 ID 为 3 的玩家，2018-07-03 共玩了 0+5=5 个游戏。
请注意，对于每个玩家，我们只关心玩家的登录日期。
****************************************************************************************************/


/****************************************************************************************************
解题思路：
进行自关联，关联条件为 play_id 相同，并且 a1 的日期要 大于等于 a2 的日期。
然后按照 a1 的 player_id 和 event_date 进行分组 GROUP BY，利用 SUM 来统计每个玩家到目前的 event_date 为止玩的游戏数量。
****************************************************************************************************/


SELECT a1.player_id, 
       a1.event_date, 
       SUM(a2.games_played) AS games_played_so_far
FROM Activity AS a1
INNER JOIN Activity AS a2
ON a1.player_id = a2.player_id
WHERE a1.event_date >= a2.event_date
GROUP BY a1.player_id, a1.event_date


