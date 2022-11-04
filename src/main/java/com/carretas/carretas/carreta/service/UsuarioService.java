package com.carretas.carretas.carreta.service;

import com.carretas.carretas.carreta.entity.Usuario;
import com.carretas.carretas.carreta.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.carretas.carretas.carreta.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) throws ExcecaoExemplo {
        if (usuario.getNome() == null || usuario.getNome().equals("") || usuario.getNome().length() > 300) {
            //lanco um erro
            throw new ExcecaoExemplo("ERR001","O dados dos usuário estão errados manow.");
        }

        return usuarioRepository.save(usuario);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }

}