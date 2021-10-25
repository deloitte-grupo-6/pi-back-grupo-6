package br.com.delove.controller;

import br.com.delove.model.Usuario;
import br.com.delove.model.UsuarioPF;
import br.com.delove.model.UsuarioPJ;
import br.com.delove.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

//    @GetMapping
//    public ResponseEntity<?> listar() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/cadastrarpf")
    public ResponseEntity<UsuarioPF> cadastrarUsuarioPF(@RequestBody UsuarioPF usuarioPF) {
        UsuarioPF novoUsuarioPF = usuarioService.adicionarUsuarioPF(usuarioPF);
        return new ResponseEntity<>(novoUsuarioPF, HttpStatus.CREATED);
    }

    @PostMapping("/cadastrarpj")
    public ResponseEntity<UsuarioPJ> cadastrarUsuarioPJ(@RequestBody UsuarioPJ usuarioPJ) {
        UsuarioPJ novoUsuarioPJ = usuarioService.adicionarUsuarioPJ(usuarioPJ);
        return new ResponseEntity<>(novoUsuarioPJ, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarpf")
    public ResponseEntity<UsuarioPF> atualizarUsuarioPF(@RequestBody UsuarioPF usuarioPF) {
        UsuarioPF novoUsuarioPF = usuarioService.adicionarUsuarioPF(usuarioPF);
        return new ResponseEntity<>(novoUsuarioPF, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarpj")
    public ResponseEntity<UsuarioPJ> atualizarUsuarioPJ(@RequestBody UsuarioPJ usuarioPJ) {
        UsuarioPJ novoUsuarioPJ = usuarioService.adicionarUsuarioPJ(usuarioPJ);
        return new ResponseEntity<>(novoUsuarioPJ, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
