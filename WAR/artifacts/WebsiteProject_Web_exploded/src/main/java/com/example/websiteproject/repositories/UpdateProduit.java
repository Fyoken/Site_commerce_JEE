package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
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

@WebServlet(name = "updateProduit", value = "/updateProduit")
@Controller
public class UpdateProduit extends HttpServlet {
    @Override
    @PostMapping("/update")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id1 = Integer.parseInt(request.getParameter("id1"));
        double prix = Double.parseDouble(request.getParameter("prix"));
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Produit p where p.id= :id");
        query.setParameter("id",id1);
        Produit p = (Produit) query.list().get(0);
        String ref = p.getReference();
        ModifBDD.updateProduit(ref,prix);
        response.sendRedirect("/accueil");
    }
}