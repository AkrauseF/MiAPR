-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 21-08-2021 a las 03:17:20
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
(1, 9952345, 'MARIA', 'SANSEVERINO', 'MANSILLA', 'SALOMON SACK 351', 34, 1, 1),
(2, 12496093, 'JULIO', 'FIANDRA', 'CUCULIE', 'AV. EL GUANACO 3100', 23, 2, 2),
(3, 13855541, 'LUIS', 'BONILLA', 'MARABOTTO', 'GRAN AVENIDA JOSE MIGUEL CARRERA 6555', 12, 3, 3),
(4, 25433282, 'HUGO', 'PRADERI', 'GONZALEZ', 'AV. VICUÑA MACKENNA 9090', 54, 4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control_tran`
--

CREATE TABLE `control_tran` (
  `id` int(1) NOT NULL,
  `valor` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `control_tran`
--

INSERT INTO `control_tran` (`id`, `valor`) VALUES
(1, 1);

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
(1, 591548000000000, 'Tesla'),
(2, 828304000000000, 'Proco'),
(3, 715121000000000, 'Mast'),
(4, 255108000000000, 'Tesla');

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
(1, 142199181, 'Maria', 'Gonzalez', 'Fuentes', 'Av Los Castanos', 'oper1', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(2, 204148325, 'Jose', 'Carmona', 'Carmona', 'Calle uno', 'oper2', '1e00a562a0ffc0f766b16302f8a76161252974360c29772e51b1eb6900a6c4d3'),
(3, 142199181, 'admin', 'admin', 'admin', 'admin', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

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
(1, '2021-02-21', 0, 0, 7012, 1),
(2, '2021-02-21', 0, 0, 4264, 2),
(3, '2021-02-21', 0, 0, 6236, 3),
(4, '2021-02-21', 0, 0, 2631, 4),
(201, '2021-03-21', 0, 0, 9626, 1),
(202, '2021-03-21', 0, 0, 9234, 2),
(203, '2021-03-21', 0, 0, 6640, 3),
(204, '2021-03-21', 0, 0, 3973, 4),
(401, '2021-04-21', 4134, 0, 0, 1),
(402, '2021-04-21', 9891, 0, 0, 2),
(403, '2021-04-21', 4759, 0, 0, 3),
(404, '2021-04-21', 8680, 0, 0, 4),
(601, '2021-08-16', 7300, 7300, 0, 1),
(602, '2021-08-16', 7300, 7300, 0, 1),
(603, '2021-08-16', 7300, 7300, 0, 1),
(604, '2021-08-16', 7300, 7300, 0, 1),
(605, '2021-08-16', 0, 0, 0, 2),
(606, '2021-08-16', 3650, 3650, 0, 3),
(607, '2021-08-16', 0, 0, 0, 4),
(608, '2021-08-16', 2500, 2500, 0, 1),
(609, '2021-08-16', 0, 0, 0, 2),
(610, '2021-08-16', 1650, 1650, 0, 3),
(611, '2021-08-16', 400, 400, 0, 4),
(612, '2021-08-16', 2500, 2500, 0, 1),
(613, '2021-08-16', 0, 0, 0, 2),
(614, '2021-08-16', 1650, 1650, 0, 3),
(615, '2021-08-16', 400, 400, 0, 4),
(616, '2021-08-20', 6900, 6900, 0, 1),
(617, '2021-08-20', 0, 0, 0, 2),
(618, '2021-08-20', 3450, 3450, 0, 3),
(619, '2021-08-20', 0, 0, 0, 4),
(620, '2021-08-20', 6900, 6900, 0, 1),
(621, '2021-08-20', 0, 0, 0, 2),
(622, '2021-08-20', 3450, 3450, 0, 3),
(623, '2021-08-20', 0, 0, 0, 4),
(624, '2021-08-20', 6900, 6900, 0, 1),
(625, '2021-08-20', 0, 0, 0, 2),
(626, '2021-08-20', 3450, 3450, 0, 3),
(627, '2021-08-20', 0, 0, 0, 4),
(628, '2021-08-20', 6900, 6900, 0, 1),
(629, '2021-08-20', 0, 0, 0, 2),
(630, '2021-08-20', 3450, 3450, 0, 3),
(631, '2021-08-20', 0, 0, 0, 4),
(632, '2021-08-20', 6900, 6900, 0, 1),
(633, '2021-08-20', 0, 0, 0, 2),
(634, '2021-08-20', 3450, 3450, 0, 3),
(635, '2021-08-20', 0, 0, 0, 4),
(636, '2021-08-20', 6900, 6900, 0, 1),
(637, '2021-08-20', 0, 0, 0, 2),
(638, '2021-08-20', 3450, 3450, 0, 3),
(639, '2021-08-20', 0, 0, 0, 4),
(640, '2021-08-20', 6900, 6900, 0, 1),
(641, '2021-08-20', 0, 0, 0, 2),
(642, '2021-08-20', 3450, 3450, 0, 3),
(643, '2021-08-20', 0, 0, 0, 4);

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
(1, 23, 6900, '2021-08-20', 11, 591548000000000, 0),
(2, 16, 0, '2021-08-20', 3, 828304000000000, 0),
(3, 25, 3450, '2021-08-20', 11, 715121000000000, 0),
(4, 23, 0, '2021-08-20', 7, 255108000000000, 0);

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
(1, 142199181, 'Andres ', 'Campos', 'Campos', 'Av Cuatro 234', 'sec', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(2, 201658903, 'Jesus', 'Casanova', 'Casanova', 'Av cuatro', 'sec1', 'f1cb1e433a589d1a70a868f6ffe174f07dcb9ac7f18482905697f25be72bd2e3'),
(3, 204148325, 'Esteban', 'Morande', 'Castillo', 'Av Cinco', 'sec2', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
(4, 204148325, 'Jesus', 'Casanova', 'Casanova', 'Av cuatro', 'sec7', 'ad2a3c7510122073d675533aa98c34691f7009dc0eeea2eb78ffc345edb41224');

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
-- Indices de la tabla `control_tran`
--
ALTER TABLE `control_tran`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id_cliente` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT de la tabla `control_tran`
--
ALTER TABLE `control_tran`
  MODIFY `id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id_medidores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT de la tabla `operadores`
--
ALTER TABLE `operadores`
  MODIFY `id_operadores` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pago` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=644;

--
-- AUTO_INCREMENT de la tabla `registro_lectura`
--
ALTER TABLE `registro_lectura`
  MODIFY `id_registro` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuarios` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
