


INSERT INTO house(`name`,`desc`,`status`,`seller`,`buyer`,`middler`,`img`,`size`,`price`)
SELECT `name`,`desc`,`status`,`seller`,`buyer`,`middler`,`img`,`size`,`price` FROM house;