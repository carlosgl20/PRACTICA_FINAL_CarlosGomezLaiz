package com.spring.proyecto.empleado;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoDAO extends CrudRepository<Empleado, Long>{

}