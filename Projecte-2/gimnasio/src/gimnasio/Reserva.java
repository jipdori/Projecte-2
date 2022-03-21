
package gimnasio;

class Reserva {
    private int id_activitat;
    private String nie;

    public Reserva(int id_activitat, String nie) {
        this.id_activitat = id_activitat;
        this.nie = nie;
    }    

    public int getId_activitat() {
        return id_activitat;
    }

    public void setId_activitat(int id_activitat) {
        this.id_activitat = id_activitat;
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }
    
}
