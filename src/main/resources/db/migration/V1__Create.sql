CREATE TABLE `User` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `user_name` varchar(100) DEFAULT NULL,
                        `avatar` varchar(100) DEFAULT NULL,
                        `create_at` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                        `update_at` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




insert into User(id,user_name,avatar,create_at,update_at,password)
values
(1,'had','http://localhost:8080/static/fonts/HAD.jpeg','2021-01-15 06:05:25.766774','2021-01-15 06:05:25.766774','$2a$10$g4B.wEsE6O5WvGjXywrh3OM07Ti/gdbVXZyiIgDNnmsMLhOk8tHOi');

insert into User(id,user_name,avatar,create_at,update_at,password)
values
(2,'wbw','http://localhost:8080/static/fonts/WBW.png','2021-01-15 06:05:25.766774','2021-01-15 06:05:25.766774','$2a$10$g4B.wEsE6O5WvGjXywrh3OM07Ti/gdbVXZyiIgDNnmsMLhOk8tHOi');

insert into User(id,user_name,avatar,create_at,update_at,password)
values
(3,'QZY','http://localhost:8080/static/fonts/QZY.jpeg','2021-01-15 06:05:25.766774','2021-01-15 06:05:25.766774','$2a$10$g4B.wEsE6O5WvGjXywrh3OM07Ti/gdbVXZyiIgDNnmsMLhOk8tHOi');




CREATE TABLE `Blog` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `title` varchar(50) DEFAULT NULL,
                        `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                        `description` varchar(100) DEFAULT NULL,
                        `create_at` varchar(50) DEFAULT NULL,
                        `update_at` varchar(50) DEFAULT NULL,
                        `user_id` int DEFAULT NULL,
                        `in_index` tinyint DEFAULT '0',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



insert into Blog (id,title,content,description,create_at,update_at,user_id,in_index)
values (1,'侯傲东是个大帅比','它是一个大帅哥','帅哥简介','2021-01-15 06:06:58.974038','2021-01-15 06:06:58.974038',1,0);

insert into Blog (id,title,content,description,create_at,update_at,user_id,in_index)
values (2,'隔壁老王今天不回家过年的原因','隔壁一少妇也不回家过年，因此老王不也不回家过年','不回家过年的原因','2021-01-15 06:06:58.974038','2021-01-15 06:06:58.974038',2,0);

insert into Blog (id,title,content,description,create_at,update_at,user_id,in_index)
values (3,'996的生活','天天加班不在群里恢复好基友的问题究竟是什么原因，天天加班的背后与老母猪怀孕究竟有什么联系，这是道德的沦丧还是价值观的扭曲，请走进老秦的内心是世界。','天天加班的背后又隐藏着什么秘密','2021-01-15 06:06:58.974038','2021-01-15 06:06:58.974038',3,0);