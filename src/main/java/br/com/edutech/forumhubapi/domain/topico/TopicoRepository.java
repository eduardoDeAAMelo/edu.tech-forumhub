package br.com.edutech.forumhubapi.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    Boolean existsByTituloAndMensagem(@NotBlank(message = "Título é obrigatório") String titulo, @NotBlank(message = "Mensagem é obrigatório") String mensagem);

}
