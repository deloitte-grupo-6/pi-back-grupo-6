package br.com.delove.service;

import br.com.delove.model.Usuario;
import br.com.delove.model.UsuarioPF;
import br.com.delove.model.UsuarioPJ;
import br.com.delove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioPF adicionarUsuarioPF(UsuarioPF usuarioPF){
        return usuarioRepository.save(usuarioPF);
    }

    public UsuarioPJ adicionarUsuarioPJ(UsuarioPJ usuarioPJ){
        return usuarioRepository.save(usuarioPJ);
    }
}
