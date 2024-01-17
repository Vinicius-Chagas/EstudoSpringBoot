package med.voll.EstudoSpringBoot.domain.consulta.validacoes.agendamento;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;


@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoConsulta{
    public void validar(DadosConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() > 18;

        if(domingo || antesDaAbertura || depoisDoEncerramento){
            throw new ValidationException("Consulta fora do horário de funcionamento.");
        }
    }
}
