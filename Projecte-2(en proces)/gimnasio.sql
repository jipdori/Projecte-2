-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-03-2022 a las 02:40:07
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gimnasio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activitats`
--

CREATE TABLE `activitats` (
  `id_activitat` int(30) NOT NULL,
  `descripcio` varchar(20) NOT NULL,
  `durada` int(20) NOT NULL,
  `data` date NOT NULL,
  `hora` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `activitats`
--

INSERT INTO `activitats` (`id_activitat`, `descripcio`, `durada`, `data`, `hora`) VALUES
(1, 'ciclisme', 2, '2022-03-30', '20:00:00.000000'),
(2, 'atletisme', 2, '2022-03-25', '21:00:00.000000'),
(3, 'zumba', 2, '2022-03-30', '20:00:00.000000'),
(4, 'kickboxing', 2, '2022-03-25', '21:00:00.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `assigna`
--

CREATE TABLE `assigna` (
  `id_monitor` int(20) NOT NULL,
  `id_sala` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clients`
--

CREATE TABLE `clients` (
  `nie` varchar(9) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `cognom` varchar(20) NOT NULL,
  `dataNaixement` date NOT NULL,
  `telefon` varchar(12) NOT NULL,
  `condiciofisica` varchar(20) NOT NULL,
  `direccio` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `ccc` varchar(25) NOT NULL,
  `comunicacio` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clients`
--

INSERT INTO `clients` (`nie`, `nom`, `cognom`, `dataNaixement`, `telefon`, `condiciofisica`, `direccio`, `email`, `ccc`, `comunicacio`) VALUES
('12312312k', 'Juan', 'Fernandez', '2019-02-14', '655000871', 'bona', 'Madrid', 'juan@gmail.com', 'ES6600190020961234567890', 'Hola,bones'),
('12345678z', 'Diego', 'Hernandez', '2005-12-12', '654321999', 'bona', 'Barcelona', 'diego@gmail.com', 'ES6600190020961234567890', 'Hola, soc nou !'),
('134123413', 'Marcos', 'Gamez', '2022-03-01', '966777888', 'muy bien', 'Canada', 'marcos@gmail.com', '13141450', 'hola, ya vuelvo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectives`
--

CREATE TABLE `colectives` (
  `id_activitat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `donen`
--

CREATE TABLE `donen` (
  `dataAlta` date NOT NULL,
  `dataBaixa` date NOT NULL,
  `nie` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `en`
--

CREATE TABLE `en` (
  `id_activitat` int(30) NOT NULL,
  `id_sala` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lliures`
--

CREATE TABLE `lliures` (
  `id_activitat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monitors`
--

CREATE TABLE `monitors` (
  `id_monitor` int(30) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `cognom` varchar(20) NOT NULL,
  `edat` int(20) NOT NULL,
  `telefon` int(12) NOT NULL,
  `sou` int(20) NOT NULL,
  `direccio` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `realitzen`
--

CREATE TABLE `realitzen` (
  `data` date NOT NULL,
  `hora` time(6) NOT NULL,
  `id_activitat` int(30) NOT NULL,
  `nie` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserves`
--

CREATE TABLE `reserves` (
  `nie` varchar(9) NOT NULL,
  `id_activitat` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reserves`
--

INSERT INTO `reserves` (`nie`, `id_activitat`) VALUES
('12312312k', 1),
('12345678z', 1),
('12345678z', 4),
('134123413', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sales`
--

CREATE TABLE `sales` (
  `id_sala` int(30) NOT NULL,
  `descripcio` varchar(20) NOT NULL,
  `aforament` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `activitats`
--
ALTER TABLE `activitats`
  ADD PRIMARY KEY (`id_activitat`);

--
-- Indices de la tabla `assigna`
--
ALTER TABLE `assigna`
  ADD PRIMARY KEY (`id_monitor`,`id_sala`),
  ADD KEY `id_monitor` (`id_monitor`),
  ADD KEY `id_sala` (`id_sala`);

--
-- Indices de la tabla `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`nie`);

--
-- Indices de la tabla `colectives`
--
ALTER TABLE `colectives`
  ADD PRIMARY KEY (`id_activitat`),
  ADD KEY `id_activitat` (`id_activitat`);

--
-- Indices de la tabla `donen`
--
ALTER TABLE `donen`
  ADD PRIMARY KEY (`dataAlta`,`dataBaixa`),
  ADD KEY `dataAlta` (`dataAlta`,`dataBaixa`),
  ADD KEY `dataAlta_2` (`dataAlta`),
  ADD KEY `nie` (`nie`);

--
-- Indices de la tabla `en`
--
ALTER TABLE `en`
  ADD PRIMARY KEY (`id_activitat`,`id_sala`),
  ADD KEY `id_sala` (`id_sala`);

--
-- Indices de la tabla `lliures`
--
ALTER TABLE `lliures`
  ADD PRIMARY KEY (`id_activitat`),
  ADD KEY `id_activitat` (`id_activitat`);

--
-- Indices de la tabla `monitors`
--
ALTER TABLE `monitors`
  ADD PRIMARY KEY (`id_monitor`);

--
-- Indices de la tabla `realitzen`
--
ALTER TABLE `realitzen`
  ADD PRIMARY KEY (`data`,`hora`),
  ADD KEY `data` (`data`),
  ADD KEY `hora` (`hora`),
  ADD KEY `id_activitat` (`id_activitat`),
  ADD KEY `nie` (`nie`);

--
-- Indices de la tabla `reserves`
--
ALTER TABLE `reserves`
  ADD PRIMARY KEY (`nie`,`id_activitat`),
  ADD KEY `id_activitat` (`id_activitat`);

--
-- Indices de la tabla `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id_sala`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `assigna`
--
ALTER TABLE `assigna`
  ADD CONSTRAINT `assigna_ibfk_1` FOREIGN KEY (`id_monitor`) REFERENCES `monitors` (`id_monitor`),
  ADD CONSTRAINT `assigna_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sales` (`id_sala`);

--
-- Filtros para la tabla `colectives`
--
ALTER TABLE `colectives`
  ADD CONSTRAINT `colectives_ibfk_1` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`);

--
-- Filtros para la tabla `donen`
--
ALTER TABLE `donen`
  ADD CONSTRAINT `donen_ibfk_1` FOREIGN KEY (`nie`) REFERENCES `clients` (`nie`);

--
-- Filtros para la tabla `en`
--
ALTER TABLE `en`
  ADD CONSTRAINT `en_ibfk_1` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`),
  ADD CONSTRAINT `en_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sales` (`id_sala`);

--
-- Filtros para la tabla `lliures`
--
ALTER TABLE `lliures`
  ADD CONSTRAINT `lliures_ibfk_1` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`);

--
-- Filtros para la tabla `realitzen`
--
ALTER TABLE `realitzen`
  ADD CONSTRAINT `realitzen_ibfk_1` FOREIGN KEY (`nie`) REFERENCES `clients` (`nie`),
  ADD CONSTRAINT `realitzen_ibfk_2` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`);

--
-- Filtros para la tabla `reserves`
--
ALTER TABLE `reserves`
  ADD CONSTRAINT `reserves_ibfk_1` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`),
  ADD CONSTRAINT `reserves_ibfk_2` FOREIGN KEY (`nie`) REFERENCES `clients` (`nie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
