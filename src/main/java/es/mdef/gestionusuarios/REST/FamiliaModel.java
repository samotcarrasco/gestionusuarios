package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(itemRelation="pregunta")
public class FamiliaModel extends RepresentationModel<FamiliaModel>{

	private String enunciado;
	public Long tamanio;
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public Long getTamanio() {
		return tamanio;
	}
	public void setTamabio(Long tamanio) {
		this.tamanio = tamanio;
	}
	
	
	@Override
	public String toString() {
		return "PreguntaModel [enunciado=" + enunciado + " tamanio " + tamanio+ " ]";
	}
	
}
