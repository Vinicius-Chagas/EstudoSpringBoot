package med.voll.EstudoSpringBoot.domain.consulta.validacoes.agendamento;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import med.voll.EstudoSpringBoot.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadosMedicoInativo implements ValidadorAgendamentoConsulta{
    @Autowired
    MedicoRepository repository;
    public void validar(DadosConsulta dados){

        if(dados.idMedico() == null){
            return;
        }

        var medico = repository.getReferenceById(dados.idMedico());

        if(medico.isInativo()){
            throw new ValidationException("A consulta não pode ser agendada pois o médico está inativo no sistema.");
        }
    }
}
