package med.voll.EstudoSpringBoot.domain.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.EstudoSpringBoot.domain.endereco.Endereco;

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
    private boolean inativo;


    public Paciente(DadosPaciente paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();;
        this.cpf = paciente.cpf();
        this.endereco = new Endereco(paciente.endereco());
        this.inativo = false;
    }

    public void atualizarInformacoes(DadosPacienteAtt dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarEndereco(dados.endereco());
        }

    }

    public void inativo(){
        this.inativo = true;
    }

    }
