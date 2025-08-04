package br.com.edutech.forumhubapi.controller;

import br.com.edutech.forumhubapi.domain.resposta.DetalhamentoRespostaDTO;
import br.com.edutech.forumhubapi.domain.resposta.Resposta;
import br.com.edutech.forumhubapi.domain.resposta.RespostaDTO;
import br.com.edutech.forumhubapi.domain.resposta.RespostaRepository;
import br.com.edutech.forumhubapi.domain.topico.TopicoRepository;
import br.com.edutech.forumhubapi.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("resposta")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarResposta(@RequestBody @Valid RespostaDTO dados,
                                            @AuthenticationPrincipal Usuario usuarioLogado,
                                            UriComponentsBuilder uriBuilder){

        var topico = topicoRepository.getReferenceById(dados.idTopico());

        var resposta = new Resposta(dados,usuarioLogado,topico);
        System.out.println(resposta);
        repository.save(resposta);

        topico.atualizaStatus();
        var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoRespostaDTO(resposta));


    }
}
