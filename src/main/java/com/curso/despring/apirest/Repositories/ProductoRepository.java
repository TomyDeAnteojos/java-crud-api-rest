package com.curso.despring.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.despring.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{


    
}
