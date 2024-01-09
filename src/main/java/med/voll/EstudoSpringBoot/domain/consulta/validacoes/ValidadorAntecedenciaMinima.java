package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorAntecedenciaMinima {
    public void Validacao(DadosConsulta dados){

        if(Duration.between(dados.data(),LocalDateTime.now()).toMinutes() < 30){
            throw new ValidationException("A antecedencia mínima para agendamento é de 30 minutos.");
        }
    }
}
