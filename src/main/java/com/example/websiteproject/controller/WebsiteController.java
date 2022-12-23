package com.example.websiteproject.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class WebsiteController {
    @GetMapping("/")
    public String index() {
        return "index.jsp";
    }

    @GetMapping("/ordinateur")
    public String ordi() {
        return "ordinateur.jsp";
    }

    @GetMapping("/telephone")
    public String tele() {
        return "telephone.jsp";
    }

    @GetMapping("/accessoireP")
    public String ap() {
        return "accessoireP.jsp";
    }

    @GetMapping("/accessoireC")
    public String ac() {
        return "accessoireC.jsp";
    }

    @GetMapping("/jeux")
    public String jeu() {
        return "jeux.jsp";
    }

    @GetMapping("/console")
    public String conso() {
        return "console.jsp";
    }

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil.jsp";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "connexion.jsp";
    }

    @GetMapping("/inscription")
    public String inscription() {
        return "inscription.jsp";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin.jsp";
    }

    @GetMapping("/supprimer")
    public String supprimer() {
        return "supprimer.jsp";
    }

    @GetMapping("/panier")
    public ResponseEntity<String> getFile() {
        // Read the contents of the file
        String contents = readFile("src/main/webapp/WEB-INF/views/panier.jsp");

        // Set the HTTP headers in the response
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setExpires(0);

        // Return the file contents as the response
        return ResponseEntity.ok().headers(headers).body(contents);
    }

    public static String readFile(String fileName) {
        try {
            // Create a BufferedReader for the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Read the file line by line and append the lines to a StringBuilder
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }

            // Close the reader and return the contents of the file as a String
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
