package by.peachack.exchanger.controller;

import by.peachack.exchanger.dto.CurrencyRateDTO;
import by.peachack.exchanger.mapper.CurrencyRateMapper;
import by.peachack.exchanger.model.CurrencyRate;
import by.peachack.exchanger.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;
    private final CurrencyRateMapper currencyRateMapper;

    @GetMapping("/load")
    ResponseEntity<List<CurrencyRateDTO>> loadRates(@RequestParam(name = "date", required = false) String dateStr) {
        LocalDate date = parseDate(dateStr);
        List<CurrencyRateDTO> currencyRates = currencyRateService.loadCurrency(date).stream()
                .map(currencyRateMapper::currencyRateToDTO)
                .toList();
        return ResponseEntity.ok()
                .body(currencyRates);
    }


    @GetMapping("/rate")
    ResponseEntity<CurrencyRateDTO> getRate(@RequestParam(name = "date", required = false) String dateStr,
                                            @RequestParam(name = "code") Integer code) {
        LocalDate date = parseDate(dateStr);
        CurrencyRate currencyRate = currencyRateService.getRate(date, code);
        CurrencyRateDTO currencyRateDTO = currencyRateMapper.currencyRateToDTO(currencyRate);
        return ResponseEntity.ok()
                .body(currencyRateDTO);
    }
    private static LocalDate parseDate(String dateStr) {
        LocalDate date;
        if (dateStr == null)
            date = LocalDate.now();
        else
            date = LocalDate.parse(dateStr);
        return date;
    }
}
