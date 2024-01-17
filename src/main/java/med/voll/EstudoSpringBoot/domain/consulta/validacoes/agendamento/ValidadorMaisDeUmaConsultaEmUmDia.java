package med.voll.EstudoSpringBoot.domain.consulta.validacoes.agendamento;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.ConsultaRepository;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMaisDeUmaConsultaEmUmDia implements ValidadorAgendamentoConsulta{
    @Autowired
    ConsultaRepository repository;
    public void validar(DadosConsulta dados){
        
        if(repository.todasAsConsultasDeUmPacienteNumDia(dados.idPaciente(), dados.data())){
            throw new ValidationException("Não é possível agendar mais de uma consulta em um mesmo dia.");
        }
    }
}
