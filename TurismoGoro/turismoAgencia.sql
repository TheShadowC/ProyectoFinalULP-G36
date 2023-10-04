-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 03-10-2023 a las 01:18:26
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `turismoAgencia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento`
--

CREATE TABLE `alojamiento` (
  `idAlojamiento` tinyint UNSIGNED NOT NULL,
  `fechaIn` date NOT NULL,
  `fechaFin` date NOT NULL,
  `servicio` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `importeDiario` double UNSIGNED NOT NULL,
  `idCiudad` tinyint UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `idCiudad` tinyint UNSIGNED NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `provincia` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `pais` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquete`
--

CREATE TABLE `paquete` (
  `idPaquete` tinyint UNSIGNED NOT NULL,
  `idCiudadOrigen` tinyint UNSIGNED NOT NULL,
  `idCiudadDestino` tinyint UNSIGNED NOT NULL,
  `idAlojamiento` tinyint UNSIGNED NOT NULL,
  `idPasaje` tinyint UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajes`
--

CREATE TABLE `pasajes` (
  `idPasaje` tinyint UNSIGNED NOT NULL,
  `tipoPasaje` tinyint UNSIGNED NOT NULL,
  `importe` double UNSIGNED NOT NULL,
  `idCiudadOrigen` tinyint UNSIGNED NOT NULL,
  `idCiudadDestino` tinyint UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD PRIMARY KEY (`idAlojamiento`),
  ADD UNIQUE KEY `destino` (`idCiudad`),
  ADD UNIQUE KEY `aloja` (`idAlojamiento`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`idCiudad`),
  ADD UNIQUE KEY `ciudad` (`idCiudad`);

--
-- Indices de la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`idPaquete`),
  ADD UNIQUE KEY `origen` (`idCiudadOrigen`),
  ADD UNIQUE KEY `destino` (`idCiudadDestino`),
  ADD UNIQUE KEY `aloja` (`idAlojamiento`),
  ADD UNIQUE KEY `pasaje` (`idPasaje`);

--
-- Indices de la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD PRIMARY KEY (`idPasaje`),
  ADD UNIQUE KEY `origen` (`idCiudadOrigen`),
  ADD UNIQUE KEY `destino` (`idCiudadDestino`),
  ADD UNIQUE KEY `id` (`idPasaje`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  MODIFY `idAlojamiento` tinyint UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `idCiudad` tinyint UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paquete`
--
ALTER TABLE `paquete`
  MODIFY `idPaquete` tinyint UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pasajes`
--
ALTER TABLE `pasajes`
  MODIFY `idPasaje` tinyint UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD CONSTRAINT `alojamiento_ibfk_1` FOREIGN KEY (`idAlojamiento`) REFERENCES `paquete` (`idAlojamiento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`idCiudad`) REFERENCES `paquete` (`idCiudadDestino`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD CONSTRAINT `pasajes_ibfk_1` FOREIGN KEY (`idPasaje`) REFERENCES `paquete` (`idPasaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pasajes_ibfk_2` FOREIGN KEY (`idCiudadDestino`) REFERENCES `alojamiento` (`idCiudad`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
