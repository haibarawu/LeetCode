/****************************************************************************************************
627. Swap Salary
627. 交换工资

Difficulty: Easy

https://leetcode.com/problems/swap-salary/
https://leetcode.com/articles/swap-salary/

SQL Schema:
create table if not exists salary(id int, name varchar(100), sex char(1), salary int)
Truncate table salary
insert into salary (id, name, sex, salary) values ('1', 'A', 'm', '2500')
insert into salary (id, name, sex, salary) values ('2', 'B', 'f', '1500')
insert into salary (id, name, sex, salary) values ('3', 'C', 'm', '5500')
insert into salary (id, name, sex, salary) values ('4', 'D', 'f', '500')

Given a table salary, such as the one below, that has m=male and f=female values. 
Swap all f and m values (i.e., change all f values to m and vice versa) with a single update statement and no intermediate temp table.
Note that you must write a single update statement, DO NOT write any select statement for this problem.
给定一个 salary 表，如下所示，有 m = 男性 和 f = 女性 的值。
交换所有的 f 和 m 值（例如，将所有 f 值更改为 m，反之亦然）。要求只使用一个更新（Update）语句，并且没有中间的临时表。
注意，您必只能写一个 Update 语句，请不要编写任何 Select 语句。

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

****************************************************************************************************/


--Method1:
UPDATE salary
SET sex = IIF(sex = 'm', 'f', 'm')


/****************************************************************************************************/


--Method2:
UPDATE salary
SET sex = 
    CASE sex
        WHEN 'm' THEN 'f'
        ELSE 'm'
    END


