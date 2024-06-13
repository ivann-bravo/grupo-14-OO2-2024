CREATE DATABASE  IF NOT EXISTS `bd-grupo14` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bd-grupo14`;

-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema-stock-grupo14
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almacen` (
  `idAlmacen` int NOT NULL AUTO_INCREMENT,
  `cantMinima` int NOT NULL,
  `Stock_idStock` int NOT NULL,
  PRIMARY KEY (`idAlmacen`,`Stock_idStock`),
  KEY `fk_Almacen_Stock1_idx` (`Stock_idStock`),
  CONSTRAINT `fk_Almacen_Stock1` FOREIGN KEY (`Stock_idStock`) REFERENCES `mydb`.`stock` (`idStock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `codigo` int NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `costo` double NOT NULL,
  `precioVenta` double NOT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lote`
--

DROP TABLE IF EXISTS `lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lote` (
  `idLote` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `fechaRecepcion` date NOT NULL,
  `proovedor` varchar(45) NOT NULL,
  `precioCompra` int NOT NULL,
  `Almacen_idAlmacen` int NOT NULL,
  PRIMARY KEY (`idLote`),
  KEY `fk_Lote_Almacen1_idx` (`Almacen_idAlmacen`),
  CONSTRAINT `fk_Lote_Almacen1` FOREIGN KEY (`Almacen_idAlmacen`) REFERENCES `mydb`.`almacen` (`idAlmacen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lote`
--

LOCK TABLES `lote` WRITE;
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordencompra`
--

DROP TABLE IF EXISTS `ordencompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordencompra` (
  `idOrdenCompra` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `Articulo_idProducto` int NOT NULL,
  PRIMARY KEY (`idOrdenCompra`,`Articulo_idProducto`),
  KEY `fk_OrdenCompra_Articulo1_idx` (`Articulo_idProducto`),
  CONSTRAINT `fk_OrdenCompra_Articulo1` FOREIGN KEY (`Articulo_idProducto`) REFERENCES `mydb`.`articulo` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordencompra`
--

LOCK TABLES `ordencompra` WRITE;
/*!40000 ALTER TABLE `ordencompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordencompra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `proovedor` varchar(45) NOT NULL,
  `Stock_idStock` int NOT NULL,
  PRIMARY KEY (`idPedido`,`Stock_idStock`),
  KEY `fk_Pedido_Stock1_idx` (`Stock_idStock`),
  CONSTRAINT `fk_Pedido_Stock1` FOREIGN KEY (`Stock_idStock`) REFERENCES `mydb`.`stock` (`idStock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `idStock` int NOT NULL AUTO_INCREMENT,
  `Articulo_idProducto` int NOT NULL,
  PRIMARY KEY (`idStock`),
  KEY `fk_Stock_Articulo1_idx` (`Articulo_idProducto`),
  CONSTRAINT `fk_Stock_Articulo1` FOREIGN KEY (`Articulo_idProducto`) REFERENCES `mydb`.`articulo` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrol`
--

DROP TABLE IF EXISTS `userrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrol` (
  `idUserRol` int NOT NULL AUTO_INCREMENT,
  `rol` varchar(45) NOT NULL,
  `Usuario_idUsuario` int NOT NULL,
  PRIMARY KEY (`idUserRol`,`Usuario_idUsuario`),
  KEY `fk_UserRol_Usuario1_idx` (`Usuario_idUsuario`),
  CONSTRAINT `fk_UserRol_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `mydb`.`usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrol`
--

LOCK TABLES `userrol` WRITE;
/*!40000 ALTER TABLE `userrol` DISABLE KEYS */;
/*!40000 ALTER TABLE `userrol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `idVenta` int NOT NULL AUTO_INCREMENT,
  `fechaCompra` date NOT NULL,
  `OrdenCompra_idOrdenCompra` int NOT NULL,
  `OrdenCompra_Articulo_idProducto` int NOT NULL,
  `Usuario_idUsuario` int NOT NULL,
  PRIMARY KEY (`idVenta`,`OrdenCompra_idOrdenCompra`,`OrdenCompra_Articulo_idProducto`),
  KEY `fk_Venta_OrdenCompra1_idx` (`OrdenCompra_idOrdenCompra`,`OrdenCompra_Articulo_idProducto`),
  KEY `fk_Venta_Usuario1_idx` (`Usuario_idUsuario`),
  CONSTRAINT `fk_Venta_OrdenCompra1` FOREIGN KEY (`OrdenCompra_idOrdenCompra`, `OrdenCompra_Articulo_idProducto`) REFERENCES `mydb`.`ordencompra` (`idOrdenCompra`, `Articulo_idProducto`),
  CONSTRAINT `fk_Venta_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `mydb`.`usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-11  1:09:14
