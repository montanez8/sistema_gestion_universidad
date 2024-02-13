-- MariaDB dump 10.19-11.2.3-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: ProyectoCampusJava
-- ------------------------------------------------------
-- Server version	11.2.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Alumno`
--

DROP TABLE IF EXISTS `Alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Alumno` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `tipoDocumento` enum('CEDULA','CEDULA_EXTRANJERIA','PASAPORTE','TARJETA_IDENTIDAD') DEFAULT NULL,
                          `numeroDocumento` varchar(50) NOT NULL,
                          `nombres` varchar(255) NOT NULL,
                          `apellidos` varchar(255) NOT NULL,
                          `ciudadResidencia` varchar(255) DEFAULT NULL,
                          `direccion` varchar(255) DEFAULT NULL,
                          `numeroTelefono` varchar(20) DEFAULT NULL,
                          `fechaNacimiento` date DEFAULT NULL,
                          `genero` char(1) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `unique_alumno_documento` (`numeroDocumento`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alumno`
--

LOCK TABLES `Alumno` WRITE;
/*!40000 ALTER TABLE `Alumno` DISABLE KEYS */;
INSERT INTO `Alumno` VALUES
                         (1,'CEDULA','111222333','Luisa','Martínez','Ciudad X','Avenida 567','555-3456','1995-12-03','F'),
                         (2,'CEDULA','444555666','Roberto','González','Ciudad Y','Avenida 890','555-7890','1998-07-18','M'),
                         (3,'CEDULA','110238804','Laura','López','Ciudad Z','Avenida 123','555-2345','2000-04-25','f'),
                         (4,'CEDULA','123456785','Sofia','Gomez','Bogota','Calle 129','1234573','2006-01-01','F'),
                         (5,'CEDULA','123456786','Juan','Rodriguez','Bogota','Calle 130','1234574','2007-01-01','M'),
                         (6,'CEDULA','123456787','Laura','Martinez','Bogota','Calle 131','1234575','2008-01-01','F'),
                         (7,'CEDULA','123456788','Jose','Gonzalez','Bogota','Calle 132','1234576','2009-01-01','M'),
                         (8,'CEDULA','1102456789','carlos','moreno','bucaramnga','323','4234523','2000-12-08','m'),
                         (9,'TARJETA_IDENTIDAD','123456789','Juan','Perez','Bogota','Calle 123','1234567','2007-01-01','M'),
                         (11,'CEDULA_EXTRANJERIA','12034455','Jorge','Martinez','Bogota','cr','321456','1998-06-12','M'),
                         (12,'CEDULA','123456780','Pedro','Gomez','Bogota','Calle 124','1234568','2001-01-01','M'),
                         (13,'CEDULA','123456781','Maria','Rodriguez','Bogota','Calle 125','1234569','2002-01-01','F'),
                         (14,'CEDULA','123456782','Carlos','Martinez','Bogota','Calle 126','1234570','2003-01-01','M'),
                         (15,'CEDULA','123456783','Ana','Gonzalez','Bogota','Calle 127','1234571','2004-01-01','F'),
                         (16,'CEDULA','123456784','Luis','Perez','Bogota','Calle 128','1234572','2005-01-01','M'),
                         (17,'CEDULA','123456798','Carmen','Perez','Bogota','Calle 133','1234577','2010-01-01','F'),
                         (35,'CEDULA','22423423','carlos','moreno','bucarmnaga','23','26666','1998-10-22','M');
