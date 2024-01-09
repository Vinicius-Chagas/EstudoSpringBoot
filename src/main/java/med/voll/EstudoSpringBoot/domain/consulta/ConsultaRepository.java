package med.voll.EstudoSpringBoot.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    @Query("SELECT CASE WHEN COUNT(c) THEN TRUE ELSE FALSE END FROM cadastros c WHERE c.paciente_id = :idPaciente AND DATE(c.data) = DATE(:data)")
    Boolean todasAsConsultasDeUmPacienteNumDia(Long idPaciente, LocalDateTime data);

    @Query("SELECT CASE WHEN COUNT(c) THEN TRUE ELSE FALSE END FROM cadastros c WHERE c.medico_id = :idMedico AND c.data = :data")
    Boolean medicoComConsultaNaMesmaHora(Long idMedico, LocalDateTime data);
}
