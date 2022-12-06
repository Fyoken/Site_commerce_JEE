package com.example.websiteproject.repositories;

import com.example.websiteproject.entities.Panier;
import com.example.websiteproject.entities.Produit;
import com.example.websiteproject.entities.Utilisateur;
import com.example.websiteproject.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
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
        Query query = session.createQuery(hql);
        ((org.hibernate.query.Query<?>) query).setString("ref",ref);
        ((org.hibernate.query.Query<?>) query).setString("prix", String.valueOf(prix));
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

    public static void removeUtilisateur(String pseudo)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "DELETE FROM Utilisateur u WHERE u.pseudo= :pseudo";
        Query query = session.createQuery(hql);
        ((org.hibernate.query.Query<?>) query).setString("pseudo",pseudo);
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
        for (int j=0; j<idPaniers.size(); j++) {
            session.createQuery("delete from Utilisateur u where u.panier.id = :idPanier ").setString("idPanier", String.valueOf(idPaniers.get(j))).executeUpdate();
        }
        for (int k=0; k<idPaniers.size(); k++) {
            session.createQuery("delete from Panier p where p.id = :id").setString("id", String.valueOf(k+1)).executeUpdate();
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


    public static Utilisateur getUtilisateur(int id)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Utilisateur> result = session.createQuery("from Utilisateur u where u.id = :id").setString("id", String.valueOf(id)).list();
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
}
