package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import jakarta.validation.constraints.NotBlank;


@Relation(itemRelation="familia")
public class FamiliaPostModel extends RepresentationModel<FamiliaPostModel>{

	//en este caso, al no tener acceso a la entidad, validamos aquí (en el modelo) los campos)
	//el enunciado de la familia debe ser único
	//@Column(unique=true)
	//@NotBlank(message="El enunciado es obligatorio")
	private String enunciado;
	
	public String getEnunciado() {
		return enunciado;
	}
		
	
	@Override
	public String toString() {
		return "FamiliaPostModel [enunciado=" + enunciado + " ]";
	}
	
}
