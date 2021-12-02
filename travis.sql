CREATE USER 'dev'@'localhost' IDENTIFIED BY 'dev';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'dev'@'localhost';

CREATE DATABASE IF NOT EXISTS `pruebas_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pruebas_db`;

CREATE TABLE IF NOT EXISTS `alumnos_tb1` (
  `id` int(2),
  `nombre` varchar(250),
  `email` varchar(250),
  `edad` int(3)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
