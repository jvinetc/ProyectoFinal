/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppro.contoller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import ppro.modelo.PproDocumento;
import ppro.modelo.PproEstadoDocumento;
import ppro.modelo.PproUsuario;
import ppro.servicio.DocumentoServicio;
import ppro.servicio.EstadoDocServicio;

/**
 *
 * @author casa
 */
@ManagedBean
public class PagarController {

    @EJB
    private DocumentoServicio documentoServicio;

    @EJB
    private EstadoDocServicio estadoDocServicio;
    private final String pathAbsoluto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    private final String destino = "resources\\tmp";
    private final String destinoNominas = "\\nominas\\";

    @ManagedProperty(value = "#{pproDocumento}")
    private PproDocumento pproDocumento;

    @ManagedProperty(value = "#{pproEstadoDocumento}")
    private PproEstadoDocumento pproEstadoDocumento;
    PproUsuario pproUsuario = (PproUsuario) FacesContext.
            getCurrentInstance().getExternalContext().
            getSessionMap().get("pproUsuario");

    private List<PproDocumento> listaSeleccion;

    public List<PproDocumento> getListaSeleccion() {
        return listaSeleccion;
    }

    public void setListaSeleccion(List<PproDocumento> listaSeleccion) {
        this.listaSeleccion = listaSeleccion;
    }

    public PproDocumento getPproDocumento() {
        return pproDocumento;
    }

    public void setPproDocumento(PproDocumento pproDocumento) {
        this.pproDocumento = pproDocumento;
    }

    public void grabarPago() {
        if (!listaSeleccion.isEmpty()) {
            pproEstadoDocumento = estadoDocServicio.buscarEstado(3);
            for (PproDocumento pproDoc : listaSeleccion) {
                pproDoc.setDocEdocId(pproEstadoDocumento);
                pproDoc.setDocUsuAutoriza(pproUsuario);
                if (!documentoServicio.actualizar(pproDoc)) {
                    return;
                }
            }
            if(generaNomina()){
                FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Archivo generado"));
            }else{
                FacesContext.getCurrentInstance().
                                addMessage(null, new FacesMessage("Archivo no generado"));
            }
        }
    }

    public PproEstadoDocumento getPproEstadoDocumento() {
        return pproEstadoDocumento;
    }

    public void setPproEstadoDocumento(PproEstadoDocumento pproEstadoDocumento) {
        this.pproEstadoDocumento = pproEstadoDocumento;
    }

    public boolean generaNomina() {
        try {
            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat formatoNomina = new SimpleDateFormat("ddMMyyyy");
            String fechaActual = date.format(now);
            String ruta = pathAbsoluto+destino + destinoNominas + "nomina_" + fechaActual + ".dat";
            File archivo = new File(ruta);
            BufferedWriter bw;
            if (!archivo.exists()) {
                 bw = new BufferedWriter(new FileWriter(archivo));
                for (PproDocumento doc : listaSeleccion) {
                    Integer rutProv = doc.getDocProvId().getProvPerId().getPerRut();
                    int largoRut = contarNumero(rutProv);
                    String rutAux;
                    if (largoRut < 8) {
                        rutAux = "0" + rutProv.toString();
                    } else {
                        rutAux = rutProv.toString();
                    }
                    String digitoV = doc.getDocProvId().getProvPerId().getPerDigito();
                    String razonSoc = doc.getDocProvId().getProvRazonSocial();
                    razonSoc = completaCampo(razonSoc.length(), 45, razonSoc);
                    String direccion = doc.getDocProvId().getProvPerId().getPerDireccion();
                    direccion = completaCampo(direccion.length(), 39, direccion);
                    String codigoBanco = doc.getDocProvId().getPproRelEntidadProveedorCollection().iterator()
                            .next().getRepEntFinId().getEntFinCodigo();
                    String nCuenta = doc.getDocProvId().getProvNCuenta();
                    nCuenta = agregaCeros(nCuenta.length(), 20, nCuenta);
                    String tipoDoc = doc.getDocTdocId().getTdocNombre();
                    if (tipoDoc.equalsIgnoreCase("factura")) {
                        tipoDoc = "FAC";
                    } else {
                        tipoDoc = "NCR";
                    }
                    String numeroDoc = doc.getDocNumero();
                    numeroDoc = agregaCeros(numeroDoc.length(), 12, numeroDoc);
                    Integer monto = doc.getPproFacturaCollection().iterator().next().getFacMonto();
                    String montoAux = agregaCeros(contarNumero(monto), 11, monto.toString());
                    Date fechaDoc = doc.getPproFacturaCollection().iterator().next().getFacFecha();
                    String fechaVence = formatoNomina.format(fechaDoc);
                   
                    bw.write("G" + rutAux + digitoV + "     "
                            + razonSoc + direccion + "OTC" + codigoBanco
                            + nCuenta + codigoBanco + tipoDoc + numeroDoc
                            + "            " + montoAux + montoAux + "   "
                            + fechaVence + fechaVence);
                    bw.newLine();
                }
                bw.close();
            }
            if (archivo.exists()) {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public int contarNumero(int numero) {
        int cifras = 0;
        while (numero != 0) {
            numero = numero / 10;
            cifras++;
        }
        return cifras;
    }

    public String completaCampo(int largo, int largoReal, String palabra) {
        String completo;
        StringBuilder txt = new StringBuilder();
        if (largo <= largoReal) {
            int aux = largoReal - largo;
            for (int i = 0; i < aux; i++) {
                txt.append(" ");
            }
            completo = palabra + txt;
        } else {
            completo = palabra.substring(0, largoReal);
        }
        return completo;
    }

    public String agregaCeros(int largo, int largoReal, String palabra) {
        String completo;
        if (largo < largoReal) {
            int aux = largoReal - largo;
            String ceros = "";
            for (int i = 0; i < aux; i++) {
                ceros += "0";
            }
            completo = ceros+palabra;
        } else {
            completo = palabra;
        }
        return completo;
    }

}
