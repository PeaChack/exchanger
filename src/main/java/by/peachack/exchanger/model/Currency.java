package by.peachack.exchanger.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "currency")
@Data
public class Currency {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "code")
    private Integer code;
    @Column(name = "name")
    private String name;
}
