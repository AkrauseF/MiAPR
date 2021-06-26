-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 27-06-2021 a las 01:08:58
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
(1, 151634982, 'Maria', 'Gonzalez', 'Fuentes', 'Av Los Castanos', 34, 1, 1),
(2, 151639690, 'Jose', 'Hernandez', 'Carmona', 'Calle uno', 23, 2, 3),
(3, 191600695, 'Antonio', 'Perez', 'Vasquez', 'Calle dos', 12, 3, 4),
(4, 191601874, 'Jorge', 'Alarcon', 'Gonzalez', 'Av Los Carreras', 54, 4, 5),
(5, 201658896, 'Juan', 'Gimenez', 'Hernandez', 'Los manzanos', 56, 5, 6),
(6, 201658903, 'Pedro', 'Patino', 'Perez', 'Av Los Manjares', 25, 6, 7),
(7, 202871496, 'Felipe', 'Garcia', 'Alarcon', 'Calle Metro dos', 67, 7, 8),
(8, 202987818, 'Ana', 'Vasquez', 'Gimenez', 'Calle cuatro', 72, 8, 9),
(9, 202997603, 'Luis', 'Carmona', 'Patino', 'Av Los Matasanos', 92, 9, 10),
(10, 204148325, 'Cecilia', 'Fuentes', 'Garcia', 'Calle cinco', 18, 10, 11);

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
  `numero` bigint(200) NOT NULL,
  `marca` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `medidores`
--

