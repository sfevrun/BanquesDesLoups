/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.CompteBancaire.managebeans;

import com.github.adminfaces.template.session.AdminSession;
import ht.mbds.comptebancaire.entities.Utilisateurs;
import ht.mbds.comptebancaire.entities.statutUtilisateur;
import ht.mbds.comptebancaire.session.UtilisateursFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Administrator
 */
@Named(value = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {

    @EJB
    private UtilisateursFacadeLocal utilisateursFacade;
    private String username;
    private String password;
    private String newPassword;
    private String newPasswordConfirmation;
    private Utilisateurs userCurrent;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirmation() {
        return newPasswordConfirmation;
    }

    public void setNewPasswordConfirmation(String newPasswordConfirmation) {
        this.newPasswordConfirmation = newPasswordConfirmation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Creates a new instance of LoginMBean
     */
    public LoginMBean() {
    }

    public String authentification() {
        RequestContext.getCurrentInstance().update("growl");
        
        try {
            userCurrent = utilisateursFacade.find(username, getCryptedPassword(password));
        } catch (Exception ex) {
            userCurrent=null;
        }
        if (userCurrent != null) {
            
            
            if (userCurrent.getType() ==statutUtilisateur.desactive){
                
                FacesContext context = FacesContext.getCurrentInstance();
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","l'utilisateur est desactive ."));
                 return "";
            }
            if (userCurrent.getType() ==statutUtilisateur.actif){
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", userCurrent);
            return "dashboard?faces-redirect=true";
            }
             System.out.print("test");
             return "signin?faces-redirect=true" ;
              
            }
       
   
   else{
        FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error"," Username ou mot de passe incorrect."));
                 return "";
        }
        }

    public Utilisateurs getUserCurrent() {
        return userCurrent;
    }

    public void setUserCurrent(Utilisateurs userCurrent) {
        this.userCurrent = userCurrent;
    }

    public String changePassword() {
        FacesContext context = FacesContext.getCurrentInstance();

        //1)Checking whether both new password and password confirmation are the same or not
        if (!this.newPassword.equals(this.newPasswordConfirmation)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","le mot de passe ne correspond pas avec le mot de passe de confirmation! Essayer a nouveau."));
            return"";
        } //2)Checking whether the new password is equal to the current one or not
        else if (userCurrent.getPassword().equals(getCryptedPassword(this.newPassword))) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","le mot de passe n'as pas ete modifie! essayer a nouveaau."));
            return "";
        } else {
            userCurrent.setPassword(getCryptedPassword(this.newPassword));
             userCurrent.setType(statutUtilisateur.actif);
            utilisateursFacade.edit(userCurrent);
            context.addMessage(null, new FacesMessage("Le mot de passe a ete modifie '" + this.username + "'."));
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", userCurrent);
             System.out.println("Li la : "+userCurrent.getEmail());
             return "dashboard?faces-redirect=true";
        }
  
    }

    public void preRenderView() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (userCurrent== null) {
            System.out.println("allo");
            userCurrent = new Utilisateurs();
        }
        else{
                if(userCurrent.getType()==statutUtilisateur.nouveau){
                System.out.print("test 3");
               RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('ConfirmLoginPwd').show();");
           }
        }
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
