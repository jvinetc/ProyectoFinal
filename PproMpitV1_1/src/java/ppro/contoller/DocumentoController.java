/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.contoller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import ppro.modelo.PproAnexoDocumento;
import ppro.modelo.PproDocumento;
import ppro.modelo.PproEstadoDocumento;
import ppro.modelo.PproFactura;
import ppro.modelo.PproUsuario;
import ppro.servicio.DocumentoServicio;
import ppro.servicio.EstadoDocServicio;
import ppro.servicio.FacturaServicio;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import ppro.modelo.PproPersona;
import ppro.modelo.PproProveedor;
import ppro.modelo.PproTipoDocumento;
import ppro.modelo.PproTipoPersona;
import ppro.servicio.PersonaServicio;
import ppro.servicio.PproTipoPersonaFacade;
import ppro.servicio.ProveedorServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@SessionScoped
public class DocumentoController implements Serializable {

    @EJB
    private PproTipoPersonaFacade pproTipoPersonaFacade;

    @EJB
    private PersonaServicio personaServicio;

    @EJB
    private ProveedorServicio proveedorServicio;

    @EJB
    private DocumentoServicio documentoServicio;

    @EJB
    private EstadoDocServicio estadoDocServicio;

    @EJB
    private FacturaServicio facturaServicio;

    private final String pathAbsoluto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    private final String destino = "resources\\tmp";
    private final String destinoFacura = "\\factura\\";
    private final String destinoBoleta = "\\boleta\\";
    private final String destinoNota = "\\notaCredito\\";
    private final String destinoAnexo = "\\anexos\\";
    @ManagedProperty(value = "#{pproDocumento}")
    private PproDocumento pproDocumento;
    @ManagedProperty(value = "#{pproAnexoDocumento}")
    private PproAnexoDocumento pproAnexoDocumento;
    @ManagedProperty(value = "#{pproFactura}")
    private PproFactura pproFactura;
    @ManagedProperty(value = "#{pproEstadoDocumento}")
    private PproEstadoDocumento pproEstadoDocumento;
    @ManagedProperty(value = "#{pproPersona}")
    private PproPersona pproPersona;
    @ManagedProperty(value = "#{pproProveedor}")
    private PproProveedor pproProveedor;
    @ManagedProperty(value="#{pproTipoDocumento}")
    private PproTipoDocumento pproTipoDocumento;

    public PproTipoDocumento getPproTipoDocumento() {
        return pproTipoDocumento;
    }

    public void setPproTipoDocumento(PproTipoDocumento pproTipoDocumento) {
        this.pproTipoDocumento = pproTipoDocumento;
    }
    
    
    private Date now = new Date(System.currentTimeMillis());

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public PproEstadoDocumento getPproEstadoDocumento() {
        return pproEstadoDocumento;
    }

    public void setPproEstadoDocumento(PproEstadoDocumento pproEstadoDocumento) {
        this.pproEstadoDocumento = pproEstadoDocumento;
    }

