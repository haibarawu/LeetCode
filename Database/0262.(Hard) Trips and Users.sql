/****************************************************************************************************
262. Trips and Users
262. 行程和用户

Difficulty: Hard

SQL Schema:
Create table If Not Exists Trips (Id int, Client_Id int, Driver_Id int, City_Id int, Status ENUM('completed', 'cancelled_by_driver', 'cancelled_by_client'), Request_at varchar(50))
Create table If Not Exists Users (Users_Id int, Banned varchar(50), Role ENUM('client', 'driver', 'partner'))
Truncate table Trips
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('1', '1', '10', '1', 'completed', '2013-10-01')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('2', '2', '11', '1', 'cancelled_by_driver', '2013-10-01')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('3', '3', '12', '6', 'completed', '2013-10-01')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('4', '4', '13', '6', 'cancelled_by_client', '2013-10-01')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('5', '1', '10', '1', 'completed', '2013-10-02')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('6', '2', '11', '6', 'completed', '2013-10-02')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('7', '3', '12', '6', 'completed', '2013-10-02')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('8', '2', '12', '12', 'completed', '2013-10-03')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('9', '3', '10', '12', 'completed', '2013-10-03')
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at) values ('10', '4', '13', '12', 'cancelled_by_driver', '2013-10-03')
Truncate table Users
insert into Users (Users_Id, Banned, Role) values ('1', 'No', 'client')
insert into Users (Users_Id, Banned, Role) values ('2', 'Yes', 'client')
insert into Users (Users_Id, Banned, Role) values ('3', 'No', 'client')
insert into Users (Users_Id, Banned, Role) values ('4', 'No', 'client')
insert into Users (Users_Id, Banned, Role) values ('10', 'No', 'driver')
insert into Users (Users_Id, Banned, Role) values ('11', 'No', 'driver')
insert into Users (Users_Id, Banned, Role) values ('12', 'No', 'driver')
insert into Users (Users_Id, Banned, Role) values ('13', 'No', 'driver')

The Trips table holds all taxi trips. 
Each trip has a unique Id, while Client_Id and Driver_Id are both foreign keys to the Users_Id at the Users table. 
Status is an ENUM type of (‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’).
Trips 表中存所有出租车的行程信息。
每段行程有唯一键 Id，Client_Id 和 Driver_Id 是 Users 表中 Users_Id 的外键。
Status 是枚举类型，枚举成员为 (‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’)。

+----+-----------+-----------+---------+--------------------+----------+
| Id | Client_Id | Driver_Id | City_Id |        Status      |Request_at|
+----+-----------+-----------+---------+--------------------+----------+
| 1  |     1     |    10     |    1    |     completed      |2013-10-01|
| 2  |     2     |    11     |    1    | cancelled_by_driver|2013-10-01|
| 3  |     3     |    12     |    6    |     completed      |2013-10-01|
| 4  |     4     |    13     |    6    | cancelled_by_client|2013-10-01|
| 5  |     1     |    10     |    1    |     completed      |2013-10-02|
| 6  |     2     |    11     |    6    |     completed      |2013-10-02|
| 7  |     3     |    12     |    6    |     completed      |2013-10-02|
| 8  |     2     |    12     |    12   |     completed      |2013-10-03|
| 9  |     3     |    10     |    12   |     completed      |2013-10-03| 
| 10 |     4     |    13     |    12   | cancelled_by_driver|2013-10-03|
+----+-----------+-----------+---------+--------------------+----------+

The Users table holds all users. 
Each user has an unique Users_Id, and Role is an ENUM type of (‘client’, ‘driver’, ‘partner’).
Users 表存所有用户。每个用户有唯一键 Users_Id。Banned 表示这个用户是否被禁止，Role 则是一个表示（‘client’, ‘driver’, ‘partner’）的枚举类型。

+----------+--------+--------+
| Users_Id | Banned |  Role  |
+----------+--------+--------+
|    1     |   No   | client |
|    2     |   Yes  | client |
|    3     |   No   | client |
|    4     |   No   | client |
|    10    |   No   | driver |
|    11    |   No   | driver |
|    12    |   No   | driver |
|    13    |   No   | driver |
+----------+--------+--------+

Write a SQL query to find the cancellation rate of requests made by unbanned users 
(both client and driver must be unbanned) between Oct 1, 2013 and Oct 3, 2013. 
写一段 SQL 语句查出 2013年10月1日 至 2013年10月3日 期间非禁止用户的取消率。

The cancellation rate is computed by 
dividing the number of canceled (by client or driver) requests made by unbanned users 
by the total number of requests made by unbanned users.
取消率的计算方式如下：(被司机或乘客取消的非禁止用户生成的订单数量) / (非禁止用户生成的订单总数)

For the above tables, your SQL query should return the following rows with the cancellation rate being rounded to two decimal places.
基于上表，你的 SQL 语句应返回如下结果，取消率（Cancellation Rate）保留两位小数。

+------------+-------------------+
|     Day    | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 |       0.33        |
| 2013-10-02 |       0.00        |
| 2013-10-03 |       0.50        |
+------------+-------------------+
****************************************************************************************************/


--Method1:
SELECT Request_at AS Day,
       ROUND(SUM(CancelCount) / SUM(TotalCount), 2) AS [Cancellation Rate]
FROM (
    SELECT Client_Id,
           Request_at, 
           CASE WHEN Status IN ('cancelled_by_driver', 'cancelled_by_client') THEN 1.0 ELSE 0.0 END AS CancelCount,
           CASE WHEN Status = Status THEN 1.0 ELSE 0.0 END AS TotalCount
    FROM Trips AS t
    INNER JOIN Users AS u
    ON t.Client_Id = u.Users_Id
    WHERE Banned = 'No'
    AND CAST(Request_at AS DATE) BETWEEN '2013-10-01' AND '2013-10-03'
) AS t
GROUP BY Request_at
ORDER BY Day


/****************************************************************************************************/


--Method2:
SELECT t.Request_at AS Day,
	ROUND (CAST(SUM(CASE WHEN t.Status = 'completed' THEN 0 ELSE 1 END) AS float) / COUNT(t.Status), 2) AS 'Cancellation Rate' 
FROM Trips t
	LEFT JOIN Users u1 ON t.Client_ID = u1.Users_Id
	LEFT JOIN Users u2 ON t.Driver_ID = u2.Users_Id
WHERE Request_at BETWEEN '2013-10-01' AND '2013-10-03' 
	AND u1.Banned = 'No'
	AND u2.Banned = 'No'
GROUP BY t.Request_at


