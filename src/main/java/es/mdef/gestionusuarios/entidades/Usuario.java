package es.mdef.gestionusuarios.entidades;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
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
import jakarta.validation.constraints.NotBlank;

public class Usuario implements UserDetails{
	private static final long serrialVersionUID = 1L;
	public static enum Rol {
		Administrator,
	    noAdministrator	
	}
	private Long id;
	
	private String nombre;
	
	private String username;
	
	//private Rol rol;
	private String password;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("PWD DESDE METODO SETTER: " + password);
		this.password = password;
	}
	
	public Rol getRol() {
		//los roles se obtendrán de las subclases
		return null;
	}

//	public void setRol(Rol rol) {
//		this.rol = rol;
//	}
	
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	
	// metodos seguridad
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	//preguntar al profesor
//	public List <FamiliaImpl> getFamilias() {
//		 List<FamiliaImpl> familias = new ArrayList<>();
//	    for (Pregunta pregunta : this.preguntas) {
//	        FamiliaImpl familia = pregunta.getFamilia();
//        	familias.add(familia);
//    }	    
//	    return null;
//	    //return new ArrayList<>(familias);
//	}
	
	@Override
	public String toString() {
		return "USUARIO (DESDE clase usuario) [Nombre=" + nombre + ", NombreUSER=" + username + "   ID " + id + "]" + password;
	}
	

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<SimpleGrantedAuthority>(
				Arrays.asList(new SimpleGrantedAuthority(getRol().toString()))
				);
	}

		
}