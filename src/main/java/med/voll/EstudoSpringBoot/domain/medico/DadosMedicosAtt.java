package med.voll.EstudoSpringBoot.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.EstudoSpringBoot.domain.endereco.DadosEndereco;
import org.springframework.validation.annotation.Validated;

public record DadosMedicosAtt(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Validated
        DadosEndereco endereco) {
}
