-- MySQL Script generated by MySQL Workbench
-- Tue Apr  9 21:43:23 2019
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
SHOW WARNINGS;
USE `OrdemServico` ;

-- -----------------------------------------------------
-- Table `endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `endereco` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `endereco` (
  `idendereco` INT NOT NULL AUTO_INCREMENT COMMENT 'Atributo chave primária responsável por identificar os atributos da tabela.',
  `rua` VARCHAR(60) NOT NULL COMMENT 'Atributo responsável  por receber o nome da rua.',
  `bairro` VARCHAR(45) NOT NULL COMMENT 'Atributo responsável por receber o nome do Bairro.',
  `cidade` VARCHAR(50) NOT NULL COMMENT 'Atrituo responsável por receber o nome da Cidade',
  `numero` VARCHAR(4) NOT NULL COMMENT 'Atrituo responsável por receber o numero do endereço.',
  `cep` VARCHAR(8) NOT NULL COMMENT 'Atrituo responsável por receber o número do CEP.',
  PRIMARY KEY (`idendereco`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `funcionario` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `idfuncionario` INT NOT NULL AUTO_INCREMENT,
  `numerocarteiratrabalho` VARCHAR(8),
  `PIS` VARCHAR(11),
  `salario` FLOAT,
  PRIMARY KEY (`idfuncionario`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pessoa` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pessoa` (
  `idpessoa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `datanascimento` DATE NOT NULL,
  `rg` VARCHAR(7) NOT NULL,
  `profissao` VARCHAR(75),
  `ativo` varchar(1) not null,
  `endereco_idendereco` INT,
  `funcionario_idfuncionario` INT,
  PRIMARY KEY (`idpessoa`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marca` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `marca` (
  `marca` INT NOT NULL AUTO_INCREMENT,
  `nomemarca` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`marca`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `categoriaaparelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoriaaparelho` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `categoriaaparelho` (
  `categoriaaparelho` INT NOT NULL AUTO_INCREMENT,
  `tipocategoria` VARCHAR(45) NOT NULL,
  `descricaocategoria` VARCHAR(200) NULL,
  PRIMARY KEY (`categoriaaparelho`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `registroaparelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `registroaparelho` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `registroaparelho` (
  `idregistroaparelho` INT NOT NULL AUTO_INCREMENT,
  `nomeaparelho` VARCHAR(45) NOT NULL,
  `numerodeserie` VARCHAR(45) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `ativo` varchar(1) not null,
  `marca_marca` INT NOT NULL,
  `categoriaaparelho_categoriaaparelho` INT NOT NULL,
  PRIMARY KEY (`idregistroaparelho`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ordemservico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordemservico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ordemservico` (
  `numeroos` INT(11) NOT NULL,
  `observacoes` VARCHAR(250) NULL,
  `registroaparelho_idregistroaparelho` INT NOT NULL,
  PRIMARY KEY (`numeroos`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `perfil` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `perfil` (
  `idperfil` INT NOT NULL AUTO_INCREMENT,
  `tipoperfil` BINARY(1) NOT NULL,
  `nometipoperfil` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idperfil`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `login` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `login` (
  `idlogin` INT NOT NULL AUTO_INCREMENT COMMENT 'Atributo chave primaria resposável por receber o identificador dos atributos na tabela.',
  `email` VARCHAR(45) NOT NULL COMMENT 'Atrituo responsável por receber o e-mail registrado para o cliente.',
  `senha` VARCHAR(16) NOT NULL COMMENT 'Atrituo responsável por receber a senha de acesso ao sistema para o usuário.',
  `pessoa_idpessoa` INT NOT NULL,
  `perfil_idperfil` INT NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`idlogin`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `servicos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servicos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `servicos` (
  `idservico` INT NOT NULL AUTO_INCREMENT,
  `nometiposervico` VARCHAR(30) NOT NULL,
  `nomeservico` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idservico`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `orcamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orcamento` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `orcamento` (
  `numeroorcamento` INT NOT NULL AUTO_INCREMENT,
  `precoorcamento` FLOAT NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`numeroorcamento`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `controle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `controle` (
  `idcontrole` INT NOT NULL AUTO_INCREMENT,
  `statuscontrole` BINARY(1) NOT NULL,
  `orcamento_numeroorcamento` INT NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`idcontrole`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pecascategoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pecascategoria` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pecascategoria` (
  `idcategoria` INT NOT NULL AUTO_INCREMENT,
  `tipocategoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcategoria`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pecasestoque`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pecasestoque` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pecasestoque` (
  `idpeca` INT NOT NULL AUTO_INCREMENT,
  `nomepeca` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(30) NOT NULL,
  `quantidadeestoque` INT(11) NOT NULL,
  `pecascategoria_idcategoria` INT NOT NULL,
  PRIMARY KEY (`idpeca`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `statusos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `statusos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `statusos` (
  `status` INT NOT NULL AUTO_INCREMENT,
  `nomestatus` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`status`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ordemservicopecasestoque`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordemservicopecasestoque` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ordemservicopecasestoque` (
  `ordemservico_numeroos` INT(11) NOT NULL,
  `pecasestoque_idpeca` INT NOT NULL,
  `quantidadecotada` INT(11) NOT NULL,
  PRIMARY KEY (`ordemservico_numeroos`, `pecasestoque_idpeca`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ordemservicoservicos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordemservicoservicos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ordemservicoservicos` (
  `ordemservico_numeroos` INT(11) NOT NULL,
  `servicos_idservico` INT NOT NULL,
  `observacoes` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`ordemservico_numeroos`, `servicos_idservico`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `tipocontrole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipocontrole` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `tipocontrole` (
  `idtipocontrole` INT NOT NULL AUTO_INCREMENT,
  `nometipocontrole` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtipocontrole`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `tipocontrolecontrole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipocontrolecontrole` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `tipocontrolecontrole` (
  `tipocontrole_idtipocontrole` INT NOT NULL,
  `controle_idcontrole` INT NOT NULL,
  PRIMARY KEY (`tipocontrole_idtipocontrole`, `controle_idcontrole`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
