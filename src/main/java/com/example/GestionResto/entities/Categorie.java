package com.example.GestionResto.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories")
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCategorie;

    @Column(length = 128, nullable = false)
    private String nomCategorie;

    @Column(length = 256)
    private String description;
    @OneToMany(mappedBy="categorie")
    private List<ProduitRef> produitRefs;


    public Categorie() {
    }

    public Categorie(String nomCategorie, String description, List<ProduitRef> produitRefs) {
        this.nomCategorie = nomCategorie;
        this.description = description;
        this.produitRefs = produitRefs;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProduitRef> getProduitRefs() {
        return produitRefs;
    }

    public void setProduitRefs(List<ProduitRef> produitRefs) {
        this.produitRefs = produitRefs;
    }
}