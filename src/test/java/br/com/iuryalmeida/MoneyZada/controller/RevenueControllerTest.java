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

    @SuppressWarnings("removal")
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
        Mockito.when(revenueRepository.save(Mockito.any(Revenue.class))).thenReturn(revenue);

        mockMvc.perform(post("/revenues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(revenue)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Receita registrada com sucesso"));
    }

    @Test
    void registerRevenue_ShouldReturn400_WhenInvalidRevenue() throws Exception {
        Revenue invalidRevenue = new Revenue();

        mockMvc.perform(post("/revenues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRevenue)))
                .andExpect(status().isBadRequest());
    }
}
