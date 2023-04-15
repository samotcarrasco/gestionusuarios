package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Usuario;


@Relation(collectionRelation = "preguntas")
public class PreguntaListaModel extends RepresentationModel<PreguntaListaModel> {
	private String enunciado;
	private Usuario usuario;
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//	
//	
	@Override
	public String toString() {
		return "PreguntaModel [enunciado=" + enunciado + "]";
	}

}
