package br.com.edutech.forumhubapi.domain.usuario;

public record UsuarioCadastradoDTO(Long id, String email, String nome) {

    public UsuarioCadastradoDTO(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getNome());
    }
}
