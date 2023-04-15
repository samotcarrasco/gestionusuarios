package es.mdef.gestionusuarios.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;
import es.mdef.gestionusuarios.entidades.NoAdministrador.Dpto;
import es.mdef.gestionusuarios.entidades.NoAdministrador.Tipo;;

//import es.mdef.gestionpedidos.entidades.Usuario.UsuarioEstado;

@Relation(itemRelation="usuario")
public class UsuarioModel extends RepresentationModel<UsuarioModel>{

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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Rol getRol() {
		return rol;
	}

	public void tlf(String tlf) {
		this.telefono = tlf;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setDpto(Dpto dpto) {
		this.dpto = dpto;
	}
	public Dpto getDpto() {
		return dpto;
	}


	@Override
	public String toString() {
		return "UsuarioModel [Nombre=" + nombre + ", NombreUsuario=" 
				+ nombreUsuario + "ROL" + rol + "]";
	}
	
}