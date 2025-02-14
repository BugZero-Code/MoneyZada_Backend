package br.com.iuryalmeida.MoneyZada.controller;

import br.com.iuryalmeida.MoneyZada.model.Revenue;
import br.com.iuryalmeida.MoneyZada.repository.RevenueRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(RevenueController.class)
class RevenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevenueRepository revenueRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Revenue revenue;

    @BeforeEach
    void setUp() {
        revenue = new Revenue();
        revenue.setDescription("Salário");
        revenue.setAmount(5000.0);
        revenue.setDate("2025-02-14");
    }

    @Test
    void registerRevenue_ShouldReturn201_WhenValidRevenue() throws Exception {
        // Mockando a resposta do repository para quando salvar um Revenue
        Mockito.when(revenueRepository.save(Mockito.any(Revenue.class))).thenReturn(revenue);

        mockMvc.perform(post("/revenues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(revenue)))  // Enviando o objeto como JSON
                .andExpect(status().isCreated())  // Espera o status 201 Created
                .andExpect(content().string("Receita registrada com sucesso"));  // Verifica a resposta correta
    }

    @Test
    void registerRevenue_ShouldReturn400_WhenInvalidRevenue() throws Exception {
        // Criando um Revenue inválido (sem dados suficientes)
        Revenue invalidRevenue = new Revenue();  // Faltando dados obrigatórios

        mockMvc.perform(post("/revenues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRevenue)))  // Enviando um objeto vazio como JSON
                .andExpect(status().isBadRequest());  // Espera o status 400 Bad Request
    }
}
