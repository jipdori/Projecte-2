
package gimnasio;

import java.sql.Time;
import java.time.LocalDate;

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
    
}
