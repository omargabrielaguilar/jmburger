create database jmburger;
use jmburger;

CREATE TABLE categoria (
  id_categoria INT AUTO_INCREMENT PRIMARY KEY,
  nombre_categoria VARCHAR(55),
  descripcion TEXT
); 

CREATE TABLE categoria_usuario (
  id_categoria_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre_categoria_usuario VARCHAR(45) NOT NULL
);

CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(255),
    descripcion TEXT,
    stock_actual INT,
    stock_minimo INT CHECK (stock_minimo <=150),
    stock_maximo INT CHECK (stock_maximo <=500),
    fecharegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre_usuario VARCHAR(255) NOT NULL,
  correo_electronico VARCHAR(255) NOT NULL,
  id_categoria_usuario INT NOT NULL,
  contrasenia_hash VARCHAR(255) NOT NULL,
  CONSTRAINT fk_categoria_usuario
    FOREIGN KEY (id_categoria_usuario)
    REFERENCES categoria_usuario(id_categoria_usuario),
  UNIQUE (correo_electronico)
);

CREATE TABLE proveedor (
  id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
  nombre_proveedor VARCHAR(255) NOT NULL,
  direccion VARCHAR(255),
  telefono VARCHAR(20),
  precio DECIMAL(10,2),
  correo_electronico VARCHAR(255),
  sitio_web VARCHAR(255),
  descripcion TEXT
);

CREATE TABLE pedido (
  id_pedido INT AUTO_INCREMENT PRIMARY KEY,
  fecha_pedido DATETIME NOT NULL,
  estado_pedido ENUM('pendiente', 'en_ejecucion', 'entregado') NOT NULL,
  comentarios VARCHAR(255),
  id_usuario INT NOT NULL,
  id_proveedor INT NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
  FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE detalle_pedido (
  id_detalle_pedido INT AUTO_INCREMENT PRIMARY KEY,
  id_pedido INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  precio_unitario DECIMAL(10,2) NOT NULL,
  precio_total DECIMAL(10, 2) AS (cantidad * precio_unitario),
  fecharegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);



INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Carnes', 'Categoría de carnes para hamburgesas');

INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Panes', 'Categoría de panes para hamburgesas');

CREATE TABLE produccion (
  id_produccion INT AUTO_INCREMENT PRIMARY KEY,
  fecha_produccion DATETIME NOT NULL,
  id_pedido INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad_producida INT NOT NULL,
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE detalle_produccion (
  id_detalle_produccion INT AUTO_INCREMENT PRIMARY KEY,
  id_produccion INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad_producida INT NOT NULL,
  FOREIGN KEY (id_produccion) REFERENCES produccion(id_produccion),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);



--Correción de crud producto y trabajar con demas tablas
