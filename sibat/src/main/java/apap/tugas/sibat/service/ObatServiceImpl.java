package apap.tugas.sibat.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.repository.ObatDb;

@Service
@Transactional

public class ObatServiceImpl implements ObatService {
    @Autowired
    private ObatDb obatDb;

    @Override
    public void addObat(ObatModel obat) {
        obatDb.save(obat);
    }

    @Override
    public List<ObatModel> getObatList() {
        return obatDb.findAllByOrderByNamaAsc();
    }

    @Override
    // Need check
    public Optional<ObatModel> getObatByIdObat(Long idObat) {
        try {
            return obatDb.findByIdObat(idObat);
        } catch (NoSuchElementException x) {
            return null;
        }
    }

    @Override
    // Need check
    public boolean deleteObat(Long idObat) {
        obatDb.deleteById(idObat);
        return true;
    }

    @Override
    public ObatModel updateObat(ObatModel obatModel) {
        // Mengambil object obat yang ingin diubah
        ObatModel targetObat = obatDb.findById(obatModel.getIdObat()).get();

        try {
            targetObat.setNama(obatModel.getNama());
            targetObat.setTanggalTerbit(obatModel.getTanggalTerbit());
            targetObat.setHarga(obatModel.getHarga());
            targetObat.setBentuk(obatModel.getBentuk());
            obatDb.save(targetObat);
            return targetObat;
        } catch (NullPointerException nullException) {
            return null;
        }
    }
}