-- MySQL Script generated by MySQL Workbench
-- Fri Jun 28 19:37:27 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OrdemServico
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OrdemServico` ;

-- -----------------------------------------------------
-- Schema OrdemServico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OrdemServico` DEFAULT CHARACTER SET utf8 ;
USE `OrdemServico` ;

-- -----------------------------------------------------
-- Table `endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `endereco` ;

CREATE TABLE IF NOT EXISTS `endereco` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Atributo chave primária responsável por identificar os atributos da tabela.',
  `estado` VARCHAR(70) NOT NULL,
  `cidade` VARCHAR(70) NOT NULL COMMENT 'Atrituo responsável por receber o nome da Cidade',
  `bairro` VARCHAR(70) NULL COMMENT 'Atributo responsável por receber o nome do Bairro.',
  `rua` VARCHAR(70) NULL COMMENT 'Atributo responsável  por receber o nome da rua.',
  `numero` VARCHAR(6) NULL COMMENT 'Atrituo responsável por receber o numero do endereço.',
  `cep` VARCHAR(8) NULL COMMENT 'Atrituo responsável por receber o número do CEP.',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marca` ;

CREATE TABLE IF NOT EXISTS `marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nomemarca` VARCHAR(45) NOT NULL,
  `ativo` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `categoriaaparelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoriaaparelho` ;

CREATE TABLE IF NOT EXISTS `categoriaaparelho` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `ativo` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aparelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aparelho` ;

CREATE TABLE IF NOT EXISTS `aparelho` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `numeroserie` VARCHAR(45) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `ativo` CHAR(1) NOT NULL,
  `marca_id` INT NOT NULL,
  `categoriaaparelho_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_aparelho_marca1`
    FOREIGN KEY (`marca_id`)
    REFERENCES `marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aparelho_categoriaaparelho1`
    FOREIGN KEY (`categoriaaparelho_id`)
    REFERENCES `categoriaaparelho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servicos` ;

CREATE TABLE IF NOT EXISTS `servicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tiposervico` VARCHAR(60) NOT NULL,
  `nomeservico` VARCHAR(60) NOT NULL,
  `descricao` VARCHAR(120) NULL,
  `ativo` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orcamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orcamento` ;

CREATE TABLE IF NOT EXISTS `orcamento` (
  `numero` INT NOT NULL AUTO_INCREMENT,
  `total` FLOAT NOT NULL,
  PRIMARY KEY (`numero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ordemservico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordemservico` ;

CREATE TABLE IF NOT EXISTS `ordemservico` (
  `numeroos` INT(11) NOT NULL AUTO_INCREMENT,
  `observacoes` VARCHAR(250) NULL,
  `statusos` CHAR(2) NULL,
  `login_id` INT NOT NULL,
  `ativo` CHAR(1) NOT NULL,
  `aparelho_id` INT NOT NULL,
  `servicos_id` INT NOT NULL,
  `orcamento_numero` INT NOT NULL,
  PRIMARY KEY (`numeroos`),
  CONSTRAINT `fk_ordemservico_aparelho1`
    FOREIGN KEY (`aparelho_id`)
    REFERENCES `aparelho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordemservico_servicos1`
    FOREIGN KEY (`servicos_id`)
    REFERENCES `servicos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordemservico_orcamento1`
    FOREIGN KEY (`orcamento_numero`)
    REFERENCES `orcamento` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `funcionario` ;

CREATE TABLE IF NOT EXISTS `funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cargo` VARCHAR(30) NOT NULL,
  `setor` VARCHAR(30) NULL,
  `salario` VARCHAR(12) NOT NULL,
  `dataadmissao` DATE NOT NULL,
  `numeroct` VARCHAR(8) NULL,
  `PIS` VARCHAR(11) NULL,
  `datademissao` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pessoa` ;

CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `rg` VARCHAR(7) NULL,
  `datanascimento` DATE NOT NULL,
  `sexo` VARCHAR(1) NOT NULL,
  `telefone` VARCHAR(14) NULL,
  `celular` VARCHAR(16) NULL,
  `email` VARCHAR(50) NULL,
  `profissao` VARCHAR(45) NULL,
  `tipomorada` VARCHAR(4) NULL,
  `tipopessoa` CHAR(2) NULL,
  `ativo` VARCHAR(1) NOT NULL,
  `funcionario_id` INT NOT NULL,
  `endereco_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_pessoa_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_endereco1`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `login` ;

CREATE TABLE IF NOT EXISTS `login` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Atributo chave primaria resposável por receber o identificador dos atributos na tabela.',
  `email` VARCHAR(45) NOT NULL COMMENT 'Atrituo responsável por receber o e-mail registrado para o cliente.',
  `senha` VARCHAR(16) NOT NULL COMMENT 'Atrituo responsável por receber a senha de acesso ao sistema para o usuário.',
  `ordemservico_numeroos` INT(11) NOT NULL,
  `pessoa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_login_ordemservico1`
    FOREIGN KEY (`ordemservico_numeroos`)
    REFERENCES `ordemservico` (`numeroos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_login_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
