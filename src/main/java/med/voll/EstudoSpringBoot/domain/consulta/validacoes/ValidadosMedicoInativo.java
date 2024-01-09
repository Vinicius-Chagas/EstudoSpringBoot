package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import med.voll.EstudoSpringBoot.domain.medico.MedicoRepository;
import med.voll.EstudoSpringBoot.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadosMedicoInativo {
    @Autowired
    MedicoRepository repository;
    public void Validar(DadosConsulta dados){

        if(dados.idMedico() == null){
            return;
        }

        var medico = repository.getReferenceById(dados.idMedico());

        if(medico.isInativo()){
            throw new ValidationException("A consulta não pode ser agendada pois o médico está inativo no sistema.");
        }
    }
}
