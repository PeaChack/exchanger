package by.peachack.exchanger.repository;

import by.peachack.exchanger.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Integer> {
    Optional<CurrencyRate> findByDateAndCurrency_Code(LocalDate date, Integer code);
}
