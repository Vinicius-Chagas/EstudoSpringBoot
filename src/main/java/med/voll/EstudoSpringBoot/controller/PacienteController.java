package med.voll.EstudoSpringBoot.controller;

import jakarta.validation.Valid;

import med.voll.EstudoSpringBoot.domain.paciente.*;
import med.voll.EstudoSpringBoot.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity pacientes(@RequestBody @Valid DadosPaciente paciente, UriComponentsBuilder uriBuilder){
        var item = new Paciente(paciente);
        repository.save(item);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(item.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(item));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> listar(@PageableDefault(sort = "nome",direction = Sort.Direction.ASC, size = 10) Pageable page){
        var item = repository.findAllByInativoFalse(page).map(DadosListagemPacientes::new);
        return ResponseEntity.ok(item);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosPacienteAtt dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.inativo();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhes(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

}
