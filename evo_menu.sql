-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 11-Jan-2023 às 02:40
-- Versão do servidor: 8.0.31
-- versão do PHP: 8.1.13

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
CREATE DATABASE IF NOT EXISTS `evo_menu` DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci;
USE `evo_menu`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_assignment`
--

DROP TABLE IF EXISTS `auth_assignment`;
CREATE TABLE IF NOT EXISTS `auth_assignment` (
  `item_name` varchar(64) COLLATE utf8mb3_unicode_ci NOT NULL,
  `user_id` varchar(64) COLLATE utf8mb3_unicode_ci NOT NULL,
  `created_at` int DEFAULT NULL,
  PRIMARY KEY (`item_name`,`user_id`),
  KEY `idx-auth_assignment-user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Extraindo dados da tabela `auth_assignment`
--

INSERT INTO `auth_assignment` (`item_name`, `user_id`, `created_at`) VALUES
('Administrador', '1', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_item`
--

DROP TABLE IF EXISTS `auth_item`;
CREATE TABLE IF NOT EXISTS `auth_item` (
  `name` varchar(64) COLLATE utf8mb3_unicode_ci NOT NULL,
  `type` smallint NOT NULL,
  `description` text COLLATE utf8mb3_unicode_ci,
  `rule_name` varchar(64) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `data` blob,
  `created_at` int DEFAULT NULL,
  `updated_at` int DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `rule_name` (`rule_name`),
  KEY `idx-auth_item-type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Extraindo dados da tabela `auth_item`
--

INSERT INTO `auth_item` (`name`, `type`, `description`, `rule_name`, `data`, `created_at`, `updated_at`) VALUES
('acessoBackend', 2, 'Permite o acesso ao backend', NULL, NULL, 1673323316, 1673323316),
('Administrador', 1, 'Tem permissões para tudo', NULL, NULL, 1673323316, 1673323316),
('alterarEstadoMenu', 2, 'Permite alterar o estado dos menus', NULL, NULL, 1673323316, 1673323316),
('alterarEstadoMesa', 2, 'Permite alterar o estado das mesas', NULL, NULL, 1673323316, 1673323316),
('Cliente', 1, 'Só tem permissão para visualizar as páginas inicias', NULL, NULL, 1673323316, 1673323316),
('crudFuncionarios', 2, 'Permite gerir os funcionarios', NULL, NULL, 1673323316, 1673323316),
('crudMenus', 2, 'Permite gerir os menus', NULL, NULL, 1673323316, 1673323316),
('crudMesas', 2, 'Permite gerir as mesas', NULL, NULL, 1673323316, 1673323316),
('editarPedidos', 2, 'Permite editar os pedidos', NULL, NULL, 1673323316, 1673323316),
('Funcionario', 1, 'Tem permissões para gerir os pedidos do seu restaurante', NULL, NULL, 1673323316, 1673323316),
('Gestor', 1, 'Tem permissões para gerir o seu restaurante', NULL, NULL, 1673323316, 1673323316),
('visualizarMenus', 2, 'Permite visualizar os menus', NULL, NULL, 1673323316, 1673323316),
('visualizarMesas', 2, 'Permite visualizar as mesas', NULL, NULL, 1673323316, 1673323316),
('visualizarPagamentos', 2, 'Permite visualizar os pagamentos', NULL, NULL, 1673323316, 1673323316),
('visualizarPedidos', 2, 'Permite visualizar os pedidos', NULL, NULL, 1673323316, 1673323316);

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_item_child`
--

DROP TABLE IF EXISTS `auth_item_child`;
CREATE TABLE IF NOT EXISTS `auth_item_child` (
  `parent` varchar(64) COLLATE utf8mb3_unicode_ci NOT NULL,
  `child` varchar(64) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`parent`,`child`),
  KEY `child` (`child`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Extraindo dados da tabela `auth_item_child`
--

INSERT INTO `auth_item_child` (`parent`, `child`) VALUES
('Administrador', 'acessoBackend'),
('Funcionario', 'alterarEstadoMenu'),
('Funcionario', 'alterarEstadoMesa'),
('Gestor', 'crudFuncionarios'),
('Gestor', 'crudMenus'),
('Gestor', 'crudMesas'),
('Funcionario', 'editarPedidos'),
('Gestor', 'editarPedidos'),
('Funcionario', 'visualizarMenus'),
('Funcionario', 'visualizarMesas'),
('Funcionario', 'visualizarPagamentos'),
('Gestor', 'visualizarPagamentos'),
('Funcionario', 'visualizarPedidos'),
('Gestor', 'visualizarPedidos');

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_rule`
--

DROP TABLE IF EXISTS `auth_rule`;
CREATE TABLE IF NOT EXISTS `auth_rule` (
  `name` varchar(64) COLLATE utf8mb3_unicode_ci NOT NULL,
  `data` blob,
  `created_at` int DEFAULT NULL,
  `updated_at` int DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `idRestaurante` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idRestaurante` (`idRestaurante`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `horario_funcionamento`
--

DROP TABLE IF EXISTS `horario_funcionamento`;
CREATE TABLE IF NOT EXISTS `horario_funcionamento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `segunda` varchar(23) NOT NULL,
  `terca` varchar(23) NOT NULL,
  `quarta` varchar(23) NOT NULL,
  `quinta` varchar(23) NOT NULL,
  `sexta` varchar(23) NOT NULL,
  `sabado` varchar(23) NOT NULL,
  `domingo` varchar(23) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `horario_funcionamento`
--

INSERT INTO `horario_funcionamento` (`id`, `segunda`, `terca`, `quarta`, `quinta`, `sexta`, `sabado`, `domingo`) VALUES
(1, '12:00-15:00-19:00-23:00', '12:00-15:00-19:00-23:00', '12:00-15:00-19:00-23:00', '12:00-15:00-19:00-23:00', '12:00-15:00-19:00-23:00', 'Folga', 'Folga');

-- --------------------------------------------------------

--
-- Estrutura da tabela `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `fotografia` text NOT NULL,
  `preco` double NOT NULL,
  `idCategoria` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `items_menu`
--

DROP TABLE IF EXISTS `items_menu`;
CREATE TABLE IF NOT EXISTS `items_menu` (
  `idMenu` int NOT NULL,
  `idItem` int NOT NULL,
  KEY `idMenu` (`idMenu`),
  KEY `idItem` (`idItem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `items_pedido`
--

DROP TABLE IF EXISTS `items_pedido`;
CREATE TABLE IF NOT EXISTS `items_pedido` (
  `idItem` int NOT NULL,
  `idPedido` int NOT NULL,
  KEY `idItem` (`idItem`),
  KEY `idPedido` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `fotografia` text NOT NULL,
  `desconto` double NOT NULL,
  `idCategoria` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `menus_pedido`
--

DROP TABLE IF EXISTS `menus_pedido`;
CREATE TABLE IF NOT EXISTS `menus_pedido` (
  `idMenu` int NOT NULL,
  `idPedido` int NOT NULL,
  KEY `idMenu` (`idMenu`),
  KEY `idPedido` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mesa`
--

DROP TABLE IF EXISTS `mesa`;
CREATE TABLE IF NOT EXISTS `mesa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `capacidade` int NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idRestaurante` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idRestaurante` (`idRestaurante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `migration`
--

DROP TABLE IF EXISTS `migration`;
CREATE TABLE IF NOT EXISTS `migration` (
  `version` varchar(180) NOT NULL,
  `apply_time` int DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `migration`
--

INSERT INTO `migration` (`version`, `apply_time`) VALUES
('m000000_000000_base', 1669194424),
('m130524_201442_init', NULL),
('m200409_110543_rbac_update_mssql_trigger', 1673323141),
('m180523_151638_rbac_updates_indexes_without_prefix', 1673323141),
('m140506_102106_rbac_init', 1673323140),
('m170907_052038_rbac_add_index_on_auth_assignment_user_id', 1673323140),
('m190124_110200_add_verification_token_column_to_user_table', 1673323316),
('m230110_022819_init_rbac', 1673323316);

-- --------------------------------------------------------

--
-- Estrutura da tabela `morada`
--

DROP TABLE IF EXISTS `morada`;
CREATE TABLE IF NOT EXISTS `morada` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pais` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `codpost` varchar(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `morada`
--

INSERT INTO `morada` (`id`, `pais`, `cidade`, `rua`, `codpost`) VALUES
(1, 'Portugal', 'Lisboa', 'Rua das couves', '2342-234');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE IF NOT EXISTS `pagamento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valor` double NOT NULL,
  `metodo` varchar(50) NOT NULL,
  `idPedido` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pagamento_ibfk_1` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pais`
--

DROP TABLE IF EXISTS `pais`;
CREATE TABLE IF NOT EXISTS `pais` (
  `paisId` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `paisNome` varchar(50) NOT NULL,
  `paisName` varchar(50) NOT NULL,
  PRIMARY KEY (`paisId`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pais`
--

INSERT INTO `pais` (`paisId`, `paisNome`, `paisName`) VALUES
(1, 'AFEGANISTÃO', 'AFGHANISTAN'),
(2, 'ACROTÍRI E DECELIA', 'AKROTIRI E DEKÉLIA'),
(3, 'ÁFRICA DO SUL', 'SOUTH AFRICA'),
(4, 'ALBÂNIA', 'ALBANIA'),
(5, 'ALEMANHA', 'GERMANY'),
(6, 'AMERICAN SAMOA', 'AMERICAN SAMOA'),
(7, 'ANDORRA', 'ANDORRA'),
(8, 'ANGOLA', 'ANGOLA'),
(9, 'ANGUILLA', 'ANGUILLA'),
(10, 'ANTÍGUA E BARBUDA', 'ANTIGUA AND BARBUDA'),
(11, 'ANTILHAS NEERLANDESAS', 'NETHERLANDS ANTILLES'),
(12, 'ARÁBIA SAUDITA', 'SAUDI ARABIA'),
(13, 'ARGÉLIA', 'ALGERIA'),
(14, 'ARGENTINA', 'ARGENTINA'),
(15, 'ARMÉNIA', 'ARMENIA'),
(16, 'ARUBA', 'ARUBA'),
(17, 'AUSTRÁLIA', 'AUSTRALIA'),
(18, 'ÁUSTRIA', 'AUSTRIA'),
(19, 'AZERBAIJÃO', 'AZERBAIJAN'),
(20, 'BAHAMAS', 'BAHAMAS, THE'),
(21, 'BANGLADECHE', 'BANGLADESH'),
(22, 'BARBADOS', 'BARBADOS'),
(23, 'BARÉM', 'BAHRAIN'),
(24, 'BASSAS DA ÍNDIA', 'BASSAS DA INDIA'),
(25, 'BÉLGICA', 'BELGIUM'),
(26, 'BELIZE', 'BELIZE'),
(27, 'BENIM', 'BENIN'),
(28, 'BERMUDAS', 'BERMUDA'),
(29, 'BIELORRÚSSIA', 'BELARUS'),
(30, 'BOLÍVIA', 'BOLIVIA'),
(31, 'BÓSNIA E HERZEGOVINA', 'BOSNIA AND HERZEGOVINA'),
(32, 'BOTSUANA', 'BOTSWANA'),
(33, 'BRASIL', 'BRAZIL'),
(34, 'BRUNEI DARUSSALAM', 'BRUNEI DARUSSALAM'),
(35, 'BULGÁRIA', 'BULGARIA'),
(36, 'BURQUINA FASO', 'BURKINA FASO'),
(37, 'BURUNDI', 'BURUNDI'),
(38, 'BUTÃO', 'BHUTAN'),
(39, 'CABO VERDE', 'CAPE VERDE'),
(40, 'CAMARÕES', 'CAMEROON'),
(41, 'CAMBOJA', 'CAMBODIA'),
(42, 'CANADÁ', 'CANADA'),
(43, 'CATAR', 'QATAR'),
(44, 'CAZAQUISTÃO', 'KAZAKHSTAN'),
(45, 'CENTRO-AFRICANA REPÚBLICA', 'CENTRAL AFRICAN REPUBLIC'),
(46, 'CHADE', 'CHAD'),
(47, 'CHILE', 'CHILE'),
(48, 'CHINA', 'CHINA'),
(49, 'CHIPRE', 'CYPRUS'),
(50, 'COLÔMBIA', 'COLOMBIA'),
(51, 'COMORES', 'COMOROS'),
(52, 'CONGO', 'CONGO'),
(53, 'CONGO REPÚBLICA DEMOCRÁTICA', 'CONGO DEMOCRATIC REPUBLIC'),
(54, 'COREIA DO NORTE', 'KOREA NORTH'),
(55, 'COREIA DO SUL', 'KOREA SOUTH'),
(56, 'COSTA DO MARFIM', 'IVORY COAST'),
(57, 'COSTA RICA', 'COSTA RICA'),
(58, 'CROÁCIA', 'CROATIA'),
(59, 'CUBA', 'CUBA'),
(60, 'DINAMARCA', 'DENMARK'),
(61, 'DOMÍNICA', 'DOMINICA'),
(62, 'EGIPTO', 'EGYPT'),
(63, 'EMIRADOS ÁRABES UNIDOS', 'UNITED ARAB EMIRATES'),
(64, 'EQUADOR', 'ECUADOR'),
(65, 'ERITREIA', 'ERITREA'),
(66, 'ESLOVÁQUIA', 'SLOVAKIA'),
(67, 'ESLOVÉNIA', 'SLOVENIA'),
(68, 'ESPANHA', 'SPAIN'),
(69, 'ESTADOS UNIDOS', 'UNITED STATES'),
(70, 'ESTÓNIA', 'ESTONIA'),
(71, 'ETIÓPIA', 'ETHIOPIA'),
(72, 'FAIXA DE GAZA', 'GAZA STRIP'),
(73, 'FIJI', 'FIJI'),
(74, 'FILIPINAS', 'PHILIPPINES'),
(75, 'FINLÂNDIA', 'FINLAND'),
(76, 'FRANÇA', 'FRANCE'),
(77, 'GABÃO', 'GABON'),
(78, 'GÂMBIA', 'GAMBIA'),
(79, 'GANA', 'GHANA'),
(80, 'GEÓRGIA', 'GEORGIA'),
(81, 'GIBRALTAR', 'GIBRALTAR'),
(82, 'GRANADA', 'GRENADA'),
(83, 'GRÉCIA', 'GREECE'),
(84, 'GRONELÂNDIA', 'GREENLAND'),
(85, 'GUADALUPE', 'GUADELOUPE'),
(86, 'GUAM', 'GUAM'),
(87, 'GUATEMALA', 'GUATEMALA'),
(88, 'GUERNSEY', 'GUERNSEY'),
(89, 'GUIANA', 'GUYANA'),
(90, 'GUIANA FRANCESA', 'FRENCH GUIANA'),
(91, 'GUINÉ', 'GUINEA'),
(92, 'GUINÉ EQUATORIAL', 'EQUATORIAL GUINEA'),
(93, 'GUINÉ-BISSAU', 'GUINEA-BISSAU'),
(94, 'HAITI', 'HAITI'),
(95, 'HONDURAS', 'HONDURAS'),
(96, 'HONG KONG', 'HONG KONG'),
(97, 'HUNGRIA', 'HUNGARY'),
(98, 'IÉMEN', 'YEMEN'),
(99, 'ILHA BOUVET', 'BOUVET ISLAND'),
(100, 'ILHA CHRISTMAS', 'CHRISTMAS ISLAND'),
(101, 'ILHA DE CLIPPERTON', 'CLIPPERTON ISLAND'),
(102, 'ILHA DE JOÃO DA NOVA', 'JUAN DE NOVA ISLAND'),
(103, 'ILHA DE MAN', 'ISLE OF MAN'),
(104, 'ILHA DE NAVASSA', 'NAVASSA ISLAND'),
(105, 'ILHA EUROPA', 'EUROPA ISLAND'),
(106, 'ILHA NORFOLK', 'NORFOLK ISLAND'),
(107, 'ILHA TROMELIN', 'TROMELIN ISLAND'),
(108, 'ILHAS ASHMORE E CARTIER', 'ASHMORE AND CARTIER ISLANDS'),
(109, 'ILHAS CAIMAN', 'CAYMAN ISLANDS'),
(110, 'ILHAS COCOS (KEELING)', 'COCOS (KEELING) ISLANDS'),
(111, 'ILHAS COOK', 'COOK ISLANDS'),
(112, 'ILHAS DO MAR DE CORAL', 'CORAL SEA ISLANDS'),
(113, 'ILHAS FALKLANDS (ILHAS MALVINAS)', 'FALKLAND ISLANDS (ISLAS MALVINAS)'),
(114, 'ILHAS FEROE', 'FAROE ISLANDS'),
(115, 'ILHAS GEÓRGIA DO SUL E SANDWICH DO SUL', 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS'),
(116, 'ILHAS MARIANAS DO NORTE', 'NORTHERN MARIANA ISLANDS'),
(117, 'ILHAS MARSHALL', 'MARSHALL ISLANDS'),
(118, 'ILHAS PARACEL', 'PARACEL ISLANDS'),
(119, 'ILHAS PITCAIRN', 'PITCAIRN ISLANDS'),
(120, 'ILHAS SALOMÃO', 'SOLOMON ISLANDS'),
(121, 'ILHAS SPRATLY', 'SPRATLY ISLANDS'),
(122, 'ILHAS VIRGENS AMERICANAS', 'UNITED STATES VIRGIN ISLANDS'),
(123, 'ILHAS VIRGENS BRITÂNICAS', 'BRITISH VIRGIN ISLANDS'),
(124, 'ÍNDIA', 'INDIA'),
(125, 'INDONÉSIA', 'INDONESIA'),
(126, 'IRÃO', 'IRAN'),
(127, 'IRAQUE', 'IRAQ'),
(128, 'IRLANDA', 'IRELAND'),
(129, 'ISLÂNDIA', 'ICELAND'),
(130, 'ISRAEL', 'ISRAEL'),
(131, 'ITÁLIA', 'ITALY'),
(132, 'JAMAICA', 'JAMAICA'),
(133, 'JAN MAYEN', 'JAN MAYEN'),
(134, 'JAPÃO', 'JAPAN'),
(135, 'JERSEY', 'JERSEY'),
(136, 'JIBUTI', 'DJIBOUTI'),
(137, 'JORDÂNIA', 'JORDAN'),
(138, 'KIRIBATI', 'KIRIBATI'),
(139, 'KOWEIT', 'KUWAIT'),
(140, 'LAOS', 'LAOS'),
(141, 'LESOTO', 'LESOTHO'),
(142, 'LETÓNIA', 'LATVIA'),
(143, 'LÍBANO', 'LEBANON'),
(144, 'LIBÉRIA', 'LIBERIA'),
(145, 'LÍBIA', 'LIBYAN ARAB JAMAHIRIYA'),
(146, 'LISTENSTAINE', 'LIECHTENSTEIN'),
(147, 'LITUÂNIA', 'LITHUANIA'),
(148, 'LUXEMBURGO', 'LUXEMBOURG'),
(149, 'MACAU', 'MACAO'),
(150, 'MACEDÓNIA', 'MACEDONIA'),
(151, 'MADAGÁSCAR', 'MADAGASCAR'),
(152, 'MALÁSIA', 'MALAYSIA'),
(153, 'MALAVI', 'MALAWI'),
(154, 'MALDIVAS', 'MALDIVES'),
(155, 'MALI', 'MALI'),
(156, 'MALTA', 'MALTA'),
(157, 'MARROCOS', 'MOROCCO'),
(158, 'MARTINICA', 'MARTINIQUE'),
(159, 'MAURÍCIA', 'MAURITIUS'),
(160, 'MAURITÂNIA', 'MAURITANIA'),
(161, 'MAYOTTE', 'MAYOTTE'),
(162, 'MÉXICO', 'MEXICO'),
(163, 'MIANMAR', 'MYANMAR BURMA'),
(164, 'MICRONÉSIA', 'MICRONESIA'),
(165, 'MOÇAMBIQUE', 'MOZAMBIQUE'),
(166, 'MOLDÁVIA', 'MOLDOVA'),
(167, 'MÓNACO', 'MONACO'),
(168, 'MONGÓLIA', 'MONGOLIA'),
(169, 'MONTENEGRO', 'MONTENEGRO'),
(170, 'MONTSERRAT', 'MONTSERRAT'),
(171, 'NAMÍBIA', 'NAMIBIA'),
(172, 'NAURU', 'NAURU'),
(173, 'NEPAL', 'NEPAL'),
(174, 'NICARÁGUA', 'NICARAGUA'),
(175, 'NÍGER', 'NIGER'),
(176, 'NIGÉRIA', 'NIGERIA'),
(177, 'NIUE', 'NIUE'),
(178, 'NORUEGA', 'NORWAY'),
(179, 'NOVA CALEDÓNIA', 'NEW CALEDONIA'),
(180, 'NOVA ZELÂNDIA', 'NEW ZEALAND'),
(181, 'OMÃ', 'OMAN'),
(182, 'PAÍSES BAIXOS', 'NETHERLANDS'),
(183, 'PALAU', 'PALAU'),
(184, 'PALESTINA', 'PALESTINE'),
(185, 'PANAMÁ', 'PANAMA'),
(186, 'PAPUÁSIA-NOVA GUINÉ', 'PAPUA NEW GUINEA'),
(187, 'PAQUISTÃO', 'PAKISTAN'),
(188, 'PARAGUAI', 'PARAGUAY'),
(189, 'PERU', 'PERU'),
(190, 'POLINÉSIA FRANCESA', 'FRENCH POLYNESIA'),
(191, 'POLÓNIA', 'POLAND'),
(192, 'PORTO RICO', 'PUERTO RICO'),
(193, 'PORTUGAL', 'PORTUGAL'),
(194, 'QUÉNIA', 'KENYA'),
(195, 'QUIRGUIZISTÃO', 'KYRGYZSTAN'),
(196, 'REINO UNIDO', 'UNITED KINGDOM'),
(197, 'REPÚBLICA CHECA', 'CZECH REPUBLIC'),
(198, 'REPÚBLICA DOMINICANA', 'DOMINICAN REPUBLIC'),
(199, 'ROMÉNIA', 'ROMANIA'),
(200, 'RUANDA', 'RWANDA'),
(201, 'RÚSSIA', 'RUSSIAN FEDERATION'),
(202, 'SAHARA OCCIDENTAL', 'WESTERN SAHARA'),
(203, 'SALVADOR', 'EL SALVADOR'),
(204, 'SAMOA', 'SAMOA'),
(205, 'SANTA HELENA', 'SAINT HELENA'),
(206, 'SANTA LÚCIA', 'SAINT LUCIA'),
(207, 'SANTA SÉ', 'HOLY SEE'),
(208, 'SÃO CRISTÓVÃO E NEVES', 'SAINT KITTS AND NEVIS'),
(209, 'SÃO MARINO', 'SAN MARINO'),
(210, 'SÃO PEDRO E MIQUELÃO', 'SAINT PIERRE AND MIQUELON'),
(211, 'SÃO TOMÉ E PRÍNCIPE', 'SAO TOME AND PRINCIPE'),
(212, 'SÃO VICENTE E GRANADINAS', 'SAINT VINCENT AND THE GRENADINES'),
(213, 'SEICHELES', 'SEYCHELLES'),
(214, 'SENEGAL', 'SENEGAL'),
(215, 'SERRA LEOA', 'SIERRA LEONE'),
(216, 'SÉRVIA', 'SERBIA'),
(217, 'SINGAPURA', 'SINGAPORE'),
(218, 'SÍRIA', 'SYRIA'),
(219, 'SOMÁLIA', 'SOMALIA'),
(220, 'SRI LANCA', 'SRI LANKA'),
(221, 'SUAZILÂNDIA', 'SWAZILAND'),
(222, 'SUDÃO', 'SUDAN'),
(223, 'SUÉCIA', 'SWEDEN'),
(224, 'SUÍÇA', 'SWITZERLAND'),
(225, 'SURINAME', 'SURINAME'),
(226, 'SVALBARD', 'SVALBARD'),
(227, 'TAILÂNDIA', 'THAILAND'),
(228, 'TAIWAN', 'TAIWAN'),
(229, 'TAJIQUISTÃO', 'TAJIKISTAN'),
(230, 'TANZÂNIA', 'TANZANIA'),
(231, 'TERRITÓRIO BRITÂNICO DO OCEANO ÍNDICO', 'BRITISH INDIAN OCEAN TERRITORY'),
(232, 'TERRITÓRIO DAS ILHAS HEARD E MCDONALD', 'HEARD ISLAND AND MCDONALD ISLANDS'),
(233, 'TIMOR-LESTE', 'TIMOR-LESTE'),
(234, 'TOGO', 'TOGO'),
(235, 'TOKELAU', 'TOKELAU'),
(236, 'TONGA', 'TONGA'),
(237, 'TRINDADE E TOBAGO', 'TRINIDAD AND TOBAGO'),
(238, 'TUNÍSIA', 'TUNISIA'),
(239, 'TURKS E CAICOS', 'TURKS AND CAICOS ISLANDS'),
(240, 'TURQUEMENISTÃO', 'TURKMENISTAN'),
(241, 'TURQUIA', 'TURKEY'),
(242, 'TUVALU', 'TUVALU'),
(243, 'UCRÂNIA', 'UKRAINE'),
(244, 'UGANDA', 'UGANDA'),
(245, 'URUGUAI', 'URUGUAY'),
(246, 'USBEQUISTÃO', 'UZBEKISTAN'),
(247, 'VANUATU', 'VANUATU'),
(248, 'VENEZUELA', 'VENEZUELA'),
(249, 'VIETNAME', 'VIETNAM'),
(250, 'WALLIS E FUTUNA', 'WALLIS AND FUTUNA'),
(251, 'ZÂMBIA', 'ZAMBIA'),
(252, 'ZIMBABUÉ', 'ZIMBABWE');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valorTotal` double NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idCliente` int NOT NULL,
  `idRestaurante` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCliente` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidoinscricao`
--

DROP TABLE IF EXISTS `pedidoinscricao`;
CREATE TABLE IF NOT EXISTS `pedidoinscricao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telemovel` varchar(13) NOT NULL,
  `morada` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
CREATE TABLE IF NOT EXISTS `restaurante` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `lotacaoMaxima` int NOT NULL DEFAULT '0',
  `telemovel` varchar(13) NOT NULL,
  `idHorario` int DEFAULT NULL,
  `idMorada` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idHorario` (`idHorario`),
  KEY `idMorada` (`idMorada`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `restaurante`
--

INSERT INTO `restaurante` (`id`, `nome`, `email`, `lotacaoMaxima`, `telemovel`, `idHorario`, `idMorada`) VALUES
(1, 'Quim das Couves', 'quimdascouves@gmail.com', 0, '915536478', 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `auth_key` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `password_hash` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `password_reset_token` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `status` smallint DEFAULT '10',
  `created_at` int DEFAULT NULL,
  `updated_at` int DEFAULT NULL,
  `verification_token` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `telemovel` varchar(13) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `nif` varchar(9) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `tipo` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `nome` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `idRestaurante` int DEFAULT NULL,
  `idMorada` int DEFAULT NULL,
  `idMesa` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `password_reset_token` (`password_reset_token`),
  KEY `idRestaurante` (`idRestaurante`),
  KEY `idMorada` (`idMorada`),
  KEY `pessoa_ibfk_1` (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `username`, `auth_key`, `password_hash`, `password_reset_token`, `email`, `status`, `created_at`, `updated_at`, `verification_token`, `telemovel`, `nif`, `tipo`, `nome`, `idRestaurante`, `idMorada`, `idMesa`) VALUES
(1, 'teste', 'msT1V1kj7TdQfFFYm7fNoutfP7nVOmX3', '$2y$13$OYtx8..pAk2FpNSFbhuCCuHLPoTE45nABTWTPIyVf1JGR3h3sNvv.', NULL, 'teste@gmail.com', 10, 1669197141, 1673400653, 'TMcvALa_z4Jb7uVIa1ODhRHKAmYRLRth_1669197141', '', '', 'Admin', 'Teste', NULL, NULL, NULL);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `auth_assignment`
--
ALTER TABLE `auth_assignment`
  ADD CONSTRAINT `auth_assignment_ibfk_1` FOREIGN KEY (`item_name`) REFERENCES `auth_item` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `auth_item`
--
ALTER TABLE `auth_item`
  ADD CONSTRAINT `auth_item_ibfk_1` FOREIGN KEY (`rule_name`) REFERENCES `auth_rule` (`name`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limitadores para a tabela `auth_item_child`
--
ALTER TABLE `auth_item_child`
  ADD CONSTRAINT `auth_item_child_ibfk_1` FOREIGN KEY (`parent`) REFERENCES `auth_item` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `auth_item_child_ibfk_2` FOREIGN KEY (`child`) REFERENCES `auth_item` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`idRestaurante`) REFERENCES `restaurante` (`id`);

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
