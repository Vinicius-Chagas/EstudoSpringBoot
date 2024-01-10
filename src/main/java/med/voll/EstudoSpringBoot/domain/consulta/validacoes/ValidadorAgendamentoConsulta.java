package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import med.voll.EstudoSpringBoot.domain.usuario.DadosAutenticacao;

public interface ValidadorAgendamentoConsulta {
    void validar(DadosConsulta dados);
}
