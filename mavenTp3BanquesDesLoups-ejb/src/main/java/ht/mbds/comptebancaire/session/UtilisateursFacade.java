/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.Client;
import ht.mbds.comptebancaire.entities.Utilisateurs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class UtilisateursFacade extends AbstractFacade<Utilisateurs> implements UtilisateursFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_mavenTp3BanquesDesLoups-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
    
    public Utilisateurs find (String email, String password ) {
        System.out.println("JE CHERCHE un Utilisateur email : " + email);
        Query query = em.createNamedQuery("Login.Control");
        query.setParameter("email", email);
        query.setParameter("password", password );
       
        return (Utilisateurs) query.getSingleResult();
        
    }

     public List<Utilisateurs> getAllUtilisateurs(boolean forceRefresh) {
        List<Utilisateurs> liste = findAll();
         if(forceRefresh) {
            for (Utilisateurs utilisateurs: liste) {
                // em.refresh force le rafraichissement des
                // attributs de l'objet en mémoire en fonction
                // des dernières valeurs pour cet objet, dans la base
                // (au plus près du dernier commit)
                em.refresh(utilisateurs);               
            }
        }
        return  liste;
     }
    public UtilisateursFacade() {
        super(Utilisateurs.class);
    }
    
}
