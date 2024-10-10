package com.csys.workflow.domain;




import javax.persistence.*;
import java.util.Objects;


@Table(name = "Options")

@NamedQueries({
        @NamedQuery(name = "Options.findAll", query = "SELECT o FROM Options o")})
@Entity
public class Options {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_option", nullable = false)
    private Integer idOption;
    @Basic
    @Column(name = "nom_option", nullable = true, length = 255)
    private String nomOption;
    @ManyToOne
    @JoinColumn(name = "id_ticket", referencedColumnName = "id_ticket")
    private Ticket ticket;


    public Integer getIdOption() {
        return idOption;
    }

    public void setIdOption(Integer idOption) {
        this.idOption = idOption;
    }

    public String getNomOption() {
        return nomOption;
    }

    public void setNomOption(String nomOption) {
        this.nomOption = nomOption;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Options() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Options options = (Options) o;

        if (idOption != options.idOption) return false;
        return Objects.equals(nomOption, options.nomOption);
    }

    @Override
    public int hashCode() {
        int result = idOption;
        result = 31 * result + (nomOption != null ? nomOption.hashCode() : 0);
        return result;
    }
}
