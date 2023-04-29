package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Categoria.TipoGrupo;
import jakarta.validation.constraints.NotBlank;


@Relation(itemRelation="categoria")
public class CategoriaPostModel extends RepresentationModel<CategoriaPostModel>{


  private String categoria;
	private String descripcion;
	private TipoGrupo grupo;
	private int minMilis;
	private int maxMilis;

	public String getCategoria() {
		return categoria;
	}


	public String getDescripcion() {
		return descripcion;
	}



	public TipoGrupo getGrupo() {
		return grupo;
	}


	public int getMinMilis() {
		return minMilis;
	}



	public int getMaxMilis() {
		return maxMilis;
	}
	
	
	@Override
	public String toString() {
		return "Categoria MODELO POST [" + getCategoria() + "]";
	}
	
	
	
}
