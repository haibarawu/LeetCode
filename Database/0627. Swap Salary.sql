/********************************************************************************
627. Swap Salary

Difficulty: Easy

https://leetcode.com/problems/swap-salary/
https://leetcode.com/articles/swap-salary/

Given a table salary, such as the one below, that has m=male and f=female values. 
Swap all f and m values (i.e., change all f values to m and vice versa) with a single update statement and no intermediate temp table.
Note that you must write a single update statement, DO NOT write any select statement for this problem.

Example:
| id | name | sex | salary |
|----|------|-----|--------|
| 1  | A    | m   | 2500   |
| 2  | B    | f   | 1500   |
| 3  | C    | m   | 5500   |
| 4  | D    | f   | 500    |

After running your update statement, the above salary table should have the following rows:
| id | name | sex | salary |
|----|------|-----|--------|
| 1  | A    | f   | 2500   |
| 2  | B    | m   | 1500   |
| 3  | C    | f   | 5500   |
| 4  | D    | m   | 500    |

********************************************************************************/


--Method1:
UPDATE salary
SET sex = IIF(sex = 'm', 'f', 'm')


--method2:
UPDATE salary
SET sex = 
    CASE sex
        WHEN 'm' THEN 'f'
        ELSE 'm'
    END