/*!40000 ALTER TABLE `Alumno` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER validar_edad_alumno
    BEFORE INSERT ON Alumno
    FOR EACH ROW
BEGIN
    IF NEW.tipoDocumento = 'TARJETA_IDENTIDAD' AND TIMESTAMPDIFF(YEAR, NEW.fechaNacimiento, CURDATE()) >= 18 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: Si el tipo de documento es TARJETA_IDENTIDAD, el estudiante deberá ser menor de edad.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `AlumnoPrograma`
--

DROP TABLE IF EXISTS `AlumnoPrograma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AlumnoPrograma` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `alumno_id` int(11) DEFAULT NULL,
                                  `programa_id` int(11) DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `alumno_id` (`alumno_id`),
                                  KEY `programa_id` (`programa_id`),
                                  CONSTRAINT `AlumnoPrograma_ibfk_1` FOREIGN KEY (`alumno_id`) REFERENCES `Alumno` (`id`),
                                  CONSTRAINT `AlumnoPrograma_ibfk_2` FOREIGN KEY (`programa_id`) REFERENCES `Programa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AlumnoPrograma`
--

LOCK TABLES `AlumnoPrograma` WRITE;
/*!40000 ALTER TABLE `AlumnoPrograma` DISABLE KEYS */;
/*!40000 ALTER TABLE `AlumnoPrograma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Asignatura`
--

DROP TABLE IF EXISTS `Asignatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Asignatura` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `nombre` varchar(255) NOT NULL,
                              `codigo` varchar(50) NOT NULL,
                              `creditos` int(11) DEFAULT NULL,
                              `cupoDisponible` int(11) DEFAULT NULL,
                              `profesor_id` int(11) DEFAULT NULL,
                              `programa_id` int(11) DEFAULT NULL,
                              `curso_id` int(11) DEFAULT NULL,
                              `periodo_id` int(11) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `unique_asignatura_codigo` (`codigo`),
                              KEY `programa_id` (`programa_id`),
                              KEY `curso_id` (`curso_id`),
                              KEY `periodo_id` (`periodo_id`),
                              KEY `Asignatura_ibfk_1` (`profesor_id`),
                              CONSTRAINT `Asignatura_ibfk_1` FOREIGN KEY (`profesor_id`) REFERENCES `Profesor` (`id`) ON DELETE CASCADE,
                              CONSTRAINT `Asignatura_ibfk_2` FOREIGN KEY (`programa_id`) REFERENCES `Programa` (`id`),
                              CONSTRAINT `Asignatura_ibfk_3` FOREIGN KEY (`curso_id`) REFERENCES `Curso` (`id`),
                              CONSTRAINT `Asignatura_ibfk_4` FOREIGN KEY (`periodo_id`) REFERENCES `Periodo` (`id`),
                              CONSTRAINT `fk_profesor` FOREIGN KEY (`profesor_id`) REFERENCES `Profesor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Asignatura`
--

LOCK TABLES `Asignatura` WRITE;
/*!40000 ALTER TABLE `Asignatura` DISABLE KEYS */;
INSERT INTO `Asignatura` VALUES
                             (1,'Programación Avanzada','PR1-C1-PEP22-01-Programación Avanzada',4,30,1,1,1,1),
                             (2,'Álgebra Lineal I','PR1-C2-PEP22-01-Álgebra Lineal I',3,25,2,1,2,1),
                             (6,'Matematicas','PR1-C1-PEP22-01-Matematicas',3,30,1,1,1,1),
                             (7,'PogramacioPoo','PR3-C1-PEP24-02-PogramacioPoo',4,30,2,3,1,10),
                             (9,'Física','PR1-C5-PEP22-01-Física',3,30,1,1,5,1),
                             (10,'Química','PR3-C6-PEP24-02-Química',4,30,2,3,6,10),
                             (11,'Mecanica','PR1-C2-PEP22-01-Mecanica',4,30,1,1,2,1),
                             (12,'videojuegos II','PR3-C2-PEP24-02-videojuegos II',2,30,2,3,2,10),
                             (13,'Videojuegos','PR1-C1-PEP22-01-Videojuegos',2,30,1,1,1,1),
                             (14,'videojuegos II','PR3-C1-PEP24-02-videojuegos II',2,30,2,3,1,10),
                             (15,'Sociología I','PR1-C6-PEP22-01-Sociología I',3,30,1,1,6,1),
                             (16,'Sociología II','PR3-C6-PEP24-02-Sociología II',3,30,2,3,6,10),
                             (17,'Psicología I','PR1-C6-PEP22-01-Psicología I',3,30,1,1,6,1),
                             (18,'Psicología II','PR3-C6-PEP24-02-Psicología II',3,30,2,3,6,10);
