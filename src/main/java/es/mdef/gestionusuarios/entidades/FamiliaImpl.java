package es.mdef.gestionusuarios.entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="FAMILIAS")
public class FamiliaImpl implements Familia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String enunciado;
	private Long tamanio;
//	
//	@OneToOne(mappedBy = "familia")
//	private Pregunta pregunta;	
//	

    public String getEnunciado() {
        return enunciado;
    }
    public void setEnunciado(String enunciado) {
    	this.enunciado = enunciado;
    }

//    public Pregunta getPregunta() {
//        return pregunta;
//    }
//    
//    public void setPregunta(Pregunta pregunta) {
//    	this.pregunta = pregunta;
//    }
    
	public Long getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

}