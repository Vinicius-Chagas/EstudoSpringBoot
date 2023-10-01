package med.voll.EstudoSpringBoot.domain.medico;


public record DadosListagemMedicos(
        String nome,
        String email,
        Especialidade especialidade,
        String crm,
        Long id,
        boolean inativo
) {
    public DadosListagemMedicos(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getEspecialidade(),medico.getCrm(), medico.getId(), medico.isInativo());
    }
}
