package br.com.edutech.forumhubapi.domain.usuario;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByLogin(String login);

    Boolean existsByLogin(@NotNull(message = "Login é obrigatório!") String login);
}
