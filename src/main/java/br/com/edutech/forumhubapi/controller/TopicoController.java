package br.com.edutech.forumhubapi.controller;

import br.com.edutech.forumhubapi.domain.topico.*;
import br.com.edutech.forumhubapi.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid CadastroTopicoDTO dados, @AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uriBuilder){

        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Existe um tópico com esse titulo e mensagem no banco de dados ");
        }

        var topico = new Topico(dados, usuarioLogado);
        topicoRepository.save(topico);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoTopicoDTO(topico));


    }

    @GetMapping
    public ResponseEntity<Page<ListagemTopicosDTO>>listarTodosTopicos(@PageableDefault(size =10,sort ={"dataCriacao"}) Pageable paginacao){
        var page = topicoRepository.findAll(paginacao).map(ListagemTopicosDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUnicoTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok((new DetalhamentoTopicoUnico(topico)));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoTopicoDTO dados ,@AuthenticationPrincipal Usuario usuarioLogado){

        var topico = topicoRepository.getReferenceById(dados.id());

        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! somente o autor do tópico tem autorização para atualizar! ");
        }

        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DetalhamentoTopicoDTO(topico) );
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id,@AuthenticationPrincipal Usuario usuarioLogado){

        var topico = topicoRepository.getReferenceById(id);

        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado! Apenas o autor do tópico pode excluí-lo ");
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }



}
