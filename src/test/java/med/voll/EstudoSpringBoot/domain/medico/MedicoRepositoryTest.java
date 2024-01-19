package med.voll.EstudoSpringBoot.domain.medico;


import med.voll.EstudoSpringBoot.domain.consulta.Consulta;
import med.voll.EstudoSpringBoot.domain.endereco.DadosEndereco;
import med.voll.EstudoSpringBoot.domain.paciente.DadosPaciente;
import med.voll.EstudoSpringBoot.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {


    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Há médico cadastrado, mas não está disponível. Deve retornar null")
    void escolherMedicoAleatorioLivreNaDateCenario1() {
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = cadastrarMedico("Gabriel", "Gabriel@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Gabriela", "Gabriela@email.com", "10203040506");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaDate(Especialidade.CARDIOLOGIA,proximaSegundaAs10);

        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Há médico cadastrado e está disponivel. Deve retornar medico")
    void escolherMedicoAleatorioLivreNaDateCenario2() {
        //given or arrange
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var proximaSegundaAs11 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11,0);


        var medico = cadastrarMedico("Gabriel", "Gabriel@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Gabriela", "Gabriela@email.com", "10203040506");
        cadastrarConsulta(medico, paciente, proximaSegundaAs11);

        // when or act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaDate(Especialidade.CARDIOLOGIA,proximaSegundaAs10);

        //then or assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    @Test
    @DisplayName("Há médico cadastrado mas não é da especialidade solicitada. Deve retornar null")
    void escolherMedicoAleatorioLivreNaDateCenario3() {
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = cadastrarMedico("Gabriel", "Gabriel@voll.med", "123456", Especialidade.GINECOLOGIA);
        var paciente = cadastrarPaciente("Gabriela", "Gabriela@email.com", "10203040506");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaDate(Especialidade.CARDIOLOGIA,proximaSegundaAs10);

        assertThat(medicoLivre).isNull();
    }

    //teste de aleatoriedade

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosMedicos dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosMedicos(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}