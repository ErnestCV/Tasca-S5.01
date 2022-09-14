package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.repository;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.model.Flor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlorRepository extends JpaRepository<Flor, Integer> {

    @Query(value = "SELECT * FROM flor f WHERE f.nom LIKE %:keyword%  OR f.pais LIKE %:keyword% ", nativeQuery = true)
    List<Flor> findByKeyword(@Param("keyword") String keyword);
}
