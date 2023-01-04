-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 28-Nov-2022 às 11:56
-- Versão do servidor: 5.7.36
-- versão do PHP: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `evo_menu`
--
DROP DATABASE IF EXISTS `evo_menu`;
CREATE DATABASE `evo_menu` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `evo_menu`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `idRestaurante` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `horario_funcionamento`
--

DROP TABLE IF EXISTS `horario_funcionamento`;
CREATE TABLE `horario_funcionamento` (
  `id` int(11) NOT NULL,
  `segunda` varchar(23) NOT NULL,
  `terca` varchar(23) NOT NULL,
  `quarta` varchar(23) NOT NULL,
  `quinta` varchar(23) NOT NULL,
  `sexta` varchar(23) NOT NULL,
  `sabado` varchar(23) NOT NULL,
  `domingo` varchar(23) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `fotografia` text NOT NULL,
  `preco` double NOT NULL,
  `idCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `items_menu`
--

DROP TABLE IF EXISTS `items_menu`;
CREATE TABLE `items_menu` (
  `idMenu` int(11) NOT NULL,
  `idItem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `items_pedido`
--

DROP TABLE IF EXISTS `items_pedido`;
CREATE TABLE `items_pedido` (
  `idItem` int(11) NOT NULL,
  `idPedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `fotografia` text NOT NULL,
  `desconto` double NOT NULL,
  `idCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `menus_pedido`
--

DROP TABLE IF EXISTS `menus_pedido`;
CREATE TABLE `menus_pedido` (
  `idMenu` int(11) NOT NULL,
  `idPedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mesa`
--

DROP TABLE IF EXISTS `mesa`;
CREATE TABLE `mesa` (
  `id` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `capacidade` int(11) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idRestaurante` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `migration`
--

DROP TABLE IF EXISTS `migration`;
CREATE TABLE `migration` (
  `version` varchar(180) NOT NULL,
  `apply_time` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `migration`
--

INSERT INTO `migration` (`version`, `apply_time`) VALUES
('m000000_000000_base', 1669194424),
('m130524_201442_init', 1669194667),
('m190124_110200_add_verification_token_column_to_user_table', 1669194667);

-- --------------------------------------------------------

--
-- Estrutura da tabela `morada`
--

DROP TABLE IF EXISTS `morada`;
CREATE TABLE `morada` (
  `id` int(11) NOT NULL,
  `pais` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `codpost` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE `pagamento` (
  `id` int(11) NOT NULL,
  `valor` double NOT NULL,
  `metodo` varchar(50) NOT NULL,
  `idPedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `valorTotal` double NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidoinscricao`
--

DROP TABLE IF EXISTS `pedidoinscricao`;
CREATE TABLE `pedidoinscricao` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL UNIQUE,
  `email` varchar(100) NOT NULL,
  `telemovel` varchar(13) NOT NULL,
  `morada` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
CREATE TABLE `restaurante` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `lotacaoMaxima` int(11) NOT NULL DEFAULT '0',
  `telemovel` varchar(13) NOT NULL,
  `idHorario` int(11) DEFAULT NULL,
  `idMorada` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `restaurante`
--

INSERT INTO `restaurante` (`id`, `nome`, `email`, `lotacaoMaxima`, `telemovel`, `idHorario`, `idMorada`) VALUES
(1, 'teste', 'teste@teste.com', 0, '111111111', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `auth_key` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_reset_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) DEFAULT '10',
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `verification_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telemovel` varchar(13) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nif` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idRestaurante` int(11) DEFAULT NULL,
  `idMorada` int(11) DEFAULT NULL,
  `idMesa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `username`, `auth_key`, `password_hash`, `password_reset_token`, `email`, `status`, `created_at`, `updated_at`, `verification_token`, `telemovel`, `nif`, `tipo`, `nome`, `idRestaurante`, `idMorada`, `idMesa`) VALUES
(1, 'teste', 'msT1V1kj7TdQfFFYm7fNoutfP7nVOmX3', '$2y$13$OYtx8..pAk2FpNSFbhuCCuHLPoTE45nABTWTPIyVf1JGR3h3sNvv.', NULL, 'teste@gmail.com', 10, 1669197141, 1669197141, 'TMcvALa_z4Jb7uVIa1ODhRHKAmYRLRth_1669197141', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idRestaurante` (`idRestaurante`);

--
-- Índices para tabela `horario_funcionamento`
--
ALTER TABLE `horario_funcionamento`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCategoria` (`idCategoria`);

--
-- Índices para tabela `items_menu`
--
ALTER TABLE `items_menu`
  ADD KEY `idMenu` (`idMenu`),
  ADD KEY `idItem` (`idItem`);

--
-- Índices para tabela `items_pedido`
--
ALTER TABLE `items_pedido`
  ADD KEY `idItem` (`idItem`),
  ADD KEY `idPedido` (`idPedido`);

--
-- Índices para tabela `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCategoria` (`idCategoria`);

--
-- Índices para tabela `menus_pedido`
--
ALTER TABLE `menus_pedido`
  ADD KEY `idMenu` (`idMenu`),
  ADD KEY `idPedido` (`idPedido`);

--
-- Índices para tabela `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idRestaurante` (`idRestaurante`);

--
-- Índices para tabela `migration`
--
ALTER TABLE `migration`
  ADD PRIMARY KEY (`version`);

--
-- Índices para tabela `morada`
--
ALTER TABLE `morada`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pagamento_ibfk_1` (`idPedido`);

--
-- Índices para tabela `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Índices para tabela `pedidoinscricao`
--
ALTER TABLE `pedidoinscricao`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `restaurante`
--
ALTER TABLE `restaurante`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idHorario` (`idHorario`),
  ADD KEY `idMorada` (`idMorada`);

--
-- Índices para tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `password_reset_token` (`password_reset_token`),
  ADD KEY `idRestaurante` (`idRestaurante`),
  ADD KEY `idMorada` (`idMorada`),
  ADD KEY `pessoa_ibfk_1` (`idMesa`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `mesa`
--
ALTER TABLE `mesa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pedidoinscricao`
--
ALTER TABLE `pedidoinscricao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `restaurante`
--
ALTER TABLE `restaurante`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `morada`
--
ALTER TABLE `morada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pagamento`
--
ALTER TABLE `pagamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `horario_funcionamento`
--
ALTER TABLE `horario_funcionamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `categoria`
--
ALTER TABLE `categoria`
  ADD CONSTRAINT `categoria_ibfk_1` FOREIGN KEY (`idRestaurante`) REFERENCES `restaurante` (`id`);

--
-- Limitadores para a tabela `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`);

--
-- Limitadores para a tabela `items_menu`
--
ALTER TABLE `items_menu`
  ADD CONSTRAINT `items_menu_ibfk_1` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`id`),
  ADD CONSTRAINT `items_menu_ibfk_2` FOREIGN KEY (`idItem`) REFERENCES `item` (`id`);

--
-- Limitadores para a tabela `items_pedido`
--
ALTER TABLE `items_pedido`
  ADD CONSTRAINT `items_pedido_ibfk_1` FOREIGN KEY (`idItem`) REFERENCES `item` (`id`),
  ADD CONSTRAINT `items_pedido_ibfk_2` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`id`);

--
-- Limitadores para a tabela `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`);

