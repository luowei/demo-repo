#连接与断开服务器
C:\Documents and Settings\Administrator>mysql -u root -p
Enter password: ****

mysql> quit
Bye

#---------------------------
mysql -u root -p
Enter password: ****

#----------------------------------------------
#输入查询
#mysql的版本号和当前日期
SELECT VERSION(), CURRENT_DATE;

#将mysql用作一个简单的计算器
SELECT SIN(PI()/4), (4+1)*5;

#一行上输入多条语句
SELECT VERSION(); SELECT NOW();

#多行语句的例子
SELECT
USER()
,
CURRENT_DATE;

#输入\c取消已输入的不想执行的命令
SELECT
USER()
\c

#-------------------------------------------
#创建并使用数据库
#显示数据库
SHOW DATABASES;

#使用数据库
USE test

#创建并选择数据库
CREATE DATABASE menagerie;
USE menagerie;

#设置每次启动mysql会话使用menagerie数据库
mysql -u root -p menagerie

#创建表
SHOW TABLES;
CREATE TABLE pet (name VARCHAR(20), owner VARCHAR(20),
species VARCHAR(20), sex CHAR(1), birth DATE, death DATE);
SHOW TABLES;
DESCRIBE pet;


#将数据装入表中
LOAD DATA LOCAL INFILE '/path/pet.txt' INTO TABLE pet;
LOAD DATA LOCAL INFILE '/path/pet.txt' INTO TABLE pet
LINES TERMINATED BY '\r\n';

#pet.txt
Fluffy	Harold	cat	f	1993-02-04
Claws	Gwen	cat	m	1994-03-17
Buffy	Harold	dog	f	1989-05-13
Fang	Benny	dog	m	1990-08-27
Bowser	Diane	dog	m	1979-08-31	1995-07-29
Chirpy	Gwen	bird	f	1998-09-11
Whistler	Gwen	bird	f	1997-12-09
Slim	Benny	snake	m	1996-04-29

INSERT INTO pet VALUES ('Puffball','Diane','hamster','f','1999-03-30',NULL);
INSERT INTO pet VALUES ('Bowser','Diane','dog','m','1979-08-31','1995-07-29');
INSERT INTO pet VALUES ('Chirpy','Gwen','bird','f','1998-09-11',NULL);


#从表检索信息
select * from pet;
UPDATE pet SET birth = '1989-08-30' WHERE name = 'Bowser';

#选择特殊行
SELECT * FROM pet WHERE name = 'Bowser';
SELECT * FROM pet WHERE birth > '1998-1-1';
SELECT * FROM pet WHERE species = 'dog' AND sex = 'f';

#选择特殊列
SELECT name, birth FROM pet;
SELECT owner FROM pet;
SELECT DISTINCT owner FROM pet;

SELECT name, species, birth FROM pet
WHERE species = 'dog' OR species = 'cat';

#分类行
SELECT name, birth FROM pet ORDER BY birth;
SELECT name, birth FROM pet ORDER BY birth DESC;
SELECT name, species, birth FROM pet
ORDER BY species, birth DESC;

#日期计算
#确定每个宠物有多大
SELECT name, birth, CURDATE(),
(YEAR(CURDATE())-YEAR(birth))
- (RIGHT(CURDATE(),5)<RIGHT(birth,5))
AS age
FROM pet;
#YEAR()提取日期的年部分，RIGHT()提取日期的MM-DD (日历年)部分的最右面5个字符。

SELECT name, birth, CURDATE(),
(YEAR(CURDATE())-YEAR(birth))
- (RIGHT(CURDATE(),5)<RIGHT(birth,5))
AS age
FROM pet ORDER BY name;

SELECT name, birth, CURDATE(),
(YEAR(CURDATE())-YEAR(birth))
- (RIGHT(CURDATE(),5)<RIGHT(birth,5))
AS age
FROM pet ORDER BY age;


#查出下个月过生日的动物
SELECT name, birth, MONTH(birth) FROM pet;
SELECT name, birth FROM pet WHERE MONTH(birth) = 5;

SELECT name, birth FROM pet
WHERE MONTH(birth) = MONTH(DATE_ADD(CURDATE(),INTERVAL 1 MONTH));

SELECT name, birth FROM pet
WHERE MONTH(birth) = MOD(MONTH(CURDATE()), 12) + 1;


#NULL值操作
#不能使用算术比较 操作符例如=、<或!=
SELECT 1 = NULL, 1 <> NULL, 1 < NULL, 1 > NULL;

#在MySQL中，0或 NULL意味着假而其它值意味着真。布尔运算的默认真值是1
SELECT 1 IS NULL, 1 IS NOT NULL;
SELECT 0 IS NULL, 0 IS NOT NULL, '' IS NULL, '' IS NOT NULL;

