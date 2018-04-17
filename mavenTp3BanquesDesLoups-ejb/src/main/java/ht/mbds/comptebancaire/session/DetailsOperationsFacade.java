/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.DetailsOperations;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class DetailsOperationsFacade extends AbstractFacade<DetailsOperations> implements DetailsOperationsFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_mavenTp3BanquesDesLoups-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
public List<DetailsOperations> getAllDetailsOperations(boolean forceRefresh) {
     //   creerComptesTest();
        
         List<DetailsOperations> liste = findAll() ;
         if(forceRefresh) {
            for (DetailsOperations detailsOperation: liste) {
                // em.refresh force le rafraichissement des
                // attributs de l'objet en mémoire en fonction
                // des dernières valeurs pour cet objet, dans la base
                // (au plus près du dernier commit)
                em.refresh(detailsOperation);               
            }
        }
        
        return liste;
        
    }
    public DetailsOperationsFacade() {
        super(DetailsOperations.class);
    }
    
}
