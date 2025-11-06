package com.imc.controller;

import com.imc.exception.BadRequestException;
import com.imc.exception.NotFoundException;
import com.imc.modelo.Medicion;
import com.imc.modelo.Usuario;
import com.imc.repository.UsuarioRepository;
import com.imc.servicio.MedicionServicio;
import com.imc.servicio.UtilIMC;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/mediciones", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ImcApiController {
    private final MedicionServicio mediciones;
    private final UsuarioRepository usuarios;
    public ImcApiController(MedicionServicio mediciones, UsuarioRepository usuarios){
        this.mediciones=mediciones; this.usuarios=usuarios;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Medicion crear(@RequestParam("masaKg") double masaKg, HttpSession sesion){
        Long idUsuario = (Long) sesion.getAttribute("idUsuario");
        if (idUsuario == null) throw new BadRequestException("Debe iniciar sesión.");
        if (masaKg <= 0) throw new BadRequestException("La masa corporal debe ser mayor a 0.");
        Usuario u = usuarios.findById(idUsuario).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        double imc = UtilIMC.calcularIMC(masaKg, u.getEstaturaM());
        Medicion m = new Medicion();
        m.setUsuario(u); m.setMasaKg(masaKg); m.setImc(imc);
        return mediciones.guardar(m);
    }

    @GetMapping("/mias")
    public List<Medicion> mias(HttpSession sesion){
        Long idUsuario = (Long) sesion.getAttribute("idUsuario");
        if (idUsuario == null) throw new BadRequestException("Debe iniciar sesión.");
        Usuario u = usuarios.findById(idUsuario).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return mediciones.historial(u);
    }
}
