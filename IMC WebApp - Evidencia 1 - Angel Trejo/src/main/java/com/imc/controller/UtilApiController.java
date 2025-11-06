package com.imc.controller;

import com.imc.exception.BadRequestException;
import com.imc.servicio.ArchivoServicio;
import com.imc.servicio.NotificacionServicio;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value="/api/util", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class UtilApiController {
    private final ArchivoServicio archivos;
    private final NotificacionServicio notificaciones;
    public UtilApiController(ArchivoServicio archivos, NotificacionServicio notificaciones){
        this.archivos=archivos; this.notificaciones=notificaciones;
    }

    @GetMapping("/directorio")
    public Map<String,String> listar(@RequestParam String path) throws IOException {
        return Map.of("ruta", path, "contenido", archivos.listarDirectorio(path));
    }

    @PostMapping(value="/archivo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String,String> crear(@RequestParam String ruta, @RequestParam(required=false) String contenido) throws IOException {
        if (ruta==null || ruta.isBlank()) throw new BadRequestException("Ruta requerida");
        String abs = archivos.crearArchivo(ruta, contenido);
        return Map.of("ruta", abs, "ok", "true");
    }

    @PostMapping(value="/notify", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String,String> notify(@RequestParam String mensaje){
        return Map.of("resultado", notificaciones.notificar(mensaje));
    }
}
