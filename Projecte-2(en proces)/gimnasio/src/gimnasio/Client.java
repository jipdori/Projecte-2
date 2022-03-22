
package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author DAM
 */
public class Client {
    
    Scanner teclat = new Scanner(System.in);
    
    private Nie NIE;
    private String nom;
    private String cognom;
    private LocalDate dataNaixement;
    private Telefon telefon;
    private String condiciofisica;
    private String direcció;
    private CorreuElectronic email;
    private CompteBancari CCC;
    private String comunicacio;
    
    private int id_activitat;

    /**
     *
     * @param NIE
     * @param nom
     * @param cognom
     * @param dataNaixement
     * @param telefon
     * @param condiciofisica
     * @param direcció
     * @param email
     * @param CCC
     * @param comunicacio
     */
    public Client(String NIE, String nom, String cognom, LocalDate dataNaixement, String telefon, String condiciofisica, String direcció, String email, String CCC, String comunicacio) {
        NIE = NIE;
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = dataNaixement;
        telefon = telefon;
        this.condiciofisica = condiciofisica;
        this.direcció = direcció;
        email = email;
        CCC = CCC;
        this.comunicacio = comunicacio;
    } 

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @param cognom
     */
    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    /**
     *
     * @param dataNaixement
     */
    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    /**
     *
     * @param telefon
     */
    public void setTelefon(Telefon telefon) {
        this.telefon = telefon;
    }

    /**
     *
     * @param condiciofisica
     */
    public void setCondiciofisica(String condiciofisica) {
        this.condiciofisica = condiciofisica;
    }

    /**
     *
     * @param direcció
     */
    public void setDirecció(String direcció) {
        this.direcció = direcció;
    }

    /**
     *
     * @param email
     */
    public void setEmail(CorreuElectronic email) {
        this.email = email;
    }

    /**
     *
     * @param CCC
     */
    public void setCCC(CompteBancari CCC) {
        this.CCC = CCC;
    }

    /**
     *
     * @param comunicacio
     */
    public void setComunicacio(String comunicacio) {
        this.comunicacio = comunicacio;
    }

    /**
     *
     * @return
     */
    public Nie getNIE() {
        return NIE;
    }

    /**
     *
     * @return
     */
    public Telefon getTelefon() {
        return telefon;
    }

    /**
     *
     * @return
     */
    public CorreuElectronic getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public CompteBancari getCCC() {
        return CCC;
    }
    
    Client() {
        
    }

    public int getId_activitat() {
        return id_activitat;
    }

    public void setId_activitat(int id_activitat) {
        this.id_activitat = id_activitat;
    }
    
    
    
    private void cargarDatosDeClienteEnSentencia(PreparedStatement ps) throws SQLException{
        
        ps.setString(1, this.NIE.getNie());
        ps.setString(2, this.nom);
        ps.setString(3, this.cognom);
        ps.setString(4, this.telefon.getTelefon());
        ps.setString(5, this.condiciofisica);
        ps.setString(6, this.direcció);
        ps.setString(7, this.email.getEmail());
        ps.setString(8, this.CCC.getCCC());
        ps.setString(9, this.comunicacio);
        ps.setString(10, this.dataNaixement.toString());

    }
    
    private void cargarDatosDeSentenciaEnClient(ResultSet rs) throws SQLException{
        
        this.setNIE(new Nie(rs.getString("nie")));
        this.setNom(rs.getString("nom"));
        this.setCognom(rs.getString("cognom"));
        this.setTelefon(new Telefon(rs.getString("telefon")));
        this.setCondiciofisica(rs.getString("condiciofisica"));
        this.setDirecció(rs.getString("direccio"));
        this.setEmail(new CorreuElectronic(rs.getString("email")));
        this.setCCC(new CompteBancari(rs.getString("CCC")));
        this.setComunicacio(rs.getString("comunicacio"));
        
        this.setDataNaixement(rs.getDate("dataNaixement").toLocalDate());
        calcularEdat();
        
    }
    
