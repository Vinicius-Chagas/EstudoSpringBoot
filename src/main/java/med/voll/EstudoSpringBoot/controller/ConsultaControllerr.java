package med.voll.EstudoSpringBoot.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.domain.consulta.AgendaDeConsultas;
import med.voll.EstudoSpringBoot.domain.consulta.DadosCancelamentoConsulta;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    private ResponseEntity cadastrarConsulta(@RequestBody @Valid DadosConsulta dados){
        System.out.println(agenda);
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping
    @Transactional
    private ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);
        return ResponseEntity.ok().body(null);
    }
}
