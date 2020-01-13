/********************************************************************************
603. Consecutive Available Seats

Difficulty: Easy

Several friends at a cinema ticket office would like to reserve consecutive available seats.
Can you help to query all the consecutive available seats order by the seat_id using the following cinema table?

| seat_id | free |
|---------|------|
| 1       | 1    |
| 2       | 0    |
| 3       | 1    |
| 4       | 1    |
| 5       | 1    |

Your query should return the following result for the sample case above.
| seat_id |
|---------|
| 3       |
| 4       |
| 5       |

Note:
The seat_id is an auto increment int, and free is bool ('1' means free, and '0' means occupied.).
Consecutive available seats are more than 2(inclusive) seats consecutively available.
********************************************************************************/


--Method1: 
SELECT DISTINCT A.seat_id
FROM cinema AS A
INNER JOIN cinema AS B
ON ABS(A.seat_id - B.seat_id) = 1
AND A.free = 1 AND B.free = 1
ORDER BY A.seat_id


--Method2: 
WITH seats (id1, id2) AS
(
SELECT A.seat_id, B.seat_id
FROM cinema AS A
INNER JOIN cinema AS B
ON A.seat_id - B.seat_id = 1
AND A.free = 1 AND B.free = 1
)

SELECT id1 AS seat_id
FROM seats
UNION
SELECT id2
FROM seats

