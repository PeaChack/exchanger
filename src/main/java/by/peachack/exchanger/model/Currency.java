package by.peachack.exchanger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "currency")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
