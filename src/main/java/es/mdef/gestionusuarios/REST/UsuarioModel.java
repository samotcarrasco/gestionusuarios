package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//import es.mdef.gestionpedidos.entidades.Usuario.UsuarioEstado;

@Relation(itemRelation="usuario")
public class UsuarioModel extends RepresentationModel<UsuarioModel>{

	private int id;
	private String nombre;
	private String nombreUsuario;
	
	//private UsuarioEstado estado = UsuarioEstado.Realizado;
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public String toString() {
		return "UsuarioModel [Nombre=" + nombre + ", NombreUsuario=" 
				+ nombreUsuario + "]";
	}
	
}