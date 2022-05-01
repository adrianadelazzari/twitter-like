CREATE TABLE `user`
(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `username` varchar(45)  NOT NULL,
    `email`    varchar(150) NOT NULL,
    `password` varchar(50)  NOT NULL,
    `uuid`     varchar(36)  NOT NULL,
    `created`  datetime     NOT NULL,
    UNIQUE (`username`),
    UNIQUE (`email`),
    PRIMARY KEY (`id`)
);

CREATE TABLE `session`
(
    `id`      int      NOT NULL AUTO_INCREMENT,
    `login`   datetime NOT NULL,
    `logout`  datetime NULL,
    `user_id` int      NOT NULL,
    PRIMARY KEY (`id`),
    KEY       `user_id_fk_1` (`user_id`),
    CONSTRAINT `user_id_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `role`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `name`        varchar(45)  NOT NULL,
    `description` varchar(150) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_role`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY       `role_id_fk_1` (`role_id`),
    CONSTRAINT `role_id_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `user_id_fk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `profile`
(
    `user_id`       int NOT NULL,
    `name`          varchar(100) DEFAULT NULL,
    `location`      varchar(100) DEFAULT NULL,
    `date_of_birth` date         DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    CONSTRAINT `user_id_fk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `producer_subscriber`
(
    `producer_id`   int NOT NULL,
    `subscriber_id` int NOT NULL,
    PRIMARY KEY (`producer_id`, `subscriber_id`),
    KEY             `user_id_fk_5` (`subscriber_id`),
    CONSTRAINT `user_id_fk_4` FOREIGN KEY (`producer_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `user_id_fk_5` FOREIGN KEY (`subscriber_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `message`
(
    `id`      int      NOT NULL AUTO_INCREMENT,
    `content` text     NOT NULL,
    `date`    datetime NOT NULL,
    `user_id` int      NOT NULL,
    PRIMARY KEY (`id`),
    KEY       `user_id_fk_6` (`user_id`),
    CONSTRAINT `user_id_fk_6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `message_like`
(
    `message_id` int      NOT NULL,
    `user_id`    int      NOT NULL,
    `date`       datetime NOT NULL,
    PRIMARY KEY (`message_id`, `user_id`),
    KEY          `user_id_fk_7` (`user_id`),
    CONSTRAINT `message_id_fk_1` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `user_id_fk_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `message_reply`
(
    `id`         int      NOT NULL AUTO_INCREMENT,
    `content`    text     NOT NULL,
    `date`       datetime NOT NULL,
    `message_id` int      NOT NULL,
    `user_id`    int      NOT NULL,
    PRIMARY KEY (`id`),
    KEY          `message_id_fk_2` (`message_id`),
    KEY          `user_id_fk_8` (`user_id`),
    CONSTRAINT `message_id_fk_2` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `user_id_fk_8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);