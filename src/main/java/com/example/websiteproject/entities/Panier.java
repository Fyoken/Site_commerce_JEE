package com.example.websiteproject.entities;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Panier")
public class Panier {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(mappedBy = "panier")
    Utilisateur u;

    @OneToMany
    List<Produit> produits;

    public Panier(Utilisateur u, List<Produit> produits) {
        this.u = u;
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

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
