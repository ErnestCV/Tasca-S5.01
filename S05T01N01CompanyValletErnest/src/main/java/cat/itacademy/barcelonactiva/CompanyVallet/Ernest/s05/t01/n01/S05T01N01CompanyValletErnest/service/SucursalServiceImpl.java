package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.service;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.exception.ElementNotFoundException;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.model.Sucursal;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.repository.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalServiceImpl implements SucursalService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public SucursalDTO saveSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = mapToEntity(sucursalDTO);
        Sucursal newSucursal = sucursalRepository.save(sucursal);
        return mapToDTO(newSucursal);
    }

    @Override
    public List<SucursalDTO> getAll() {
        List<Sucursal> list = sucursalRepository.findAll();
        return list.stream().map(this::mapToDTO).sorted((Comparator.comparing(SucursalDTO::getId))).collect(Collectors.toList());
    }

    public List<SucursalDTO> findByKeyword(String keyword) {
        List<Sucursal> list = sucursalRepository.findByKeyword(keyword);
        return list.stream().map(this::mapToDTO).sorted((Comparator.comparing(SucursalDTO::getId))).collect(Collectors.toList());
    }

    @Override
    public SucursalDTO getSucursalById(Integer id) {
        return sucursalRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ElementNotFoundException("Sucursal amb id: " + id + " no trobada"));
    }

    @Override
    public SucursalDTO updateSucursal(Integer id, SucursalDTO sucursalDTO) {
        SucursalDTO sucursalOld = getSucursalById(id);
        sucursalOld.setNomSucursal(sucursalDTO.getNomSucursal());
        sucursalOld.setPaisSucursal(sucursalDTO.getPaisSucursal());

        sucursalRepository.save(mapToEntity(sucursalOld));
        return sucursalOld;
    }

    @Override
    public void deleteSucursalById(Integer id) {
        sucursalRepository.deleteById(id);
    }

    // Convert entity to DTO
    private SucursalDTO mapToDTO(Sucursal sucursal) {
        return modelMapper.map(sucursal, SucursalDTO.class);
    }

    // Convert DTO to entity
    private Sucursal mapToEntity(SucursalDTO sucursalDTO) {
        return modelMapper.map(sucursalDTO, Sucursal.class);
    }
}
