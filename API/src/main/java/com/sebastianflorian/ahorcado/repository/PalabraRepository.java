package com.sebastianflorian.ahorcado.repository;

import com.sebastianflorian.ahorcado.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {

    boolean existsByPalabra(String palabra);

    boolean existsByPalabraAndIdPalabraNot(String palabra, Integer id_Palabra);

}