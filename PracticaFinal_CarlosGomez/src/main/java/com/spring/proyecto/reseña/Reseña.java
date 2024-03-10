package com.spring.proyecto.reseña;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.proyecto.cliente.Cliente;
import com.spring.proyecto.pedido.Pedido;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Reseña {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String comentario;
	private int valoracion;
	
	// Una reseña es de un cliente
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FK_CLIENTE_OPINION")
	@JsonBackReference
	Cliente cliente;
	
	// Una reseña es de un pedido
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FK_NUM_PEDIDO")
	@JsonBackReference
	Pedido pedido;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Reseña [id=" + id + ", comentario=" + comentario + ", valoracion=" + valoracion + ", cliente=" + cliente
				+ "]";
	}
	
	
}