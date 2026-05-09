package com.empresa.productos.repositorio;

import com.empresa.productos.dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
