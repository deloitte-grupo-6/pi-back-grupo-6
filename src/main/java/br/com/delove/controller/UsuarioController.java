package br.com.delove.controller;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delove.model.Pet;
import br.com.delove.model.Usuario;
import br.com.delove.security.JwtTokenUtil;
import br.com.delove.security.JwtUser;
import br.com.delove.service.PetService;
import br.com.delove.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PetService petService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        return new ResponseEntity<>(usuarioService.encontrarTodosUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.findUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.adicionarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.adicionarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResp> login(@RequestBody LoginDto login, HttpServletRequest request,
            HttpServletResponse response) {
        try {

            Usuario usuario = usuarioService.encontrarUsuarioPorEmail(login.getEmail());
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                    login.getEmail(), login.getSenha());
            Authentication authentication = authenticationManager.authenticate(loginToken);
            final JwtUser userDatails = (JwtUser) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = "Bearer " + jwtTokenUtil.generateToken(userDatails);
            response.setHeader("Authorization", token);

            LoginResp loginResp = new LoginResp(usuario.getId(), usuario.getEmail(), token);

            return new ResponseEntity<>(loginResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String mensagemErro = "Falha ao autenticar o usu??rio com email " + login.getEmail();
            Logger.getLogger(UsuarioController.class.getSimpleName()).log(Level.WARNING, mensagemErro);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{usuarioId}/pets-interessados")
    public ResponseEntity<Set<Pet>> pegarPetsInteressados(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findUsuarioById(usuarioId);
        return new ResponseEntity<>(usuario.getPetsInteressados(), HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}/pets-em-doacao")
    public ResponseEntity<Set<Pet>> pegarPetsEmDoacao(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findUsuarioById(usuarioId);
        return new ResponseEntity<>(usuario.getPetsEmDoacao(), HttpStatus.OK);
    }

    @PutMapping("/{usuarioId}/remover-pet-interessado/{petId}")
    public ResponseEntity<Set<Pet>> removerPetInteressado(@PathVariable Long usuarioId, @PathVariable Long petId) {
        Usuario usuario = usuarioService.findUsuarioById(usuarioId);
        Pet pet = petService.findPetById(petId);

        Usuario novoUsuario = usuarioService.removerPetInteressado(usuario, pet);

        return new ResponseEntity<>(novoUsuario.getPetsInteressados(), HttpStatus.OK);
    }
}

class LoginDto {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

class LoginResp {
    private Long id;
    private String email;
    private String token;

    public LoginResp(Long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
