
package gimnasio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

class Activitat {
    private int id_activitat;
    private String descripcio;
    private int durada;
    private LocalDate data;
    private Time hora;

    public Activitat(int id_activitat, String descripcio, int durada, LocalDate data, Time hora) {
        this.id_activitat = id_activitat;
        this.descripcio = descripcio;
        this.durada = durada;
        this.data = data;
        this.hora = hora;
    }

    Activitat() {
        
    }

    public int getId_activitat() {
        return id_activitat;
    }

    public void setId_activitat(int id_activitat) {
        this.id_activitat = id_activitat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getDurada() {
        return durada;
    }

    public void setDurada(int durada) {
        this.durada = durada;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    
    private void cargarDatosDeReservaEnSentencia(PreparedStatement ps) throws SQLException{
        
        ps.setInt(1, this.id_activitat);
        ps.setString(2, this.descripcio);
        ps.setInt(3, this.durada);
        ps.setTime(4, this.hora);
        ps.setString(5, this.data.toString());

    }
    
    private void cargarDatosDeSentenciaEnReserva(ResultSet rs) throws SQLException{
         
        this.setId_activitat(rs.getInt("id_activitat"));
        this.setDescripcio(rs.getString("descripcio"));
        this.setDurada(rs.getInt("durada"));
        this.setHora(rs.getTime("hora"));
        
        this.setData(data);rs.getDate("data").toLocalDate();
        
    }
    
    public ArrayList<Activitat> ordenarReservas() throws SQLException {
      
        System.out.println("Estas accedint a CONSULTAR CLIENTS ordenats per Reserves");
        ArrayList<Activitat> activitats = new ArrayList();

        try {
            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();
            
            System.out.println("\nMOSTRANT TOTES LES ACTIVITATS...");
            String query = "select COUNT(c.nie),c.nie from clients c JOIN realitzen r ON c.nie=r.nie JOIN activitats a ON  r.id_activitat=a.id_activitat GROUP BY c.nie ORDER BY count(c.nie) DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                Activitat a = new Activitat();
                a.cargarDatosDeSentenciaEnReserva(rs);
                activitats.add(a);
            }
            ps.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
        
        return activitats;
        
    }

    @Override
    public String toString() {
        System.out.println("====================================================================================================");
        System.out.format("%-20s %-20s %-20s %-20s %-25s", "ID ACTIVITAT", "DESCRIPCIO", "DURADA", "HORA", "DATA");
        System.out.println("");
        System.out.println("====================================================================================================");
        System.out.format("%-20s %-20s %-20s %-20s %-25s\n", id_activitat, descripcio, durada, hora, data);
        return null;
    }

    
}
