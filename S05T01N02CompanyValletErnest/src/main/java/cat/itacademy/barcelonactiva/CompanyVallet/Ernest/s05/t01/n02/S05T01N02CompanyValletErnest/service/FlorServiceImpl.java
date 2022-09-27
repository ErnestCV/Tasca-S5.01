package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.service;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.dto.FlorDTO;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.exception.ElementNotFoundException;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.model.Flor;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.repository.FlorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlorServiceImpl implements FlorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    FlorRepository florRepository;

    @Override
    public FlorDTO saveFlor(FlorDTO florDTO) {
        Flor flor = mapToEntity(florDTO);
        Flor newFlor = florRepository.save(flor);
        return mapToDTO(newFlor);
    }

    @Override
    public List<FlorDTO> getAll() {
        List<Flor> list = florRepository.findAll();
        return list.stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(FlorDTO::getId))
                .collect(Collectors.toList());
    }

    @Override
    public FlorDTO getFlorById(Integer id) {
        return florRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ElementNotFoundException("Flor amb id: " + id + " no trobada."));
    }

    @Override
    public FlorDTO updateFlor(Integer id, FlorDTO florDTO) {
        FlorDTO flor = getFlorById(id);
        flor.setNom(florDTO.getNom());
        flor.setPais(florDTO.getPais());

        florRepository.save(mapToEntity(flor));
        flor.calculateTipusFlor();
        return flor;
    }

    @Override
    public void deleteFlorById(Integer id) {
        FlorDTO florDTO = getFlorById(id);
        florRepository.delete(mapToEntity(florDTO));
    }

    @Override
    public List<FlorDTO> findByKeyword(String keyword) {
        List<Flor> florList = florRepository.findByKeyword(keyword);
        return florList.stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(FlorDTO::getId))
                .collect(Collectors.toList());
    }

    // Convert entity to DTO
    private FlorDTO mapToDTO(Flor flor) {
        FlorDTO florDTO = modelMapper.map(flor, FlorDTO.class);
        florDTO.calculateTipusFlor();
        return florDTO;
    }

    // Convert DTO to entity
    private Flor mapToEntity(FlorDTO florDTO) {
        return modelMapper.map(florDTO, Flor.class);
    }
}
