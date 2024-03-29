package med.voll.EstudoSpringBoot.domain.consulta.validacoes.agendamento;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaMinima implements ValidadorAgendamentoConsulta{
    public void validar(DadosConsulta dados){

        if(Duration.between(LocalDateTime.now(), dados.data()).toMinutes() < 30){
            throw new ValidationException("A antecedencia mínima para agendamento é de 30 minutos.");
        }
    }
}
