package apap.tugas.sibat.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import apap.tugas.sibat.model.*;

@Entity
@Table(name="gudang")
public class GudangModel implements Serializable {

	/**
	 *
	 */
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGudang;

	@NotNull
	@Size(max = 255)
	@Column(name="nama", nullable = false)
	private String nama;

	@NotNull
	@Size(max = 255)
	@Column(name="alamat", nullable = false)
	private String alamat;
	
	@ManyToMany(mappedBy = "listGudang", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<ObatModel> listObat;

	public Long getIdGudang() {
		return idGudang;
	}

	public void setIdGudang(Long idGudang) {
		this.idGudang = idGudang;
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

	public List<ObatModel> getListObat() {
		return listObat;
	}
	
	public Integer getJumlahObat() {
		return listObat.size();
	}

	public void setListObat(List<ObatModel> listObat) {
		this.listObat = listObat;
	}
	
	public void addObat(ObatModel obat) {
		this.listObat.add(obat);
	}
}