package br.com.delove.service;

import br.com.delove.model.Usuario;
import br.com.delove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUsuarioById(Long id) { return usuarioRepository.getById(id); }

    public Usuario adicionarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


//    public UsuarioPF atualizarUsuarioPF(UsuarioPF usuarioPF) {
//        return usuarioRepository.save(usuarioPF);
//    }
//
//    public UsuarioPJ atualizarUsuarioPJ(UsuarioPJ usuarioPJ){
//        return usuarioRepository.save(usuarioPJ);
//    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
