package br.com.edutech.forumhubapi.domain.resposta;

import jakarta.validation.constraints.NotNull;

public record RespostaDTO(

        String mensagem,

        @NotNull
        Long idTopico

) {
}
