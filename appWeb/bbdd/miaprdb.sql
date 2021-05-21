-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 22-05-2021 a las 00:10:17
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
  `nombre` text COLLATE utf8_spanish_ci NOT NULL,
  `apellido_p` text COLLATE utf8_spanish_ci NOT NULL,
  `apellido_m` text COLLATE utf8_spanish_ci NOT NULL,
  `direccion` text COLLATE utf8_spanish_ci NOT NULL,
  `num_sitio` int(200) NOT NULL,
  `id_medidor` int(200) NOT NULL,
  `id_decreto` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `rut`, `nombre`, `apellido_p`, `apellido_m`, `direccion`, `num_sitio`, `id_medidor`, `id_decreto`) VALUES
(1, 100000000, 'Maria', 'Gonzalez', 'Fuentes', 'Av Los Castanos', 34, 1, 2),
(2, 100000001, 'Jose', 'Hernandez', 'Carmona', 'Calle uno', 23, 2, 3),
(3, 100000002, 'Antonio', 'Perez', 'Vasquez', 'Calle dos', 12, 3, 4),
(4, 100000003, 'Jorge', 'Alarcon', 'Gonzalez', 'Av Los Carreras', 54, 4, 5),
(5, 100000004, 'Juan', 'Gimenez', 'Hernandez', 'Los manzanos', 56, 5, 6),
(6, 100000005, 'Pedro', 'Patino', 'Perez', 'Av Los Manjares', 25, 6, 7),
(7, 100000006, 'Felipe', 'Garcia', 'Alarcon', 'Calle Metro dos', 67, 7, 8),
(8, 100000007, 'Ana', 'Vasquez', 'Gimenez', 'Calle cuatro', 72, 8, 9),
(9, 100000008, 'Luis', 'Carmona', 'Patino', 'Av Los Matasanos', 92, 9, 10),
(10, 100000009, 'Cecilia', 'Fuentes', 'Garcia', 'Calle cinco', 18, 10, 11),
(11, 100000010, 'ANTONIO', 'VEDAIN', 'VILA', 'Los manzanos', 34, 11, 12),
(12, 100000011, 'MANUEL', 'BLENGIO', 'SASTRE', 'Av Los Manjares', 23, 12, 13),
(13, 100000012, 'JOSE', 'MONTANS', 'FIORELLI', 'Calle Metro dos', 12, 13, 14),
(14, 100000013, 'FRANCISCO', 'LEMES', 'SARALEGUI', 'Calle cuatro', 67, 14, 15),
(15, 100000014, 'DAVID', 'DE LOS SANTOS', 'FERNANDEZ', 'Av Los Matasanos', 34, 15, 16),
(16, 100000015, 'JUAN', 'MOREIRA', 'APARICIO', 'Los manzanos', 45, 16, 17),
(17, 100000016, 'JOSE ANTONIO', 'BERDInAS', 'CHALELA', 'Av Los Manjares', 78, 17, 18),
(18, 100000017, 'JAVIER', 'SAIBENE', 'ROCCA', 'Calle Metro dos', 23, 18, 19),
(19, 100000018, 'DANIEL', 'BELEN', 'DE NICOLA', 'Calle cuatro', 82, 19, 20),
(20, 100000019, 'JOSE LUIS', 'FREDES', 'CEDRES', 'Av Los Matasanos', 81, 20, 21),
(21, 100000020, 'FRANCISCO JAVIER', 'CHIAPPARA', 'MACHADO', 'Los manzanos', 53, 21, 22),
(22, 100000021, 'CARLOS', 'MONTAnO', 'LUSARARIAN', 'Av Los Manjares', 25, 22, 23),
(23, 100000022, 'JESUS', 'MAY', 'MAZZULA', 'Calle Metro dos', 12, 23, 24),
(24, 100000023, 'ALEJANDRO', 'FIRPI', 'ROSSO', 'Calle cuatro', 21, 24, 25),
(25, 100000024, 'MIGUEL', 'RODRIGUEZ', 'LOPEZ', 'Av Los Matasanos', 54, 25, 26),
(26, 100000025, 'JOSE MANUEL', 'HAUW', 'AMY', 'Los manzanos', 23, 26, 27),
(27, 100000026, 'RAFAEL', 'FLUMINI', 'FERNANDEZ', 'Av Los Manjares', 12, 27, 28),
(28, 100000027, 'MIGUEL ANGEL', 'CASAS', 'BATLLE', 'Calle Metro dos', 43, 28, 1),
(29, 100000028, 'PEDRO', 'DE LA FUENTE', 'TEJERA', 'Calle cuatro', 54, 29, 1);

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
(1, 11111, '0000-00-00', '0', '0000-00-00', 0, 0, 0, 0),
(2, 22222, '2020-12-16', 'tramo 50', '2020-12-16', 9912445, 0, 1, 20),
(3, 33333, '2018-08-18', 'tramo 70', '2018-08-18', 923421, 0, 1, 10),
(4, 44444, '2018-08-18', 'tramo 80', '2018-08-18', 942352, 0, 1, 20),
(5, 55555, '2018-08-18', 'tramo 80', '2018-08-18', 912312, 0, 1, 10),
(6, 66666, '2018-08-18', 'tramo 80', '2018-08-18', 95742, 0, 1, 20),
(7, 77777, '2004-05-19', 'tramo 50', '2004-05-19', 97234, 0, 1, 20),
(8, 88888, '2004-05-19', 'tramo 50', '2004-05-19', 90479234, 0, 1, 10),
(9, 99999, '2004-05-19', 'tramo 40', '2004-05-19', 96336, 0, 1, 10),
(10, 111110, '2004-05-19', 'tramo 40', '2004-05-19', 94528, 0, 1, 10),
(11, 122221, '2005-05-19', 'tramo 50', '2005-05-19', 92720, 0, 1, 20),
(12, 133332, '2006-05-19', 'tramo 70', '2006-05-19', 90912, 0, 1, 10),
(13, 144443, '2007-05-19', 'tramo 80', '2007-05-19', 89104, 0, 1, 20),
(14, 155554, '2008-05-19', 'tramo 80', '2008-05-19', 87296, 0, 1, 10),
(15, 166665, '2009-05-19', 'tramo 80', '2009-05-19', 85488, 0, 1, 20),
(16, 177776, '2010-05-19', 'tramo 50', '2010-05-19', 83680, 0, 1, 20),
(17, 188887, '2011-05-19', 'tramo 50', '2011-05-19', 81872, 0, 1, 10),
(18, 199998, '2012-05-19', 'tramo 40', '2012-05-19', 80064, 0, 1, 10),
(19, 211109, '2013-05-19', 'tramo 40', '2013-05-19', 78256, 0, 1, 10),
(20, 222220, '2014-05-19', 'tramo 50', '2014-05-19', 76448, 0, 1, 20),
(21, 233331, '2015-05-19', 'tramo 70', '2015-05-19', 74640, 0, 1, 10),
(22, 244442, '2016-05-19', 'tramo 80', '2016-05-19', 72832, 0, 1, 20),
(23, 255553, '2017-05-19', 'tramo 80', '2017-05-19', 71024, 0, 1, 10),
(24, 266664, '2018-05-19', 'tramo 80', '2018-05-19', 69216, 0, 1, 20),
(25, 277775, '2019-05-19', 'tramo 50', '2019-05-19', 67408, 0, 1, 20),
(26, 288886, '2020-05-19', 'tramo 50', '2020-05-19', 65600, 0, 1, 10),
(27, 299997, '2021-05-19', 'tramo 40', '2021-05-19', 63792, 0, 1, 10),
(28, 311108, '2022-05-19', 'tramo 40', '2022-05-19', 61984, 0, 1, 10);

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
(11, 10001, 'Mast'),
(12, 10002, 'Tesla'),
(13, 10003, 'Cobro'),
(14, 10004, 'Proco'),
(15, 10005, 'Kia'),
(16, 10006, 'Nesus'),
(17, 10007, 'Tesla'),
(18, 10008, 'Nesus'),
(19, 10009, 'Mast'),
(20, 10010, 'Tesla'),
(21, 10011, 'Cobro'),
(22, 10012, 'Proco'),
(23, 10013, 'Kia'),
(24, 10014, 'Nesus'),
(25, 10015, 'Tesla'),
(26, 10016, 'Nesus'),
(27, 10017, 'Tesla'),
(28, 10018, 'Cobro'),
(29, 10019, 'Procol');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id_pago` int(200) NOT NULL,
  `fecha` date NOT NULL,
  `monto` int(200) NOT NULL,
  `saldo` int(200) NOT NULL,
  `monto_cancelado` int(11) NOT NULL,
  `id_cliente` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id_pago`, `fecha`, `monto`, `saldo`, `monto_cancelado`, `id_cliente`) VALUES
