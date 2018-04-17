/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.CompteBancaire.managebeans;

import ht.mbds.comptebancaire.entities.Client;
import ht.mbds.comptebancaire.entities.TypeCompte;
import ht.mbds.comptebancaire.session.ClientFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
@Named(value = "clientMbean")
@ViewScoped
public class ClientMbean implements Serializable {

    @EJB
    private ClientFacadeLocal clientFacade;
    private Client client;
    private List<Client> clientList = new ArrayList();
    private long id;

    /**
     * Creates a new instance of ClientMbean
     */
    public ClientMbean() {
    }

    public List<Client> getClients() {
       // if (clientList.isEmpty()) {
          System.out.println("ok test list client");
           clientList = clientFacade.getAllClients(true);
       return clientList;
     //   }
       //  clientList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        System.out.println("test id");
        this.client = client;
    }

    public long getId() {
        return id;
    }
    
     public void edit() {
        //
        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        this.client = clientFacade.find(id2);
        System.out.println(id2+" test 2"+ this.client.getNom());
         System.out.println(id2+" test 2");
    //   RequestContext context = RequestContext.getCurrentInstance();
    //context.execute("PF('CLIENT').show();");
        //return "ListTypeCompte?faces-redirect=true";
    }
   public String remove() {

        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        System.out.println("test est "+id);
        Client client =clientFacade.find(id2);
        if (client.getComptesBancaires().isEmpty()){
     
         clientFacade.remove( clientFacade.find(id2));
          return "ClientList?faces-redirect=true";
         
        }
        FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error"," Le client possede un compte."));
         return "";
       
    }

    public void preRenderView() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (client == null) {
            System.out.println("allo");
            //client = clientFacade.find(52);
            client = new Client();
        }
        System.out.println("test list pre client");
        clientList =clientFacade.getAllClients(false);
       // long idtest =52;
       // client = clientFacade.find(idtest);
    }
    public void setId(long id) {
        System.out.println("#### DANS SET ID !!! ###" + id);
        this.id = id;
    }
    public String showDetails(Client client) {
        this.client = client;
        return "EditClient?faces-redirect=true";
    }

    public String update() {
        System.out.println("Process update");
        clientFacade.edit(client);
        return "ClientList?faces-redirect=true";
    }

    public String listClient() {

        return "ClientList?faces-redirect=true";
    }

    public void lireClientParId() {
        if (id != 0) {
            client = clientFacade.find(id);
        } else {
            client = new Client();
        }
    }

}
