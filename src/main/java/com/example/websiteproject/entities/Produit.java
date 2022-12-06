package com.example.websiteproject.entities;

import com.example.websiteproject.repositories.ModifBDD;

import javax.persistence.*;

@Entity
@Table(name="Produit")
public class Produit {
    @Column(name="reference")
    String reference;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="categorie")
    String categorie;

    @Column(name="prix")
    double prix;

    public Produit(String reference, double p, String C) {
        this.reference = reference;
        this.prix = p;
        this.categorie = C;
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
