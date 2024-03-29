package med.voll.EstudoSpringBoot.domain.consulta;


import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;

import med.voll.EstudoSpringBoot.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import med.voll.EstudoSpringBoot.domain.medico.Medico;
import med.voll.EstudoSpringBoot.domain.medico.MedicoRepository;
import med.voll.EstudoSpringBoot.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadoresAgendamento;

    @Autowired List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidationException("ID informado para o paciente não existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidationException("ID informado para o médico não existe");
        }

        validadoresAgendamento.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if(medico == null){
            throw new ValidationException("Não existe médico disponível nesta data");
        }

        var consulta = new Consulta(null,medico,paciente, dados.data());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        System.out.println(dados.especialidade());
        if(dados.especialidade() == null){
            throw new ValidationException("Especialidade é obrigatória quando o médico não for escolhido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaDate(dados.especialidade(), dados.data());
    }


    public DadosDetalhamentoConsulta cancelar(DadosCancelamentoConsulta dados) {

        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidationException("O ID da consulta é obrigatório");
        }
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        validadoresCancelamento.forEach(v -> v.validar(dados));

        consulta.cancelar(dados.motivo());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }
}
