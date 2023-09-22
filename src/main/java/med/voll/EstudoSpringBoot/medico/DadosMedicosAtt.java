package med.voll.EstudoSpringBoot.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.EstudoSpringBoot.endereco.DadosEndereco;
import med.voll.EstudoSpringBoot.endereco.Endereco;
import org.springframework.validation.annotation.Validated;

public record DadosMedicosAtt(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Validated
        DadosEndereco endereco) {
}
