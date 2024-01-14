package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.ConsultaRepository;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaNoMesmoHorarioEMedico implements ValidadorAgendamentoConsulta{
    @Autowired
    ConsultaRepository repository;
    public void validar(DadosConsulta dados){

        if(dados.idMedico() == null){
            return;
        }

        if(repository.medicoComConsultaNaMesmaHora(dados.idMedico(),dados.data())){
            throw new ValidationException("Este médico já tem uma consulta neste horário.");
        }
    }
}