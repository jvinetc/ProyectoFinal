/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author casa
 */
@Entity
@Table(name = "ppro_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PproDocumento.findAll", query = "SELECT p FROM PproDocumento p ORDER BY p.docId DESC"),
    @NamedQuery(name = "PproDocumento.findByDocId", query = "SELECT p FROM PproDocumento p WHERE p.docId = :docId"),
    @NamedQuery(name = "PproDocumento.findByDocNumero", query = "SELECT p FROM PproDocumento p WHERE p.docNumero = :docNumero"),
    @NamedQuery(name = "PproDocumento.findByDocRuta", query = "SELECT p FROM PproDocumento p WHERE p.docRuta = :docRuta"),
    @NamedQuery(name = "PproDocumento.buscarPorProv", query = "SELECT p FROM PproDocumento p WHERE p.docProvId = :docProvId"),
    @NamedQuery(name = "PproDocumento.validarDocumento", query = "SELECT p FROM PproDocumento p INNER JOIN p.docProvId prov INNER JOIN prov.provPerId per WHERE per.perRutComp= :rutProv and p.docNumero = :nDoc")})
@ManagedBean
@SessionScoped

public class PproDocumento implements Serializable {

    @Column(name = "doc_fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date docFechaIngreso;
    @JoinColumn(name = "doc_form_pag_id", referencedColumnName = "fpag_id")
    @ManyToOne
    private PproFormaPago docFormPagId;

    @Size(max = 255)
    @Column(name = "doc_nombre")
    private String docNombre;

    @OneToMany(mappedBy = "facDocId")
    private Collection<PproFactura> pproFacturaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doc_id")
    private Integer docId;
    @Column(name = "doc_numero")
    private String docNumero;
    @Size(max = 255)
    @Column(name = "doc_ruta")
    private String docRuta;
    @OneToMany(mappedBy = "aneDocId")
    private Collection<PproAnexoDocumento> pproAnexoDocumentoCollection;
    @JoinColumn(name = "doc_tdoc_id", referencedColumnName = "tdoc_id")
    @ManyToOne
    private PproTipoDocumento docTdocId;
    @JoinColumn(name = "doc_usu_ingresa", referencedColumnName = "usu_id")
    @ManyToOne
    private PproUsuario docUsuIngresa;
    @JoinColumn(name = "doc_usu_autoriza", referencedColumnName = "usu_id")
    @ManyToOne
    private PproUsuario docUsuAutoriza;
    @JoinColumn(name = "doc_usu_modifica", referencedColumnName = "usu_id")
    @ManyToOne
    private PproUsuario docUsuModifica;
    @JoinColumn(name = "doc_prov_id", referencedColumnName = "prov_id")
    @ManyToOne
    private PproProveedor docProvId;
    @JoinColumn(name = "doc_edoc_id", referencedColumnName = "edoc_id")
    @ManyToOne
    private PproEstadoDocumento docEdocId;

    public PproDocumento() {
    }

    public PproDocumento(Integer docId) {
        this.docId = docId;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDocNumero() {
        return docNumero;
    }

    public void setDocNumero(String docNumero) {
        this.docNumero = docNumero;
    }

    public String getDocRuta() {
        return docRuta;
    }

    public void setDocRuta(String docRuta) {
        this.docRuta = docRuta;
    }

    @XmlTransient
    public Collection<PproAnexoDocumento> getPproAnexoDocumentoCollection() {
        return pproAnexoDocumentoCollection;
    }

    public void setPproAnexoDocumentoCollection(Collection<PproAnexoDocumento> pproAnexoDocumentoCollection) {
        this.pproAnexoDocumentoCollection = pproAnexoDocumentoCollection;
    }

    public PproTipoDocumento getDocTdocId() {
        return docTdocId;
    }

    public void setDocTdocId(PproTipoDocumento docTdocId) {
        this.docTdocId = docTdocId;
    }

    public PproUsuario getDocUsuIngresa() {
        return docUsuIngresa;
    }

    public void setDocUsuIngresa(PproUsuario docUsuIngresa) {
        this.docUsuIngresa = docUsuIngresa;
    }

    public PproUsuario getDocUsuAutoriza() {
        return docUsuAutoriza;
    }

    public void setDocUsuAutoriza(PproUsuario docUsuAutoriza) {
        this.docUsuAutoriza = docUsuAutoriza;
    }

    public PproUsuario getDocUsuModifica() {
        return docUsuModifica;
    }

    public void setDocUsuModifica(PproUsuario docUsuModifica) {
        this.docUsuModifica = docUsuModifica;
    }

    public PproProveedor getDocProvId() {
        return docProvId;
    }

    public void setDocProvId(PproProveedor docProvId) {
        this.docProvId = docProvId;
    }

    public PproEstadoDocumento getDocEdocId() {
        return docEdocId;
    }

    public void setDocEdocId(PproEstadoDocumento docEdocId) {
        this.docEdocId = docEdocId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PproDocumento)) {
            return false;
        }
        PproDocumento other = (PproDocumento) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ppro.modelo.PproDocumento[ docId=" + docId + " ]";
    }

    @XmlTransient
    public Collection<PproFactura> getPproFacturaCollection() {
        return pproFacturaCollection;
    }

    public void setPproFacturaCollection(Collection<PproFactura> pproFacturaCollection) {
        this.pproFacturaCollection = pproFacturaCollection;
    }

    public String getDocNombre() {
        return docNombre;
    }

    public void setDocNombre(String docNombre) {
        this.docNombre = docNombre;
    }

    public Date getDocFechaIngreso() {
        return docFechaIngreso;
    }

    public void setDocFechaIngreso(Date docFechaIngreso) {
        this.docFechaIngreso = docFechaIngreso;
    }

    public PproFormaPago getDocFormPagId() {
        return docFormPagId;
    }

    public void setDocFormPagId(PproFormaPago docFormPagId) {
        this.docFormPagId = docFormPagId;
    }

}
