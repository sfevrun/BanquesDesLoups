/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.CompteBancaire.managebeans;

import ht.mbds.comptebancaire.entities.TypeCompte;
import ht.mbds.comptebancaire.entities.Utilisateurs;
import ht.mbds.comptebancaire.entities.statutUtilisateur;
import ht.mbds.comptebancaire.entities.typeRoleUser;
import ht.mbds.comptebancaire.session.UtilisateursFacadeLocal;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Administrator
 */
@Named(value = "utilisateurMBean")
@ViewScoped
public class UtilisateurMBean implements Serializable{

    @EJB
    private UtilisateursFacadeLocal utilisateursFacade;
    private Utilisateurs utilisateur;
    private long id;
    private final statutUtilisateur defaultStatu = statutUtilisateur.desactive;
    private List<Utilisateurs> utilisateurList = new ArrayList();

    /**
     * Creates a new instance of UtilisateurMBean
     */
    public UtilisateurMBean() {
    }

    public statutUtilisateur getDefaultStatu() {
        return defaultStatu;
    }

    private Converter roleUserConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object code transaction: " + value);
            //long code = Long.parseLong(value);
            return typeRoleUser.valueOf(value);

        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {

            typeRoleUser code = (typeRoleUser) value;
            return code.name();

        }
    };

     private Converter statutUtilisateurConverter = new Converter() {
        @Override

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            System.out.println("Dans GET AS Object code transaction: " + value);
            //long code = Long.parseLong(value);
            return statutUtilisateur.valueOf(value);

        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {

            statutUtilisateur code = (statutUtilisateur) value;
            return code.name();

        }
    };

    public void changeStaut (){
       
        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        
        utilisateur = utilisateursFacade.find(id2);
        
        if (utilisateur.getType() ==statutUtilisateur.actif){
            utilisateur.setType(statutUtilisateur.desactive);
        }
        else{
            utilisateur.setType(statutUtilisateur.actif);
        }
       update(); 
    }
    
    public void restaureMotdePasse (){
       
        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        
        utilisateur = utilisateursFacade.find(id2);
        utilisateursFacade.edit(utilisateur);
         RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('MOTDEPWD').show();");
    }
    public Converter getStatutUtilisateurConverter() {
        return statutUtilisateurConverter;
    }

    public void setStatutUtilisateurConverter(Converter statutUtilisateurConverter) {
        this.statutUtilisateurConverter = statutUtilisateurConverter;
    }
     
       public List<typeRoleUser> getListTypeRoleUser() {

        List<typeRoleUser> liste = new ArrayList();
        for (typeRoleUser code : typeRoleUser.values()) {
            liste.add(code);
        }
        return liste;
    }
    public void preRenderView() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (utilisateur == null) {
            System.out.println("allo");
            utilisateur = new Utilisateurs();
            utilisateur.setType(statutUtilisateur.nouveau);
        }
        System.out.println("test");
        utilisateurList=utilisateursFacade.getAllUtilisateurs(true);
    }

    public List<Utilisateurs> getAllUtilisateurs() {
        if (utilisateurList.isEmpty()) {
            utilisateurList = utilisateursFacade.getAllUtilisateurs(true);
        }
        return utilisateurList;
    }

    public Utilisateurs getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateurs utilisateur) {
        this.utilisateur = utilisateur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Converter getRoleUserConverter() {
        return roleUserConverter;
    }

    public void setRoleUserConverter(Converter roleUserConverter) {
        this.roleUserConverter = roleUserConverter;
    }

    public List<Utilisateurs> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateurs> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    public String update() {
        System.out.println("Process update "+utilisateur.getEmail());
        
        
        utilisateur.setPassword(getCryptedPassword(utilisateur.getEmail()));
        utilisateursFacade.edit(utilisateur);
        
        return "utilisateur-liste?faces-redirect=true";
    }

    public String remove() {

        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        System.out.println("test est "+id);
     
        utilisateursFacade.remove(utilisateursFacade.find(id2));
        return "utilisateur-liste?faces-redirect=true";
    }
    
    

    public void edit() {
        //
        long id2 = Long.parseLong((new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))).toString());
        
        this.utilisateur = utilisateursFacade.find(id2);
        RequestContext context = RequestContext.getCurrentInstance();
       context.execute("PF('UTILISATEUR').show();");
       
        //return "ListTypeCompte?faces-redirect=true";
    }

      private String getCryptedPassword(String notCryptedPassword) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        if (md == null) {
            return notCryptedPassword;
        }

        md.update(notCryptedPassword.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("En format hexa : " + sb.toString());

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

   
}
