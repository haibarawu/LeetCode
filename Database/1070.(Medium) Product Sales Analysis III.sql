/********************************************************************************
1070. Product Sales Analysis III

Difficulty: Medium

https://leetcode.com/problems/product-sales-analysis-iii

Write an SQL query that selects the product id, year, quantity, and price for the first year of every product sold.
The query result format is in the following example:

Sales table:
+---------+------------+------+----------+-------+
| sale_id | product_id | year | quantity | price |
+---------+------------+------+----------+-------+ 
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |
+---------+------------+------+----------+-------+

Product table:
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |
+------------+--------------+

Result table:
+------------+------------+----------+-------+
| product_id | first_year | quantity | price |
+------------+------------+----------+-------+
| 100        | 2008       | 10       | 5000  |
| 200        | 2011       | 15       | 9000  |
+------------+------------+----------+-------+

********************************************************************************/


--Method1: 
SELECT s.product_id, f.first_year, s.quantity, s.price
FROM Sales AS s
INNER JION
(
SELECT product_id, MIN(year) AS 'first_year'
FROM Sales
GROUP BY product_id
) AS f
ON s.product_id = f.product_id AND s.year = f.first_year


--Method2: 
SELECT product_id, year AS first_year, quantity, price
FROM Sales 
WHERE (product_id, year) IN 
(
SELECT product_id, MIN(year)
FROM Sales
GROUP BY product_id
)

