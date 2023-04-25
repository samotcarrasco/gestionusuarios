package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(itemRelation="familia")
public class FamiliaModel extends RepresentationModel<FamiliaModel>{

	private String enunciado;
	private int tamanio;
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public int getTamanio() {
		return tamanio;
	}
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	
	@Override
	public String toString() {
		return "PreguntaModel [enunciado=" + enunciado + " tamanio " + tamanio+ " ]";
	}
	
}
