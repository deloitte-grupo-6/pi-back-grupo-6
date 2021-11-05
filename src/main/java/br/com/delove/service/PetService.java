package br.com.delove.service;

import br.com.delove.model.Pet;
import br.com.delove.model.Usuario;
import br.com.delove.repository.PetRepository;
import org.hibernate.event.internal.DefaultSaveOrUpdateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Transactional
    public List<Pet> findPetByDisponibilidade(Boolean disponivel) {
        return petRepository.findAllByDisponivel(disponivel);
    }

    @Transactional
    public Pet findPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet com o id " + id + " não existe"));
    }

    public List<Pet> listarPetsPorEspecieEDisponibilidade(String especie, boolean disponivel) {
        return petRepository.findAllByEspecieAndDisponivel(especie, disponivel);
    }

    @Transactional
    public List<Pet> listarPetsPorSexoEDisponibilidade(String sexo, boolean disponivel) {
        return petRepository.findAllBySexoAndDisponivel(sexo, disponivel);
    }

    @Transactional
    public List<Pet> listarPetsPorRacaEDisponibilidade(String raca, boolean disponivel) {
        return petRepository.findAllByRacaAndDisponivel(raca, disponivel);
    }

    public Pet inserirPet(Pet pet) {
        pet.setDisponivel(true);
        return petRepository.save(pet);
    }

    public Pet doacaoConcluida(Pet pet) {
        pet.setDisponivel(false);
        return petRepository.save(pet);
    }

    public void deletarPet(Long petId) {
        petRepository.deleteById(petId);
    }

    public boolean petExistePorId(Long petId) {
        return petRepository.existsById(petId);
    }

    public Pet atualizarPet(Pet pet) {
        return petRepository.save(pet);
    }
}
