package med.voll.EstudoSpringBoot.controller;

import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.domain.consulta.Consulta;
import med.voll.EstudoSpringBoot.domain.consulta.ConsultaRepository;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import med.voll.EstudoSpringBoot.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    private ResponseEntity cadastrarConsulta(@RequestBody @Valid DadosConsulta dados){
        var item = new Consulta(dados);
        repository.save(item);

        return ResponseEntity.ok().body(null);
    }
}
