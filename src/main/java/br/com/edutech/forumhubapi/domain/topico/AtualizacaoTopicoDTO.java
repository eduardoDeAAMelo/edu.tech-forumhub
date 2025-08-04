package br.com.edutech.forumhubapi.domain.topico;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoTopicoDTO(

        @NotNull(message = "Campo id é obrigatório!")
        Long id,
        String titulo,
        String mensagem

) {
}
