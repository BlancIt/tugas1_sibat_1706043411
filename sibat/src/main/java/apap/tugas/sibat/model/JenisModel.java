package apap.tugas.sibat.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import apap.tugas.sibat.model.*;

@Entity
@Table(name="jenis")
public class JenisModel implements Serializable {

	/**
	 *
	 */
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJenis;

	@NotNull
	@Size(max = 255)
	@Column(name="nama", nullable = false)
	private String nama;

	@NotNull
	@Size(max = 255)
	@Column(name="deskripsi", nullable = false)
	private String deskripsi;

	@OneToMany(mappedBy = "jenis", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <ObatModel> listObat;

	public Long getIdJenis() {
		return idJenis;
	}

	public void setIdJenis(Long idJenis) {
		this.idJenis = idJenis;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setAlamat(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public List<ObatModel> getListObat() {
		return listObat;
	}

	public void setListObat(List<ObatModel> listObat) {
		this.listObat = listObat;
	}
}