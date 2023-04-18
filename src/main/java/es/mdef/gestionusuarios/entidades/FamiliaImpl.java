package es.mdef.gestionusuarios.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="FAMILIAS")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class FamiliaImpl implements Familia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String enunciado;
	private Long tamanio;
	
	@OneToMany(mappedBy = "familia")
	List<Pregunta> preguntas;	
	//List<Usuario> usuarios;

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
	
//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}	
    
	public Long getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

}