/*!40000 ALTER TABLE `Asignatura` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER before_insert_asignatura
    BEFORE INSERT ON Asignatura
    FOR EACH ROW
BEGIN
    DECLARE periodo_codigo VARCHAR(50);
    SELECT codigo INTO periodo_codigo FROM Periodo WHERE id = NEW.periodo_id;
    SET NEW.codigo = CONCAT('PR',NEW.programa_id, '-', 'C',NEW.curso_id, '-','PE', periodo_codigo, '-', NEW.nombre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER before_insert_asignatura_update
    BEFORE UPDATE ON Asignatura
    FOR EACH ROW
BEGIN
    DECLARE periodo_codigo VARCHAR(50);
    SELECT codigo INTO periodo_codigo FROM Periodo WHERE id = NEW.periodo_id;
    SET NEW.codigo = CONCAT('PR',NEW.programa_id, '-', 'C',NEW.curso_id, '-','PE', periodo_codigo, '-', NEW.nombre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Curso`
--

DROP TABLE IF EXISTS `Curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Curso` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `nombre` varchar(255) NOT NULL,
                         `guiaCatedra` text DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `unique_curso_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Curso`
--

LOCK TABLES `Curso` WRITE;
/*!40000 ALTER TABLE `Curso` DISABLE KEYS */;
INSERT INTO `Curso` VALUES
                        (1,'Introducción a la Programación','Guía introductoria al mundo de la programación'),
                        (2,'Álgebra Lineal','Conceptos fundamentales de álgebra lineal'),
                        (3,'Historia Antigua','Estudio de las civilizaciones antiguas'),
                        (4,'Cálculo Diferencial','Introducción a las derivadas y sus aplicaciones'),
                        (5,'Física Clásica','Estudio de la mecánica newtoniana, termodinámica y ondas'),
                        (6,'Química General','Conceptos fundamentales de química inorgánica');
/*!40000 ALTER TABLE `Curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Departamento`
--

DROP TABLE IF EXISTS `Departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Departamento` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `nombre` varchar(255) NOT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `unique_departamento_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departamento`
--

LOCK TABLES `Departamento` WRITE;
/*!40000 ALTER TABLE `Departamento` DISABLE KEYS */;
INSERT INTO `Departamento` VALUES
                               (7,'Departamento de Biología'),
                               (4,'Departamento de Filosofia'),
                               (5,'Departamento de Fisica'),
                               (8,'Departamento de Geología'),
                               (3,'Departamento de Historia'),
                               (2,'Departamento de Informática'),
                               (1,'Departamento de Matemáticas'),
                               (10,'Departamento de Psicología'),
                               (6,'Departamento de Química'),
                               (9,'Departamento de Sociología');
/*!40000 ALTER TABLE `Departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Edificio`
--

DROP TABLE IF EXISTS `Edificio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Edificio` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Edificio`
--

LOCK TABLES `Edificio` WRITE;
/*!40000 ALTER TABLE `Edificio` DISABLE KEYS */;
INSERT INTO `Edificio` VALUES
                           (1,'A'),
                           (2,'B'),
                           (3,'C'),
                           (4,'E'),
                           (5,'D');
/*!40000 ALTER TABLE `Edificio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Horario`
--

DROP TABLE IF EXISTS `Horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Horario` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `dia` int(11) DEFAULT NULL,
                           `horaInicio` time NOT NULL,
                           `horaFin` time NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Horario`
--

LOCK TABLES `Horario` WRITE;
/*!40000 ALTER TABLE `Horario` DISABLE KEYS */;
INSERT INTO `Horario` VALUES
                          (1,1,'08:00:00','10:00:00'),
                          (2,2,'10:30:00','12:30:00'),
                          (3,3,'14:00:00','16:00:00'),
                          (4,1,'07:00:00','09:00:00'),
                          (5,6,'16:00:00','18:00:00'),
                          (6,2,'12:00:00','13:00:00'),
                          (7,4,'08:00:00','10:00:00'),
                          (8,5,'10:30:00','12:30:00'),
                          (9,1,'14:00:00','16:00:00'),
                          (10,2,'07:00:00','09:00:00'),
                          (11,3,'16:00:00','18:00:00'),
                          (12,4,'12:00:00','13:00:00');
