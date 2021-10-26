package br.com.delove.service;

import br.com.delove.model.Pet;
import br.com.delove.repository.PetRepository;
import org.hibernate.event.internal.DefaultSaveOrUpdateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> findPetBySpecie(String especie) {
        return petRepository.findAllByEspecieContainingIgnoreCase(especie);
    }

    public List<Pet> findPetBySexo (String sexo) {
        return petRepository.findAllBySexoContainingIgnoreCase(sexo);
    }

    public List<Pet> findPetByRaca(String raca){
        return petRepository.findAllByRacaContainingIgnoreCase(raca);
    }

    public Pet inserirPet(Pet pet){
        return petRepository.save(pet);
    }


}
