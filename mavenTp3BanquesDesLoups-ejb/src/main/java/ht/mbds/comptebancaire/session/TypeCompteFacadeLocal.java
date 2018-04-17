/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.TypeCompte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface TypeCompteFacadeLocal {

    void create(TypeCompte typeCompte);

    void edit(TypeCompte typeCompte);

    void remove(TypeCompte typeCompte);

    List<TypeCompte> getAllTypeCompte(boolean forceRefresh);

    TypeCompte find(Object id);

    List<TypeCompte> findAll();

    List<TypeCompte> findRange(int[] range);

    int count();

}
