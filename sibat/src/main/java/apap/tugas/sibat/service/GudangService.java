package apap.tugas.sibat.service;

import java.util.List;  
import java.util.Optional;
import apap.tugas.sibat.model.*;

public interface GudangService {
    // Method untuk menambah Gudang
    void addGudang(GudangModel gudang);

    // Method untuk mendapatkan semua data Gudang yang tersimpan
    List<GudangModel> getGudangList();

    // Method untuk mendapatkan data sebuah Gudang berdasarkan idGudang
    Optional<GudangModel> getGudangByIdGudang(Long idGudang);

    // Method untuk menghapus data sebuah Gudang berdasarkan idGudang
    boolean deleteGudang(Long idGudang);
    
    // Method untuk mengupdate data sebuah Gudang berdasarkan idGudang
    GudangModel updateGudang(GudangModel gudangModel);
    
    GudangModel assignObat(GudangModel gudangModel, ObatModel obatModel);
}