/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.RadniNalog;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RadniNalogRepository implements MyAppRepository<RadniNalog, Integer>{
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<RadniNalog> findAll() {
        return em.createQuery("SELECT r FROM RadniNalog r", RadniNalog.class).getResultList();
    }

    @Override
    public RadniNalog findById(Integer id) throws Exception {
        RadniNalog r = em.find(RadniNalog.class, id);
        if (r == null) throw new Exception("RadniNalog nije pronađen: " + id);
        return r;
    }

    @Override
    @Transactional
    public void save(RadniNalog entity) {
        if (entity.getIdRadniNalog()== null) em.persist(entity);
        else em.merge(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        RadniNalog r = em.find(RadniNalog.class, id);
        if (r != null) em.remove(r);
    }

}
