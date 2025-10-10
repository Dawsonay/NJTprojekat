/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.StavkaRadnogNaloga;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StavkaRadnogNalogaRepository implements MyAppRepository<StavkaRadnogNaloga, Integer>{
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<StavkaRadnogNaloga> findAll() {
        return em.createQuery("SELECT s FROM StavkaRadnogNaloga s", StavkaRadnogNaloga.class).getResultList();
    }

    @Override
    public StavkaRadnogNaloga findById(Integer id) throws Exception {
        StavkaRadnogNaloga s = em.find(StavkaRadnogNaloga.class, id);
        if (s == null) {
            throw new Exception("StavkaRadnogNaloga nije pronađena: " + id);
        }
        return s;
    }

    @Override
    public void save(StavkaRadnogNaloga entity) {
        if (entity.getRb() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public void deleteById(Integer id) {
        StavkaRadnogNaloga s = em.find(StavkaRadnogNaloga.class, id);
        if (s != null) {
            em.remove(s);
        }
    }
    
    // dodatne metode koje servisu trebaju:
    public List<StavkaRadnogNaloga> findByRadniNalog(Integer id) {
        return em.createQuery("SELECT s FROM StavkaRadnogNaloga s WHERE s.idRadniNalog = :id", StavkaRadnogNaloga.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void deleteByRadniNalogId(Integer id) {
        em.createQuery("DELETE FROM StavkaRadnogNaloga s WHERE s.idRadniNalog = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void saveAll(List<StavkaRadnogNaloga> stavke) {
        for (StavkaRadnogNaloga s : stavke) {
            if (s.getRb() == null) {
                em.persist(s);
            } else {
                em.merge(s);
            }
        }
    }

    public void delete(StavkaRadnogNaloga stara) {
        StavkaRadnogNaloga attached = em.contains(stara) ? stara : em.merge(stara);
        em.remove(attached);
    }

    public List<StavkaRadnogNaloga> findByIdRadniNalog(Integer id) {
        return em.createQuery("SELECT s FROM StavkaRadnogNaloga s WHERE s.idRadniNalog = :id", StavkaRadnogNaloga.class)
                .setParameter("id", id)
                .getResultList();
    }

}
