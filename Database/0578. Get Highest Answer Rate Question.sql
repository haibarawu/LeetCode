/********************************************************************************
578. Get Highest Answer Rate Question

Difficulty: Medium

Get the highest answer rate question from a table survey_log with these columns: id, action, question_id, answer_id, q_num, timestamp.

id means user id; 
action has these kind of values: "show", "answer", "skip"; 
answer_id is not null when action column is "answer", while is null for "show" and "skip"; 
q_num is the numeral order of the question in current session.

Write a sql query to identify the question which has the highest answer rate.

Example:
Input:
+------+-----------+--------------+------------+-----------+------------+
| id   | action    | question_id  | answer_id  | q_num     | timestamp  |
+------+-----------+--------------+------------+-----------+------------+
| 5    | show      | 285          | null       | 1         | 123        |
| 5    | answer    | 285          | 124124     | 1         | 124        |
| 5    | show      | 369          | null       | 2         | 125        |
| 5    | skip      | 369          | null       | 2         | 126        |
+------+-----------+--------------+------------+-----------+------------+
Output:
+-------------+
| survey_log  |
+-------------+
|    285      |
+-------------+
Explanation:
question 285 has answer rate 1/1, while question 369 has 0/1 answer rate, so output 285.

Note: The highest answer rate meaning is: answer number's ratio in show number in the same question.
********************************************************************************/


/********************************************************************************

For each question, result = answered times / show times

********************************************************************************/


SELECT TOP 1 questioinId AS survey_log
FROM
(
  SELECT question_id,
         SUM(IIF(action = 'answer', 1, 0)) AS answer_time,
         SUM(CASE WHEN action = 'show' THEN 1 ELSE 0 END)) AS show_time
  FROM survey_log
  GROUP BY question_id
) AS t
ORDER BY (answer_time / show_time) DESC

