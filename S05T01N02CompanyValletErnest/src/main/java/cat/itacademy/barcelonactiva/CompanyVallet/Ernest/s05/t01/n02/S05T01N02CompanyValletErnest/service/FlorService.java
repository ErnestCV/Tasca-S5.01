package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.service;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.dto.FlorDTO;

import java.util.List;

public interface FlorService {

    FlorDTO saveFlor(FlorDTO florDTO);
    List<FlorDTO> getAll();
    FlorDTO getFlorById(Integer id);
    FlorDTO updateFlor(Integer id, FlorDTO florDTO);
    void deleteFlorById(Integer id);
    List<FlorDTO> findByKeyword(String keyword);
}
