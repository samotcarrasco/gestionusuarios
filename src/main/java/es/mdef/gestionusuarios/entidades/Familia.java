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
@Table(name="FAMILIAS")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "usuarios"})

public class Familia extends es.mdef.support.Familia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//el enunciado de la familia debe ser único
	//el username a parte de NotBlank debe ser único. añadimos el decorador
	@Column(unique=true)
	@NotBlank(message="El enunciado es obligatorio")
	private String enunciado;
	
	@OneToMany(mappedBy = "familia")
	List<Pregunta> preguntas;	
	

    public String getEnunciado() {
        return enunciado;
    }
    public void setEnunciado(String enunciado) {
    	this.enunciado = enunciado;
    }
    
	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
}