    PproUsuario pproUsuario = (PproUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pproUsuario");
    private UploadedFile pdfFactura;
    private UploadedFile pdfOc;
    private UploadedFile pdfRecepcion;
    private UploadedFile pdfContrato;
    private UploadedFile pdfRealizado;
    private UploadedFile pdfBoleta;
    private UploadedFile pdfNotaCredito;

    //private List<PproTipoAnexo> listaAnexo = new ArrayList<>();
    private List<PproAnexoDocumento> listaAnexo = new ArrayList<>();

    private List<PproDocumento> listaDocumentos = new ArrayList<>();

    private List<PproFactura> listaFactura = new ArrayList<>();
    
    private List<PproDocumento> listaIngresado= new ArrayList<>();
    
    private List<PproDocumento> listaAutorizados= new ArrayList<>();
    
    private List<PproDocumento> listaPagados= new ArrayList<>();
    

    public PproDocumento getPproDocumento() {
        return pproDocumento;
    }

    public void setPproDocumento(PproDocumento pproDocumento) {
        this.pproDocumento = pproDocumento;
    }

    public UploadedFile getPdfOc() {
        return pdfOc;
    }

    public void setPdfOc(UploadedFile pdfOc) {
        this.pdfOc = pdfOc;
    }

    public UploadedFile getPdfRecepcion() {
        return pdfRecepcion;
    }

    public void setPdfRecepcion(UploadedFile pdfRecepcion) {
        this.pdfRecepcion = pdfRecepcion;
    }

    public UploadedFile getPdfContrato() {
        return pdfContrato;
    }

    public void setPdfContrato(UploadedFile pdfContrato) {
        this.pdfContrato = pdfContrato;
    }

    public UploadedFile getPdfRealizado() {
        return pdfRealizado;
    }

    public void setPdfRealizado(UploadedFile pdfRealizado) {
        this.pdfRealizado = pdfRealizado;
    }

    public UploadedFile getPdfBoleta() {
        return pdfBoleta;
    }

    public void setPdfBoleta(UploadedFile pdfBoleta) {
        this.pdfBoleta = pdfBoleta;
    }

    public UploadedFile getPdfNotaCredito() {
        return pdfNotaCredito;
    }

    public void setPdfNotaCredito(UploadedFile pdfNotaCredito) {
        this.pdfNotaCredito = pdfNotaCredito;
    }

    public UploadedFile getPdfFactura() {
        return pdfFactura;
    }

    public void setPdfFactura(UploadedFile pdfFactura) {
        this.pdfFactura = pdfFactura;
    }

    public PproAnexoDocumento getPproAnexoDocumento() {
        return pproAnexoDocumento;
    }

    public void setPproAnexoDocumento(PproAnexoDocumento pproAnexoDocumento) {
        this.pproAnexoDocumento = pproAnexoDocumento;
    }

    public PproFactura getPproFactura() {
        return pproFactura;
    }

    public void setPproFactura(PproFactura pproFactura) {
        this.pproFactura = pproFactura;
    }

    public List<PproAnexoDocumento> getListaAnexo() {
        return listaAnexo;
    }

    public void setListaAnexo(List<PproAnexoDocumento> listaAnexo) {
        this.listaAnexo = listaAnexo;
    }

    public void reset() {
        RequestContext.getCurrentInstance().reset("formProveedor");
        RequestContext.getCurrentInstance().reset("formProveedorNuevo");
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("El formulario fue Limpiado"));
    }

