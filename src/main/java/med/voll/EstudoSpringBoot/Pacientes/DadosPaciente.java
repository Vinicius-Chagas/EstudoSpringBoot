package med.voll.EstudoSpringBoot.Pacientes;

import med.voll.EstudoSpringBoot.endereco.DadosEndereco;

public record DadosPaciente(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
}
