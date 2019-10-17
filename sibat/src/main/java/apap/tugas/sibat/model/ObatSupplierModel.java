package apap.tugas.sibat.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import apap.tugas.sibat.model.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="obatSupplier")
public class ObatSupplierModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGudangObat;

	@ManyToOne
    @JoinColumn(name = "obatId", referencedColumnName = "idObat", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ObatModel obat;
 
    @ManyToOne
    @JoinColumn(name = "supplierId", referencedColumnName = "idSupplier", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SupplierModel supplier;

	public Long getIdGudangObat() {
		return idGudangObat;
	}

	public void setIdGudangObat(Long idGudangObat) {
		this.idGudangObat = idGudangObat;
	}

	public ObatModel getObat() {
		return obat;
	}

	public void setObat(ObatModel obat) {
		this.obat = obat;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}
}