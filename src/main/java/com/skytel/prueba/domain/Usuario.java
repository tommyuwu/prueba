package com.skytel.prueba.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingrese un nombre valido.")
    @Column(name = "nombre", length = 50)
    private String nombre;

    @NotBlank(message = "Ingrese un apellido valido.")
    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "email")
    private String correoElectronico;

    public String getToken() {
        Pattern p = Pattern.compile("[aeiouAEIOU]");
        Matcher m = p.matcher(this.getNombre());
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group().toLowerCase());
        }
        String vocales = sb.toString();
        char primeraLetra = this.getApellido().charAt(0);
        char ultimaLetra = this.getApellido().charAt(this.getApellido().length() - 1);
        Random random = new Random();
        int numero = random.nextInt(900) + 100;
        String token = vocales + primeraLetra + ultimaLetra + String.format("%03d", numero);

        return token.toLowerCase();
    }
}

