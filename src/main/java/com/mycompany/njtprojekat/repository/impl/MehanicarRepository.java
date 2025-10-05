/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.repository.impl;

import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MehanicarRepository implements MyAppRepository<Mehanicar, Integer>{
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Mehanicar> findAll() {
        return entityManager.createQuery("Select m from Mehanicar m", Mehanicar.class).getResultList();
    }

    @Override
    public Mehanicar findById(Integer id) throws Exception {
        Mehanicar mehanicar = entityManager.find(Mehanicar.class, id);
        if(mehanicar==null){
            throw new Exception("Mehanicar nije pronadjen!");
        }
        return mehanicar;
    }

    @Override
    @Transactional
    public void save(Mehanicar entity) {
        if(entity.getIdMehanicar()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Mehanicar mehanicar = entityManager.find(Mehanicar.class, id);
        if(mehanicar!=null){
            entityManager.remove(mehanicar);
        }
    }

}
