/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.CompteBancaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface CompteBancaireFacadeLocal {

    void create(CompteBancaire compteBancaire);

    void edit(CompteBancaire compteBancaire);

    void remove(CompteBancaire compteBancaire);

    CompteBancaire find(Object id);

    List<CompteBancaire> findAll();
    List<CompteBancaire> getAllCompteBancaires(boolean forceRefresh) ;
    List<CompteBancaire> findRange(int[] range);
    void creercompteTest();

    int count();
    
}
