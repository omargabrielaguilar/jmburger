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


--Procedimientos almacenados:
--Crear Productos
CREATE PROCEDURE crearProducto(
  IN p_nombre VARCHAR(255),
  IN p_descripcion TEXT,
  IN p_precio DECIMAL(10,2),
  IN p_stock_actual INT,
  IN p_stock_minimo INT,
  IN p_stock_maximo INT,
  IN p_id_categoria INT
)
BEGIN
  INSERT INTO producto (nombre_producto, descripcion, precio, stock_actual, stock_minimo, stock_maximo, id_categoria)
  VALUES (p_nombre, p_descripcion, p_precio, p_stock_actual, p_stock_minimo, p_stock_maximo, p_id_categoria);
END;


--Listar Productos
CREATE PROCEDURE listarProductos()
BEGIN
  SELECT * FROM producto;
END;

--Actualizar Productos
CREATE PROCEDURE actualizarProducto(
  IN p_id_producto INT,
  IN p_nombre VARCHAR(255),
  IN p_descripcion TEXT,
  IN p_precio DECIMAL(10,2),
  IN p_stock_actual INT,
  IN p_stock_minimo INT,
  IN p_stock_maximo INT,
  IN p_id_categoria INT
)
BEGIN
  UPDATE producto
  SET nombre_producto = p_nombre,
      descripcion = p_descripcion,
      precio = p_precio,
      stock_actual = p_stock_actual,
      stock_minimo = p_stock_minimo,
      stock_maximo = p_stock_maximo,
      id_categoria = p_id_categoria
  WHERE id_producto = p_id_producto;
END;

--Eliminar Productos
CREATE PROCEDURE borrarProducto(
  IN p_id_producto INT
)
BEGIN
  DELETE FROM producto
  WHERE id_producto = p_id_producto;
END;
