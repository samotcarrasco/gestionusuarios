package es.mdef.gestionusuarios.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {
	
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@JsonIgnore
	private String telefono;
	
	public String getTelefono() {
		return telefono;
	}
	public void setTipo(String tlf) {
		this.telefono = tlf;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Administrador [TLF" + telefono + "]";
	}


}
	