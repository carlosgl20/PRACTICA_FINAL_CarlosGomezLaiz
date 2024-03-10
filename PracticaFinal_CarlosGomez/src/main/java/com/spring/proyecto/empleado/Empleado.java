package com.spring.proyecto.empleado;

import java.util.HashSet;
import java.util.Set;

import com.spring.proyecto.encargo.Encargo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(max = 15, message = "El nombre no puede tener más de 10 letras!!!")
	@Pattern(regexp = "^[\\p{L}0-9\\s]*$", message = "El nombre solo puede contener letras, números y espacios")
	private String nombre;
	
	@NotNull(message = "La edad no puede ser nula")
	private int edad;
	
	@NotBlank(message = "El rol no puede estar en blanco")
	private String rol;

	@NotBlank(message = "El horario no puede estar en blanco")
	private String horario;
	
	//Clase intermedia N:M
	@OneToMany(mappedBy="empleado", targetEntity=Encargo.class)
	private Set<Encargo> encargo;

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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Set<Encargo> getPedido_empleado() {
		return encargo;
	}

	public void setPedido_empleado(Set<Encargo> encargo) {
		this.encargo = encargo;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", rol=" + rol + ", horario=" + horario
				+ ", encargo=" + encargo + "]";
	}
		
}