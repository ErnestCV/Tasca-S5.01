package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.model.Sucursal;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    //Cerca per nom o país (keyword)
    @Query(value = "SELECT * FROM sucursal s WHERE s.nom LIKE %:keyword% OR s.pais LIKE %:keyword% ", nativeQuery = true)
    List<Sucursal> findByKeyword(@Param("keyword") String keyword);
}
