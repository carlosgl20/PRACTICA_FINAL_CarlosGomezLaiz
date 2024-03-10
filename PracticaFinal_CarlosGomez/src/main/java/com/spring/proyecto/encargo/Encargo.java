package com.spring.proyecto.encargo;


import com.spring.proyecto.empleado.Empleado;
import com.spring.proyecto.pedido.Pedido;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Encargo {

	@EmbeddedId
	EncargoKey id;

	@ManyToOne
	@MapsId("idPedido")
	@JoinColumn(name = "pedido_id")
	Pedido pedido;

	@ManyToOne
	@MapsId("idEmpleado")
	@JoinColumn(name = "empleado_id")
	Empleado empleado;
	
	private String fecha;

	public EncargoKey getId() {
		return id;
	}

	public void setId(EncargoKey id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Encargo [id=" + id + ", pedido=" + pedido + ", empleado=" + empleado + ", fecha=" + fecha + "]";
	}

	

	
	
}