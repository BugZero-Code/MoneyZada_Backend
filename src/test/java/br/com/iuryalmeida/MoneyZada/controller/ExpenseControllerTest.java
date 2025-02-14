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

    @SuppressWarnings("removal")
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
        Mockito.when(expenseRepository.save(Mockito.any(Expense.class))).thenReturn(expense);

        mockMvc.perform(post("/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Despesa registrada com sucesso"));
    }

    @Test
    void registerExpense_ShouldReturn400_WhenInvalidExpense() throws Exception {
        Expense invalidExpense = new Expense();

        mockMvc.perform(post("/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidExpense)))
                .andExpect(status().isBadRequest());
    }
}
