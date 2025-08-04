package br.com.edutech.forumhubapi.domain.topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhamentoTopicoUnico(Long id,
                                      String titulo,
                                      String mensagem,
                                      String curso ,
                                      LocalDateTime dataCriacao,
                                      String autor,
                                      Status status,
                                      List<DetalhamentoTopicoRespostaDTO> respostas) {

    public DetalhamentoTopicoUnico(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getCurso(),
                topico.getDataCriacao(),
                topico.getAutor().getNome(),
                topico.getStatus(),
                topico.getResposta().stream()
                        .map(DetalhamentoTopicoRespostaDTO::new)
                        .collect(Collectors.toList())
        );
    }
}
