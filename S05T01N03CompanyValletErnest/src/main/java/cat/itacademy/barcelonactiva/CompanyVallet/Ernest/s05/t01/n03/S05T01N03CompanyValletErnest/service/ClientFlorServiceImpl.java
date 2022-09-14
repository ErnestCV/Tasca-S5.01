package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.service;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.dto.FlorDTO;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.exception.ElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientFlorServiceImpl implements ClientFlorService {

    @Autowired
    WebClient webClient;

    @Override
    public Mono<Void> saveFlor(FlorDTO florDTO) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/add").build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(florDTO)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Flux<FlorDTO> getAll() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getAll").build())
                .retrieve()
                .bodyToFlux(FlorDTO.class);
    }

    @Override
    public Mono<FlorDTO> getFlorById(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getOne/{id}").build(id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new ElementNotFoundException("Flor amb id: " + id + " no trobada.")))
                .bodyToMono(FlorDTO.class);
    }

    @Override
    public Mono<FlorDTO> updateFlor(Integer id, FlorDTO florDTO) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder.path("/update/{id}").build(id))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(florDTO)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new ElementNotFoundException("Flor amb id: " + id + " no trobada.")))
                .bodyToMono(FlorDTO.class);
    }

    @Override
    public Mono<Void> deleteFlorById(Integer id) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/delete/{id}").build(id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new ElementNotFoundException("Flor amb id: " + id + " no trobada.")))
                .bodyToMono(Void.class);
    }

    @Override
    public Flux<FlorDTO> findByKeyword(String keyword) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getAll").queryParam("keyword", keyword).build())
                .retrieve()
                .bodyToFlux(FlorDTO.class);
    }
}
