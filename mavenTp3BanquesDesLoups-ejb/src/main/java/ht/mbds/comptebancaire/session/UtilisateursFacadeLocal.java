/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.session;

import ht.mbds.comptebancaire.entities.Client;
import ht.mbds.comptebancaire.entities.Utilisateurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface UtilisateursFacadeLocal {

    void create(Utilisateurs utilisateurs);

    void edit(Utilisateurs utilisateurs);

    void remove(Utilisateurs utilisateurs);

    Utilisateurs find(Object id);

    Utilisateurs find(String email,String password);

    List<Utilisateurs> getAllUtilisateurs(boolean forceRefresh);

    List<Utilisateurs> findAll();

    List<Utilisateurs> findRange(int[] range);

    int count();

}
