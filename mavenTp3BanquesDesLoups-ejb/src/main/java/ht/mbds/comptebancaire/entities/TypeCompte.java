/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeCompte.findAll", query = "SELECT t FROM TypeCompte t"),
    @NamedQuery(name = "TypeCompte.findByTypeCompteId", query = "SELECT t FROM TypeCompte t WHERE t.id = :id")})
public class TypeCompte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Column(name = "type_compte", unique = true, nullable = true)
    private String typeCompte;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "typeCompte")
    private Collection<CompteBancaire> compteBancaire = new ArrayList();

    public TypeCompte() {
    }

    public TypeCompte(String typeCompte, String description) {
        this.typeCompte = typeCompte;
        this.description = description;
    }

    public void addComteBancaire(CompteBancaire c) {
        c.setTypeCompte(this);
        compteBancaire.add(c);
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Collection<CompteBancaire> getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(Collection<CompteBancaire> compteBancaire) {
        this.compteBancaire = compteBancaire;
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
        if (!(object instanceof TypeCompte)) {
            return false;
        }
        TypeCompte other = (TypeCompte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ht.mbds.comptebancaire.entities.TypeCompte[ id=" + id + " ]";
    }

}
