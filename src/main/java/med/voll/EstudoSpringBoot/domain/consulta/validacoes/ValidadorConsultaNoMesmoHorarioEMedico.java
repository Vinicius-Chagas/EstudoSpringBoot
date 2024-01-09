package med.voll.EstudoSpringBoot.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.EstudoSpringBoot.domain.consulta.ConsultaRepository;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorConsultaNoMesmoHorarioEMedico {
    @Autowired
    ConsultaRepository repository;
    public void Validar(DadosConsulta dados){

        if(dados.idMedico() == null){
            return;
        }



        if(repository.medicoComConsultaNaMesmaHora(dados.idMedico(),dados.data())){
            throw new ValidationException("Não é possível agendar mais de uma consulta em um mesmo dia.");
        }
    }
}