package med.voll.EstudoSpringBoot.medico;

import lombok.AllArgsConstructor;


public record DadosListagemMedicos(
        String nome,
        String email,
        Especialidade especialidade,
        String crm
) {
    public DadosListagemMedicos(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getEspecialidade(),medico.getCrm());
    }
}
