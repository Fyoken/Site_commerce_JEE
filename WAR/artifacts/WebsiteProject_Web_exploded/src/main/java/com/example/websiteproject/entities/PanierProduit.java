package com.example.websiteproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "Panier_Produit")
public class PanierProduit {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "produits_id")
    int produitsId;

    @Column(name = "paniers_id")
    int paniersId;

    public PanierProduit() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PanierProduit(int paniersId, int produitsId) {
        this.paniersId = paniersId;
        this.produitsId = produitsId;
    }

    public int getProduitsId() {
        return produitsId;
    }

    public void setProduitsId(int produitsId) {
        this.produitsId = produitsId;
    }

    public int getPanierId() {
        return paniersId;
    }

    public void setPanierId(int panierId) {
        this.paniersId = panierId;
    }


}
