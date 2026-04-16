CREATE TABLE `Centro_Datos` (
  `id_centro` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `ubicacion` VARCHAR(150),
  `pais` VARCHAR(100),
  `fecha_apertura` DATE,
  `superficie_m2` INT,
  `num_servidores` INT
);

CREATE TABLE `Sector` (
  `id_sector` INT PRIMARY KEY AUTO_INCREMENT,
  `id_centro` INT NOT NULL,
  `nombre` VARCHAR(100),
  `tipo` VARCHAR(50),
  `superficie_m2` INT,
  `temperatura_media` DECIMAL(5,2)
);

CREATE TABLE `Consumo_Energetico` (
  `id_consumo` INT PRIMARY KEY AUTO_INCREMENT,
  `id_sector` INT NOT NULL,
  `fecha` DATE,
  `kwh_consumidos` DECIMAL(10,2),
  `coste_eur` DECIMAL(10,2),
  `fuente_energia` VARCHAR(50)
);

CREATE TABLE `Refrigeracion` (
  `id_refrigeracion` INT PRIMARY KEY AUTO_INCREMENT,
  `id_sector` INT NOT NULL,
  `fecha` DATE,
  `temperatura_media` DECIMAL(5,2),
  `sistema` VARCHAR(100),
  `eficiencia_porcentaje` DECIMAL(5,2)
);

CREATE TABLE `Emisiones` (
  `id_emision` INT PRIMARY KEY AUTO_INCREMENT,
  `id_sector` INT NOT NULL,
  `fecha` DATE,
  `co2_kg` DECIMAL(10,2),
  `otros_gases` VARCHAR(100),
  `certificacion_verde` VARCHAR(100)
);

CREATE TABLE `Costes_Operativos` (
  `id_coste` INT PRIMARY KEY AUTO_INCREMENT,
  `id_sector` INT NOT NULL,
  `fecha` DATE,
  `coste_energia` DECIMAL(10,2),
  `coste_mantenimiento` DECIMAL(10,2),
  `coste_personal` DECIMAL(10,2),
  `coste_total` DECIMAL(10,2)
);

CREATE TABLE `Personal` (
  `id_personal` INT PRIMARY KEY AUTO_INCREMENT,
  `id_centro` INT NOT NULL,
  `nombre` VARCHAR(100),
  `puesto` VARCHAR(100),
  `salario` DECIMAL(10,2),
  `fecha_contrato` DATE,
  `tipo_contrato` VARCHAR(50)
);

CREATE TABLE `Formacion` (
  `id_formacion` INT PRIMARY KEY AUTO_INCREMENT,
  `id_centro` INT NOT NULL,
  `curso` VARCHAR(150),
  `horas` INT,
  `fecha` DATE,
  `certificacion` VARCHAR(100),
  `proveedor` VARCHAR(100)
);

CREATE TABLE `Residuos` (
  `id_residuo` INT PRIMARY KEY AUTO_INCREMENT,
  `id_sector` INT NOT NULL,
  `fecha` DATE,
  `tipo` VARCHAR(100),
  `cantidad_kg` DECIMAL(10,2),
  `metodo_reciclaje` VARCHAR(100)
);

CREATE TABLE `Sensores` (
  `id_sensor` INT PRIMARY KEY AUTO_INCREMENT,
  `id_sector` INT NOT NULL,
  `tipo_sensor` VARCHAR(50),
  `ubicacion` VARCHAR(100),
  `fecha_instalacion` DATE,
  `estado` VARCHAR(30)
);

CREATE TABLE `Lecturas_Sensores` (
  `id_lectura` INT PRIMARY KEY AUTO_INCREMENT,
  `id_sensor` INT NOT NULL,
  `fecha_hora` DATETIME,
  `valor` DECIMAL(10,2),
  `unidad` VARCHAR(20)
);

CREATE TABLE `Indicadores_Sostenibilidad` (
  `id_indicador` INT PRIMARY KEY AUTO_INCREMENT,
  `id_centro` INT NOT NULL,
  `anio` INT,
  `huella_carbono` DECIMAL(10,2),
  `porcentaje_renovable` DECIMAL(5,2),
  `indice_social` DECIMAL(5,2),
  `puntuacion_global` DECIMAL(5,2)
);

ALTER TABLE `Sector` ADD FOREIGN KEY (`id_centro`) REFERENCES `Centro_Datos` (`id_centro`) ON DELETE CASCADE;

ALTER TABLE `Consumo_Energetico` ADD FOREIGN KEY (`id_sector`) REFERENCES `Sector` (`id_sector`) ON DELETE CASCADE;

ALTER TABLE `Refrigeracion` ADD FOREIGN KEY (`id_sector`) REFERENCES `Sector` (`id_sector`) ON DELETE CASCADE;

ALTER TABLE `Emisiones` ADD FOREIGN KEY (`id_sector`) REFERENCES `Sector` (`id_sector`) ON DELETE CASCADE;

ALTER TABLE `Costes_Operativos` ADD FOREIGN KEY (`id_sector`) REFERENCES `Sector` (`id_sector`) ON DELETE CASCADE;

ALTER TABLE `Personal` ADD FOREIGN KEY (`id_centro`) REFERENCES `Centro_Datos` (`id_centro`) ON DELETE CASCADE;

ALTER TABLE `Formacion` ADD FOREIGN KEY (`id_centro`) REFERENCES `Centro_Datos` (`id_centro`) ON DELETE CASCADE;

ALTER TABLE `Residuos` ADD FOREIGN KEY (`id_sector`) REFERENCES `Sector` (`id_sector`) ON DELETE CASCADE;

ALTER TABLE `Sensores` ADD FOREIGN KEY (`id_sector`) REFERENCES `Sector` (`id_sector`) ON DELETE CASCADE;

ALTER TABLE `Lecturas_Sensores` ADD FOREIGN KEY (`id_sensor`) REFERENCES `Sensores` (`id_sensor`) ON DELETE CASCADE;

ALTER TABLE `Indicadores_Sostenibilidad` ADD FOREIGN KEY (`id_centro`) REFERENCES `Centro_Datos` (`id_centro`) ON DELETE CASCADE;
