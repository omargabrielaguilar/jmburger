
--Procedimientos almacenados:
--Crear Productos
DELIMITER //

CREATE PROCEDURE crearProducto(
  IN p_nombre VARCHAR(255),
  IN p_descripcion TEXT,
  IN p_precio DECIMAL(10,2),
  IN p_stock_actual INT,
  IN p_stock_minimo INT,
  IN p_stock_maximo INT,
  IN p_nombre_categoria VARCHAR(55)
)
BEGIN
  DECLARE v_id_categoria INT;

  -- Obtener el ID de categoría basado en el nombre proporcionado
  SELECT id_categoria INTO v_id_categoria FROM categoria WHERE nombre_categoria = p_nombre_categoria;

  -- Insertar el nuevo producto
  INSERT INTO producto (nombre_producto, descripcion, precio, stock_actual, stock_minimo, stock_maximo, fecharegistro, id_categoria)
  VALUES (p_nombre, p_descripcion, p_precio, p_stock_actual, p_stock_minimo, p_stock_maximo, CURRENT_TIMESTAMP, v_id_categoria);
END //

DELIMITER ;

--Listar Productos
DELIMITER //

CREATE PROCEDURE listarProductos()
BEGIN
  SELECT p.id_producto, p.nombre_producto, p.descripcion, p.precio, p.stock_actual, p.stock_minimo, p.stock_maximo, p.fecharegistro, c.nombre_categoria
  FROM producto p
  INNER JOIN categoria c ON p.id_categoria = c.id_categoria;
END //

DELIMITER ;

--Actualizar Productos
DELIMITER //

CREATE PROCEDURE actualizarProducto(
  IN p_id_producto INT,
  IN p_nombre_producto VARCHAR(255),
  IN p_descripcion TEXT,
  IN p_precio DECIMAL(10,2),
  IN p_stock_actual INT,
  IN p_stock_minimo INT,
  IN p_stock_maximo INT,
  IN p_nombre_categoria VARCHAR(55)
)
BEGIN
  DECLARE v_id_categoria INT;

  -- Obtener el ID de categoría basado en el nombre proporcionado
  SELECT id_categoria INTO v_id_categoria FROM categoria WHERE nombre_categoria = p_nombre_categoria;

  -- Actualizar el producto
  UPDATE producto
  SET nombre_producto = p_nombre_producto,
      descripcion = p_descripcion,
      precio = p_precio,
      stock_actual = p_stock_actual,
      stock_minimo = p_stock_minimo,
      stock_maximo = p_stock_maximo,
      id_categoria = v_id_categoria
  WHERE id_producto = p_id_producto;
END //

DELIMITER ;



--Eliminar Productos
DELIMITER //

CREATE PROCEDURE borrarProducto(
  IN p_id_producto INT
)
BEGIN
  DELETE FROM producto
  WHERE id_producto = p_id_producto;
END //

DELIMITER ;

