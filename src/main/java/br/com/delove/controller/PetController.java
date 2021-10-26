package br.com.delove.controller;

import br.com.delove.model.Pet;
import br.com.delove.model.Usuario;
import br.com.delove.service.PetService;
import br.com.delove.service.UsuarioService;
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

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Pet>> listarPetsPorDisponibilidade(){
        List<Pet> listaPorDisponivel = petService.findPetByDisponibilidade(true);
        return new ResponseEntity<>(listaPorDisponivel, HttpStatus.OK);
    }

//    @GetMapping("/especie/{especie}")
//    public ResponseEntity<List<Pet>> listarPetsPorEspecie(@PathVariable String especie) {
//        List<Pet> listaPorDisponivel = petService.findPetByDisponibilidade(true);
//        List<Pet> listaPorEspecie = petService.findPetBySpecie(especie);
//        return new ResponseEntity<>(listaPorEspecie, HttpStatus.OK);
//    }


    @GetMapping("/especie/{especie}/{disponivel}")
    public ResponseEntity<List<Pet>> listarPetsPorEspecieEDisponibilidade(@PathVariable String especie, @PathVariable boolean disponivel) {
        List<Pet> listaPorEspecie = petService.listarPetsPorEspecieEDisponibilidade(especie, disponivel);
        return new ResponseEntity<>(listaPorEspecie, HttpStatus.OK);
    }

    @GetMapping("/sexo/{sexo}/{disponivel}")
    public ResponseEntity<List<Pet>> listarPetsPorSexoEDisponibilidade(@PathVariable String sexo, @PathVariable boolean disponivel) {
        List<Pet> listaPorSexo = petService.listarPetsPorSexoEDisponibilidade(sexo, disponivel);
        return new ResponseEntity<>(listaPorSexo, HttpStatus.OK);
    }

    @GetMapping("/raca/{raca}/{disponivel}")
    public ResponseEntity<List<Pet>> listarPetsPorRacaEDisponibilidade(@PathVariable String raca, @PathVariable boolean disponivel) {
        List<Pet> listaPorRaca = petService.listarPetsPorRacaEDisponibilidade(raca, disponivel);
        return new ResponseEntity<>(listaPorRaca, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet){
        Pet novoPet = petService.inserirPet(pet);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")

    public ResponseEntity<?> deletarPetPorId(@PathVariable Long id) {
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

   @PutMapping("/{petId}/interessado/{usuarioId}")
    public ResponseEntity<Pet> adicionarUsuarioNaLista(@PathVariable Long usuarioId, @PathVariable Long petId){
        Pet pet = petService.findPetById(petId);
        Usuario usuario = usuarioService.findUsuarioById(usuarioId);
        pet.getFilaInteressados().add(usuario);
        usuario.getPetsInteressados().add(pet);
        petService.atualizarPet(pet);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
   }
}
