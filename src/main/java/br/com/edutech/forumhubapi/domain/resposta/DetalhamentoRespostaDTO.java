package br.com.edutech.forumhubapi.domain.resposta;

import java.time.LocalDateTime;

public record DetalhamentoRespostaDTO(Long id, String topico, String mensagem , LocalDateTime dataCriacao, String autor, Boolean solucao) {

    public DetalhamentoRespostaDTO(Resposta resposta){
        this(resposta.getId(),resposta.getTopico().getTitulo(),resposta.getMensagem(),resposta.getDataCriacao(),resposta.getAutor().getNome(),resposta.getSolucao());
    }
}
