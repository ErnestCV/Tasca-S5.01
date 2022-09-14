package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.service;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.dto.SucursalDTO;

import java.util.List;

public interface SucursalService {

    SucursalDTO saveSucursal(SucursalDTO sucursalDTO);
    List<SucursalDTO> getAll();
    SucursalDTO getSucursalById(Integer id);
    SucursalDTO updateSucursal(Integer id, SucursalDTO sucursalDTO);
    void deleteSucursalById(Integer id);
}
