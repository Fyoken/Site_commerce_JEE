package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import com.example.websiteproject.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "supprimerUser", value = "/supprimerUser")
@Controller
public class SupprimerUtilisateur extends HttpServlet {
    @Override
    @GetMapping("/supprUser")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Utilisateur u where u.id= :id");
        query.setParameter("id",id);
        Utilisateur u = (Utilisateur) query.list().get(0);
        ModifBDD.removeUtilisateur(u.getPseudo());
        response.sendRedirect("localhost:8082/inscription");
    }
}