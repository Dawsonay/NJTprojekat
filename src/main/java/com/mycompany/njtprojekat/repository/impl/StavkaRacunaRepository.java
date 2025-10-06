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
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class StavkaRacunaRepository implements MyAppRepository<StavkaRacuna, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<StavkaRacuna> findAll() {
        return entityManager.createQuery("Select sr from StavkaRacuna sr", StavkaRacuna.class).getResultList();
    }

    @Override
    public StavkaRacuna findById(Integer id) throws Exception {
        StavkaRacuna stavkaRacuna = entityManager.find(StavkaRacuna.class, id);
        if(stavkaRacuna==null){
            throw new Exception("Stavka racuna nije pronadjena!");
        }
        return stavkaRacuna;
    }

    @Override
    @Transactional
    public void save(StavkaRacuna entity) {
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
        StavkaRacuna stavkaRacuna = entityManager.find(StavkaRacuna.class, id);
        if(stavkaRacuna!=null){
            entityManager.remove(stavkaRacuna);
        }
    }

}
