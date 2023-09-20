package med.voll.EstudoSpringBoot.controller;


import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.medico.DadosListagemMedicos;
import med.voll.EstudoSpringBoot.medico.DadosMedicos;
import med.voll.EstudoSpringBoot.medico.Medico;
import med.voll.EstudoSpringBoot.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosMedicos medico){
        repository.save(new Medico(medico));

    }

    @GetMapping
    public List<DadosListagemMedicos> listar(){
        return repository.findAll().stream().map(DadosListagemMedicos::new).toList();
    }

}
