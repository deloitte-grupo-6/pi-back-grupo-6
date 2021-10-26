package br.com.delove.repository;

import br.com.delove.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

    List<Pet> findAllByEspecieAndDisponivel(String especie, boolean disponivel);

    List<Pet> findAllBySexoAndDisponivel(String sexo, boolean disponivel);

    List<Pet> findAllByRacaAndDisponivel(String raca, boolean disponivel);

    List<Pet> findAllByDisponivel(Boolean disponivel);


}


