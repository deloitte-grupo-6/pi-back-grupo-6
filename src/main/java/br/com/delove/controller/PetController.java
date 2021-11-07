package br.com.delove.controller;

import br.com.delove.model.Pet;
import br.com.delove.model.Usuario;
import br.com.delove.service.PetService;
import br.com.delove.service.UsuarioService;
import br.com.delove.summary.PetAssembler;
import br.com.delove.summary.PetSummaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pets")
@CrossOrigin("*")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PetAssembler petAssembler;

    @GetMapping
    public ResponseEntity<List<Pet>> listarPetsPorDisponibilidade() {
        List<Pet> listaPorDisponivel = petService.findPetByDisponibilidade(true);
        return new ResponseEntity<>(listaPorDisponivel, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pet> encontrarPorId(@PathVariable Long id) {
        Pet pet = petService.findPetById(id);
        // return new ResponseEntity<>(pet, HttpStatus.OK);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping("/especie/{especie}/{disponivel}")
    public ResponseEntity<List<Pet>> listarPetsPorEspecieEDisponibilidade(@PathVariable String especie,
            @PathVariable boolean disponivel) {
        List<Pet> listaPorEspecie = petService.listarPetsPorEspecieEDisponibilidade(especie, disponivel);
        return new ResponseEntity<>(listaPorEspecie, HttpStatus.OK);
        // return new ResponseEntity<>(petAssembler.toCollectionModel(listaPorEspecie),
        // HttpStatus.OK);
    }

    @GetMapping("/sexo/{sexo}/{disponivel}")
    public ResponseEntity<List<Pet>> listarPetsPorSexoEDisponibilidade(@PathVariable String sexo,
            @PathVariable boolean disponivel) {
        List<Pet> listaPorSexo = petService.listarPetsPorSexoEDisponibilidade(sexo, disponivel);
        return new ResponseEntity<>(listaPorSexo, HttpStatus.OK);
    }

    @GetMapping("/raca/{raca}/{disponivel}")
    public ResponseEntity<List<Pet>> listarPetsPorRacaEDisponibilidade(@PathVariable String raca,
            @PathVariable boolean disponivel) {
        List<Pet> listaPorRaca = petService.listarPetsPorRacaEDisponibilidade(raca, disponivel);
        return new ResponseEntity<>(listaPorRaca, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet) {
        Pet novoPet = petService.inserirPet(pet);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<Pet> editarPet(@RequestBody Pet pet) {
        Pet petAtualizado = petService.atualizarPet(pet);
        return new ResponseEntity<>(petAtualizado, HttpStatus.CREATED);
    }

    // @DeleteMapping("/deletar/{id}")
    // public ResponseEntity<?> deletarPetById(@PathVariable Long id) {
    // if (!petService.petExistePorId(id)) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // petService.deletarPet(id);
    // return new ResponseEntity<>(HttpStatus.OK);
    // }

    @PutMapping("/doar/{id}")
    public ResponseEntity<Pet> doarPet(@PathVariable Long id) {
        if (!petService.petExistePorId(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Pet pet = petService.findPetById(id);
        petService.doacaoConcluida(pet);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/{petId}/interessado/{usuarioId}")
    public ResponseEntity<Pet> adicionarUsuarioNaLista(@PathVariable Long usuarioId, @PathVariable Long petId) {
        Pet pet = petService.findPetById(petId);
        Usuario usuario = usuarioService.findUsuarioById(usuarioId);
        // pet.getFilaInteressados().add(usuario);
        pet.getListaInteressados().add(usuario);
        usuario.getPetsInteressados().add(pet);
        petService.atualizarPet(pet);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @GetMapping("/{petId}/usuarios-interessados")
    public ResponseEntity<Set<Usuario>> pegarUsuariosInteressados(@PathVariable Long petId) {
        Pet pet = petService.findPetById(petId);
        return new ResponseEntity<>(pet.getListaInteressados(), HttpStatus.OK);
    }
}
