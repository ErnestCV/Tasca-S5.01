package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Flor implements Serializable {

    private Integer id;

    private String nom;
    private String pais;
}

