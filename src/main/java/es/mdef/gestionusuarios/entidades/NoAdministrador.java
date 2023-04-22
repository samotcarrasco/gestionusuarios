package es.mdef.gestionusuarios.entidades;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("N")
public class NoAdministrador extends Usuario {
	public static enum Dpto {
		EMIES,
	    CCESP	
	}
	public static enum Tipo {
		alumno,
	    docente,
	    adminsitracion
	}
	
	@NotNull(message="El tipo es obligatorio")
	private Tipo tipo;
	
	@NotNull(message="El dpto es obligatorio")
	private Dpto dpto;
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Dpto getDpto() {
		return dpto;
	}
	public void setDpto(Dpto dpto) {
		this.dpto = dpto;
	}
	
	public Rol getRol() {
		return Rol.noAdministrator;
	}
	@Override
	public String toString() {
		return super.toString() + "NoAdministrador [tipo=" + tipo + ", dpto=" + dpto + "]";
	}


}
	