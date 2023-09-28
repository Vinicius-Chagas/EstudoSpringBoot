package med.voll.EstudoSpringBoot.paciente;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Page<Paciente> findAllByInativoFalse(Pageable page);

    Paciente getReferenceByInativoFalse(Long id);
}
