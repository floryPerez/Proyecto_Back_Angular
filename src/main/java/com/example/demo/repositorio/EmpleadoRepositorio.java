package com.example.demo.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Empleado;

@Repository
//para interactuar con la base de datos
//para las consultas, nuevos registros
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {

}