    public void grabar() throws Exception {
        
        pproDocumento.setDocTdocId(pproTipoDocumento);
        proveedorServicio.actualizarProv(pproProveedor);        
        pproDocumento.setDocProvId(pproProveedor);
        pproDocumento.setDocUsuIngresa(pproUsuario);
        pproEstadoDocumento = estadoDocServicio.buscarEstado(1);
        pproDocumento.setDocEdocId(pproEstadoDocumento);
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String fechaActual = date.format(now);
        switch (pproDocumento.getDocTdocId().getTdocNombre()) {
            case "Factura":
                if (getPdfFactura() != null) {
                    String nombreFactura = getPdfFactura().getFileName();
                    subeArchivo(nombreFactura, getPdfFactura().getInputstream(), destinoFacura, fechaActual);
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo Subido " + nombreFactura));
                    pproDocumento.setDocRuta(destino + destinoFacura + fechaActual + "@" + nombreFactura);
                    pproDocumento.setDocNombre(nombreFactura);
                    pproDocumento.setDocFechaIngreso(now);
                    if (documentoServicio.guardarDocumento(pproDocumento)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }
                    
                    pproFactura.setFacDocId(pproDocumento);
                    if (facturaServicio.grabarFactura(pproFactura)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }
                    FacesContext.getCurrentInstance().getExternalContext().redirect("escritorio.xhtml");
                } else {
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo vacio "));
                }
                break;
            case "Boleta":
                if (getPdfBoleta() != null) {
                    String nombreBoleta = getPdfBoleta().getFileName();
                    subeArchivo(nombreBoleta, getPdfBoleta().getInputstream(), destinoBoleta, fechaActual);
                    pproDocumento.setDocRuta(destino + destinoBoleta + fechaActual + "@" + nombreBoleta);
                    pproDocumento.setDocNombre(nombreBoleta);
                    pproDocumento.setDocFechaIngreso(now);
                    if (documentoServicio.guardarDocumento(pproDocumento)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo Subido " + nombreBoleta));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("escritorio.xhtml");
                } else {
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo vacio "));
                }
                break;
            case "Nota de Credito":
                if (getPdfNotaCredito() != null) {
                    String nombreNota = getPdfNotaCredito().getFileName();
                    subeArchivo(nombreNota, getPdfNotaCredito().getInputstream(), destinoNota, fechaActual);
                    pproDocumento.setDocRuta(destino + destinoNota + fechaActual + "@" + nombreNota);
                    pproDocumento.setDocNombre(nombreNota);
                    pproDocumento.setDocFechaIngreso(now);
                    if (documentoServicio.guardarDocumento(pproDocumento)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo Subido " + nombreNota));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("escritorio.xhtml");
                } else {
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo vacio "));
                }
                break;
        }
        PproUsuario usu= pproUsuario;
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pproUsuario",usu);
        RequestContext.getCurrentInstance().reset(":formProv");
    }

    public void subeArchivo(String nombreArchivo, InputStream in, String carpeta, String fechaActual) {
        try {
            OutputStream out = new FileOutputStream(new File(pathAbsoluto + destino + carpeta + fechaActual + "@" + nombreArchivo));
            int leer = 0;
            byte[] bytes = new byte[1024];
            while ((leer = in.read(bytes)) != -1) {
                out.write(bytes, 0, leer);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<PproDocumento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<PproDocumento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    @PostConstruct
    public void listarDocumenos() {
        listaFactura = facturaServicio.listaFactura();
        listaDocumentos = documentoServicio.listaDocumento();
        PproEstadoDocumento estadoDocIngresado= estadoDocServicio.buscarEstado(1);
        PproEstadoDocumento estadoDocAutorizado= estadoDocServicio.buscarEstado(2);
        PproEstadoDocumento estadoDocPagado= estadoDocServicio.buscarEstado(3);
        listaAutorizados= documentoServicio.listaPorEstado(estadoDocAutorizado);
        listaIngresado= documentoServicio.listaPorEstado(estadoDocIngresado);
        listaPagados= documentoServicio.listaPorEstado(estadoDocPagado);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pproDocumento", null);
        // FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("pproDocumento");
        //FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("pproProveedor");
    }

    public void buscaDocumento(int docId) throws IOException {
        //FacesContext.getCurrentInstance().getExternalContext().redirect("gestion.xhtml");  
        pproDocumento.setDocId(docId);
        pproDocumento = documentoServicio.buscarDoc(pproDocumento);
        //FacesContext.getCurrentInstance().getExternalContext().redirect("gestion.xhtml");
        RequestContext.getCurrentInstance().update("updateForm");
    }

    

    public void validTipo(File file) {
        String fileType;
        String nombre = file.getName();
        fileType = nombre.substring(nombre.indexOf('.', nombre.lastIndexOf('/')) + 1);
        String[] validos = new String[]{"pdf", "jpg", "png"};
        for (String valido : validos) {
            if (valido.equals(fileType)) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage("Archivo valido "));
            } else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage("Archivo invalido "));
            }
        }

    }

    public List<PproFactura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<PproFactura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public void validaExistencia() {
        pproProveedor.setProvPerId(pproPersona);
        pproDocumento.setDocProvId(pproProveedor);
        if (documentoServicio.validaDocumento(pproDocumento)) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("El documento ya existe"));

            pproDocumento.setDocNumero(null);
        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("El documento es valido"));
        }
    }

    public void grabarNuevo() throws IOException, InterruptedException {
        PproTipoPersona tipoPersona = pproTipoPersonaFacade.find(2);
        pproPersona.setPerTiperId(tipoPersona);
        pproProveedor.setProvPerId(pproPersona);
        pproDocumento.setDocProvId(pproProveedor);

        String rut = pproDocumento.getDocProvId().getProvPerId().getPerRutComp().toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
        char dv = rut.charAt(rut.length() - 1);
        pproDocumento.getDocProvId().getProvPerId().setPerRut(rutAux);
        pproDocumento.getDocProvId().getProvPerId().setPerDigito(String.valueOf(dv));
        if (personaServicio.grabarPersona(pproDocumento.getDocProvId().getProvPerId())) {
            if (!proveedorServicio.grabarProveedor(pproDocumento.getDocProvId())) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage("El proveedor no ha podido ser grabado"));
                Thread.sleep(5000);
                return;
            }
        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("La persona no ha podido ser grabada"));
            Thread.sleep(5000);
            return;
        }

        pproDocumento.setDocUsuIngresa(pproUsuario);
        pproEstadoDocumento = estadoDocServicio.buscarEstado(1);
        pproDocumento.setDocEdocId(pproEstadoDocumento);
        pproDocumento.setDocTdocId(pproTipoDocumento);
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        //java.sql.Date fechaSql = new java.sql.Date(0);
        String fechaActual = date.format(now);
        //pproDocumento.setDocFechaIngreso(fechaSql);
        switch (pproDocumento.getDocTdocId().getTdocNombre()) {
            case "Factura":
                if (getPdfFactura() != null) {
                    String nombreFactura = getPdfFactura().getFileName();
                    subeArchivo(nombreFactura, getPdfFactura().getInputstream(), destinoFacura, fechaActual);
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo Subido " + nombreFactura));
                    pproDocumento.setDocRuta(destino + destinoFacura + fechaActual + "@" + nombreFactura);
                    pproDocumento.setDocNombre(nombreFactura);
                    pproDocumento.setDocFechaIngreso(now);
                    if (documentoServicio.guardarDocumento(pproDocumento)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }
                    pproFactura.setFacDocId(pproDocumento);
                    if (facturaServicio.grabarFactura(pproFactura)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }

                    FacesContext.getCurrentInstance().getExternalContext().redirect("escritorio.xhtml");
                } else {
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo vacio "));
                }
                break;
            case "Boleta":
                if (getPdfBoleta() != null) {
                    String nombreBoleta = getPdfBoleta().getFileName();
                    subeArchivo(nombreBoleta, getPdfBoleta().getInputstream(), destinoBoleta, fechaActual);
                    pproDocumento.setDocRuta(destino + destinoBoleta + fechaActual + "@" + nombreBoleta);
                    pproDocumento.setDocNombre(nombreBoleta);
                    pproDocumento.setDocFechaIngreso(now);
                    if (documentoServicio.guardarDocumento(pproDocumento)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo Subido " + nombreBoleta));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("escritorio.xhtml");
                } else {
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo vacio "));
                }
                break;
            case "Nota de Credito":
                if (getPdfNotaCredito() != null) {
                    String nombreNota = getPdfNotaCredito().getFileName();
                    subeArchivo(nombreNota, getPdfNotaCredito().getInputstream(), destinoNota, fechaActual);
                    pproDocumento.setDocRuta(destino + destinoNota + fechaActual + "@" + nombreNota);
                    pproDocumento.setDocNombre(nombreNota);
                    pproDocumento.setDocFechaIngreso(now);
                    if (documentoServicio.guardarDocumento(pproDocumento)) {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros guardados"));
                    } else {
                        FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Registros no guardados"));
                    }
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo Subido " + nombreNota));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("escritorio.xhtml");
                } else {
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage("Archivo vacio "));
                }
                break;
        }
        RequestContext.getCurrentInstance().reset(":formProv");
    }

    public PproPersona getPproPersona() {
        return pproPersona;
    }

    public void setPproPersona(PproPersona pproPersona) {
        this.pproPersona = pproPersona;
    }

    public PproProveedor getPproProveedor() {
        return pproProveedor;
    }

    public void setPproProveedor(PproProveedor pproProveedor) {
        this.pproProveedor = pproProveedor;
    }

    public List<PproDocumento> getListaIngresado() {
        return listaIngresado;
    }

    public void setListaIngresado(List<PproDocumento> listaIngresado) {
        this.listaIngresado = listaIngresado;
    }

    public List<PproDocumento> getListaAutorizados() {
        return listaAutorizados;
    }

    public void setListaAutorizados(List<PproDocumento> listaAutorizados) {
        this.listaAutorizados = listaAutorizados;
    }

    public List<PproDocumento> getListaPagados() {
        return listaPagados;
    }

    public void setListaPagados(List<PproDocumento> listaPagados) {
        this.listaPagados = listaPagados;
    }
}
