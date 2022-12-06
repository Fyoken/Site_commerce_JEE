package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Categorie;
import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class AjoutBDD {
    public static void ajoutBDD() {

        ModifBDD.addProduit(new Produit("huawei", 223.99, Categorie.TELEPHONE.toString()));
        ModifBDD.addProduit(new Produit("iphone", 2129, Categorie.TELEPHONE.toString()));
        ModifBDD.addProduit(new Produit("pocophone", 399.99, Categorie.TELEPHONE.toString()));
        ModifBDD.addProduit(new Produit("samsung", 459.90, Categorie.TELEPHONE.toString()));

        ModifBDD.addProduit(new Produit("pokemon",59.99,Categorie.JEU.toString()));
        ModifBDD.addProduit(new Produit("gow",79.99,Categorie.JEU.toString()));
        ModifBDD.addProduit(new Produit("cod",62.99,Categorie.JEU.toString()));
        ModifBDD.addProduit(new Produit("mario",20,Categorie.JEU.toString()));

        ModifBDD.addProduit(new Produit("switch",311.99,Categorie.CONSOLE.toString()));
        ModifBDD.addProduit(new Produit("ps5",499,Categorie.CONSOLE.toString()));
        ModifBDD.addProduit(new Produit("xbox",299.99,Categorie.CONSOLE.toString()));
        ModifBDD.addProduit(new Produit("3ds",49.99,Categorie.CONSOLE.toString()));

        ModifBDD.addProduit(new Produit("joycon",59.99,Categorie.ACCESSOIRECONSOLE.toString()));
        ModifBDD.addProduit(new Produit("mps5",69.99,Categorie.ACCESSOIRECONSOLE.toString()));
        ModifBDD.addProduit(new Produit("mxbox",64.99,Categorie.ACCESSOIRECONSOLE.toString()));
        ModifBDD.addProduit(new Produit("joystick",29.99,Categorie.ACCESSOIRECONSOLE.toString()));

        ModifBDD.addProduit(new Produit("hp",999.99,Categorie.ORDINATEUR.toString()));
        ModifBDD.addProduit(new Produit("lenovo",1299.99,Categorie.ORDINATEUR.toString()));
        ModifBDD.addProduit(new Produit("alienware",1149.99,Categorie.ORDINATEUR.toString()));
        ModifBDD.addProduit(new Produit("rog",3199.99,Categorie.ORDINATEUR.toString()));

        ModifBDD.addProduit(new Produit("souris",109.99,Categorie.ACCESSOIREPC.toString()));
        ModifBDD.addProduit(new Produit("clavier",149.99,Categorie.ACCESSOIREPC.toString()));
        ModifBDD.addProduit(new Produit("triton",41.99,Categorie.ACCESSOIREPC.toString()));
        ModifBDD.addProduit(new Produit("tapis",137.99,Categorie.ACCESSOIREPC.toString()));

        /*
        Utilisateur u = null;
        List<Produit> l = new ArrayList<>();
        l.add(p);
        l.add(p2);
        Panier pa = new Panier(u, l);
        ModifBDD.addPanier(pa);

        u = new Utilisateur("Toto", pa, "Toto Titi");
        ModifBDD.addUtilisateur(u);

        ModifBDD.updateProduit("iPhone X",598);

*/
        //ModifBDD.removeUtilisateur("Toto");
        //ModifBDD.removeProduit(p);

    }
}
