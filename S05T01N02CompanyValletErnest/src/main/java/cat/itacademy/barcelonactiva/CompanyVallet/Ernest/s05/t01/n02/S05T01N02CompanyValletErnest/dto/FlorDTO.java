package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FlorDTO implements Serializable {

    private Integer id;
    @NotBlank(message = "La flor ha de tenir un nom")
    private String nom;
    @NotBlank(message = "La flor ha de tenir un país")
    private String pais;
    private String tipusFlor;

    public void calculateTipusFlor() {
        String tipus = Arrays.stream(EUCountries.class.getEnumConstants())
                .anyMatch(e -> e.name().equalsIgnoreCase(pais)) ? "UE" : "Fora UE";
        setTipusFlor(tipus);
    }
}
