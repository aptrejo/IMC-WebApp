package com.imc.controller;

import com.imc.modelo.Usuario;
import com.imc.servicio.CifradorSHA;
import com.imc.servicio.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    private final UsuarioServicio usuarios;
    public WebController(UsuarioServicio usuarios){ this.usuarios = usuarios; }

    @GetMapping("/") public String index(){ return "index"; }
    @GetMapping("/registro") public String registro(Model m){ m.addAttribute("usuario", new Usuario()); return "registro"; }
    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("usuario") Usuario u, BindingResult br, HttpSession sesion, Model m){
        if (br.hasErrors()){ return "registro"; }
        if (usuarios.buscarPorNombreUsuario(u.getNombreUsuario()).isPresent()){
            m.addAttribute("error", "El nombre de usuario ya existe."); return "registro";
        }
        u.setContrasenaHash(CifradorSHA.sha256(u.getContrasenaHash()));
        u = usuarios.guardar(u);
        sesion.setAttribute("idUsuario", u.getId());
        sesion.setAttribute("nombreUsuario", u.getNombreUsuario());
        return "redirect:/panel";
    }

    @GetMapping("/iniciar") public String inicio(){ return "iniciar"; }
    @PostMapping("/iniciar")
    public String iniciar(@RequestParam String nombreUsuario, @RequestParam String contrasena, HttpSession sesion, Model m){
        return usuarios.buscarPorNombreUsuario(nombreUsuario)
                .filter(u -> u.getContrasenaHash().equals(CifradorSHA.sha256(contrasena)))
                .map(u -> { sesion.setAttribute("idUsuario", u.getId()); sesion.setAttribute("nombreUsuario", u.getNombreUsuario()); return "redirect:/panel"; })
                .orElseGet(() -> { m.addAttribute("error","Credenciales inválidas."); return "iniciar"; });
    }

    @GetMapping("/salir")
    public String salir(HttpSession sesion){ sesion.invalidate(); return "redirect:/"; }

    @GetMapping("/panel")
    public String panel(HttpSession sesion){
        if (sesion.getAttribute("idUsuario")==null) return "redirect:/iniciar";
        return "panel";
    }
}