/*!40000 ALTER TABLE `Horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Matricula`
--

DROP TABLE IF EXISTS `Matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Matricula` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `alumno_id` int(11) DEFAULT NULL,
                             `asignatura_id` int(11) DEFAULT NULL,
                             `periodo_id` int(11) DEFAULT NULL,
                             `salon_id` int(11) DEFAULT NULL,
                             `horario_id` int(11) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `periodo_id` (`periodo_id`),
                             KEY `salon_id` (`salon_id`),
                             KEY `horario_id` (`horario_id`),
                             KEY `Matricula_ibfk_1` (`alumno_id`),
                             KEY `Matricula_ibfk_2` (`asignatura_id`),
                             CONSTRAINT `Matricula_ibfk_1` FOREIGN KEY (`alumno_id`) REFERENCES `Alumno` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `Matricula_ibfk_2` FOREIGN KEY (`asignatura_id`) REFERENCES `Asignatura` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `Matricula_ibfk_3` FOREIGN KEY (`periodo_id`) REFERENCES `Periodo` (`id`),
                             CONSTRAINT `Matricula_ibfk_4` FOREIGN KEY (`salon_id`) REFERENCES `Salon` (`id`),
                             CONSTRAINT `Matricula_ibfk_5` FOREIGN KEY (`horario_id`) REFERENCES `Horario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Matricula`
--

LOCK TABLES `Matricula` WRITE;
/*!40000 ALTER TABLE `Matricula` DISABLE KEYS */;
INSERT INTO `Matricula` VALUES
                            (4,1,1,1,1,1),
                            (5,2,2,2,2,2),
                            (8,3,1,3,1,1),
                            (9,8,7,3,3,2),
                            (12,7,6,1,5,6),
                            (13,8,7,2,6,7),
                            (18,12,11,7,10,11),
                            (19,11,10,7,9,10),
                            (24,13,12,7,11,12),
                            (26,14,13,7,11,10),
                            (30,15,18,10,2,1),
                            (31,1,17,10,2,10),
                            (32,1,16,2,1,1),
                            (33,1,15,2,3,2);
/*!40000 ALTER TABLE `Matricula` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER before_insert_matricula
BEFORE INSERT ON Matricula
FOR EACH ROW
BEGIN
    DECLARE conflicto INT;

    -- Verificar solapamiento en el horario
    SELECT COUNT(*)
    INTO conflicto
    FROM Matricula m
    JOIN Horario h1 ON m.horario_id = h1.id
    JOIN Horario h2 ON NEW.horario_id = h2.id
    WHERE m.salon_id = NEW.salon_id
        AND m.periodo_id = NEW.periodo_id
        AND h1.dia = h2.dia
        AND (
            (h2.horaInicio BETWEEN h1.horaInicio AND h1.horaFin)
            OR (h2.horaFin BETWEEN h1.horaInicio AND h1.horaFin)
            OR (h2.horaInicio <= h1.horaInicio AND h2.horaFin >= h1.horaFin)
        );

    -- Si hay conflicto, evita la inserción
    IF conflicto > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Solapamiento detectado en el horario';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Periodo`
--

DROP TABLE IF EXISTS `Periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Periodo` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `codigo` varchar(50) NOT NULL,
                           `anio` int(11) NOT NULL,
                           `semestre` int(11) NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `Periodo_pk` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Periodo`
--

LOCK TABLES `Periodo` WRITE;
/*!40000 ALTER TABLE `Periodo` DISABLE KEYS */;
INSERT INTO `Periodo` VALUES
                          (1,'P22-01',2022,1),
                          (2,'P22-02',2022,2),
                          (3,'P23-01',2023,1),
                          (4,'P23-02',2023,2),
                          (7,'P24-01',2024,1),
                          (10,'P24-02',2024,2);
