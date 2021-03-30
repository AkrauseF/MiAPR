-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 30-03-2021 a las 21:00:16
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

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
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(200) NOT NULL,
  `rut` int(200) NOT NULL,
  `nombre` text COLLATE utf8_unicode_ci NOT NULL,
  `apellido` text COLLATE utf8_unicode_ci NOT NULL,
  `direccion` text COLLATE utf8_unicode_ci NOT NULL,
  `subsidio` int(200) NOT NULL,
  `num_sitio` int(200) NOT NULL,
  `id_medidor` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `rut`, `nombre`, `apellido`, `direccion`, `subsidio`, `num_sitio`, `id_medidor`) VALUES
(1, 100000000, 'Maria', 'Gonzalez', 'Av Los Castanos', 100, 34, 1),
(2, 100000001, 'Jose', 'Hernandez', 'Calle uno', 50, 23, 2),
(3, 100000002, 'Antonio', 'Perez', 'Calle dos', 0, 12, 3),
(4, 100000003, 'Jorge', 'Alarcon', 'Av Los Carreras', 0, 54, 4),
(5, 100000004, 'Juan', 'Gimenez', 'Los manzanos', 100, 56, 5),
(6, 100000005, 'Pedro', 'Patino', 'Av Los Manjares', 0, 25, 6),
(7, 100000006, 'Felipe', 'Garcia', 'Calle Metro dos', 50, 67, 7),
(8, 100000007, 'Ana', 'Vasquez', 'Calle cuatro', 50, 72, 8),
(9, 100000008, 'Luis', 'Carmona', 'Av Los Matasanos', 100, 92, 9),
(10, 100000009, 'Cecilia', 'Fuentes', 'Calle cinco', 0, 18, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_cobros`
--

CREATE TABLE `datos_cobros` (
  `id_datos` int(200) NOT NULL,
  `cargo_fijo` int(200) NOT NULL,
  `metros_subsidio` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `datos_cobros`
--

INSERT INTO `datos_cobros` (`id_datos`, `cargo_fijo`, `metros_subsidio`) VALUES
(1, 2500, 15);

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
(1, 1111, 'Tesla'),
(2, 2222, 'Proco'),
(3, 3333, 'Mast'),
(4, 4444, 'Tesla'),
(5, 5555, 'Cobro'),
(6, 6666, 'Proco'),
(7, 7777, 'Kia'),
(8, 8888, 'Nesus'),
(9, 9999, 'Tesla'),
(10, 1000, 'Nesus');

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
(1, 147, 8800, '2021-02-22', 37, 1111, 0),
(2, 150, 2250, '2021-02-22', 10, 2222, 0),
(3, 140, 18500, '2021-02-22', 40, 3333, 0),
(4, 140, 18500, '2021-02-22', 40, 4444, 0),
(5, 107, 0, '2021-02-22', 7, 5555, 0),
(6, 102, 3300, '2021-02-22', 2, 6666, 0),
(7, 105, 250, '2021-02-22', 5, 7777, 0),
(8, 103, 0, '2021-02-22', 3, 8888, 0),
(9, 140, 10000, '2021-02-22', 40, 9999, 0),
(10, 140, 18500, '2021-02-22', 40, 1000, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `cliente_medidor` (`id_medidor`);

--
-- Indices de la tabla `datos_cobros`
--
ALTER TABLE `datos_cobros`
  ADD PRIMARY KEY (`id_datos`);

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
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `datos_cobros`
--
ALTER TABLE `datos_cobros`
  MODIFY `id_datos` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `medidores`
--
ALTER TABLE `medidores`
  MODIFY `id_medidores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `registro_lectura`
--
ALTER TABLE `registro_lectura`
  MODIFY `id_registro` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `cliente_medidor` FOREIGN KEY (`id_medidor`) REFERENCES `medidores` (`id_medidores`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
