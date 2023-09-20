package med.voll.EstudoSpringBoot.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.EstudoSpringBoot.endereco.DadosEndereco;
import med.voll.EstudoSpringBoot.endereco.Endereco;

@Entity(name = "Paciente")
@Table(name = "Pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;


    public Paciente(DadosPaciente paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();;
        this.cpf = paciente.cpf();
        this.endereco = new Endereco(paciente.endereco());
    }
}
