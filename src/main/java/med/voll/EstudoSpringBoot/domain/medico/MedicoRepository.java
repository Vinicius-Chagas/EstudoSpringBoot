package med.voll.EstudoSpringBoot.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findAllByInativoFalse(Pageable pagina);
}
