package med.voll.EstudoSpringBoot.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarConsulta(@RequestBody @Valid DadosConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados){
        var dto = agenda.cancelar(dados);
        return ResponseEntity.ok().body(dto);
    }
}
