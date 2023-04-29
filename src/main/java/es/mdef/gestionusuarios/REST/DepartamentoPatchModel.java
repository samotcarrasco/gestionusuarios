package es.mdef.gestionusuarios.REST;



import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


import es.mdef.gestionusuarios.entidades.Departamento.TipoAcuartelamiento;
import es.mdef.gestionusuarios.entidades.Departamento.TipoEmpleo;



@Relation(collectionRelation = "departamentos")
public class DepartamentoPatchModel extends RepresentationModel<CategoriaModel>{
	
	private int credito;
	
	public int getCredito() {
		return this.credito;
	}
	
		
	
}
