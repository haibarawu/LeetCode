/****************************************************************************************************
178. Rank Scores
178. 分数排名

Difficulty: Medium

Write a SQL query to rank scores. 
If there is a tie between two scores, both should have the same ranking. 
Note that after a tie, the next ranking number should be the next consecutive integer value. 
In other words, there should be no "holes" between ranks.

编写一个 SQL 查询来实现分数排名。
如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。
换句话说，名次之间不应该有“间隔”。

SQL 架构：
Create table If Not Exists Scores (Id int, Score DECIMAL(3,2))
Truncate table Scores
insert into Scores (Id, Score) values ('1', '3.5')
insert into Scores (Id, Score) values ('2', '3.65')
insert into Scores (Id, Score) values ('3', '4.0')
insert into Scores (Id, Score) values ('4', '3.85')
insert into Scores (Id, Score) values ('5', '4.0')
insert into Scores (Id, Score) values ('6', '3.65')

+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+

For example, given the above Scores table, your query should generate the following report (order by highest score):
例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：
+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
****************************************************************************************************/


--Method1: 
SELECT Score,
        DENSE_RANK() OVER(ORDER BY Score DESC) AS Rank
FROM Scores
ORDER BY Score DESC
--(ORDER BY Rank) 也可以


/****************************************************************************************************/


--Method2:
/****************************************************************************************************
解题思路：
1）DISTINCT s2.score 表示查询出不重复的score, 
2）COUNT(DISTINCT s2.score) 意思是统计出不重复的score的总个数。
3）s2.score >= s1.score s1表中成绩大于等于 s2表中的成绩。 
4）ORDER BY Score DESC 意思是按照成绩降序排列。
****************************************************************************************************/


SELECT Score,
        (SELECT COUNT(DISTINCT s2.Score)
         FROM Scores AS s2
         WHERE s2.Score >= s1.Score) AS Rank
FROM Scores AS s1
ORDER BY Score DESC


