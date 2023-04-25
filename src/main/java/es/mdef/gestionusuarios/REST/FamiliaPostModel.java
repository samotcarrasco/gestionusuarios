package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Relation(itemRelation="familia")
public class FamiliaPostModel extends RepresentationModel<FamiliaPostModel>{

	private String enunciado;
	
	public String getEnunciado() {
		return enunciado;
	}
		
	
	@Override
	public String toString() {
		return "FamiliaPostModel [enunciado=" + enunciado + " ]";
	}
	
}
