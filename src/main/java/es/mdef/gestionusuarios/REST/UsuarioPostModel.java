package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;
import es.mdef.gestionusuarios.entidades.NoAdministrador.Dpto;
import es.mdef.gestionusuarios.entidades.NoAdministrador.Tipo;;

//import es.mdef.gestionpedidos.entidades.Usuario.UsuarioEstado;

@Relation(itemRelation="usuario")
public class UsuarioPostModel extends RepresentationModel<UsuarioPostModel>{

	private String nombre;
	private String nombreUsuario;
	private String password;
	private Rol rol;
	//atributo del administrador
    private String telefono;
   //atributos del noAdministrador
    private Dpto dpto;
    private Tipo tipo;
	

	public String getNombre() {
		return nombre;
	}

	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public String getPassword() {
		return password;
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
		return "UsuarioPostModel [Nombre=" + nombre + ", NombreUsuario=" 
				+ nombreUsuario + "Pass" + password + "]";
	}
	
}