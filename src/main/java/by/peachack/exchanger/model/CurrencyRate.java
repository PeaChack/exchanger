package by.peachack.exchanger.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "currency_rate")
@Data
public class    CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    @Column(name = "scale")
    private Integer scale;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "official_rate")
    private Double officialRate;
}
