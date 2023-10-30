-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Oct 30, 2023 at 12:25 AM
-- Server version: 8.1.0
-- PHP Version: 8.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `turismoAgencia`
--

-- --------------------------------------------------------

--
-- Table structure for table `alojamiento`
--

CREATE TABLE `alojamiento` (
  `idAlojamiento` tinyint UNSIGNED NOT NULL,
  `tipoAlojamiento` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fechaIn` date NOT NULL,
  `fechaFin` date NOT NULL,
  `servicio` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `importeDiario` double UNSIGNED NOT NULL,
  `idCiudad` tinyint UNSIGNED NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alojamiento`
--

INSERT INTO `alojamiento` (`idAlojamiento`, `tipoAlojamiento`, `fechaIn`, `fechaFin`, `servicio`, `importeDiario`, `idCiudad`, `estado`) VALUES
(8, 'BUNGALOWS', '2024-01-05', '2024-01-12', 'MERIENDA_CENA', 25659.99, 7, 0),
(14, 'HOSTEL', '2024-01-02', '2024-01-12', 'PENSION COMPLETA', 25659.99, 3, 1),
(19, 'DEPARTAMENTO', '2023-11-05', '2023-11-10', 'PENSION_COMPLETA', 25659.99, 7, 1),
(24, 'CABAÑA', '2023-11-10', '2023-11-15', 'DESAYUNO', 25000, 9, 1),
(25, 'CABAÑA', '2023-10-23', '2023-10-25', 'DESAYUNO_ALMUERZO', 20000, 9, 1),
(26, 'hotel', '2024-01-04', '2024-01-08', 'Pension_Completa', 18000, 22, 1),
(27, 'HOSTEL', '2023-10-02', '2023-10-09', 'DESAYUNO', 5000, 3, 1),
(28, 'HOSTEL', '2023-10-02', '2023-10-09', 'DESAYUNO', 5000, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `alojamientoDisponible`
--

CREATE TABLE `alojamientoDisponible` (
  `idAlojamiento` tinyint UNSIGNED NOT NULL,
  `tipoAlojamiento` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `servicio` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `importeDiario` double UNSIGNED NOT NULL,
  `idCiudad` tinyint UNSIGNED NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alojamientoDisponible`
--

INSERT INTO `alojamientoDisponible` (`idAlojamiento`, `tipoAlojamiento`, `servicio`, `importeDiario`, `idCiudad`, `estado`) VALUES
(1, 'HOTEL', 'DESAYUNO', 13000, 1, 1),
(2, 'HOTEL', 'DESAYUNO_CENA', 20000, 1, 1),
(3, 'HOTEL', 'PENSION_COMPLETA', 25000, 1, 1),
(4, 'HOSTEL', 'DESAYUNO', 8000, 1, 1),
(5, 'DEPARTAMENTO', 'PENSION_COMPLETA', 35000, 1, 1),
(6, 'HOSTEL', 'DESAYUNO', 5000, 3, 1),
(7, 'HOTEL', 'PENSION_COMPLETA', 22000, 3, 1),
(8, 'HOTEL', 'DESAYUNO_CENA', 16000, 3, 1),
(9, 'HOSTEL', 'DESAYUNO', 5000, 13, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ciudad`
--

CREATE TABLE `ciudad` (
  `idCiudad` tinyint UNSIGNED NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `provincia` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pais` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ciudad`
--

INSERT INTO `ciudad` (`idCiudad`, `nombre`, `provincia`, `pais`, `estado`) VALUES
(1, 'Colon', 'Buenos Aires', 'Argentina', 1),
(3, 'Corrientes', 'Corrientes', 'Argentina', 1),
(7, 'Colon', 'Entre Rios', 'Argentina', 1),
(9, 'Villa Carlos Paz', 'Cordoba', 'Argentina', 1),
(13, 'Curuzu Cuatya', 'Corrientes', 'Argentin', 1),
(14, 'Santa Fe', 'Santa Fe', 'Argentina', 1),
(15, 'Parana', 'Entre Rios', 'Argentina', 1),
(16, 'Viale', 'Entre Rios', 'Argentina', 1),
(17, 'Mar del Plata', 'Buenos Aires', 'Argentina', 1),
(22, 'Gualeguachu', 'Entre Rios', 'Argentina', 1),
(23, 'Rosario', 'Santa Fe', 'Argentina', 1),
(24, 'San Juan', 'San Juan', 'Argentina', 1),
(25, 'Bella Vista', 'Buenos Aires', 'Argentina', 1),
(26, 'Santa Anita', 'Entre Rios', 'Argentina', 1),
(27, 'Salta', 'Salta', 'Argentina', 1),
(28, 'Santo Tome', 'Santa Fe', 'Argentina', 1);

-- --------------------------------------------------------

--
-- Table structure for table `logueo`
--

CREATE TABLE `logueo` (
  `usuario` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pasword` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logueo`
--

INSERT INTO `logueo` (`usuario`, `pasword`) VALUES
('cmartinez', 'p21Mu61354'),
('Susy', 'Su5691');

-- --------------------------------------------------------

--
-- Table structure for table `paquete`
--

CREATE TABLE `paquete` (
  `idPaquete` tinyint UNSIGNED NOT NULL,
  `idCiudadOrigen` tinyint UNSIGNED NOT NULL,
  `idCiudadDestino` tinyint UNSIGNED NOT NULL,
  `idAlojamiento` tinyint UNSIGNED NOT NULL,
  `idPasaje` tinyint UNSIGNED NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `paquete`
--

INSERT INTO `paquete` (`idPaquete`, `idCiudadOrigen`, `idCiudadDestino`, `idAlojamiento`, `idPasaje`, `estado`) VALUES
(1, 1, 3, 24, 28, 1),
(2, 15, 9, 24, 23, 1),
(3, 3, 17, 24, 21, 1),
(6, 1, 3, 28, 28, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pasajes`
--

CREATE TABLE `pasajes` (
  `idPasaje` tinyint UNSIGNED NOT NULL,
  `tipoTransporte` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `importe` double UNSIGNED NOT NULL,
  `idCiudadOrigen` tinyint UNSIGNED NOT NULL,
  `idCiudadDestino` tinyint UNSIGNED NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pasajes`
--

INSERT INTO `pasajes` (`idPasaje`, `tipoTransporte`, `importe`, `idCiudadOrigen`, `idCiudadDestino`, `estado`) VALUES
(12, 'taxi', 4000, 14, 7, 1),
(21, 'colectivo', 9000, 9, 7, 1),
(23, 'TREN', 7000, 15, 9, 1),
(24, 'TREN', 25000, 15, 9, 0),
(25, 'taxi', 15000, 14, 7, 1),
(26, 'COLECTIVO', 25000, 15, 9, 1),
(27, 'COLECTIVO', 30000, 14, 3, 1),
(28, 'COLECTIVO', 40000, 1, 3, 1),
(32, 'COLECTIVO', 8000, 3, 22, 1),
(37, 'COLECTIVO', 5600.8, 23, 3, 1),
(38, 'COLECTIVO', 5678.78, 28, 3, 1),
(39, 'COLECTIVO', 4564, 9, 3, 1),
(40, 'TREN', 3500, 27, 28, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD PRIMARY KEY (`idAlojamiento`),
  ADD UNIQUE KEY `aloja` (`idAlojamiento`),
  ADD KEY `destino` (`idCiudad`) USING BTREE;

--
-- Indexes for table `alojamientoDisponible`
--
ALTER TABLE `alojamientoDisponible`
  ADD PRIMARY KEY (`idAlojamiento`),
  ADD UNIQUE KEY `aloja` (`idAlojamiento`),
  ADD KEY `destino` (`idCiudad`) USING BTREE;

--
-- Indexes for table `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`idCiudad`),
  ADD KEY `idCiudad` (`idCiudad`),
  ADD KEY `idCiudad_2` (`idCiudad`);

--
-- Indexes for table `logueo`
--
ALTER TABLE `logueo`
  ADD PRIMARY KEY (`pasword`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD UNIQUE KEY `pasword` (`pasword`),
  ADD UNIQUE KEY `pasword_2` (`pasword`);

--
-- Indexes for table `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`idPaquete`),
  ADD KEY `idAlojamiento` (`idAlojamiento`),
  ADD KEY `origen` (`idCiudadOrigen`) USING BTREE,
  ADD KEY `pasaje` (`idPasaje`) USING BTREE,
  ADD KEY `destino` (`idCiudadDestino`) USING BTREE;

--
-- Indexes for table `pasajes`
--
ALTER TABLE `pasajes`
  ADD PRIMARY KEY (`idPasaje`),
  ADD UNIQUE KEY `id` (`idPasaje`),
  ADD KEY `destino` (`idCiudadDestino`) USING BTREE,
  ADD KEY `origen` (`idCiudadOrigen`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alojamiento`
--
ALTER TABLE `alojamiento`
  MODIFY `idAlojamiento` tinyint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `alojamientoDisponible`
--
ALTER TABLE `alojamientoDisponible`
  MODIFY `idAlojamiento` tinyint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `idCiudad` tinyint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `paquete`
--
ALTER TABLE `paquete`
  MODIFY `idPaquete` tinyint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pasajes`
--
ALTER TABLE `pasajes`
  MODIFY `idPasaje` tinyint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD CONSTRAINT `alojamiento_ibfk_1` FOREIGN KEY (`idCiudad`) REFERENCES `ciudad` (`idCiudad`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `alojamientoDisponible`
--
ALTER TABLE `alojamientoDisponible`
  ADD CONSTRAINT `alojamientoDisponible_ibfk_1` FOREIGN KEY (`idCiudad`) REFERENCES `ciudad` (`idCiudad`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `paquete`
--
ALTER TABLE `paquete`
  ADD CONSTRAINT `paquete_ibfk_1` FOREIGN KEY (`idAlojamiento`) REFERENCES `alojamiento` (`idAlojamiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `paquete_ibfk_2` FOREIGN KEY (`idCiudadOrigen`) REFERENCES `ciudad` (`idCiudad`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `paquete_ibfk_3` FOREIGN KEY (`idCiudadDestino`) REFERENCES `ciudad` (`idCiudad`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `paquete_ibfk_4` FOREIGN KEY (`idPasaje`) REFERENCES `pasajes` (`idPasaje`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pasajes`
--
ALTER TABLE `pasajes`
  ADD CONSTRAINT `pasajes_ibfk_4` FOREIGN KEY (`idCiudadDestino`) REFERENCES `ciudad` (`idCiudad`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pasajes_ibfk_5` FOREIGN KEY (`idCiudadOrigen`) REFERENCES `ciudad` (`idCiudad`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
