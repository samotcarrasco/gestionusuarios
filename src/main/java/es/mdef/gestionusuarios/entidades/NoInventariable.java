package es.mdef.gestionusuarios.entidades;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import es.mdef.gestionusuarios.entidades.Material.TipoMaterial;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("N")
public class NoInventariable extends Material {
	
	private int bonificacion;
    


	public int getBonificacion() {
		return bonificacion;
	}



	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}

	public TipoMaterial getTipoMaterial() {
		return TipoMaterial.noInventariable;
	}

	@Override
	public String toString() {
		return super.toString() + "MATERIAL INVENTARIABLE [TLF" + super.getNombre() +" " +  getBonificacion() + "]";
	}

}
	