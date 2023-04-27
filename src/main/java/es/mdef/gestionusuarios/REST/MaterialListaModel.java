package es.mdef.gestionusuarios.REST;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Familia;
import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(collectionRelation="materiales")
public class MaterialListaModel extends RepresentationModel<MaterialListaModel>{

	 private String nombre;
	    private String descripcion;
	    	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }

	

}
