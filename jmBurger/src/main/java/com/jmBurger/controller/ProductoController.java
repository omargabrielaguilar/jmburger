package com.jmBurger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jmBurger.entity.Categoria;
import com.jmBurger.entity.Producto;
import com.jmBurger.repository.CategoriaRepository;
import com.jmBurger.repository.ProductoRepository;

@Controller
public class ProductoController {
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("productos")
	public String listarProductos(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "productos";
	}

	@GetMapping("productos/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("categorias", categoriaRepository.findAll());
		return "nuevoProducto";
	}

	@PostMapping("productos/nuevo")
	public String crearProducto(@ModelAttribute("producto") Producto producto) {
		// Verificar si la categoría ya existe en la base de datos
		Categoria categoria = categoriaRepository.findByNombreCategoria(producto.getCategoria().getNombreCategoria());
		if (categoria == null) {
			// La categoría no existe, crear una nueva
			categoria = new Categoria();
			categoria.setNombreCategoria(producto.getCategoria().getNombreCategoria());
			categoria.setDescripcion(producto.getCategoria().getDescripcion());
			categoriaRepository.save(categoria);
		}
		// Asignar la categoría al producto
		producto.setCategoria(categoria);
		// Guardar el producto
		productoRepository.save(producto);
		return "redirect:/productos";
	}

	@GetMapping("productos/{id}/editar")
	public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));

		model.addAttribute("producto", producto);
		model.addAttribute("categorias", categoriaRepository.findAll());
		return "editarProducto";
	}

	@PostMapping("productos/{id}/editar")
	public String actualizarProducto(@PathVariable("id") int id, @ModelAttribute("producto") Producto producto) {
		producto.setIdProducto(id);

		// Verificar si la categoría ya existe en la base de datos
		Categoria categoria = categoriaRepository.findByNombreCategoria(producto.getCategoria().getNombreCategoria());
		if (categoria == null) {
			// La categoría no existe, crear una nueva
			categoria = new Categoria();
			categoria.setNombreCategoria(producto.getCategoria().getNombreCategoria());
			categoria.setDescripcion(producto.getCategoria().getDescripcion());
			categoriaRepository.save(categoria);
		}
		// Asignar la categoría al producto
		producto.setCategoria(categoria);

		productoRepository.save(producto);
		return "redirect:/productos";
	}

	@GetMapping("productos/{id}/borrar")
	public String borrarProducto(@PathVariable("id") int id) {
		productoRepository.deleteById(id);
		return "redirect:/productos";
	}
}