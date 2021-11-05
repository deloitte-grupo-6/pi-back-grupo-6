package br.com.delove.service;

import br.com.delove.model.Usuario;
import br.com.delove.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.getById(id);
    }

    @Transactional
    public List<Usuario> encontrarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
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
}
