DROP TABLE `session`;

ALTER TABLE `user`
DROP COLUMN `email`,
DROP COLUMN `password`,
DROP COLUMN `uuid`;

ALTER TABLE `profile`
ADD COLUMN `email` varchar(150) NOT NULL;