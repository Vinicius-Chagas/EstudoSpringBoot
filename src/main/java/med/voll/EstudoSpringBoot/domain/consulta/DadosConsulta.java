package med.voll.EstudoSpringBoot.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.EstudoSpringBoot.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosConsulta(

        long idMedico,
        @NotNull
        long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}
