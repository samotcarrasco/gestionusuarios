package es.mdef.gestionusuarios.entidades;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("I")
public class Inventariable extends Material {
	
	private String noc;
    private String numeroSerie;
	
	
    public String getNoc() {
		return noc;
	}

	public void setNoc(String noc) {
		this.noc = noc;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public TipoMaterial getTipoMaterial() {
		return TipoMaterial.Inventariable;
	}

	@Override
	public String toString() {
		return super.toString() + "MATERIAL INVENTARIABLE [TLF" + super.getNombre() +" " +  getNoc() + "]";
	}

}
	