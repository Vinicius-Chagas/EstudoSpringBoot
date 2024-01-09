package med.voll.EstudoSpringBoot.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id,long idMedico, long idPaciente, LocalDateTime data) {

}
