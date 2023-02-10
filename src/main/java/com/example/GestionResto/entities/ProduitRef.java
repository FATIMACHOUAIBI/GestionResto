package com.example.GestionResto.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "produitRef")
public class ProduitRef implements Serializable {
    @Column(name = "idProduitRef")
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer idProduitRef;
    @Column(name = "nomProduitReference")
    private String nomProduitRef;
    @Column(name = "dureeProduitReference")
    private long dureeProduitRef;
    @ManyToOne
    @JoinColumn(name="idCategorie")
    private Categorie categorie;
    @OneToMany(mappedBy="produitRef")
    private List<ProduitStock> produitStocks;

    public ProduitRef() {
    }

    public ProduitRef(String nomProduitRef, long dureeProduitRef, Categorie categorie, List<ProduitStock> produitStocks) {
        this.nomProduitRef = nomProduitRef;
        this.dureeProduitRef = dureeProduitRef;
        this.categorie = categorie;
        this.produitStocks = produitStocks;
    }

    public Integer getIdProduitRef() {

        return idProduitRef;
    }

    public void setIdProduitRef(Integer idProduitRef) {

        this.idProduitRef = idProduitRef;
    }

    public String getNomProduitRef() {

        return nomProduitRef;
    }

    public void setNomProduitRef(String nomProduitRef) {

        this.nomProduitRef = nomProduitRef;
    }

    public long getDureeProduitRef() {
        return dureeProduitRef;
    }

    public void setDureeProduitRef(long dureeProduitRef) {
        this.dureeProduitRef = dureeProduitRef;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {

        this.categorie = categorie;
    }

    public List<ProduitStock> getProduitStocks() {

        return produitStocks;
    }

    public void setProduitStocks(List<ProduitStock> produitStocks) {

        this.produitStocks = produitStocks;
    }

    @Override
    public String toString() {
        return "ProduitRef{" +
                "idProduitRef=" + idProduitRef +
                ", nomProduitRef='" + nomProduitRef + '\'' +
                ", dureeProduitRef=" + dureeProduitRef +
                ", categorie=" + categorie +
                ", produitStocks=" + produitStocks +
                '}';
    }
}