/*!40000 ALTER TABLE `Periodo` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER validar_periodo
    BEFORE INSERT ON Periodo
    FOR EACH ROW
BEGIN
    IF NEW.anio < 1900 OR NEW.anio > YEAR(CURDATE()) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El año debe estar entre 1900 y el año actual.';
    END IF;

    IF NEW.semestre < 1 OR NEW.semestre > 2 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El semestre debe ser 1 o 2.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER generar_codigo_periodo_insert
    BEFORE INSERT  ON Periodo

    FOR EACH ROW
BEGIN
    SET NEW.codigo = CONCAT('P', RIGHT(YEAR(CURDATE()), 2), '-', '0',NEW.semestre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER generar_codigo_periodo_update
    BEFORE UPDATE  ON Periodo

    FOR EACH ROW
BEGIN
    SET NEW.codigo = CONCAT('P', RIGHT(YEAR(CURDATE()), 2), '-', '0',NEW.semestre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger validar_periodo_update
    before update
    on Periodo
    for each row
BEGIN
    IF NEW.anio < 1900 OR NEW.anio > YEAR(CURDATE()) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El año debe estar entre 1900 y el año actual.';
    END IF;

    IF NEW.semestre < 1 OR NEW.semestre > 2 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El semestre debe ser 1 o 2.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Profesor`
--

DROP TABLE IF EXISTS `Profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Profesor` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `tipoDocumento` enum('CEDULA','CEDULA_EXTRANJERIA','PASAPORTE') DEFAULT NULL,
                            `numeroDocumento` varchar(50) NOT NULL,
                            `nombres` varchar(255) NOT NULL,
                            `apellidos` varchar(255) NOT NULL,
                            `ciudadResidencia` varchar(255) DEFAULT NULL,
                            `direccion` varchar(255) DEFAULT NULL,
                            `numeroTelefono` varchar(20) DEFAULT NULL,
                            `fechaNacimiento` date DEFAULT NULL,
                            `genero` char(1) DEFAULT NULL,
                            `departamento_id` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `unique_profesor_documento` (`numeroDocumento`),
                            KEY `departamento_id` (`departamento_id`),
                            CONSTRAINT `Profesor_ibfk_1` FOREIGN KEY (`departamento_id`) REFERENCES `Departamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Profesor`
--

LOCK TABLES `Profesor` WRITE;
/*!40000 ALTER TABLE `Profesor` DISABLE KEYS */;
INSERT INTO `Profesor` VALUES
                           (1,'CEDULA','123456789','Juan','Pérez','Ciudad A','Calle 123','555-1234','1980-05-15','M',1),
                           (2,'CEDULA','987654321','Ana','Gómez','Ciudad B','Calle 456','555-5678','1975-08-20','F',2),
                           (5,'CEDULA_EXTRANJERIA','1102369587','Grabiel','Moreno','Giron','cr45#23','32654818','1998-02-23','M',1),
                           (6,'CEDULA','123456790','Carlos','Mendoza','Ciudad C','Calle 789','555-9012','1982-03-30','M',3),
                           (7,'CEDULA','123456791','Sandra','Ortiz','Ciudad D','Calle 012','555-3456','1978-06-25','F',4),
                           (8,'CEDULA','123456792','Luis','Paredes','Ciudad E','Calle 345','555-6789','1985-09-10','M',5),
                           (9,'CEDULA','123456793','Marta','Rojas','Ciudad F','Calle 678','555-0123','1983-12-15','F',6),
                           (10,'CEDULA','123456794','Javier','Santos','Ciudad G','Calle 901','555-2345','1981-05-20','M',7);
/*!40000 ALTER TABLE `Profesor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER validar_edad_profesor
    BEFORE INSERT ON Profesor
    FOR EACH ROW
BEGIN
    IF TIMESTAMPDIFF(YEAR, NEW.fechaNacimiento, CURDATE()) < 18 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El profesor debe mayor de edad.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Programa`
--

DROP TABLE IF EXISTS `Programa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Programa` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(255) NOT NULL,
                            `nivel` varchar(50) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `unique_programa_codigo` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Programa`
--

LOCK TABLES `Programa` WRITE;
/*!40000 ALTER TABLE `Programa` DISABLE KEYS */;
INSERT INTO `Programa` VALUES
                           (1,'Ingeniería de Sistemas','Pregrado'),
                           (2,'Historia del Arte','Pregrado'),
                           (3,'Ciencias de la Computación','Postgrado'),
                           (4,'Matemáticas','Pregrado'),
                           (5,'Física','Pregrado'),
                           (6,'Química','Pregrado');
/*!40000 ALTER TABLE `Programa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Salon`
--

DROP TABLE IF EXISTS `Salon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Salon` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `capacidad` int(11) NOT NULL,
                         `piso` int(11) NOT NULL,
                         `numeroIdentificacion` varchar(50) NOT NULL,
                         `edificio_id` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `numeroIdentificacion` (`numeroIdentificacion`),
                         KEY `fk_edificio` (`edificio_id`),
                         CONSTRAINT `fk_edificio` FOREIGN KEY (`edificio_id`) REFERENCES `Edificio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salon`
--

LOCK TABLES `Salon` WRITE;
/*!40000 ALTER TABLE `Salon` DISABLE KEYS */;
INSERT INTO `Salon` VALUES
                        (1,50,1,'A1-01',1),
                        (2,30,2,'A2-02',1),
                        (3,40,3,'A3-03',1),
                        (4,30,4,'A4-04',1),
                        (5,40,2,'A2-05',1),
                        (6,30,3,'A3-06',1),
                        (7,45,1,'B1-01',2),
                        (8,50,2,'B2-10',2),
                        (9,50,2,'C2-02',3),
                        (10,50,3,'C3-11',3),
                        (11,45,1,'A1-A1',1);
