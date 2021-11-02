package br.com.delove.service;

import br.com.delove.model.Usuario;
import br.com.delove.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

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
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
