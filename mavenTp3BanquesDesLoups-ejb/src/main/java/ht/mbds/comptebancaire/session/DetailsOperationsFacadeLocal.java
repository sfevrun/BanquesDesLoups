/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.CompteBancaire;
import ht.mbds.comptebancaire.entities.DetailsOperations;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface DetailsOperationsFacadeLocal {

    void create(DetailsOperations detailsOperations);

    void edit(DetailsOperations detailsOperations);

    void remove(DetailsOperations detailsOperations);

    DetailsOperations find(Object id);

    List<DetailsOperations> findAll();

    List<DetailsOperations> getAllDetailsOperations(boolean forceRefresh);

    List<DetailsOperations> findRange(int[] range);

    int count();

}
