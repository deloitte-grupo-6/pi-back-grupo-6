package br.com.delove.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.delove.model.Pet;
import br.com.delove.model.Usuario;
import br.com.delove.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.getById(id);
    }

    public List<Usuario> encontrarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario encontrarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario adicionarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario removerPetInteressado(Usuario usuario, Pet pet) {
        for (Pet p : usuario.getPetsInteressados()) {
            if (p.equals(pet)) {
                usuario.getPetsInteressados().remove(p);
                return usuarioRepository.save(usuario);
            }
        }
        return null;
    }
}
