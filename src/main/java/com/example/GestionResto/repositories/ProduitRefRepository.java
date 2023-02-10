package com.example.GestionResto.repositories;


import com.example.GestionResto.entities.ProduitRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface ProduitRefRepository extends JpaRepository<ProduitRef, Integer> {
    List<ProduitRef> findProduitRefByNomProduitRefContainingIgnoreCase(String keyword);
}