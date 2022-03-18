-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Temps de generació: 07-03-2022 a les 19:29:26
-- Versió del servidor: 10.4.21-MariaDB
-- Versió de PHP: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de dades: `ironbody`
--

-- --------------------------------------------------------

--
-- Estructura de la taula `activitats`
--

CREATE TABLE `activitats` (
  `id_activitat` int(30) NOT NULL,
  `descripcio` varchar(20) NOT NULL,
  `durada` int(20) NOT NULL,
  `data` date NOT NULL,
  `hora` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de la taula `assigna`
--

CREATE TABLE `assigna` (
  `id_monitor` int(20) NOT NULL,
  `id_sala` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de la taula `clients`
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
-- Bolcament de dades per a la taula `clients`
--

INSERT INTO `clients` (`nie`, `nom`, `cognom`, `dataNaixement`, `telefon`, `condiciofisica`, `direccio`, `email`, `ccc`, `comunicacio`) VALUES
('12345678z', 'Diego', 'Hernandez', '2005-12-12', '654321999', 'bona', 'Barcelona', 'diego@gmail.com', 'ES6600190020961234567890', 'Hola, soc nou !'),
('134123413', 'Marcos', 'Gamez', '2022-03-01', '966777888', 'muy bien', 'Canada', 'marcos@gmail.com', '13141450', 'hola, ya vuelvo');

-- --------------------------------------------------------

--
-- Estructura de la taula `colectives`
--

CREATE TABLE `colectives` (
  `id_activitat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de la taula `donen`
--

CREATE TABLE `donen` (
  `dataAlta` date NOT NULL,
  `dataBaixa` date NOT NULL,
  `nie` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de la taula `en`
--

CREATE TABLE `en` (
  `id_activitat` int(30) NOT NULL,
  `id_sala` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de la taula `lliures`
--

CREATE TABLE `lliures` (
  `id_activitat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de la taula `monitors`
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
-- Estructura de la taula `realitzen`
--

CREATE TABLE `realitzen` (
  `data` date NOT NULL,
  `hora` time(6) NOT NULL,
  `id_activitat` int(30) NOT NULL,
  `nie` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de la taula `sales`
--

CREATE TABLE `sales` (
  `id_sala` int(30) NOT NULL,
  `descripcio` varchar(20) NOT NULL,
  `aforament` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índexs per a les taules bolcades
--

--
-- Índexs per a la taula `activitats`
--
ALTER TABLE `activitats`
  ADD PRIMARY KEY (`id_activitat`);

--
-- Índexs per a la taula `assigna`
--
ALTER TABLE `assigna`
  ADD PRIMARY KEY (`id_monitor`,`id_sala`),
  ADD KEY `id_monitor` (`id_monitor`),
  ADD KEY `id_sala` (`id_sala`);

--
-- Índexs per a la taula `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`nie`);

--
-- Índexs per a la taula `colectives`
--
ALTER TABLE `colectives`
  ADD PRIMARY KEY (`id_activitat`),
  ADD KEY `id_activitat` (`id_activitat`);

--
-- Índexs per a la taula `donen`
--
ALTER TABLE `donen`
  ADD PRIMARY KEY (`dataAlta`,`dataBaixa`),
  ADD KEY `dataAlta` (`dataAlta`,`dataBaixa`),
  ADD KEY `dataAlta_2` (`dataAlta`),
  ADD KEY `nie` (`nie`);

--
-- Índexs per a la taula `en`
--
ALTER TABLE `en`
  ADD PRIMARY KEY (`id_activitat`,`id_sala`),
  ADD KEY `id_sala` (`id_sala`);

--
-- Índexs per a la taula `lliures`
--
ALTER TABLE `lliures`
  ADD PRIMARY KEY (`id_activitat`),
  ADD KEY `id_activitat` (`id_activitat`);

--
-- Índexs per a la taula `monitors`
--
ALTER TABLE `monitors`
  ADD PRIMARY KEY (`id_monitor`);

--
-- Índexs per a la taula `realitzen`
--
ALTER TABLE `realitzen`
  ADD PRIMARY KEY (`data`,`hora`),
  ADD KEY `data` (`data`),
  ADD KEY `hora` (`hora`),
  ADD KEY `id_activitat` (`id_activitat`),
  ADD KEY `nie` (`nie`);

--
-- Índexs per a la taula `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id_sala`);

--
-- Restriccions per a les taules bolcades
--

--
-- Restriccions per a la taula `assigna`
--
ALTER TABLE `assigna`
  ADD CONSTRAINT `assigna_ibfk_1` FOREIGN KEY (`id_monitor`) REFERENCES `monitors` (`id_monitor`),
  ADD CONSTRAINT `assigna_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sales` (`id_sala`);

--
-- Restriccions per a la taula `colectives`
--
ALTER TABLE `colectives`
  ADD CONSTRAINT `colectives_ibfk_1` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`);

--
-- Restriccions per a la taula `donen`
--
ALTER TABLE `donen`
  ADD CONSTRAINT `donen_ibfk_1` FOREIGN KEY (`nie`) REFERENCES `clients` (`nie`);

--
-- Restriccions per a la taula `en`
--
ALTER TABLE `en`
  ADD CONSTRAINT `en_ibfk_1` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`),
  ADD CONSTRAINT `en_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sales` (`id_sala`);

--
-- Restriccions per a la taula `lliures`
--
ALTER TABLE `lliures`
  ADD CONSTRAINT `lliures_ibfk_1` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`);

--
-- Restriccions per a la taula `realitzen`
--
ALTER TABLE `realitzen`
  ADD CONSTRAINT `realitzen_ibfk_1` FOREIGN KEY (`nie`) REFERENCES `clients` (`nie`),
  ADD CONSTRAINT `realitzen_ibfk_2` FOREIGN KEY (`id_activitat`) REFERENCES `activitats` (`id_activitat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
