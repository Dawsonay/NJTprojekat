package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MehanicarRepository implements MyAppRepository<Mehanicar, Integer> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Mehanicar> findAll() {
        return entityManager.createQuery("SELECT m FROM Mehanicar m", Mehanicar.class)
                .getResultList();
    }

    @Override
    public Mehanicar findById(Integer id) throws Exception {
        Mehanicar mehanicar = entityManager.find(Mehanicar.class, id);
        if (mehanicar == null) {
            throw new Exception("Mehaničar nije pronađen!");
        }
        return mehanicar;
    }

    @Override
    @Transactional
    public void save(Mehanicar entity) {
        if (entity.getIdMehanicar() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Mehanicar mehanicar = entityManager.find(Mehanicar.class, id);
        if (mehanicar != null) {
            entityManager.remove(mehanicar);
        }
    }

    @Transactional
    public Mehanicar findByUsername(String username) {
        List<Mehanicar> list = entityManager.createQuery(
                        "SELECT u FROM Mehanicar u WHERE u.username = :un",
                        Mehanicar.class)
                .setParameter("un", username)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Transactional
    public boolean existsByUsername(String username) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM Mehanicar u WHERE u.username = :un",
                        Long.class)
                .setParameter("un", username)
                .getSingleResult();
        return count > 0;
    }
}
