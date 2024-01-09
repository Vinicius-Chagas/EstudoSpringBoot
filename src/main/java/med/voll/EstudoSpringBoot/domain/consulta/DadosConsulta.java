package med.voll.EstudoSpringBoot.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.EstudoSpringBoot.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosConsulta(


        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade) {
}
