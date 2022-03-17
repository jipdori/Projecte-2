
package gimnasio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
    
    
    static Scanner teclat = new Scanner(System.in);
    static String opcio;
    
    //ArrayList<Client> clients;

    /**
     *
     * @throws SQLException
     */
    
    public void gestionarGimnas() throws SQLException{
        
        Scanner teclat = new Scanner(System.in);

        boolean sortir = false;
        String opcio;
        
        System.out.println("===================== MENU PRINCIPAL =======================");
        System.out.println("|                                                          |");
        System.out.println("|                 1. MANTENIMENT CLIENTS (C,A,B,M)         |");
        System.out.println("|                 2. VISUALITZAR CLIENTS                   |");
        System.out.println("|                 3. VISUALITZAR ACTIVITATS                |");
        System.out.println("|                 4. GUARDAR FITXER / SORTIR               |");
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
                    guardarFitxer();
                    sortir = true;
                    break;

                default:
                    System.out.println("FORMAT ERRONI");
            }
        
    }
    
    private void mantenimentClients() throws SQLException {
        
        System.out.println("============== MANTENIMENT CLIENTS =============");
        System.out.println("|                                              |");
        System.out.println("|         1. CONSULTA clients                  |");
        System.out.println("|         2. CONSULTA client                   |");
        System.out.println("|         3. ALTA clients                      |");
        System.out.println("|         4. BAIXA clients                     |");
        System.out.println("|         5. MODIFICAR clients                 |");
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
        }
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

    private static void baixaClients() {
        
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

    private static void modificaClients() {
        
        System.out.println("MODIFICAR CLIENTS");
        String query = "UPDATE clients SET nom = ?, cognom = ?, dataNaixement = ?, telefon= ?, condiciofisica= ?, direccio= ?, email= ?, ccc= ?, comunicacio= ?"
                + "WHERE nie = ?";

        try {

            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();

            PreparedStatement ps = con.prepareStatement(query);

            System.out.println("Nom: ");
            String nom = teclat.nextLine();
            ps.setString(1, nom);

            System.out.println("Cognom: ");
            String cognom = teclat.nextLine();
            ps.setString(2, cognom);

            //------------------------------------------
            
            System.out.println("DataNaixement: ");
            int dataNaixement = teclat.nextInt();
            ps.setInt(3, dataNaixement);

            //------------------------------------------
            
            System.out.println("Telefon: ");
            String telefon = teclat.nextLine();
            ps.setString(4, telefon);

            System.out.println("CondicioFisica: ");
            String condiciofisica = teclat.nextLine();
            ps.setString(5, condiciofisica);
            
            System.out.println("Direccio: ");
            String direccio = teclat.nextLine();
            ps.setString(6, direccio);
            
            System.out.println("Email: ");
            String email = teclat.nextLine();
            ps.setString(7, email);
            
            System.out.println("ccc: ");
            String ccc = teclat.nextLine();
            ps.setString(8, ccc);
            
            System.out.println("Comunicació: ");
            String comunicacio = teclat.nextLine();
            ps.setString(9, comunicacio);

            System.out.println("nie: ");
            String nie = teclat.nextLine();
            ps.setString(10, nie);
            teclat.nextLine();

            System.out.println("Modificant client: ");

            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }

        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
        
    }
    
    private void visualitzarClients() throws SQLException {
        
        System.out.println("============== MANTENIMENT CLIENTS =============");
        System.out.println("|                                              |");
        System.out.println("|         1. ORDENAT per Cognoms               |");
        System.out.println("|         2. ORDENAT per Edats                 |");
        System.out.println("|         3. ORDENAT per Reserves              |");
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
           
        }
        
    }

    private void ordenatCognoms() throws SQLException {
        
        Client c = new Client();
        
        clients = c.ordenarCognoms();
        
        visualitzarClientss();
        
    }
    
    private void ordenatEdats() throws SQLException {
        
        Client c = new Client();
        
        clients = c.ordenarEdats();
        
        visualitzarClientss();
        
    }
    
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
        
    }

    private static void visualitzarActivitats() {
        
    }

    private static void guardarFitxer() {
        
    }
    
}