    void consultaClient() throws SQLException {
        
        Scanner teclat = new Scanner(System.in);
        System.out.println("CONSULTA CLIENT");
        System.out.println("Introdueix NIE");
        String nie = teclat.next();
        
        Client client = consultaClientBD(nie);
        
        if(client != null){
            System.out.println("Dades del client");
            System.out.println(client);
        } else{
            System.out.println("Client no trobat");
        }
        
    }
    
    /**
     *
     * @param nie
     * @return
     * @throws SQLException
     */
    public Client consultaClientBD(String nie) throws SQLException{
        
        Connexiobd conn = new Connexiobd();
        Connection con = conn.conectar();
        String consulta = "SELECT * FROM clients WHERE nie = ?";
        PreparedStatement sentencia = con.prepareStatement(consulta);
        
        sentencia.setString(1,nie);
        
        ResultSet rs = sentencia.executeQuery();
        
        if (rs.next()){
            cargarDatosDeSentenciaEnClient(rs);
            return this;
        }
        
        return null;
        
    }
    
    /**
     *
     * @throws SQLException
     */
    public void altaClient() throws SQLException{
        
        Scanner teclat = new Scanner(System.in);
        
        Nie nieObj = new Nie();
        boolean nieCorrecte = false;
        String nie;
        do{
            
            System.out.println("Introdueix el nie del client que vols donar d'alta: ");
            nie = teclat.next();
            
        }while(!nieObj.validarNie(nie));
        
        nieObj.setNie(nie);
        
        setNIE(nieObj);
        
        teclat.nextLine();
        
        //---------------------------------------
        
        System.out.println("Nom: ");
        String nom = teclat.nextLine();
        setNom(nom);
        
        System.out.println("Cognom: ");
        String cognom = teclat.nextLine();
        setCognom(cognom);
        
        //---------------------------------------
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        boolean dataCorrecta;
        
        do{
            dataCorrecta=true;
            System.out.println("Introdueix la Data de Naixement del client que vols donar d'alta: ");
            try{
                this.dataNaixement=LocalDate.parse(teclat.next(),formatter);
            }catch (Exception ex){
                dataCorrecta=false;
            }
        }while (!dataCorrecta);
        
        //---------------------------------------
        
        Telefon tlfObj = new Telefon();
        boolean tlfCorrecte = false;
        String telefon;
        do{
            System.out.println("Introdueix el Telefon del client que vols donar d'alta: ");
            telefon = teclat.next();
        }while(!tlfObj.validarTelefon(telefon));
        
        tlfObj.setTelefon(telefon);
        
        setTelefon(tlfObj);
        
        teclat.nextLine();
        
        //---------------------------------------
        
        System.out.println("CondicioFisica: ");
        String condiciofisica = teclat.nextLine();
        setCondiciofisica(condiciofisica);
        
        System.out.println("Direcció: ");
        String direcció = teclat.nextLine();
        setDirecció(direcció);
        
        //---------------------------------------
        
        CorreuElectronic emailObj = new CorreuElectronic();
        boolean emailCorrecte = false;
        String email;
        do{
            
            System.out.println("Introdueix el Email del client que vols donar d'alta: ");
            email = teclat.next();
            
        }while(!emailObj.validarEmail(email));
        
        emailObj.setEmail(email);
        
        setEmail(emailObj);
        
        teclat.nextLine();
        
        //---------------------------------------
        
        CompteBancari cccObj = new CompteBancari();
        boolean cccCorrecte = false;
        String ccc;
        do{
            
            System.out.println("Introdueix el CCC del client que vols donar d'alta: ");
            ccc = teclat.next();
            
        }while(!cccObj.validarCCC(ccc));
        
        cccObj.setCCC(ccc);
        
        setCCC(cccObj);
        
        teclat.nextLine();
        
        //ES6600190020961234567890
        //---------------------------------------
        
        System.out.println("Comunicació: ");
        String comunicacio = teclat.nextLine();
        setComunicacio(comunicacio);
        teclat.nextLine();
        
        altaaClient();
        
    }
    
