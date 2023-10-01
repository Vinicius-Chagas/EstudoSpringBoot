package med.voll.EstudoSpringBoot.domain.paciente;

public record DadosListagemPacientes(
        String nome,
        String email,
        String cpf,
        boolean inativo
) {

    public DadosListagemPacientes(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.isInativo());
    }

}
