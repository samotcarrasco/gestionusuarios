package es.mdef.gestionusuarios.entidades;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="USUARIOS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name="rol_usuario", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("null")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Usuario {
	public static enum Rol {
		Administrator,
	    noAdministrator	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String nombreUsuario;
	private Rol rol;
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy = "usuario")
	List<Pregunta> preguntas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("PWD DESDE METODO SETTER: " + password);
		this.password = password;
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	
	//preguntar al profesor
	public List <FamiliaImpl> getFamilias() {
//		 List<FamiliaImpl> familias = new ArrayList<>();
//	    for (Pregunta pregunta : this.preguntas) {
//	        FamiliaImpl familia = pregunta.getFamilia();
//	        	familias.add(familia);
//	    }	    
	    return null;
	    //return new ArrayList<>(familias);
	}
	
	@Override
	public String toString() {
		return "USUARIO (DESDE clase usuario) [Nombre=" + nombre + ", NombreUSER=" + nombreUsuario + "   ID " + id + "]" + password;
	}

	
}