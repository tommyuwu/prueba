package com.skytel.prueba.infrastructure;

import com.skytel.prueba.domain.Usuario;
import com.skytel.prueba.domain.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> findAll() {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    @Override
    public Usuario findById(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        return entityManager.merge(usuario);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        entityManager.remove(entityManager.contains(usuario) ? usuario : entityManager.merge(usuario));
    }

    @Override
    public boolean isEmailAlreadyTaken(String email) {
        try {
            Usuario usuario = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico", Usuario.class)
                    .setParameter("correoElectronico", email)
                    .getSingleResult();
            return usuario != null;
        } catch (NoResultException e) {
            return false;
        }

    }
}

