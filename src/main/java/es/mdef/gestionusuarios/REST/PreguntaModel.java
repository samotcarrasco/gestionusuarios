package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(itemRelation="pregunta")
public class PreguntaModel extends RepresentationModel<PreguntaModel>{

	private String enunciado;
	private Usuario usuario;
	private FamiliaImpl familia;
	private String nombreDeUsuario;
        
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	//ejemplo para mostrar en el Modelo el nombre del usuario
	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}
	
	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}
	@Override
	public String toString() {
		return "PreguntaModel [enunciado=" + enunciado + "]";
	}
	
}
