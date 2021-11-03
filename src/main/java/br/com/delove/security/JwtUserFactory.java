package br.com.delove.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.delove.model.Usuario;

public class JwtUserFactory {

    public static JwtUser create(Usuario usuario) {
        return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario,
                maptoGrantedAuthrities(new ArrayList<String>(Arrays.asList("ROLE_ADMIN"))), true);
    }

    private static List<? extends GrantedAuthority> maptoGrantedAuthrities(List<String> authorities) {
        return authorities.stream().map(Authority -> new SimpleGrantedAuthority(Authority))
                .collect(Collectors.toList());
    }

}