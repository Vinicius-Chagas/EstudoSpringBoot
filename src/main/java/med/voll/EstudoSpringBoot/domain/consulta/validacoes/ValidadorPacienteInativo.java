package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import med.voll.EstudoSpringBoot.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorPacienteInativo {
    @Autowired
    PacienteRepository repository;
    public void Validar(DadosConsulta dados){

        var paciente = repository.getReferenceById(dados.idPaciente());

        if(paciente.isInativo()){
            throw new ValidationException("A consulta não pode ser agendada pois o paciente está inativo no sistema.");
        }
    }
}
