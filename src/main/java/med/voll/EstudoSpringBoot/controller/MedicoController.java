package med.voll.EstudoSpringBoot.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    //O padrao é responder 201(created) para metodos cadastrar, mas o metodo requer a uri do objeto e o objeto criado no banco de dados como retorno.
    public ResponseEntity cadastrar(@RequestBody @Valid DadosMedicos medico, UriComponentsBuilder uriBuilder){
        var item = new Medico(medico);
        repository.save(item);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(item.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(item));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(sort = {"nome"}) Pageable pagina){
        var page = repository.findAllByInativoFalse(pagina).map(DadosListagemMedicos::new);

        return ResponseEntity.ok(page);

    }

    //É uma boa pratica criar um objeto de detalhamento para devolver quando atualizar o registro
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosMedicosAtt dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    // NoContent é a boa pratica para resposta de metodos delete
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.inativo();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhes(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
