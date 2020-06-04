/********************************************************************************
596. Classes More Than 5 Students
596. 超过5名学生的课

Difficulty: Easy

SQL Schema: 
Create table If Not Exists courses (student varchar(255), class varchar(255))
Truncate table courses
insert into courses (student, class) values ('A', 'Math')
insert into courses (student, class) values ('B', 'English')
insert into courses (student, class) values ('C', 'Math')
insert into courses (student, class) values ('D', 'Biology')
insert into courses (student, class) values ('E', 'Math')
insert into courses (student, class) values ('F', 'Computer')
insert into courses (student, class) values ('G', 'Math')
insert into courses (student, class) values ('H', 'Math')
insert into courses (student, class) values ('I', 'Math')

There is a table courses with columns: student and class
Please list out all classes which have more than or equal to 5 students.
有一个courses 表 ，有: student (学生) 和 class (课程)。
请列出所有超过或等于5名学生的课。

For example, the table:
+---------+------------+
| student | class      |
+---------+------------+
| A       | Math       |
| B       | English    |
| C       | Math       |
| D       | Biology    |
| E       | Math       |
| F       | Computer   |
| G       | Math       |
| H       | Math       |
| I       | Math       |
+---------+------------+

Should output:
+---------+
| class   |
+---------+
| Math    |
+---------+

Note:
The students should not be counted duplicate in each course.
Note:
学生在每个课中不应被重复计算。
********************************************************************************/


SELECT class
FROM courses
GROUP BY class
HAVING COUNT(DISTINCT student) >= 5 


