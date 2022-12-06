package com.example.websiteproject;

import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import com.example.websiteproject.repositories.AjoutBDD;
import com.example.websiteproject.repositories.ModifBDD;
import jdk.jshell.execution.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class WebsiteProjectApplication {

    public static String toStringProd(List<Produit> lp) {
        String res = "Produits : [";
        for (int i=0; i < lp.size()-1;i++) {
            res += lp.get(0).getReference()+" ; ";
        }
        res += lp.get(lp.size()-1).getReference();
        return res+"]";
    }

    public static String toStringUtil(List<Utilisateur> lu) {
        if (lu.size()<1) {
            return "";
        }
        String res = "Utilisateur : [";
        for (int i=0; i < lu.size()-1;i++) {
            res += lu.get(0).getPseudo()+" ; ";
        }
        res += lu.get(lu.size()-1).getPseudo();
        return res+"]";
    }

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(WebsiteProjectApplication.class);
        AjoutBDD.ajoutBDD();
        java.util.List<Utilisateur> lu = ModifBDD.getListUtilisateur();
        List<Produit> lp = ModifBDD.getListProduits();
        Utilisateur u = ModifBDD.getUtilisateur(1);
        if (u!=null) {
            logger.info("Utilisateur : "+u.getPseudo());
        }
        logger.info(toStringProd(lp));
        logger.info(toStringUtil(lu));
        SpringApplication.run(WebsiteProjectApplication.class, args);
        browse();
    }

    public static void browse() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        URI homepage = new URI("http://localhost:8082");
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(homepage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                runtime.exec("xdg-open "+homepage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
