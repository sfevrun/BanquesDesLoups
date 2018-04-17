/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.TypeCompte;
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
public class TypeCompteFacade extends AbstractFacade<TypeCompte> implements TypeCompteFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_mavenTp3BanquesDesLoups-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   public List<TypeCompte> getAllTypeCompte(boolean forceRefresh) {
        
        List<TypeCompte> liste = findAll();
         if(forceRefresh) {
            for (TypeCompte typeCompte: liste) {
                // em.refresh force le rafraichissement des
                // attributs de l'objet en mémoire en fonction
                // des dernières valeurs pour cet objet, dans la base
                // (au plus près du dernier commit)
                em.refresh(typeCompte);               
            }
        }
        return  liste;
    }
    public TypeCompteFacade() {
        super(TypeCompte.class);
    }
    
}