/*!40000 ALTER TABLE `Salon` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER generar_numero_identificacion_salon_insert
    BEFORE INSERT ON Salon
    FOR EACH ROW
BEGIN
    DECLARE edificio_nombre VARCHAR(255);
    SELECT nombre INTO edificio_nombre FROM Edificio WHERE id = NEW.edificio_id;
    SET NEW.numeroIdentificacion = CONCAT(edificio_nombre, NEW.piso, '-', LPAD(NEW.numeroIdentificacion, 2, '0'));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER generar_numero_identificacion_salon_update
    BEFORE UPDATE ON Salon
    FOR EACH ROW
BEGIN
    DECLARE edificio_nombre VARCHAR(255);
    SELECT nombre INTO edificio_nombre FROM Edificio WHERE id = NEW.edificio_id;
    SET NEW.numeroIdentificacion = CONCAT(edificio_nombre, NEW.piso, '-', LPAD(NEW.numeroIdentificacion, 2, '0'));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Tarifa`
--

DROP TABLE IF EXISTS `Tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tarifa` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `valorCredito` int(11) NOT NULL,
                          `periodo_id` int(11) DEFAULT NULL,
                          `programa_id` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `periodo_id` (`periodo_id`),
                          KEY `programa_id` (`programa_id`),
                          CONSTRAINT `Tarifa_ibfk_1` FOREIGN KEY (`periodo_id`) REFERENCES `Periodo` (`id`),
                          CONSTRAINT `Tarifa_ibfk_2` FOREIGN KEY (`programa_id`) REFERENCES `Programa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tarifa`
--

LOCK TABLES `Tarifa` WRITE;
/*!40000 ALTER TABLE `Tarifa` DISABLE KEYS */;
INSERT INTO `Tarifa` VALUES
                         (1,150000,1,1),
                         (2,120000,2,1),
                         (3,200000,3,2),
                         (4,200000,4,1);
/*!40000 ALTER TABLE `Tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ProyectoCampusJava'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizar_cupo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar_cupo`(IN asignatura_id INT, IN cupos INT)
BEGIN
UPDATE Asignatura SET cupoDisponible = cupoDisponible + cupos WHERE id = asignatura_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calcular_costo_matricula` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calcular_costo_matricula`(IN alumno_id INT)
BEGIN
SELECT SUM(Tarifa.valorCredito * Asignatura.creditos) AS costo_matricula
FROM Matricula
         JOIN Asignatura ON Matricula.asignatura_id = Asignatura.id
         JOIN Tarifa ON Tarifa.programa_id = Asignatura.programa_id AND Tarifa.periodo_id = Matricula.periodo_id
WHERE Matricula.alumno_id = alumno_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calcular_ingresos_universidad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calcular_ingresos_universidad`(IN periodo_id INT)
BEGIN
SELECT SUM(Tarifa.valorCredito * Asignatura.creditos) AS ingresos_universidad
FROM Matricula
         JOIN Asignatura ON Matricula.asignatura_id = Asignatura.id
         JOIN Tarifa ON Tarifa.programa_id = Asignatura.programa_id AND Tarifa.periodo_id = Matricula.periodo_id
WHERE Matricula.periodo_id = periodo_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtener_horario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_horario`(IN alumno_id INT, IN periodo_id INT)
BEGIN
SELECT Horario.* FROM Horario
                          JOIN Matricula ON Matricula.horario_id = Horario.id
WHERE Matricula.alumno_id = alumno_id AND Matricula.periodo_id = periodo_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verificar_disponibilidad_cupo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificar_disponibilidad_cupo`(IN asignatura_id INT)
BEGIN
SELECT cupoDisponible FROM Asignatura WHERE id = asignatura_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-12 19:31:29
