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
  fecha_pedido DATE NOT NULL,
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
  FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);



INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Carnes', 'Categoría de carnes para hamburgesas');

INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Panes', 'Categoría de panes para hamburgesas');

CREATE TABLE produccion (
  id_produccion INT AUTO_INCREMENT PRIMARY KEY,
  fecha_produccion DATE NOT NULL,
  id_pedido INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad_producida INT NOT NULL,
  FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE detalle_produccion (
  id_detalle_produccion INT AUTO_INCREMENT PRIMARY KEY,
  id_produccion INT NOT NULL,
  id_producto INT NOT NULL,
  FOREIGN KEY (id_produccion) REFERENCES produccion(id_produccion),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);


-- Inserciones 
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Verduras', 'Categoría de verduras para hamburgesas');

INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Bebidas', 'Categoría de bebidas para hamburgesas');


-- categoria usuario
insert into categoria_usuario (nombre_categoria_usuario) Values ('administrador');
insert into categoria_usuario (nombre_categoria_usuario) VALUES ('rrhh');
insert into categoria_usuario (nombre_categoria_usuario) VALUES ('contador');


-- productos
-- Inserciones para la categoría "Carnes"
INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Hamburguesa de Res', 'Deliciosa hamburguesa de carne de res', 50, 10, 100, 1);

INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Hamburguesa de Pollo', 'Deliciosa hamburguesa de pollo', 40, 10, 100, 1);

-- Inserciones para la categoría "Panes"
INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Pan de Hamburguesa Clásico', 'Pan de hamburguesa tradicional', 100, 20, 200, 2);

INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Pan de Hamburguesa Integral', 'Pan de hamburguesa integral', 80, 20, 200, 2);

-- Inserciones para la categoría "Verduras"
INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Lechuga', 'Fresca lechuga para hamburguesas', 200, 50, 300, 3);

INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Tomate', 'Sabroso tomate para hamburguesas', 150, 50, 300, 3);

-- Inserciones para la categoría "Bebidas"
INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Refresco de Cola', 'Refresco de cola 500 ml', 100, 10, 200, 4);

INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Agua Mineral', 'Botella de agua mineral 500 ml', 200, 20, 400, 4);

-- Otras inserciones
INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Queso Cheddar', 'Delicioso queso cheddar para hamburguesas', 80, 10, 200, 1);

INSERT INTO producto (nombre_producto, descripcion, stock_actual, stock_minimo, stock_maximo, id_categoria)
VALUES ('Tocino', 'Sabroso tocino para hamburguesas', 60, 10, 200, 1);

-- usuario
-- Inserciones para la categoría de usuario "administrador"
INSERT INTO usuario (nombre_usuario, correo_electronico, id_categoria_usuario, contrasenia_hash)
VALUES ('Admin1', 'admin1@example.com', 1, 'contrasenia_hash_admin1');

INSERT INTO usuario (nombre_usuario, correo_electronico, id_categoria_usuario, contrasenia_hash)
VALUES ('Admin2', 'admin2@example.com', 1, 'contrasenia_hash_admin2');

-- Inserciones para la categoría de usuario "rrhh"
INSERT INTO usuario (nombre_usuario, correo_electronico, id_categoria_usuario, contrasenia_hash)
VALUES ('RRHH1', 'rrhh1@example.com', 2, 'contrasenia_hash_rrhh1');

-- Inserciones para la categoría de usuario "contador"
INSERT INTO usuario (nombre_usuario, correo_electronico, id_categoria_usuario, contrasenia_hash)
VALUES ('Contador1', 'contador1@example.com', 3, 'contrasenia_hash_contador1');

INSERT INTO usuario (nombre_usuario, correo_electronico, id_categoria_usuario, contrasenia_hash)
VALUES ('Contador2', 'contador2@example.com', 3, 'contrasenia_hash_contador2');



-- proveedor
INSERT INTO proveedor (nombre_proveedor, direccion, telefono, precio, correo_electronico, sitio_web, descripcion)
VALUES ('Embutidos S.A.', 'Calle Principal 123', '+51 987654321', 10.50, 'info@embutidos.com', 'www.embutidos.com', 'Proveedor de embutidos de alta calidad');

INSERT INTO proveedor (nombre_proveedor, direccion, telefono, precio, correo_electronico, sitio_web, descripcion)
VALUES ('Carnes del Sur', 'Avenida Central 456', '+51 912345678', 12.75, 'info@carnesdelsur.com', 'www.carnesdelsur.com', 'Proveedor de carnes frescas y jugosas');

INSERT INTO proveedor (nombre_proveedor, direccion, telefono, precio, correo_electronico, sitio_web, descripcion)
VALUES ('Mercados Peruanos Norte', 'Calle Norte 789', '+51 923456789', 8.99, 'info@mercadospnorte.com', 'www.mercadospnorte.com', 'Proveedor de productos variados en mercados peruanos');

INSERT INTO proveedor (nombre_proveedor, direccion, telefono, precio, correo_electronico, sitio_web, descripcion)
VALUES ('Embutidos del Valle', 'Avenida Sur 321', '+51 934567890', 9.75, 'info@embutidosvalle.com', 'www.embutidosvalle.com', 'Proveedor de embutidos artesanales del valle');

INSERT INTO proveedor (nombre_proveedor, direccion, telefono, precio, correo_electronico, sitio_web, descripcion)
VALUES ('Mercados Peruanos Centro', 'Avenida Central 987', '+51 945678901', 7.50, 'info@mercadospcentro.com', 'www.mercadospcentro.com', 'Proveedor de productos frescos en mercados peruanos');


-- pedidos
-- Pedido 1
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-06-10', 'pendiente', 'Pedido de productos frescos', 1, 3);

-- Pedido 2
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-06-15', 'en_ejecucion', 'Pedido urgente de embutidos', 2, 1);

-- Pedido 3
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-06-20', 'pendiente', 'Pedido de carnes para evento', 1, 2);

-- Pedido 4
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-06-25', 'en_ejecucion', 'Pedido especial de productos variados', 2, 3);

-- Pedido 5
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-07-05', 'pendiente', 'Pedido de embutidos artesanales', 1, 4);

-- Pedido 6
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-07-10', 'en_ejecucion', 'Pedido de productos frescos', 2, 5);

-- Pedido 7
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-07-15', 'pendiente', 'Pedido urgente de carnes', 1, 2);

-- Pedido 8
INSERT INTO pedido (fecha_pedido, estado_pedido, comentarios, id_usuario, id_proveedor)
VALUES ('2023-07-20', 'en_ejecucion', 'Pedido de embutidos y productos variados', 2, 4);



-- detalle pedido


-- Detalle pedido 1
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (1, 1, 5, 8.99);

-- Detalle pedido 2
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (2, 3, 3, 4.50);

-- Detalle pedido 3
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (3, 2, 2, 6.75);

-- Detalle pedido 4
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (4, 5, 4, 2.99);

-- Detalle pedido 5
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (5, 4, 1, 5.50);

-- Detalle pedido 6
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (6, 6, 3, 3.25);

-- Detalle pedido 7
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (7, 1, 5, 8.99);

-- Detalle pedido 8
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario)
VALUES (8, 3, 2, 4.50);

-- produccion
INSERT INTO produccion (fecha_produccion, id_pedido, id_producto, cantidad_producida)
VALUES
  ('2023-06-28', 1, 1, 10),
  ('2023-06-28', 2, 2, 15),
  ('2023-06-29', 3, 4, 8),
  ('2023-06-29', 4, 5, 20),
  ('2023-07-06', 5, 1, 5);

-- Detalle prod
INSERT INTO detalle_produccion (id_produccion, id_producto)
VALUES (1, 1),
       (2, 2),
       (4, 3),
       (5, 4),
       (1, 5),
       (2, 6),
       (3, 7),
       (4, 8),
       (5, 9),
       (1, 10);

update usuario set contrasenia_hash = '123';

