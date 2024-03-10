package com.spring.proyecto.reseña;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseñaDAO extends CrudRepository<Reseña, Long>{

}
