/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c")})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @Column(name = "numero_compte", nullable = false, unique = true)
    private String numeroCompte;
    private double solde;
    private String descriotion;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;

    private statutCompte statut;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id_typeCompte")
    private TypeCompte typeCompte;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id_client")
    private Client client;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "numeroComteBancaire")
    private Collection<DetailsOperations> detailsOperations = new ArrayList();

    public CompteBancaire() {
    }
    
        public CompteBancaire(String numeroCompte, double solde, String descriotion, statutCompte statut, TypeCompte typeCompte, Client client) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.descriotion = descriotion;
        this.statut = statut;
        this.typeCompte = typeCompte;
        this.client = client;
    }

    public CompteBancaire(Client client, String numeroCompte, double solde, String description, TypeCompte typecompte) {
        this.client = client;
        this.solde = solde;
        this.descriotion = description;
        this.dateCreation = new Date();
        this.numeroCompte = numeroCompte;
        this.typeCompte = typecompte;
    }

    public void addDetailsOperations(DetailsOperations d) {
        d.setNumeroComteBancaire(this);
        detailsOperations.add(d);
    }

    public double getSolde() {
        return solde;
    }

    public statutCompte getStatut() {
        return statut;
    }

    public void setStatut(statutCompte statut) {
        this.statut = statut;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void deposer(double montant) {
        this.solde += montant;
    }

    public double retirer(double montant) {
        if (montant < solde) {
            this.solde -= montant;
            return montant;
        } else {
            return 0;
        }
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getDescriotion() {
        return descriotion;
    }

    public void setDescriotion(String descriotion) {
        this.descriotion = descriotion;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<DetailsOperations> getOperations() {
        return detailsOperations;
    }

    public void setOperations(Collection<DetailsOperations> detailsOperations) {
        this.detailsOperations = detailsOperations;
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
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ht.mbds.comptebancaire.entities.CompteBancaire[ id=" + id + " ]";
    }

}
