package br.com.delove.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.delove.model.Pet;
import br.com.delove.repository.PetRepository;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> findPetByDisponibilidade(Boolean disponivel) {
        return petRepository.findAllByDisponivel(disponivel);
    }

    public Pet findPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet com o id " + id + " não existe"));
    }

    public List<Pet> listarPetsPorEspecieEDisponibilidade(String especie, boolean disponivel) {
        return petRepository.findAllByEspecieAndDisponivel(especie, disponivel);
    }

    public List<Pet> listarPetsPorSexoEDisponibilidade(String sexo, boolean disponivel) {
        return petRepository.findAllBySexoAndDisponivel(sexo, disponivel);
    }

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
