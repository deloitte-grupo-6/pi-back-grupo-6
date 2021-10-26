package br.com.delove.controller;

import br.com.delove.model.Pet;
import br.com.delove.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Pet>> listarPetsPorEspecie(@PathVariable String especie) {
        List<Pet> listaPorEspecie = petService.findPetBySpecie(especie);
        return new ResponseEntity<>(listaPorEspecie, HttpStatus.OK);
    }

    @GetMapping("/sexo/{sexo}")
    public ResponseEntity<List<Pet>> listarPetsPorSexo(@PathVariable String sexo) {
        List<Pet> listaPorSexo = petService.findPetBySexo(sexo);
        return new ResponseEntity<>(listaPorSexo, HttpStatus.OK);
    }

    @GetMapping("/raca/{raca}")
    public ResponseEntity<List<Pet>> listarPetsPorRaca(@PathVariable String raca) {
        List<Pet> listaPorRaca = petService.findPetByRaca(raca);
        return new ResponseEntity<>(listaPorRaca, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet){
        Pet novoPet = petService.inserirPet(pet);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")

    public ResponseEntity<?> deletarPetById(@PathVariable Long id) {
        if(!petService.petExistePorId(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        petService.deletarPet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   @PutMapping("/doar/{id}")
    public ResponseEntity<Pet> doarPet(@PathVariable Long id) {
       if(!petService.petExistePorId(id)){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       Pet pet = petService.findPetById(id);
       petService.doacaoConcluida(pet);
       return new ResponseEntity<>(pet, HttpStatus.CREATED);
   }
}
