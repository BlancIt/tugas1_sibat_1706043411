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
@Table(name="supplier")
public class SupplierModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSupplier;

	@NotNull
	@Size(max = 255)
	@Column(name="nama", nullable = false)
	private String nama;

	@NotNull
	@Size(max = 255)
	@Column(name="alamat", nullable = false)
	private String alamat;

	@NotNull
	@Column(name="nomorTelepon", nullable = false)
	private Long nomorTelepon;
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ObatSupplierModel> obatSupplier;

	public Long getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Long getNomorTelepon() {
		return nomorTelepon;
	}

	public void setNomorTelepon(Long nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}

	public Set<ObatSupplierModel> getObatSupplier() {
		return obatSupplier;
	}

	public void setObatSupplier(Set<ObatSupplierModel> obatSupplier) {
		this.obatSupplier = obatSupplier;
	}
}