#模式匹配
#找出以“b”开头的名字
SELECT * FROM pet WHERE name LIKE 'b%';

#找出以“fy”结尾的名字
SELECT * FROM pet WHERE name LIKE '%fy';

#找出包含“w”的名字
SELECT * FROM pet WHERE name LIKE '%w%';

#找出正好包含5个字符的名字，使用“_”模式字符
SELECT * FROM pet WHERE name LIKE '_____';

#------------------------------------------------------------
扩展正则表达式的一些字符是：

·‘.’匹配任何单个的字符。

·字符类“[...]”匹配在方括号内的任何字符。
	例如，“[abc]”匹配“a”、“b”或“c”。为了命名字符的范围，使用一个“-”。
	“[a-z]”匹配任何字母，而“[0-9]”匹配任何数字。

·“ * ”匹配零个或多个在它前面的字符。
	例如，“x*”匹配任何数量的“x”字符，“[0-9]*”匹配任何数量的数字，
	而“.*”匹配任何数量的任何字符。

如果REGEXP模式与被测试值的任何地方匹配，模式就匹配(这不同于LIKE模式匹配，
只有与整个值匹配，模式才匹配)。 为了定位一个模式以便它必须匹配被测试值的
开始或结尾，在模式开始处使用“^”或在模式的结尾用“$”。
#-------------------------------------------------------------

#查询只匹配名称首字母的小写‘b’
SELECT * FROM pet WHERE name REGEXP BINARY '^b';

#找出以“fy”结尾的名字，使用“$”匹配名字的结尾
SELECT * FROM pet WHERE name REGEXP 'fy$';

#找出包含一个“w”的名字
SELECT * FROM pet WHERE name REGEXP 'w';

#找出包含正好5个字符的名字，使用“^”、“$”和5个“.”匹配名字的开始、结尾和5个字符
SELECT * FROM pet WHERE name REGEXP '^.....$';

#也可以使用“{n}”“重复n次”操作符
SELECT * FROM pet WHERE name REGEXP '^.{5}$';


#计数行
#计算你拥有动物的总数目或“在pet表中有多少行?”
SELECT COUNT(*) FROM pet;

#检索每个主人有多少宠物
SELECT owner, COUNT(*) FROM pet GROUP BY owner;
#注意：使用GROUP BY对每个owner的所有记录分组，没有它，你会得到错误消息

#检索每种动物的数量
SELECT species, COUNT(*) FROM pet GROUP BY species;

#检索每种性别的动物数量
SELECT sex, COUNT(*) FROM pet GROUP BY sex

#按种类和性别组合的动物数量
SELECT species, sex, COUNT(*) FROM pet GROUP BY species, sex;

#若使用COUNT( )，你不必检索整个表。例如, 前面的查询，当只对狗和猫进行时，应为
SELECT species, sex, COUNT(*) FROM pet
WHERE species = 'dog' OR species = 'cat'
GROUP BY species, sex;

#仅需要知道已知性别的按性别的动物数目
SELECT species, sex, COUNT(*) FROM pet
WHERE sex IS NOT NULL
GROUP BY species, sex;

#使用1个以上的表
#创建event表
CREATE TABLE event (name VARCHAR(20), date DATE,
type VARCHAR(15), remark VARCHAR(255));

#装载记录
LOAD DATA LOCAL INFILE 'event.txt' INTO TABLE event;
#----event-------------------------------------------
Fluffy	1995-05-15	litter	4 kittens, 3 female, 1 male
Buffy	1993-06-23	litter	5 puppies, 2 female, 3 male
Buffy	1994-06-19	litter	3 puppies, 3 female
Chirpy	1999-03-21	vet	needed beak straightened
Slim	1997-08-03	vet	broken rib
Bowser	1991-10-12	kennel
Fang	1991-10-12	kennel
Fang	1998-08-28	birthday	Gave him a new chew toy
Claws	1998-03-17	birthday	Gave him a new flea collar
Whistler	1998-12-09	birthday	First birthday
#----------------------------------------------------
#有了一窝小动物时，找出每只宠物的年龄
SELECT pet.name,
(YEAR(date)-YEAR(birth)) - (RIGHT(date,5)<RIGHT(birth,5)) AS age,
remark
FROM pet, event
WHERE pet.name = event.name AND event.type = 'litter';

SELECT p1.name, p1.sex, p2.name, p2.sex, p1.species
FROM pet AS p1, pet AS p2
WHERE p1.species = p2.species AND p1.sex = 'f' AND p2.sex = 'm';


#获得数据库和表的信息
#找出当前选择了哪个数据库
SELECT DATABASE();

#找出当前的数据库包含什么表
SHOW TABLES;

#表的结构
DESCRIBE pet;

