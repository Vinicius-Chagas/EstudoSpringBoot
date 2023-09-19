package med.voll.EstudoSpringBoot.medico;

import med.voll.EstudoSpringBoot.endereco.DadosEndereco;

public record DadosMedicos(String nome, String email, String crm,
                           Especialidade especialidade, DadosEndereco endereco) {
}
