package com.skytel.prueba.application;

import com.skytel.prueba.domain.Usuario;
import com.skytel.prueba.domain.UsuarioRepository;
import com.skytel.prueba.domain.responses.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    public UsuarioResponse createUser(Usuario user) {
        Usuario usuarioNuevo = usuarioRepository.save(user);
        return new UsuarioResponse(usuarioNuevo.getId(), usuarioNuevo.getNombre(), usuarioNuevo.getApellido(), usuarioNuevo.getTelefono(), usuarioNuevo.getCorreoElectronico(), usuarioNuevo.getToken());
    }

    public Usuario updateUser(Long id, Usuario user) {
        Usuario existingUser = usuarioRepository.findById(id);

        existingUser.setNombre(user.getNombre());
        existingUser.setApellido(user.getApellido());
        existingUser.setTelefono(user.getTelefono());
        existingUser.setCorreoElectronico(user.getCorreoElectronico());

        return usuarioRepository.save(existingUser);
    }

    public void deleteUser(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(email).matches();
    }

    public boolean isEmailAlreadyPicked(String email) {
        return usuarioRepository.isEmailAlreadyTaken(email);
    }
}

