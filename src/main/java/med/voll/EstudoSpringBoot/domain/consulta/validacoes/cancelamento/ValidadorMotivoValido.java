package med.voll.EstudoSpringBoot.domain.consulta.validacoes.cancelamento;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosCancelamentoConsulta;
import med.voll.EstudoSpringBoot.domain.consulta.MotivoCancelamento;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ValidadorMotivoValido implements ValidadorCancelamentoConsulta{
    public void validar(DadosCancelamentoConsulta dados){
        if(dados.idConsulta() == null){
            throw new ValidationException("O id da consulta não pode ser nulo.");
        }
        if(!Arrays.stream(MotivoCancelamento.values()).toList().contains(dados.motivo())){
            throw new ValidationException("Motivo do cancelamento inválido.");
        }
    }
}
