package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;
import es.mdef.gestionusuarios.entidades.NoAdministrador.Dpto;
import es.mdef.gestionusuarios.entidades.NoAdministrador.Tipo;;

//import es.mdef.gestionpedidos.entidades.Usuario.UsuarioEstado;

@Relation(itemRelation="usuario")
public class UsuarioPutModel extends RepresentationModel<UsuarioPutModel>{

	private String nombre;
	private String userName;
	private Rol rol;
	//atributo del administrador
    private String telefono;
   //atributos del noAdministrador
    private Dpto dpto;
    private Tipo tipo;
	

	public String getNombre() {
		return nombre;
	}

	
	public String getUserName() {
		return userName;
	}
	
		
	public Rol getRol() {
		return rol;
	}

	public String getTelefono() {
		return telefono;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	public Dpto getDpto() {
		return dpto;
	}


	@Override
	public String toString() {
		return "UsuarioPutModel [Nombre=" + nombre + ", UserName=" 
				+ userName ;
	}
	
}