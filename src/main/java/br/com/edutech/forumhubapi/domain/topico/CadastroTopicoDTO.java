package br.com.edutech.forumhubapi.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record CadastroTopicoDTO(
        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "Mensagem é obrigatório")
        String mensagem,

        @NotBlank(message = "Curso é obrigatório")
        String curso

) {
}