/********************************************************************************
612. Shortest Distance in a Plane

Difficulty: Medium

Table point_2d holds the coordinates (x,y) of some unique points (more than two) in a plane.
Write a query to find the shortest distance between these points rounded to 2 decimals.

| x  | y  |
|----|----|
| -1 | -1 |
| 0  | 0  |
| -1 | -2 |

The shortest distance is 1.00 from point (-1,-1) to (-1,2). So the output should be:
| shortest |
|----------|
| 1.00     |

Note: The longest distance among all the points are less than 10000.
********************************************************************************/

/********************************************************************************
Approach:
distance = Sqrt((x1 - x2)^2 + (y1 - y2)^2)
To put the MIN() inside of SQRT() will slightly improve the performance.
********************************************************************************/

SELECT ROUND(MIN(SQRT(POW(p1.x - p2.x, 2) + POW(p1.y - p2.y, 2))), 2) AS shortest
FROM point_2d AS p1
JOIN point_2d AS p2
ON !(p1.x = p2.x AND p1.y = p2.y)

