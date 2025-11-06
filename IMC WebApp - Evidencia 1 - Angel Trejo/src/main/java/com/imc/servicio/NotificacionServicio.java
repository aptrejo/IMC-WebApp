package com.imc.servicio;

import org.springframework.stereotype.Service;

@Service
public class NotificacionServicio {
    public String notificar(String mensaje){
        // Simulación de envío; en real, correo/SMS/Push
        return "Notificación enviada: " + mensaje;
    }
}
