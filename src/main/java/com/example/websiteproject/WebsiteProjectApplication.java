package com.example.websiteproject;

import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import com.example.websiteproject.repositories.AjoutBDD;
import com.example.websiteproject.repositories.ModifBDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class WebsiteProjectApplication {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(webApplicationContext);
        templateResolver.setOrder(9);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix("");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine springTemplateEngine= new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(thymeleafTemplateResolver());
        springTemplateEngine.setEnableSpringELCompiler(true);
        return springTemplateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setViewNames(new String[] {"*.html"});
        viewResolver.setExcludedViewNames(new String[] {"*.jsp"});
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(10);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix("");
        viewResolver.setViewNames("*.jsp");
        return viewResolver;
    }


    public static String toStringProd(List<Produit> lp) {
        String res = "Produits : [";
        for (int i=0; i < lp.size()-1;i++) {
            res += lp.get(i).getReference()+" ; ";
        }
        res += lp.get(lp.size()-1).getReference();
        return res+"]";
    }

    public static String toStringUtil(List<Utilisateur> lu) {
        if (lu.size()<1) {
            return "";
        }
        String res = "Utilisateurs : [";
        for (int i=0; i < lu.size()-1;i++) {
            res += lu.get(i).getPseudo()+" ; ";
        }
        res += lu.get(lu.size()-1).getPseudo();
        return res+"]";
    }

    public static String toStringS(List<String> l, int id) {
        String res = "Panier "+id+" :";
        if (l.size()<1) {
            return res;
        }
        res += "[";
        for (int i=0; i < l.size()-1;i++) {
            res += l.get(i)+" ; ";
        }
        res += l.get(l.size()-1);
        return res+"]";
    }

    public static void main(String[] args) throws Exception {
        AjoutBDD.ajoutBDD();
        try {
            File f = new File("src/main/webapp/WEB-INF/views/panier.jsp");
            if (f.exists()) {
                f.delete();
                f.createNewFile();

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                // Create a <body> element
                Element bodyElement = doc.createElement("body");

                // Create a <p> element
                Element script = doc.createElement("script");
                Element pElement = doc.createElement("p");

                // Set the text content of the <p> element
                pElement.setTextContent("Veuillez vous connecter");
                script.setAttribute("src", "ajouter.js");
                script.setTextContent("Votre panier :");
                bodyElement.appendChild(script);


                // Append the <p> element to the <body> element
                bodyElement.appendChild(pElement);

                Element a = doc.createElement("a");
                a.setAttribute("href", "/connexion");
                a.setTextContent("Connexion");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
