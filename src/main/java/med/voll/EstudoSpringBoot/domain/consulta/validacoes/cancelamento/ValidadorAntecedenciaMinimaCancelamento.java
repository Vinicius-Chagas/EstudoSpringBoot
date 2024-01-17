package med.voll.EstudoSpringBoot.domain.consulta.validacoes.cancelamento;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.ConsultaRepository;
import med.voll.EstudoSpringBoot.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaMinimaCancelamento implements ValidadorCancelamentoConsulta{

    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosCancelamentoConsulta dados){
        if(dados.idConsulta() == null){
            throw new ValidationException("O id da consulta não pode ser nulo.");
        }

        var consulta = repository.getReferenceById(dados.idConsulta());

        if(Duration.between(LocalDateTime.now(), consulta.getData()).toHours() < 24){
            throw new ValidationException("A consulta não pode ser cancelada sem uma antecedência mínima de 24 horas.");
        }
    }
}
