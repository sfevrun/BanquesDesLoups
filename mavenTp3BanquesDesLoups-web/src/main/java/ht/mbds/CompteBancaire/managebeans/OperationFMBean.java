/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.CompteBancaire.managebeans;

import ht.mbds.comptebancaire.entities.CompteBancaire;
import ht.mbds.comptebancaire.entities.DetailsOperations;
import ht.mbds.comptebancaire.entities.Operation;
import ht.mbds.comptebancaire.entities.TypeCompte;
import ht.mbds.comptebancaire.entities.TypeTransaction;
import ht.mbds.comptebancaire.entities.codeTransaction;
import ht.mbds.comptebancaire.session.CompteBancaireFacadeLocal;
import ht.mbds.comptebancaire.session.DetailsOperationsFacadeLocal;
import ht.mbds.comptebancaire.session.OperationFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
@Named(value = "operationFMBean")
@ViewScoped
public class OperationFMBean implements Serializable {

    @EJB
    private CompteBancaireFacadeLocal compteBancaireFacade;

    @EJB
    private DetailsOperationsFacadeLocal detailsOperationsFacade;

    @EJB
    private OperationFacadeLocal operationFacade;

    private Operation operation;
    private DetailsOperations detailsoperation;
    private CompteBancaire compteBancaireCR;
    private CompteBancaire compteBancaireOperation;
    private long id;
    private long idDetails;
    private long idCompte;
    private long idCompteCr;
    private List <DetailsOperations>  listoperationscompte = new ArrayList();

    public List<DetailsOperations> getListoperationscomptes() {
        return listoperationscompte;
    }

    public void setListoperationscompte(List<DetailsOperations> listoperations) {
        this.listoperationscompte= listoperations;
    }
   
    
    
     public List<CompteBancaire> geCompteByNumeroCompte(String numeroCompte) {
         List<CompteBancaire> Comptes = new ArrayList (); 
        try{
       
        
        for(CompteBancaire compteBancaire: compteBancaireFacade.findAll()) {  
            if(compteBancaire.getNumeroCompte().toLowerCase().startsWith(numeroCompte.toLowerCase()))  
                Comptes.add(compteBancaire);
        }
        }catch (Exception e){
        }
        return Comptes;
    }


  
    
    

    public long getIdCompte() {
        return idCompte;
    }
    
    public CompteBancaire getCompteBancaireOperation() {
        return compteBancaireOperation;
    }

    public void setCompteBancaireOperation(CompteBancaire compteBancaireOperation) {
        this.compteBancaireOperation = compteBancaireOperation;
    }

    public long getIdCompteCr() {
        return idCompteCr;
    }

    public void setIdCompteCr(long idCompteCr) {
        this.idCompteCr = idCompteCr;
    }

    public List<DetailsOperations> getDetailsOperationList() {
        return detailsOperationList;
    }

    public void setDetailsOperationList(List<DetailsOperations> detailsOperationList) {
        this.detailsOperationList = detailsOperationList;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }
  

    public DetailsOperations getDetailsoperation() {
        return detailsoperation;
    }

    public void setDetailsoperation(DetailsOperations detailsoperation) {
        this.detailsoperation = detailsoperation;
    }
    private List<DetailsOperations> detailsOperationList = new ArrayList();

    /**
     * Creates a new instance of OperationFMBean
     */
    public OperationFMBean() {
    }

    public List<TypeTransaction> getListTypeOperation() {

        List<TypeTransaction> liste = new ArrayList();
        for (TypeTransaction typeTransaction : TypeTransaction.values()) {
            liste.add(typeTransaction);
        }
        return liste;
    }

    public List<codeTransaction> getListCodeTransaction() {

        List<codeTransaction> liste = new ArrayList();
        for (codeTransaction code : codeTransaction.values()) {
            liste.add(code);
        }
        return liste;
    }

