package med.voll.EstudoSpringBoot.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="usuario")
@Table(name="usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
}
