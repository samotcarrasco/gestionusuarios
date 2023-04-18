package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(itemRelation="familia")
public class FamiliaModel extends RepresentationModel<FamiliaModel>{

	private String enunciado;
	private Long tamanio;
	
//	private Long id;	
//	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	
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
