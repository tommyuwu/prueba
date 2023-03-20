package com.skytel.prueba.domain;

import java.util.List;

public interface UsuarioRepository {

    List<Usuario> findAll();

    Usuario findById(Long id);

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(Usuario usuario);

    boolean isEmailAlreadyTaken(String email);
}

