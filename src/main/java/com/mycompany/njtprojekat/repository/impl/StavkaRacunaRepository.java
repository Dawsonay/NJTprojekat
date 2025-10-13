/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class StavkaRacunaRepository implements MyAppRepository<StavkaRacuna, Integer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<StavkaRacuna> findAll() {
        return em.createQuery("SELECT s FROM StavkaRacuna s", StavkaRacuna.class).getResultList();
    }

    @Override
    public StavkaRacuna findById(Integer id) throws Exception {
        StavkaRacuna s = em.find(StavkaRacuna.class, id);
        if (s == null) {
            throw new Exception("Stavka nije pronađena: " + id);
        }
        return s;
    }

    @Override
    public void save(StavkaRacuna entity) {
        if (entity.getRb() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public void deleteById(Integer id) {
        StavkaRacuna s = em.find(StavkaRacuna.class, id);
        if (s != null) {
            em.remove(s);
        }
    }

    public List<StavkaRacuna> findByRacun(Integer idRacun) {
        return em.createQuery("SELECT s FROM StavkaRacuna s WHERE s.idRacun = :id", StavkaRacuna.class)
                .setParameter("id", idRacun)
                .getResultList();
    }

    public void deleteByRacunId(Integer idRacun) {
        em.createQuery("DELETE FROM StavkaRacuna s WHERE s.idRacun = :id")
                .setParameter("id", idRacun)
                .executeUpdate();
    }

    public void saveAll(List<StavkaRacuna> stavke) {
        for (StavkaRacuna s : stavke) {
            if (s.getRb() == null) {
                em.persist(s);
            } else {
                em.merge(s);
            }
        }
    }

    public void delete(StavkaRacuna stara) {
        StavkaRacuna attached = em.contains(stara) ? stara : em.merge(stara);
        em.remove(attached);
    }

    public List<StavkaRacuna> findByIdRacun(Integer id) {
        return em.createQuery("SELECT s FROM StavkaRacuna s WHERE s.idRacun = :id", StavkaRacuna.class)
                .setParameter("id", id)
                .getResultList();
    }

}
