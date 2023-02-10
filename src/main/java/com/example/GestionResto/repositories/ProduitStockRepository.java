package com.example.GestionResto.repositories;

import com.example.GestionResto.entities.ProduitStock;
import com.example.GestionResto.entities.Statue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface ProduitStockRepository extends JpaRepository<ProduitStock, Integer> {
    List<ProduitStock>findByProduitRefNomProduitRefLikeIgnoreCase(String keyword);
    List<ProduitStock> findByDateOverLessThanEqual(Date date);

    List<ProduitStock> findByStatue(Statue expiredStatus);
}

