package br.com.edutech.forumhubapi.controller;

import br.com.edutech.forumhubapi.domain.usuario.CadastroUsuarioDTO;
import br.com.edutech.forumhubapi.domain.usuario.Usuario;
import br.com.edutech.forumhubapi.domain.usuario.UsuarioCadastradoDTO;
import br.com.edutech.forumhubapi.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class CadastroUsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDTO dados){
        var emailJaCadastrado = repository.existsByLogin(dados.login());

        if (emailJaCadastrado){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login já cadastrado, tente novamente!");
        }
        var senhaBcrypt = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados,senhaBcrypt);
        repository.save(usuario);
        return ResponseEntity.ok(new UsuarioCadastradoDTO(usuario));
    }
}
