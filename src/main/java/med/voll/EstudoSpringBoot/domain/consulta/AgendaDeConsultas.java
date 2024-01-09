package med.voll.EstudoSpringBoot.domain.consulta;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.medico.Especialidade;
import med.voll.EstudoSpringBoot.domain.medico.Medico;
import med.voll.EstudoSpringBoot.domain.medico.MedicoRepository;
import med.voll.EstudoSpringBoot.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidationException("ID informado para o paciente não existe");
        }
        if(!medicoRepository.existsById(dados.idMedico())){
            throw new ValidationException("ID informado para o médico não existe");
        }

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);

    }

    private Medico escolherMedico(DadosConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidationException("Especialidade é obrigatória quando o médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaDate(dados.especialidade(), dados.data());
    }

    @Transactional
    public void cancelar(DadosCancelamentoConsulta dados) {

        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidationException("O ID da consulta é obrigatório");
        }
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        if (Duration.between(consulta.getData(), LocalDateTime.now()).toHours() < 24) {
            throw new ValidationException("A consulta deve ser cancelada com no minimo 24 horas de antecedência.");
        }

        consulta.cancelar(dados.motivo());
    }
}
