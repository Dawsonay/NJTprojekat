/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RacunRepository implements MyAppRepository<Racun, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Racun> findAll() {
        return entityManager.createQuery("Select r from Racun r", Racun.class).getResultList();
    }

    @Override
    public Racun findById(Integer id) throws Exception {
        Racun racun = entityManager.find(Racun.class, id);
        if(racun==null){
            throw new Exception("Racun nije pronadjen!");
        }
        return racun;
    }

    @Override
    @Transactional
    public void save(Racun entity) {
        if(entity.getIdRacun()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Racun racun = entityManager.find(Racun.class, id);
        if(racun!=null){
            entityManager.remove(racun);
        }
    }

}
