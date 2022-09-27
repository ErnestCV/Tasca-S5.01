package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SucursalDTO implements Serializable {

    private Integer id;
    @NotBlank(message = "La sucursal ha de tenir un nom")
    private String nomSucursal;
    @NotBlank(message = "La sucursal ha de tenir un país")
    private String paisSucursal;
    private final List<String> EUCountries = List.of(
            "Austria",
            "Belgium",
            "Bulgaria",
            "Croatia",
            "Cyprus,",
            "Czech Republic",
            "Denmark",
            "Estonia",
            "Finland",
            "France",
            "Germany",
            "Greece",
            "Hungary",
            "Ireland",
            "Italy",
            "Latvia",
            "Lithuania",
            "Luxembourg",
            "Malta",
            "The Netherlands",
            "Poland",
            "Portugal",
            "Romania",
            "Slovakia",
            "Slovenia",
            "Spain",
            "Sweden"
    );

    private String tipusSucursal = EUCountries.stream()
            .anyMatch(country -> country.equalsIgnoreCase(paisSucursal)) ? "UE" : "Fora UE";
}

