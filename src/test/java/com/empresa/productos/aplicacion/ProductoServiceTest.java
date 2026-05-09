package com.empresa.productos.aplicacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.empresa.productos.dominio.Producto;
import com.empresa.productos.repositorio.ProductoRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {
    @Mock
    private ProductoRepository repo;

    @InjectMocks
    private ProductoService service;

    @Test
    void buscar_inexistente_lanza_excepcion() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.buscar(99L));
    }

    @Test
    void procesarProducto_guarda_datos_validados() {
        when(repo.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Producto creado = service.procesarProducto("Cafe", 10.0, 5);

        assertEquals("Cafe", creado.getNombre());
        assertEquals(10.0, creado.getPrecio());
        assertEquals(5, creado.getStock());
    }
}
