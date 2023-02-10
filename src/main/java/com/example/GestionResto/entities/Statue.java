package com.example.GestionResto.entities;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "statues")
public class Statue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idStatue;

    @Column(length = 128, nullable = false)
    private String nomStatue;
    @OneToMany(mappedBy="statue")
    private List<ProduitStock> produitStocks;

    public Statue() {
    }

    public Statue(int i, String nomStatue) {
        this.nomStatue = nomStatue;
        this.produitStocks = produitStocks;
    }

    public Integer getIdStatue() {
        return idStatue;
    }

    public void setIdStatue(Integer idStatue) {
        this.idStatue = idStatue;
    }

    public String getNomStatue() {
        return nomStatue;
    }

    public void setNomStatue(String nomStatue) {
        this.nomStatue = nomStatue;
    }

    public List<ProduitStock> getProduitStocks() {
        return produitStocks;
    }

    public void setProduitStocks(List<ProduitStock> produitStocks) {
        this.produitStocks = produitStocks;
    }

    @Override
    public String toString() {
        return "Statue{" +
                "idStatue=" + idStatue +
                ", nomStatue='" + nomStatue + '\'' +
                ", produitStocks=" + produitStocks +
                '}';
    }
}

