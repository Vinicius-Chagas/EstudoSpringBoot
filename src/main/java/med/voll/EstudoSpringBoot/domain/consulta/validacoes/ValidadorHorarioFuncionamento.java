package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;

import java.time.DayOfWeek;
import java.util.Date;

public class ValidadorHorarioFuncionamento {
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
