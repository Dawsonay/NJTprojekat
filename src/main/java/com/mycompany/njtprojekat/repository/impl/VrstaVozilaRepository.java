/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.VrstaVozila;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class VrstaVozilaRepository implements MyAppRepository<VrstaVozila, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<VrstaVozila> findAll() {
        return entityManager.createQuery("Select vv from VrstaVozila vv", VrstaVozila.class).getResultList();
    }

    @Override
    public VrstaVozila findById(Integer id) throws Exception {
        VrstaVozila vrstaVozila = entityManager.find(VrstaVozila.class, id);
        if(vrstaVozila==null){
            throw new Exception("Vrsta vozila nije pronadjena!");
        }
        return vrstaVozila;
    }

    @Override
    @Transactional
    public void save(VrstaVozila entity) {
        if(entity.getIdVrsta()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        VrstaVozila vrstaVozila = entityManager.find(VrstaVozila.class, id);
        if(vrstaVozila!=null){
            entityManager.remove(id);
        }
    }

}
