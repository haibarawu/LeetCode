/********************************************************************************
602. Friend Requests II: Who Has Most Friend?

Difficulty: Medium

In social network like Facebook or Twitter, people send friend requests and accept others' requests as well.

Table request_accepted
+--------------+-------------+------------+
| requester_id | accepter_id | accept_date|
|--------------|-------------|------------|
| 1            | 2           | 2016_06-03 |
| 1            | 3           | 2016-06-08 |
| 2            | 3           | 2016-06-08 |
| 3            | 4           | 2016-06-09 |
+--------------+-------------+------------+
This table holds the data of friend acceptance, while requester_id and accepter_id both are the id of a person.
 
Write a query to find the the people who has most friends and the most friends number under the following rules:

It is guaranteed there is only 1 people having the most friends.
The friend request could only been accepted once, which mean there is no multiple records with the same requester_id and accepter_id value.

For the sample data above, the result is:

Result table:
+------+------+
| id   | num  |
|------|------|
| 3    | 3    |
+------+------+
The person with id '3' is a friend of people '1', '2' and '4', so he has 3 friends in total, which is the most number than any others.
********************************************************************************/

 
SELECT TOP 1 id, COUNT(id) AS num
FROM
(
SELECT requester_id AS id
FROM request_accepted
UNION ALL
SELECT accepter_id AS id
FROM request_accepted
) AS count
GROUP BY id
ORDER BY num DESC


/********************************************************************************
Follow-up:
In the real world, multiple people could have the same most number of friends, can you find all these people in this case?
********************************************************************************/


SELECT id, COUNT(id) AS num
FROM
(
  SELECT requester_id AS id
  FROM request_accepted
  UNION ALL
  SELECT accepter_id AS id
  FROM request_accepted
) AS count
GROUP BY id
HAVING COUNT(id) = 
(
  SELECT TOP 1 COUNT(id) AS num
  FROM
  (
    SELECT requester_id AS id
    FROM request_accepted
    UNION ALL
    SELECT accepter_id AS id
    FROM request_accepted
  ) AS counts
  GROUP BY id
  ORDER BY num DESC
)

