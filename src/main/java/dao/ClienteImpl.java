package dao;

//import com.mysql.jdbc.Statement;
import java.sql.Statement;
import java.util.List;
import modelo.Cliente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Ubigeo;
//import javax.resource.cci.ResultSet;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void registrar(Cliente cli) throws Exception {
        System.out.println("ID CLIENTE ES: " + cli.getIDUBI());
        try {
            String sql = "insert into cliente (NOMCLIE,APECLIE,DOCCLIE,EMACLIE,CELCLIE,ESTCLIE, IDUBI) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            
            ps.setString(1, cli.getNomclie());
            ps.setString(2, cli.getApeclie());
            ps.setString(3, cli.getDocclie());
            ps.setString(4, cli.getEmaclie());
            ps.setString(5, cli.getCelclie());
            ps.setString(6,"A");
            ps.setInt(7, cli.getIDUBI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar ClienteImpl: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Cliente cli) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "update cliente set apeclie=?,celclie=?,docclie=?,emaclie=?,estclie=?,nomclie=?, idubi=? WHERE IDCLI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getApeclie());
            ps.setString(2, cli.getCelclie());
            ps.setString(3, cli.getDocclie());
            ps.setString(4, cli.getEmaclie());
            ps.setString(5, cli.getEstclie());
            ps.setString(6, cli.getNomclie());
            ps.setInt(7, cli.getIDUBI());
            ps.setInt(8, cli.getIdcli());           
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar Cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Cliente cli) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        try {
            String sql = "update cliente set ESTCLIE=? where IDCLI=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getEstclie());
            ps.setInt(2, cli.getIdcli());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en Eliminar Cliente: " + e.getMessage());
        }
    }

    @Override
    public List listar() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Cliente> listado = null;
        Cliente cli;
        String sql = "select * from cliente ";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cli = new Cliente();
                cli.setIdcli(rs.getInt("IDCLI"));
                cli.setNomclie(rs.getString("NOMCLIE"));
                cli.setApeclie(rs.getString("APECLIE"));
                cli.setCelclie(rs.getString("CELCLIE"));
                cli.setDocclie(rs.getString("DOCCLIE"));
                cli.setEmaclie(rs.getString("EMACLIE"));
                cli.setEstclie(rs.getString("ESTCLIE"));
                cli.setIDUBI(rs.getInt("IDUBI"));
                listado.add(cli);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar Cliente " + e.getMessage());
        }
        return listado;
    }

    public List<String> listarPais() throws SQLException {
        List pais = null;
        String sql = "SELECT PAISUBI FROM UBIGEO GROUP BY PAISUBI";
        try {

            pais = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                pais.add(rs.getString("PAISUBI"));
                System.out.println("pais es este:" + rs.getString("PAISUBI"));
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Error en listar PAIS DAo : " + e.getMessage());
        }

        return pais;

    }

    public List<String> listarDepartamento(String Pais) throws SQLException {
        List departamento = null;
        String sql = "SELECT DEPUBI FROM UBIGEO WHERE PAISUBI = ? GROUP BY DEPUBI";
        try {
            departamento = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Pais);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                departamento.add(rs.getString("DEPUBI"));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en listar departamento DAo : " + e.getMessage());
        }

        return departamento;

    }

    public List<String> listarProvincia(String Departamento) throws SQLException {
        List provincia = null;
        String sql = "SELECT PROVUBI FROM UBIGEO  WHERE DEPUBI = ? GROUP BY PROVUBI ";
        try {
            System.out.println("Mi departamento es " + Departamento);
            provincia = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Departamento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                provincia.add(rs.getString("PROVUBI"));
                System.out.println("provincia es este:" + rs.getString("PROVUBI"));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en listar PROVINCIA DAo : " + e.getMessage());
        }

        return provincia;

    }

    public List<String> listarDistrito(String Provincia) throws SQLException {
        List distrito = null;
        String sql = "SELECT DISUBI FROM UBIGEO  WHERE PROVUBI =? GROUP BY DISUBI ";
        try {
            System.out.println("Mi departamento es " + Provincia);
            distrito = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Provincia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                distrito.add(rs.getString("DISUBI"));
                System.out.println("provincia es este:" + rs.getString("DISUBI"));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en listar PROVINCIA DAo : " + e.getMessage());
        }

        return distrito;

    }

    public int traerIdUbigeo(String pais, String departamento, String provincia, String distrito) {
        System.out.println("ESTE ES MI CADENA:^" + pais + departamento+provincia+distrito);
        String sql = "select IDUBI FROM UBIGEO WHERE PAISUBI = ? and DEPUBI = ?  and PROVUBI = ?  and DISUBI=? ";
        int id = 0;
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, pais);
            ps.setString(2, departamento);
            ps.setString(3, provincia);
            ps.setString(4, distrito);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDUBI");
            }
            System.out.println(" id ubigeo es:" + id);

        } catch (Exception e) {
        }

        return -1;

    }

}
