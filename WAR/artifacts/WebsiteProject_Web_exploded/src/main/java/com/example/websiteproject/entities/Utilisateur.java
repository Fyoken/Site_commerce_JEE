package com.example.websiteproject.entities;

import javax.persistence.*;

@Entity
@Table(name="Utilisateur")
public class Utilisateur {
    @Column(name="pseudo")
    String pseudo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(mappedBy = "u", cascade = {CascadeType.ALL})
    @MapsId
    Panier panier;

    @Column(name = "fullname")
    String fullname;

    @Column(name = "password")
    String mdp;

    public Utilisateur(String p, String mdp, Panier panier, String full) {
        this.pseudo = p;
        this.panier = panier;
        this.fullname = full;
        this.mdp = mdp;
    }

    public Utilisateur(String p,int id, String mdp, Panier panier, String full) {
        this.pseudo = p;
        this.panier = panier;
        this.fullname = full;
        this.id = id;
        this.mdp = mdp;
    }

    public Utilisateur() {

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Panier getPanier () {return panier;}
    public void setPanier(Panier p) {this.panier = p;}
    public int getId() {
        return id;
    }
    public void setId(int id) {this.id=id;}
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
