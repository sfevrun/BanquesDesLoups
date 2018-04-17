/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.mbds.comptebancaire.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Administrator
 */
@Entity
public class DetailsOperations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "code_transaction")
    private codeTransaction code;

    @Column(name = "montant")
    private double montant;

    @Column(name = "frais")
    private double frais;
    
    @ManyToOne
    @JoinColumn(name = "id_compteBancaire")
    private CompteBancaire numeroComteBancaire;

    @ManyToOne (cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id_operation")
    private Operation operation;

    public DetailsOperations() {
    }

    public DetailsOperations(codeTransaction code, double montant, double frais, CompteBancaire numeroComteBancaire, Operation operation) {
        this.code = code;
        this.montant = montant;
        this.frais = frais;
        this.numeroComteBancaire = numeroComteBancaire;
        this.operation = operation;
    }

    public codeTransaction getCode() {
        return code;
    }

    public void setCode(codeTransaction code) {
        this.code = code;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
    
    public CompteBancaire getNumeroComteBancaire() {
        return numeroComteBancaire;
    }

    public void setNumeroComteBancaire(CompteBancaire numeroComteBancaire) {
        this.numeroComteBancaire = numeroComteBancaire;
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
        if (!(object instanceof DetailsOperations)) {
            return false;
        }
        DetailsOperations other = (DetailsOperations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ht.mbds.comptebancaire.entities.DetailsOperations[ id=" + id + " ]";
    }

}
