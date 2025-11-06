package com.imc.controller;

import com.imc.modelo.Usuario;
import com.imc.servicio.CifradorSHA;
import com.imc.servicio.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/api/usuarios", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class UsuarioApiController {
    private final UsuarioServicio usuarios;
    public UsuarioApiController(UsuarioServicio usuarios){ this.usuarios = usuarios; }

    @PostMapping(value="/registro", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario registro(@Valid @RequestBody Usuario u){
        u.setContrasenaHash(CifradorSHA.sha256(u.getContrasenaHash()));
        return usuarios.guardar(u);
    }

    @PostMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> login(@RequestBody Map<String,String> body, HttpSession sesion){
        String nombreUsuario = body.get("nombreUsuario");
        String contrasena = body.get("contrasena");
        return usuarios.buscarPorNombreUsuario(nombreUsuario)
                .filter(u -> u.getContrasenaHash().equals(CifradorSHA.sha256(contrasena)))
                .map(u -> { sesion.setAttribute("idUsuario", u.getId()); sesion.setAttribute("nombreUsuario", u.getNombreUsuario()); return Map.of("ok",true,"usuario",u.getNombreUsuario()); })
                .orElse(Map.of("ok",false, "error","Credenciales inválidas."));
    }
}
