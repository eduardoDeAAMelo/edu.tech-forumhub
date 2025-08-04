package br.com.edutech.forumhubapi.domain.resposta;

import br.com.edutech.forumhubapi.domain.topico.Topico;
import br.com.edutech.forumhubapi.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "resposta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id",nullable = false)
    private Topico topico;

    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    private Boolean solucao;


    public Resposta(RespostaDTO dados, Usuario usuarioLogado, Topico topico) {
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.solucao = true;
        this.autor = usuarioLogado;
        this.topico = topico;

    }
}
