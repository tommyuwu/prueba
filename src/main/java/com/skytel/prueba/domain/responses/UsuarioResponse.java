package com.skytel.prueba.domain.responses;

import com.skytel.prueba.domain.Usuario;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UsuarioResponse extends Usuario {

    private String token;

    public UsuarioResponse(Long id, String nombre, String apellido, String telefono, String correoElectronico, String token) {
        super(id, nombre, apellido, telefono, correoElectronico);
        this.token = token;
    }
}
