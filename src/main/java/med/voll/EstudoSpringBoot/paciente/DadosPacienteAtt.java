package med.voll.EstudoSpringBoot.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.EstudoSpringBoot.endereco.DadosEndereco;
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
