package com.example.websiteproject.repositories;

import com.example.websiteproject.WebsiteProjectApplication;
import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "boutonAjouter", value = "/boutonAjouter")
@Controller
public class BoutonAjouter extends HttpServlet {
    @Override
    @GetMapping("/boutonAjouter")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idP = Integer.parseInt(request.getParameter("idP"));
        Utilisateur u = ModifBDD.getUtilisateur(id);
        Panier p = ModifBDD.getPanier(id);
        ModifBDD.updatePanier(p,ModifBDD.getProduit(idP));
    }
}
