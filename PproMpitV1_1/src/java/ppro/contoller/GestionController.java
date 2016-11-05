/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.contoller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import ppro.modelo.PproAnexoDocumento;
import ppro.modelo.PproDocumento;
import ppro.modelo.PproUsuario;
import ppro.servicio.AnexoServicio;
import ppro.servicio.DocumentoServicio;

/**
 *
 * @author casa
 */
@ManagedBean
@ViewScoped
public class GestionController {

    @EJB
    private DocumentoServicio documentoServicio;

    @EJB
    private AnexoServicio anexoServicio;

    @ManagedProperty(value = "#{pproDocumento}")
    private PproDocumento pproDocumento;

    private final String pathAbsoluto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    private final String destino = "resources\\tmp";
    private final String destinoFacura = "\\factura\\";
    private final String destinoBoleta = "\\boleta\\";
    private final String destinoNota = "\\notaCredito\\";
    private final String destinoAnexo = "\\anexos\\";

    private UploadedFile pdfFactura;
    private UploadedFile pdfBoleta;
    private UploadedFile pdfNotaCredito;
    private UploadedFile pdfAnexo;

    public PproDocumento getPproDocumento() {
        return pproDocumento;
    }

    public void setPproDocumento(PproDocumento pproDocumento) {
        this.pproDocumento = pproDocumento;
    }

    public void onCancel() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("La edicion fue cancelada"));
    }

    public void onSave(AjaxBehaviorEvent event) throws IOException {
        File antiguo = new File(pathAbsoluto + pproDocumento.getDocRuta());
        if (antiguo.exists()) {
            if (antiguo.delete()) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage("El archivo "
                                + pproDocumento.getDocNombre() + " fue eliminado"));
            }
        }
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String fechaActual = date.format(now);
        subeArchivo(pdfFactura.getFileName(), pdfFactura.getInputstream(), destinoFacura, fechaActual);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Archivo Subido " + pdfFactura.getFileName()));
        pproDocumento.setDocRuta(destino + destinoFacura + fechaActual + "@" + pdfFactura.getFileName());
        pproDocumento.setDocNombre(pdfFactura.getFileName());
        PproUsuario pproUsuario = (PproUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pproUsuario");
        pproDocumento.setDocUsuModifica(pproUsuario);
        if (documentoServicio.actualizar(pproDocumento)) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("El documento fue actualizado con exito"));
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        pdfFactura = event.getFile();
        FacesMessage message = new FacesMessage("Archivo seleccionado", event.getFile().getFileName());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowEdit(RowEditEvent event) throws IOException {
        PproAnexoDocumento aneDoc = (PproAnexoDocumento) event.getObject();
        File anexo = new File(pathAbsoluto + aneDoc.getAneRuta());
        if (anexo.exists()) {
            if (anexo.delete()) {
                FacesMessage msg = new FacesMessage("El documneto ha sido borrado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String fechaActual = date.format(now);
        subeArchivo(pdfAnexo.getFileName(), pdfAnexo.getInputstream(), destinoAnexo, fechaActual);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Archivo Subido " + pdfAnexo.getFileName()));
        aneDoc.setAneRuta(destino + destinoAnexo + fechaActual + "@" + pdfAnexo.getFileName());
        aneDoc.setAneNombre(pdfAnexo.getFileName());
        if (anexoServicio.actulizarAexo(aneDoc)) {
            FacesMessage msg = new FacesMessage("Documento editado  " + aneDoc.getAneNombre());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void handleAnexos(FileUploadEvent event) {
        pdfAnexo = event.getFile();
        FacesMessage message = new FacesMessage("Archivo seleccionado", event.getFile().getFileName());
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public UploadedFile getPdfFactura() {
        return pdfFactura;
    }

    public void setPdfFactura(UploadedFile pdfFactura) {
        this.pdfFactura = pdfFactura;
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

    public UploadedFile getPdfAnexo() {
        return pdfAnexo;
    }

    public void setPdfAnexo(UploadedFile pdfAnexo) {
        this.pdfAnexo = pdfAnexo;
    }

}
