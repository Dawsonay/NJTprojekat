package com.mycompany.njtprojekat.repository.impl;


import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RacunRepository implements MyAppRepository<Racun, Integer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Racun> findAll() {
        return em.createQuery("SELECT r FROM Racun r", Racun.class).getResultList();
    }

    @Override
    public Racun findById(Integer id) throws Exception {
        Racun r = em.find(Racun.class, id);
        if (r == null) throw new Exception("Racun nije pronađen: " + id);
        return r;
    }

    @Override
    @Transactional
    public void save(Racun entity) {
        if (entity.getIdRacun() == null) em.persist(entity);
        else em.merge(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Racun r = em.find(Racun.class, id);
        if (r != null) em.remove(r);
    }
}