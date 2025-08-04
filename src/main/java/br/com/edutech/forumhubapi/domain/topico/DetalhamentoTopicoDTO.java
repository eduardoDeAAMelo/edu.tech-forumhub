package br.com.edutech.forumhubapi.domain.topico;

import java.time.LocalDateTime;

public record DetalhamentoTopicoDTO(Long id, String titulo, String mensagem, LocalDateTime dataCriacao) {

    public DetalhamentoTopicoDTO(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
    }
}
