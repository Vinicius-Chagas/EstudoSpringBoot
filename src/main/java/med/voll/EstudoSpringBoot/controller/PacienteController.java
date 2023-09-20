package med.voll.EstudoSpringBoot.controller;

import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.paciente.DadosPaciente;
import med.voll.EstudoSpringBoot.paciente.Paciente;
import med.voll.EstudoSpringBoot.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void pacientes(@RequestBody @Valid DadosPaciente paciente){
        repository.save(new Paciente(paciente));

    }

}
