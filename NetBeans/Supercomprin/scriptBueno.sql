-- MySQL Script generated by MySQL Workbench
-- Thu Nov 26 11:31:35 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema supercomprin
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema supercomprin
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `supercomprin` DEFAULT CHARACTER SET utf8 ;
USE `supercomprin` ;

-- -----------------------------------------------------
-- Table `supercomprin`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supercomprin`.`producto` (
  `idproducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `puntos` INT UNSIGNED NOT NULL,
  `precio` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idproducto`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `supercomprin`.`wallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supercomprin`.`wallet` (
  `idwallet` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NULL DEFAULT NULL,
  `dni` VARCHAR(9) NOT NULL,
  `fechanacimiento` DATE NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `puntos` INT UNSIGNED NOT NULL DEFAULT '0',
  `saldo` INT UNSIGNED NOT NULL DEFAULT '0',
  `edad` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idwallet`),
CONSTRAINT `mayor_de_edad` CHECK ((edad>=18))
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `supercomprin`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supercomprin`.`compra` (
  `idcompra` INT NOT NULL AUTO_INCREMENT,
  `idproducto` INT NOT NULL,
  `idwallet` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`idcompra`),
  INDEX `fk_COMPRA_WALLET` (`idwallet` ASC) VISIBLE,
  INDEX `fk_COMPRA_PRODUCTO` (`idproducto` ASC) VISIBLE,
  CONSTRAINT `fk_COMPRA_PRODUCTO`
    FOREIGN KEY (`idproducto`)
    REFERENCES `supercomprin`.`producto` (`idproducto`),
  CONSTRAINT `fk_COMPRA_WALLET`
    FOREIGN KEY (`idwallet`)
    REFERENCES `supercomprin`.`wallet` (`idwallet`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `supercomprin`.`devolucion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supercomprin`.`devolucion` (
  `iddevolucion` INT NOT NULL AUTO_INCREMENT,
  `idcompra` INT NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`iddevolucion`),
  UNIQUE INDEX `idcompra_UNIQUE` (`idcompra` ASC) VISIBLE,
  INDEX `fk_devolucion_1_idx` (`idcompra` ASC) VISIBLE,
  CONSTRAINT `fk_devolucion_compra`
    FOREIGN KEY (`idcompra`)
    REFERENCES `supercomprin`.`compra` (`idcompra`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;