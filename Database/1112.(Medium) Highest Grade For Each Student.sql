/********************************************************************************
1112. Highest Grade For Each Student

Difficulty: Medium

Table: Enrollments
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| course_id     | int     |
| grade         | int     |
+---------------+---------+
(student_id, course_id) is the primary key of this table.

Write a SQL query to find the highest grade with its corresponding course for each student. 
In case of a tie, you should find the course with the smallest course_id. 
The output must be sorted by increasing student_id.

The query result format is in the following example:

Enrollments table:
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 2          | 2         | 95    |
| 2          | 3         | 95    |
| 1          | 1         | 90    |
| 1          | 2         | 99    |
| 3          | 1         | 80    |
| 3          | 2         | 75    |
| 3          | 3         | 82    |
+------------+-----------+-------+

Result table:
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 1          | 2         | 99    |
| 2          | 2         | 95    |
| 3          | 3         | 82    |
+------------+-----------+-------+
********************************************************************************/


--Method1:
SELECT e. student_id, MIN(e. course_id) AS course_id, e. grade
FROM Enrollments AS e
INNER JOIN
(
  SELECT student_id, MAX(grade) AS max_grade
  FROM Enrollments
  GROUP BY student_id
) AS max
ON e.student_id = max.student_id AND e.grade = max.max_grade
GROUP BY e.student_id
ORDER BY e.student_id 


--Method2: 
SELECT student_id, MIN(course_id) AS course_id, grade
FROM Enrollments
WHERE (student_id, grade) IN
(
  SELECT student_id, MAX(grade) AS max_grade
  FROM Enrollments
  GROUP BY student_id
) 
GROUP BY student_id
ORDER BY student_id 

