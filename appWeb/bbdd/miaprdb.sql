-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 14-04-2021 a las 05:11:46
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
  `apellido_p` text COLLATE utf8_unicode_ci NOT NULL,
  `apellido_m` text COLLATE utf8_unicode_ci NOT NULL,
  `direccion` text COLLATE utf8_unicode_ci NOT NULL,
  `num_sitio` int(200) NOT NULL,
  `id_medidor` int(200) NOT NULL,
  `id_decreto` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `rut`, `nombre`, `apellido_p`, `apellido_m`, `direccion`, `num_sitio`, `id_medidor`, `id_decreto`) VALUES
(1, 100000027, 'Mariana', 'Gonzalez', 'Fuentes', 'Av Los Castanos', 34, 1, 1),
(2, 100000001, 'Jose', 'Hernandez', 'Carmona', 'Calle uno', 23, 2, 2),
(3, 100000002, 'Antonio', 'Perez', 'Vasquez', 'Calle dos', 12, 3, 1),
(4, 100000003, 'Jorge', 'Alarcon', 'Gonzalez', 'Av Los Carreras', 54, 4, 4),
(5, 100000004, 'Juan', 'Gimenez', 'Hernandez', 'Los manzanos', 56, 5, 1),
(6, 100000005, 'Pedro', 'Patino', 'Perez', 'Av Los Manjares', 25, 6, 6),
(7, 100000006, 'Felipe', 'Garcia', 'Alarcon', 'Calle Metro dos', 67, 7, 7),
(8, 100000007, 'Ana', 'Vasquez', 'Gimenez', 'Calle cuatro', 72, 8, 1),
(9, 100000008, 'Luis', 'Carmona', 'Patino', 'Av Los Matasanos', 92, 9, 9),
(10, 100000009, 'Cecilia', 'Fuentes', 'Garcia', 'Calle cinco', 18, 10, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_cobros`
--

CREATE TABLE `datos_cobros` (
  `id_datos` int(200) NOT NULL,
  `cargo_fijo` int(200) NOT NULL,
  `metros_subsidio` int(200) NOT NULL,
  `valorMetro` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `datos_cobros`
--

INSERT INTO `datos_cobros` (`id_datos`, `cargo_fijo`, `metros_subsidio`, `valorMetro`) VALUES
(1, 2500, 15, 400);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `decretos`
--

CREATE TABLE `decretos` (
  `id_decreto` int(200) NOT NULL,
  `num_decreto` int(200) NOT NULL,
  `fecha_confeccion` date NOT NULL,
  `tramo` text NOT NULL,
  `fecha_encuesta` date NOT NULL,
  `num_socio` int(200) NOT NULL,
  `vnu` int(200) NOT NULL,
  `numero_viviendas` int(200) NOT NULL,
  `tipo_sub` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `decretos`
--

INSERT INTO `decretos` (`id_decreto`, `num_decreto`, `fecha_confeccion`, `tramo`, `fecha_encuesta`, `num_socio`, `vnu`, `numero_viviendas`, `tipo_sub`) VALUES
(1, 0, '0000-00-00', '0', '0000-00-00', 0, 0, 0, 0),
(2, 222222, '2016-12-20', 'tramo 50', '2016-12-20', 9912445, 0, 1, 20),
(3, 333333, '2018-08-18', 'tramo 70', '2018-08-18', 923421, 0, 1, 10),
(4, 444444, '2018-08-18', 'tramo 80', '2018-08-18', 942352, 0, 1, 20),
(6, 666666, '2018-08-18', 'tramo 80', '2018-08-18', 95742, 0, 1, 20),
(7, 777777, '2019-05-04', 'tramo 50', '2019-05-04', 97234, 0, 1, 20),
(9, 999999, '2019-05-04', 'tramo 40', '2019-05-04', 96336, 0, 1, 10),
(10, 100001, '2019-05-04', 'tramo 40', '2019-05-04', 94528, 0, 1, 10);

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
(10, 1000, 'Nesus'),
(11, 1001, 'SEDRO');

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
(30, 12, 7300, '2021-03-03', 12, 1111, 0),
(31, 19, 1600, '2021-03-03', 19, 2222, 0),
(32, 20, 10500, '2021-03-03', 20, 3333, 0),
(33, 19, 1600, '2021-03-03', 19, 4444, 0),
(34, 15, 8500, '2021-03-03', 15, 5555, 0),
(35, 10, 0, '2021-03-03', 10, 6666, 0),
(36, 16, 400, '2021-03-03', 16, 7777, 0),
(37, 9, 6100, '2021-03-03', 9, 8888, 0),
(38, 15, 4250, '2021-03-03', 15, 9999, 0),
(39, 18, 5450, '2021-03-03', 18, 1000, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `cliente_medidor` (`id_medidor`),
  ADD KEY `clientes_ibfk_1` (`id_decreto`);

--
-- Indices de la tabla `datos_cobros`
--
ALTER TABLE `datos_cobros`
  ADD PRIMARY KEY (`id_datos`);

--
-- Indices de la tabla `decretos`
--
ALTER TABLE `decretos`
  ADD PRIMARY KEY (`id_decreto`);

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
-- AUTO_INCREMENT de la tabla `decretos`
--
ALTER TABLE `decretos`
  MODIFY `id_decreto` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `medidores`
--
ALTER TABLE `medidores`
  MODIFY `id_medidores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `registro_lectura`
--
ALTER TABLE `registro_lectura`
  MODIFY `id_registro` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `cliente_medidor` FOREIGN KEY (`id_medidor`) REFERENCES `medidores` (`id_medidores`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_decreto`) REFERENCES `decretos` (`id_decreto`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
