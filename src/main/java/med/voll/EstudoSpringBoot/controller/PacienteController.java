package med.voll.EstudoSpringBoot.controller;

import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.medico.DadosListagemMedicos;
import med.voll.EstudoSpringBoot.paciente.DadosListagemPacientes;
import med.voll.EstudoSpringBoot.paciente.DadosPaciente;
import med.voll.EstudoSpringBoot.paciente.Paciente;
import med.voll.EstudoSpringBoot.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<DadosListagemPacientes> listar(@PageableDefault(sort = "nome",direction = Sort.Direction.ASC, size = 10) Pageable page){
        return repository.findAll(page).map(DadosListagemPacientes::new);
    }

}
