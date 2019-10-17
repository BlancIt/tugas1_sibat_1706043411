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
@Table(name="gudangObat")
public class GudangObatModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGudangObat;

	@ManyToOne
    @JoinColumn(name = "obatId", referencedColumnName = "idObat", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ObatModel obat;
 
    @ManyToOne
    @JoinColumn(name = "gudangId", referencedColumnName = "idGudang", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GudangModel gudang;

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

	public GudangModel getGudang() {
		return gudang;
	}

	public void setGudang(GudangModel gudang) {
		this.gudang = gudang;
	}
}