--
-- Limitadores para a tabela `menus_pedido`
--
ALTER TABLE `menus_pedido`
  ADD CONSTRAINT `menus_pedido_ibfk_1` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`id`),
  ADD CONSTRAINT `menus_pedido_ibfk_2` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`id`);

--
-- Limitadores para a tabela `mesa`
--
ALTER TABLE `mesa`
  ADD CONSTRAINT `mesa_ibfk_1` FOREIGN KEY (`idRestaurante`) REFERENCES `restaurante` (`id`);

--
-- Limitadores para a tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`id`);

--
-- Limitadores para a tabela `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `user` (`id`);

--
-- Limitadores para a tabela `restaurante`
--
ALTER TABLE `restaurante`
  ADD CONSTRAINT `restaurante_ibfk_2` FOREIGN KEY (`idHorario`) REFERENCES `horario_funcionamento` (`id`),
  ADD CONSTRAINT `restaurante_ibfk_3` FOREIGN KEY (`idMorada`) REFERENCES `morada` (`id`);

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `pessoa_ibfk_1` FOREIGN KEY (`idMesa`) REFERENCES `mesa` (`id`),
  ADD CONSTRAINT `pessoa_ibfk_2` FOREIGN KEY (`idRestaurante`) REFERENCES `restaurante` (`id`),
  ADD CONSTRAINT `pessoa_ibfk_3` FOREIGN KEY (`idMorada`) REFERENCES `morada` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;