/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UslugaRepository implements MyAppRepository<Usluga, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Usluga> findAll() {
        return entityManager.createQuery("Select u from Usluga u", Usluga.class).getResultList();
    }

    @Override
    public Usluga findById(Integer id) throws Exception {
        Usluga usluga = entityManager.find(Usluga.class, id);
        if(usluga==null){
            throw new Exception("Usluga nije pronadjena!");
        }
        return usluga;
    }

    @Override
    @Transactional
    public void save(Usluga entity) {
        if(entity.getIdUsluga()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }
    @Transactional
    public Usluga saveU(Usluga entity) {
        if (entity.getIdUsluga() == null) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Usluga usluga = entityManager.find(Usluga.class, id);
        if(usluga!=null){
            entityManager.remove(usluga);
        }
    }

}
