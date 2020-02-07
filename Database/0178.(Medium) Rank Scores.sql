/****************************************************************************************************
0178. Rank Scores

Difficulty: Medium

Write a SQL query to rank scores. 
If there is a tie between two scores, both should have the same ranking. 
Note that after a tie, the next ranking number should be the next consecutive integer value. 
In other words, there should be no "holes" between ranks.
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


