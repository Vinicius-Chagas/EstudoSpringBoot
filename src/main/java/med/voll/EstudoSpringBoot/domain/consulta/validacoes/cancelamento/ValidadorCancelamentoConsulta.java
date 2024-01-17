package med.voll.EstudoSpringBoot.domain.consulta.validacoes.cancelamento;

import med.voll.EstudoSpringBoot.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
