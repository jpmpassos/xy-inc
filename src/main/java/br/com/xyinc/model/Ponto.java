package br.com.xyinc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "ponto")
@SequenceGenerator(name = "ponto_pontoid_seq", sequenceName = "ponto_pontoid_seq", allocationSize = 1)
public class Ponto implements Serializable {
	private static final long serialVersionUID = -3197406533338200682L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ponto_pontoid_seq")
	private Integer pontoid;
	private String nome;
	private Integer coordenadax;
	private Integer coordenaday;
	
	
	public Integer getPontoid() {
		return pontoid;
	}
	public void setPontoid(Integer pontoid) {
		this.pontoid = pontoid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCoordenadax() {
		return coordenadax;
	}
	public void setCoordenadax(Integer coordenadax) {
		this.coordenadax = coordenadax;
	}
	public Integer getCoordenaday() {
		return coordenaday;
	}
	public void setCoordenaday(Integer coordenaday) {
		this.coordenaday = coordenaday;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenadax == null) ? 0 : coordenadax.hashCode());
		result = prime * result + ((coordenaday == null) ? 0 : coordenaday.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pontoid == null) ? 0 : pontoid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ponto other = (Ponto) obj;
		if (coordenadax == null) {
			if (other.coordenadax != null)
				return false;
		} else if (!coordenadax.equals(other.coordenadax))
			return false;
		if (coordenaday == null) {
			if (other.coordenaday != null)
				return false;
		} else if (!coordenaday.equals(other.coordenaday))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pontoid == null) {
			if (other.pontoid != null)
				return false;
		} else if (!pontoid.equals(other.pontoid))
			return false;
		return true;
	}

	
}
