package com.cursojavaatl.curso.controllers;

import com.cursojavaatl.curso.dao.UsuarioDao;
import com.cursojavaatl.curso.models.Usuario;
import com.cursojavaatl.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("api/login")
    public String iniciarSesion(@RequestBody Usuario usuario){
        Usuario usuarioVerificado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioVerificado != null){
            String tokenJWT = jwtUtil.create(String.valueOf(usuarioVerificado.getId()), usuarioVerificado.getEmail());
            return tokenJWT;
        }else{
            return "FALSE";
        }
    }
}
