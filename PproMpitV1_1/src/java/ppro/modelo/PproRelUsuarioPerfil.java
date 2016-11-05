/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.modelo;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author casa
 */
@Entity
@Table(name = "ppro_rel_usuario_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PproRelUsuarioPerfil.findAll", query = "SELECT p FROM PproRelUsuarioPerfil p"),
    @NamedQuery(name = "PproRelUsuarioPerfil.findByRupId", query = "SELECT p FROM PproRelUsuarioPerfil p WHERE p.rupId = :rupId"),
    @NamedQuery(name = "PproRelUsuarioPerfil.findByRupEstado", query = "SELECT p FROM PproRelUsuarioPerfil p WHERE p.rupEstado = :rupEstado")})
@ManagedBean
@SessionScoped
public class PproRelUsuarioPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rup_id")
    private Integer rupId;
    @Column(name = "rup_estado")
    private Integer rupEstado;
    @JoinColumn(name = "rup_usu_id", referencedColumnName = "usu_id")
    @ManyToOne
    private PproUsuario rupUsuId;
    @JoinColumn(name = "rup_perf_id", referencedColumnName = "perf_id")
    @ManyToOne
    private PproPerfil rupPerfId;

    public PproRelUsuarioPerfil() {
    }

    public PproRelUsuarioPerfil(Integer rupId) {
        this.rupId = rupId;
    }

    public Integer getRupId() {
        return rupId;
    }

    public void setRupId(Integer rupId) {
        this.rupId = rupId;
    }

    public Integer getRupEstado() {
        return rupEstado;
    }

    public void setRupEstado(Integer rupEstado) {
        this.rupEstado = rupEstado;
    }

    public PproUsuario getRupUsuId() {
        return rupUsuId;
    }

    public void setRupUsuId(PproUsuario rupUsuId) {
        this.rupUsuId = rupUsuId;
    }

    public PproPerfil getRupPerfId() {
        return rupPerfId;
    }

    public void setRupPerfId(PproPerfil rupPerfId) {
        this.rupPerfId = rupPerfId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rupId != null ? rupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PproRelUsuarioPerfil)) {
            return false;
        }
        PproRelUsuarioPerfil other = (PproRelUsuarioPerfil) object;
        if ((this.rupId == null && other.rupId != null) || (this.rupId != null && !this.rupId.equals(other.rupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       // return "ppro.modelo.PproRelUsuarioPerfil[ rupId=" + rupId + " ]";
        return String.valueOf(rupId);
    }
    
}
