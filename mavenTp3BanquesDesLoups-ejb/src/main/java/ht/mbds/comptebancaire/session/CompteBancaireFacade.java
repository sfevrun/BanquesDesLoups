/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.Client;
import ht.mbds.comptebancaire.entities.CompteBancaire;
import ht.mbds.comptebancaire.entities.TypeCompte;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class CompteBancaireFacade extends AbstractFacade<CompteBancaire> implements CompteBancaireFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_mavenTp3BanquesDesLoups-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<CompteBancaire> getAllCompteBancaires(boolean forceRefresh) {
     //   creerComptesTest();
        
         List<CompteBancaire> liste = findAll() ;
         if(forceRefresh) {
            for (CompteBancaire compteBancaire: liste) {
                // em.refresh force le rafraichissement des
                // attributs de l'objet en mémoire en fonction
                // des dernières valeurs pour cet objet, dans la base
                // (au plus près du dernier commit)
                em.refresh(compteBancaire);               
            }
        }
        
        return liste;
        
    }
  
   /* public void creerCompte(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    
    }*/
          // Cette liste provient du cache de niveau 2 et 1
        // Si les données changent en insert/delete, la liste est à jour
        // Mais pas forcément les updates
       
        
        // Force le refresh des valeurs
        
 public void creercompteTest(){
      SimpleDateFormat sm = new SimpleDateFormat("mm-dd-yyyy");
    // myDate is the java.util.Date in yyyy-mm-dd format
    // Converting it into String using formatter
  
          try {
           
               create(new CompteBancaire(new Client("Dollin","Gregory", sm.parse("11-23-1984"),"01233"),"111111111",2012990.20,"test client1",new TypeCompte("Epargne","Compte epargne")));
               create(new CompteBancaire(new Client("Saul","Fevrun", sm.parse("10-23-1983"),"01234"),"222222222",5609990.20,"test client1",new TypeCompte("Cheque","Compte Cheque")));
               create(new CompteBancaire(new Client("Zephirin","Vladimir", sm.parse("09-26-1986"),"01236"),"333333333",203570.20,"test client1",new TypeCompte("public","Compte conjoint")));
               create(new CompteBancaire(new Client("pierre","jean", sm.parse("12-29-1974"),"01237"),"444444444",8509990.20,"test client1",new TypeCompte("Courant","Compte courant")));
            
        } catch (ParseException ex) {
            Logger.getLogger(CompteBancaireFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 
 }

    public CompteBancaireFacade() {
        super(CompteBancaire.class);
    }
    
}
