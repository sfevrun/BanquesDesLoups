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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Administrator
 */
@Entity
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_operation")
    private TypeTransaction typeOperation;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)

    @Column(name = "date_operation")
    private Date dateOperation;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "operation")
    private Collection<DetailsOperations> detailsOperations= new ArrayList();

    /*@ManyToOne
     @JoinColumn(name = "id_transaction")
     private TransactionOperation transactionop;
     */
    public Operation() {
    }

    /* public Operation(TypeTransaction typeOperation, TransactionOperation transactionop, double montant, Date dateOperation, CompteBancaire numeroComteBancaire) {
     this.typeOperation = typeOperation;
     this.transactionop = transactionop;
     this.montant = montant;
     this.dateOperation = dateOperation;
     this.numeroComteBancaire = numeroComteBancaire;
     }
     */
    public Operation(TypeTransaction typeOperation, Date dateOperation) {
        this.typeOperation = typeOperation;
        this.dateOperation = dateOperation;
    }

    public void addDetailsOperations(DetailsOperations d) {
        d.setOperation(this);
        detailsOperations.add(d);
    }

    public Collection<DetailsOperations> getDetailsOperations() {
        return detailsOperations;
    }

    public void setDetailsOperations(Collection<DetailsOperations> detailsOperations) {
        this.detailsOperations = detailsOperations;
    }

    public TypeTransaction getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeTransaction typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    /*
     public TransactionOperation getTransactionop() {
     return transactionop;
     }

     public void setTransactionop(TransactionOperation transactionop) {
     this.transactionop = transactionop;
     }

     */
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
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ht.mbds.comptebancaire.entities.Operation[ id=" + id + " ]";
    }

}
