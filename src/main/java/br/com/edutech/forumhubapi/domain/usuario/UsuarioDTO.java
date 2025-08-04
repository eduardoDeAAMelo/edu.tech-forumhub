package br.com.edutech.forumhubapi.domain.usuario;

public record UsuarioDTO(

        String nome,
        String email,
        String senha,
        Perfil perfil

) {
}
