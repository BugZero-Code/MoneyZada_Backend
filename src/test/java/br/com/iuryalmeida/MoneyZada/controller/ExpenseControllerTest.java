package br.com.iuryalmeida.MoneyZada.controller;

import br.com.iuryalmeida.MoneyZada.model.Expense;
import br.com.iuryalmeida.MoneyZada.repository.ExpenseRepository;
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

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseRepository expenseRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Expense expense;

    @BeforeEach
    void setUp() {
        expense = new Expense();
        expense.setName("Aluguel");
        expense.setAmount(1500.0);
    }

    @Test
    void registerExpense_ShouldReturn201_WhenValidExpense() throws Exception {
        // Mockando a resposta do repository para quando salvar uma despesa
        Mockito.when(expenseRepository.save(Mockito.any(Expense.class))).thenReturn(expense);

        mockMvc.perform(post("/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expense)))  // Enviando o objeto como JSON
                .andExpect(status().isCreated())  // Espera o status 201 Created
                .andExpect(content().string("Despesa registrada com sucesso"));  // Verifica a resposta correta
    }

    @Test
    void registerExpense_ShouldReturn400_WhenInvalidExpense() throws Exception {
        // Criando uma despesa inválida (sem dados suficientes)
        Expense invalidExpense = new Expense();  // Faltando dados obrigatórios

        mockMvc.perform(post("/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidExpense)))  // Enviando um objeto vazio como JSON
                .andExpect(status().isBadRequest());  // Espera o status 400 Bad Request
    }
}
