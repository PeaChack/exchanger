package by.peachack.exchanger.controller;

import by.peachack.exchanger.dto.CurrencyRateDTO;
import by.peachack.exchanger.mapper.CurrencyRateMapper;
import by.peachack.exchanger.model.CurrencyRate;
import by.peachack.exchanger.service.CurrencyRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CurrencyRateControllerTest {


    private MockMvc mockMvc;

    @Mock
    private CurrencyRateService currencyRateService;

    @Mock
    private CurrencyRateMapper currencyRateMapper;

    @InjectMocks
    private CurrencyRateController currencyRateController;

    @BeforeEach
    void setUp() {
            mockMvc = MockMvcBuilders.standaloneSetup(currencyRateController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testGetRateValidDate() throws Exception {
        CurrencyRate mockRate = new CurrencyRate();
        CurrencyRateDTO mockRateDTO = new CurrencyRateDTO();

        when(currencyRateService.getRate(any(LocalDate.class), anyInt())).thenReturn(mockRate);
        when(currencyRateMapper.currencyRateToDTO(any(CurrencyRate.class))).thenReturn(mockRateDTO);

        mockMvc.perform(get("/api/currency/rate")
                        .param("date", "2023-06-30")
                        .param("code", "123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetRateInvalidDate() throws Exception {
        mockMvc.perform(get("/api/currency/rate")
                        .param("date", "invalid-date")
                        .param("code", "123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testLoadRatesValidDate() throws Exception {
        List<CurrencyRate> mockRates = List.of(new CurrencyRate());
        List<CurrencyRateDTO> mockRateDTOs = List.of(new CurrencyRateDTO());

        when(currencyRateService.loadCurrency(any(LocalDate.class))).thenReturn(mockRates);
        when(currencyRateMapper.currencyRateToDTO(any(CurrencyRate.class))).thenReturn(mockRateDTOs.get(0));

        mockMvc.perform(get("/api/currency/load")
                        .param("date", "2023-06-30"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testLoadRatesInvalidDate() throws Exception {
        mockMvc.perform(get("/api/currency/load")
                        .param("date", "invalid-date"))
                .andExpect(status().isBadRequest());
    }
}