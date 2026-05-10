package com.empresa.productos.aplicacion;

import com.empresa.productos.dominio.Producto;
import com.empresa.productos.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public Producto buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Producto procesarProducto(String nombre, Double precio, Integer stock) {
        if (nombre == null || nombre.equals("")) {
            throw new IllegalArgumentException("nombre requerido");
        }
        if (precio == null || precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (precio > 999999) {
            throw new IllegalArgumentException("El precio excede el maximo permitido");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        Producto producto = new Producto();
        producto.setNombre(nombre.strip());
        producto.setPrecio(precio);
        producto.setStock(stock);
        return repo.save(producto);
    }
}
