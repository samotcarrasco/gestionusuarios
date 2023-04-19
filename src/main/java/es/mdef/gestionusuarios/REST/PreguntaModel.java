package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(itemRelation="pregunta")
public class PreguntaModel extends RepresentationModel<PreguntaModel>{

	private String enunciado;
	public Usuario usuario;
	public FamiliaImpl familia;

	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public FamiliaImpl getFamilia() {
		return familia;
	}
	
	public void setFamilia(FamiliaImpl familia) {
		this.familia = familia;
	}
	
	
	@Override
	public String toString() {
		return "PreguntaModel [enunciado=" + enunciado + "]";
	}
	
}
