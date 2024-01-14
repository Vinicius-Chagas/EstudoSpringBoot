package med.voll.EstudoSpringBoot.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findAllByInativoFalse(Pageable pagina);

    @Query("SELECT m FROM medicos m WHERE m.inativo = FALSE AND m.especialidade = :especialidade AND m.id NOT IN(SELECT c.medico.id FROM consultas c WHERE c.data = :data) ORDER BY rand() LIMIT 1")
    Medico escolherMedicoAleatorioLivreNaDate(Especialidade especialidade, LocalDateTime data);

}
