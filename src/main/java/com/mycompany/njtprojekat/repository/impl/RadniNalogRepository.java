/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.RadniNalog;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RadniNalogRepository implements MyAppRepository<RadniNalog, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<RadniNalog> findAll() {
        return entityManager.createQuery("Select rn from RadniNalog rn", RadniNalog.class).getResultList();
    }

    @Override
    public RadniNalog findById(Integer id) throws Exception {
        RadniNalog radniNalog = entityManager.find(RadniNalog.class, id);
        if(radniNalog==null){
            throw new Exception("Radni nalog nije pronadjen!");
        }
        return radniNalog;
    }

    @Override
    @Transactional
    public void save(RadniNalog entity) {
        if(entity.getIdRadniNalog()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        RadniNalog radniNalog = entityManager.find(RadniNalog.class, id);
        if(radniNalog!=null){
            entityManager.remove(id);
        }
    }

}
