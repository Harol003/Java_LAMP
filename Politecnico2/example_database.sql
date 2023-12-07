-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 07-12-2023 a las 15:49:17
-- Versión del servidor: 8.0.35-0ubuntu0.22.04.1
-- Versión de PHP: 8.1.2-1ubuntu2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `example_database`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `todo_list`
--

CREATE TABLE `todo_list` (
  `item_id` int NOT NULL,
  `content` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `todo_list`
--

INSERT INTO `todo_list` (`item_id`, `content`) VALUES
(1, 'Edicion de Actividad'),
(3, 'Prueba de Ingreso de datos 2'),
(4, '7 de diciembre de 2023');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`) VALUES
(1, 'ejemplo_usuario', 'contrasena_segura'),
(3, 'Harol', '$2y$10$BfBVPtuqWGrtvzT/42CeG.Xv6LApm84PhiV3R9ZCY2zVq00QT1Az6'),
(4, 'Adriana', '$2y$10$R2S1u/3OclC6QJiFgUbal.5ImN6ULvWc72aC1cPuBRfFvWNEv1kvK'),
(5, 'Paola', '$2y$10$3Pll5eI6Nm3APAHYjU93MuONOUXmqkoEkV78pUE4cab6VfTPdN91m'),
(6, 'Carlos', '$2y$10$2WQRKoDDbq0maZZINNiZ2es.HT.uixFHXjz9AEII92PzGRqHYV0.u'),
(7, 'carlos2', '$2y$10$uMMMJE5lDbTlYyG7ANbW/.bBYkdC02.bw0ga9oT7Ytr42lknWr2s6'),
(8, 'Politecnico3', '$2y$10$H5OiYmHYqKT5Wo9D4SW8GeKe2P5Y1vj9nNT7xUOwhRnZr0y0dson2');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `todo_list`
--
ALTER TABLE `todo_list`
  ADD PRIMARY KEY (`item_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `todo_list`
--
ALTER TABLE `todo_list`
  MODIFY `item_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
