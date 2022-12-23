package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Categorie;
import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AjoutBDD {
    public static void ajoutBDD() {

        ModifBDD.addProduit(new Produit("huawei", 223.99, Categorie.TELEPHONE));
        ModifBDD.addProduit(new Produit("iphone", 2129, Categorie.TELEPHONE));
        ModifBDD.addProduit(new Produit("pocophone", 399.99, Categorie.TELEPHONE));
        ModifBDD.addProduit(new Produit("samsung", 459.90, Categorie.TELEPHONE));

        ModifBDD.addProduit(new Produit("pokemon",59.99,Categorie.JEU));
        ModifBDD.addProduit(new Produit("gow",79.99,Categorie.JEU));
        ModifBDD.addProduit(new Produit("cod",62.99,Categorie.JEU));
        ModifBDD.addProduit(new Produit("mario",20,Categorie.JEU));

        ModifBDD.addProduit(new Produit("switch",311.99,Categorie.CONSOLE));
        ModifBDD.addProduit(new Produit("ps5",499,Categorie.CONSOLE));
        ModifBDD.addProduit(new Produit("xbox",299.99,Categorie.CONSOLE));
        ModifBDD.addProduit(new Produit("3ds",49.99,Categorie.CONSOLE));

        ModifBDD.addProduit(new Produit("joycon",59.99,Categorie.ACCESSOIRECONSOLE));
        ModifBDD.addProduit(new Produit("mps5",69.99,Categorie.ACCESSOIRECONSOLE));
        ModifBDD.addProduit(new Produit("mxbox",64.99,Categorie.ACCESSOIRECONSOLE));
        ModifBDD.addProduit(new Produit("joystick",29.99,Categorie.ACCESSOIRECONSOLE));

        ModifBDD.addProduit(new Produit("hp",999.99,Categorie.ORDINATEUR));
        ModifBDD.addProduit(new Produit("lenovo",1299.99,Categorie.ORDINATEUR));
        Produit p3 = new Produit("alienware",1149.99,Categorie.ORDINATEUR);
        ModifBDD.addProduit(p3);
        Produit p1 = new Produit("rog",3199.99,Categorie.ORDINATEUR);
        ModifBDD.addProduit(p1);

        Produit p2 = new Produit("souris",109.99,Categorie.ACCESSOIREPC);
        ModifBDD.addProduit(p2);
        ModifBDD.addProduit(new Produit("clavier",149.99,Categorie.ACCESSOIREPC));
        ModifBDD.addProduit(new Produit("triton",41.99,Categorie.ACCESSOIREPC));
        ModifBDD.addProduit(new Produit("tapis",137.99,Categorie.ACCESSOIREPC));

        List<Produit> lp = new ArrayList<>();
        lp.add(p1);
        ModifBDD.addUtilisateur(new Utilisateur("admin","admin",new Panier(),"admin"));
    }
}
