package apap.tugas.sibat.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.repository.GudangDb;

@Service
@Transactional

public class GudangServiceImpl implements GudangService {
    @Autowired
    private GudangDb gudangDb;

    @Override
    public void addGudang(GudangModel gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public List<GudangModel> getGudangList() {
        return gudangDb.findAllByOrderByNamaAsc();
    }

    @Override
    // Need check
    public Optional<GudangModel> getGudangByIdGudang(Long idGudang) {
        try {
            return gudangDb.findByIdGudang(idGudang);
        } catch (NoSuchElementException x) {
            return null;
        }
    }

    @Override
    // Need check
    public boolean deleteGudang(Long idGudang) {
        gudangDb.deleteById(idGudang);
        return true;
    }

    @Override
    public GudangModel updateGudang(GudangModel gudangModel) {
        // Mengambil object gudang yang ingin diubah
        GudangModel targetGudang = gudangDb.findById(gudangModel.getIdGudang()).get();

        try {
            targetGudang.setNama(gudangModel.getNama());
            targetGudang.setAlamat(gudangModel.getAlamat());
            gudangDb.save(targetGudang);
            return targetGudang;
        } catch (NullPointerException nullException) {
            return null;
        }
    }
}