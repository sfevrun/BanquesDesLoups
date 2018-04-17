/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.CompteBancaire.managebeans;

import ht.mbds.comptebancaire.entities.Client;
import ht.mbds.comptebancaire.entities.CompteBancaire;
import ht.mbds.comptebancaire.entities.TypeCompte;
import ht.mbds.comptebancaire.entities.statutCompte;
import ht.mbds.comptebancaire.session.ClientFacadeLocal;
import ht.mbds.comptebancaire.session.CompteBancaireFacadeLocal;
import ht.mbds.comptebancaire.session.TypeCompteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@Named(value = "compteMBean")
@ViewScoped
public class CompteMBean implements Serializable {

    @EJB
    private TypeCompteFacadeLocal typeCompteFacade;
    @EJB
    private CompteBancaireFacadeLocal compteBancaireFacade;
    @EJB
    private ClientFacadeLocal clientFacade;

    /**
     * Creates a new instance of CompteMBean
     */
   // private GestionnaireDeCompteBancaire gc;
    private CompteBancaire compteBancaire;
    private long id;
    private Long idClient;
    private List<CompteBancaire> compteBancaireList = new ArrayList();

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        System.out.println("#### DANS SET IDClient !!! ###" + id);
        this.idClient = idClient;
    }

    public CompteMBean() {
    }
    /* @PostConstruct
     public void init() {
     compteBancaire= new CompteBancaire ();
     }
     */

    public Converter getStatutCompteConverter() {
        return statutCompteConverter;
    }

    public void setStatutCompteConverter(Converter statutCompteConverter) {
        this.statutCompteConverter = statutCompteConverter;
    }

            private Converter statutCompteConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object type transaction: " + value);
            //long code = Long.parseLong(value);
            return statutCompte.valueOf(value);

        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {

            statutCompte typeTransaction = (statutCompte) value;
            return typeTransaction.name();

        }
    };
      public List<statutCompte> getListStatutCompte() {

        List<statutCompte> liste = new ArrayList();
        for (statutCompte code : statutCompte.values()) {
            liste.add(code);
        }
        return liste;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        System.out.println("#### DANS SET ID !!! ###" + id);
        this.id = id;
    }

    /**
     * Creates a new instance of CompteMBean
     */
    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public void creerDonneTest() {
        System.out.print("test test");
        compteBancaireFacade.creercompteTest();
    }

    public List<CompteBancaire> getCompteBancaires() {
        if (compteBancaireList.isEmpty()) {
            refreshListOfCompteBancaireFromDatabase();
        }
        return compteBancaireList;
    }

    public void refreshListOfCompteBancaireFromDatabase() {
        compteBancaireList = compteBancaireFacade.getAllCompteBancaires(true);
    }

    public void preRenderView() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        refreshListOfCompteBancaireFromDatabase();
    }

    public String showDetails(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
        return "EditerCompteBancaire?faces-redirect=true";
    }

    public Converter getTypeCompteConverter() {
        return typeCompteConverter;
    }

    public void setTypeCompteConverter(Converter typeCompteConverter) {
        this.typeCompteConverter = typeCompteConverter;
    }

    public String update() {
        System.out.println("Process update");
        compteBancaireFacade.edit(compteBancaire);
        return "compte-liste?faces-redirect=true";
    }

    public String listComptebancaire() {

        return "index?faces-redirect=true";
    }

    // Pour remplir la liste des discount codes

    public List<Client> getAllClients() {
        return clientFacade.getAllClients(false);
    }
 public  String getClient() {
     try{
      Client client =clientFacade.find(idClient);
      return client.getNom()+" "+client.getPrenom();
     }
     catch(Exception e){
     return"";
     }
        
    }
    public List<TypeCompte> getAllTypeComptes() {
        return typeCompteFacade.getAllTypeCompte(false);
    }

    public Converter getClientCodeConverter() {
        return clientCodeConverter;
    }

    public Converter getTypeCompteCodeConverter() {
        return typeCompteConverter;
    }

    private Converter clientCodeConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object : " + value);
            long code = Long.parseLong(value);

            Client client = clientFacade.find(code);
            return client;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {

            Client client = (Client) value;
            return client.getId().toString();

        }
    };

    private Converter typeCompteConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object type compte: " + value);
            long code = Long.parseLong(value);

            TypeCompte typeCompte = typeCompteFacade.find(code);
            return typeCompte;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {

            TypeCompte typeCompte = (TypeCompte) value;
            return typeCompte.getId().toString();

        }
    };

    public void lireCompteParId() {
        if (id != 0) {
            compteBancaire = compteBancaireFacade.find(id);
        } else {
             System.out.println("Process e  creer");
            compteBancaire = new CompteBancaire();
            if (idClient != 0) {
                compteBancaire.setClient(clientFacade.find(idClient));
                System.out.println("Process e "+idClient);
            }
        }
         System.out.println("Process e ok");
    }
 public String nouveau() {
        System.out.println("Process update");
  
        return "ClientList?faces-redirect=true";
    }

}
