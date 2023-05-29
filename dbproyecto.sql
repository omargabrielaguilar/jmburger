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
    precio DECIMAL(10,2),
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
  contraseña_hash VARCHAR(255) NOT NULL,
  CONSTRAINT fk_categoria_usuario
    FOREIGN KEY (id_categoria_usuario)
    REFERENCES categoria_usuario(id_categoria_usuario),
  UNIQUE (correo_electronico)
);

CREATE TABLE pedido (
  id_pedido INT AUTO_INCREMENT PRIMARY KEY,
  fecha_pedido DATETIME NOT NULL,
  estado_pedido ENUM('pendiente', 'en_ejecucion', 'entregado') NOT NULL,
  comentarios VARCHAR(255),
  id_usuario INT NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE detalle_pedido (
  id_detalle_pedido INT AUTO_INCREMENT PRIMARY KEY,
  id_pedido INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  precio_unitario DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE proveedor (
  id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
  nombre_proveedor VARCHAR(255) NOT NULL,
  direccion VARCHAR(255),
  telefono VARCHAR(20),
  correo_electronico VARCHAR(255),
  sitio_web VARCHAR(255),
  descripcion TEXT
);

--Agregamos unas categorias
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Carnes', 'Categoría de carnes para hamburgesas');

INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Panes', 'Categoría de panes para hamburgesas');


--Probamos el script
CALL crearProducto('Pan Bimbo', 'Pan de maiz', 10.99, 50, 10, 100, 'Panes');

