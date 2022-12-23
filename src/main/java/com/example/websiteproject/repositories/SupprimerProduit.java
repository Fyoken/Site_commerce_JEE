package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import com.example.websiteproject.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "boutonAjouter", value = "/boutonAjouter")
@Controller
public class SupprimerProduit extends HttpServlet {
    @Override
    @PostMapping("/remove")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Produit p where p.id= :id");
        query.setParameter("id",id);
        Produit p = (Produit) query.list().get(0);
        ModifBDD.removeProduit(p);
        response.sendRedirect("∕accueil");
    }
}