-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-02-2021 a las 05:35:08
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `miaprdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medidores`
--

CREATE TABLE `medidores` (
  `id_medidores` int(200) NOT NULL,
  `numero` int(200) NOT NULL,
  `marca` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `medidores`
--

INSERT INTO `medidores` (`id_medidores`, `numero`, `marca`) VALUES
(1, 1111, 'CROCO'),
(2, 2222, 'PROTON'),
(3, 3333, 'CROCO'),
(4, 4444, 'PROTON'),
(5, 5555, 'LOLO'),
(6, 6666, 'LAIS'),
(7, 7777, 'CROCO'),
(8, 8888, 'PROTON'),
(9, 9999, 'LOLO'),
(10, 1001, 'DIEZ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_lectura`
--

CREATE TABLE `registro_lectura` (
  `id_registro` int(200) NOT NULL,
  `lectura` int(200) NOT NULL,
  `monto` int(200) NOT NULL,
  `fecha` date NOT NULL,
  `metros_cubicos` int(200) NOT NULL,
  `num_medidor` int(200) NOT NULL,
  `pago` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `registro_lectura`
--

INSERT INTO `registro_lectura` (`id_registro`, `lectura`, `monto`, `fecha`, `metros_cubicos`, `num_medidor`, `pago`) VALUES
(1, 99999, 1990, '2021-10-27', 40, 1111, 0),
(2, 33333, 1999, '2021-10-27', 80, 4444, 0),
(3, 77777, 1872, '2021-10-27', 70, 2222, 0),
(4, 88888, 1234, '2021-10-27', 10, 3333, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `medidores`
--
ALTER TABLE `medidores`
  ADD PRIMARY KEY (`id_medidores`);

--
-- Indices de la tabla `registro_lectura`
--
ALTER TABLE `registro_lectura`
  ADD PRIMARY KEY (`id_registro`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medidores`
--
ALTER TABLE `medidores`
  MODIFY `id_medidores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `registro_lectura`
--
ALTER TABLE `registro_lectura`
  MODIFY `id_registro` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
