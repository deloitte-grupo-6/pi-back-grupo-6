package br.com.delove.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.delove.model.Usuario;
import br.com.delove.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.encontrarUsuarioPorEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("Usuário com título %s não encontrado.", email));
        } else {
            return JwtUserFactory.create(usuario);
        }
    }

}