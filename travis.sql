CREATE USER 'dev'@'localhost' IDENTIFIED BY 'dev';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'dev'@'localhost';

CREATE DATABASE IF NOT EXISTS `pruebas_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pruebas_db`;

CREATE TABLE IF NOT EXISTS `alumnos_tb1` (
  `id` int(20),
  `nombre` varchar(80),
  `email` varchar(90),
  `edad` int(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
