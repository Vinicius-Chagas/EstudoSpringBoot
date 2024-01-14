package med.voll.EstudoSpringBoot.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    @Query("SELECT CASE WHEN COUNT(c.id) > 0 THEN TRUE ELSE FALSE END FROM consultas c WHERE c.paciente.id = :idPaciente AND DATE(c.data) = DATE(:data)")
    Boolean todasAsConsultasDeUmPacienteNumDia(@Param("idPaciente") long idPaciente, @Param("data") LocalDateTime data);

    @Query("SELECT CASE WHEN COUNT(c.id) > 0 THEN TRUE ELSE FALSE END FROM consultas c WHERE c.medico.id = :idMedico AND c.data = :data")
    Boolean medicoComConsultaNaMesmaHora(@Param("idMedico") long idMedico, @Param("data")LocalDateTime data);
}