#在批处理模式下使用mysql
#shell> mysql < batch-file
#C:\> mysql -e "source batch-file"
#shell> mysql -h host -u user -p < batch-file
#Enter password: ********

#通过一个分页器在屏幕输出多个查询
shell> mysql < batch-file | more

#捕捉文件中的输出
shell> mysql < batch-file > mysql.out

#使用源代码或 \.命令从mysql提示符运行脚本
mysql> source filename;
mysql> \. filename;


#===========================
#一些常见问题的例子
#----------------------------------------------
#使用数据库表“shop”来储存某个商人（经销商）的每件物品(物品号)的价格。
#假定每个商人对每项物品有一个固定价格，那么(物品，商人)即为该记录的主关键字。
CREATE TABLE shop (
article INT(4) UNSIGNED ZEROFILL DEFAULT '0000' NOT NULL,
dealer  CHAR(20)                 DEFAULT ''     NOT NULL,
price   DOUBLE(16,2)             DEFAULT '0.00' NOT NULL,
PRIMARY KEY(article, dealer));

INSERT INTO shop VALUES
(1,'A',3.45),(1,'B',3.99),(2,'A',10.99),(3,'B',1.45),
(3,'C',1.69),(3,'D',1.25),(4,'D',19.95);

#列的最大值
SELECT MAX(article) AS article FROM shop;

#拥有某个列的最大值的行
#找出最贵物品的编号、销售商和价格
SELECT article, dealer, price
FROM   shop
WHERE  price=(SELECT MAX(price) FROM shop);

SELECT article, dealer, price
FROM shop
ORDER BY price DESC
LIMIT 1;

#列的最大值：按组
SELECT article, MAX(price) AS price
FROM   shop
GROUP BY article;

#拥有某个字段的组间最大值的行
SELECT article, dealer, price
FROM   shop s1
WHERE  price=(SELECT MAX(s2.price)
              FROM shop s2
              WHERE s1.article = s2.article);

#使用用户变量
#找出价格最高或最低的物品
SELECT @min_price:=MIN(price),@max_price:=MAX(price) FROM shop;
SELECT * FROM shop WHERE price=@min_price OR price=@max_price;

