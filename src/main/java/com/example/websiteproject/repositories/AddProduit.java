package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Categorie;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "addProduit", value = "/addProduit")
@Controller
public class AddProduit extends HttpServlet {
    @Override
    @PostMapping("/add")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ref = request.getParameter("ref");
        String catS = request.getParameter("cat");
        catS = catS.toUpperCase(Locale.ROOT);
        Categorie cat = (Categorie) Categorie.valueOf(catS);
        double prix = Double.parseDouble(request.getParameter("price"));
        Produit p = new Produit(ref, prix, cat);
        ModifBDD.addProduit(p);
        response.sendRedirect("/accueil");
    }
}