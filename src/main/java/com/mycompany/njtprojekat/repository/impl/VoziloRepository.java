/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class VoziloRepository implements MyAppRepository<Vozilo, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Vozilo> findAll() {
        return entityManager.createQuery("Select v from Vozilo v", Vozilo.class).getResultList();
    }

    @Override
    public Vozilo findById(Integer id) throws Exception {
        Vozilo vozilo = entityManager.find(Vozilo.class, id);
        if(vozilo==null){
            throw new Exception("Vozilo nije pronadjena!");
        }
        return vozilo;
    }

    @Override
    @Transactional
    public void save(Vozilo entity) {
        if(entity.getIdVozilo()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Vozilo vozilo = entityManager.find(Vozilo.class, id);
        if(vozilo!=null){
            entityManager.remove(vozilo);
        }
    }

}
