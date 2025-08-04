package br.com.edutech.forumhubapi.domain.topico;

import br.com.edutech.forumhubapi.domain.resposta.Resposta;

public record DetalhamentoTopicoRespostaDTO(
        Long id,
        String mensagem,
        String autorNome

) {

    public DetalhamentoTopicoRespostaDTO(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getAutor().getNome());
    }
}
