package com.example.GestionResto.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "produitStock")
public class ProduitStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStock", nullable = false)
    private Integer idStock;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Datetime", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date DateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateOver")
    Date dateOver;

    @ManyToOne
    @JoinColumn(name="idStatue")
    private Statue statue;
    @ManyToOne
    @JoinColumn(name="idProduitRef")
    private ProduitRef produitRef;
    @Column(name = "quantité")
    private Integer quantite;

    public ProduitStock() {
        this.statue = new Statue(154, "in stock");
    }

    public ProduitStock(Date dateTime, Date dateOver, Statue statue, ProduitRef produitRef, Integer quantite) {
        DateTime = dateTime;
        this.dateOver = dateOver;
        this.statue = statue;
        this.produitRef = produitRef;
        this.quantite = quantite;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Date getDateOver() {
        return dateOver;
    }

    public void setDateOver(Date dateOver) {
        this.dateOver = dateOver;
    }

    public Statue getStatue() {
        return statue;
    }

    public void setStatue(Statue statue) {
        this.statue = statue;
    }

    public ProduitRef getProduitRef() {
        return produitRef;
    }

    public void setProduitRef(ProduitRef produitRef) {
        this.produitRef = produitRef;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ProduitStock{" +
                "idStock=" + idStock +
                ", DateTime=" + DateTime +
                ", dateOver=" + dateOver +
                ", statue='" + statue + '\'' +
                ", produitRef=" + produitRef +
                ", quantite=" + quantite +
                '}';
    }
}



