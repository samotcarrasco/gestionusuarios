package es.mdef.gestionusuarios.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name="CATEGORIAS")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

public class Categoria {
	
	public static enum TipoGrupo {
		Informatica,
	    Comunicaciones,
	    Mobiliario_Oficina,
	    Material_Oficina,
	    Otros
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "categoria")
	private List<Material> materiales;	
	
	private String categoria;
	private String descripcion;
	private TipoGrupo grupo;
	private int minMilis;
	private int maxMilis;

	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(TipoGrupo grupo) {
		this.grupo = grupo;
	}

	public int getMinMilis() {
		return minMilis;
	}

	public void setMinMilis(int minMilis) {
		this.minMilis = minMilis;
	}

	public int getMaxMilis() {
		return maxMilis;
	}

	public void setMaxMilis(int maxMilis) {
		this.maxMilis = maxMilis;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public List<Material> getMateriales() {
		return materiales;
	}

	public void setPreguntas(List<Material> materiales) {
		this.materiales = materiales;
	}
	

	@Override
	public String toString() {
		return "Categoria [id=" + getId() + ", nombre" + getCategoria() + "]";
	}
	
}