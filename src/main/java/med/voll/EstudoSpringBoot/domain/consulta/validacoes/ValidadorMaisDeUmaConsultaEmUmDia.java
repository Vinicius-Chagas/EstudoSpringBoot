package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.ConsultaRepository;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMaisDeUmaConsultaEmUmDia {
    @Autowired
    ConsultaRepository repository;
    public void Validar(DadosConsulta dados){
        
        if(repository.todasAsConsultasDeUmPacienteNumDia(dados.idPaciente(), dados.data())){
            throw new ValidationException("Este médico já tem uma consulta neste horário.");
        }
    }
}
