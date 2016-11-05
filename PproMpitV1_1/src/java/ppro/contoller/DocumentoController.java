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
import ppro.servicio.AnexoServicio;
import ppro.servicio.DocumentoServicio;
import ppro.servicio.EstadoDocServicio;
import ppro.servicio.FacturaServicio;
import ppro.servicio.TipoAnexoServicio;
import ppro.servicio.TipoDocumentoServicio;
import java.io.File;
import java.io.IOException;
import javax.el.ELContextListener;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import ppro.modelo.PproPersona;
import ppro.modelo.PproProveedor;
import ppro.modelo.PproTipoPersona;
import ppro.servicio.PersonaServicio;
import ppro.servicio.PproTipoPersonaFacade;
import ppro.servicio.ProveedorServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@ViewScoped
public class DocumentoController {

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
    private AnexoServicio anexoServicio;

    @EJB
    private FacturaServicio facturaServicio;

    @EJB
    private TipoDocumentoServicio tipoDocumentoServicio;

    @EJB
    private TipoAnexoServicio tipoAnexoServicio;
    

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
        pproDocumento.setDocUsuIngresa(pproUsuario);
        pproEstadoDocumento = estadoDocServicio.buscarEstado(1);
        pproDocumento.setDocEdocId(pproEstadoDocumento);
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        //SimpleDateFormat fechaSql= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //java.sql.Date fechaSql = new java.sql.Date(0);
        String fechaActual = date.format(now);
        //String fechaGuarda= fechaSql.format(now);
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
                    if (pproDocumento.getDocProvId().getProvTiproId().getTiproId() == 1) {
                        if (getPdfOc() != null) {
                            String nombreOc = getPdfOc().getFileName();
                            subeArchivo(nombreOc, getPdfOc().getInputstream(), destinoAnexo, fechaActual);
                            pproAnexoDocumento.setAneTanId(tipoAnexoServicio.
                                    porTipoProv(pproDocumento.getDocProvId(), "Orden de Compra"));
                            pproAnexoDocumento.setAneRuta(destino + destinoAnexo + fechaActual + "@" + nombreOc);
                            pproAnexoDocumento.setAneDocId(pproDocumento);
                            pproAnexoDocumento.setAneNombre(nombreOc);
                            if (anexoServicio.grabarAnexo(pproAnexoDocumento)) {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros guardados"));
                            } else {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros no guardados"));
                            }
                            FacesContext.getCurrentInstance().
                                    addMessage(null, new FacesMessage("Archivo Subido " + nombreOc));
                        }
                        if (getPdfRecepcion() != null) {
                            String nombreRecepcion = getPdfRecepcion().getFileName();
                            subeArchivo(nombreRecepcion, getPdfRecepcion().getInputstream(), destinoAnexo, fechaActual);
                            FacesContext.getCurrentInstance().
                                    addMessage(null, new FacesMessage("Archivo Subido " + nombreRecepcion));
                            pproAnexoDocumento.setAneTanId(tipoAnexoServicio.
                                    porTipoProv(pproDocumento.getDocProvId(), "Recepcion Conforme"));
                            pproAnexoDocumento.setAneRuta(destino + destinoAnexo + fechaActual + "@" + nombreRecepcion);
                            pproAnexoDocumento.setAneNombre(nombreRecepcion);
                            pproAnexoDocumento.setAneDocId(pproDocumento);
                            if (anexoServicio.grabarAnexo(pproAnexoDocumento)) {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros guardados"));
                            } else {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros no guardados"));
                            }
                        }
                    } else if (pproDocumento.getDocProvId().getProvTiproId().getTiproId() == 2) {
                        if (getPdfContrato() != null) {
                            String nombreContrato = getPdfContrato().getFileName();
                            subeArchivo(nombreContrato, getPdfContrato().getInputstream(), destinoAnexo, fechaActual);
                            FacesContext.getCurrentInstance().
                                    addMessage(null, new FacesMessage("Archivo Subido " + nombreContrato));
                            pproAnexoDocumento.setAneTanId(tipoAnexoServicio.
                                    porTipoProv(pproDocumento.getDocProvId(), "Contrato"));
                            pproAnexoDocumento.setAneRuta(destino + destinoAnexo + fechaActual + "@" + nombreContrato);
                            pproAnexoDocumento.setAneDocId(pproDocumento);
                            pproAnexoDocumento.setAneNombre(nombreContrato);
                            if (anexoServicio.grabarAnexo(pproAnexoDocumento)) {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros guardados"));
                            } else {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros no guardados"));
                            }

                        }
                        if (getPdfRealizado() != null) {
                            String nombreRealizado = getPdfRealizado().getFileName();
                            subeArchivo(nombreRealizado, getPdfRealizado().getInputstream(), destinoAnexo, fechaActual);
                            FacesContext.getCurrentInstance().
                                    addMessage(null, new FacesMessage("Archivo Subido " + nombreRealizado));
                            pproAnexoDocumento.setAneTanId(tipoAnexoServicio.
                                    porTipoProv(pproDocumento.getDocProvId(), "Conformidad"));
                            pproAnexoDocumento.setAneRuta(destino + destinoAnexo + fechaActual + "@" + nombreRealizado);
                            pproAnexoDocumento.setAneNombre(nombreRealizado);
                            pproAnexoDocumento.setAneDocId(pproDocumento);
                            if (anexoServicio.grabarAnexo(pproAnexoDocumento)) {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros guardados"));
                            } else {
                                FacesContext.getCurrentInstance().
                                        addMessage(null, new FacesMessage("Registros no guardados"));
                            }
                        }
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
        RequestContext.getCurrentInstance().reset("formProveedor");
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

    public void calculaIva() {
        double iva = 0.19;
        double neto = (double) pproFactura.getFacNeto();
        double valorIva = neto * iva;
        double total = pproFactura.getFacNeto() + valorIva;
        pproFactura.setFacIva((int) valorIva);
        pproFactura.setFacMonto((int) total);
        //RequestContext.getCurrentInstance().update("formProveedorNuevo:iva formProveedorNuevo:monto");
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
        PproTipoPersona tipoPersona= pproTipoPersonaFacade.find(2);
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
        }else{
            FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage("La persona no ha podido ser grabada"));
                Thread.sleep(5000);
                return;
        }

        pproDocumento.setDocUsuIngresa(pproUsuario);
        pproEstadoDocumento = estadoDocServicio.buscarEstado(1);
        pproDocumento.setDocEdocId(pproEstadoDocumento);
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
        RequestContext.getCurrentInstance().reset("formProveedorNuevo");
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
}
