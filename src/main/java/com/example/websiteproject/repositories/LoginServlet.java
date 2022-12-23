package com.example.websiteproject.repositories;
import com.example.websiteproject.WebsiteProjectApplication;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import com.example.websiteproject.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
@Controller
public class LoginServlet extends HttpServlet {

    @Override
    @PostMapping("/login")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Session em = HibernateUtil.getSessionFactory().openSession();
        em.beginTransaction();
        Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.pseudo = :username AND u.mdp = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            Utilisateur user = (Utilisateur) query.getResultList().get(0);
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            File f = new File("src/main/webapp/WEB-INF/views/panier.jsp");
            if (f.exists()) {
                boolean b = f.delete();
                if (b) {
                    f.createNewFile();
                }
            }
            List<String> ls=ModifBDD.getListProduitsPanier(user.getId_panier());
            double prix = 0.0;
            List<Produit> lp1 = ModifBDD.getProduitsPanier(user.getId_panier());
            for (Produit p : lp1) {
                prix+=p.getPrix();
            }
            String panier = WebsiteProjectApplication.toStringS(ls, user.getId_panier());
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                // Create a <body> element
                Element bodyElement = doc.createElement("body");

                // Create a <p> element
                Element script = doc.createElement("script");
                Element pElement = doc.createElement("p");
                pElement.setTextContent(panier+" coût total : "+prix+"€");

                script.setAttribute("src","ajouter.js");
                script.setTextContent("Votre panier :");
                bodyElement.appendChild(script);


                // Append the <p> element to the <body> element
                bodyElement.appendChild(pElement);
                Element buttonElement = doc.createElement("button");
                buttonElement.setAttribute("onclick","suppr("+user.getId_panier()+");");
                buttonElement.setTextContent("Vider le panier");

                Element buttonElement2 = doc.createElement("a");
                buttonElement2.setAttribute("href","/panier");
                buttonElement2.setTextContent("Actualiser le panier");
                Element a = doc.createElement("a");
                a.setAttribute("href","/accueil");
                a.setTextContent("Continuer vos achats");
                bodyElement.appendChild(buttonElement);
                bodyElement.appendChild(buttonElement2);
                bodyElement.appendChild(a);
                // Append the <body> element to the <html> element
                doc.appendChild(bodyElement);

                // Create a TransformerFactory object
                TransformerFactory transformerFactory = TransformerFactory.newInstance();

                // Create a Transformer object
                Transformer transformer = transformerFactory.newTransformer();

                // Set the output properties of the Transformer object
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                // Create a DOMSource object from the Document object
                DOMSource source = new DOMSource(doc);

                // Create a StreamResult object for the output file
                StreamResult res = new StreamResult(f);

                // Transform the DOMSource object to the StreamResult object
                transformer.transform(source, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("accueil");
        } catch (IndexOutOfBoundsException e) {
            request.setAttribute("errorMessage", "Invalid username or password");
            response.sendRedirect("connexion");
        }
        em.close();
    }
}

