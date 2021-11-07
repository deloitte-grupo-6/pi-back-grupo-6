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
    public ResponseEntity<List<PetSummaryModel>> listarPetsPorDisponibilidade() {
        List<Pet> listaPorDisponivel = petService.findPetByDisponibilidade(true);
        return new ResponseEntity<>(petAssembler.toCollectionModel(listaPorDisponivel), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PetSummaryModel> encontrarPorId(@PathVariable Long id) {
        Pet pet = petService.findPetById(id);
        // return new ResponseEntity<>(pet, HttpStatus.OK);
        return new ResponseEntity<>(petAssembler.toModel(pet), HttpStatus.OK);
    }


    @GetMapping("/especie/{especie}/{disponivel}")
    public ResponseEntity<List<PetSummaryModel>> listarPetsPorEspecieEDisponibilidade(@PathVariable String especie,
            @PathVariable boolean disponivel) {
        List<Pet> listaPorEspecie = petService.listarPetsPorEspecieEDisponibilidade(especie, disponivel);
        return new ResponseEntity<>(petAssembler.toCollectionModel(listaPorEspecie), HttpStatus.OK);
    }

    @GetMapping("/sexo/{sexo}/{disponivel}")
    public ResponseEntity<List<PetSummaryModel>> listarPetsPorSexoEDisponibilidade(@PathVariable String sexo,
            @PathVariable boolean disponivel) {
        List<Pet> listaPorSexo = petService.listarPetsPorSexoEDisponibilidade(sexo, disponivel);
        return new ResponseEntity<>(petAssembler.toCollectionModel(listaPorSexo), HttpStatus.OK);
    }

    @GetMapping("/raca/{raca}/{disponivel}")
    public ResponseEntity<List<PetSummaryModel>> listarPetsPorRacaEDisponibilidade(@PathVariable String raca,
            @PathVariable boolean disponivel) {
        List<Pet> listaPorRaca = petService.listarPetsPorRacaEDisponibilidade(raca, disponivel);
        return new ResponseEntity<>(petAssembler.toCollectionModel(listaPorRaca), HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PetSummaryModel> cadastrarPet(@RequestBody Pet pet) {
        Pet novoPet = petService.inserirPet(pet);
        return new ResponseEntity<>(petAssembler.toModel(novoPet), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<PetSummaryModel> editarPet(@RequestBody Pet pet) {
        Pet petAtualizado = petService.atualizarPet(pet);
        return new ResponseEntity<>(petAssembler.toModel(petAtualizado), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPetById(@PathVariable Long id) {
        if (!petService.petExistePorId(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        petService.deletarPet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/doar/{id}")
    public ResponseEntity<PetSummaryModel> doarPet(@PathVariable Long id) {
        if (!petService.petExistePorId(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Pet pet = petService.findPetById(id);
        petService.doacaoConcluida(pet);
        return new ResponseEntity<>(petAssembler.toModel(pet), HttpStatus.CREATED);
    }

    @PutMapping("/{petId}/interessado/{usuarioId}")
    public ResponseEntity<PetSummaryModel> adicionarUsuarioNaLista(@PathVariable Long usuarioId,
            @PathVariable Long petId) {
        Pet pet = petService.findPetById(petId);
        Usuario usuario = usuarioService.findUsuarioById(usuarioId);
        // pet.getFilaInteressados().add(usuario);
        pet.getListaInteressados().add(usuario);
        usuario.getPetsInteressados().add(pet);
        petService.atualizarPet(pet);
        return new ResponseEntity<>(petAssembler.toModel(pet), HttpStatus.CREATED);
    }
}
