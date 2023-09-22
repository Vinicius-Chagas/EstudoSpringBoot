package med.voll.EstudoSpringBoot.controller;


import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
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
    public Page<DadosListagemMedicos> listar(@PageableDefault(sort = {"nome"}) Pageable pagina){
        return repository.findAllByInativoFalse(pagina).map(DadosListagemMedicos::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosMedicosAtt dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.inativo();
    }

}
