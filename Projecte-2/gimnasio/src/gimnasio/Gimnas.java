
package gimnasio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DAM
 */
public class Gimnas {
    
    private String CIF;
    private String domicili;
    private ArrayList<Client> clients;
    private ArrayList<Activitat> activitats;
    private ArrayList<Reserva> reserves;
    
    
    Scanner teclat = new Scanner(System.in);
    String opcio;
    boolean sortir = false;

    /**
     *
     * @throws SQLException
     */
    
    public void gestionarGimnas() throws SQLException{
        
        do{
        System.out.println("===================== MENU PRINCIPAL =======================");
        System.out.println("|                                                          |");
        System.out.println("|                 1. MANTENIMENT CLIENTS (C,A,B,M)         |");
        System.out.println("|                 2. VISUALITZAR CLIENTS                   |");
        System.out.println("|                 3. VISUALITZAR ACTIVITATS                |");
        System.out.println("|                 4. SORTIR                                |");
        System.out.println("|                                                          |");
        System.out.println("============================================================");
        
        opcio = teclat.next();
        
        switch (opcio.charAt(0)) {

                case '1':
                    System.out.println("Et trobes en manteniment de clients");
                    mantenimentClients();
                    break;
                case '2':
                    System.out.println("Et trobes en actualitzar stock");
                    visualitzarClients();
                    break;
                case '3':
                    System.out.println("Et trobes en preparar comandes");
                    visualitzarActivitats();
                    break;
                case '4':
                    System.out.println("Fins una altra.");
                    sortir = true;
                    break;

                default:
                    System.out.println("FORMAT ERRONI");
            }
        }while(!sortir);
    }
    
    private void mantenimentClients() throws SQLException {
        
        do{
        System.out.println("============== MANTENIMENT CLIENTS =============");
        System.out.println("|                                              |");
        System.out.println("|         1. CONSULTA clients                  |");
        System.out.println("|         2. CONSULTA client                   |");
        System.out.println("|         3. ALTA clients                      |");
        System.out.println("|         4. BAIXA clients                     |");
        System.out.println("|         5. MODIFICAR clients                 |");
        System.out.println("|         6. Torna al menú principal           |");
        System.out.println("|                                              |");
        System.out.println("================================================");
        System.out.println("\nTRIA UNA OPCIÓ: ");

        int opcio = teclat.nextInt();
        teclat.nextLine();

        switch (opcio) {
            case 1:
                consultaClients();
                break;
            case 2:
                consultaClient();
                break;
            case 3:
                altaClients();
                break;
            case 4:
                baixaClients();
                break;
            case 5:
                modificaClients();
                break;
            case 6:
                gestionarGimnas();    
            default:
                    System.out.println("FORMAT ERRONI");    
        }
       }while(!sortir); 
    }
    
    private void consultaClients() {
        
        System.out.println("Estas accedint a CONSULTAR CLIENTS");

        try {
            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();

            System.out.println("\nMOSTRANT TOTS ELS CLIENTS...");
            String query = "SELECT * FROM clients";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            System.out.println("=====================================================================================================================================================================================================================");
            System.out.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-25s", "NIE", "NOM", "COGNOM", "DATA NAIXEMENT", "TELEFON", "CONDICIO FISICA", "DIRECCIO", "EMAIL", "CCC", "COMUNICACIO");
            System.out.println("");
            System.out.println("=====================================================================================================================================================================================================================");

            while (rs.next()) {
                String nie = rs.getString("nie");
                String nom = rs.getString("nom");
                String cognom = rs.getString("cognom");
                Date dataNaixement = rs.getDate("dataNaixement");
                String telefon = rs.getString("telefon");
                String condiciofisica = rs.getString("condiciofisica");
                String direccio = rs.getString("direccio");
                String email = rs.getString("email");
                String ccc = rs.getString("ccc");
                String comunicacio = rs.getString("comunicacio");

                System.out.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-25s\n", nie, nom, cognom, dataNaixement, telefon, condiciofisica, direccio, email, ccc, comunicacio);

            }
            ps.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }

        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
        
    }
    
    private static void consultaClient() throws SQLException {
        Client c = new Client();
        
        c.consultaClient();
    }

    private static void altaClients() throws SQLException {
        
        Client c = new Client();
        
        c.altaClient();
        
    }

    private void baixaClients() {
        
        System.out.println("Escriu el nie del Client a donar de baixa:");

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ironbody", "root", "");

            String query = "DELETE FROM clients WHERE nie = ?";
            PreparedStatement st = conn.prepareStatement(query);

            opcio = teclat.next();
            System.out.println("Donant de baixa: " + opcio);
            st.setString(1, (opcio));

            st.executeUpdate();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }

        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
    }
    
    private static void modificaClients() throws SQLException {
        
        Client c = new Client();
        
        c.modificaClient();
        
    }
    
    private void visualitzarClients() throws SQLException {
        
        do{
        System.out.println("============== MANTENIMENT CLIENTS =============");
        System.out.println("|                                              |");
        System.out.println("|         1. ORDENAT per Cognoms               |");
        System.out.println("|         2. ORDENAT per Edats                 |");
        System.out.println("|         3. ORDENAT per Reserves              |");
        System.out.println("|         4. Torna al menú principal           |");
        System.out.println("|                                              |");
        System.out.println("================================================");
        System.out.println("\nTRIA UNA OPCIÓ: ");

        int opcio = teclat.nextInt();
        teclat.nextLine();

        switch (opcio) {
            
            case 1:
                ordenatCognoms();
                break;
            case 2:
                ordenatEdats();
                break;
            case 3:
                ordenatReserves();
                break;
            case 4:
                gestionarGimnas();     
           
        }
      }while(!sortir);
    }

    private void ordenatCognoms() throws SQLException {
        
        Client c = new Client();
        
        clients = c.ordenarCognoms();
        
        visualitzarClientss();
        
        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
        
    }
    
    private void ordenatEdats() throws SQLException {
        
        Client c = new Client();
        
        clients = c.ordenarEdats();
        
        visualitzarClientss();
        
        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
        
    }
    
    /**
     *
     * @throws SQLException
     */
    public void visualitzarClientss() throws SQLException{
        
        var num = 1;
        
        if(num<=5){
            System.out.println(clients);
        }else{
            System.out.println("error");
        }
        
    }
    
    private void ordenatReserves() throws SQLException {
        
        Client c = new Client();
        
        clients = c.ordenarReserves();
        
        visualitzarClientss();
        
        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
        
    }

    private void visualitzarActivitats() throws SQLException {
        
        Activitat a = new Activitat();
        
        activitats = a.ordenarReservas();
        
        visualitzarActivitatss();
        
        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
        
    }
    
    /**
     *
     * @throws SQLException
     */
    public void visualitzarActivitatss() throws SQLException{
        
        var num = 1;
        
        if(num<=5){
            System.out.println(activitats);
        }else{
            System.out.println("error");
        }
        
    }
    
}