    private void altaaClient() {
        
        System.out.println("ALTA CLIENTS");
        
        String query = "INSERT INTO clients (nie, nom, cognom, dataNaixement, telefon, condiciofisica, direccio, email, ccc, comunicacio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();

            PreparedStatement ps = con.prepareStatement(query);
           
            ps.setString(1, this.NIE.getNie());
            ps.setString(2, this.nom);
            ps.setString(3, this.cognom);
            ps.setString(4, this.dataNaixement.toString());
            ps.setString(5, this.telefon.getTelefon());
            ps.setString(6, this.condiciofisica);
            ps.setString(7, this.direcció);
            ps.setString(8, this.email.getEmail());
            ps.setString(9, this.CCC.getCCC());
            ps.setString(10, this.comunicacio);
            
            
            ps.executeUpdate();
            
            System.out.println("Donant d'alta al client: ");

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }

        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
    }
    
    public void modificaClient() throws SQLException{
        
        Scanner teclat = new Scanner(System.in);
        
        Nie nieObj = new Nie();
        boolean nieCorrecte = false;
        String nie;
        do{
            
            System.out.println("Introdueix el nie del client que vols modificar: ");
            nie = teclat.next();
            
        }while(!nieObj.validarNie(nie));
        
        nieObj.setNie(nie);
        
        setNIE(nieObj);
        
        teclat.nextLine();
        
        //---------------------------------------
        
        System.out.println("Nom: ");
        String nom = teclat.nextLine();
        setNom(nom);
        
        System.out.println("Cognom: ");
        String cognom = teclat.nextLine();
        setCognom(cognom);
        
        //---------------------------------------
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        boolean dataCorrecta;
        
        do{
            dataCorrecta=true;
            System.out.println("Introdueix la Data de Naixement del client que vols donar d'alta: ");
            try{
                this.dataNaixement=LocalDate.parse(teclat.next(),formatter);
            }catch (Exception ex){
                dataCorrecta=false;
            }
        }while (!dataCorrecta);
        
        //---------------------------------------
        
        Telefon tlfObj = new Telefon();
        boolean tlfCorrecte = false;
        String telefon;
        do{
            System.out.println("Introdueix el Telefon del client que vols donar d'alta: ");
            telefon = teclat.next();
        }while(!tlfObj.validarTelefon(telefon));
        
        tlfObj.setTelefon(telefon);
        
        setTelefon(tlfObj);
        
        teclat.nextLine();
        
        //---------------------------------------
        
        System.out.println("CondicioFisica: ");
        String condiciofisica = teclat.nextLine();
        setCondiciofisica(condiciofisica);
        
        System.out.println("Direcció: ");
        String direcció = teclat.nextLine();
        setDirecció(direcció);
        
        //---------------------------------------
        
        CorreuElectronic emailObj = new CorreuElectronic();
        boolean emailCorrecte = false;
        String email;
        do{
            
            System.out.println("Introdueix el Email del client que vols modificar: ");
            email = teclat.next();
            
        }while(!emailObj.validarEmail(email));
        
        emailObj.setEmail(email);
        
        setEmail(emailObj);
        
        teclat.nextLine();
        
        //---------------------------------------
        
        CompteBancari cccObj = new CompteBancari();
        boolean cccCorrecte = false;
        String ccc;
        do{
            
            System.out.println("Introdueix el CCC del client que vols modificar: ");
            ccc = teclat.next();
            
        }while(!cccObj.validarCCC(ccc));
        
        cccObj.setCCC(ccc);
        
        setCCC(cccObj);
        
        teclat.nextLine();
        
        //ES6600190020961234567890
        //---------------------------------------
        
        System.out.println("Comunicació: ");
        String comunicacio = teclat.nextLine();
        setComunicacio(comunicacio);
        teclat.nextLine();
        
        modificaaClient();
        
    }
    
    private void modificaaClient() {
        
        System.out.println("MODIFICAR CLIENTS");
        String query = "UPDATE clients SET nom = ?, cognom = ?, dataNaixement = ?, telefon= ?, condiciofisica= ?, direccio= ?, email= ?, ccc= ?, comunicacio= ?"
                + "WHERE nie = ?";

        try {

            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();

            PreparedStatement ps = con.prepareStatement(query);
           
            ps.setString(1, this.nom);
            ps.setString(2, this.cognom);
            ps.setString(3, this.dataNaixement.toString());
            ps.setString(4, this.telefon.getTelefon());
            ps.setString(5, this.condiciofisica);
            ps.setString(6, this.direcció);
            ps.setString(7, this.email.getEmail());
            ps.setString(8, this.CCC.getCCC());
            ps.setString(9, this.comunicacio);
            ps.setString(10, this.NIE.getNie());
            
            
            ps.executeUpdate();
            
            System.out.println("Donant d'alta al client: ");

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }

        System.out.println("\nApreta ENTER per sortir....");

        teclat.nextLine();
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Client> ordenarCognoms() throws SQLException {
      
        System.out.println("Estas accedint a CONSULTAR CLIENTS ordenats per Cognoms");
        ArrayList<Client> clients = new ArrayList();

        try {
            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();

            System.out.println("\nMOSTRANT TOTS ELS CLIENTS...");
            String query = "SELECT * FROM clients ORDER BY cognom";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) { 
                Client c = new Client();
                c.cargarDatosDeSentenciaEnClient(rs);
                clients.add(c);
            }
            ps.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }

        //System.out.println("\nApreta ENTER per sortir....");

        //teclat.nextLine();
        
        return clients;
        
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Client> ordenarEdats() throws SQLException {
      
        System.out.println("Estas accedint a CONSULTAR CLIENTS ordenats per Edats");
        ArrayList<Client> clients = new ArrayList();

        try {
            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();
            
            System.out.println("\nMOSTRANT TOTS ELS CLIENTS...");
            String query = "SELECT * FROM clients ORDER BY dataNaixement";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                Client c = new Client();
                c.cargarDatosDeSentenciaEnClient(rs);
                clients.add(c);
            }
            ps.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
        
        
        //System.out.println("\nApreta ENTER per sortir....");

        //teclat.nextLine();
        
        return clients;
        
    }
    
    /**
     *
     * @throws SQLException
     */
    public ArrayList<Client> ordenarReserves() throws SQLException {
      
        System.out.println("Estas accedint a CONSULTAR CLIENTS ordenats per Reserves");
        ArrayList<Client> clients = new ArrayList();

        try {
            Connexiobd conn = new Connexiobd();
            Connection con = conn.conectar();
            
            System.out.println("\nMOSTRANT TOTS ELS CLIENTS...");
            String query = "select COUNT(c.nie),c.nie from clients c JOIN realitzen r ON c.nie=r.nie JOIN activitats a ON  r.id_activitat=a.id_activitat GROUP BY c.nie ORDER BY count(c.nie) DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                Client c = new Client();
                c.cargarDatosDeSentenciaEnClient(rs);
                clients.add(c);
            }
            ps.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
        
        
        //System.out.println("\nApreta ENTER per sortir....");

        //teclat.nextLine();
        
        return clients;
        
    }
    
    private void setdataNaixement(LocalDate toLocalDate) {
        
    }

    private void calcularEdat() {
        
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        System.out.println("=====================================================================================================================================================================================================================");
        System.out.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-25s", "NIE", "NOM", "COGNOM", "DATA NAIXEMENT", "TELEFON", "CONDICIO FISICA", "DIRECCIO", "EMAIL", "CCC", "COMUNICACIO");
        System.out.println("");
        System.out.println("=====================================================================================================================================================================================================================");
        System.out.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-25s\n", NIE, nom, cognom, dataNaixement, telefon, condiciofisica, direcció, email, CCC, comunicacio);
        return null;
    }

    private void setNIE(Nie nie) {
        this.NIE = nie;
    }

    private void id_activitat(int aInt) {
        
    }

}
    

