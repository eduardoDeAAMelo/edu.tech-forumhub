package br.com.edutech.forumhubapi.domain.topico;

import java.time.LocalDateTime;

public record ListagemTopicosDTO(Long id, String titulo, String mensagem, LocalDateTime dataCriacao) {

    public ListagemTopicosDTO(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),topico.getDataCriacao());
    }

}
