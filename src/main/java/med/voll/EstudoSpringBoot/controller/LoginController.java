package med.voll.EstudoSpringBoot.controller;

import jakarta.validation.Valid;
import med.voll.EstudoSpringBoot.domain.usuario.DadosAutenticacao;
import med.voll.EstudoSpringBoot.domain.usuario.Usuario;
import med.voll.EstudoSpringBoot.infra.security.TokenDadosJwT;
import med.voll.EstudoSpringBoot.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){
        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); //Select no banco
        var authentication = manager.authenticate(authToken); // Autentica o resultado do select
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal()); // Gera um toke para o usuario

        return ResponseEntity.ok(new TokenDadosJwT(tokenJwt));
    }


}
