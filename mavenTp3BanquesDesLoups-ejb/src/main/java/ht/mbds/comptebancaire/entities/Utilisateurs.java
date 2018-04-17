/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.Control", query = "SELECT u FROM Utilisateurs u WHERE u.email = :email and u.password = :password" ),
    @NamedQuery(name = "Utilisateurs.findAll", query = "SELECT u FROM Utilisateurs u"),
    @NamedQuery(name = "Utilisateurs.findByClientEmail", query = "SELECT u FROM Utilisateurs u WHERE u.email = :email"),})

public class Utilisateurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column( nullable = false, unique = true)
    private String email;
    private statutUtilisateur type;

    public statutUtilisateur getType() {
        return type;
    }

    public void setType(statutUtilisateur type) {
        this.type = type;
    }
    
    

    private String password;
    private typeRoleUser roleUser;


    public Utilisateurs(String email, statutUtilisateur type, String password, typeRoleUser roleUser) {
        this.email = email;
        this.type = type;
        this.password = password;
        this.roleUser = roleUser;
    }
    

    public Utilisateurs() {
    }

    public typeRoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(typeRoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public boolean isLoggedIn() {

        return email != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateurs)) {
            return false;
        }
        Utilisateurs other = (Utilisateurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ht.mbds.comptebancaire.entities.Utilisateurs[ id=" + id + " ]";
    }

}
