package com.carretas.carretas.carreta.controller;

import com.carretas.carretas.carreta.dto.CredenciaisDTO;
import com.carretas.carretas.carreta.dto.TokenDTO;
import com.carretas.carretas.carreta.entity.Usuario;
import com.carretas.carretas.carreta.service.UsuarioService;
import com.carretas.carretas.carreta.util.excecao.ExcecaoExemplo;
import com.carretas.carretas.carreta.util.excecao.SenhaInvalidaException;
import com.carretas.carretas.security.component.JwtService;
import com.carretas.carretas.security.component.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/usuarios")
//@SecurityScheme(
//        name = "Bearer",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer"
//)

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    private final  UserDetailServiceImpl userDetailService;

    private final   JwtService jwtService;

    public UsuarioController(PasswordEncoder passwordEncoder, UserDetailServiceImpl userDetailService, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar( @RequestBody @Valid Usuario usuario ) throws ExcecaoExemplo {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }

//    @PostMapping
//    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) throws ExcecaoExemplo {
//        usuario = usuarioService.salvar(usuario);
//        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
//    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Integer id) {
        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Integer id) {
        usuarioService.excluir(id);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();

            UserDetails usuarioAutenticado = userDetailService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}