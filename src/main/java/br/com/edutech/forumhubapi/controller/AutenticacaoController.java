package br.com.edutech.forumhubapi.controller;

import br.com.edutech.forumhubapi.domain.usuario.AutenticacaoDTO;
import br.com.edutech.forumhubapi.domain.usuario.Usuario;
import br.com.edutech.forumhubapi.infra.security.JWTTokenDTO;
import br.com.edutech.forumhubapi.infra.security.TokenService;
import jakarta.validation.Valid;
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
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO dados){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        System.out.println(authenticationToken);
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());


        return ResponseEntity.ok(new JWTTokenDTO(tokenJWT));

    }
}
