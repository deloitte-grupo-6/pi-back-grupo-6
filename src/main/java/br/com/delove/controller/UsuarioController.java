package br.com.delove.controller;

import br.com.delove.model.Usuario;
import br.com.delove.model.UsuarioPF;
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

    @GetMapping
    public ResponseEntity<?> listar(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioPF> cadastrarUsuarioPF(@RequestBody UsuarioPF usuarioPF){
        UsuarioPF novoUsuarioPF = usuarioService.adicionarUsuarioPF(usuarioPF);
        return new ResponseEntity<>(novoUsuarioPF, HttpStatus.CREATED);
    }
}
