package med.voll.EstudoSpringBoot.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.EstudoSpringBoot.domain.endereco.DadosEndereco;
import org.springframework.validation.annotation.Validated;

public record DadosPacienteAtt(
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @Validated
        DadosEndereco endereco,
        @NotNull
        Long id) {
}
