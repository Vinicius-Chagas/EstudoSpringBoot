package med.voll.EstudoSpringBoot.controller;

import med.voll.EstudoSpringBoot.domain.endereco.DadosEndereco;
import med.voll.EstudoSpringBoot.domain.endereco.Endereco;
import med.voll.EstudoSpringBoot.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosMedicos> DM;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> DDM;

    @MockBean
    private MedicoRepository repository;


    @Test
    @DisplayName("Requisição com dados inválidos deve devolver codigo 400.")
    @WithMockUser
    void cadastrarcaso1() throws Exception {
        var response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Requisição com dados validos deve devolver codigo 201.")
    @WithMockUser
    void cadastrarcaso2() throws Exception {

        var endereco = new Endereco(dadosEndereco());
        var medico = cadastrarMedico("Gabriel", "Gabriel@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var dadosMedico = dadosMedico("Gabriel","Gabriel@voll.med","123456",Especialidade.CARDIOLOGIA);
        var detalhamentoMedico = new DadosDetalhamentoMedico(medico);

        var response = mvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DM.write(dadosMedico).getJson()
                        )
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = DDM.write(detalhamentoMedico).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        return medico;
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