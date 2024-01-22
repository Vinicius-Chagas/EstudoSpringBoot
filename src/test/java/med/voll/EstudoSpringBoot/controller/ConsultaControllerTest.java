package med.voll.EstudoSpringBoot.controller;

import med.voll.EstudoSpringBoot.domain.consulta.AgendaDeConsultas;
import med.voll.EstudoSpringBoot.domain.consulta.Consulta;
import med.voll.EstudoSpringBoot.domain.consulta.DadosConsulta;
import med.voll.EstudoSpringBoot.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.EstudoSpringBoot.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosConsulta> JT;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> JTD;

    @MockBean
    private AgendaDeConsultas agendaDeConsultas;

    @Test
    @DisplayName("Requisição com dados inválidos deve devolver codigo 400.")
    @WithMockUser
    void cadastrarConsultaCenario1() throws Exception {
        var response =  mvc.perform(post("/consulta"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Requisição com dados corretos deve devolver codigo 200.")
    @WithMockUser
    void cadastrarConsultaCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);

        var detalhamentoConsulta = new DadosDetalhamentoConsulta(null, 2L, 5L, data,null);
        when(agendaDeConsultas.agendar(any())).thenReturn(detalhamentoConsulta);

        var response =  mvc
                .perform(post("/consulta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JT
                                .write(new DadosConsulta(2L, 5L, data, Especialidade.CARDIOLOGIA)).getJson()
                        )
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = JTD.write(detalhamentoConsulta).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}