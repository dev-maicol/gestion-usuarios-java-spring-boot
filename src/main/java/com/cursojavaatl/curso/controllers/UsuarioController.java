package com.cursojavaatl.curso.controllers;

import com.cursojavaatl.curso.dao.UsuarioDao;
import com.cursojavaatl.curso.dao.UsuarioDaoImp;
import com.cursojavaatl.curso.models.Usuario;
import com.cursojavaatl.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("api/prueba")
    public String prueba(){
        return "Pruebaaaaaa";
    }

    @GetMapping("api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario u = new Usuario();
        u.setId(id);
        u.setNombres("Maicol German");
        u.setApellidos("Condori Adrian");
        u.setCelular("61845111");
        u.setEmail("maicol@maicol.com");
        u.setPassword("123456789");
        return u;
    }

    @GetMapping("api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        if (!validarToken(token)){
            return null;
        }

        return usuarioDao.getUsuarios();
//        List<Usuario> lista = new ArrayList<>();
//
//        Usuario u = new Usuario();
//        u.setId(123L);
//        u.setNombres("Maicol German");
//        u.setApellidos("Condori Adrian");
//        u.setCelular("61845111");
//        u.setEmail("maicol@maicol.com");
//        u.setPassword("123456789");
//
//        Usuario u2 = new Usuario();
//        u2.setId(456L);
//        u2.setNombres("Victoria");
//        u2.setApellidos("Mamani LOra");
//        u2.setCelular("68456289");
//        u2.setEmail("vic@vic.com");
//        u2.setPassword("987654321");
//
//        Usuario u3 = new Usuario();
//        u3.setId(789L);
//        u3.setNombres("Blablabla");
//        u3.setApellidos("AAaaaaa BBbbbb");
//        u3.setCelular("11223344");
//        u3.setEmail("aaa@aaa.com");
//        u3.setPassword("11098098");
//
//        lista.add(u);
//        lista.add(u2);
//        lista.add(u3);
//
//        return lista;
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @GetMapping("/usuario111")
    public Usuario editar(){
        Usuario u = new Usuario();
        u.setNombres("Maicol German");
        u.setApellidos("Condori Adrian");
        u.setCelular("61845111");
        u.setEmail("maicol@maicol.com");
        u.setPassword("123456789");
        return u;
    }

    @DeleteMapping("api/usuario/{id}")
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if (!validarToken(token)){
            return;
        }

        usuarioDao.eliminar(id);
//        Usuario u = new Usuario();
//        u.setNombres("Maicol German");
//        u.setApellidos("Condori Adrian");
//        u.setCelular("61845111");
//        u.setEmail("maicol@maicol.com");
//        u.setPassword("123456789");
//        return u;
    }

    @GetMapping("/usuario333")
    public Usuario buscar(){
        Usuario u = new Usuario();
        u.setNombres("Maicol German");
        u.setApellidos("Condori Adrian");
        u.setCelular("61845111");
        u.setEmail("maicol@maicol.com");
        u.setPassword("123456789");
        return u;
    }

    @PostMapping("api/usuarios")
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashPassword = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hashPassword);
        usuarioDao.registrar(usuario);
    }
}
