package br.com.edutech.forumhubapi.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDTO(

        @NotNull(message = "Login é obrigatório!")
        String login,
        String senha,
        String nome

) {
}
