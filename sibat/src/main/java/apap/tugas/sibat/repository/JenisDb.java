package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.JenisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface JenisDb extends JpaRepository<JenisModel, Long> {
    Optional<JenisModel> findByIdJenis(Long idJenis);
    List<JenisModel> findAllByOrderByNamaAsc();
}


