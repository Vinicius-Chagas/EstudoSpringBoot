package med.voll.EstudoSpringBoot.controller;

import med.voll.EstudoSpringBoot.Pacientes.DadosPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @PostMapping
    public void pacientes(@RequestBody DadosPaciente paciente){

        System.out.println(paciente);

    }

}
