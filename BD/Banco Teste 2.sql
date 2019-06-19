-- MySQL Script generated by MySQL Workbench
-- Tue Jun 11 22:10:29 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OrdemServico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `endereco` ;

CREATE TABLE IF NOT EXISTS `endereco` (
  `idendereco` INT NOT NULL AUTO_INCREMENT COMMENT 'Atributo chave primária responsável por identificar os atributos da tabela.',
  `estado` VARCHAR(70) NOT NULL,
  `cidade` VARCHAR(50) NOT NULL COMMENT 'Atrituo responsável por receber o nome da Cidade',
  `bairro` VARCHAR(45) NULL COMMENT 'Atributo responsável por receber o nome do Bairro.',
  `rua` VARCHAR(60) NULL COMMENT 'Atributo responsável  por receber o nome da rua.',
  `numero` VARCHAR(4) NULL COMMENT 'Atrituo responsável por receber o numero do endereço.',
  `cep` VARCHAR(8) NULL COMMENT 'Atrituo responsável por receber o número do CEP.',
  PRIMARY KEY (`idendereco`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `funcionario` ;

CREATE TABLE IF NOT EXISTS `funcionario` (
  `idfuncionario` INT NOT NULL AUTO_INCREMENT,
  `numerocarteiratrabalho` VARCHAR(8) NULL,
  `PIS` VARCHAR(11) NULL,
  `cargo` VARCHAR(30) NOT NULL,
  `setor` VARCHAR(30) NULL,
  `salario` VARCHAR(20) NOT NULL,
  `dataadmissao` DATE NOT NULL,
  `datademissao` DATE NULL,
  PRIMARY KEY (`idfuncionario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pessoa` ;

CREATE TABLE IF NOT EXISTS `pessoa` (
  `idpessoa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `rg` VARCHAR(7) NULL,
  `datanascimento` DATE NULL,
  `sexo` VARCHAR(1) NULL,
  `telefone` VARCHAR(14) NULL,
  `celular` VARCHAR(16) NULL,
  `email` VARCHAR(50) NULL,
  `profissao` VARCHAR(45) NULL,
  `tipomorada` VARCHAR(4) NULL,
  `ativo` VARCHAR(1) NOT NULL,
  `funcionario_idfuncionario` INT NOT NULL,
  `endereco_idendereco` INT NOT NULL,
  PRIMARY KEY (`idpessoa`),
  CONSTRAINT `fk_pessoa_endereco1`
    FOREIGN KEY (`endereco_idendereco`)
    REFERENCES `endereco` (`idendereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_funcionario1`
    FOREIGN KEY (`funcionario_idfuncionario`)
    REFERENCES `funcionario` (`idfuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marca` ;

CREATE TABLE IF NOT EXISTS `marca` (
  `marca` INT NOT NULL AUTO_INCREMENT,
  `nomemarca` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`marca`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `categoriaaparelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoriaaparelho` ;

CREATE TABLE IF NOT EXISTS `categoriaaparelho` (
  `categoriaaparelho` INT NOT NULL AUTO_INCREMENT,
  `tipocategoria` VARCHAR(45) NOT NULL,
  `descricaocategoria` VARCHAR(200) NULL,
  PRIMARY KEY (`categoriaaparelho`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `registroaparelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `registroaparelho` ;

CREATE TABLE IF NOT EXISTS `registroaparelho` (
  `idregistroaparelho` INT NOT NULL AUTO_INCREMENT,
  `nomeaparelho` VARCHAR(45) NOT NULL,
  `numerodeserie` VARCHAR(45) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `marca_marca` INT NOT NULL,
  `categoriaaparelho_categoriaaparelho` INT NOT NULL,
  PRIMARY KEY (`idregistroaparelho`),
  CONSTRAINT `fk_registroaparelho_marca1`
    FOREIGN KEY (`marca_marca`)
    REFERENCES `marca` (`marca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registroaparelho_categoriaaparelho1`
    FOREIGN KEY (`categoriaaparelho_categoriaaparelho`)
    REFERENCES `categoriaaparelho` (`categoriaaparelho`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ordemservico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordemservico` ;

CREATE TABLE IF NOT EXISTS `ordemservico` (
  `numeroos` INT(11) NOT NULL,
  `observacoes` VARCHAR(250) NULL,
  `registroaparelho_idregistroaparelho` INT NOT NULL,
  PRIMARY KEY (`numeroos`),
  CONSTRAINT `fk_ordemservico_registroaparelho1`
    FOREIGN KEY (`registroaparelho_idregistroaparelho`)
    REFERENCES `registroaparelho` (`idregistroaparelho`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `perfil` ;

CREATE TABLE IF NOT EXISTS `perfil` (
  `idperfil` INT NOT NULL AUTO_INCREMENT,
  `tipoperfil` BINARY(1) NOT NULL,
  `nometipoperfil` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idperfil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `login` ;

CREATE TABLE IF NOT EXISTS `login` (
  `idlogin` INT NOT NULL AUTO_INCREMENT COMMENT 'Atributo chave primaria resposável por receber o identificador dos atributos na tabela.',
  `email` VARCHAR(45) NOT NULL COMMENT 'Atrituo responsável por receber o e-mail registrado para o cliente.',
  `senha` VARCHAR(16) NOT NULL COMMENT 'Atrituo responsável por receber a senha de acesso ao sistema para o usuário.',
  `pessoa_idpessoa` INT NOT NULL,
  `perfil_idperfil` INT NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`idlogin`),
  CONSTRAINT `fk_login_pessoa1`
    FOREIGN KEY (`pessoa_idpessoa`)
    REFERENCES `pessoa` (`idpessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_login_ordemservico1`
    FOREIGN KEY (`ordemservico_numeroos`)
    REFERENCES `ordemservico` (`numeroos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_login_perfil1`
    FOREIGN KEY (`perfil_idperfil`)
    REFERENCES `perfil` (`idperfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servicos` ;

CREATE TABLE IF NOT EXISTS `servicos` (
  `idservico` INT NOT NULL AUTO_INCREMENT,
  `nometiposervico` VARCHAR(30) NOT NULL,
  `nomeservico` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idservico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orcamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orcamento` ;

CREATE TABLE IF NOT EXISTS `orcamento` (
  `numeroorcamento` INT NOT NULL AUTO_INCREMENT,
  `precoorcamento` FLOAT NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`numeroorcamento`),
  CONSTRAINT `fk_orcamento_ordemservico1`
    FOREIGN KEY (`ordemservico_numeroos`)
    REFERENCES `ordemservico` (`numeroos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle` ;

CREATE TABLE IF NOT EXISTS `controle` (
  `idcontrole` INT NOT NULL AUTO_INCREMENT,
  `statuscontrole` BINARY(1) NOT NULL,
  `orcamento_numeroorcamento` INT NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`idcontrole`),
  CONSTRAINT `fk_controleos_orcamento1`
    FOREIGN KEY (`orcamento_numeroorcamento`)
    REFERENCES `orcamento` (`numeroorcamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_controleos_ordemservico1`
    FOREIGN KEY (`ordemservico_numeroos`)
    REFERENCES `ordemservico` (`numeroos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pecascategoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pecascategoria` ;

CREATE TABLE IF NOT EXISTS `pecascategoria` (
  `idcategoria` INT NOT NULL AUTO_INCREMENT,
  `tipocategoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pecasestoque`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pecasestoque` ;

CREATE TABLE IF NOT EXISTS `pecasestoque` (
  `idpeca` INT NOT NULL AUTO_INCREMENT,
  `nomepeca` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(30) NOT NULL,
  `quantidadeestoque` INT(11) NOT NULL,
  `pecascategoria_idcategoria` INT NOT NULL,
  PRIMARY KEY (`idpeca`),
  CONSTRAINT `fk_pecasestoque_pecascategoria1`
    FOREIGN KEY (`pecascategoria_idcategoria`)
    REFERENCES `pecascategoria` (`idcategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `statusos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `statusos` ;

CREATE TABLE IF NOT EXISTS `statusos` (
  `status` INT NOT NULL AUTO_INCREMENT,
  `nomestatus` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `ordemservico_numeroos` INT(11) NOT NULL,
  PRIMARY KEY (`status`),
  CONSTRAINT `fk_statusos_ordemservico1`
    FOREIGN KEY (`ordemservico_numeroos`)
    REFERENCES `ordemservico` (`numeroos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ordemservicopecasestoque`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordemservicopecasestoque` ;

CREATE TABLE IF NOT EXISTS `ordemservicopecasestoque` (
  `ordemservico_numeroos` INT(11) NOT NULL,
  `pecasestoque_idpeca` INT NOT NULL,
  `quantidadecotada` INT(11) NOT NULL,
  PRIMARY KEY (`ordemservico_numeroos`, `pecasestoque_idpeca`),
  CONSTRAINT `fk_ordemservico_has_pecasestoque_ordemservico1`
    FOREIGN KEY (`ordemservico_numeroos`)
    REFERENCES `ordemservico` (`numeroos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordemservico_has_pecasestoque_pecasestoque1`
    FOREIGN KEY (`pecasestoque_idpeca`)
    REFERENCES `pecasestoque` (`idpeca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ordemservicoservicos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordemservicoservicos` ;

CREATE TABLE IF NOT EXISTS `ordemservicoservicos` (
  `ordemservico_numeroos` INT(11) NOT NULL,
  `servicos_idservico` INT NOT NULL,
  `observacoes` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`ordemservico_numeroos`, `servicos_idservico`),
  CONSTRAINT `fk_ordemservico_has_servicos_ordemservico1`
    FOREIGN KEY (`ordemservico_numeroos`)
    REFERENCES `ordemservico` (`numeroos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordemservico_has_servicos_servicos1`
    FOREIGN KEY (`servicos_idservico`)
    REFERENCES `servicos` (`idservico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tipocontrole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipocontrole` ;

CREATE TABLE IF NOT EXISTS `tipocontrole` (
  `idtipocontrole` INT NOT NULL AUTO_INCREMENT,
  `nometipocontrole` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtipocontrole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tipocontrolecontrole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipocontrolecontrole` ;

CREATE TABLE IF NOT EXISTS `tipocontrolecontrole` (
  `tipocontrole_idtipocontrole` INT NOT NULL,
  `controle_idcontrole` INT NOT NULL,
  PRIMARY KEY (`tipocontrole_idtipocontrole`, `controle_idcontrole`),
  CONSTRAINT `fk_tipocontrole_has_controle_tipocontrole1`
    FOREIGN KEY (`tipocontrole_idtipocontrole`)
    REFERENCES `tipocontrole` (`idtipocontrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipocontrole_has_controle_controle1`
    FOREIGN KEY (`controle_idcontrole`)
    REFERENCES `controle` (`idcontrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;