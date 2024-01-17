package med.voll.EstudoSpringBoot.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id,long idMedico, long idPaciente, LocalDateTime data, MotivoCancelamento motivo) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getMotivoCancelamento());
    }
}
