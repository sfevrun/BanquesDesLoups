/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.CompteBancaire.managebeans;

import ht.mbds.comptebancaire.entities.Client;
import ht.mbds.comptebancaire.entities.TypeCompte;
import ht.mbds.comptebancaire.session.TypeCompteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Administrator
 */
@Named(value = "typeCompteMbean")
@ViewScoped
public class TypeCompteMbean implements Serializable{

    @EJB
    private TypeCompteFacadeLocal typeCompteFacade;

    /**
     * Creates a new instance of TypeCompteMbean
     */
    private TypeCompte typeCompte;
    private long id;
    private List<TypeCompte> typeCompteList = new ArrayList();

    /**
     * Creates a new instance of TypeCompteMbean
     */
    public TypeCompteMbean() {
    }

    public List<TypeCompte> getTypeComptes() {
        if (typeCompteList.isEmpty()) {
            typeCompteList = typeCompteFacade.getAllTypeCompte(true);
        }
        return typeCompteList;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        System.out.println("typest id");
        this.typeCompte = typeCompte;
    }

    public long getId() {
        return id;
    }

    public void preRenderView() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (typeCompte == null) {
            System.out.println("allo");
            typeCompte = new TypeCompte();
        }
        System.out.println("test");
        typeCompteList=typeCompteFacade.getAllTypeCompte(true);
    }

    public void setId(long id) {
        System.out.println("#### DANS SET ID !!! ###" + id);
        this.id = id;
    }

    public String showDetails(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
        return "ListTypeCompte?faces-redirect=true";
    }

    public String update() {
        System.out.println("Process update");
        typeCompteFacade.edit(typeCompte);
        return "ListTypeCompte?faces-redirect=true";
    }

    public String remove() {
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       
        
        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        //for (String key : params.keySet()){
            //iterate over key
            //System.out.println(key+" "+params.get(key) +"  valuse "+params.get(params.get(key)));
       // }
     
       // System.out.println("test  c ok  1 "+ params.size());
       // System.out.println(id);
      
       TypeCompte type = typeCompteFacade.find(id2);
         if ( type.getCompteBancaire().isEmpty()){
        //System.out.println(type.getDescription());
        typeCompteFacade.remove(type);
        return "ListTypeCompte?faces-redirect=true";
        
         }
        
        FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error"," Impossile de supprimer un type compte qui a au moins un compte."));
         return "";
       
    }

    public void edit() {
        //
        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        this.typeCompte = typeCompteFacade.find(id2);
        System.out.println(id2+" test 2");
         System.out.println(id2+" test 2");
        //RequestContext context = RequestContext.getCurrentInstance();
      // context.execute("PF('TYPECOMPTE').show();");
        //return "ListTypeCompte?faces-redirect=true";
    }

    public String listTypeCompte() {

        return "ListTypeCompte?faces-redirect=true";
    }

}
