package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Relation(collectionRelation = "familias")
public class FamiliaListaModel extends RepresentationModel<FamiliaListaModel> {
	private String enunciado;
	private Long tamanio;
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public Long getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}
	
	@Override
	public String toString() {
		return "FamiliaListaModel [enun=" + enunciado + ", tamabio=" + tamanio + "]";
	}

}
