package com.skytel.prueba.application;

import com.skytel.prueba.domain.Usuario;
import com.skytel.prueba.domain.responses.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioService.isValidEmail(usuario.getCorreoElectronico())) {
            return ResponseEntity.badRequest().body("El correo electrónico " + usuario.getCorreoElectronico() + " no es válido.");
        }
        if (usuarioService.isEmailAlreadyPicked(usuario.getCorreoElectronico())) {
            return ResponseEntity.badRequest().body("El correo electrónico " + usuario.getCorreoElectronico() + " ya fue registrado.");
        }
        UsuarioResponse usuarioResponse = usuarioService.createUser(usuario);
        return ResponseEntity.ok(usuarioResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario) {
        if (usuarioService.isValidEmail(usuario.getCorreoElectronico())) {
            return ResponseEntity.badRequest().body("El correo electrónico " + usuario.getCorreoElectronico() + " no es válido");
        }
        Usuario usuarioActualizado = usuarioService.updateUser(id, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Long id) {
        if (usuarioService.obtenerUsuarioPorId(id) == null) {
            return ResponseEntity.badRequest().body("El usuario no existe.");
        }
        usuarioService.deleteUser(usuarioService.obtenerUsuarioPorId(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsers();
        return ResponseEntity.ok(usuarios);
    }
}

