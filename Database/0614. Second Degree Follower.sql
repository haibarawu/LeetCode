/********************************************************************************
614. Second Degree Follower

Difficulty: Medium

In facebook, there is a follow table with two columns: followee, follower.
Please write a sql query to get the amount of each follower’s follower if he/she has one.

For example:
+-------------+------------+
| followee    | follower   |
+-------------+------------+
|     A       |     B      |
|     B       |     C      |
|     B       |     D      |
|     D       |     E      |
+-------------+------------+

should output:
+-------------+------------+
| follower    | num        |
+-------------+------------+
|     B       |  2         |
|     D       |  1         |
+-------------+------------+

Explaination:
Both B and D exist in the follower list, when as a followee, B's follower is C and D, and D's follower is E. 
A does not exist in follower list.

Note:
Followee would not follow himself/herself in all cases.
Please display the result in follower's alphabet order. 
********************************************************************************/


CREATE TABLE IF NOT EXISTS follow (followee varchar(255), follower varchar(255))
TRUNCATE TABLE follow
INSERT INTO follow (followee, follower) VALUES ('A', 'B')
INSERT INTO follow (followee, follower) VALUES ('B', 'C')
INSERT INTO follow (followee, follower) VALUES ('B', 'D')
INSERT INTO follow (followee, follower) VALUES ('D', 'E')


SELECT DISTINCT f.follower, n.num
FROM follow AS f
INNER JION
(
SELECT followee, COUNT(DISTINCT follower) AS num
FROM follow
GROUP BY followee
) AS n
ON f.follower = n.followee
ORDER BY follower

