package com.example.websiteproject.repositories;

import com.example.websiteproject.WebsiteProjectApplication;
import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.PanierProduit;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import com.example.websiteproject.utils.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModifBDD {
    public static void addUtilisateur(Utilisateur u)
    {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(u);
        session.getTransaction().commit();
        session.close();
    }

    public static void addProduit(Produit p)
    {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateProduit(String ref, double prix)
    {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = " update Produit p set p.prix= :prix where p.reference= :ref";
        org.hibernate.query.Query<?> query = session.createQuery(hql);
        query.setString("ref",ref);
        query.setString("prix", String.valueOf(prix));
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void addPanier(Panier p)
    {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    public static void updatePanier(Panier p1, Produit p2) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        PanierProduit p = new PanierProduit(p1.getId(), p2.getId());
        session.save(p);
        session.getTransaction().commit();
        session.close();
        List<String> ls=ModifBDD.getListProduitsPanier(p1.getId());
        String panier = WebsiteProjectApplication.toStringS(ls, p1.getId());
        double prix=0.0;
        List<Produit> lp = ModifBDD.getProduitsPanier(p1.getId());
        for (Produit produit:lp) {
            prix+=produit.getPrix();
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
            pElement.setTextContent(panier+" coût total : "+prix+"€");
            script.setAttribute("src","ajouter.js");
            script.setTextContent("Votre panier :");
            bodyElement.appendChild(script);


            // Append the <p> element to the <body> element
            bodyElement.appendChild(pElement);
            Element a = doc.createElement("a");
            a.setAttribute("href","/accueil");
            a.setTextContent("Continuer vos achats");

            Element buttonElement = doc.createElement("button");
            buttonElement.setAttribute("onclick","suppr("+p1.getId()+");");
            buttonElement.setTextContent("Vider le panier");

            Element buttonElement2 = doc.createElement("a");
            buttonElement2.setAttribute("href","/panier");
            buttonElement2.setTextContent("Actualiser le panier");
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

            File f = new File("src/main/webapp/WEB-INF/views/panier.jsp");
            if (f.exists()) {
                boolean b = f.delete();
                if (b) {
                    f.createNewFile();
                }
            }
            // Create a StreamResult object for the output file
            StreamResult res = new StreamResult(f);

            // Transform the DOMSource object to the StreamResult object
            transformer.transform(source, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removePanier(int id)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "DELETE FROM PanierProduit p WHERE p.paniersId= :id";
        org.hibernate.query.Query<?> query = session.createQuery(hql);
        query.setParameter("id",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void removeUtilisateur(String pseudo)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "DELETE FROM Utilisateur u WHERE u.pseudo= :pseudo";
        org.hibernate.query.Query<?> query = session.createQuery(hql);
        query.setString("pseudo",pseudo);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void removeProduit(Produit p)
    {

        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Panier> result = session.createQuery("from Panier").list();
        List<Integer> idPaniers = new ArrayList<>();
        for (int i = 0; i<result.size(); i++) {
            List<Produit> prod = result.get(i).getProduits();
            for (int j=0; j<prod.size(); j++) {
                if (prod.get(j).getId()==p.getId()) {
                    idPaniers.add(i+1);
                }
            }
        }
        for (int k=0; k<idPaniers.size(); k++) {
            List<Panier> lp = session.createQuery("from Panier p where p.id=:k").setString("k", String.valueOf(idPaniers.get(k))).list();
            if (!lp.isEmpty()) {
                List<Produit> prod = lp.get(0).getProduits();
                prod.remove(k);
                lp.get(0).setProduits(prod);
            }

        }
        session.getTransaction().commit();
        session.close();
        Session session2= HibernateUtil.getSessionFactory().openSession();
        session2.beginTransaction();
        String hql = "DELETE FROM Produit p WHERE p.reference= :ref";
        Query query = session2.createQuery(hql);
        ((org.hibernate.query.Query<?>) query).setString("ref",p.getReference());
        query.executeUpdate();
        session2.getTransaction().commit();
        session2.close();
    }

    public static List<Utilisateur> getListUtilisateur()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Utilisateur> result = session.createQuery("from Utilisateur").list();
        List<String> noms = new ArrayList<>();
        for (Utilisateur u:result) {
            noms.add(u.getPseudo());
        }
        session.close();
        return result;
    }

    public static List<Produit> getListProduits()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("from Produit").list();
        List<String> noms = new ArrayList<>();
        for (Produit p:result) {
            noms.add(p.getReference());
        }
        session.close();
        return result;
    }

    public static List<String> getListProduitsPanier(int idP)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Integer> p = session.createQuery(" select produitsId from PanierProduit p where p.paniersId = :id").setParameter("id", idP).list();
        List<Produit> prod = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            prod.add((Produit) session.createQuery(" from Produit p where p.id = :id").setParameter("id",p.get(i)).list().get(0));
        }
        List<String> noms = new ArrayList<>();
        for (Produit pro:prod) {
            noms.add(pro.getReference());
        }
        session.close();
        return noms;
    }

    public static List<Produit> getProduitsPanier(int idP)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Integer> p = session.createQuery(" select produitsId from PanierProduit p where p.paniersId = :id").setParameter("id", idP).list();
        List<Produit> prod = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            prod.add((Produit) session.createQuery(" from Produit p where p.id = :id").setParameter("id",p.get(i)).list().get(0));
        }
        return prod;
    }

    public static Utilisateur getUtilisateur(int id)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Utilisateur> result = session.createQuery("from Utilisateur u where u.id = :id").setParameter("id", id).list();
        session.close();

        Utilisateur utilisateur = null;

        if (!result.isEmpty()) {
            utilisateur = result.get(0);
        }

        return utilisateur;
    }

    public static Produit getProduit(String ref)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("from Produit p where p.reference = :ref").setString("ref",ref).list();
        session.close();

        Produit produit = null;

        if (!result.isEmpty()) {
            produit = result.get(0);
        }

        return produit;
    }

    public static Produit getProduit(int id)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("from Produit p where p.id = :id").setParameter("id",id).list();
        session.close();

        Produit produit = null;

        if (!result.isEmpty()) {
            produit = result.get(0);
        }

        return produit;
    }

    public static Panier getPanier(int id)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Panier> result = session.createQuery("from Panier p where p.id = :id").setParameter("id",id).list();
        session.close();

        Panier panier = null;

        if (!result.isEmpty()) {
            panier = result.get(0);
        }

        return panier;
    }
}
