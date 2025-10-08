/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        return entityManager.createQuery("Select m from Mehanicar m", Mehanicar.class).getResultList();
    }

    @Override
    public Mehanicar findById(Integer id) throws Exception {
        Mehanicar mehanicar = entityManager.find(Mehanicar.class, id);
        if (mehanicar == null) {
            throw new Exception("Mehanicar nije pronadjen!");
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
        List<Mehanicar> list = entityManager.createQuery("Select u from Mehanicar u where u.username=:un", Mehanicar.class)
                .setParameter("un", username).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Transactional
    public boolean existsByUsnerame(String username) {
        Integer c = entityManager.createQuery("Select count(u) from Mehanicar u where u.username=:un", Integer.class)
                .setParameter("un", username).getSingleResult();
        return c > 0;

    }

}
