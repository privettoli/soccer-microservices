-- liquibase formatted SQL

-- changeset Anatolii_Papenko:add_auditing_table_revinfo
CREATE TABLE `revinfo` (
  `rev`      INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `revtstmp` BIGINT(20)            DEFAULT NULL,
  PRIMARY KEY (`rev`)
);
-- rollback DROP TABLE `revinfo`;

-- changeset Anatolii_Papenko:add_auditing_table_for_Player
CREATE TABLE `player_aud` (
  `id`          INT UNSIGNED NOT NULL,
  `first_name`  VARCHAR(255) NOT NULL,
  `middle_name` VARCHAR(255),
  `last_name`   VARCHAR(255) NOT NULL,
  `birth_date`  DATE         NOT NULL,
  `position`    VARCHAR(2)   NOT NULL,

  `rev`         INT UNSIGNED NOT NULL,
  `revtype`     TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (`id`, `rev`),
  INDEX `rev_index` (`rev`),
  CONSTRAINT `player_aud_revinfo_fk` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);
-- rollback DROP TABLE `player_aud`;

-- changeset Anatolii_Papenko:add_auditing_table_for_Matches
CREATE TABLE `matches_aud` (
  `id`                        INT UNSIGNED NOT NULL,
  `name`                      VARCHAR(255) NOT NULL,
  `scheduled_start_timestamp` TIMESTAMP    NOT NULL,
  `team_a_id`                 INT UNSIGNED NOT NULL,
  `team_b_id`                 INT UNSIGNED NOT NULL,
  `league_id`                 INT UNSIGNED NOT NULL,

  `rev`                       INT UNSIGNED NOT NULL,
  `revtype`                   TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (`id`, `rev`),
  INDEX `rev_index` (`rev`),
  CONSTRAINT `matches_aud_revinfo_fk` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);
-- rollback DROP TABLE `matches_aud`;


-- changeset Anatolii_Papenko:add_auditing_table_for_Team
CREATE TABLE `team_aud` (
  `id`      INT UNSIGNED NOT NULL,
  `name`    VARCHAR(255) NOT NULL,

  `rev`     INT UNSIGNED NOT NULL,
  `revtype` TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (`id`, `rev`),
  INDEX `rev_index` (`rev`),
  CONSTRAINT `team_aud_revinfo_fk` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);
-- rollback DROP TABLE `team_aud`;

-- changeset Anatolii_Papenko:add_auditing_table_for_Teams_Players
CREATE TABLE `teams_players_audit_log` (
  `team_id`   INT UNSIGNED NOT NULL,
  `player_id` INT UNSIGNED NOT NULL,

  `rev`       INT UNSIGNED NOT NULL,
  `revtype`   TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (`rev`, `team_id`, `player_id`),
  CONSTRAINT `teams_players_audit_log_revinfo_fk` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);
-- rollback DROP TABLE `teams_players_audit_log`;

