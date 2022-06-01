package controlador;

import dao.ClienteImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;
import modelo.Cliente;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Ubigeo;

@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cli;
    private ClienteImpl dao;
    private Ubigeo ubi;
    private List<Cliente> listadoCli;

    private List listadoPais;
    private List listadoDepartamento;
    private List listadoProvincia;
    private List listadoDistrito;
//    private Map<String, String> listadoDepartamento = new HashMap<>();

    public ClienteC() {
        cli = new Cliente();
        dao = new ClienteImpl();
        ubi = new Ubigeo();
    }

    public void registrar() throws Exception {
        try {
            System.out.println("distrito: " + ubi.getDISUBI());
            
            cli.setIDUBI(dao.traerIdUbigeo(ubi.getPAISUBI(), ubi.getDEPUBI(), ubi.getPROVUBI(), ubi.getDISUBI()));
            System.out.println("distrito: " + ubi.getIDUBI());
            dao.registrar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            cli.setEstclie("A");
            dao.modificar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en modificarC" + e.getMessage());
        }
    }

    public void eliminar(Cliente cli) throws Exception {
        try {
            cli.setEstclie("I");
            dao.eliminar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en eliminarC" + e.getMessage());
        }
    }

    public void limpiar() {
        cli = new Cliente();
    }

    public void listar() {
        try {
            listadoCli = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void listarPais() {
        try {
            listadoPais = null;
            listadoPais = dao.listarPais();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void listarDep() {
        try {
            listadoDepartamento = null;
            listadoDepartamento = dao.listarDepartamento(ubi.getPAISUBI());
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void listaProv() {
        try {
            listadoProvincia = dao.listarProvincia(ubi.getDEPUBI());
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void listaDist() {
        try {
            listadoDistrito = dao.listarDistrito(ubi.getPROVUBI());
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public List<Cliente> getListadoCli() {
        return listadoCli;
    }

    public void setListadoCli(List<Cliente> listadoCli) {
        this.listadoCli = listadoCli;
    }

    public Ubigeo getUbi() {
        return ubi;
    }

    public void setUbi(Ubigeo ubi) {
        this.ubi = ubi;
    }

    public List getListadoDepartamento() {
        return listadoDepartamento;
    }

    public void setListadoDepartamento(List listadoDepartamento) {
        this.listadoDepartamento = listadoDepartamento;
    }

    public List getListadoProvincia() {
        return listadoProvincia;
    }

    public void setListadoProvincia(List listadoProvincia) {
        this.listadoProvincia = listadoProvincia;
    }

    public List getListadoDistrito() {
        return listadoDistrito;
    }

    public void setListadoDistrito(List listadoDistrito) {
        this.listadoDistrito = listadoDistrito;
    }

    public List getListadoPais() {
        return listadoPais;
    }

    public void setListadoPais(List listadoPais) {
        this.listadoPais = listadoPais;
    }

}
