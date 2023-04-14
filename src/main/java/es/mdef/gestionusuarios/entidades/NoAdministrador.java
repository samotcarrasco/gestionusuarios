package es.mdef.gestionusuarios.entidades;


import jakarta.persistence.Entity;
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
	
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@JsonIgnore
	private Tipo tipo;
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
	@Override
	public String toString() {
		return super.toString() + "NoAdministrador [tipo=" + tipo + ", dpto=" + dpto + "]";
	}


}
	