    private Converter typeTransactionConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object type transaction: " + value);
            //long code = Long.parseLong(value);
            return TypeTransaction.valueOf(value);

        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {

            TypeTransaction typeTransaction = (TypeTransaction) value;
            return typeTransaction.name();

        }
    };
    private Converter compteBancaireConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object : " + value);
            long code = Long.parseLong(value);

            CompteBancaire compteBancaire = compteBancaireFacade.find(code);
            return compteBancaire;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
     System.out.println("Dans GET AS Object : " + value);
            CompteBancaire compteBancaire = (CompteBancaire) value;
            return compteBancaire.getId().toString();

        }
    };
    private Converter codeTransactionConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object code transaction: " + value);
            //long code = Long.parseLong(value);
            return codeTransaction.valueOf(value);

        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {

            codeTransaction code = (codeTransaction) value;
            return code.name();

        }
    };

    public CompteBancaire getCompteBancaireCR() {
        return compteBancaireCR;
    }

    public void setCompteBancaireCR(CompteBancaire compteBancaireCR) {
        this.compteBancaireCR = compteBancaireCR;
    }

    public Converter getTypeTransactionConverter() {
        return typeTransactionConverter;
    }

    public void setTypeTransactionConverter(Converter typeTransactionConverter) {
        this.typeTransactionConverter = typeTransactionConverter;
    }

    public Converter getCodeTransactionConverter() {
        return codeTransactionConverter;
    }

    public void setCodeTransactionConverter(Converter codeTransactionConverter) {
        this.codeTransactionConverter = codeTransactionConverter;
    }

    public Converter getCompteBancaireConverter() {
        return compteBancaireConverter;
    }

    public void setCompteBancaireConverter(Converter compteBancaireConverter) {
        this.compteBancaireConverter = compteBancaireConverter;
    }

    public List<DetailsOperations> getAllOperations() {
        if (detailsOperationList.isEmpty()) {
            detailsOperationList = detailsOperationsFacade.getAllDetailsOperations(true);
        }
        return detailsOperationList;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        System.out.println("typest id");
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public long getIdDetails() {
        return idDetails;
    }

    public void preRenderView() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        

            operation = new Operation();
            operation.setDateOperation(new Date ());
            detailsoperation = new DetailsOperations();
            
            System.out.println("allo " + detailsoperation.toString());
       
       // detailsOperationsFacade.getAllDetailsOperations(true);
    }

    public void setId(long id) {
        System.out.println("#### DANS SET ID !!! ###" + id);
        this.id = id;
    }

    public void setIdDetails(long idDetails) {
        this.idDetails = idDetails;
    }

    public String showDetails(Operation operation) {
        this.operation = operation;
        return "ListOperations?faces-redirect=true";
    }

    public String update() {

        switch (operation.getTypeOperation()) {
            case Depot:
                depot();
                break;
            case Retrait:
                retrait();
                break;
            case Virement:
                virement();
                break;
            default:
                System.out.println("");
                ;

        }
        updateColumns() ;
        return "ListOperations?idCompte="+ compteBancaireOperation.getId().toString()+"&faces-redirect=true";
    }

    private void depot() {
       detailsoperation.setNumeroComteBancaire(compteBancaireOperation);

        System.out.println("Process update");
        System.out.println("Process update " + detailsoperation.getNumeroComteBancaire().getNumeroCompte());
        System.out.println("Process update " + detailsoperation.getFrais());
        System.out.println("Process update " + detailsoperation.getMontant());
        System.out.println("Process update " + operation.getTypeOperation().name());
        detailsoperation.setCode(codeTransaction.credit);
        operation.addDetailsOperations(detailsoperation);
        System.out.println("Process update  " + detailsoperation.getNumeroComteBancaire().getSolde());
        // detailsoperation.setOperation(operation);
        detailsoperation.getNumeroComteBancaire().deposer(detailsoperation.getMontant());
        compteBancaireFacade.edit(detailsoperation.getNumeroComteBancaire());
         System.out.println("Process update modifier " + detailsoperation.getNumeroComteBancaire().getSolde());
        operationFacade.edit(operation);
        // detailsoperation.setOperation(operation);
        //detailsOperationsFacade.edit(detailsoperation);
        System.out.println("Process Fin update");
    }

    private void retrait() {
        detailsoperation.setNumeroComteBancaire(compteBancaireOperation);

        System.out.println("Process update");
        System.out.println("Process update " + detailsoperation.getNumeroComteBancaire().getNumeroCompte());
        System.out.println("Process update " + detailsoperation.getFrais());
        System.out.println("Process update " + detailsoperation.getMontant());
        System.out.println("Process update " + operation.getTypeOperation().name());
        detailsoperation.setCode(codeTransaction.debit);
        operation.addDetailsOperations(detailsoperation);
        System.out.println("Process update  " + detailsoperation.getNumeroComteBancaire().getSolde());
        //detailsoperation.setOperation(operation);
        detailsoperation.getNumeroComteBancaire().retirer(detailsoperation.getMontant());
        //detailsoperation.setOperation(operation);
        compteBancaireFacade.edit(detailsoperation.getNumeroComteBancaire());
         System.out.println("Process update modifier " + detailsoperation.getNumeroComteBancaire().getSolde());
        operationFacade.edit(operation);
        // detailsOperationsFacade.edit(detailsoperation);
        System.out.println("Process Fin update");
    }

    private void virement() {
        detailsoperation.setNumeroComteBancaire(compteBancaireOperation);
        
        System.out.println("Process update");
        System.out.println("Process update " + detailsoperation.getNumeroComteBancaire().getNumeroCompte());
        System.out.println("Process update " + detailsoperation.getFrais());
        System.out.println("Process update " + detailsoperation.getMontant());
        System.out.println("Process update " + operation.getTypeOperation().name());

        detailsoperation.setCode(codeTransaction.debit);
       //detailsoperation.setOperation(operation);

        operation.addDetailsOperations(detailsoperation);
       //detailsoperation.setOperation(operation);

        detailsoperation.getNumeroComteBancaire().retirer(detailsoperation.getMontant() + detailsoperation.getFrais());

       // detailsoperation.getNumeroComteBancaire().retirer(detailsoperation.getMontant());
        //detailsoperation.setOperation(operation);
      //  

       //detailsoperation.setOperation(operation);
        //detailsOperationsFacade.edit(detailsoperation);
        compteBancaireCR.deposer(detailsoperation.getMontant());

        DetailsOperations detailsoperationvirement = new DetailsOperations();
        //detailsoperationvirement.setNumeroComteBancaire(compteBancaireCR);
        detailsoperationvirement.setFrais(detailsoperation.getFrais());
        detailsoperationvirement.setMontant(detailsoperation.getMontant());
        detailsoperationvirement.setCode(codeTransaction.credit);
        compteBancaireCR.addDetailsOperations(detailsoperationvirement);

        operation.addDetailsOperations(detailsoperationvirement);

        
        //detailsOperationsFacade.edit(detailsoperationvirement);
        //operationFacade.edit(operation);
        //operation.addDetailsOperations(detailsoperation) 
       // operationFacade.edit(operation);
       compteBancaireFacade.edit(compteBancaireCR);
       compteBancaireFacade.edit(detailsoperation.getNumeroComteBancaire());

    }

    public String remove() {

        long id = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        System.out.println(id);
        Operation operationT = operationFacade.find(id);
        System.out.println(operationT.toString());
        operationFacade.remove(operationT);
        return "ListOperations?idCompte="+ compteBancaireOperation.getId().toString()+"&faces-redirect=true";
    }

    public void edit() {
        //
        long id = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        this.operation = operationFacade.find(id);
        System.out.println(id);
        //return "ListTypeCompte?faces-redirect=true";
    }

    public String listOperations() {

        return "ListOperations?idCompte="+ compteBancaireOperation.getId().toString()+"&faces-redirect=true";
    }
    
     private void createDynamicColumns() {
         
         
        for(DetailsOperations columnKey : compteBancaireOperation.getOperations()) {
           
            listoperationscompte.add(detailsoperation);
           
            }
        
    }
     
    public void updateColumns() {
        //reset table state
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:operations");
        table.setValueExpression("sortBy", null);
         
        //update columns
        createDynamicColumns();
    }
    
 public void lireCompteParId() {
        if (idCompte != 0) {
            compteBancaireOperation= compteBancaireFacade.find(idCompte);
            updateColumns() ;
            
            for(CompteBancaire compteBancaire: compteBancaireFacade.findAll()) {  
            if(!compteBancaire.getNumeroCompte().equalsIgnoreCase(compteBancaireOperation.getNumeroCompte())){  
                compteBancaireCR=compteBancaire;
                break;
            }
            }
           // compteBancaireCR= new compteBancaire
         preRenderView();
        }
        else{
             FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error"," Vous devriez selectionner un Compte."));
          
        }
       
 }
}

