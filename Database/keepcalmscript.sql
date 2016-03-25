
CREATE SCHEMA IF NOT EXISTS `keepCalmAndWatch` DEFAULT CHARACTER SET utf8 ;
USE `keepCalmAndWatch` ;


CREATE TABLE IF NOT EXISTS `keepCalmAndWatch`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `channel_name` VARCHAR(45) NOT NULL,
  `description` NVARCHAR(250) NULL,
  `avatar` MEDIUMBLOB NULL,
  `registration_date` DATETIME NULL,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `chanel_name_UNIQUE` (`channel_name` ASC));

CREATE TABLE IF NOT EXISTS `keepCalmAndWatch`.`playlists` (
  `title` VARCHAR(45) NOT NULL,
  `users_username` VARCHAR(45) NOT NULL,
  `playlist_id` INT NOT NULL,
  INDEX `fk_playlists_users1_idx` (`users_username` ASC),
  PRIMARY KEY (`playlist_id`),
  CONSTRAINT `fk_playlists_users1`
    FOREIGN KEY (`users_username`)
    REFERENCES `keepCalmAndWatch`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `title_username` UNIQUE(title, users_username));

CREATE TABLE IF NOT EXISTS `keepCalmAndWatch`.`videos` (
  `videos_id` INT NOT NULL AUTO_INCREMENT,
  `title` NVARCHAR(45) NOT NULL,
  `description` NVARCHAR(500) NOT NULL,
  `path` VARCHAR(150) NOT NULL,
  `views` INT NOT NULL,
  `likes` INT NOT NULL,
  `dislikes` INT NOT NULL,
  `thumbnail` MEDIUMBLOB NULL,
  `upload_date` DATETIME NULL,
  `users_username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`videos_id`),
  INDEX `fk_videos_users_idx` (`users_username` ASC),
  CONSTRAINT `fk_videos_users`
    FOREIGN KEY (`users_username`)
    REFERENCES `keepCalmAndWatch`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `keepCalmAndWatch`.`comments` (
  `comments_id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(500) NOT NULL,
  `date` DATETIME NOT NULL,
  `likes` INT NULL,
  `dislikes` INT NULL,
  `videos_videos_id` INT NOT NULL,
  `users_username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`comments_id`),
  INDEX `fk_comments_videos1_idx` (`videos_videos_id` ASC),
  INDEX `fk_comments_users1_idx` (`users_username` ASC),
  CONSTRAINT `fk_comments_videos1`
    FOREIGN KEY (`videos_videos_id`)
    REFERENCES `keepCalmAndWatch`.`videos` (`videos_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`users_username`)
    REFERENCES `keepCalmAndWatch`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `keepCalmAndWatch`.`history` (
  `date` DATETIME NOT NULL,
  `users_username` VARCHAR(45) NOT NULL,
  `videos_videos_id` INT NOT NULL,
  INDEX `fk_history_users1_idx` (`users_username` ASC),
  INDEX `fk_history_videos1_idx` (`videos_videos_id` ASC),
  PRIMARY KEY (`users_username`, `videos_videos_id`),
  CONSTRAINT `fk_history_users1`
    FOREIGN KEY (`users_username`)
    REFERENCES `keepCalmAndWatch`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_videos1`
    FOREIGN KEY (`videos_videos_id`)
    REFERENCES `keepCalmAndWatch`.`videos` (`videos_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION));

CREATE TABLE IF NOT EXISTS `keepCalmAndWatch`.`playlists_to_videos` (
  `playlist_id` INT NOT NULL,
  `video_id` INT NOT NULL,
  `date_added` DATETIME NULL,
  PRIMARY KEY (`playlist_id`, `video_id`),
  INDEX `fk_playlists_to_videos_videos1_idx` (`video_id` ASC),
  CONSTRAINT `fk_playlists_to_videos_playlists1`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `keepCalmAndWatch`.`playlists` (`playlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_playlists_to_videos_videos1`
    FOREIGN KEY (`video_id`)
    REFERENCES `keepCalmAndWatch`.`videos` (`videos_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