INSERT INTO `medidores` (`id_medidores`, `numero`, `marca`) VALUES
(1, 533864831367721, 'Tesla'),
(2, 420873267014365, 'Proco'),
(3, 680288263122386, 'Mast'),
(4, 115388020823359, 'Tesla'),
(5, 866600193607125, 'Cobro'),
(6, 336845885414860, 'Proco'),
(7, 585753553502448, 'Kia'),
(8, 891179101787755, 'Nesus'),
(9, 937072357246191, 'Tesla'),
(10, 450861742151811, 'Nesus');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operadores`
--

CREATE TABLE `operadores` (
  `id_operadores` int(200) NOT NULL,
  `rut` int(200) NOT NULL,
  `nombre` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `apellidoP` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `apellidoM` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `contrasena` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `operadores`
--

INSERT INTO `operadores` (`id_operadores`, `rut`, `nombre`, `apellidoP`, `apellidoM`, `direccion`, `usuario`, `contrasena`) VALUES
(1, 178261835, 'Juana', 'Lagos', 'Martinez', 'av los castanos', 'oper', '123'),
(2, 178261835, 'Daniel', 'Martinez', 'Pardo', 'Av los didos', 'oper2', '123');

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
(1, '2021-03-11', 0, 3250, 0, 1),
(2, '2021-03-11', 0, 0, 2400, 2),
(3, '2021-03-11', 0, 0, 3000, 3),
(4, '2021-03-11', 0, 0, 1100, 4),
(5, '2021-03-11', 0, 0, 4500, 5),
(6, '2021-03-11', 0, 0, 2100, 6),
(7, '2021-03-11', 0, 0, 3020, 7),
(8, '2021-03-11', 0, 1500, 0, 8),
(9, '2021-03-11', 0, 2500, 0, 9),
(10, '2021-03-11', 0, 3700, 0, 10),
(11, '2021-04-18', 0, 3250, 0, 1),
(12, '2021-04-18', 0, 2600, 0, 2),
(13, '2021-04-18', 0, 3200, 0, 3),
(14, '2021-04-18', 0, 0, 1300, 4),
(15, '2021-04-18', 0, 0, 4700, 5),
(16, '2021-04-18', 0, 0, 2300, 6),
(17, '2021-04-18', 0, 0, 3220, 7),
(18, '2021-04-18', 0, 1500, 0, 8),
(19, '2021-04-18', 0, 2500, 0, 9),
(20, '2021-04-18', 0, 3700, 0, 10),
(21, '2021-05-20', 0, 3700, 0, 1),
(22, '2021-05-20', 0, 3250, 0, 2),
(23, '2021-05-20', 0, 3900, 0, 3),
(24, '2021-05-20', 0, 4000, 0, 4),
(25, '2021-05-20', 0, 2000, 0, 5),
(26, '2021-05-20', 0, 2750, 0, 6),
(27, '2021-05-20', 0, 1725, 0, 7),
(28, '2021-05-20', 0, 2850, 0, 8),
(29, '2021-05-20', 0, 1500, 0, 9),
(30, '2021-05-20', 0, 2500, 0, 10);

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
  `num_medidor` bigint(200) NOT NULL,
  `pago` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `registro_lectura`
--

INSERT INTO `registro_lectura` (`id_registro`, `lectura`, `monto`, `fecha`, `metros_cubicos`, `num_medidor`, `pago`) VALUES
(1, 12, 6500, '2021-03-21', 10, 533864831367721, 0),
(2, 13, 7800, '2021-03-21', 12, 420873267014365, 0),
(3, 12, 6500, '2021-03-21', 10, 680288263122386, 0),
(4, 13, 7800, '2021-03-21', 12, 115388020823359, 0),
(5, 12, 6500, '2021-03-21', 10, 866600193607125, 0),
(6, 13, 7800, '2021-03-21', 12, 336845885414860, 0),
(7, 12, 6500, '2021-03-21', 10, 585753553502448, 0),
(8, 13, 7800, '2021-03-21', 12, 891179101787755, 0),
(9, 12, 6500, '2021-03-21', 10, 937072357246191, 0),
(10, 13, 7800, '2021-03-21', 12, 450861742151811, 0),
(11, 12, 6500, '2021-04-21', 10, 533864831367721, 0),
(12, 13, 7800, '2021-04-21', 12, 420873267014365, 0),
(13, 12, 6500, '2021-04-21', 10, 680288263122386, 0),
(14, 13, 7800, '2021-04-21', 12, 115388020823359, 0),
(15, 12, 6500, '2021-04-21', 10, 866600193607125, 0),
(16, 13, 7800, '2021-04-21', 12, 336845885414860, 0),
(17, 12, 6500, '2021-04-21', 10, 585753553502448, 0),
(18, 13, 7800, '2021-04-21', 12, 891179101787755, 0),
(19, 12, 6500, '2021-04-21', 10, 937072357246191, 0),
(20, 13, 7800, '2021-04-21', 12, 450861742151811, 0),
(21, 12, 6500, '2021-05-21', 10, 533864831367721, 0),
(22, 13, 7800, '2021-05-21', 12, 420873267014365, 0),
(23, 12, 6500, '2021-05-21', 10, 680288263122386, 0),
(24, 13, 7800, '2021-05-21', 12, 115388020823359, 0),
(25, 12, 6500, '2021-05-21', 10, 866600193607125, 0),
(26, 13, 7800, '2021-05-21', 12, 336845885414860, 0),
(27, 12, 6500, '2021-05-21', 10, 585753553502448, 0),
(28, 13, 7800, '2021-05-21', 12, 891179101787755, 0),
(29, 12, 6500, '2021-05-21', 10, 937072357246191, 0),
(30, 13, 7800, '2021-05-21', 12, 450861742151811, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuarios` int(200) NOT NULL,
  `rut` int(200) NOT NULL,
  `nombre` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `apellidoP` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `apellidoM` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(200) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `contrasena` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuarios`, `rut`, `nombre`, `apellidoP`, `apellidoM`, `direccion`, `usuario`, `contrasena`) VALUES
(17, 151634982, 'Juana', 'Martinez', 'Martinez', 'av los castanos', 'sec', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(18, 178261835, 'admin', 'admin', 'admin', 'admin', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

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
-- Indices de la tabla `operadores`
--
ALTER TABLE `operadores`
  ADD PRIMARY KEY (`id_operadores`);

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
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuarios`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
  MODIFY `id_medidores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `operadores`
--
ALTER TABLE `operadores`
  MODIFY `id_operadores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pago` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `registro_lectura`
--
ALTER TABLE `registro_lectura`
  MODIFY `id_registro` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuarios` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

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