(1, '2022-02-21', 0, 0, 3250, 1),
(2, '2022-02-21', 0, 0, 3900, 2),
(3, '2022-02-21', 0, 4000, 0, 3),
(4, '2022-02-21', 0, 0, 2000, 4),
(5, '2022-02-21', 0, 2750, 0, 5),
(6, '2022-02-21', 0, 1725, 0, 6),
(7, '2022-02-21', 0, 2850, 0, 7),
(8, '2022-02-21', 0, 1500, 0, 8),
(9, '2022-02-21', 0, 2500, 0, 9),
(10, '2022-02-21', 0, 3700, 0, 10),
(11, '2022-02-21', 0, 0, 0, 11),
(12, '2022-02-21', 0, 0, 0, 12),
(13, '2022-02-21', 0, 0, 0, 13),
(14, '2022-02-21', 0, 0, 0, 14),
(15, '2022-02-21', 0, 0, 0, 15),
(16, '2022-02-21', 0, 0, 0, 16),
(17, '2022-02-21', 0, 0, 0, 17),
(18, '2022-02-21', 0, 0, 0, 18),
(19, '2022-02-21', 0, 0, 0, 19),
(20, '2022-02-21', 0, 0, 0, 20),
(21, '2022-02-21', 0, 0, 0, 21),
(22, '2022-02-21', 0, 0, 0, 22),
(23, '2022-02-21', 0, 0, 0, 23),
(24, '2022-02-21', 0, 0, 0, 24),
(25, '2022-02-21', 0, 0, 0, 25),
(26, '2022-02-21', 0, 0, 0, 26),
(27, '2022-02-21', 0, 0, 0, 27),
(28, '2022-02-21', 0, 0, 0, 28),
(29, '2022-02-21', 0, 0, 0, 29),
(31, '2025-03-21', 0, 0, 2850, 1),
(32, '2025-03-21', 0, 0, 3500, 2),
(33, '2025-03-21', 0, 3600, 0, 3),
(34, '2025-03-21', 0, 0, 1600, 4),
(35, '2025-03-21', 0, 2350, 0, 5),
(36, '2025-03-21', 0, 1325, 0, 6),
(37, '2025-03-21', 0, 2450, 0, 7),
(38, '2025-03-21', 0, 1100, 0, 8),
(39, '2025-03-21', 0, 2100, 0, 9),
(40, '2025-03-21', 0, 3300, 0, 10),
(41, '2025-03-21', 0, 0, 0, 11),
(42, '2025-03-21', 0, 0, 0, 12),
(43, '2025-03-21', 0, 0, 0, 13),
(44, '2025-03-21', 0, 0, 0, 14),
(45, '2025-03-21', 0, 0, 0, 15),
(46, '2025-03-21', 0, 0, 0, 16),
(47, '2025-03-21', 0, 0, 0, 17),
(48, '2025-03-21', 0, 0, 0, 18),
(49, '2025-03-21', 0, 0, 0, 19),
(50, '2025-03-21', 0, 0, 0, 20),
(51, '2025-03-21', 0, 0, 0, 21),
(52, '2025-03-21', 0, 0, 0, 22),
(53, '2025-03-21', 0, 0, 0, 23),
(54, '2025-03-21', 0, 0, 0, 24),
(55, '2025-03-21', 0, 0, 0, 25),
(56, '2025-03-21', 0, 0, 0, 26),
(57, '2025-03-21', 0, 0, 0, 27),
(58, '2025-03-21', 0, 0, 0, 28),
(59, '2025-03-21', 0, 0, 0, 29),
(61, '2010-04-21', 0, 0, 100, 1),
(62, '2010-04-21', 0, 0, 3800, 2),
(63, '2010-04-21', 0, 3900, 0, 3),
(64, '2010-04-21', 0, 0, 1700, 4),
(65, '2010-04-21', 0, 2650, 0, 5),
(66, '2010-04-21', 0, 1625, 0, 6),
(67, '2010-04-21', 0, 2750, 0, 7),
(68, '2010-04-21', 0, 1400, 0, 8),
(69, '2010-04-21', 0, 2400, 0, 9),
(70, '2010-04-21', 0, 3600, 0, 10),
(71, '2010-04-21', 0, 0, 0, 11),
(72, '2010-04-21', 0, 0, 0, 12),
(73, '2010-04-21', 0, 0, 0, 13),
(74, '2010-04-21', 0, 0, 0, 14),
(75, '2010-04-21', 0, 0, 0, 15),
(76, '2010-04-21', 0, 0, 0, 16),
(77, '2010-04-21', 0, 0, 0, 17),
(78, '2010-04-21', 0, 0, 0, 18),
(79, '2010-04-21', 0, 0, 0, 19),
(80, '2010-04-21', 0, 0, 0, 20),
(81, '2010-04-21', 0, 0, 0, 21),
(82, '2010-04-21', 0, 0, 0, 22),
(83, '2010-04-21', 0, 0, 0, 23),
(84, '2010-04-21', 0, 0, 0, 24),
(85, '2010-04-21', 0, 0, 0, 25),
(86, '2010-04-21', 0, 0, 0, 26),
(87, '2010-04-21', 0, 0, 0, 27),
(88, '2010-04-21', 0, 0, 0, 28),
(89, '2010-04-21', 0, 0, 0, 29),
(91, '2010-05-21', 6300, 6300, 0, 1),
(92, '2011-05-21', 7600, 0, 7600, 2),
(93, '2012-05-21', 7800, 7800, 0, 3),
(94, '2013-05-21', 3800, 0, 3800, 4),
(95, '2014-05-21', 5300, 5300, 0, 5),
(96, '2015-05-21', 3250, 3250, 0, 6),
(97, '2016-05-21', 5500, 5500, 0, 7),
(98, '2017-05-21', 2800, 2800, 0, 8),
(99, '2018-05-21', 4800, 4800, 0, 9),
(100, '2019-05-21', 7200, 7200, 0, 10),
(101, '2020-05-21', 3800, 3800, 0, 11),
(102, '2021-05-21', 6600, 6600, 0, 12),
(103, '2022-05-21', 2800, 2800, 0, 13),
(104, '2023-05-21', 2900, 2900, 0, 14),
(105, '2024-05-21', 5800, 5800, 0, 15),
(106, '2025-05-21', 4300, 4300, 0, 16),
(107, '2026-05-21', 8100, 8100, 0, 17),
(108, '2027-05-21', 1800, 1800, 0, 18),
(109, '2028-05-21', 7692, 7692, 0, 19),
(110, '2029-05-21', 3209, 3209, 0, 20),
(111, '2030-05-21', 1035, 1035, 0, 21),
(112, '2031-05-21', 9642, 9642, 0, 22),
(113, '2001-06-21', 5800, 5800, 0, 23),
(114, '2002-06-21', 7321, 7321, 0, 24),
(115, '2003-06-21', 8890, 0, 8890, 25),
(116, '2004-06-21', 2800, 2800, 0, 26),
(117, '2005-06-21', 1200, 1200, 0, 27),
(118, '2006-06-21', 2580, 2580, 0, 28),
(119, '2007-06-21', 3690, 3690, 0, 29);

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
(1, 12, 6500, '2022-02-21', 10, 1111, 0),
(2, 13, 7800, '2022-02-21', 12, 2222, 0),
(3, 12, 6500, '2022-03-21', 10, 1111, 0),
(4, 13, 7800, '2022-03-21', 12, 2222, 0),
(5, 12, 6500, '2022-04-21', 10, 1111, 0),
(6, 13, 7800, '2022-04-21', 12, 2222, 0),
(7, 12, 6500, '2022-05-21', 10, 1111, 0),
(8, 13, 7800, '2022-05-21', 12, 2222, 0),
(9, 12, 6500, '2022-06-21', 10, 1111, 0),
(10, 13, 7800, '2022-06-21', 12, 2222, 0);

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
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `pagos_clientes` (`id_cliente`);

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
  MODIFY `id_cliente` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `datos_cobros`
--
ALTER TABLE `datos_cobros`
  MODIFY `id_datos` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `decretos`
--
ALTER TABLE `decretos`
  MODIFY `id_decreto` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `medidores`
--
ALTER TABLE `medidores`
  MODIFY `id_medidores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pago` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

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
  ADD CONSTRAINT `cliente_medidor` FOREIGN KEY (`id_medidor`) REFERENCES `medidores` (`id_medidores`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_decreto`) REFERENCES `decretos` (`id_decreto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `pagos_clientes` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
