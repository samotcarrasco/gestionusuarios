package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Usuario.Rol;

//import es.mdef.gestionusuarios.entidades.Usuario.UsuarioEstado;

@Relation(collectionRelation = "usuarios")
public class UsuarioListaModel extends RepresentationModel<UsuarioListaModel> {
	private String nombre;
	private String nombreUsuario;
	private Rol rol;
	//private UsuarioEstado estado;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
//	public String getNombreUsuario() {
//		return nombreUsuario;
//	}
//	
//	public void setNombreUsuario(String nombreUsuario) {
//		this.nombreUsuario = nombreUsuario;
//	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	@Override
	public String toString() {
		return "UsuarioModel [nombre=" + nombre + ", Rol=" + rol + "]";
	}

}