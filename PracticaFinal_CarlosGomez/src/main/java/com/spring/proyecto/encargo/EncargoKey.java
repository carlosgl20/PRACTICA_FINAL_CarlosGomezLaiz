package com.spring.proyecto.encargo;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EncargoKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Long idPedido;

	@Column
	private Long idEmpleado;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEmpleado, idPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncargoKey other = (EncargoKey) obj;
		return Objects.equals(idEmpleado, other.idEmpleado) && Objects.equals(idPedido, other.idPedido);
	}

}