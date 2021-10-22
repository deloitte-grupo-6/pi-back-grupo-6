package br.com.delove.repository;

import br.com.delove.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByEspecieContainingIgnoreCase (String especie);
    List<Pet> findAllByRacaContainingIgnoreCase (String raca);
}
