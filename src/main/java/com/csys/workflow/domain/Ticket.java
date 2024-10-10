/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;



import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */

@Entity

@Table(name = "Ticket")
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ticket", nullable = false)
    private Integer idTicket;
    @Column(name = "ordre_ticket")
    private Integer ordreTicket;
    @Size(max = 50)
    @Column(name = "nom_ticket", length = 50)
    private String nomTicket;
    @Column(name = "minLength")
    private Integer minLength;
    @Column(name = "maxLength")
    private Integer maxLength;
    @Size(max = 255)
    @Column(name = "regle_ticket", length = 255)
    private String regleTicket;
    @Size(max = 50)
    @Column(name = "placeholder", length = 50)
    private String placeholder;
    @OneToMany(mappedBy = "ticket")
    private List<TicketData> ticketDataList;
    @OneToMany(mappedBy = "ticket")
    private List<Options> optionsList;
    @JoinColumn(name = "id_interface", referencedColumnName = "id_interface")
    @ManyToOne
    private Interface interface1;
    @JoinColumn(name = "id_type_ticket", referencedColumnName = "id_type_ticket")
    @ManyToOne
    private TypeTicket typeTicket;

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getOrdreTicket() {
        return ordreTicket;
    }

    public void setOrdreTicket(Integer ordreTicket) {
        this.ordreTicket = ordreTicket;
    }

    public String getNomTicket() {
        return nomTicket;
    }

    public void setNomTicket(String nomTicket) {
        this.nomTicket = nomTicket;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public Ticket setMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public Ticket setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public String getRegleTicket() {
        return regleTicket;
    }

    public Ticket setRegleTicket(String regleTicket) {
        this.regleTicket = regleTicket;
        return this;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public Ticket setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public List<TicketData> getTicketDataList() {
        return ticketDataList;
    }

    public List<Options> getOptionsList() {
        return optionsList;
    }

    public Interface getInterface1() {
        return interface1;
    }

    public Ticket setInterface1(Interface interface1) {
        this.interface1 = interface1;
        return this;
    }

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public Ticket setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
        return this;
    }

    public Ticket setOptionsList(List<Options> optionsList) {
        this.optionsList = optionsList;
        return this;
    }

    public Ticket setTicketDataList(List<TicketData> ticketDataList) {
        this.ticketDataList = ticketDataList;
        return this;
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket() {

    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.Ticket[ idTicket=" + idTicket + " ]";
    }


}
