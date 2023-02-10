package com.example.GestionResto.repositories;


import com.example.GestionResto.entities.Statue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StatueRepository extends JpaRepository<Statue, Integer> {

    List<Statue> findByNomStatueContainingIgnoreCase(String keyword);

    Statue findByNomStatue(String nomStatue);
}