package com.carretas.carretas.security.component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.carretas.carretas.carreta.entity.Usuario;
import com.carretas.carretas.carreta.repository.UsuarioRepository;
import com.carretas.carretas.carreta.util.excecao.SenhaInvalidaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public UserDetails autenticar( Usuario usuario ){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches( usuario.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(username)
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


//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
//
//        UserDetails user = loadUserByUsernameAux(userName);
//
//        try {
//            //$2a$12$LlxKRPHgE2I41V9o2hcN5ud.4dRUA67QCZhDUbh9C6GevJBoyoPo2
//            UserDetails u = new CustomUser(user.getUsername(),
//                    user.getPassword(),
//                    true,
//                    true,
//                    true,
//                    true,
//                    new ArrayList<>(),
//                    null);
//
//            logger.info("Username: " + userName + " encontrado.");
//
//            return u;
//        } catch (Exception ex) {
//            logger.error("Username: " + userName + " não econtrado na base. Acesso negado. ");
//            throw new UsernameNotFoundException(userName);
//        }
//
////        try {
////            //$2a$12$LlxKRPHgE2I41V9o2hcN5ud.4dRUA67QCZhDUbh9C6GevJBoyoPo2
////            UserDetails u = new CustomUser("cleverson",
////                    "$2a$12$LlxKRPHgE2I41V9o2hcN5ud.4dRUA67QCZhDUbh9C6GevJBoyoPo2",
////                    true,
////                    true,
////                    true,
////                    true,
////                    new ArrayList<>(),
////                    null);
////
////            logger.info("Username: " + userName + " encontrado.");
////
////            return u;
////        } catch (Exception ex) {
////            logger.error("Username: " + userName + " não econtrado na base. Acesso negado. ");
////            throw new UsernameNotFoundException(userName);
////        }
//
//    }

//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Usuario usuario = usuarioRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));
//
//        String[] roles = usuario.isAdmin() ?
//                new String[]{"ADMIN", "USER"} : new String[]{"USER"};
//
//        return User
//                .builder()
//                .username(usuario.getLogin())
//                .password(usuario.getSenha())
//                .roles(roles)
//                .build();
//    }

//    private CustomUser getCustomUser(String userName) {
//
//        logger.info("getCustomUser: " + userName + ".");
//
//        CustomUser customUser = jdbcTemplate.queryForObject(
//                "select email, senha, guidusuario from usuario where email=?", new Object[] { userName },
//                new UserRowMapper());
//
//        if (customUser != null) {
//
//            customUser = new CustomUser(customUser.getUsername(), customUser.getPassword(), customUser.isEnabled(),
//                    customUser.isAccountNonExpired(), customUser.isCredentialsNonExpired(),
//                    customUser.isAccountNonLocked(), getUserRoles(customUser), customUser.getGuidUsuario());
//        }
//
//        return customUser;
//
//    }
//
//    private class UserRowMapper implements RowMapper<CustomUser> {
//        @Override
//        public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new CustomUser(rs.getString("email"), rs.getString("senha"), true, true, true, true,
//                    Collections.emptyList(), rs.getInt("guidusuario"));
//
//        }
//    }
//
//    private List<GrantedAuthority> getUserRoles(CustomUser user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        return authorities;
//    }

}