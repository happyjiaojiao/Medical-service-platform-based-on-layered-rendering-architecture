-- MySQL Script generated by MySQL Workbench
-- 2020年04月05日 星期日 14时45分45秒
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT '用户',
  `sex` ENUM('male', 'female') NULL,
  `email` VARCHAR(45) NULL,
  `wechat_id` VARCHAR(45) NULL,
  `head_portrait_id` INT NULL,
  `password` VARCHAR(255) NULL,
  `registration_time` DATETIME NOT NULL DEFAULT now(),
  `phone` VARCHAR(45) NULL,
  `type` ENUM('user', 'medical_institutions', 'administrator') NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `idUser_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `wechat_id_UNIQUE` (`wechat_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`image` ;

CREATE TABLE IF NOT EXISTS `mydb`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image` MEDIUMBLOB NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
