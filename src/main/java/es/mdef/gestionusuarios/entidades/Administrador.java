package es.mdef.gestionusuarios.entidades;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {
	
	@NotBlank(message="El telefono es obligatorio")
	private String telefono;
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String tlf) {
		this.telefono = tlf;
	}
	public Rol getRol() {
		return Rol.Administrator;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Administrador [TLF" + telefono + "]";
	}


}
	