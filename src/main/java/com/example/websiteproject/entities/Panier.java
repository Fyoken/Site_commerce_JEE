package com.example.websiteproject.entities;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Panier")
public class Panier implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(cascade={CascadeType.ALL},fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "id")
    Utilisateur u;

    @ManyToMany
    @JoinColumn(name = "produits_id")
    private List<Produit> produits;

    public Panier(List<Produit> produits) {
        this.produits = produits;
    }

    public Panier() {

    }


    public int getId() {
        return id;
    }

    public static int getId(int id) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur() {
        return u.getId();
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public List<Produit> addProduit(Produit p) {produits.add(p); return produits;}

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
