package com.example.websiteproject.entities;

import javax.persistence.*;

@Entity
@Table(name="Utilisateur")
public class Utilisateur {
    @Column(name="pseudo")
    String pseudo;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Panier.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "Panier_id", referencedColumnName = "id")
    private Panier panier;

    @Column(name = "fullname")
    String fullname;

    public Utilisateur(String p, Panier panier, String full) {
        this.pseudo = p;
        this.panier = panier;
        this.fullname = full;
    }

    public Utilisateur() {

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public int getId() {
        return id;
    }

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId_panier() {
        return panier.getId(getId());
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
