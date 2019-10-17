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
@Table(name="obat")
public class ObatModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idObat;

	@NotNull
	@Size(max = 255)
	@Column(name="nama", nullable = false)
	private String nama;

    @NotNull
    @Size(max = 255)
	@Column(name="kode", nullable = false, unique = true)
	private String kode;

    @NotNull
	@Column(name="harga", nullable = false)
	private Double harga;
    
    @NotNull
    @Size(max = 255)
	@Column(name="nomorRegistrasi", nullable = false, unique = true)
	private String nomorRegistrasi;
    
    @NotNull
	@Size(max = 255)
	@Column(name="bentuk", nullable = false)
	private String bentuk;

	@NotNull
	@Size(max = 50)
	@Column(name="deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name="tanggalTerbit", nullable = false)
	@Temporal(TemporalType.DATE)
    private Date tanggalTerbit;
	
	@OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ObatSupplierModel> obatSupplier;
	 
	@OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<GudangObatModel> gudangObat;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jenisId", referencedColumnName = "idJenis", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisModel jenis;

	public Long getId() {
		return idObat;
	}

	public void setId(Long idObat) {
		this.idObat = idObat;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public Double getHarga() {
		return harga;
	}

	public void setHarga(Double harga) {
		this.harga = harga;
	}

	public String getNomorRegistrasi() {
		return nomorRegistrasi;
	}

	public void setNomorRegistrasi(String nomorRegistrasi) {
		this.nomorRegistrasi = nomorRegistrasi;
	}

	public String getBentuk() {
		return bentuk;
	}

	public void setBentuk(String bentuk) {
		this.bentuk = bentuk;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Date getTanggalTerbit() {
		return tanggalTerbit;
	}

	public void setTanggalTerbit(Date tanggalTerbit) {
		this.tanggalTerbit = tanggalTerbit;
	}

	public Set<ObatSupplierModel> getObatSupplier() {
		return obatSupplier;
	}

	public void setObatSupplier(Set<ObatSupplierModel> obatSupplier) {
		this.obatSupplier = obatSupplier;
	}

	public Set<GudangObatModel> getGudangObat() {
		return gudangObat;
	}

	public void setGudangObat(Set<GudangObatModel> gudangObat) {
		this.gudangObat = gudangObat;
	}

	public JenisModel getJenis() {
		return jenis;
	}

	public void setJenis(JenisModel jenis) {
		this.jenis = jenis;
	}
}