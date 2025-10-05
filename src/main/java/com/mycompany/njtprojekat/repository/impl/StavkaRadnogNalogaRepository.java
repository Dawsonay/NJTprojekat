/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.StavkaRadnogNaloga;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class StavkaRadnogNalogaRepository implements MyAppRepository<StavkaRadnogNaloga, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<StavkaRadnogNaloga> findAll() {
        return entityManager.createQuery("Select srn from StavkaRadnogNaloga srn", StavkaRadnogNaloga.class).getResultList();
    }

    @Override
    public StavkaRadnogNaloga findById(Integer id) throws Exception {
        StavkaRadnogNaloga stavkaRadnogNaloga = entityManager.find(StavkaRadnogNaloga.class, id);
        if(stavkaRadnogNaloga==null){
            throw new Exception("Stavka radnog naloga nije pronadjena!");
        }
        return stavkaRadnogNaloga;
    }

    @Override
    @Transactional
    public void save(StavkaRadnogNaloga entity) {
        if(entity.getRb()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        StavkaRadnogNaloga stavkaRadnogNaloga = entityManager.find(StavkaRadnogNaloga.class, id);
        if(stavkaRadnogNaloga!=null){
            entityManager.remove(id);
        }
    }

}
