package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Familia;
import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(itemRelation="pregunta")
public class PreguntaPostModel extends RepresentationModel<PreguntaPostModel>{

	private String enunciado;
	private Usuario usuario;
	private Familia familia;

	public String getEnunciado() {
		return enunciado;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public Familia getFamilia() {
		return familia;
	}
	
	
	@Override
	public String toString() {
		return "PreguntaModel [enunciado=" + enunciado + "]";
	}
	
}
