package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


import es.mdef.gestionusuarios.entidades.FamiliaImpl;

@Relation(itemRelation="familia")
public class FamiliaPostModel extends RepresentationModel<FamiliaPostModel>{

	private String enunciado;
	private Long tamanio;
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public Long getTamanio() {
		return tamanio;
	}
	
	
	@Override
	public String toString() {
		return "FamiliaPostModel [enunciado=" + enunciado + " ]";
	}
	
}
