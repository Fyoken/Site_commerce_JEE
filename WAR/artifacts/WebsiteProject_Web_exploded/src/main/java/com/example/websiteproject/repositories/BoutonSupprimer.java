package com.example.websiteproject.repositories;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "boutonSupprimer", value = "/boutonSupprimer")
@Controller
public class BoutonSupprimer extends HttpServlet {
    @Override
    @GetMapping("/boutonSupprimer")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idP = Integer.parseInt(request.getParameter("idP"));
        ModifBDD.removePanier(idP);
        File f = new File("src/main/webapp/WEB-INF/views/panier.jsp");
        if (f.exists()) {
            boolean b = f.delete();
            if (b) {
                f.createNewFile();
            }
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create a <body> element
            Element bodyElement = doc.createElement("body");

            // Create a <p> element
            Element script = doc.createElement("script");
            Element pElement = doc.createElement("p");

            // Set the text content of the <p> element
            pElement.setTextContent("Vide");
            Element a = doc.createElement("a");
            a.setAttribute("href","/accueil");
            a.setTextContent("Continuer vos achats");
            script.setAttribute("src","ajouter.js");
            script.setTextContent("Votre panier :");
            bodyElement.appendChild(script);


            // Append the <p> element to the <body> element
            bodyElement.appendChild(pElement);
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
    }
}