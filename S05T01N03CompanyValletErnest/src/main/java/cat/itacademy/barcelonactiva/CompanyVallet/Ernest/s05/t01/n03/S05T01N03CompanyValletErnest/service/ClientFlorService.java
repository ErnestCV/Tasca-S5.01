package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.service;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.dto.FlorDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientFlorService {

    Mono<Void> saveFlor(FlorDTO florDTO);
    Flux<FlorDTO> getAll();
    Mono<FlorDTO> getFlorById(Integer id);
    Mono<FlorDTO> updateFlor(Integer id, FlorDTO florDTO);
    Mono<Void> deleteFlorById(Integer id);
    Flux<FlorDTO> findByKeyword(String keyword);
}
