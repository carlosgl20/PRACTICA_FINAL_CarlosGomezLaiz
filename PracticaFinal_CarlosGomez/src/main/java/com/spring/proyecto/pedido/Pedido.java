package com.spring.proyecto.pedido;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.proyecto.cliente.Cliente;
import com.spring.proyecto.encargo.Encargo;
import com.spring.proyecto.reseña.Reseña;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// A la hora de editar un pedido, si dejamos datos en blanco
	// o ponemos un nombre mas largo de 15 caracteres,
	// nos saltará un error
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(max = 15, message = "El nombre no puede tener más de 10 letras!!!")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "El nombre solo puede contener letras, números y espacios")
	private String nombre;

	@NotNull(message = "El precio no puede ser nulo")
	@Min(value = 1, message = "El precio debe ser como mínimo 1 euro")
	private double precio;

	@NotBlank(message = "La compañía no puede estar en blanco")
	private String compañia;

	// Muchos pedidos son de un cliente
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_CLIENTE")
	@JsonBackReference
	Cliente cliente;

	// Un pedido tiene de una a muchas reseñas
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	@Cascade(CascadeType.ALL)
	private List<Reseña> reseñas = new ArrayList<Reseña>();

	// Clase intermedia N:M
	@OneToMany(targetEntity = Encargo.class, mappedBy = "pedido")
	private List<Encargo> encargo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCompañia() {
		return compañia;
	}

	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Reseña> getReseñas() {
		return reseñas;
	}

	public void setReseñas(List<Reseña> reseñas) {
		this.reseñas = reseñas;
	}

	public List<Encargo> getEncargo() {
		return encargo;
	}

	public void setEncargo(List<Encargo> encargo) {
		this.encargo = encargo;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", compañia=" + compañia
				+ ", cliente=" + cliente + ", reseñas=" + reseñas + ", encargo=" + encargo + "]";
	}

}