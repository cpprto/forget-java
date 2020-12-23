-- test.customers definition

CREATE TABLE `customers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `photo` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- test.customers initial data
INSERT INTO test.customers (name,email,birth,photo) VALUES 
('A','A@126.com','2010-02-02',NULL)
,('B','B@163.com','1988-12-26',NULL)
,('C','C@gmail.com','1984-06-12',NULL)
,('D','D@sina.com','1986-06-13',NULL)
,('E','E@gmai.com','1955-07-14',NULL)
,('F','F@163.com','1983-05-17',NULL)
,('G','G@qq.com','1991-11-14',NULL)
,('H','H@126.com','2014-01-17',NULL)
,('I','I@sina.com','1979-11-15',NULL)
,('J','J@126.com','1998-09-08',NULL)
,('K','K@126.com','1998-12-21',NULL)
,('L','L@126.com','2014-01-16',NULL)
;