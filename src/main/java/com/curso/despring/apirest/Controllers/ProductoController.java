package com.curso.despring.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.despring.apirest.Entities.Producto;
import com.curso.despring.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Producto getProductoByID(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow( ()-> new RuntimeException("No se encontro el producto con el ID: "+ id));
    }
    

    @PostMapping
    public Producto createProducto (@RequestBody Producto producto) {
        return productoRepository.save(producto); //graba y devuelve un producto
    }
    
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detalle) {    
        Producto producto = productoRepository.findById(id)
        .orElseThrow( ()-> new RuntimeException("No se encontro el producto con el ID: "+ id));
        
        producto.setNombre(detalle.getNombre());
        producto.setPrecio(detalle.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id)
    {
        Producto producto = productoRepository.findById(id)
        .orElseThrow( ()-> new RuntimeException("No se encontro el producto con el ID: "+ id));
        
        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente.";
    }
}
