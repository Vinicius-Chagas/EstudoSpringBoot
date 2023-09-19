package med.voll.EstudoSpringBoot.controller;

import med.voll.EstudoSpringBoot.endereco.Endereco;
import med.voll.EstudoSpringBoot.medico.DadosMedicos;
import med.voll.EstudoSpringBoot.medico.Medico;
import med.voll.EstudoSpringBoot.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    @PostMapping
    public void cadastrar(@RequestBody DadosMedicos medico){
        repository.save(new Medico(medico));

    }

}
