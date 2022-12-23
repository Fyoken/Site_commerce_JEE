package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignServlet", value = "/SignServlet")
@Controller
public class SignServlet extends HttpServlet {
    @Override
    @PostMapping("/sign")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        Utilisateur u = new Utilisateur(username,password,new Panier(),fullname);
        ModifBDD.addUtilisateur(u);
        response.sendRedirect("/connexion");
    }
}
