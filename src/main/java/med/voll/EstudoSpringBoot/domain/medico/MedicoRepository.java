package med.voll.EstudoSpringBoot.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findAllByInativoFalse(Pageable pagina);

    @Query("SELECT m FROM Medico m WHERE m.inativo = false AND m.especialidade = :especialidade AMD m.id NOT IN(SELECT c.medico.id FROM Consulta c WHERE c.data = :data) ORDER BY rand() LIMIT 1")
    Medico escolherMedicoAleatorioLivreNaDate(Especialidade especialidade, LocalDateTime data);

}
