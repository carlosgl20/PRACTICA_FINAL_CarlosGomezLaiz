package com.spring.proyecto.pedido;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoDAO extends CrudRepository<Pedido, Long>{

}