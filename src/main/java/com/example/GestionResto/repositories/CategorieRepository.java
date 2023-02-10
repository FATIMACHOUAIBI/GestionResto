package com.example.GestionResto.repositories;


import com.example.GestionResto.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

    List<Categorie> findByNomCategorieContainingIgnoreCase(String keyword);
}