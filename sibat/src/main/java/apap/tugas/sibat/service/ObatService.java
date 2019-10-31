package apap.tugas.sibat.service;

import java.util.List;  
import java.util.Optional;
import apap.tugas.sibat.model.*;

public interface ObatService {
    // Method untuk menambah Obat
    void addObat(ObatModel obat);

    // Method untuk mendapatkan semua data Obat yang tersimpan
    List<ObatModel> getObatList();

    // Method untuk mendapatkan data sebuah Obat berdasarkan idObat
    Optional<ObatModel> getObatByIdObat(Long idObat);
    
    // Method untuk mendapatkan data sebuah Obat berdasarkan nomorRegistrasi
    Optional<ObatModel> getObatByNomorRegistrasi(String nomorRegistrasi);

    // Method untuk menghapus data sebuah Obat berdasarkan idObat
    boolean deleteObat(Long idObat);
    
    // Method untuk mengupdate data sebuah Obat berdasarkan idObat
    ObatModel updateObat(ObatModel obatModel);
    
    ObatModel assignObat(GudangModel gudangModel, ObatModel obatModel);
}