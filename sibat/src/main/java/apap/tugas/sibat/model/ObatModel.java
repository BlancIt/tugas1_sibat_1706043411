package apap.tugas.sibat.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import apap.tugas.sibat.model.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

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
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggalTerbit;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	  name = "obat_supplier", 
	  joinColumns = @JoinColumn(name = "obatId",  referencedColumnName = "idObat"), 
	  inverseJoinColumns = @JoinColumn(name = "supplierId", referencedColumnName = "idSupplier"))
	private List<SupplierModel> listSupplier;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	  name = "gudang_obat", 
	  joinColumns = @JoinColumn(name = "obatId",  referencedColumnName = "idObat"), 
	  inverseJoinColumns = @JoinColumn(name = "gudangId", referencedColumnName = "idGudang"))
	private List<GudangModel> listGudang;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jenisId", referencedColumnName = "idJenis", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisModel jenis;

	public Long getIdObat() {
		return idObat;
	}

	public void setIdObat(Long idObat) {
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

	public LocalDate getTanggalTerbit() {
		return tanggalTerbit;
	}

	public void setTanggalTerbit(LocalDate tanggalTerbit) {
		this.tanggalTerbit = tanggalTerbit;
	}
	
	

	/*public Set<ObatSupplierModel> getObatSupplier() {
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
	}*/

	public List<SupplierModel> getListSupplier() {
		return listSupplier;
	}

	public void setListSupplier(List<SupplierModel> listSupplier) {
		this.listSupplier = listSupplier;
	}

	public List<GudangModel> getListGudang() {
		return listGudang;
	}

	public void setListGudang(List<GudangModel> listGudang) {
		this.listGudang = listGudang;
	}

	public JenisModel getJenis() {
		return jenis;
	}

	public void setJenis(JenisModel jenis) {
		this.jenis = jenis;
	}
	
	public void generateKode() {
		String a = "";
		if (this.getJenis().getNama().equals("Generik")) {
			a += "1";
		} else {
			a += "2";
		}
		
		if (this.getBentuk().equals("Cairan")) {
			a += "01";
		} else if (this.getBentuk().equals("Kapsul")) {
			a += "02";
		} else {
			a += "03";
		}
		
		a = a + this.getEnteredDate() + this.getTanggalTerbitInYearPlusFive() + this.randomCapitalLetter();
		this.kode = a;
	}
	
	public String showKode() {
		String a = "";
		if (this.getJenis().getNama().equals("Generik")) {
			a += "1";
		} else {
			a += "2";
		}
		
		if (this.getBentuk().equals("Cairan")) {
			a += "01";
		} else if (this.getBentuk().equals("Kapsul")) {
			a += "02";
		} else {
			a += "03";
		}
		
		a = a + this.getEnteredDate() + this.getTanggalTerbitInYearPlusFive() + this.randomCapitalLetter();
		return a;
	}
	
	public String getTanggalTerbitInYear() {
		return Integer.toString(tanggalTerbit.getYear());
	}
	
	public String getTanggalTerbitInYearPlusFive() {
		return Integer.toString(tanggalTerbit.getYear() + 5);
	}
	
	public String getEnteredDate() {
		return Integer.toString(LocalDate.now().getYear());
	}
	
	public String randomCapitalLetter() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder result = new StringBuilder(2); 

		for (int i = 0; i < 2; i++) { 
			int index = (int)(letters.length() * Math.random()); 
			result.append(letters.charAt(index)); 
		} 
		return result.toString(); 
	}
}