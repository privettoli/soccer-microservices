-- liquibase formatted SQL

-- changeset Anatolii_Papenko:Create_Player_table
CREATE TABLE `player` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name`  VARCHAR(255) NOT NULL,
  `middle_name` VARCHAR(255),
  `last_name`   VARCHAR(255) NOT NULL,
  `birth_date`  DATE         NOT NULL,
  `position`    VARCHAR(2)   NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `player`;

-- changeset Anatolii_Papenko:Create_league
CREATE TABLE `league` (
  `id`                               INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `auditing_created_timestamp`       TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  `auditing_last_modified_timestamp` TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  `name`                             VARCHAR(255)          DEFAULT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `league`;

-- changeset Anatolii_Papenko:Create_Team_table
CREATE TABLE `team` (
  `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `team`;

-- changeset Anatolii_Papenko:Create_Matches_table
CREATE TABLE `matches` (
  `id`                        INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`                      VARCHAR(255) NOT NULL,
  `scheduled_start_timestamp` TIMESTAMP    NOT NULL,
  `team_a_id`                 INT UNSIGNED NOT NULL,
  `team_b_id`                 INT UNSIGNED NOT NULL,
  `league_id`                 INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `team_a_index` (`team_a_id`),
  INDEX `team_b_index` (`team_b_id`),
  INDEX `league_index` (`league_id`),
  CONSTRAINT `matches_team_a_fk`
  FOREIGN KEY (`team_a_id`) REFERENCES `team` (`id`),
  CONSTRAINT `matches_team_b_fk`
  FOREIGN KEY (`team_b_id`) REFERENCES `team` (`id`),
  CONSTRAINT `matches_league_fk`
  FOREIGN KEY (`league_id`) REFERENCES `league` (`id`)
);
-- rollback DROP TABLE `matches`;

-- changeset Anatolii_Papenko:Create_many2many_between_Player_and_Team
CREATE TABLE `teams_players` (
  `team_id`   INT UNSIGNED NOT NULL,
  `player_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`team_id`, `player_id`),
  INDEX `player_index` (`player_id`),
  CONSTRAINT `teams_players_team_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `teams_players_player_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`)
);
-- rollback DROP TABLE `teams_players`;