#使用外键
CREATE TABLE person (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name CHAR(60) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE shirt (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    style ENUM('t-shirt', 'polo', 'dress') NOT NULL,
    color ENUM('red', 'blue', 'orange', 'white', 'black') NOT NULL,
    owner SMALLINT UNSIGNED NOT NULL REFERENCES person(id),
    PRIMARY KEY (id)
);

INSERT INTO person VALUES (NULL, 'Antonio Paz');

SELECT @last := LAST_INSERT_ID();

INSERT INTO shirt VALUES
(NULL, 'polo', 'blue', @last),
(NULL, 'dress', 'white', @last),
(NULL, 't-shirt', 'blue', @last);

INSERT INTO person VALUES (NULL, 'Lilliana Angelovska');

SELECT @last := LAST_INSERT_ID();

INSERT INTO shirt VALUES
(NULL, 'dress', 'orange', @last),
(NULL, 'polo', 'red', @last),
(NULL, 'dress', 'blue', @last),
(NULL, 't-shirt', 'white', @last);

SELECT * FROM person;
SELECT * FROM shirt;

SELECT s.* FROM person p, shirt s
 WHERE p.name LIKE 'Lilliana%'
   AND s.owner = p.id
   AND s.color <> 'white';

SHOW CREATE TABLE shirt\G;

#根据两个键搜索
SELECT field1_index, field2_index FROM test_table
WHERE field1_index = '1' OR  field2_index = '1';

SELECT field1_index, field2_index
    FROM test_table WHERE field1_index = '1'
UNION
SELECT field1_index, field2_index
    FROM test_table WHERE field2_index = '1';

#根据天计算访问量
CREATE TABLE t1 (year YEAR(4), month INT(2) UNSIGNED ZEROFILL,
             day INT(2) UNSIGNED ZEROFILL);
INSERT INTO t1 VALUES(2000,1,1),(2000,1,20),(2000,1,30),(2000,2,2),
            (2000,2,23),(2000,2,23);

SELECT year,month,BIT_COUNT(BIT_OR(1<<day)) AS days FROM t1
       GROUP BY year,month;

#使用AUTO_INCREMENT
CREATE TABLE animals (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     PRIMARY KEY (id)
 );

INSERT INTO animals (name) VALUES
    ('dog'),('cat'),('penguin'),
    ('lax'),('whale'),('ostrich');

SELECT * FROM animals;


CREATE TABLE animals (
    grp ENUM('fish','mammal','bird') NOT NULL,
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name CHAR(30) NOT NULL,
    PRIMARY KEY (grp,id)
);

INSERT INTO animals (grp,name) VALUES
    ('mammal','dog'),('mammal','cat'),
    ('bird','penguin'),('fish','lax'),('mammal','whale'),
    ('bird','ostrich');

SELECT * FROM animals ORDER BY grp,id;

#以AUTO_INCREMENT值开始而不是1
ALTER TABLE tbl AUTO_INCREMENT = 100;

#查找所有未分发的孪生项
SELECT
    CONCAT(p1.id, p1.tvab) + 0 AS tvid,
    CONCAT(p1.christian_name, ' ', p1.surname) AS Name,
    p1.postal_code AS Code,
    p1.city AS City,
    pg.abrev AS Area,
    IF(td.participation = 'Aborted', 'A', ' ') AS A,
    p1.dead AS dead1,
    l.event AS event1,
    td.suspect AS tsuspect1,
    id.suspect AS isuspect1,
    td.severe AS tsevere1,
    id.severe AS isevere1,
    p2.dead AS dead2,
    l2.event AS event2,
    h2.nurse AS nurse2,
    h2.doctor AS doctor2,
    td2.suspect AS tsuspect2,
    id2.suspect AS isuspect2,
    td2.severe AS tsevere2,
    id2.severe AS isevere2,
    l.finish_date
FROM
    twin_project AS tp
    /* For Twin 1 */
    LEFT JOIN twin_data AS td ON tp.id = td.id
              AND tp.tvab = td.tvab
    LEFT JOIN informant_data AS id ON tp.id = id.id
              AND tp.tvab = id.tvab
    LEFT JOIN harmony AS h ON tp.id = h.id
              AND tp.tvab = h.tvab
    LEFT JOIN lentus AS l ON tp.id = l.id
              AND tp.tvab = l.tvab
    /* For Twin 2 */
    LEFT JOIN twin_data AS td2 ON p2.id = td2.id
              AND p2.tvab = td2.tvab
    LEFT JOIN informant_data AS id2 ON p2.id = id2.id
              AND p2.tvab = id2.tvab
    LEFT JOIN harmony AS h2 ON p2.id = h2.id
              AND p2.tvab = h2.tvab
    LEFT JOIN lentus AS l2 ON p2.id = l2.id
              AND p2.tvab = l2.tvab,
    person_data AS p1,
    person_data AS p2,
    postal_groups AS pg
WHERE
    /* p1 gets main twin and p2 gets his/her twin. */
    /* ptvab is a field inverted from tvab */
    p1.id = tp.id AND p1.tvab = tp.tvab AND
    p2.id = p1.id AND p2.ptvab = p1.tvab AND
    /* Just the screening survey */
    tp.survey_no = 5 AND
    /* Skip if partner died before 65 but allow emigration (dead=9) */
    (p2.dead = 0 OR p2.dead = 9 OR
     (p2.dead = 1 AND
      (p2.death_date = 0 OR
       (((TO_DAYS(p2.death_date) - TO_DAYS(p2.birthday)) / 365)
        >= 65))))
    AND
    (
    /* Twin is suspect */
    (td.future_contact = 'Yes' AND td.suspect = 2) OR
    /* Twin is suspect - Informant is Blessed */
    (td.future_contact = 'Yes' AND td.suspect = 1
                               AND id.suspect = 1) OR
    /* No twin - Informant is Blessed */
    (ISNULL(td.suspect) AND id.suspect = 1
                        AND id.future_contact = 'Yes') OR
    /* Twin broken off - Informant is Blessed */
    (td.participation = 'Aborted'
     AND id.suspect = 1 AND id.future_contact = 'Yes') OR
    /* Twin broken off - No inform - Have partner */
    (td.participation = 'Aborted' AND ISNULL(id.suspect)
                                  AND p2.dead = 0))
    AND
    l.event = 'Finished'
    /* Get at area code */
    AND SUBSTRING(p1.postal_code, 1, 2) = pg.code
    /* Not already distributed */
    AND (h.nurse IS NULL OR h.nurse=00 OR h.doctor=00)
    /* Has not refused or been aborted */
    AND NOT (h.status = 'Refused' OR h.status = 'Aborted'
    OR h.status = 'Died' OR h.status = 'Other')
ORDER BY
    tvid;


#显示孪生对状态的表
SELECT
        t1.event,
        t2.event,
        COUNT(*)
FROM
        lentus AS t1,
        lentus AS t2,
        twin_project AS tp
WHERE
        /* We are looking at one pair at a time */
        t1.id = tp.id
        AND t1.tvab=tp.tvab
        AND t1.id = t2.id
        /* Just the screening survey */
        AND tp.survey_no = 5
        /* This makes each pair only appear once */
        AND t1.tvab='1' AND t2.tvab='2'
GROUP BY
        t1.event, t2.event;







