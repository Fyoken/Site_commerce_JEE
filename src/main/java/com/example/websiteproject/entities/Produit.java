package com.example.websiteproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Produit")
public class Produit implements Serializable {
    @Column(name="reference")
    String reference;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="categorie")
    Categorie categorie;

    @Column(name = "nomCategorie")
    String name;

    @Column(name="prix")
    double prix;

    @ManyToMany(mappedBy = "produits")
    private List<Panier> paniers;

    public Produit(String reference, double p, Categorie C) {
        this.reference = reference;
        this.prix = p;
        this.categorie = C;
        this.name = C.name();
    }

    public Produit() {

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}
